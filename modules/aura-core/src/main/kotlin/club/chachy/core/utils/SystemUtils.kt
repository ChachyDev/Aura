package club.chachy.core.utils

import club.chachy.core.CoreModule
import org.apache.commons.lang3.time.DurationFormatUtils
import java.lang.management.ManagementFactory
import kotlin.math.roundToInt

/**
 * Returns an approximate value of the currently used system memory in Mebibytes (bytes / 1024 / 1024)
 */
val programMemory get() = (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1024 / 1024

/**
 * Returns an approximate value of the currently used system memory in Mebibytes (bytes / 1024 / 1024)
 */
val totalMemory get() = Runtime.getRuntime().totalMemory() / 1024 / 1024

val cpu get() = ManagementFactory.getOperatingSystemMXBean().let { it.systemLoadAverage / it.availableProcessors }.roundToInt()

val CoreModule.humanUptime: String get() = DurationFormatUtils.formatDurationWords(System.currentTimeMillis() - start, true, true)