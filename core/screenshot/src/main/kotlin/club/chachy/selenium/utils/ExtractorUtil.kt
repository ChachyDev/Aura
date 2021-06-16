package club.chachy.selenium.utils

import club.chachy.selenium.utils.extractor.Extractor
import club.chachy.selenium.utils.extractor.targz.TarGzExtractor
import club.chachy.selenium.utils.extractor.zip.ZipExtractor
import club.chachy.selenium.utils.http.github.GithubAsset
import org.slf4j.LoggerFactory

private val logger = LoggerFactory.getLogger("Extractor")

internal fun extractor(asset: GithubAsset): Extractor {
    logger.info("Detecting required extractor")
    return if (asset.download.endsWith("zip")) {
        ZipExtractor()
    } else {
        TarGzExtractor()
    }
}