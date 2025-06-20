package rohan.groups.sunrise.Signup;

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
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import rohan.groups.sunrise.Functionality.Encryption;
import rohan.groups.sunrise.Functionality.Session;
import rohan.groups.sunrise.MainActivity;
import rohan.groups.sunrise.R;

public class PasswordSignup extends AppCompatActivity {

    private static final String TAG = "LOGIN";
    private Session session;
    private EditText name,email,password,cpassword,referral;
    private Button register;
    private static int RC_SIGN_IN = 100;
    String username;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_signup);


        session=new Session(PasswordSignup.this);
        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        cpassword=findViewById(R.id.cpassword);
        register=findViewById(R.id.register);
        referral=findViewById(R.id.referral);
        username=getIntent().getStringExtra("mobile");
        session.setusername("");
        name.requestFocus();

         String pattern = "dd-MM-yyyy";
        final String dateInString =new SimpleDateFormat(pattern).format(new Date());

            register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String password1 = password.getText().toString().trim();
                    String password2 = cpassword.getText().toString().trim();

                    if (TextUtils.isEmpty(name.getText().toString())) {
                        name.setError("Enter Name");
                        name.requestFocus();
                        return;
                    }
                    if (TextUtils.isEmpty(password1)) {
                        password.setError("Enter Password");
                        password.requestFocus();
                        return;
                    }

                    if (password1.length() < 6) {
                        Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_LONG).show();
                        return;
                    }
                    if (!password1.equals(password2)) {
                        Toast.makeText(getApplicationContext(), "Password & Confirm Password Don't Match!", Toast.LENGTH_LONG).show();
                        return;
                    }
                    if (TextUtils.isEmpty(email.getText())) {
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

                        ref.child("Password").setValue(hash);
                        ref.child("Status").setValue("Registered");
                        ref.child("Name").setValue(name.getText().toString());
                        ref.child("Email").setValue(email.getText().toString());
                        ref.child("Role").setValue("user");
                        ref.child("Rewards").setValue(0);
                        ref.child("Phone").setValue(username);
                        ref.child("Referral").setValue(referral.getText().toString());
                        ref.child("JoiningDate").setValue(dateInString);
                        session.setrole("user");
                        session.setusername("+91"+username);

//                        if(TextUtils.isEmpty(session.getreferral())){
//                        }
//                        else{
//                            ref.child("Referral").setValue(session.getreferral());
//                        }


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



                        Intent intent = new Intent(PasswordSignup.this, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();

                    }
                    else{
                        Toast.makeText(PasswordSignup.this,"Server Error Code #1200. Please try after Sometime or contact Admin",Toast.LENGTH_LONG).show();
                        return;
                    }
                }

            });

    }
}
