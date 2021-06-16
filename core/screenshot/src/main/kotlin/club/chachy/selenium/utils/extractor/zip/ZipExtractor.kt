package club.chachy.selenium.utils.extractor.zip

import club.chachy.selenium.utils.extractor.Extractor
import org.rauschig.jarchivelib.ArchiveFormat
import org.rauschig.jarchivelib.ArchiverFactory
import java.io.File

class ZipExtractor : Extractor {
    private val archiver = ArchiverFactory.createArchiver(ArchiveFormat.ZIP)

    override fun extract(file: File): File {
        val dest = File(file.parentFile, file.nameWithoutExtension)
        if (dest.exists() && dest.listFiles() != null) return dest

        archiver.extract(file, dest)
        return dest
    }
}