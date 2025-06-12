package com.example.dashcam.media

import android.content.Context
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.video.FileOutputOptions
import androidx.camera.video.Quality
import androidx.camera.video.QualitySelector
import androidx.camera.video.Recorder
import androidx.camera.video.Recording
import androidx.camera.video.VideoCapture
import androidx.camera.video.VideoRecordEvent
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.io.File

actual object VideoRecorder {
    private lateinit var appContext: Context

    actual fun init(platformContext: Any?) {
        appContext = (platformContext as Context).applicationContext
    }

    actual suspend fun record(durationMillis: Int): String? {
        if (!::appContext.isInitialized) return null
        val provider = withContext(Dispatchers.Main) {
            ProcessCameraProvider.getInstance(appContext).get()
        }
        val recorder = Recorder.Builder()
            .setQualitySelector(QualitySelector.from(Quality.SD))
            .build()
        val videoCapture = VideoCapture.withOutput(recorder)
        val preview = Preview.Builder().build()

        val owner = object : LifecycleOwner {
            private val registry = LifecycleRegistry(this)
            init { registry.currentState = Lifecycle.State.CREATED }
            override fun getLifecycle(): Lifecycle = registry
            fun start() { registry.currentState = Lifecycle.State.STARTED }
            fun stop() { registry.currentState = Lifecycle.State.DESTROYED }
        }
        owner.start()
        provider.unbindAll()
        provider.bindToLifecycle(owner, CameraSelector.DEFAULT_BACK_CAMERA, preview, videoCapture)

        val dir = File(appContext.filesDir, "events").apply { mkdirs() }
        val file = File(dir, "test_${System.currentTimeMillis()}.mp4")
        val options = FileOutputOptions.Builder(file).build()
        var recording: Recording? = null
        withContext(Dispatchers.Main) {
            recording = videoCapture.output
                .prepareRecording(appContext, options)
                .start(ContextCompat.getMainExecutor(appContext)) { _: VideoRecordEvent -> }
        }
        delay(durationMillis.toLong())
        withContext(Dispatchers.Main) { recording?.stop() }
        provider.unbindAll()
        owner.stop()
        return file.absolutePath
    }
}
