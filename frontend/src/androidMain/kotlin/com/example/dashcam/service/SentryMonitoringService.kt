package com.example.dashcam.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.example.dashcam.Event
import com.example.dashcam.EventType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import com.example.dashcam.Settings
import org.opencv.imgcodecs.Imgcodecs
import java.io.File
import org.opencv.android.OpenCVLoader
import org.opencv.core.Core
import org.opencv.core.CvType
import org.opencv.core.Mat
import org.opencv.imgproc.Imgproc
import org.opencv.objdetect.HOGDescriptor
import org.opencv.core.MatOfRect
import java.util.concurrent.Executors
import androidx.lifecycle.LifecycleService

/**
 * Foreground service that would monitor the camera feed using an open source
 * computer vision API such as OpenCV to detect motion or people. Detected
 * events are emitted to [sharedEvents].
 */
class SentryMonitoringService : LifecycleService() {
    private val scope = CoroutineScope(Dispatchers.Default)
    private val executor = Executors.newSingleThreadExecutor()
    private var cameraProvider: ProcessCameraProvider? = null
    private var previous: Mat? = null
    private var lastEventTime = 0L
    private val hog = HOGDescriptor().apply { setSVMDetector(HOGDescriptor.getDefaultPeopleDetector()) }

    override fun onCreate() {
        super.onCreate()
        createChannel()
        if (!OpenCVLoader.initDebug()) {
            Log.e(TAG, "OpenCV init failed")
            stopSelf()
            return
        }
        val notification: Notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Sentry Monitoring")
            .setContentText("Monitoring vehicle")
            .setSmallIcon(android.R.drawable.ic_menu_camera)
            .build()
        startForeground(1, notification)
        startCamera()
    }

    private fun startCamera() {
        val providerFuture = ProcessCameraProvider.getInstance(this)
        providerFuture.addListener({
            cameraProvider = providerFuture.get()
            val analysis = ImageAnalysis.Builder()
                .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                .build()
            analysis.setAnalyzer(executor, this::analyzeFrame)
            try {
                cameraProvider?.unbindAll()
                cameraProvider?.bindToLifecycle(this, CameraSelector.DEFAULT_BACK_CAMERA, analysis)
            } catch (e: Exception) {
                Log.e(TAG, "Camera bind failed", e)
            }
        }, ContextCompat.getMainExecutor(this))
    }

    private fun analyzeFrame(proxy: ImageProxy) {
        val mat = proxy.toMat()
        val gray = Mat()
        Imgproc.cvtColor(mat, gray, Imgproc.COLOR_RGBA2GRAY)
        val now = System.currentTimeMillis()
        if (now - lastEventTime < Settings.eventThrottleMillis.value) {
            previous?.release()
            previous = gray
            mat.release()
            proxy.close()
            return
        }
        previous?.let { prev ->
            val diff = Mat()
            Core.absdiff(prev, gray, diff)
            Imgproc.threshold(diff, diff, 30.0, 255.0, Imgproc.THRESH_BINARY)
            val count = Core.countNonZero(diff)
            var detected = false
            if (count > MOTION_THRESHOLD) {
                detected = true
                emitEvent(EventType.Motion, mat)
            }
            if (!detected && detectHuman(gray, Settings.humanSensitivity.value)) {
                detected = true
                emitEvent(EventType.Person, mat)
            }
            if (detected) {
                lastEventTime = now
            }
            diff.release()
            prev.release()
        }
        previous = gray
        mat.release()
        proxy.close()
    }

    private fun ImageProxy.toMat(): Mat {
        val yBuffer = planes[0].buffer
        val uBuffer = planes[1].buffer
        val vBuffer = planes[2].buffer

        val ySize = yBuffer.remaining()
        val uSize = uBuffer.remaining()
        val vSize = vBuffer.remaining()

        val nv21 = ByteArray(ySize + uSize + vSize)
        yBuffer.get(nv21, 0, ySize)
        vBuffer.get(nv21, ySize, vSize)
        uBuffer.get(nv21, ySize + vSize, uSize)

        val yuv = Mat(height + height / 2, width, CvType.CV_8UC1)
        yuv.put(0, 0, nv21)

        val rgba = Mat()
        Imgproc.cvtColor(yuv, rgba, Imgproc.COLOR_YUV2RGBA_NV21, 4)
        yuv.release()
        return rgba
    }

    override fun onDestroy() {
        cameraProvider?.unbindAll()
        executor.shutdown()
        previous?.release()
        super.onDestroy()
    }

    private fun createChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(CHANNEL_ID, "Sentry", NotificationManager.IMPORTANCE_LOW)
            val nm = getSystemService(NotificationManager::class.java)
            nm.createNotificationChannel(channel)
        }
    }

    private fun saveScreenshot(mat: Mat): String {
        val dir = File(filesDir, "events").apply { mkdirs() }
        val file = File(dir, "screenshot_${System.currentTimeMillis()}.jpg")
        val bgr = Mat()
        Imgproc.cvtColor(mat, bgr, Imgproc.COLOR_RGBA2BGR)
        Imgcodecs.imwrite(file.absolutePath, bgr)
        bgr.release()
        return file.absolutePath
    }

    private fun recordVideo(durationSec: Int): String {
        val dir = File(filesDir, "events").apply { mkdirs() }
        val file = File(dir, "video_${System.currentTimeMillis()}.mp4")
        // Placeholder for real video recording
        file.writeBytes(ByteArray(0))
        return file.absolutePath
    }

    private fun emitEvent(type: EventType, mat: Mat) {
        val screenshot = saveScreenshot(mat)
        val video = recordVideo(Settings.videoDurationSec.value)
        scope.launch {
            sharedEvents.emit(
                Event(
                    type = type,
                    description = if (type == EventType.Person) "Person detected" else "Motion detected",
                    timestamp = System.currentTimeMillis(),
                    screenshotPath = screenshot,
                    videoPath = video,
                )
            )
        }
    }

    private fun detectHuman(gray: Mat, sensitivity: Int): Boolean {
        val locations = MatOfRect()
        hog.detectMultiScale(gray, locations)
        val count = locations.toArray().size
        locations.release()
        return count >= sensitivity / 5
    }

    companion object {
        private const val CHANNEL_ID = "sentry_monitoring"
        private const val MOTION_THRESHOLD = 5000
        private const val TAG = "SentryService"
        val sharedEvents = MutableSharedFlow<Event>()
    }
}
