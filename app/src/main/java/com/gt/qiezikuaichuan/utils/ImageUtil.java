package com.gt.qiezikuaichuan.utils;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import com.gt.qiezikuaichuan.MainApp;
import com.gt.qiezikuaichuan.model.ImageFile;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.R.attr.data;
import static android.R.attr.fillAfter;
import static android.R.id.list;

/**
 * Created by 巴巴 on 2017/1/4.
 */

public class ImageUtil {

    public static HashMap<String, List<ImageFile>> scanImage() {

        ContentResolver contentResolver = MainApp.getAppContext().getContentResolver();
        Uri imageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String selection =
                MediaStore.Images.Media.MIME_TYPE + "=? or " +
                        MediaStore.Images.Media.MIME_TYPE + "=? or " +
                        MediaStore.Images.Media.MIME_TYPE + "=?";
        String[] selectionArgs = new String[]{"image/jpeg", "image/png", "image/gif"};
        String data_modified = MediaStore.Images.Media.DATE_MODIFIED;
        Cursor cursor = contentResolver.query(imageUri, null, selection, selectionArgs, data_modified);
        if (cursor == null) {
            return null;
        }
        int data = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

        HashMap<String, List<ImageFile>> imageMap = new HashMap<>();
        List<ImageFile> list = null;
        List<ImageFile> paths = new ArrayList<>();
        while (cursor.moveToNext()) {
            String path = cursor.getString(data);
            File file = new File(path);
            ImageFile imageFile = new ImageFile();
            imageFile.setSize(file.length());
            imageFile.setName(file.getName());
            imageFile.setPath(path);
            String parentFilePath = file.getParentFile().getPath();
            imageFile.setParentFilePath(parentFilePath);
            if (!imageMap.containsKey(parentFilePath)) {
                list = new ArrayList<>();
            } else {
                list = imageMap.get(parentFilePath);
            }
            list.add(imageFile);
            imageMap.put(parentFilePath, list);
            paths.add(imageFile);
        }
        imageMap.put("paths", paths);
        cursor.close();
        return imageMap;
    }

}
