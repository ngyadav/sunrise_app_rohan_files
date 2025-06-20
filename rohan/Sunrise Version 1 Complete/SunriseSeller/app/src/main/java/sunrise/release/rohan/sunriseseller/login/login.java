package sunrise.release.rohan.sunriseseller.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.ValueEventListener;

import sunrise.release.rohan.sunriseseller.Functionality.Encryption;
import sunrise.release.rohan.sunriseseller.Functionality.LoginSession;
import sunrise.release.rohan.sunriseseller.R;
import sunrise.release.rohan.sunriseseller.forgotpassword.forgotpassword;
import sunrise.release.rohan.sunriseseller.home.UnRegistered;
import sunrise.release.rohan.sunriseseller.home.home;


public class login extends AppCompatActivity {


    private static final String TAG = "LOGIN";
    private LoginSession session;
    private FirebaseAuth mAuth;
    private EditText Temail, Tpassword;
    private Button Bregister;
    private TextView Tforgot, Tregister;
    private ProgressBar progressBar;
    private static int RC_SIGN_IN = 100;
    private int temp = 0;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();

        setContentView(R.layout.activity_login);

        Temail = (EditText) findViewById(R.id.Tpassword);
        Tpassword = (EditText) findViewById(R.id.Tpassword1);
        Bregister = (Button) findViewById(R.id.Bregister);
        Tforgot = (TextView) findViewById(R.id.forgotpassword);
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


                database.child("SellerUsers").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        // TODO: handle the post here
                        if (dataSnapshot.hasChild(email1)) {
                            DatabaseReference ref = database.child("SellerUsers").child(email1);
                            ref.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {

                                    // TODO: handle the post here
                                    User user = dataSnapshot.getValue(User.class);
                                    String hash = null;
                                   try {
                                        hash = Encryption.decrypt(user.Password);
                                    }
                                    catch(Exception e){
                                        e.printStackTrace();
                                    }
                                      if (password1.equals(hash)) {
                                            session = new LoginSession(login.this);
                                            String no = Temail.getText().toString();
                                            session.setusename(no);
                                          session.setstatus(user.Status);
                                            progressBar.setVisibility(View.GONE);
                                            if(user.Status.equalsIgnoreCase("Not Registered"))
                                            {
                                                Intent intent = new Intent(login.this, UnRegistered.class);
                                                startActivity(intent);
                                                finish();
                                            }
                                            else {
                                                Intent intent = new Intent(login.this, home.class);
                                                startActivity(intent);
                                                finish();
                                            }
                                        } else {
                                            progressBar.setVisibility(View.GONE);
                                            Toast.makeText(getApplicationContext(), "Password Doesn't Match", Toast.LENGTH_SHORT).show();
                                            return;
                                        }

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

    }

@IgnoreExtraProperties
public  static class User {


    public String Password,Status;


    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String Password,String Status) {
        this.Password = Password;
        this.Status=Status;

    }

}
}