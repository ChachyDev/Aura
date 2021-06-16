package club.chachy.selenium.utils.extractor.targz

import club.chachy.selenium.utils.extractor.Extractor
import org.rauschig.jarchivelib.ArchiverFactory
import java.io.File

class TarGzExtractor : Extractor {
    private val archiver = ArchiverFactory.createArchiver("tar", "gz")

    override fun extract(file: File): File {
        val dest = File(file.parentFile, file.nameWithoutExtension)
        if (dest.exists() && dest.listFiles() != null) return dest

        archiver.extract(file, dest)
        return dest
    }
}