package com.example.lab4;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;

import java.util.Random;

public class AddCartBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        int item_index = intent.getIntExtra("selectedItem",0);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context,"")
                .setSmallIcon(Item.items[item_index].picture_id)
                .setContentTitle("马上下单")
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(),Item.items[item_index].picture_id))
                .setContentText(Item.items[item_index].name + "已添加到购物车");
        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
// mId allows you to update the notification later on.

        Intent resultIntent = new Intent(context, MainActivity.class);
        resultIntent.putExtra("view",0);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        mBuilder.setContentIntent(resultPendingIntent);
        mNotificationManager.notify(item_index+100, mBuilder.build());
    }
}
