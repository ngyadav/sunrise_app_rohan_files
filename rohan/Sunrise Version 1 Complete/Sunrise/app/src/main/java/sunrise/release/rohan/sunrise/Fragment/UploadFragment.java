package sunrise.release.rohan.sunrise.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import sunrise.release.rohan.sunrise.Accesories.AccesoriesUploadFragment;
import sunrise.release.rohan.sunrise.Computers.ComputerUploadFragment;
import sunrise.release.rohan.sunrise.Fashion.FashionUploadFragment;
import sunrise.release.rohan.sunrise.GeneralStore.GeneralUploadFragment;
import sunrise.release.rohan.sunrise.HealthCare.HealthCareUploadFragment;
import sunrise.release.rohan.sunrise.HomeAppliances.HomeAppliancesUploadFragment;
import sunrise.release.rohan.sunrise.Homedelivery.HomedeliveryUpload;
import sunrise.release.rohan.sunrise.Mobiles.MobileUploadFragment;
import sunrise.release.rohan.sunrise.NewArrival.NewArrivalUpload;
import sunrise.release.rohan.sunrise.R;
import sunrise.release.rohan.sunrise.Services.ServicesUploadFragment;
import sunrise.release.rohan.sunrise.Stationary.StationaryUploadFragment;

import sunrise.release.rohan.sunrise.Computers.ComputerUploadFragment;
import sunrise.release.rohan.sunrise.Fashion.FashionUploadFragment;
import sunrise.release.rohan.sunrise.GeneralStore.GeneralUploadFragment;
import sunrise.release.rohan.sunrise.Stationary.StationaryUploadFragment;

public class UploadFragment extends Fragment {

    ImageView uMobiles,uAccesories,uComputer,uHome,uGeneral,uStationary,unewarrival,uFashion,uDelivery;
    Fragment fragment=null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_upload, container, false);
        uMobiles=(ImageView)v.findViewById(R.id.uMobile);
        uAccesories=(ImageView)v.findViewById(R.id.uAccesories);
        uComputer=(ImageView)v.findViewById(R.id.uComputer);
        uHome=(ImageView)v.findViewById(R.id.uHomeappliances);
        uGeneral=(ImageView)v.findViewById(R.id.uGeneral);
        uStationary=(ImageView)v.findViewById(R.id.uStationary);
        unewarrival=(ImageView)v.findViewById(R.id.unewarrival);
        uFashion=(ImageView)v.findViewById(R.id.uFashion);
        uDelivery=(ImageView)v.findViewById(R.id.uDelivery);

        uMobiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment= new MobileUploadFragment();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.frame_container, fragment).commit();

            }
        });
         uAccesories.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 fragment= new AccesoriesUploadFragment();
                 FragmentManager fragmentManager = getFragmentManager();
                 fragmentManager.beginTransaction()
                         .addToBackStack(null)
                         .replace(R.id.frame_container, fragment).commit();
             }
         });
        uComputer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment= new ComputerUploadFragment();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.frame_container, fragment).commit();
            }
        });
        uHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment= new HomeAppliancesUploadFragment();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.frame_container, fragment).commit();
            }
        });
        uGeneral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment= new GeneralUploadFragment();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.frame_container, fragment).commit();
            }
        });
        uStationary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment= new StationaryUploadFragment();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.frame_container, fragment).commit();
            }
        });

        uFashion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment= new FashionUploadFragment();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.frame_container, fragment).commit();
            }
        });
        unewarrival.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment= new NewArrivalUpload();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.frame_container, fragment).commit();
            }
        });
        uDelivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment= new HomedeliveryUpload();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.frame_container, fragment).commit();
            }
        });

        return v;
    }


}
