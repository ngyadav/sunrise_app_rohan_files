package sunrise.release.rohan.sunrise.Functionality;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import sunrise.release.rohan.sunrise.Mobiles.Mobile;
import sunrise.release.rohan.sunrise.R;
public class EnlargeImage extends Fragment {

    CarouselView mobile_pic_details;
    Bundle bundle=new Bundle();
    String[] sampleimages=new String[3];
    public EnlargeImage() {
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
        View v=inflater.inflate(R.layout.fragment_enlarge_image, container, false);
        bundle=getArguments();
        String name=bundle.getString("name");
        String image1=bundle.getString("0");
        String image2=bundle.getString("1");
        String image3=bundle.getString("2");


        mobile_pic_details=(CarouselView)v.findViewById(R.id.mobile_pic_details);


                sampleimages[0]=image1;
                sampleimages[1]=image2;
                sampleimages[2]=image3;

                mobile_pic_details.setImageListener(imageListener);
                mobile_pic_details.setPageCount(sampleimages.length);


        return v;
    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
//            imageView.setImageResource(R.drawable.accesories);
            Glide.with(getContext())
                    .load(sampleimages[position])
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageView);
        }
    };

}
