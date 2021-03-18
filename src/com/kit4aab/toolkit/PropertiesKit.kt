package com.kit4aab.toolkit

import java.io.File
import java.util.*
import kotlin.collections.HashMap

object PropertiesKit {

    private val cache = HashMap<String, String>()

    private fun getProperties(filePath: String): Properties {
        val proFile = Properties()
        File(filePath).inputStream().use {
            proFile.load(it)
        }
        return proFile
    }

    fun getValue(key: String, fileName: String): String {
        return if (cache.isNotEmpty() && cache.containsKey(key)) {
            cache[key].toString()
        } else {
            val value = getProperties(fileName).getProperty(key)
            cache[key] = value
            value
        }
    }
}