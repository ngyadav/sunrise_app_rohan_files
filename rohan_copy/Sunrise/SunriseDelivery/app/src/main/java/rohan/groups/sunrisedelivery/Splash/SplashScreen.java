package rohan.groups.sunrisedelivery.Splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

import rohan.groups.sunrisedelivery.Functionality.Session;
import rohan.groups.sunrisedelivery.Login.Login;
import rohan.groups.sunrisedelivery.MainActivity;
import rohan.groups.sunrisedelivery.R;

public class SplashScreen extends AppCompatActivity {

    private static final String TAG ="LOGIN DATA" ;
    protected boolean _active = true;
    protected int _splashTime = 1000;
    private Session session;
    private HashMap<String, Object> firebaseDefaultMap;

    DatabaseReference database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        setContentView(R.layout.activity_splash_screen);

        FirebaseDatabase.getInstance().getReference().keepSynced(true);



        session = new Session(SplashScreen.this);

        FirebaseDatabase.getInstance().getReference().child("Users");

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


                    if (session.getusername() != "") {

                        startActivity(new Intent(SplashScreen.this,
                                MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                        finish();

                    }
                    else
                    {
                        startActivity(new Intent(SplashScreen.this,
                                Login.class));
                        finish();
                    }



                }
            }


        };
        splashTread.start();

    }
}

