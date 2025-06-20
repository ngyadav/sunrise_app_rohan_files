package rohan.groups.sunrise;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.github.paolorotolo.appintro.AppIntroFragment;
import com.github.paolorotolo.appintro.model.SliderPage;
import com.google.firebase.database.annotations.Nullable;

import rohan.groups.sunrise.Fragments.firstfragment;
import rohan.groups.sunrise.Fragments.secondfragment;
import rohan.groups.sunrise.Fragments.thirdfragment;
import rohan.groups.sunrise.Login.Login;

public class AppIntro extends com.github.paolorotolo.appintro.AppIntro {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_app_intro);




        Fragment firstfragment=new firstfragment();
        Fragment secondfragment=new secondfragment();
        Fragment thirdfragment=new thirdfragment();

        addSlide(firstfragment);
        addSlide(secondfragment);
        addSlide(thirdfragment);


        setBarColor(Color.parseColor("#1A587C"));
        setSeparatorColor(Color.parseColor("#03AC13"));


        showSkipButton(true);
//        setProgressButtonEnabled(false);
    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        // Do something when users tap on Skip button.
        startActivity(new Intent(AppIntro.this,
                WelcomeScreen.class));
        finish();

    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        // Do something when users tap on Done button.

        startActivity(new Intent(AppIntro.this,
                WelcomeScreen.class));
        finish();


    }

    @Override
    public void onSlideChanged(@Nullable Fragment oldFragment, @Nullable Fragment newFragment) {
        super.onSlideChanged(oldFragment, newFragment);
        // Do something when the slide changes.
    }

}
