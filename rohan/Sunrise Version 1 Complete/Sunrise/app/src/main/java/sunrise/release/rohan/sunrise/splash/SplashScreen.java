package sunrise.release.rohan.sunrise.splash;

import android.content.Intent;
import android.os.TokenWatcher;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import sunrise.release.rohan.sunrise.Functionality.LoginSession;
import sunrise.release.rohan.sunrise.Functionality.RetrofitClient;
import sunrise.release.rohan.sunrise.R;
import sunrise.release.rohan.sunrise.home.Unregistered;
import sunrise.release.rohan.sunrise.home.home;
import sunrise.release.rohan.sunrise.login.login;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;

public class SplashScreen extends AppCompatActivity {
    private static final String TAG ="LOGIN DATA" ;
    protected boolean _active = true;
    protected int _splashTime = 00;
    private LoginSession session;
    private FirebaseAuth mauth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
//        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        database = FirebaseDatabase.getInstance().getReference();
        FirebaseDatabase.getInstance().getReference().keepSynced(true);
//        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo ni = cm.getActiveNetworkInfo();
//        boolean isConnected = ni.isConnected();

//        if(!isConnected){
//            Toast.makeText(this, "Connect To Internet", Toast.LENGTH_SHORT).show();
//            return;
//        }



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
                    session = new LoginSession(SplashScreen.this);
                    if (session.getusename() != "") {
                        database.child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {

                                // TODO: handle the post here
                                if (dataSnapshot.hasChild("+91" + session.getusename())) {

                                            login.User user = dataSnapshot.child("+91"+session.getusename()).getValue(login.User.class);
                                            String status = user.Status;
                                            String role = user.Role;
                                            String referral=user.Referral;
                                            session.setrole(role);
                                            session.setreferral(referral);
                                            session.setstatus(status);

//                                            Call<ResponseBody> call= RetrofitClient
//                                                    .getInstance()
//                                                    .getApi()
//                                                    .sendsms("sunris","sunris2019","9632125551","sunris","","hello","19","");
//
//                                            call.enqueue(new Callback<ResponseBody>() {
//                                                @Override
//                                                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                                                    try {
//                                                        String s=response.body().string();
//                                                        Toast.makeText(SplashScreen.this,s,Toast.LENGTH_LONG).show();
//                                                    } catch (IOException e) {
//                                                        e.printStackTrace();
//                                                    }
//
//
//                                                }
//
//                                                @Override
//                                                public void onFailure(Call<ResponseBody> call, Throwable t) {
//                                                    Toast.makeText(SplashScreen.this,"Failed",Toast.LENGTH_LONG).show();
//                                                }
//                                            });




                                            if (status.equalsIgnoreCase("Not Registered")) {
                                                startActivity(new Intent(SplashScreen.this,
                                                        Unregistered.class));
                                                finish();
                                            } else {
                                                startActivity(new Intent(SplashScreen.this,
                                                        home.class));
                                                finish();
                                            }


                                }

                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });

                    } else {
                        startActivity(new Intent(SplashScreen.this,
                                login.class));
                        finish();
                    }
                }
            }


        };
        splashTread.start();
    }







}
