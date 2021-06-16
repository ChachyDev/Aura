package club.chachy.screenshot

import club.chachy.screenshot.api.ScreenshotHandler
import club.chachy.selenium.SeleniumHandler
import org.slf4j.LoggerFactory
import java.io.File

object Screenshot {
    private val apis = mutableListOf<ScreenshotHandler>(SeleniumHandler())

    private val logger = LoggerFactory.getLogger(this::class.java)

    private var api: ScreenshotHandler? = null

    fun init() {
        for (a in apis) {
            try {
                a.init()
            } catch (t: Throwable) {
                continue
            }
            
            api = a
            break
        }
        
        if (api == null) {
            logger.error("There are no screenshot handlers currently available, screenshotting utilities will not work!")
        }
    }

    fun screenshot(url: String, dest: File): File {
        return api?.screenshot(url, dest) ?: error("There are no screenshotting APIs available...")
    }
}