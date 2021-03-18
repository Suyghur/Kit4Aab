package com.kit4aab

import com.kit4aab.toolkit.PropertiesKit

class Config {

    var bundleToolPath = ""
    var aabPath = ""
    var ksPath = ""
    var ksPass = ""
    var ksKeyAlias = ""
    var ksKeyPass = ""
    var tmpApksPath = "./tmp.apks"


    init {
        bundleToolPath = getValue(BUNDLE_TOOL_PATH)
        aabPath = getValue(AAB_PATH)
        ksPath = getValue(KS_PATH)
        ksPass = getValue(KS_PASS)
        ksKeyAlias = getValue(KS_KEY_ALIAS)
        ksKeyPass = getValue(KS_KEY_PASS)
    }

    fun getValue(key: String): String {
        return PropertiesKit.getValue(key, CONFIG_PATH)
    }

    companion object {
        const val CONFIG_PATH = "./config.properties"
        const val BUNDLE_TOOL_PATH = "BUNDLE_TOOL_PATH"
        const val AAB_PATH = "AAB_PATH"
        const val KS_PATH = "KS_PATH"
        const val KS_PASS = "KS_PASS"
        const val KS_KEY_ALIAS = "KS_KEY_ALIAS"
        const val KS_KEY_PASS = "KS_KEY_PASS"
//        const val TMP_APKS_PATH = "TMP_APKS_PATH"
    }
}