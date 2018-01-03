package com.zhouzhuo.messengerone.messenger;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import com.zhouzhuo.messengerone.utils.MyConstants;


/**
 * Created by zhouzhuo on 2018/1/2.
 */

public class MessengerService extends Service {
    private static final String TAG = "MessengerService";


    private static class MessengerHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case MyConstants.MES_FROM_CLIENT:
                    Log.d("zhouzhuo","receive msg from Client："+msg.getData().getString("msg"));
                    Messenger client = msg.replyTo;
                    Message replyMessage = Message.obtain(null,MyConstants.MES_FROM_SERVICE);
                    Bundle bundle = new Bundle();
                    bundle.putString("reply","恩，你的消息已经收到，稍后回复你");
                    replyMessage.setData(bundle);
                    try {
                        client.send(replyMessage);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
            }

        }
    }
    private final Messenger messenger = new Messenger(new MessengerHandler());

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d("zhouzhuo","==Binder===");
        return messenger.getBinder();
    }

    @Override
    public int onStartCommand(Intent intent,  int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }
}
