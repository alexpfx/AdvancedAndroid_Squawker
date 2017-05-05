package android.example.com.squawker.fcm;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by alexandre on 04/05/2017.
 */
public class MyFirebaseInstanceIdService extends FirebaseInstanceIdService {

    private static final String TAG = "MyFirebaseInstanceIdSer";


    @Override
    public void onTokenRefresh() {
        String token = FirebaseInstanceId.getInstance()
                .getToken();
        Log.d(TAG, "Refreshed token: "+token);
        //dGywRQOJ3Io:APA91bEa_jvpge6ekgyUzC4ogKo5IyQyEBqYT_2yJuAOc1j-zFCsHpAUcbAOAreWGw70_5KdmcVrL-LEt4fmdTuL-2Rxby-WP97eAJo-hFyWJ4IDue6z1OMMUOmWIYeLgXHj1Di2ZPPG


    }
}
