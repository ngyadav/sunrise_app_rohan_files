package rohan.groups.sunrise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import rohan.groups.sunrise.Login.Login;
import rohan.groups.sunrise.SplashScreen.SplashScreen;

public class WelcomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

        ImageView home,register,help;

        home=findViewById(R.id.home);
        register=findViewById(R.id.register);
        help=findViewById(R.id.help);


        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WelcomeScreen.this,
                        MainActivity.class));
            }
        });


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WelcomeScreen.this,
                        Login.class));
            }
        });


        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(WelcomeScreen.this,"Coming Soon",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
