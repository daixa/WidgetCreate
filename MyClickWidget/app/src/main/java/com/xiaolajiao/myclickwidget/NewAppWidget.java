package com.xiaolajiao.myclickwidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.Toast;

import java.util.Objects;

/**
 * Implementation of App Widget functionality.
 */
public class NewAppWidget extends AppWidgetProvider {

    static boolean isTurning = false;

    static void updateAppWidget(final Context context, final AppWidgetManager appWidgetManager,
                                final int appWidgetId) {
        CharSequence widgetText = "打开activity";
        // Construct the RemoteViews object
        final RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);
        views.setTextViewText(R.id.appwidget_text, widgetText);
        Intent fullIntent = new Intent(context, MainActivity.class);
        fullIntent.putExtra("pending_", "过年了 哥");
        PendingIntent Pfullintent = PendingIntent.getActivity(context, 0, fullIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        views.setOnClickPendingIntent(R.id.appwidget_text, Pfullintent);
        /*
       打开activity
         */
        //打开activity按钮不带参数
        //        PendingIntent Pfullintent = PendingIntent.getActivity(context, 0, fullIntent, 0);
        //打开activity按钮带参数
        //        PendingIntent Pfullintent = PendingIntent.getActivity(context, 0, fullIntent, PendingIntent.FLAG_CANCEL_CURRENT);

       /*
       打开service
         */
        Intent serviceIntent = new Intent(context, MyService.class);
        PendingIntent servicePendingIntent = PendingIntent.getService(context, 0, serviceIntent, 0);
        views.setOnClickPendingIntent(R.id.appwidget_service_btn, servicePendingIntent);

        /*
       发送action
         */
        Intent anIntent = new Intent("com.action.haha");
        PendingIntent anPendingIntent = PendingIntent.getBroadcast(context, 0, anIntent, 0);
        views.setOnClickPendingIntent(R.id.appwidget_brocast_btn, anPendingIntent);

//        CountDownTimer countDownTimer = new CountDownTimer(40000, 1000) {
//            @Override
//            public void onTick(long l) {
//                String widgetText = "倒计时" + l;
//                RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);
//                views.setTextViewText(R.id.count_down, widgetText);
//                appWidgetManager.updateAppWidget(appWidgetId, views);
//            }
//
//            @Override
//            public void onFinish() {
//
//            }
//        };
//        countDownTimer.start();

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);

    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        RemoteViews remoteViews = null;

        if (Objects.equals(intent.getAction(), "com.action.haha")) {
//            Toast.makeText(context, "收到了 哥", Toast.LENGTH_SHORT).show();
            remoteViews = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);
            if (isTurning) {
                remoteViews.setImageViewResource(R.id.widget_iv, R.mipmap.b2);
                isTurning = false;
            } else {
                remoteViews.setImageViewResource(R.id.widget_iv, R.mipmap.b1);
                isTurning = true;
            }
            //获得appwidget管理实例，用于管理appwidget以便进行更新操作
            AppWidgetManager manger = AppWidgetManager.getInstance(context);
            // 相当于获得所有本程序创建的appwidget
            ComponentName thisName = new ComponentName(context, NewAppWidget.class);
            //更新widget
            manger.updateAppWidget(thisName, remoteViews);
        }

    }
}

