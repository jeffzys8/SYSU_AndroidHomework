package com.example.lab4;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Picture;
import android.graphics.drawable.PictureDrawable;
import android.support.v4.app.NotificationCompat;

import java.util.Random;

public class mBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {


        Random random = new Random();
        int item_index = Item.all.get(random.nextInt(Item.all.size()));

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context,"")
                        .setSmallIcon(Item.items[item_index].picture_id)
                        .setContentTitle("新品热卖")
                        .setLargeIcon(BitmapFactory.decodeResource(context.getResources(),Item.items[item_index].picture_id))
                        .setContentText(Item.items[item_index].name + "仅售 ￥" + Item.items[item_index].price + "!");
        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
// mId allows you to update the notification later on.

        Intent resultIntent = new Intent(context, ItemDetial.class);
        resultIntent.putExtra("selectedItem",item_index);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        mBuilder.setContentIntent(resultPendingIntent);

        mNotificationManager.notify(12, mBuilder.build());
    }
}
