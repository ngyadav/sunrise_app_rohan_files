package sunrise.release.rohan.sunriseseller.forgotpassword;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import sunrise.release.rohan.sunriseseller.Functionality.Encryption;
import sunrise.release.rohan.sunriseseller.Functionality.LoginSession;
import sunrise.release.rohan.sunriseseller.R;
import sunrise.release.rohan.sunriseseller.home.home;
import sunrise.release.rohan.sunriseseller.login.login;

public class forgotpassword1 extends AppCompatActivity {
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    private LoginSession session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword1);
        final EditText password=(EditText)findViewById(R.id.Tpassword);
        final EditText cpassword=(EditText)findViewById(R.id.Tcpassword);
        Button confirm=(Button)findViewById(R.id.Bregister);
        session = new LoginSession(forgotpassword1.this);
        final String name=session.getusename();
        session.setusename("");

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (password.getText().toString().equals("")) {
                    Toast.makeText(forgotpassword1.this, "Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                } if (cpassword.getText().toString().equals("")) {
                    Toast.makeText(forgotpassword1.this, "Enter Confirm Password", Toast.LENGTH_SHORT).show();
                    return;
                } if (!password.getText().toString().equals(cpassword.getText().toString())) {
                    Toast.makeText(forgotpassword1.this, "Passwords Doesnt Match", Toast.LENGTH_SHORT).show();
                    return;
                }

                String hash = null;
                try {
                    hash = Encryption.encrypt(password.getText().toString());
                }
                catch(Exception e){
                    e.printStackTrace();
                }

                DatabaseReference ref = database.child("SellerUsers").child("+91"+name);
                ref.child("Password").setValue(hash);
                session.setusename(name);
                Intent intent=new Intent(forgotpassword1.this, home.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
