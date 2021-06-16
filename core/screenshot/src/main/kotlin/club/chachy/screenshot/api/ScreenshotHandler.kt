package club.chachy.screenshot.api

import java.io.File
import java.net.URL

interface ScreenshotHandler {
    fun init()

    fun screenshot(url: URL, dest: File) : File

    fun screenshot(url: String, dest: File) = screenshot(URL(url), dest)
}