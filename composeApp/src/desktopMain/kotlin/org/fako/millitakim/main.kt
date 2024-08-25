package org.fako.millitakim

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "MilliTakim",
    ) {
        App()
    }
}