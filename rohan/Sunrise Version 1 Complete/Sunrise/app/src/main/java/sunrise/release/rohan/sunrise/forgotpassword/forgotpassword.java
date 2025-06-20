package sunrise.release.rohan.sunrise.forgotpassword;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import sunrise.release.rohan.sunrise.Functionality.LoginSession;
import sunrise.release.rohan.sunrise.R;
import sunrise.release.rohan.sunrise.login.login;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class forgotpassword extends AppCompatActivity {
    private Button Breset;
    private TextView Tmobile;
    private ProgressBar progressBar;
    private FirebaseAuth auth;
    private LoginSession session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);
        Breset=findViewById(R.id.BReset);
        Tmobile=findViewById(R.id.Tnumber);
        progressBar=findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
        session = new LoginSession(forgotpassword.this);
        Breset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatabaseReference ref1= FirebaseDatabase.getInstance().getReference().child("Users");
                ref1.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(!dataSnapshot.hasChild("+91"+Tmobile.getText().toString()))
                        {
                            Toast.makeText(forgotpassword.this,"User Not Registered",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        else {
                                session.setusename(Tmobile.getText().toString());
                                Intent intent=new Intent(forgotpassword.this,Resetpassword.class);
                                startActivity(intent);
                                finish();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });



            }

        });
    }
}
