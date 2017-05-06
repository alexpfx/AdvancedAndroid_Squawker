package android.example.com.squawker.fcm;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.example.com.squawker.MainActivity;
import android.example.com.squawker.R;
import android.example.com.squawker.provider.SquawkContract;
import android.example.com.squawker.provider.SquawkProvider;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

/**
 * Created by alexandre on 05/05/2017.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {


    private static final String TAG = "MyFirebaseMessagingServ";
    public static final String AUTHOR = "author";
    public static final String DATE = "date";
    public static final String MESSAGE = "message";
    public static final String AUTHOR_KEY = "authorKey";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Map<String, String> data = remoteMessage.getData();

        if (data.size() <= 0){
            return;
        }

        sendNotification (data);
        insert (data);



    }

    private void sendNotification(Map<String, String> data) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,
                                                                PendingIntent.FLAG_ONE_SHOT);

        String author = data.get(AUTHOR);
        String message = data.get(MESSAGE);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this).setSmallIcon(
                R.drawable.ic_duck)
                .setContentTitle(String.format(getString(R.string.notification_message), author))
                .setContentText(message)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(
                Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, builder.build());

    }

    private void insert(Map<String, String> data) {
        final ContentValues contentValues = createContentValues (data);

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                getContentResolver().insert(SquawkProvider.SquawkMessages.CONTENT_URI, contentValues);
                return null;
            }
        }.execute();

    }

    private ContentValues createContentValues(Map<String, String> data) {
        String author = data.get(AUTHOR);
        String date = data.get(DATE);
        String message = data.get(MESSAGE);
        String key = data.get(AUTHOR_KEY);

        ContentValues contentValues = new ContentValues();
        contentValues.put(SquawkContract.COLUMN_AUTHOR, author);
        contentValues.put(SquawkContract.COLUMN_DATE, date);
        contentValues.put(SquawkContract.COLUMN_MESSAGE, message);
        contentValues.put(SquawkContract.COLUMN_AUTHOR_KEY, key);
        return contentValues;
    }
}
