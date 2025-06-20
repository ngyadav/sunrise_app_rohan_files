package sunrise.release.rohan.sunrise.signup;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import sunrise.release.rohan.sunrise.Functionality.Encryption;
import sunrise.release.rohan.sunrise.Functionality.LoginSession;
import sunrise.release.rohan.sunrise.R;
import sunrise.release.rohan.sunrise.home.Unregistered;
import sunrise.release.rohan.sunrise.home.home;
import sunrise.release.rohan.sunrise.login.login;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;

import sunrise.release.rohan.sunrise.Functionality.Encryption;

public class passwordsignup extends AppCompatActivity {

    private static final String TAG = "LOGIN";
    private LoginSession session;
    private FirebaseAuth mAuth;
    private EditText Temail,Tname,Tpassword,Tpassword1,Trefer;
    private Button Bregister;
    private ProgressBar progressBar;
    private static int RC_SIGN_IN = 100;
    String refer="";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passwordsignup);
        mAuth = FirebaseAuth.getInstance();
        Tname=(EditText)findViewById(R.id.Tname);
        Trefer=(EditText)findViewById(R.id.Trefer);
        Temail=(EditText)findViewById(R.id.Temail);
        Tpassword = (EditText) findViewById(R.id.Tusername);
        Tpassword1=(EditText) findViewById(R.id.Tcpassword);
        Bregister = (Button) findViewById(R.id.Bregister);
        progressBar = findViewById(R.id.progressBar);
        session = new LoginSession(passwordsignup.this);

        final String username=getIntent().getStringExtra("mobile");
        session.setusename("");
        Tname.requestFocus();
        progressBar.setVisibility(View.GONE);
        if(mAuth.getCurrentUser()!=null)
            mAuth.signOut();

//        Log.d(TAG,mno);
        Bregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                String password1 = Tpassword.getText().toString().trim();
                String password2 = Tpassword1.getText().toString().trim();



                if (TextUtils.isEmpty(password1)) {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_LONG).show();
                    return;
                }

                if (password1.length() < 6) {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_LONG).show();
                    return;
                }
                if (!password1.equals(password2)) {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), "Password & Confirm Password Don't Match!", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(Temail.getText())) {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), "Enter Email!", Toast.LENGTH_LONG).show();
                    return;
                }




                if (!TextUtils.isEmpty(username)) {
                    final DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Users").child("+91" + username);
                    String hash = null;
                    try {
                        hash = Encryption.encrypt(password1);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    String device_unique_id = Settings.Secure.getString(passwordsignup.this.getContentResolver(),
                            Settings.Secure.ANDROID_ID);
                    ref.child("IMEI").setValue(device_unique_id);
                    ref.child("Password").setValue(hash);
                    ref.child("Status").setValue("Registered");
                    ref.child("Name").setValue(Tname.getText().toString());
                    ref.child("Email").setValue(Temail.getText().toString());
                    ref.child("Role").setValue("user");
                    ref.child("Rewards").setValue("0");
                    ref.child("Phone").setValue(username);
                    session.setrole("user");
                    session.setusename(username);
                    session.setimei(device_unique_id);
                    refer = Trefer.getText().toString();
                    if (refer.equals("")) {
                        refer = "empty";
                    }
                        session.setreferral(refer);
                        ref.child("Referral").setValue(refer);
                        progressBar.setVisibility(View.GONE);
                        Intent intent = new Intent(passwordsignup.this, home.class);
                        startActivity(intent);
                        finish();

//                    else {
//                        FirebaseDatabase.getInstance().getReference().child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
//                            @Override
//                            public void onDataChange(DataSnapshot dataSnapshot) {
//                                if (dataSnapshot.hasChild("+91" + Trefer.getText().toString())) {
//                                    final DatabaseReference ref1 = FirebaseDatabase.getInstance().getReference().child("Users").child("+91" + Trefer.getText().toString());
//                                    ref1.addListenerForSingleValueEvent(new ValueEventListener() {
//                                        @Override
//                                        public void onDataChange(DataSnapshot dataSnapshot) {
//                                            int a = Integer.parseInt(dataSnapshot.child("Rewards").getValue().toString());
//                                            a = a + 10;
//
//                                            ref1.child("Rewards").setValue(Integer.toString(a));

//
//                                            DatabaseReference rref = FirebaseDatabase.getInstance().getReference().child("Rewards").push();
//                                            rref.child("PushId").setValue(rref.getKey().toString());
//                                            rref.child("Authorised").setValue("+91" + Trefer.getText().toString());
//                                            rref.child("Amount").setValue("10");
//                                            rref.child("Balance").setValue(Integer.toString(a));
//                                            rref.child("Generated").setValue("+919663191201");
//                                            Date currentDate = new Date(System.currentTimeMillis());
//                                            SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
//                                            String date1 = df.format(currentDate);
//                                            rref.child("Date").setValue(date1);
//                                            rref.child("AuthorisedType").setValue("Dr");
//                                            rref.child("Type").setValue("Cr");
//                                            rref.child("Name").setValue("Referral");
//                                            rref.child("UserId").setValue("+91" + Trefer.getText().toString());
//                                            rref.child("TransactionId").setValue("+91" + username);
//                                            rref.child("AuthorisedName").setValue("ReferralReward").addOnCompleteListener(new OnCompleteListener<Void>() {
//                                                @Override
//                                                public void onComplete(@NonNull Task<Void> task) {
//
//                                                }
//                                            });
//
//                                            session.setreferral(refer);
//
//                                            ref.child("Referral").setValue(refer);
//                                            progressBar.setVisibility(View.GONE);
//                                            Intent intent = new Intent(passwordsignup.this, home.class);
//                                            startActivity(intent);
//                                            finish();


//                                }
//                            }
//
//                            @Override
//                            public void onCancelled(DatabaseError databaseError) {
//
//                            }
//                        });

//                    }

//                ref.child("Users").child("Userid").setValue(user.getUid(), new DatabaseReference.CompletionListener() {
//                    void onComplete(DatabaseError error, DatabaseReference ref) {
//                        System.out.println("Value was set. Error = "+error);
//                    }
//                });
                }
                else{
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(passwordsignup.this,"Server Error Code #1200. Please try after Sometime or contact Admin",Toast.LENGTH_LONG).show();
                    return;
                }
            }

        });

    }
}
