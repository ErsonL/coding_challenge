package com.wei.challenge.cartrack.utility

import java.security.MessageDigest
import java.util.concurrent.Executors

private val IO_EXECUTOR = Executors.newSingleThreadExecutor()

/**
 * Utility method to run blocks on a dedicated background thread, used for io/database work.
 */
fun ioThread(f : () -> Unit) {
    IO_EXECUTOR.execute(f)
}

fun runMD5Hash(text: String ): String {

    var result: String

    result = try {
        val md5 = MessageDigest.getInstance("MD5")
        val md5HashBytes = md5.digest(text.toByteArray()).toTypedArray()
        byteArrayToHexString(md5HashBytes)
    } catch ( e: Exception ) {
        "error: ${e.message}"
    }

    return result
}

private fun byteArrayToHexString( array: Array<Byte> ): String {

    var result = StringBuilder(array.size * 2)

    for ( byte in array ) {
        val toAppend =
            String.format("%2X", byte).replace(" ", "0")
        result.append(toAppend).append("-")
    }
    result.setLength(result.length - 1)

    return result.toString()
}

