package sunrise.release.rohan.sunrise.login;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInstaller;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import sunrise.release.rohan.sunrise.Functionality.Encryption;
import sunrise.release.rohan.sunrise.Functionality.LoginSession;
import sunrise.release.rohan.sunrise.R;
import sunrise.release.rohan.sunrise.forgotpassword.forgotpassword;
import sunrise.release.rohan.sunrise.home.Unregistered;
import sunrise.release.rohan.sunrise.home.home;
import sunrise.release.rohan.sunrise.signup.passwordsignup;
import sunrise.release.rohan.sunrise.signup.signup;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.ValueEventListener;


import com.google.firebase.database.ChildEventListener;

import sunrise.release.rohan.sunrise.Functionality.Encryption;
import sunrise.release.rohan.sunrise.signup.signup;

public class login extends AppCompatActivity {

    private static final String TAG = "LOGIN";
    private LoginSession session;
    private FirebaseAuth mAuth;
    private EditText Temail, Tpassword;
    private Button Bregister;
    private TextView Tforgot, Tregister,Timei;
    private ProgressBar progressBar;
    private static int RC_SIGN_IN = 100;
    private int temp = 0;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
//        if (mAuth.getCurrentUser() != null) {
//            startActivity(new Intent(login.this, home.class));
//            finish();
//        }

//        User user=new User("Rohan","Hello");
//
//        DatabaseReference ref=FirebaseDatabase.getInstance().getReference("Users");
//         ref.child("trail").setValue(user);

        setContentView(R.layout.activity_login);



        mAuth.signInAnonymously()
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInAnonymously:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInAnonymously:failure", task.getException());
                            Toast.makeText(login.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                });



        Temail = (EditText) findViewById(R.id.Tusername);
        Tpassword = (EditText) findViewById(R.id.Tpassword);
        Bregister = (Button) findViewById(R.id.Bregister);
        Tforgot = (TextView) findViewById(R.id.forgotpassword);
        Tregister = (TextView) findViewById(R.id.signup);
        Timei = (TextView) findViewById(R.id.imei);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);

        Bregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email1 = "+91" + Temail.getText().toString();
                final String password1 = Tpassword.getText().toString();
                if (TextUtils.isEmpty(email1)) {
                    Toast.makeText(getApplicationContext(), "Enter User Name!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password1)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);


                database.child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        // TODO: handle the post here
                        if (dataSnapshot.hasChild(email1)) {
                            DatabaseReference ref = database.child("Users").child(email1);
                            ref.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {

                                    // TODO: handle the post here
                                    User user = dataSnapshot.getValue(User.class);
                                    String hash = null;
                                    String device_unique_id = Settings.Secure.getString(login.this.getContentResolver(),
                                            Settings.Secure.ANDROID_ID);
                                    try {
                                        hash = Encryption.decrypt(user.Password);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
//                                    if(user.Role.equals("su")){
                                        if (password1.equals(hash)) {
                                            session = new LoginSession(login.this);
                                            String no = Temail.getText().toString();
                                            session.setusename(no);

                                            String status = user.Status;
                                            session.setrole(user.Role);

                                            String refferal=user.Referral;
                                            session.setreferral(refferal);


                                            if (status.equals("Not Registered")) {
                                                Intent intent = new Intent(login.this, Unregistered.class);
                                                startActivity(intent);
                                                finish();
                                            } else {
                                                Intent intent = new Intent(login.this, home.class);
                                                startActivity(intent);
                                                finish();
                                            }


                                            progressBar.setVisibility(View.GONE);
                                        } else {
                                            progressBar.setVisibility(View.GONE);
                                            Toast.makeText(getApplicationContext(), "Password Doesn't Match", Toast.LENGTH_SHORT).show();
                                            return;
                                        }

//                                    else if(user.IMEI.equals(device_unique_id)) {
//                                        if (password1.equals(hash)) {
//                                            session = new LoginSession(login.this);
//                                            String no = Temail.getText().toString();
//                                            session.setusename(no);
//
//                                            String status = user.Status;
//                                            session.setrole(user.Role);
//                                            if (status.equals("Not Registered")) {
//                                                Intent intent = new Intent(login.this, Unregistered.class);
//                                                startActivity(intent);
//                                                finish();
//                                            } else {
//                                                Intent intent = new Intent(login.this, home.class);
//                                                startActivity(intent);
//                                                finish();
//                                            }
//
//
//                                            progressBar.setVisibility(View.GONE);
//                                        } else {
//                                            progressBar.setVisibility(View.GONE);
//                                            Toast.makeText(getApplicationContext(), "Password Doesn't Match", Toast.LENGTH_SHORT).show();
//                                            return;
//                                        }
//                                    }
//                                    else {
//                                        progressBar.setVisibility(View.GONE);
//                                        AlertDialog.Builder builder = new AlertDialog.Builder(login.this);
//                                        builder.setTitle("DEVICE NOT VERIFIED");
//                                        builder.setMessage("Please Contact Support Center for Further Information\n No:096066 33733")
//                                                .setCancelable(false);
//
//
//                                        AlertDialog alertDialog = builder.create();
//                                        alertDialog.show();
//                                        return;
//                                    }


                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });
                        }
                        else{
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(getApplicationContext(),"User not Registered", Toast.LENGTH_SHORT).show();
                            return;
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
 }
        });
        Tforgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(login.this, forgotpassword.class));
            }
        });
        Tregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(login.this, signup.class));
            }
        });
        Timei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(login.this, Updateimei.class));
            }
        });
    }

    @IgnoreExtraProperties
    public  static class User {

        public String Password;
        public String Userid;
        public String Status;
        public String Role;
        public String IMEI;
        public String Referral;


        public User() {
            // Default constructor required for calls to DataSnapshot.getValue(User.class)
        }

        public User(String Password, String Userid,String Status,String Role,String IMEI,String Referral) {
            this.Password = Password;
            this.Userid = Userid;
            this.Status=Status;
            this.Role=Role;
            this.IMEI=IMEI;
            this.Referral=Referral;
        }

    }
}