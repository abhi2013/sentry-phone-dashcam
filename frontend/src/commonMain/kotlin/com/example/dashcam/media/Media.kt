package com.example.dashcam.media

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/** Displays an image from the given file path. */
@Composable
expect fun EventImage(path: String, modifier: Modifier = Modifier)

/** Simple video preview for the given file path. */
@Composable
expect fun VideoPreview(path: String, modifier: Modifier = Modifier)
