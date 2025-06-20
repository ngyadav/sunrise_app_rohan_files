package sunrise.release.rohan.sunriseseller.splash;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import sunrise.release.rohan.sunriseseller.Functionality.LoginSession;
import sunrise.release.rohan.sunriseseller.R;
import sunrise.release.rohan.sunriseseller.home.UnRegistered;
import sunrise.release.rohan.sunriseseller.home.home;
import sunrise.release.rohan.sunriseseller.login.login;

public class Splash extends AppCompatActivity {

    protected boolean _active = true;
    protected int _splashTime = 500;
    private LoginSession session;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);
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
                    session = new LoginSession(Splash.this);
                    if(session.getusename()!="")
                    {
                        database.child("SellerUsers").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {

                                // TODO: handle the post here
                                if (dataSnapshot.hasChild("+91" + session.getusename())) {
                                    DatabaseReference ref = database.child("SellerUsers").child("+91" + session.getusename());
                                    ref.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {

                                            // TODO: handle the post here
                                            login.User user = dataSnapshot.getValue(login.User.class);
                                            String status = user.Status;
                                            if (status.equalsIgnoreCase("Not Registered")) {
                                                startActivity(new Intent(Splash.this,
                                                        UnRegistered.class));
                                                finish();
                                            } else {
                                                startActivity(new Intent(Splash.this,
                                                        home.class));
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
                    else {
                        startActivity(new Intent(Splash.this,
                                login.class));
                        finish();
                    }
                }
            }

            ;
        };
        splashTread.start();
    }
}
