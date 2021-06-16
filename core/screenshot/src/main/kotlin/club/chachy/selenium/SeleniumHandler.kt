package club.chachy.selenium

import club.chachy.screenshot.api.ScreenshotHandler
import club.chachy.selenium.utils.download
import club.chachy.selenium.utils.extractor
import club.chachy.selenium.utils.http.github.GithubAsset
import club.chachy.selenium.utils.http.github.GithubRelease
import club.chachy.selenium.utils.http.http
import club.chachy.selenium.utils.os
import io.ktor.client.request.*
import kotlinx.coroutines.runBlocking
import org.openqa.selenium.OutputType
import org.openqa.selenium.TakesScreenshot
import org.openqa.selenium.WebDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.firefox.GeckoDriverService
import java.io.*
import java.net.URL

class SeleniumHandler : ScreenshotHandler {
    private lateinit var driver: WebDriver

    override fun init() {
        // Download gecko driver :)
        val assets =
            runBlocking { http.get<List<GithubAsset>>(http.get<List<GithubRelease>>("https://api.github.com/repos/mozilla/geckodriver/releases")[0].assets) }

        val asset = assets.find {
            it.name.contains(os) && !(it.name.endsWith("32.tar.gz") || it.name.endsWith("32.zip"))
        } ?: error("Failed to find an asset for your operating system")


        val d = extractor(asset)
            .extract(download(asset, File("drivers")))
            .listFiles()
            ?.getOrNull(0)
            ?: error("Extraction failed")
        System.setProperty(GeckoDriverService.GECKO_DRIVER_EXE_PROPERTY, d.absolutePath)
        driver = FirefoxDriver()
    }

    override fun screenshot(url: URL, dest: File, action: WebDriver.() -> Unit): File {
        driver[url.toString()]

        action(driver)
        val file = (driver as TakesScreenshot).getScreenshotAs(OutputType.FILE) ?: error("Failed to screenshot")

        BufferedInputStream(FileInputStream(file)).use { `in` ->
            BufferedOutputStream(FileOutputStream(dest)).use {
                `in`.copyTo(it)
            }
        }

        return dest
    }
}