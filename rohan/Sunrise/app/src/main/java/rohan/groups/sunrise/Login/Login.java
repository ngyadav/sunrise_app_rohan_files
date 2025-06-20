package rohan.groups.sunrise.Login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

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

import java.util.ArrayList;

import rohan.groups.sunrise.ForgotPassword.ForgotPassword_Otp;
import rohan.groups.sunrise.Functionality.Encryption;
import rohan.groups.sunrise.Functionality.Session;
import rohan.groups.sunrise.MainActivity;
import rohan.groups.sunrise.R;
import rohan.groups.sunrise.Signup.Signup_Otp;

public class Login extends AppCompatActivity {

    private static final String TAG = "LOGIN";
    private Session session;
    private FirebaseAuth mAuth;
    private EditText Tusername, Tpassword;
    private Button Bregister;
    private TextView Tforgot, Tregister;
    private ProgressBar progressBar;
    private static int RC_SIGN_IN = 100;
    private int temp = 0;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        Tusername = (EditText) findViewById(R.id.Tusername);
        Tpassword = (EditText) findViewById(R.id.Tpassword);
        Bregister = (Button) findViewById(R.id.Bregister);
        Tforgot = (TextView) findViewById(R.id.forgotpassword);
        Tregister = (TextView) findViewById(R.id.signup);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);

        Bregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (TextUtils.isEmpty(Tusername.getText().toString())) {
                  Tusername.setError("Enter Username");
                  Tusername.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(Tpassword.getText().toString())) {
                   Tpassword.setError("Enter Password");
                   Tpassword.requestFocus();
                    return;
                }

                final String email1 = "+91" + Tusername.getText().toString();
                final String password1 = Tpassword.getText().toString();

                progressBar.setVisibility(View.VISIBLE);


                database.child("Users").child(email1).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        // TODO: handle the post here
                        if (dataSnapshot.exists()) {

                            // TODO: handle the post here
                                    User user = dataSnapshot.getValue(User.class);
                                    String hash = null;

                                    try {
                                        hash = Encryption.decrypt(user.Password);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    if (password1.equals(hash)) {
                                            session = new Session(Login.this);
                                            String no = Tusername.getText().toString();
                                            session.setusername(email1);
                                            session.setname(user.Name);

                                        session.setcartitem("0");
                                        session.setcarttotal("0");
                                        ArrayList<String> iname=new ArrayList<String>();
                                        iname.clear();
                                        ArrayList<String> iprice=new ArrayList<String>();
                                        iname.clear();
                                        ArrayList<String> iqty=new ArrayList<String>();
                                        iname.clear();
                                        session.setitemname(iname,"INAME");
                                        session.setitemprice(iprice,"IPRICE");
                                        session.setitemqty(iqty,"IQTY");

                                            Intent intent = new Intent(Login.this, MainActivity.class);
                                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                            startActivity(intent);
                                            finish();

                                        progressBar.setVisibility(View.GONE);
                                    } else {
                                        progressBar.setVisibility(View.GONE);
                                        Tpassword.setError("Password doesnt macth");
                                        Tpassword.requestFocus();
                                        return;
                                    }

                        }
                        else{
                            progressBar.setVisibility(View.GONE);
                            Tusername.setError("User Not Registered");
                            Tusername.requestFocus();
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
                startActivity(new Intent(Login.this, ForgotPassword_Otp.class));
            }
        });
        Tregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, Signup_Otp.class));
            }
        });

    }

    @IgnoreExtraProperties
    public  static class User {

        public String Password;
        public String Userid;
        public String Name;


        public User() {
            // Default constructor required for calls to DataSnapshot.getValue(User.class)
        }

        public User(String Password, String Userid,String Name) {
            this.Password = Password;
            this.Userid = Userid;
            this.Name=Name;
        }

    }
}