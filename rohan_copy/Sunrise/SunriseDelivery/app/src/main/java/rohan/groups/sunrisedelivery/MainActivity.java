package rohan.groups.sunrisedelivery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yarolegovich.slidingrootnav.SlidingRootNav;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;

import rohan.groups.sunrisedelivery.Fragment.CurrentFragment;
import rohan.groups.sunrisedelivery.Fragment.HelpFragment;
import rohan.groups.sunrisedelivery.Fragment.OrderHistory;
import rohan.groups.sunrisedelivery.Fragment.ProfileFragment;
import rohan.groups.sunrisedelivery.Functionality.Session;
import rohan.groups.sunrisedelivery.Login.Login;

public class MainActivity extends AppCompatActivity {

    private SlidingRootNav slidingRootNav;
    Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        SlidingRootNav drawer;

        session=new Session(MainActivity.this);


        Fragment fragment = new CurrentFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .addToBackStack(null)
                .replace(R.id.frame_container, fragment).commit();


        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);

        slidingRootNav= new SlidingRootNavBuilder(this)
                .withMenuOpened(false)
                .withMenuLayout(R.layout.menu_left)
                .inject();



        final ImageView toggle=findViewById(R.id.toggle);

        toggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(slidingRootNav.isMenuOpened()) {
                    slidingRootNav.closeMenu();
                }
                else {
                    slidingRootNav.openMenu();
                }

            }
        });



        ImageView close=findViewById(R.id.close);
        TextView currentorder=findViewById(R.id.currentorder);
        TextView orderhistory=findViewById(R.id.orderhistory);
        TextView help=findViewById(R.id.help);
        TextView profile=findViewById(R.id.profile);
        TextView logout=findViewById(R.id.logout);


        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                slidingRootNav.closeMenu();
            }
        });

        currentorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                slidingRootNav.closeMenu();

                Fragment fragment = new CurrentFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.frame_container, fragment).commit();

            }
        });


        orderhistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                slidingRootNav.closeMenu();

                Fragment fragment = new OrderHistory();
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.frame_container, fragment).commit();

            }
        });


        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                slidingRootNav.closeMenu();

                Fragment fragment = new HelpFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.frame_container, fragment).commit();

            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                slidingRootNav.closeMenu();

                Fragment fragment = new ProfileFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.frame_container, fragment).commit();

            }
        });


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                slidingRootNav.closeMenu();

                session.setrole("");
                session.setusername("");
                session.setname("");

                startActivity(new Intent(MainActivity.this,
                        Login.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK));
                finish();

            }
        });


    }
}
