package club.chachy.screenshot.api

import org.openqa.selenium.WebDriver
import java.io.File
import java.net.URL

interface ScreenshotHandler {
    fun init()

    fun screenshot(url: URL, dest: File?, action: WebDriver.() -> Unit): File

    fun screenshot(url: String, dest: File?, action: WebDriver.() -> Unit) = screenshot(URL(url), dest, action)

    fun screenshot(url: String, dest: File?) = screenshot(URL(url), dest) {}
}