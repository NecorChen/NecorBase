package com.necor.necorbase;

import android.content.Context;
import android.text.TextUtils;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;

public class FileUtils {

    public static File getFileByPath(final String filePath) {
        return StringUtils.isSpace(filePath) ? null : new File(filePath);
    }

    public static boolean isFileExists(final File file) {
        if (file == null) return false;
        if (file.exists()) {
            return true;
        }
        return false;
    }

    /**
     * 获取data下files文件夹路径
     * 路径：data/data/user/0/包名/files
     */
    public String getDataFilePath(Context context) {
        String result = "";
        try {
            if (context != null) {
                result = context.getFilesDir().getAbsolutePath();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 获取data下cache文件夹路径
     * 路径：data/data/user/0/包名/cache
     */
    public String getCacheFilePath(Context context) {
        String result = "";
        try {
            if (context != null) {
                result = context.getCacheDir().getAbsolutePath();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * 创建文件
     *
     * @param path     生成路径
     * @param fileName 创建的文件名称
     */
    public void createFileWithPath(String path, String fileName) {
        if (!TextUtils.isEmpty(path) && !TextUtils.isEmpty(fileName)) {
            try {
                File filesDir = new File(path + File.separator + fileName);
                if (!filesDir.exists()) {
                    filesDir.createNewFile();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 创建Json文件并写入内容
     *
     * @param path     生成路径
     * @param fileName 创建的文件名称
     */
    public void writeJsonFileWithPath(String path, String fileName, String content) {
        if (!TextUtils.isEmpty(path) && !TextUtils.isEmpty(fileName)) {
            try {
                File filesDir = new File(path + File.separator + fileName);
                if (!filesDir.exists()) {
                    filesDir.createNewFile();
                }
                Writer output = new BufferedWriter(new FileWriter(filesDir));
                output.write(content);
                output.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 读Json文件内容
     *
     * @param path     文件路径
     * @param fileName 文件名
     */
    public String readJsonFileWithPath(String path, String fileName) {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            if (!TextUtils.isEmpty(path) && !TextUtils.isEmpty(fileName)) {
                File filesDir = new File(path + File.separator + fileName);
                if (filesDir.exists()) {
                    FileInputStream fileInputStream = new FileInputStream(filesDir.getPath());
                    InputStreamReader streamReader = new InputStreamReader(fileInputStream);
                    BufferedReader reader = new BufferedReader(streamReader);
                    while (reader.readLine() != null) {
                        String line = reader.readLine();
                        stringBuilder.append(line);
                    }
                    reader.close();
                    fileInputStream.close();
                }
            }
            return stringBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
