package sunrise.release.rohan.sunrise.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.concurrent.ScheduledExecutorService;

import sunrise.release.rohan.sunrise.Computers.ComputerFragment;
import sunrise.release.rohan.sunrise.Functionality.LoginSession;
import sunrise.release.rohan.sunrise.Homedelivery.HomedeliveryFragment;
import sunrise.release.rohan.sunrise.Mobiles.MobileFragment;
import sunrise.release.rohan.sunrise.NewArrival.NewArrivalDetails;
import sunrise.release.rohan.sunrise.NewArrival.NewArrivalFragment;
import sunrise.release.rohan.sunrise.R;
import sunrise.release.rohan.sunrise.Accesories.AccesoriesFragment;
import sunrise.release.rohan.sunrise.Fashion.FashionFragment;
import sunrise.release.rohan.sunrise.GeneralStore.GeneralFragment;
import sunrise.release.rohan.sunrise.HomeAppliances.HomeAppliancesFragment;
import sunrise.release.rohan.sunrise.Services.ServicesFragment;
import sunrise.release.rohan.sunrise.Stationary.StationaryFragment;

import static android.support.constraint.Constraints.TAG;
import static android.view.View.resolveSize;


public class CategoryFragment extends Fragment {

    ImageView cMobiles,cAccesories,cComputer,cHome,cGeneral,cStationary,cFashion,cHealth,cServies,cDelivery,cNewArrival;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    Fragment fragment=null;
    Bundle bundle=new Bundle();
    LoginSession session;
    public CategoryFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.fragment_category, container, false);
        cMobiles=(ImageView)v.findViewById(R.id.cMobile);
        cAccesories=(ImageView)v.findViewById(R.id.cAccesories);
        cComputer=(ImageView)v.findViewById(R.id.cComputer);
        cHome=(ImageView)v.findViewById(R.id.cHome);
        cGeneral=(ImageView)v.findViewById(R.id.cGeneral);
        cStationary=(ImageView)v.findViewById(R.id.cStationary);

        cFashion=(ImageView)v.findViewById(R.id.cFashion);
        cHealth=(ImageView)v.findViewById(R.id.cHealth);
        cServies=(ImageView)v.findViewById(R.id.cServices);
        cDelivery=(ImageView)v.findViewById(R.id.cDelivery);
        cNewArrival=(ImageView)v.findViewById(R.id.cNewArrival);

        session=new LoginSession(getContext());

        cMobiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment= new MobileFragment();
                session.setsub("MI");
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.frame_container, fragment).commit();

            }
        });
        cAccesories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment= new AccesoriesFragment();
                session.setsub("Accesories PENDRIVE");
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.frame_container, fragment).commit();
            }
        });
        cComputer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment= new ComputerFragment();
                session.setsub("Computer ACCESORIES");
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.frame_container, fragment).commit();
            }
        });
        cHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment= new HomeAppliancesFragment();
                session.setsub("HomeAppliances TELEVISION");
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.frame_container, fragment).commit();
            }
        });
        cGeneral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Coming Soon",Toast.LENGTH_SHORT).show();
                return;
//                fragment= new GeneralFragment();
//                session.setsub("General SUNRISE");
//                FragmentManager fragmentManager = getFragmentManager();
//                fragmentManager.beginTransaction()
//                        .addToBackStack(null)
//                        .replace(R.id.frame_container, fragment).commit();
            }
        });
        cStationary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Coming Soon",Toast.LENGTH_SHORT).show();
                return;
//                fragment= new StationaryFragment();
//                FragmentManager fragmentManager = getFragmentManager();
//                fragmentManager.beginTransaction()
//                        .addToBackStack(null)
//                        .replace(R.id.frame_container, fragment).commit();
            }
        });
        cDelivery.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     Toast.makeText(getContext(),"Coming Soon",Toast.LENGTH_SHORT).show();
                     return;
//                     fragment= new HomedeliveryFragment();
//                     FragmentManager fragmentManager = getFragmentManager();
//                     fragmentManager.beginTransaction()
//                             .addToBackStack(null)
//                             .replace(R.id.frame_container, fragment).commit();
                 }
             });
             cNewArrival.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     session.setsub("NewArrival GIFTS");
                     fragment= new NewArrivalFragment();
                     FragmentManager fragmentManager = getFragmentManager();
                     fragmentManager.beginTransaction()
                             .addToBackStack(null)
                             .replace(R.id.frame_container, fragment).commit();
                 }
             });
            cFashion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fragment= new FashionFragment();
                    session.setsub("Fashion SAREE");
                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction()
                            .addToBackStack(null)
                            .replace(R.id.frame_container, fragment).commit();
                }
            });

            cHealth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                fragment= new HealthCareFragment();
//                FragmentManager fragmentManager = getFragmentManager();
//                fragmentManager.beginTransaction()
//                        .addToBackStack(null)
//                        .replace(R.id.frame_container, fragment).commit();
                Toast.makeText(getContext(),"Coming Soon",Toast.LENGTH_SHORT).show();
                }
            });
            cServies.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fragment= new ServicesFragment();
                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction()
                            .addToBackStack(null)
                            .replace(R.id.frame_container, fragment).commit();
                }
            });

        return v;

    }


    @Override
    public void onStart() {

        super.onStart();

    }

}
