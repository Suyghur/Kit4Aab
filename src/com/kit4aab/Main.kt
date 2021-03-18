package com.kit4aab

import com.kit4aab.toolkit.CommandKit
import java.io.File

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        val config = Config()
        val tmpFile = File(config.tmpApksPath)
        if (tmpFile.exists()) {
            if (!tmpFile.delete()) {
                throw Exception("${tmpFile.name} is exists , but delete is failed")
            }
        }
        val aabFile = File(config.aabPath)
        if (!aabFile.exists()) {
            throw Exception("${aabFile.name} is not exists , please check up and try again")
        }
        val ksFile = File(config.ksPath)
        if (!ksFile.exists()) {
            throw Exception("${ksFile.name} is not exists , please check up and try again")
        }
        val bundleToolFile = File(config.bundleToolPath)
        if (!bundleToolFile.exists()) {
            throw Exception("${bundleToolFile.name} is not exists , please check up and try again")
        }

        //检查签名配置
        if (!checkKeystoreConfig(config)) {
            throw Exception("the config has some error , please check up and try again")
        }

        //aab转apks
        val cmdAab2Apks =
            "java -jar ${config.bundleToolPath} build-apks --bundle=${config.aabPath} --output=${config.tmpApksPath} --ks=${config.ksPath} --ks-pass=pass:${config.ksPass} --ks-key-alias=${config.ksKeyAlias} --key-pass=pass:${config.ksKeyPass}"
        if (!CommandKit.execCmd(cmdAab2Apks)) {
            throw Exception("aab 2 apks has some error , please check up and try again")
        }
        //安装apks
        val cmdInstallApks = "java -jar ${config.bundleToolPath} install-apks --apks=${config.tmpApksPath}"
        if (!CommandKit.execCmd(cmdInstallApks)) {
            throw Exception("install apks has some error , please check up and try again")
        }
    }

    private fun checkKeystoreConfig(config: Config): Boolean {
        if (config.ksPass.isEmpty()) {
            System.err.println("KS_PASS is empty , please check up")
            return false
        }
        if (config.ksKeyAlias.isEmpty()) {
            System.err.println("KS_PASS is empty , please check up")
            return false
        }
        if (config.ksKeyPass.isEmpty()) {
            System.err.println("KS_PASS is empty , please check up")
            return false
        }
        return true
    }
}