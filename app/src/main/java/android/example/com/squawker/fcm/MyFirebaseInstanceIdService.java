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

    }
}
