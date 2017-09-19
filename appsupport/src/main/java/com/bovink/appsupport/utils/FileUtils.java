package com.bovink.appsupport.utils;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Retina975 on 17/5/31.
 * <p>
 * 文件工具类
 */

public class FileUtils {

    private FileUtils() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 判断sdcard是否存在
     *
     * @return
     */
    public static boolean isSdCardExist() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * 文件是否在sdcard中
     *
     * @param parent
     * @param child
     * @return
     */
    public static boolean fileExistsInSdcard(String parent, String child) {
        if (isSdCardExist()) {

            String sdcard = Environment.getExternalStorageDirectory().getPath();
            File file = new File(sdcard + File.separator + parent, child);
            return file.exists();
        }
        return false;
    }

    /**
     * 文件是否在context外部存储中
     *
     * @param context
     * @param parent
     * @param child
     * @return
     */
    public static boolean fileExistsInContext(Context context, String parent, String child) {
        String contextExternal = context.getExternalFilesDir(null).getPath();
        File file = new File(contextExternal + File.separator + parent, child);
        return file.exists();
    }

    /**
     * 文件写入操作
     *
     * @param downloadFile
     * @param saveFile
     */
    public static void writeFile(InputStream downloadFile, File saveFile) {
        try {

            InputStream inputStream = null;
            OutputStream outputStream = null;

            try {
                byte[] fileReader = new byte[4096];

                inputStream = downloadFile;
                outputStream = new FileOutputStream(saveFile);

                while (true) {
                    int read = inputStream.read(fileReader);

                    if (read == -1) {
                        break;
                    }

                    outputStream.write(fileReader, 0, read);

                }

                outputStream.flush();

            } catch (IOException e) {

            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }

                if (outputStream != null) {
                    outputStream.close();
                }
            }
        } catch (IOException e) {

        }
    }

}
