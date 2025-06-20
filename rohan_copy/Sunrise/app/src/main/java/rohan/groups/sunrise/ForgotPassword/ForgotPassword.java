package rohan.groups.sunrise.ForgotPassword;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import rohan.groups.sunrise.Functionality.Encryption;
import rohan.groups.sunrise.Functionality.Session;
import rohan.groups.sunrise.MainActivity;
import rohan.groups.sunrise.R;
import rohan.groups.sunrise.Signup.PasswordSignup;

public class ForgotPassword extends AppCompatActivity {

    private static final String TAG = "LOGIN";
    private Session session;
    private EditText password,cpassword;
    private Button register;
    String username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);


        session=new Session(ForgotPassword.this);
        password=findViewById(R.id.password);
        cpassword=findViewById(R.id.cpassword);
        register=findViewById(R.id.register);
        username=getIntent().getStringExtra("mobile");
        session.setusername("");
        password.requestFocus();
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String password1 = password.getText().toString().trim();
                String password2 = cpassword.getText().toString().trim();


                if(TextUtils.isEmpty(username)){
                    Toast.makeText(ForgotPassword.this,"Technical Error.Error Code #1200. Try after sometime or contact admin",Toast.LENGTH_SHORT).show();
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

                if (!TextUtils.isEmpty(username)) {
                    final DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Users").child("+91" + username);
                    String hash = null;
                    try {
                        hash = Encryption.encrypt(password1);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    ref.child("Password").setValue(hash);
                    session.setusername("+91"+username);

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

                    Intent intent = new Intent(ForgotPassword.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();

                }
                else{
                    Toast.makeText(ForgotPassword.this,"Server Error Code #1200. Please try after Sometime or contact Admin",Toast.LENGTH_LONG).show();
                    return;
                }
            }

        });

    }
}
