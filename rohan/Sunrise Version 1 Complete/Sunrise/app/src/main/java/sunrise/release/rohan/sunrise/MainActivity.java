package sunrise.release.rohan.sunrise;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import sunrise.release.rohan.sunrise.login.login;
import sunrise.release.rohan.sunrise.splash.SplashScreen;
import com.google.firebase.auth.FirebaseAuth;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

public class MainActivity extends AppCompatActivity {

    CarouselView carouselView;
    String [] sampleImages = {"https://firebasestorage.googleapis.com/v0/b/sunrise-36b9a.appspot.com/o/Category%2Fapsara.png?alt=media&token=034d8d44-818a-4c91-8fc1-402d7147bd15","https://firebasestorage.googleapis.com/v0/b/sunrise-36b9a.appspot.com/o/Category%2Fcomputer.png?alt=media&token=daa1e3cb-4fcd-4622-b35e-954578a03035"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        carouselView = (CarouselView) findViewById(R.id.carouselView);
        carouselView.setPageCount(sampleImages.length);

        carouselView.setImageListener(imageListener);
    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
//            imageView.setImageResource(R.drawable.accesories);
            Glide.with(MainActivity.this)
                    .load(sampleImages[position])
                    .into(imageView);
        }
    };

}
