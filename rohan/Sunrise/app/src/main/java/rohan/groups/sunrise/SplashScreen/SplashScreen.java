package rohan.groups.sunrise.SplashScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;
import com.google.firebase.dynamiclinks.PendingDynamicLinkData;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.HashMap;

import rohan.groups.sunrise.AppIntro;
import rohan.groups.sunrise.Functionality.Session;
import rohan.groups.sunrise.Login.Login;
import rohan.groups.sunrise.MainActivity;
import rohan.groups.sunrise.PermissionActivity;
import rohan.groups.sunrise.R;
import rohan.groups.sunrise.WelcomeScreen;

public class SplashScreen extends AppCompatActivity {
    private static final String TAG ="LOGIN DATA" ;
    protected boolean _active = true;
    protected int _splashTime = 3000;
    private Session session;

    DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FirebaseApp.initializeApp(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        session = new Session(SplashScreen.this);

        FirebaseDatabase.getInstance().getReference().keepSynced(true);

        database = FirebaseDatabase.getInstance().getReference();
        final Thread splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    while (_active && (waited < _splashTime)) {
                        sleep(100);
                        if (_active) {
                            waited += 100;
                        }
                    }
                } catch (Exception e) {

                } finally {
                    if(TextUtils.isEmpty(session.getisfirsttime()))
                    {
                        FirebaseDynamicLinks.getInstance()
                                .getDynamicLink(getIntent())
                                .addOnSuccessListener(SplashScreen.this, new OnSuccessListener<PendingDynamicLinkData>() {
                                    @Override
                                    public void onSuccess(PendingDynamicLinkData pendingDynamicLinkData) {
                                        // Get deep link from result (may be null if no link is found)
                                        Uri deepLink = null;
                                        if (pendingDynamicLinkData != null) {
                                            deepLink = pendingDynamicLinkData.getLink();
                                        }

                                        if (deepLink != null
                                                && deepLink.getBooleanQueryParameter("invitedby", false)) {
                                            String referrerUid = deepLink.getQueryParameter("invitedby");
                                            session.setreferral(referrerUid);

                                            startActivity(new Intent(SplashScreen.this,
                                                    PermissionActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                                            finish();
                                        }
                                        else{
                                            startActivity(new Intent(SplashScreen.this,
                                                    PermissionActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                                            finish();
                                        }



                                    }
                                });
                    }
                    else {
                        if (session.getusername() != "") {
                            startActivity(new Intent(SplashScreen.this,
                                    MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                            finish();

                        } else {
                            startActivity(new Intent(SplashScreen.this,
                                    Login.class));
                            finish();
                        }
                    }

                }
            }


        };
        splashTread.start();

    }
}

