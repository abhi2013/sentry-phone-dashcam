package com.example.dashcam.media

import android.net.Uri
import android.widget.VideoView
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.net.toUri
import coil.compose.rememberAsyncImagePainter
import java.io.File

@Composable
actual fun EventImage(path: String, modifier: Modifier) {
    val uri = File(path).toUri()
    Image(
        painter = rememberAsyncImagePainter(uri),
        contentDescription = null,
        modifier = modifier
    )
}

@Composable
actual fun VideoPreview(path: String, modifier: Modifier) {
    AndroidView(modifier = modifier, factory = { ctx ->
        VideoView(ctx).apply {
            setVideoURI(Uri.fromFile(File(path)))
            setOnPreparedListener { it.isLooping = true; start() }
        }
    })
}
