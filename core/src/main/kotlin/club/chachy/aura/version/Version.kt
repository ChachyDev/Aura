package club.chachy.aura.version

import java.io.File

object Version {
    val version =  Version::class.java.`package`?.implementationVersion
        ?: GitVersion.gitVersion(File(System.getProperty("user.dir"))) // At least attempt to get the version
}