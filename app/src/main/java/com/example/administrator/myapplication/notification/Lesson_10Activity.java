package com.example.administrator.myapplication.notification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.administrator.myapplication.MainActivity;
import com.example.administrator.myapplication.R;

public class Lesson_10Activity extends AppCompatActivity{
    //BaseNotification
    private Button bt01;

    //UpdateBaseNotification
    private Button bt02;

    //ClearBaseNotification
    private Button bt03;

    //MediaNotification
    private Button bt04;

    //ClearMediaNotification
    private Button bt05;

    //ClearALL
    private Button bt06;

    //CustomNotification
    private Button bt07;

    //通知管理器
    private NotificationManager nm;

    //通知显示内容
    private PendingIntent pd;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
   /*加载页面*/
        setContentView(R.layout.activity_lesson_10);

        init();
    }

    private void init() {
        bt01 = (Button)findViewById(R.id.le10bt01);
        bt02 = (Button)findViewById(R.id.le10bt02);
        bt03 = (Button)findViewById(R.id.le10bt03);
        bt04 = (Button)findViewById(R.id.le10bt04);
        bt05 = (Button)findViewById(R.id.le10bt05);
        bt06 = (Button)findViewById(R.id.le10bt06);
        bt07 = (Button)findViewById(R.id.le10bt07);

        bt01.setOnClickListener(onclick);
        bt02.setOnClickListener(onclick);
        bt03.setOnClickListener(onclick);
        bt04.setOnClickListener(onclick);
        bt05.setOnClickListener(onclick);
        bt06.setOnClickListener(onclick);
        bt07.setOnClickListener(onclick);

        nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        Intent intent = new Intent(this,Lesson_10Activity.class);

        pd = PendingIntent.getActivity(Lesson_10Activity.this, 0, intent, 0);
    }

    View.OnClickListener onclick = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            switch(v.getId()) {
                case R.id.le10bt01:
                    Bitmap btm = BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);
                    NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(Lesson_10Activity.this)
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setContentTitle("您有新的订单")
                            .setContentText("twain@android.com");

                    mBuilder.setTicker("您有新的订单");//第一次提示消息的时候显示在通知栏上
                    mBuilder.setNumber(12);
                    mBuilder.setLargeIcon(btm);
                    mBuilder.setAutoCancel(true);//自己维护通知的消失

                    //构建一个Intent
                    Intent resultIntent = new Intent(Lesson_10Activity.this, MainActivity.class);
                    resultIntent.putExtra("index","test");

                    //封装一个Intent
                    PendingIntent resultPendingIntent = PendingIntent.getActivity(Lesson_10Activity.this, 0, resultIntent,PendingIntent.FLAG_UPDATE_CURRENT);
                    // 设置通知主题的意图
                    mBuilder.setContentIntent(resultPendingIntent);
                    //获取通知管理器对象
                    NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                    mNotificationManager.notify(0, mBuilder.build());
                    break;
            }
        }
    };
}
