package club.chachy.selenium.utils

internal val os: String
    get() {
        return with(System.getProperty("os.name").toLowerCase()) {
            when {
                startsWith("win") -> "win"
                contains("darwin") || contains("mac os") -> "macos"
                else -> this
            }
        }
    }