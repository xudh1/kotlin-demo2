package com.xudh.demo.util

import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.OutputStream
import java.util.zip.DeflaterOutputStream
import java.util.zip.Inflater


object ZlibCompressUtils {
    fun compress(data: ByteArray,os:OutputStream) {
        val dos = DeflaterOutputStream(os)
        try {
            dos.write(data,0,data.size)
            dos.finish()
            dos.flush()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    /**
     * 解压缩
     *
     * @param data
     *            待压缩的数据
     * @return byte[] 解压缩后的数据
     */
    fun decompress(data: ByteArray): ByteArray {
        var output: ByteArray
        val decompresser = Inflater()
        decompresser.reset()
        decompresser.setInput(data)
        val o = ByteArrayOutputStream(data.size)
        try {
            val buf = ByteArray(1024)
            while (!decompresser.finished()) {
                val i = decompresser.inflate(buf)
                o.write(buf, 0, i)
            }
            output = o.toByteArray()
        } catch (e: Exception) {
            output = data
            e.printStackTrace()
        } finally {
            try {
                o.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        decompresser.end()
        return output
    }
}