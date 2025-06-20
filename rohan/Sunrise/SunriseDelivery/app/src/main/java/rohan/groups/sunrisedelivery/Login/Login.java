package rohan.groups.sunrisedelivery.Login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

import rohan.groups.sunrisedelivery.Entity.Users;
import rohan.groups.sunrisedelivery.Forgotpassword.OtpActivity;
import rohan.groups.sunrisedelivery.Functionality.Session;
import rohan.groups.sunrisedelivery.MainActivity;
import rohan.groups.sunrisedelivery.R;

public class Login extends AppCompatActivity {


    private Session session;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final EditText phonenumber=findViewById(R.id.phonenumber);
        final EditText password=findViewById(R.id.password);
        TextView register=findViewById(R.id.register);
        TextView forgotpassword=findViewById(R.id.forgotpassword);
        Button bregister=findViewById(R.id.Bregister);

        session=new Session(Login.this);

        final DatabaseReference database = FirebaseDatabase.getInstance().getReference();

        forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this,
                        OtpActivity.class));
            }
        });


        bregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final String phone=phonenumber.getText().toString();
                final String pass=password.getText().toString();

                if(TextUtils.isEmpty(phone)){
                    phonenumber.setError("Enter PhoneNumber");
                    phonenumber.requestFocus();
                    return;
                }


                if(TextUtils.isEmpty(pass)){
                    password.setError("Enter Password");
                    password.requestFocus();
                    return;
                }

                database.child("DeliveryBoys").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if (dataSnapshot.hasChild(phone)) {

                            Users user = dataSnapshot.child(phone).getValue(Users.class);

                            if(user.Password.equals(pass)) {


                                    session.setusername(user.Number);
                                    session.setname(user.Name);
                                    session.setpassword(user.Password);


                                    startActivity(new Intent(Login.this,
                                            MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                                    finish();



                            }
                            else {
                                password.setError("Wrong Password");
                                return;
                            }

                        }
                        else {
                            Toast.makeText(getApplicationContext(), "User not Registered", Toast.LENGTH_SHORT).show();
                            return;
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

    }
}
