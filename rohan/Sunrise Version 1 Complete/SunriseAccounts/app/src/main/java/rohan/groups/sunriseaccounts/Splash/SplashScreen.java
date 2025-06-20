package rohan.groups.sunriseaccounts.Splash;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import rohan.groups.sunriseaccounts.Functionality.Sessions;
import rohan.groups.sunriseaccounts.Login.Login;
import rohan.groups.sunriseaccounts.MainActivity;
import rohan.groups.sunriseaccounts.R;

public class SplashScreen extends AppCompatActivity {
    private static final String TAG ="LOGIN DATA" ;
    protected boolean _active = true;
    protected int _splashTime = 500;
    private Sessions session;
    private FirebaseAuth mauth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    DatabaseReference database;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FirebaseApp.initializeApp(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        database = FirebaseDatabase.getInstance().getReference();
        FirebaseDatabase.getInstance().getReference().keepSynced(true);
        final Thread splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    while (_active && (waited < _splashTime)) {
                        sleep(500);
                        if (_active) {
                            waited += 100;
                        }
                    }
                } catch (Exception e) {

                } finally {



                    session = new Sessions(SplashScreen.this);





                    if (session.getusername() != "") {
                        database.child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {

                                // TODO: handle the post here
                                if (dataSnapshot.hasChild(session.getusername())) {
                                    DatabaseReference ref = database.child("Users").child(session.getusername());
                                    ref.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {

                                            // TODO: handle the post here

                                            Login.User user = dataSnapshot.getValue(Login.User.class);
                                            String status = user.Status;
                                            session.setname(user.Name);
                                            session.setusername(user.UserName);
                                            session.setrole(user.Role);


                                            if(status.equals("Active")) {

                                                startActivity(new Intent(SplashScreen.this,
                                                        MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                                                finish();
                                            }
                                            else{
                                                startActivity(new Intent(SplashScreen.this,
                                                        Login.class));
                                                finish();
                                            }


                                        }

                                        @Override
                                        public void onCancelled(DatabaseError databaseError) {

                                        }
                                    });
                                }

                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
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
