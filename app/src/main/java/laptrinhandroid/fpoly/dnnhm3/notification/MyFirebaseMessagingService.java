package laptrinhandroid.fpoly.dnnhm3.notification;


import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;


import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Date;

import laptrinhandroid.fpoly.dnnhm3.Activity.MainActivityhoadon;
import laptrinhandroid.fpoly.dnnhm3.Activity.ThongBao;
import laptrinhandroid.fpoly.dnnhm3.R;

public class MyFirebaseMessagingService extends FirebaseMessagingService  {
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        super.onMessageReceived(message);
        RemoteMessage.Notification notification = message.getNotification();
        if (notification == null) {
            return;
        }
        Uri s1 = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), s1);
        r.play();
//        //JobService
//        ComponentName componentName=new ComponentName(this, JobService.class);
//        JobInfo jobInfo=new JobInfo.Builder(0,componentName).
//                setPersisted(true).setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED).build();
//        JobScheduler jobScheduler= (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
//        jobScheduler.schedule(jobInfo);
        String stile = notification.getTitle();
        String body = notification.getBody();
        String icon = notification.getIcon();
              sendNotification(stile, body,icon);
            MediaPlayer mediaPlayer = MediaPlayer.create(getApplication(), R.raw.a);
            mediaPlayer.start();

    }


    @Override
    public void onDeletedMessages() {
        super.onDeletedMessages();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void sendNotification(String stile, String body,String icon) {
        //thao tác trên notification
        Intent intent = new Intent(this, MainActivityhoadon.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, Build.VERSION.SDK_INT < Build.VERSION_CODES.S ? PendingIntent.FLAG_UPDATE_CURRENT : PendingIntent.FLAG_IMMUTABLE);

        Notification.Builder builder = new Notification.Builder(this, "1")
                .setContentTitle(stile)
                .setContentText(body)
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.bell).setColor(getColor(R.color.green))
                .setShowWhen(true);
        Notification notification = builder.build();
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        //Để làm cho thông báo xuất hiện
        if (notificationManager != null) {
            notificationManager.notify((int) new Date().getTime(), notification);
        }

    }


    @Override
    public void onNewToken(@NonNull String token) {
        Log.d("onNewToken", "onNewToken: " + token);
        super.onNewToken(token);
    }

}
