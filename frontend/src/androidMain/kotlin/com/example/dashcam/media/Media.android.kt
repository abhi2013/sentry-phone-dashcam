package com.example.dashcam.media

import android.net.Uri
import android.widget.VideoView
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import coil.compose.rememberAsyncImagePainter

@Composable
actual fun EventImage(path: String, modifier: Modifier) {
    Image(
        painter = rememberAsyncImagePainter(path),
        contentDescription = null,
        modifier = modifier
    )
}

@Composable
actual fun VideoPreview(path: String, modifier: Modifier) {
    val context = LocalContext.current
    AndroidView(modifier = modifier, factory = { ctx ->
        VideoView(ctx).apply {
            setVideoURI(Uri.parse(path))
            setOnPreparedListener { it.isLooping = true; start() }
        }
    })
}
