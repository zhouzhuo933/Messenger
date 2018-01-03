package com.zhouzhuo.messengerone;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zhouzhuo on 2018/1/2.
 */

public class Book implements Parcelable{
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
