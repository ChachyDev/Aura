package club.chachy.selenium.utils

import club.chachy.selenium.utils.http.github.GithubAsset
import org.slf4j.LoggerFactory
import java.io.BufferedInputStream
import java.io.File
import java.io.FileOutputStream
import java.net.URL

private val logger = LoggerFactory.getLogger("DownloadUtil")

internal fun download(asset: GithubAsset, parent: File): File {
    logger.info("Downloading ${asset.download}...")

    val file = File(parent, asset.name)

    if (file.exists()) {
        return file
    }

    parent.mkdir()
    file.createNewFile()

    URL(asset.download).let { url ->
        BufferedInputStream(url.openStream()).use { `in` ->
            FileOutputStream(file).use { fos ->
                `in`.copyTo(fos)
            }
        }
    }

    return file
}