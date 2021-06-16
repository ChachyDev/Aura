package club.chachy.selenium.utils.extractor

import java.io.File

interface Extractor {
    fun extract(file: File) : File
}