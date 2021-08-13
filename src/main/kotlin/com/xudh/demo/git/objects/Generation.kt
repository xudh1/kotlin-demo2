package com.xudh.demo.git.objects

import com.xudh.demo.enums.ObjectTypeEnum
import com.xudh.demo.util.FileUtils
import com.xudh.demo.util.MessageDigetUtils
import com.xudh.demo.util.ZlibCompressUtils
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

fun main() {
    val content="what is up, doc?"
    val header= "${ObjectTypeEnum.BLOB.desc} ${content.length}"+Char.MIN_VALUE.toString()
    val store=header+content
    println("对象信息:${store}")
    val sha1 = MessageDigetUtils.sha1(store)
    println("加密hash值：${sha1}")
    println(sha1.substring(0,2))
    val crateFile="C:\\Users\\xudh\\Desktop\\test\\.git\\objects\\${sha1.substring(0,2)}\\${sha1.substring(2)}"
    FileUtils.createFile(crateFile)
    val outputStream = FileOutputStream(File(crateFile))
    ZlibCompressUtils.compress(store.toByteArray(),outputStream)

    val fileOutputStream = FileInputStream("C:\\Users\\xudh\\Desktop\\test\\.git\\objects\\bd\\9dbf5aae1a3862dd1526723246b20206e5fc37")
//    val fileOutputStream = FileInputStream("C:\\Users\\xudh\\Desktop\\test\\.git\\objects\\3c\\4e9cd789d88d8d89c1073707c3585e41b0e614")
    val decompress = ZlibCompressUtils.decompress(fileOutputStream.readBytes())
    val byteArrayOutputStream = ByteArrayOutputStream()
    byteArrayOutputStream.write(decompress)
    println("获取文件对象内容：${byteArrayOutputStream.toString()}")
}



