package com.necor.necorbase

import android.content.Context
import android.text.TextUtils
import java.io.*

class FileUtils {

    companion object {
        /**
         * 路径：data/data/user/0/包名/files
         */
        fun getDataFilePath(context: Context?): String {
            var result = ""
            try {
                if (context != null) {
                    result = context.filesDir.absolutePath
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return result
        }

        /**
         * 路径：data/data/user/0/包名/cache
         */
        fun getCacheFilePath(context: Context?): String {
            var result = ""
            try {
                if (context != null) {
                    result = context.cacheDir.absolutePath
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return result
        }


        /**
         * 创建文件
         * @param path 生成路径
         * @param fileName 创建的文件名称
         */
        fun createFileWithPath(path: String?, fileName: String?) {
            if (!TextUtils.isEmpty(path) && !TextUtils.isEmpty(fileName)) {
                val filesDir = File(path + File.separator + fileName)
                if (!filesDir.exists()) {
                    filesDir.createNewFile()
                }
            }
        }

        /**
         * 创建Json文件并写入内容
         * @param path 生成路径
         * @param fileName 创建的文件名称
         */
        fun writeJsonFileWithPath(path: String?, fileName: String?, content: String?) {
            if (!TextUtils.isEmpty(path) && !TextUtils.isEmpty(fileName)) {
                val filesDir = File(path + File.separator + fileName)
                if (!filesDir.exists()) {
                    filesDir.createNewFile()
                }
                try {
                    val output: Writer = BufferedWriter(FileWriter(filesDir))
                    output.write(content)
                    output.close()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }

        /**
         * 读Json文件内容
         * @param path 文件路径
         * @param fileName 文件名
         */
        fun readJsonFileWithPath(path: String?, fileName: String?): String {
            try {
                val stringBuilder = StringBuilder()
                if (!TextUtils.isEmpty(path) && !TextUtils.isEmpty(fileName)) {
                    val filesDir = File(path + File.separator + fileName)
                    if (filesDir.exists()) {
                        val fileInputStream = FileInputStream(filesDir.path)
                        val streamReader = InputStreamReader(fileInputStream)
                        val reader = BufferedReader(streamReader)
                        var line: String?
                        while (reader.readLine().also { line = it } != null) {
                            stringBuilder.append(line)
                        }
                        reader.close()
                        fileInputStream.close()
                    }
                }
                return stringBuilder.toString()
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return ""
        }

    }

}