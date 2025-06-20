package rohan.groups.sunriseaccounts;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;

import rohan.groups.sunriseaccounts.Fragments.AccountStatusFragment;
import rohan.groups.sunriseaccounts.Fragments.AccountsFragment;
import rohan.groups.sunriseaccounts.Fragments.AccountsHistoryFragment;
import rohan.groups.sunriseaccounts.Fragments.AddEasyFragment;
import rohan.groups.sunriseaccounts.Fragments.AdminAccountsFragment;
import rohan.groups.sunriseaccounts.Fragments.AdminEasyFragment;
import rohan.groups.sunriseaccounts.Fragments.CreateUserFragment;
import rohan.groups.sunriseaccounts.Fragments.EasyFragment;
import rohan.groups.sunriseaccounts.Fragments.EasyHistoryFragment;
import rohan.groups.sunriseaccounts.Fragments.EasyStatusFragment;
import rohan.groups.sunriseaccounts.Functionality.Sessions;
import rohan.groups.sunriseaccounts.Login.Login;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private Sessions session;
    private static final String TAG = "Token";
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private TextView rewards;
    private Button signout;
    private NavigationView   navigation;
    private StorageReference mstorageReference;
    private FirebaseAnalytics mFirebaseAnalytics;
    public Uri uri;
    Bundle bundle = new Bundle();
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    String a[] = new String[100];
    int i = 0;
    Fragment fragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        session = new Sessions(MainActivity.this);

        navigation = (NavigationView) findViewById(R.id.navigation);
        navigation.setItemIconTintList(null);
        navigation.setNavigationItemSelectedListener(this);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);

        navigation.getMenu().findItem(R.id.NAccounts).setVisible(false);
        navigation.getMenu().findItem(R.id.NAccountsHistory).setVisible(false);
        navigation.getMenu().findItem(R.id.NEasy).setVisible(false);
        navigation.getMenu().findItem(R.id.NEasyHistory).setVisible(false);
        navigation.getMenu().findItem(R.id.NCreateUser).setVisible(false);
        navigation.getMenu().findItem(R.id.NEasyAdd).setVisible(false);
        navigation.getMenu().findItem(R.id.NAStatus).setVisible(false);
        navigation.getMenu().findItem(R.id.NEStatus).setVisible(false);
        navigation.getMenu().findItem(R.id.NAAdminHistory).setVisible(false);
        navigation.getMenu().findItem(R.id.NEAdminHistory).setVisible(false);



        if(session.getrole().equalsIgnoreCase("Su"))
        {
            navigation.getMenu().findItem(R.id.NCreateUser).setVisible(true);
            navigation.getMenu().findItem(R.id.NEasyAdd).setVisible(true);
            navigation.getMenu().findItem(R.id.NAStatus).setVisible(true);
            navigation.getMenu().findItem(R.id.NEStatus).setVisible(true);
            navigation.getMenu().findItem(R.id.NAAdminHistory).setVisible(true);
            navigation.getMenu().findItem(R.id.NEAdminHistory).setVisible(true);
        }
        else{
            navigation.getMenu().findItem(R.id.NAccounts).setVisible(true);
            navigation.getMenu().findItem(R.id.NAccountsHistory).setVisible(true);
            navigation.getMenu().findItem(R.id.NEasy).setVisible(true);
            navigation.getMenu().findItem(R.id.NEasyHistory).setVisible(true);
        }





        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();



        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        fragment = new OrdersFragment();
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        fragmentManager.beginTransaction()
//                .addToBackStack(null)
//                .replace(R.id.frame_container, fragment).commit();







    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);


        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        // Handle presses on the action bar items
        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        switch (item.getItemId()) {
            case R.id.search:
                Toast.makeText(MainActivity.this, "Coming Soon", Toast.LENGTH_SHORT).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();
        if(id==R.id.NAccounts)
        {
            fragment=new AccountsFragment();
        }
        else if(id==R.id.NAccountsHistory)
        {
            fragment=new AccountsHistoryFragment();
        }
        else if(id==R.id.NEasy)
        {
            fragment=new EasyFragment();
        }
        else if(id==R.id.NEasyHistory)
        {
            fragment=new EasyHistoryFragment();
        }
        else if(id==R.id.NCreateUser)
        {
            fragment=new CreateUserFragment();
        }
        else if(id==R.id.NEasyAdd)
        {
            fragment=new AddEasyFragment();
        }
        else if(id==R.id.NAStatus)
        {
            fragment=new AccountStatusFragment();
        }
        else if(id==R.id.NEStatus)
        {
            fragment=new EasyStatusFragment();
        }
        else if(id==R.id.NAAdminHistory)
        {
            fragment=new AdminAccountsFragment();
        }
        else if(id==R.id.NEAdminHistory)
        {
            fragment=new AdminEasyFragment();
        }

        if (id == R.id.Nlogout) {
            session = new Sessions(MainActivity.this);
            session.setusername("");
            session.setname("");
            Intent intent = new Intent(this, Login.class);
            startActivity(intent);
            finish();

        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.frame_container, fragment).commit();

        }
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        mDrawerLayout.closeDrawers();
        return true;
    }
}