package com.xudh.demo.util

import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.io.PrintWriter


object FileUtils {
    fun createDir(destDirName: String): Boolean {
        var destDirName = destDirName
        val dir = File(destDirName)
        if (dir.exists()) {
            println("创建目录" + destDirName + "失败，目标目录已经存在")
            return false
        }
        if (!destDirName.endsWith(File.separator)) {
            destDirName = destDirName + File.separator
        }
        //创建目录
        return if (dir.mkdirs()) {
            println("创建目录" + destDirName + "成功！")
            true
        } else {
            println("创建目录" + destDirName + "失败！")
            false
        }
    }

    fun createFile(destFileName: String): Boolean {
        val file = File(destFileName)
        if (file.exists()) {
            println("创建单个文件" + destFileName + "失败，目标文件已存在！")
            return false
        }
        if (destFileName.endsWith(File.separator)) {
            println("创建单个文件" + destFileName + "失败，目标文件不能为目录！")
            return false
        }
        //判断目标文件所在的目录是否存在
        if (!file.parentFile.exists()) { //如果目标文件所在的目录不存在，则创建父目录
            println("目标文件所在目录不存在，准备创建它！")
            if (!file.parentFile.mkdirs()) {
                println("创建目标文件所在目录失败！")
                return false
            }
        }
        //创建目标文件
        return try {
            if (file.createNewFile()) {
                println("创建单个文件" + destFileName + "成功！")
                true
            } else {
                println("创建单个文件" + destFileName + "失败！")
                false
            }
        } catch (e: IOException) {
            e.printStackTrace()
            println("创建单个文件" + destFileName + "失败！" + e.message)
            false
        }
    }
    /**
     * 文件数据写入（如果文件夹和文件不存在，则先创建，再写入）
     * @param filePath
     * @param content
     * @param flag true:如果文件存在且存在内容，则内容换行追加；false:如果文件存在且存在内容，则内容替换
     */
    fun fileLinesWrite(filePath: String, content: String?, flag: Boolean): String? {
        var filedo = "write"
        var fw: FileWriter? = null
        try {
            val file = File(filePath)
            //如果文件夹不存在，则创建文件夹
            if (!file.parentFile.exists()) {
                file.parentFile.mkdirs()
            }
            if (!file.exists()) { //如果文件不存在，则创建文件,写入第一行内容
                file.createNewFile()
                fw = FileWriter(file)
                filedo = "create"
            } else { //如果文件存在,则追加或替换内容
                fw = FileWriter(file, flag)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        val pw = PrintWriter(fw)
        pw.println(content)
        pw.flush()
        try {
            fw!!.flush()
            pw.close()
            fw.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return filedo
    }
}