package com.zhouzhuo.messengerone.utils;

import android.os.Environment;

/**
 * Created by zhouzhuo on 2018/1/2.
 */

public class MyConstants {
    public static final String CHAPTER_2_PATH =
            Environment.getExternalStorageDirectory().getPath()
            +"/singwhatiwanna/chapter_2/";
    public static final String CACHE_FILE_PATH = CHAPTER_2_PATH +"usercache";

    public static final int MES_FROM_CLIENT = 0;
    public static final int MES_FROM_SERVICE =1;
}
