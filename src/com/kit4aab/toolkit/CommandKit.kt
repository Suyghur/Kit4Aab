package com.kit4aab.toolkit

import java.io.BufferedReader
import java.io.InputStreamReader

object CommandKit {

    fun execCmd(cmd: String): Boolean {
        println("exec command : $cmd")
        return try {
            val process = Runtime.getRuntime().exec(cmd)
            Thread {
                process.errorStream?.let { e ->
                    BufferedReader(InputStreamReader(e)).use { reader ->
                        var line = reader.readLine()
                        while (!line.isNullOrEmpty()) {
                            System.err.println(line)
                            line = reader.readLine()
                        }
                    }
                }
            }.start()

            Thread {
                process.inputStream?.let { i ->
                    BufferedReader(InputStreamReader(i)).use { reader ->
                        var line = reader.readLine()
                        while (!line.isNullOrEmpty()) {
                            println(line)
                            line = reader.readLine()
                        }
                    }
                }
            }.start()
            return process.waitFor() == 0
        } catch (e: Exception) {
            println(e.message)
            e.printStackTrace()
            false
        }
    }
}