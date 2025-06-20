package sunrise.release.rohan.sunrise.forgotpassword;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import sunrise.release.rohan.sunrise.Functionality.Encryption;
import sunrise.release.rohan.sunrise.Functionality.LoginSession;
import sunrise.release.rohan.sunrise.R;
import sunrise.release.rohan.sunrise.home.home;

public class Resetpassword extends AppCompatActivity {

    private EditText password,cpassword;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    private LoginSession session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resetpassword);

        password=(EditText)findViewById(R.id.Tpassword);
        cpassword=(EditText)findViewById(R.id.Tcpassword);
        Button confirm=(Button)findViewById(R.id.Bregister);
        session = new LoginSession(Resetpassword.this);
        final String name=session.getusename();
        session.setusename("");

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (password.getText().toString().equals("")) {
                    Toast.makeText(Resetpassword.this, "Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                } if (cpassword.getText().toString().equals("")) {
                    Toast.makeText(Resetpassword.this, "Enter Confirm Password", Toast.LENGTH_SHORT).show();
                    return;
                } if (!password.getText().toString().equals(cpassword.getText().toString())) {
                    Toast.makeText(Resetpassword.this, "Passwords Doesnt Match", Toast.LENGTH_SHORT).show();
                    return;
                }

                String hash = null;
                try {
                    hash = Encryption.encrypt(password.getText().toString());
                }
                catch(Exception e){
                    e.printStackTrace();
                }

                DatabaseReference ref = database.child("Users").child("+91"+name);
                ref.child("Password").setValue(hash).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        session.setusename(name);
                        Intent intent=new Intent(Resetpassword.this, home.class);
                        startActivity(intent);
                        finish();
                    }
                });

            }
        });

    }
}
