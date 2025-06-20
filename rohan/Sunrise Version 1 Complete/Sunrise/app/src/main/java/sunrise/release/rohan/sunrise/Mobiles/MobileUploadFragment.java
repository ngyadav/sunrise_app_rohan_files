package sunrise.release.rohan.sunrise.Mobiles;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import sunrise.release.rohan.sunrise.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MobileUploadFragment extends Fragment {
    List<String> areas = new ArrayList<String>();
    Spinner mBrand;
    TextView mName,mPrice,mCashback,mFeature1,mFeature2,mFeature3,mFeature4,mFeature5,mFeature6,mRatings,mQuantity,mSearch;
    TextView mFeature7,mFeature8,mFeature9,mFeature10,mFeature11,mFeature12;
    Button mSubmit;
    ProgressBar mProgress;
    ScrollView mScrollView;
    int temp=0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(sunrise.release.rohan.sunrise.R.layout.fragment_mobileupload, container, false);
        mName=(TextView)v.findViewById(sunrise.release.rohan.sunrise.R.id.mName);
        mPrice=(TextView)v.findViewById(sunrise.release.rohan.sunrise.R.id.mPrice);
        mCashback=(TextView)v.findViewById(sunrise.release.rohan.sunrise.R.id.mCashback);
        mFeature1=(TextView)v.findViewById(sunrise.release.rohan.sunrise.R.id.mFeature1);
        mFeature2=(TextView)v.findViewById(sunrise.release.rohan.sunrise.R.id.mFeature2);
        mFeature3=(TextView)v.findViewById(sunrise.release.rohan.sunrise.R.id.mFeature3);
        mFeature4=(TextView)v.findViewById(sunrise.release.rohan.sunrise.R.id.mFeature4);
        mFeature5=(TextView)v.findViewById(sunrise.release.rohan.sunrise.R.id.mFeature5);
        mFeature6=(TextView)v.findViewById(sunrise.release.rohan.sunrise.R.id.mFeature6);
        mFeature7=(TextView)v.findViewById(sunrise.release.rohan.sunrise.R.id.mFeature7);
        mFeature8=(TextView)v.findViewById(sunrise.release.rohan.sunrise.R.id.mFeature8);
        mFeature9=(TextView)v.findViewById(sunrise.release.rohan.sunrise.R.id.mFeature9);
        mFeature10=(TextView)v.findViewById(sunrise.release.rohan.sunrise.R.id.mFeature10);
        mFeature11=(TextView)v.findViewById(sunrise.release.rohan.sunrise.R.id.mFeature11);
        mFeature12=(TextView)v.findViewById(sunrise.release.rohan.sunrise.R.id.mFeature12);
        mBrand=(Spinner) v.findViewById(sunrise.release.rohan.sunrise.R.id.mBrand);
        mRatings=(TextView)v.findViewById(sunrise.release.rohan.sunrise.R.id.mRatings);
        mQuantity=(TextView)v.findViewById(sunrise.release.rohan.sunrise.R.id.mQuantity);
        mSubmit=(Button)v.findViewById(sunrise.release.rohan.sunrise.R.id.mSubmit);
        mProgress=(ProgressBar)v.findViewById(sunrise.release.rohan.sunrise.R.id.mProgress);
        mScrollView=(ScrollView)v.findViewById(sunrise.release.rohan.sunrise.R.id.mScrollView);
        mProgress.setVisibility(View.GONE);
        mSearch=(TextView)v.findViewById(sunrise.release.rohan.sunrise.R.id.mobile_upload_search);


        areas.add("APPLE");
        areas.add("MI");
        areas.add("NOKIA");
        areas.add("SAMSUNG");
        areas.add("OPPO");
        areas.add("VIVO");
        areas.add("HONOR");
        areas.add("KARBON");
        areas.add("LAVA");
        areas.add("ITEL");
        areas.add("OTHERS");

        ArrayAdapter<String> areasAdapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_layout, areas);
        areasAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mBrand.setAdapter(areasAdapter);
        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mName.getText().toString().equalsIgnoreCase(""))
                {
                    Toast.makeText(getContext(),"Enter Name",Toast.LENGTH_SHORT).show(); mName.requestFocus();
                    mScrollView.fullScroll(ScrollView.FOCUS_UP);
                    mName.requestFocus();
                    return;
                }
                if(mPrice.getText().toString().equalsIgnoreCase(""))
                {
                    Toast.makeText(getContext(),"Enter Price",Toast.LENGTH_SHORT).show(); mName.requestFocus();
                    mScrollView.fullScroll(ScrollView.FOCUS_UP);
                    mPrice.requestFocus();
                    return;
                }

                if(mCashback.getText().toString().equalsIgnoreCase(""))
                {
                    Toast.makeText(getContext(),"Enter Reward Points",Toast.LENGTH_SHORT).show(); mName.requestFocus();
                    mScrollView.fullScroll(ScrollView.FOCUS_UP);
                    mCashback.requestFocus();
                    return;
                }
                mProgress.setVisibility(View.VISIBLE);

                DatabaseReference ref= FirebaseDatabase.getInstance().getReference().child("Category").child("Mobile").child(mName.getText().toString());
                ref.child("Name").setValue(mName.getText().toString());
                ref.child("Price").setValue(mPrice.getText().toString());
                ref.child("Cashback").setValue(mCashback.getText().toString());
                ref.child("Feature1").setValue(mFeature1.getText().toString());
                ref.child("Feature2").setValue(mFeature2.getText().toString());
                ref.child("Feature3").setValue(mFeature3.getText().toString());
                ref.child("Feature4").setValue(mFeature4.getText().toString());
                ref.child("Feature5").setValue(mFeature5.getText().toString());
                ref.child("Feature6").setValue(mFeature6.getText().toString());
                ref.child("Feature7").setValue(mFeature7.getText().toString());
                ref.child("Feature8").setValue(mFeature8.getText().toString());
                ref.child("Feature9").setValue(mFeature9.getText().toString());
                ref.child("Feature10").setValue(mFeature10.getText().toString());
                ref.child("Feature11").setValue(mFeature11.getText().toString());
                ref.child("Feature12").setValue(mFeature12.getText().toString());
                ref.child("Brand").setValue(mBrand.getSelectedItem().toString());
                ref.child("Ratings").setValue(mRatings.getText().toString());
                ref.child("Quantity").setValue(mQuantity.getText().toString());
                ref.child("Stock").setValue("In Stock");
                if(temp==0) {
                    ref.child("Image1").setValue("Empty");
                    ref.child("Image2").setValue("Empty");
                    ref.child("Image3").setValue("Empty");
                    ref.child("Image4").setValue("Empty");
                    ref.child("Image5").setValue("Empty");
                    ref.child("Image6").setValue("Empty");
                    ref.child("Category").setValue("Mobile");
                    ref.child("Offers").child("offer1").setValue("");
                }
                mProgress.setVisibility(View.GONE);

                mName.setText("");
                mPrice.setText("");
                mCashback.setText("");
                mFeature1.setText("");
                mFeature2.setText("");
                mFeature3.setText("");
                mFeature4.setText("");
                mFeature5.setText("");
                mFeature6.setText("");
                mFeature7.setText("");
                mFeature8.setText("");
                mFeature9.setText("");
                mFeature10.setText("");
                mFeature11.setText("");
                mFeature12.setText("");
                mRatings.setText("");
                mQuantity.setText("");
                mName.requestFocus();
                mScrollView.fullScroll(ScrollView.FOCUS_UP);
                Toast.makeText(getContext(),"Successful",Toast.LENGTH_SHORT).show();
            }
        });


        mSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mName.getText().toString().equalsIgnoreCase(""))
                {
                    Toast.makeText(getContext(),"Enter Name",Toast.LENGTH_SHORT).show(); mName.requestFocus();
                    mScrollView.fullScroll(ScrollView.FOCUS_UP);
                    return;
                }
                final DatabaseReference database = FirebaseDatabase.getInstance().getReference();
                DatabaseReference ref = database.child("Category").child("Mobile");
                ref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChild(mName.getText().toString())) {
                            DatabaseReference ref = database.child("Category").child("Mobile").child(mName.getText().toString());
                            ref.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    Mobile mobile = dataSnapshot.getValue(Mobile.class);

                                    mName.setText(mobile.Name);
                                    mPrice.setText(mobile.Price);
                                    mCashback.setText(mobile.Cashback);
                                    mFeature1.setText(mobile.Feature1);
                                    mFeature2.setText(mobile.Feature2);
                                    mFeature3.setText(mobile.Feature3);
                                    mFeature4.setText(mobile.Feature4);
                                    mFeature5.setText(mobile.Feature5);
                                    mFeature6.setText(mobile.Feature6);
                                    mFeature7.setText(mobile.Feature7);
                                    mFeature8.setText(mobile.Feature8);
                                    mFeature9.setText(mobile.Feature9);
                                    mFeature10.setText(mobile.Feature10);
                                    mFeature11.setText(mobile.Feature11);
                                    mFeature12.setText(mobile.Feature12);
                                    mBrand.setSelection(getIndex(mBrand,mobile.Brand));
                                    mRatings.setText(mobile.Ratings);
                                    mQuantity.setText(mobile.Quantity);
                                    temp++;
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }

                });
            }
        });
        return v;
    }
    private int getIndex(Spinner spinner, String myString){

        int index = 0;

        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).equals(myString)){
                index = i;
            }
        }
        return index;
    }

}
