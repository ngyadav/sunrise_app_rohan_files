package rohan.groups.sunriseaccounts.Login;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
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

import rohan.groups.sunriseaccounts.ForgotPassword.ForgotPassword;
import rohan.groups.sunriseaccounts.Functionality.Sessions;
import rohan.groups.sunriseaccounts.MainActivity;
import rohan.groups.sunriseaccounts.R;

public class Login extends AppCompatActivity {

        private static final String TAG = "LOGIN";
        private Sessions session;
        private FirebaseAuth mAuth;
        private EditText Temail, Tpassword;
        private Button Bregister;
        private TextView Tforgot, Tregister, Timei;
        private ProgressBar progressBar;
        private static int RC_SIGN_IN = 100;
        private int temp = 0;
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();

        @SuppressLint("ClickableViewAccessibility")
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            setContentView(R.layout.activity_login);

//
//
//        mAuth.signInAnonymously()
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            // Sign in success, update UI with the signed-in user's information
//                            Log.d(TAG, "signInAnonymously:success");
//                            FirebaseUser user = mAuth.getCurrentUser();
//                        } else {
//                            // If sign in fails, display a message to the user.
//                            Log.w(TAG, "signInAnonymously:failure", task.getException());
//                            Toast.makeText(login.this, "Authentication failed.",
//                                    Toast.LENGTH_SHORT).show();
//                        }
//
//                    }
//                });


            Temail = (EditText) findViewById(R.id.Tusername);
            Tpassword = (EditText) findViewById(R.id.Tpassword);
            Bregister = (Button) findViewById(R.id.Bregister);
            Tforgot = (TextView) findViewById(R.id.forgotpassword);
            progressBar = findViewById(R.id.progressBar);
            progressBar.setVisibility(View.GONE);


            Tpassword.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    final int DRAWABLE_LEFT = 0;
                    final int DRAWABLE_TOP = 1;
                    final int DRAWABLE_RIGHT = 2;
                    final int DRAWABLE_BOTTOM = 3;

                    if(event.getAction() == MotionEvent.ACTION_UP) {
                        if(event.getRawX() >= (Tpassword.getRight() - Tpassword.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                            // your action

                            if(Tpassword.getTransformationMethod().equals(HideReturnsTransformationMethod.getInstance())){
                                Tpassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            }
                            else{
                                Tpassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            }



                        }
                    }
                    return false;
                }
            });

            Bregister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                Intent intent = new Intent(Login.this, MainActivity.class);
//                startActivity(intent);
//                finish();



                    final String email1 = Temail.getText().toString();
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

                                        if (password1.equals(user.Password)) {

                                            if(user.Status.equals("Active")) {
                                                session = new Sessions(Login.this);
                                                String no = Temail.getText().toString();
                                                session.setusername(no);
                                                session.setname(user.Name);
                                                session.setrole(user.Role);
                                                Intent intent = new Intent(Login.this, MainActivity.class);
                                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                startActivity(intent);
                                                progressBar.setVisibility(View.GONE);
                                            }
                                            else{
                                                progressBar.setVisibility(View.GONE);
                                                AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                                                builder.setTitle("User Not Verified");
                                                builder.setMessage("Please Contact Support Center for Further Information\n No:9686277477")
                                                        .setCancelable(false);


                                                AlertDialog alertDialog = builder.create();
                                                alertDialog.show();
                                                return;
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
                            } else {
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(getApplicationContext(), "User not Registered", Toast.LENGTH_SHORT).show();
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
                    startActivity(new Intent(Login.this, ForgotPassword.class));
                }
            });


        }

        @IgnoreExtraProperties
        public static class User {

         public String Name;
         public String Password;
         public String Role;
         public String Status;
         public String UserName;
         public Double EasyRecharge;
         public Double Accounts;


            public User() {
                // Default constructor required for calls to DataSnapshot.getValue(User.class)
            }

            public User(String Name,String Password,String Role,String Status,String UserName,Double EasyRecharge,Double Accounts) {
               this.Name=Name;
               this.Password=Password;
               this.Role=Role;
               this.Status=Status;
               this.UserName=UserName;
               this.EasyRecharge=EasyRecharge;
               this.Accounts=Accounts;
            }

        }
    }
