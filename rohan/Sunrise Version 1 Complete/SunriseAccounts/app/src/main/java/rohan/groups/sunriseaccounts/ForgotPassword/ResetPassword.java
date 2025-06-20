package rohan.groups.sunriseaccounts.ForgotPassword;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import rohan.groups.sunriseaccounts.Functionality.Sessions;
import rohan.groups.sunriseaccounts.MainActivity;
import rohan.groups.sunriseaccounts.R;

public class ResetPassword extends AppCompatActivity {

    Sessions sessions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        final EditText password=findViewById(R.id.Tpassword);
        final EditText cpassword=findViewById(R.id.Tcpassword);
        Button bregisiter=findViewById(R.id.Bregister);

        sessions=new Sessions(ResetPassword.this);

        final String no=sessions.getusername();

        sessions.setusername("");


        bregisiter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(password.getText().toString())){
                    Toast.makeText(ResetPassword.this,"Enter Password",Toast.LENGTH_SHORT).show();
                    password.requestFocus();
                    return;
                }

                if(TextUtils.isEmpty(cpassword.getText().toString())){
                    Toast.makeText(ResetPassword.this,"Enter Confirm password",Toast.LENGTH_SHORT).show();
                    cpassword.requestFocus();
                    return;
                }

                if(!password.getText().toString().equals(cpassword.getText().toString()))
                {
                    Toast.makeText(ResetPassword.this,"Password and Confirm Password Doesnt Match",Toast.LENGTH_SHORT).show();
                    return;
                }



                if(password.getText().length()<6){
                    Toast.makeText(ResetPassword.this,"Password must be Minimum of 6 Characters",Toast.LENGTH_SHORT).show();
                    return;
                }

                final DatabaseReference ref= FirebaseDatabase.getInstance().getReference().child("Users").child(no);

                ref.child("Password").setValue(password.getText().toString());

                sessions.setusername(no);


                Intent i = new Intent(ResetPassword.this, MainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);



            }
        });


    }
}
