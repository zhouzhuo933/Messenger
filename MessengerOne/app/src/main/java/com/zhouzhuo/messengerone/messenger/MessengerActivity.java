package com.zhouzhuo.messengerone.messenger;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

import com.zhouzhuo.messengerone.R;
import com.zhouzhuo.messengerone.utils.MyConstants;

public class MessengerActivity extends Activity {
    private static final String TAG = "MessengerActivity";
    private Messenger mSerVie;
    //handler 将会接收这个消息
    private Messenger mGetReplyMessenger = new Messenger(new MessengerHandler());

    private static class MessengerHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case MyConstants.MES_FROM_SERVICE:
                    Log.i("zhouzhuo", "receive msg from Service:" + msg.getData().getString("reply"));
                    break;
            }
        }
    }

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mSerVie = new Messenger(service);
            Log.d("zhouzhuo","bind service");
            Message msg = Message.obtain(null,MyConstants.MES_FROM_CLIENT);
            Bundle data = new Bundle();
            data.putString("msg","hello,this is client");
            msg.setData(data);
            //将消息
            msg.replyTo = mGetReplyMessenger;
            try {
                mSerVie.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messenger);
        Intent intent = new Intent(this,MessengerService.class);
        bindService(intent,mConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        unbindService(mConnection);
        super.onDestroy();
    }
}
