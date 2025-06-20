package sunrise.release.rohan.sunrise.Fragment;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.InputType;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageClickListener;
import com.synnapps.carouselview.ImageListener;
import com.synnapps.carouselview.ViewListener;


import sunrise.release.rohan.sunrise.Accesories.AccesoriesDetails;
import sunrise.release.rohan.sunrise.Computers.ComputerDetails;
import sunrise.release.rohan.sunrise.Computers.ComputerFragment;
import sunrise.release.rohan.sunrise.Fashion.FashionDetails;
import sunrise.release.rohan.sunrise.Functionality.ForceUpdateChecker;
import sunrise.release.rohan.sunrise.HomeAppliances.HomeAppliancesDetails;
import sunrise.release.rohan.sunrise.Homedelivery.HomedeliveryFragment;
import sunrise.release.rohan.sunrise.Mobiles.Mobile;
import sunrise.release.rohan.sunrise.Mobiles.MobileFragment;
import sunrise.release.rohan.sunrise.Mobiles.ViewHolder;
import sunrise.release.rohan.sunrise.NewArrival.NewArrivalDetails;
import sunrise.release.rohan.sunrise.NewArrival.NewArrivalFragment;
import sunrise.release.rohan.sunrise.R;
import sunrise.release.rohan.sunrise.Accesories.AccesoriesFragment;
import sunrise.release.rohan.sunrise.Cart.CartFragment;
import sunrise.release.rohan.sunrise.Fashion.FashionFragment;
import sunrise.release.rohan.sunrise.Functionality.LoginSession;
import sunrise.release.rohan.sunrise.GeneralStore.GeneralFragment;
import sunrise.release.rohan.sunrise.HomeAppliances.HomeAppliancesFragment;
import sunrise.release.rohan.sunrise.Mobiles.MobileDetailsFragment;
import sunrise.release.rohan.sunrise.Orders.OrdersFragment;
import sunrise.release.rohan.sunrise.Services.ServicesFragment;
import sunrise.release.rohan.sunrise.Stationary.StationaryFragment;
import sunrise.release.rohan.sunrise.home.home;
import sunrise.release.rohan.sunrise.login.login;

import static android.content.ContentValues.TAG;


public class HomeFragment extends Fragment implements View.OnClickListener{
    RecyclerView mRecyclerView,recyclerViewmobiledetails;
    ScrollView scroll;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mref;
    ImageView hMobiles,hAccesories,hComputer,hHome,hGeneral,hStationary,hFashion,hHealth,hServies,hDelivery,hNewArrival;
     CarouselView  homecarousel;
    private LoginSession session;
     ImageView hmi,happle,hsamsung,hhonor,hoppo,hvivo,hkarbon,hlava,hitel,hother,hnokia;
     ImageView mobilepic1,mobilepic2,mobilepic3,mobilepic4,Newimage1,Newimage2,Newimage3,Newimage4;
     TextView mobilename1,mobilename2,mobilename3,mobilename4,Newname1,Newname2,Newname3,Newname4;
     TextView mobileprice1,mobileprice2,mobileprice3,mobileprice4,Newprice1,Newprice2,Newprice3,Newprice4;
     TextView mobilerewards1,mobilerewards2,mobilerewards3,mobilerewards4,Newrewards1,Newrewards2,Newrewards3,Newrewards4;
     LinearLayout linearLayout1,linearLayout2,linearLayout3,linearLayout4,Linearlayout5,Linearlayout6,Linearlayout7,Linearlayout8,seemore,seemore1,seemore2,seemore3,seemore4,seemore5;
     //Order
     HorizontalScrollView Horizontalorder;
     LinearLayout linearLayout7,Linearoder2;
        ImageView orderpic1,orderpic2;
        TextView ordername1,ordername2;
        TextView orderprice1,orderprice2;
        TextView orderrewards1,orderrewards2;

    ImageView homecartimage1,homecartimage2,homecartimage3;
    TextView homecartprice1,homecartprice2,homecartprice3;
    TextView homecartname1,homecartname2,homecartname3;
    TextView homerewards1,homerewards2,homerewards3;
    LinearLayout Linearcart,cart1,cart2,cart3;



     ImageView baner1,baner2;
     Button border,bcheckout;
     Button bno,byes;
    String str="\u20B9";

    //Accesories
    LinearLayout Linearlayout9,Linearlayout10,Linearlayout11,Linearlayout12,Linearlayout13,Linearlayout14;
    ImageView Aimage1,Aimage2,Aimage3,Aimage4,Aimage5,Aimage6;
    TextView Aname1,Aname2,Aname3,Aname4,Aname5,Aname6;
    TextView Aprice1,Aprice2,Aprice3,Aprice4,Aprice5,Aprice6;
    TextView Arewards1,Arewards2,Arewards3,Arewards4,Arewards5,Arewards6;



    //Computer
    LinearLayout Linearlayout15,Linearlayout16,Linearlayout17,Linearlayout18,Linearlayout19,Linearlayout20;
    ImageView Aimage7,Aimage8,Aimage9,Aimage10,Aimage11,Aimage12;
    TextView Aname7,Aname8,Aname9,Aname10,Aname11,Aname12;
    TextView Aprice7,Aprice8,Aprice9,Aprice10,Aprice11,Aprice12;
    TextView Arewards7,Arewards8,Arewards9,Arewards10,Arewards11,Arewards12;


    //Home Appliances
    LinearLayout Linearlayout21,Linearlayout22,Linearlayout23,Linearlayout24,Linearlayout25,Linearlayout26;
    ImageView Aimage13,Aimage14,Aimage15,Aimage16,Aimage17,Aimage18;
    TextView Aname13,Aname14,Aname15,Aname16,Aname17,Aname18;
    TextView Aprice13,Aprice14,Aprice15,Aprice16,Aprice17,Aprice18;
    TextView Arewards13,Arewards14,Arewards15,Arewards16,Arewards17,Arewards18;


    //Fashion
    LinearLayout Linearlayout27,Linearlayout28,Linearlayout29,Linearlayout30,Linearlayout31,Linearlayout32;
    ImageView Aimage19,Aimage20,Aimage21,Aimage22,Aimage23,Aimage24;
    TextView Aname19,Aname20,Aname21,Aname22,Aname23,Aname24;
    TextView Aprice19,Aprice20,Aprice21,Aprice22,Aprice23,Aprice24;
    TextView Arewards19,Arewards20,Arewards21,Arewards22,Arewards23,Arewards24;





        FirebaseAuth mAuth;
        FirebaseStorage storage = FirebaseStorage.getInstance();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        ImageView mImageView;
        Uri mImageUri;
        int len;
        String[] sampleimages=new String[100];
        String[] samplepath=new String[100];
        Bundle bundle=new Bundle();
        Fragment fragment=null;

        private AdView mAdView,mAdView1;
        private InterstitialAd mInterstitialAd;
        Button button;



        RecyclerView dmobile,daccessories,dcomputer,dhomeapplicanes,dfashion,dnewarrival,dmobiledetails;
          public HomeFragment() {
            // Required empty public constructor
        }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth= FirebaseAuth.getInstance();
//        MobileAds.initialize(getContext(), "ca-app-pub-5167989951191945~5928872897");




    }

         @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
             setHasOptionsMenu(true);




//             ForceUpdateChecker.with(getContext()).onUpdateNeeded(this).check();

                View v = inflater.inflate(R.layout.fragment_home, container, false);
             //Ad view





//             mAdView = v.findViewById(R.id.adView); mAdView1 = v.findViewById(R.id.adView1);
//             mInterstitialAd = new InterstitialAd(getContext());
//             mInterstitialAd.setAdUnitId("ca-app-pub-5167989951191945/4548751737");
//             AdRequest adRequest = new AdRequest.Builder().build();
//             AdRequest adRequest1 = new AdRequest.Builder().build();
//             mAdView.loadAd(adRequest);
//             mAdView1.loadAd(adRequest1);
//             mInterstitialAd.loadAd(new AdRequest.Builder().build());

             scroll=v.findViewById(R.id.scroll);
             mRecyclerView=v.findViewById(R.id.homerecycler);
             recyclerViewmobiledetails=v.findViewById(R.id.recyclerviewmobiledetails);
             mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
             recyclerViewmobiledetails.setLayoutManager(new LinearLayoutManager(getContext()));
             mFirebaseDatabase = FirebaseDatabase.getInstance();


//        mRecyclerView.setNestedScrollingEnabled(false);
             RecyclerView.SmoothScroller smoothScroller = new
                     LinearSmoothScroller(getContext()) {
                         @Override protected int getVerticalSnapPreference() {
                             return LinearSmoothScroller.SNAP_TO_START;
                         }
                     };


             session = new LoginSession(getContext());




             homecarousel=(CarouselView)v.findViewById(R.id.home_carousel);
             hMobiles=(ImageView)v.findViewById(R.id.hMobile);
             hAccesories=(ImageView)v.findViewById(R.id.hAccessories);
             hComputer=(ImageView)v.findViewById(R.id.hComputer);
             hHome=(ImageView)v.findViewById(R.id.hHomeAppliances);
             hGeneral=(ImageView)v.findViewById(R.id.hGeneral);
             hStationary=(ImageView)v.findViewById(R.id.hStationary);

             hFashion=(ImageView)v.findViewById(R.id.hFashion);
             hHealth=(ImageView)v.findViewById(R.id.hHealth);
             hServies=(ImageView)v.findViewById(R.id.hServices);
             hNewArrival=(ImageView)v.findViewById(R.id.hNewArrival);
             hDelivery=(ImageView)v.findViewById(R.id.hDelivery);
             //Mobiles scroll
             hmi=(ImageView)v.findViewById(R.id.hmi);
             hsamsung=(ImageView)v.findViewById(R.id.hsamsung);
             happle=(ImageView)v.findViewById(R.id.happle);
             hhonor=(ImageView)v.findViewById(R.id.hhonor);
             hoppo=(ImageView)v.findViewById(R.id.hoppo);
             hvivo=(ImageView)v.findViewById(R.id.hvivo);
             hkarbon=(ImageView)v.findViewById(R.id.hkarbon);
             hlava=(ImageView)v.findViewById(R.id.hlava);
             hitel=(ImageView)v.findViewById(R.id.hitel);
             hother=(ImageView)v.findViewById(R.id.hothers);
             hnokia=(ImageView)v.findViewById(R.id.hnokia);
             //4 Mobiles
             mobilename1=(TextView)v.findViewById(R.id.mobilename1);
             mobilename2=(TextView)v.findViewById(R.id.mobilename2);
             mobilename3=(TextView)v.findViewById(R.id.mobilename3);
             mobilename4=(TextView)v.findViewById(R.id.mobilename4);
             mobilepic1=(ImageView)v.findViewById(R.id.mobilepic1);
             mobilepic2=(ImageView)v.findViewById(R.id.mobilepic2);
             mobilepic3=(ImageView)v.findViewById(R.id.mobilepic3);
             mobilepic4=(ImageView)v.findViewById(R.id.mobilepic4);
             mobileprice1=(TextView)v.findViewById(R.id.mobileprice1);
             mobileprice2=(TextView)v.findViewById(R.id.mobileprice2);
             mobileprice3=(TextView)v.findViewById(R.id.mobileprice3);
             mobileprice4=(TextView)v.findViewById(R.id.mobileprice4);
             mobilerewards1=(TextView)v.findViewById(R.id.mobilerewards1);
             mobilerewards2=(TextView)v.findViewById(R.id.mobilerewards2);
             mobilerewards3=(TextView)v.findViewById(R.id.mobilerewards3);
             mobilerewards4=(TextView)v.findViewById(R.id.mobilerewards4);
             linearLayout1=(LinearLayout)v.findViewById(R.id.linearLayout1);
             linearLayout2=(LinearLayout)v.findViewById(R.id.linearLayout2);
             linearLayout3=(LinearLayout)v.findViewById(R.id.linearLayout3);
             linearLayout4=(LinearLayout)v.findViewById(R.id.linearLayout4);
             seemore=(LinearLayout)v.findViewById(R.id.seemore);
             seemore1=(LinearLayout)v.findViewById(R.id.seemore1);
             seemore2=(LinearLayout)v.findViewById(R.id.seemore2);
             seemore3=(LinearLayout)v.findViewById(R.id.seemore3);
             seemore4=(LinearLayout)v.findViewById(R.id.seemore4);
             seemore5=(LinearLayout)v.findViewById(R.id.seemore5);

             //4NewArrival
             Linearlayout5=(LinearLayout)v.findViewById(R.id.Linearlayout5);
             Linearlayout6=(LinearLayout)v.findViewById(R.id.Linearlayout6);
             Linearlayout7=(LinearLayout)v.findViewById(R.id.Linearlayout7);
             Linearlayout8=(LinearLayout)v.findViewById(R.id.Linearlayout8);
             Newimage1=(ImageView)v.findViewById(R.id.Newimage1);
             Newimage2=(ImageView)v.findViewById(R.id.Newimage2);
             Newimage3=(ImageView)v.findViewById(R.id.Newimage3);
             Newimage4=(ImageView)v.findViewById(R.id.Newimage4);
             Newname1=(TextView)v.findViewById(R.id.Newname1);
             Newname2=(TextView) v.findViewById(R.id.Newname2);
             Newname3=(TextView)v.findViewById(R.id.Newname3);
             Newname4=(TextView)v.findViewById(R.id.Newname4);
             Newprice1=(TextView) v.findViewById(R.id.Newprice1);
             Newprice2=(TextView)v.findViewById(R.id.Newprice2);
             Newprice3=(TextView)v.findViewById(R.id.Newprice3);
             Newprice4=(TextView)v.findViewById(R.id.Newprice4);
             Newrewards1=(TextView)v.findViewById(R.id.Newrewards1);
             Newrewards2=(TextView)v.findViewById(R.id.Newrewards2);
             Newrewards3=(TextView) v.findViewById(R.id.Newrewards3);
             Newrewards4=(TextView) v.findViewById(R.id.Newrewards4);
             //Baner
             baner1=(ImageView)v.findViewById(R.id.baner1);
             baner2=(ImageView)v.findViewById(R.id.baner2);


             //Button
             border=(Button)v.findViewById(R.id.Border);
             bcheckout=(Button)v.findViewById(R.id.Bcheckout);
             bno=(Button)v.findViewById(R.id.Bno);
             byes=(Button)v.findViewById(R.id.Byes);

             //Orders
             orderpic1=(ImageView)v.findViewById(R.id.orderpic1);
             orderpic2=(ImageView)v.findViewById(R.id.orderpic2);
             ordername1=(TextView)v.findViewById(R.id.ordername1);
             ordername2=(TextView)v.findViewById(R.id.ordername2);
             orderprice1=(TextView)v.findViewById(R.id.orderprice1);
             orderprice2=(TextView)v.findViewById(R.id.orderprice2);
             orderrewards1=(TextView)v.findViewById(R.id.orderrewads1);
             orderrewards2=(TextView)v.findViewById(R.id.orderrewads2);
             Horizontalorder=(HorizontalScrollView)v.findViewById(R.id.horizontalScrollView2);
             linearLayout7=(LinearLayout)v.findViewById(R.id.linearLayout7);
             Linearoder2=(LinearLayout)v.findViewById(R.id.Linearorder2);



             //Cart
             homecartimage1=(ImageView)v.findViewById(R.id.homecartimage1);
             homecartimage2=(ImageView)v.findViewById(R.id.homecartimage2);
             homecartimage3=(ImageView)v.findViewById(R.id.homecartimage3);
             homecartname1=(TextView)v.findViewById(R.id.homecartname1);
             homecartname2=(TextView)v.findViewById(R.id.homecartname2);
             homecartname3=(TextView)v.findViewById(R.id.homecartname3);
             homecartprice1=(TextView)v.findViewById(R.id.homecartprice1);
             homecartprice2=(TextView)v.findViewById(R.id.homecartprice2);
             homecartprice3=(TextView)v.findViewById(R.id.homecartprice3);
             homerewards1=(TextView)v.findViewById(R.id.homecartrewards1);
             homerewards2=(TextView)v.findViewById(R.id.homecartrewards2);
             homerewards3=(TextView)v.findViewById(R.id.homecartrewards3);
             Linearcart=(LinearLayout)v.findViewById(R.id.Linearcart);
             cart1=(LinearLayout)v.findViewById(R.id.cart1);
             cart2=(LinearLayout)v.findViewById(R.id.cart2);
             cart3=(LinearLayout)v.findViewById(R.id.cart3);


             //Accessories
             Linearlayout9=v.findViewById(R.id.Linearlayout9);
             Linearlayout10=v.findViewById(R.id.Linearlayout10);
             Linearlayout11=v.findViewById(R.id.Linearlayout11);
             Linearlayout12=v.findViewById(R.id.Linearlayout12);
             Linearlayout13=v.findViewById(R.id.Linearlayout13);
             Linearlayout14=v.findViewById(R.id.Linearlayout14);
             Aimage1=v.findViewById(R.id.Aimage1);
             Aimage2=v.findViewById(R.id.Aimage2);
             Aimage3=v.findViewById(R.id.Aimage3);
             Aimage4=v.findViewById(R.id.Aimage4);
             Aimage5=v.findViewById(R.id.Aimage5);
             Aimage6=v.findViewById(R.id.Aimage6);
             Aname1=v.findViewById(R.id.Aname1);
             Aname2=v.findViewById(R.id.Aname2);
             Aname3=v.findViewById(R.id.Aname3);
             Aname4=v.findViewById(R.id.Aname4);
             Aname5=v.findViewById(R.id.Aname5);
             Aname6=v.findViewById(R.id.Aname6);
             Aprice1=v.findViewById(R.id.Aprice1);
             Aprice2=v.findViewById(R.id.Aprice2);
             Aprice3=v.findViewById(R.id.Aprice3);
             Aprice4=v.findViewById(R.id.Aprice4);
             Aprice5=v.findViewById(R.id.Aprice5);
             Aprice6=v.findViewById(R.id.Aprice6);
             Arewards1=v.findViewById(R.id.Arewards1);
             Arewards2=v.findViewById(R.id.Arewards2);
             Arewards3=v.findViewById(R.id.Arewards3);
             Arewards4=v.findViewById(R.id.Arewards4);
             Arewards5=v.findViewById(R.id.Arewards5);
             Arewards6=v.findViewById(R.id.Arewards6);
             
             
             
             //Computer
             Linearlayout15=v.findViewById(R.id.Linearlayout15);
             Linearlayout16=v.findViewById(R.id.Linearlayout16);
             Linearlayout17=v.findViewById(R.id.Linearlayout17);
             Linearlayout18=v.findViewById(R.id.Linearlayout18);
             Linearlayout19=v.findViewById(R.id.Linearlayout19);
             Linearlayout20=v.findViewById(R.id.Linearlayout20);
             Aimage7=v.findViewById(R.id.Aimage7);
             Aimage8=v.findViewById(R.id.Aimage8);
             Aimage9=v.findViewById(R.id.Aimage9);
             Aimage10=v.findViewById(R.id.Aimage10);
             Aimage11=v.findViewById(R.id.Aimage11);
             Aimage12=v.findViewById(R.id.Aimage12);
             Aname7=v.findViewById(R.id.Aname7);
             Aname8=v.findViewById(R.id.Aname8);
             Aname9=v.findViewById(R.id.Aname9);
             Aname10=v.findViewById(R.id.Aname10);
             Aname11=v.findViewById(R.id.Aname11);
             Aname12=v.findViewById(R.id.Aname12);
             Aprice7=v.findViewById(R.id.Aprice7);
             Aprice8=v.findViewById(R.id.Aprice8);
             Aprice9=v.findViewById(R.id.Aprice9);
             Aprice10=v.findViewById(R.id.Aprice10);
             Aprice11=v.findViewById(R.id.Aprice11);
             Aprice12=v.findViewById(R.id.Aprice12);
             Arewards7=v.findViewById(R.id.Arewards7);
             Arewards8=v.findViewById(R.id.Arewards8);
             Arewards9=v.findViewById(R.id.Arewards9);
             Arewards10=v.findViewById(R.id.Arewards10);
             Arewards11=v.findViewById(R.id.Arewards11);
             Arewards12=v.findViewById(R.id.Arewards12);


             //HomeAppliances
             Linearlayout21=v.findViewById(R.id.Linearlayout21);
             Linearlayout22=v.findViewById(R.id.Linearlayout22);
             Linearlayout23=v.findViewById(R.id.Linearlayout23);
             Linearlayout24=v.findViewById(R.id.Linearlayout24);
             Linearlayout25=v.findViewById(R.id.Linearlayout25);
             Linearlayout26=v.findViewById(R.id.Linearlayout26);
             Aimage13=v.findViewById(R.id.Aimage13);
             Aimage14=v.findViewById(R.id.Aimage14);
             Aimage15=v.findViewById(R.id.Aimage15);
             Aimage16=v.findViewById(R.id.Aimage16);
             Aimage17=v.findViewById(R.id.Aimage17);
             Aimage18=v.findViewById(R.id.Aimage18);
             Aname13=v.findViewById(R.id.Aname13);
             Aname14=v.findViewById(R.id.Aname14);
             Aname15=v.findViewById(R.id.Aname15);
             Aname16=v.findViewById(R.id.Aname16);
             Aname17=v.findViewById(R.id.Aname17);
             Aname18=v.findViewById(R.id.Aname18);
             Aprice13=v.findViewById(R.id.Aprice13);
             Aprice14=v.findViewById(R.id.Aprice14);
             Aprice15=v.findViewById(R.id.Aprice15);
             Aprice16=v.findViewById(R.id.Aprice16);
             Aprice17=v.findViewById(R.id.Aprice17);
             Aprice18=v.findViewById(R.id.Aprice18);
             Arewards13=v.findViewById(R.id.Arewards13);
             Arewards14=v.findViewById(R.id.Arewards14);
             Arewards15=v.findViewById(R.id.Arewards15);
             Arewards16=v.findViewById(R.id.Arewards16);
             Arewards17=v.findViewById(R.id.Arewards17);
             Arewards18=v.findViewById(R.id.Arewards18);



             //Fashion
             Linearlayout27=v.findViewById(R.id.Linearlayout27);
             Linearlayout28=v.findViewById(R.id.Linearlayout28);
             Linearlayout29=v.findViewById(R.id.Linearlayout29);
             Linearlayout30=v.findViewById(R.id.Linearlayout30);
             Linearlayout31=v.findViewById(R.id.Linearlayout31);
             Linearlayout32=v.findViewById(R.id.Linearlayout32);
             Aimage19=v.findViewById(R.id.Aimage19);
             Aimage20=v.findViewById(R.id.Aimage20);
             Aimage21=v.findViewById(R.id.Aimage21);
             Aimage22=v.findViewById(R.id.Aimage22);
             Aimage23=v.findViewById(R.id.Aimage23);
             Aimage24=v.findViewById(R.id.Aimage24);
             Aname19=v.findViewById(R.id.Aname19);
             Aname20=v.findViewById(R.id.Aname20);
             Aname21=v.findViewById(R.id.Aname21);
             Aname22=v.findViewById(R.id.Aname22);
             Aname23=v.findViewById(R.id.Aname23);
             Aname24=v.findViewById(R.id.Aname24);
             Aprice19=v.findViewById(R.id.Aprice19);
             Aprice20=v.findViewById(R.id.Aprice20);
             Aprice21=v.findViewById(R.id.Aprice21);
             Aprice22=v.findViewById(R.id.Aprice22);
             Aprice23=v.findViewById(R.id.Aprice23);
             Aprice24=v.findViewById(R.id.Aprice24);
             Arewards19=v.findViewById(R.id.Arewards19);
             Arewards20=v.findViewById(R.id.Arewards20);
             Arewards21=v.findViewById(R.id.Arewards21);
             Arewards22=v.findViewById(R.id.Arewards22);
             Arewards23=v.findViewById(R.id.Arewards23);
             Arewards24=v.findViewById(R.id.Arewards24);


                 final Button ok, cancel;
                 final TextView heading, content;
                 final ImageView dialogimage;
                 ViewGroup viewGroup = v.findViewById(android.R.id.content);

                 //then we will inflate the custom alert dialog xml that we created
                 View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.my_dialog, viewGroup, false);
                 ok = dialogView.findViewById(R.id.buttonOk);
                 cancel = dialogView.findViewById(R.id.buttonCancel);
                 heading = dialogView.findViewById(R.id.heading);
                 content = dialogView.findViewById(R.id.content);
                 dialogimage = dialogView.findViewById(R.id.dialogimage);

                 //Now we need an AlertDialog.Builder object
                 android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getContext());

                 //setting the view of the builder to our custom view that we already inflated
                 builder.setView(dialogView);

                 //finally creating the alert dialog and displaying it
                 final android.app.AlertDialog alertDialog = builder.create();


                 PackageManager manager = getActivity().getPackageManager();
                 PackageInfo info = null;
                 try {
                     info = manager.getPackageInfo(getActivity().getPackageName(), PackageManager.GET_ACTIVITIES);
                 } catch (PackageManager.NameNotFoundException e) {
                     e.printStackTrace();
                 }

                 final double version = Double.parseDouble("" + info.versionCode);


                 ok.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View view) {
                         final String appPackageName = getActivity().getPackageName(); // getPackageName() from Context or Activity object
                         try {
                             startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                         } catch (android.content.ActivityNotFoundException anfe) {

                         }

                     }
                 });


                 cancel.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View view) {
                         alertDialog.dismiss();
                     }
                 });


                 FirebaseDatabase.getInstance().getReference().child("AppContent").child("Application").addListenerForSingleValueEvent(new ValueEventListener() {
                     @Override
                     public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                         double vno = Double.parseDouble(dataSnapshot.child("Version").getValue().toString());
                         String imp = dataSnapshot.child("IMP").getValue().toString();
                         if (version != vno) {
                             if (imp.equals("No")) {
                                 dialogimage.setImageDrawable(getResources().getDrawable(R.drawable.ic_warning));
                                 heading.setText("UPDATE REQUIRED");
                                 content.setText("Update the app for a faster & better experience");
                                 alertDialog.show();
                                 return;
                             } else {
                                 dialogimage.setImageDrawable(getResources().getDrawable(R.drawable.ic_warning));
                                 heading.setText("UPDATE REQUIRED");
                                 content.setText("Update the app for a faster & better experience");
                                 cancel.setVisibility(View.GONE);
                                 alertDialog.show();
                                 alertDialog.setCancelable(false);
                                 return;
                             }
                         }
                     }

                     @Override
                     public void onCancelled(@NonNull DatabaseError databaseError) {

                     }
                 });


             
             
             hMobiles.setOnClickListener(new View.OnClickListener() {
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

             hAccesories.setOnClickListener(new View.OnClickListener() {
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

             hComputer.setOnClickListener(new View.OnClickListener() {
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

             hHome.setOnClickListener(new View.OnClickListener() {
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

             hGeneral.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     Toast.makeText(getContext(),"Coming Soon",Toast.LENGTH_SHORT).show();
                     return;
//                     fragment= new GeneralFragment();
//                     session.setsub("General SUNRISE");
//                     FragmentManager fragmentManager = getFragmentManager();
//                     fragmentManager.beginTransaction()
//                             .addToBackStack(null)
//                             .replace(R.id.frame_container, fragment).commit();
                 }
             });

             hStationary.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     Toast.makeText(getContext(),"Coming Soon",Toast.LENGTH_SHORT).show();
                     return;
//                     fragment= new StationaryFragment();
//                     FragmentManager fragmentManager = getFragmentManager();
//                     fragmentManager.beginTransaction()
//                             .addToBackStack(null)
//                             .replace(R.id.frame_container, fragment).commit();
                 }
             });

             hDelivery.setOnClickListener(new View.OnClickListener() {
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

             hNewArrival.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     fragment= new NewArrivalFragment();
                     session.setsub("NewArrival GIFTS");
                     FragmentManager fragmentManager = getFragmentManager();
                     fragmentManager.beginTransaction()
                             .addToBackStack(null)
                             .replace(R.id.frame_container, fragment).commit();
                 }
             });

             hFashion.setOnClickListener(new View.OnClickListener() {
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

             hHealth.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
//                     fragment= new HealthCareFragment();
//                     FragmentManager fragmentManager = getFragmentManager();
//                     fragmentManager.beginTransaction()
//                             .addToBackStack(null)
//                             .replace(R.id.frame_container, fragment).commit();

                     Toast.makeText(getContext(),"Coming Soon",Toast.LENGTH_SHORT).show();
                 }
             });

             hServies.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     fragment= new ServicesFragment();
                     FragmentManager fragmentManager = getFragmentManager();
                     fragmentManager.beginTransaction()
                             .addToBackStack(null)
                             .replace(R.id.frame_container, fragment).commit();
                 }
             });



             final DatabaseReference database = FirebaseDatabase.getInstance().getReference();
             DatabaseReference ref = database.child("AppContent").child("HomePage").child("Carousel");
             ref.addListenerForSingleValueEvent(new ValueEventListener() {
                 @Override
                 public void onDataChange(DataSnapshot dataSnapshot) {
                     int i=0;
                     for(DataSnapshot v:dataSnapshot.getChildren()){
                         sampleimages[i]=v.child("Name").getValue(String.class);
                         samplepath[i]=v.child("Path").getValue(String.class);
                         i++;
                     }
                     homecarousel.setImageListener(imageListener);
                     homecarousel.setPageCount(i);
                 }

                 @Override
                 public void onCancelled(DatabaseError databaseError) {

                 }
             });

             homecarousel.setImageClickListener(new ImageClickListener() {
                 @Override
                 public void onClick(int position) {
                     Bundle bundle=new Bundle();
                     ComputerDetails fragment= new ComputerDetails();

                     bundle.putString("name",samplepath[position]);
                     fragment.setArguments(bundle);
                     FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                     fragmentManager.beginTransaction()
                             .addToBackStack(null)
                             .replace(R.id.frame_container, fragment).commit();
                 }
             });


                hmi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        fragment=new MobileFragment();
                        session.setsub("MI");
                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                        fragmentManager.beginTransaction()
                                .addToBackStack(null)
                                .replace(R.id.frame_container,fragment).commit();
                    }
                });

             happle.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     fragment=new MobileFragment();
                     session.setsub("APPLE");
                     FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                     fragmentManager.beginTransaction()
                             .addToBackStack(null)
                             .replace(R.id.frame_container,fragment).commit();
                 }
             });


             hsamsung.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     fragment=new MobileFragment();
                     session.setsub("SAMSUNG");
                     FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                     fragmentManager.beginTransaction()
                             .addToBackStack(null)
                             .replace(R.id.frame_container,fragment).commit();
                 }
             });

             hoppo.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     fragment=new MobileFragment();
                     session.setsub("OPPO");
                     FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                     fragmentManager.beginTransaction()
                             .addToBackStack(null)
                             .replace(R.id.frame_container,fragment).commit();
                 }
             });

             hnokia.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     fragment=new MobileFragment();
                     session.setsub("NOKIA");
                     FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                     fragmentManager.beginTransaction()
                             .addToBackStack(null)
                             .replace(R.id.frame_container,fragment).commit();
                 }
             });

             hhonor.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     fragment=new MobileFragment();
                     session.setsub("HONOR");
                     FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                     fragmentManager.beginTransaction()
                             .addToBackStack(null)
                             .replace(R.id.frame_container,fragment).commit();
                 }
             });

             hvivo.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     fragment=new MobileFragment();
                     session.setsub("VIVO");
                     FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                     fragmentManager.beginTransaction()
                             .addToBackStack(null)
                             .replace(R.id.frame_container,fragment).commit();
                 }
             });
             hkarbon.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     fragment=new MobileFragment();
                     session.setsub("KARBON");
                     FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                     fragmentManager.beginTransaction()
                             .addToBackStack(null)
                             .replace(R.id.frame_container,fragment).commit();
                 }
             });
             hlava.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     fragment=new MobileFragment();
                     session.setsub("LAVA");
                     FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                     fragmentManager.beginTransaction()
                             .addToBackStack(null)
                             .replace(R.id.frame_container,fragment).commit();
                 }
             });
             hitel.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     fragment=new MobileFragment();
                     session.setsub("ITEL");
                     FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                     fragmentManager.beginTransaction()
                             .addToBackStack(null)
                             .replace(R.id.frame_container,fragment).commit();
                 }
             });
             hother.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     fragment=new MobileFragment();
                     session.setsub("OTHERS");
                     FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                     fragmentManager.beginTransaction()
                             .addToBackStack(null)
                             .replace(R.id.frame_container,fragment).commit();
                 }
             });


              final DatabaseReference ref1 = database.child("AppContent").child("HomePage").child("Mobiles");
                 ref1.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                      
                                DataSnapshot ref8=dataSnapshot.child("Mobile1");
                                mobilename1.setText(ref8.child("Name").getValue().toString());
                                mobileprice1.setText(str+ref8.child("Price").getValue().toString());
                                mobilerewards1.setText(" "+ref8.child("Rewards").getValue().toString()+" Reward Points");
                                Glide.with(getContext())
                                        .load(ref8.child("Image").getValue().toString())
                                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                                       .into(mobilepic1);

                        DataSnapshot ref9=dataSnapshot.child("Mobile2");
                        mobilename2.setText(ref9.child("Name").getValue().toString());
                        mobileprice2.setText(str+ref9.child("Price").getValue().toString());
                        mobilerewards2.setText(" "+ref9.child("Rewards").getValue().toString()+" Reward Points");
                        Glide.with(getContext())
                                .load(ref9.child("Image").getValue().toString())
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .into(mobilepic2);



                        DataSnapshot ref10=dataSnapshot.child("Mobile3");
                        mobilename3.setText(ref10.child("Name").getValue().toString());
                        mobileprice3.setText(str+ref10.child("Price").getValue().toString());
                        mobilerewards3.setText(" "+ref10.child("Rewards").getValue().toString()+" Reward Points");
                        Glide.with(getContext())
                                .load(ref10.child("Image").getValue().toString())
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .into(mobilepic3);



                        DataSnapshot ref11=dataSnapshot.child("Mobile4");
                        mobilename4.setText(ref11.child("Name").getValue().toString());
                        mobileprice4.setText(str+ref11.child("Price").getValue().toString());
                        mobilerewards4.setText(" "+ref11.child("Rewards").getValue().toString()+" Reward Points");
                        Glide.with(getContext())
                                .load(ref11.child("Image").getValue().toString())
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .into(mobilepic4);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


               final DatabaseReference oref=database.child("Orders");
               oref.orderByChild("Userid").equalTo("+91"+session.getusename()).limitToLast(2).addListenerForSingleValueEvent(new ValueEventListener() {
                   @Override
                   public void onDataChange(DataSnapshot dataSnapshot) {
                       int a=(int)dataSnapshot.getChildrenCount();
                       if(a>0)
                       {
                           int i=0;
                           for(DataSnapshot v:dataSnapshot.getChildren())
                           {
                               i++;
                               if(i==1){
                                   orderrewards1.setText(" "+v.child("Rewards").getValue().toString()+" Reward Points");
                                   ordername1.setText(v.child("ProductName").getValue().toString());
                                   orderprice1.setText(v.child("Mrp").getValue().toString());
                                   Glide.with(getContext()).load(v.child("Image1").getValue().toString()).diskCacheStrategy(DiskCacheStrategy.ALL).into(orderpic1);
                               }
                               else if(i==2){
                                   orderrewards2.setText(" "+v.child("Rewards").getValue().toString()+" Reward Points");
                                   ordername2.setText(v.child("ProductName").getValue().toString());
                                   orderprice2.setText(v.child("Mrp").getValue().toString());
                                   Glide.with(getContext()).load(v.child("Image1").getValue().toString())
                                           .diskCacheStrategy(DiskCacheStrategy.ALL)
                                           .into(orderpic2);
                               }
                           }
                           if(i==1)
                           {
                               Linearoder2.setVisibility(View.GONE);
                           }
                       }
                       else{
                           Horizontalorder.setVisibility(View.GONE);
                           linearLayout7.setVisibility(View.GONE);
                       }
                   }

                   @Override
                   public void onCancelled(DatabaseError databaseError) {

                   }
               });


//             final DatabaseReference cref=database.child("Users").child("+91"+session.getusename()).child("Cart");
//             cref.limitToLast(3).addListenerForSingleValueEvent(new ValueEventListener() {
//                 @Override
//                 public void onDataChange(DataSnapshot dataSnapshot) {
//                     int a=(int)dataSnapshot.getChildrenCount();
//                     if(a>0)
//                     {
//                         int i=0;
//                         for(DataSnapshot v:dataSnapshot.getChildren())
//                         {
//                             i++;
//                             if(i==1){
//                                 homerewards1.setText(" "+v.child("Rewards").getValue().toString()+" Reward Points");
//                                 homecartname1.setText(v.child("ProductName").getValue().toString());
//                                 homecartprice1.setText(v.child("Mrp").getValue().toString());
//                                 Glide.with(getContext()).load(v.child("Image1").getValue().toString())
//                                         .diskCacheStrategy(DiskCacheStrategy.ALL)
//                                         .placeholder(R.drawable.placeholder)
//                                         .into(homecartimage1);
//                             }
//                             else if(i==2){
//                                 homerewards2.setText(" "+v.child("Rewards").getValue().toString()+" Reward Points");
//                                 homecartname2.setText(v.child("ProductName").getValue().toString());
//                                 homecartprice2.setText(v.child("Mrp").getValue().toString());
//                                 Glide.with(getContext()).load(v.child("Image1").getValue().toString())
//                                         .placeholder(R.drawable.placeholder)
//                                         .diskCacheStrategy(DiskCacheStrategy.ALL)
//                                         .into(homecartimage2);
//                             }
//                             else if(i==3){
//                                 homerewards3.setText(" "+v.child("Rewards").getValue().toString()+" Reward Points");
//                                 homecartname3.setText(v.child("ProductName").getValue().toString());
//                                 homecartprice3.setText(v.child("Mrp").getValue().toString());
//                                 Glide.with(getContext()).load(v.child("Image1").getValue().toString())
//                                         .placeholder(R.drawable.placeholder)
//                                         .diskCacheStrategy(DiskCacheStrategy.ALL)
//                                         .into(homecartimage3);
//                             }
//                         }
//                         if(i==1)
//                         {
//                             cart2.setVisibility(View.GONE);
//                             cart3.setVisibility(View.GONE);
//                         }
//                         else if(i==2)
//                         {
//                             cart3.setVisibility(View.GONE);
//                         }
//                     }
//                     else{
//                        Linearcart.setVisibility(View.GONE);
//                     }
//                 }
//
//                 @Override
//                 public void onCancelled(DatabaseError databaseError) {
//
//                 }
//             });




             final DatabaseReference ref2 = database.child("AppContent").child("HomePage").child("NewArrivals");
             ref2.addListenerForSingleValueEvent(new ValueEventListener() {
                 @Override
                 public void onDataChange(DataSnapshot dataSnapshot) {
                            
                             DataSnapshot ref5=dataSnapshot.child("NewArrival1");
                             Newname1.setText(ref5.child("Name").getValue().toString());
                             Newprice1.setText(str+ref5.child("Price").getValue().toString());
                             Newrewards1.setText(" "+ref5.child("Rewards").getValue().toString()+" Reward Points");
                             Glide.with(getContext())
                                     .load(ref5.child("Image").getValue().toString())
                                     .diskCacheStrategy(DiskCacheStrategy.ALL)
                                     .into(Newimage1);



                     DataSnapshot ref6=dataSnapshot.child("NewArrival2");
                     Newname2.setText(ref6.child("Name").getValue().toString());
                     Newprice2.setText(str+ref6.child("Price").getValue().toString());
                     Newrewards2.setText(" "+ref6.child("Rewards").getValue().toString()+" Reward Points");
                     Glide.with(getContext())
                             .load(ref6.child("Image").getValue().toString())
                             .diskCacheStrategy(DiskCacheStrategy.ALL)
                             .into(Newimage2);

                     DataSnapshot ref7=dataSnapshot.child("NewArrival3");
                     Newname3.setText(ref7.child("Name").getValue().toString());
                     Newprice3.setText(str+ref7.child("Price").getValue().toString());
                     Newrewards3.setText(" "+ref7.child("Rewards").getValue().toString()+" Reward Points");
                     Glide.with(getContext())
                             .load(ref7.child("Image").getValue().toString())
                             .diskCacheStrategy(DiskCacheStrategy.ALL)
                             .into(Newimage3);


                     DataSnapshot ref8=dataSnapshot.child("NewArrival4");
                     Newname4.setText(ref8.child("Name").getValue().toString());
                     Newprice4.setText(str+ref8.child("Price").getValue().toString());
                     Newrewards4.setText(" "+ref8.child("Rewards").getValue().toString()+" Reward Points");
                     Glide.with(getContext())
                             .load(ref8.child("Image").getValue().toString())
                             .diskCacheStrategy(DiskCacheStrategy.ALL)
                             .into(Newimage4);
                        
                 }

                 @Override
                 public void onCancelled(DatabaseError databaseError) {

                 }
             });
             
             
             
             
             //Accessories
             final DatabaseReference ref20 = database.child("AppContent").child("HomePage").child("Accessories");
             ref20.addListenerForSingleValueEvent(new ValueEventListener() {
                 @Override
                 public void onDataChange(DataSnapshot dataSnapshot) {
                     
                     DataSnapshot ref1=dataSnapshot.child("Accessories1");
                     Aname1.setText(ref1.child("Name").getValue().toString());
                     Aprice1.setText(str+ref1.child("Price").getValue().toString());
                     Arewards1.setText(" "+ref1.child("Rewards").getValue().toString()+" Reward Points");
                     Glide.with(getContext())
                             .load(ref1.child("Image").getValue().toString())
                             .diskCacheStrategy(DiskCacheStrategy.ALL)
                             .into(Aimage1);


                     DataSnapshot ref2=dataSnapshot.child("Accessories2");
                     Aname2.setText(ref2.child("Name").getValue().toString());
                     Aprice2.setText(str+ref2.child("Price").getValue().toString());
                     Arewards2.setText(" "+ref2.child("Rewards").getValue().toString()+" Reward Points");
                     Glide.with(getContext())
                             .load(ref2.child("Image").getValue().toString())
                             .diskCacheStrategy(DiskCacheStrategy.ALL)
                             .into(Aimage2);

                     DataSnapshot ref3=dataSnapshot.child("Accessories3");
                     Aname3.setText(ref3.child("Name").getValue().toString());
                     Aprice3.setText(str+ref3.child("Price").getValue().toString());
                     Arewards3.setText(" "+ref3.child("Rewards").getValue().toString()+" Reward Points");
                     Glide.with(getContext())
                             .load(ref3.child("Image").getValue().toString())
                             .diskCacheStrategy(DiskCacheStrategy.ALL)
                             .into(Aimage3);

                     DataSnapshot ref4=dataSnapshot.child("Accessories4");
                     Aname4.setText(ref4.child("Name").getValue().toString());
                     Aprice4.setText(str+ref4.child("Price").getValue().toString());
                     Arewards4.setText(" "+ref4.child("Rewards").getValue().toString()+" Reward Points");
                     Glide.with(getContext())
                             .load(ref4.child("Image").getValue().toString())
                             .diskCacheStrategy(DiskCacheStrategy.ALL)
                             .into(Aimage4);


                     DataSnapshot ref5=dataSnapshot.child("Accessories5");
                     Aname5.setText(ref5.child("Name").getValue().toString());
                     Aprice5.setText(str+ref5.child("Price").getValue().toString());
                     Arewards5.setText(" "+ref5.child("Rewards").getValue().toString()+" Reward Points");
                     Glide.with(getContext())
                             .load(ref5.child("Image").getValue().toString())
                             .diskCacheStrategy(DiskCacheStrategy.ALL)
                             .into(Aimage5);


                     DataSnapshot ref6=dataSnapshot.child("Accessories6");
                     Aname6.setText(ref6.child("Name").getValue().toString());
                     Aprice6.setText(str+ref6.child("Price").getValue().toString());
                     Arewards6.setText(" "+ref6.child("Rewards").getValue().toString()+" Reward Points");
                     Glide.with(getContext())
                             .load(ref6.child("Image").getValue().toString())
                             .diskCacheStrategy(DiskCacheStrategy.ALL)
                             .into(Aimage6);
                     
                 }

                 @Override
                 public void onCancelled(DatabaseError databaseError) {

                     
                 }
             });




             //Computer
             final DatabaseReference ref21 = database.child("AppContent").child("HomePage").child("Computer");
             ref21.addListenerForSingleValueEvent(new ValueEventListener() {
                 @Override
                 public void onDataChange(DataSnapshot dataSnapshot) {

                     DataSnapshot ref7=dataSnapshot.child("Computer1");
                     Aname7.setText(ref7.child("Name").getValue().toString());
                     Aprice7.setText(str+ref7.child("Price").getValue().toString());
                     Arewards7.setText(" "+ref7.child("Rewards").getValue().toString()+" Reward Points");
                     Glide.with(getContext())
                             .load(ref7.child("Image").getValue().toString())
                             .diskCacheStrategy(DiskCacheStrategy.ALL)
                             .into(Aimage7);


                     DataSnapshot ref8=dataSnapshot.child("Computer2");
                     Aname8.setText(ref8.child("Name").getValue().toString());
                     Aprice8.setText(str+ref8.child("Price").getValue().toString());
                     Arewards8.setText(" "+ref8.child("Rewards").getValue().toString()+" Reward Points");
                     Glide.with(getContext())
                             .load(ref8.child("Image").getValue().toString())
                             .diskCacheStrategy(DiskCacheStrategy.ALL)
                             .into(Aimage8);

                     DataSnapshot ref9=dataSnapshot.child("Computer3");
                     Aname9.setText(ref9.child("Name").getValue().toString());
                     Aprice9.setText(str+ref9.child("Price").getValue().toString());
                     Arewards9.setText(" "+ref9.child("Rewards").getValue().toString()+" Reward Points");
                     Glide.with(getContext())
                             .load(ref9.child("Image").getValue().toString())
                             .diskCacheStrategy(DiskCacheStrategy.ALL)
                             .into(Aimage9);

                     DataSnapshot ref10=dataSnapshot.child("Computer4");
                     Aname10.setText(ref10.child("Name").getValue().toString());
                     Aprice10.setText(str+ref10.child("Price").getValue().toString());
                     Arewards10.setText(" "+ref10.child("Rewards").getValue().toString()+" Reward Points");
                     Glide.with(getContext())
                             .load(ref10.child("Image").getValue().toString())
                             .diskCacheStrategy(DiskCacheStrategy.ALL)
                             .into(Aimage10);


                     DataSnapshot ref11=dataSnapshot.child("Computer5");
                     Aname11.setText(ref11.child("Name").getValue().toString());
                     Aprice11.setText(str+ref11.child("Price").getValue().toString());
                     Arewards11.setText(" "+ref11.child("Rewards").getValue().toString()+" Reward Points");
                     Glide.with(getContext())
                             .load(ref11.child("Image").getValue().toString())
                             .diskCacheStrategy(DiskCacheStrategy.ALL)
                             .into(Aimage11);


                     DataSnapshot ref12=dataSnapshot.child("Computer6");
                     Aname12.setText(ref12.child("Name").getValue().toString());
                     Aprice12.setText(str+ref12.child("Price").getValue().toString());
                     Arewards12.setText(" "+ref12.child("Rewards").getValue().toString()+" Reward Points");
                     Glide.with(getContext())
                             .load(ref12.child("Image").getValue().toString())
                             .diskCacheStrategy(DiskCacheStrategy.ALL)
                             .into(Aimage12);

                 }

                 @Override
                 public void onCancelled(DatabaseError databaseError) {


                 }
             });



             //HomeAppliances
             final DatabaseReference ref22 = database.child("AppContent").child("HomePage").child("HomeAppliances");
             ref22.addListenerForSingleValueEvent(new ValueEventListener() {
                 @Override
                 public void onDataChange(DataSnapshot dataSnapshot) {

                     DataSnapshot ref13=dataSnapshot.child("HomeAppliances1");
                     Aname13.setText(ref13.child("Name").getValue().toString());
                     Aprice13.setText(str+ref13.child("Price").getValue().toString());
                     Arewards13.setText(" "+ref13.child("Rewards").getValue().toString()+" Reward Points");
                     Glide.with(getContext())
                             .load(ref13.child("Image").getValue().toString())
                             .diskCacheStrategy(DiskCacheStrategy.ALL)
                             .into(Aimage13);


                     DataSnapshot ref14=dataSnapshot.child("HomeAppliances2");
                     Aname14.setText(ref14.child("Name").getValue().toString());
                     Aprice14.setText(str+ref14.child("Price").getValue().toString());
                     Arewards14.setText(" "+ref14.child("Rewards").getValue().toString()+" Reward Points");
                     Glide.with(getContext())
                             .load(ref14.child("Image").getValue().toString())
                             .diskCacheStrategy(DiskCacheStrategy.ALL)
                             .into(Aimage14);

                     DataSnapshot ref15=dataSnapshot.child("HomeAppliances3");
                     Aname15.setText(ref15.child("Name").getValue().toString());
                     Aprice15.setText(str+ref15.child("Price").getValue().toString());
                     Arewards15.setText(" "+ref15.child("Rewards").getValue().toString()+" Reward Points");
                     Glide.with(getContext())
                             .load(ref15.child("Image").getValue().toString())
                             .diskCacheStrategy(DiskCacheStrategy.ALL)
                             .into(Aimage15);

                     DataSnapshot ref16=dataSnapshot.child("HomeAppliances4");
                     Aname16.setText(ref16.child("Name").getValue().toString());
                     Aprice16.setText(str+ref16.child("Price").getValue().toString());
                     Arewards16.setText(" "+ref16.child("Rewards").getValue().toString()+" Reward Points");
                     Glide.with(getContext())
                             .load(ref16.child("Image").getValue().toString())
                             .diskCacheStrategy(DiskCacheStrategy.ALL)
                             .into(Aimage16);


                     DataSnapshot ref17=dataSnapshot.child("HomeAppliances5");
                     Aname17.setText(ref17.child("Name").getValue().toString());
                     Aprice17.setText(str+ref17.child("Price").getValue().toString());
                     Arewards17.setText(" "+ref17.child("Rewards").getValue().toString()+" Reward Points");
                     Glide.with(getContext())
                             .load(ref17.child("Image").getValue().toString())
                             .diskCacheStrategy(DiskCacheStrategy.ALL)
                             .into(Aimage17);


                     DataSnapshot ref18=dataSnapshot.child("HomeAppliances6");
                     Aname18.setText(ref18.child("Name").getValue().toString());
                     Aprice18.setText(str+ref18.child("Price").getValue().toString());
                     Arewards18.setText(" "+ref18.child("Rewards").getValue().toString()+" Reward Points");
                     Glide.with(getContext())
                             .load(ref18.child("Image").getValue().toString())
                             .diskCacheStrategy(DiskCacheStrategy.ALL)
                             .into(Aimage18);

                 }

                 @Override
                 public void onCancelled(DatabaseError databaseError) {


                 }
             });




             //Fashion
             final DatabaseReference ref23 = database.child("AppContent").child("HomePage").child("Fashion");
             ref23.addListenerForSingleValueEvent(new ValueEventListener() {
                 @Override
                 public void onDataChange(DataSnapshot dataSnapshot) {

                     DataSnapshot ref19=dataSnapshot.child("Fashion1");
                     Aname19.setText(ref19.child("Name").getValue().toString());
                     Aprice19.setText(str+ref19.child("Price").getValue().toString());
                     Arewards19.setText(" "+ref19.child("Rewards").getValue().toString()+" Reward Points");
                     Glide.with(getContext())
                             .load(ref19.child("Image").getValue().toString())
                             .diskCacheStrategy(DiskCacheStrategy.ALL)
                             .into(Aimage19);


                     DataSnapshot ref20=dataSnapshot.child("Fashion2");
                     Aname20.setText(ref20.child("Name").getValue().toString());
                     Aprice20.setText(str+ref20.child("Price").getValue().toString());
                     Arewards20.setText(" "+ref20.child("Rewards").getValue().toString()+" Reward Points");
                     Glide.with(getContext())
                             .load(ref20.child("Image").getValue().toString())
                             .diskCacheStrategy(DiskCacheStrategy.ALL)
                             .into(Aimage20);

                     DataSnapshot ref21=dataSnapshot.child("Fashion3");
                     Aname21.setText(ref21.child("Name").getValue().toString());
                     Aprice21.setText(str+ref21.child("Price").getValue().toString());
                     Arewards21.setText(" "+ref21.child("Rewards").getValue().toString()+" Reward Points");
                     Glide.with(getContext())
                             .load(ref21.child("Image").getValue().toString())
                             .diskCacheStrategy(DiskCacheStrategy.ALL)
                             .into(Aimage21);

                     DataSnapshot ref22=dataSnapshot.child("Fashion4");
                     Aname22.setText(ref22.child("Name").getValue().toString());
                     Aprice22.setText(str+ref22.child("Price").getValue().toString());
                     Arewards22.setText(" "+ref22.child("Rewards").getValue().toString()+" Reward Points");
                     Glide.with(getContext())
                             .load(ref22.child("Image").getValue().toString())
                             .diskCacheStrategy(DiskCacheStrategy.ALL)
                             .into(Aimage22);


                     DataSnapshot ref23=dataSnapshot.child("Fashion5");
                     Aname23.setText(ref23.child("Name").getValue().toString());
                     Aprice23.setText(str+ref23.child("Price").getValue().toString());
                     Arewards23.setText(" "+ref23.child("Rewards").getValue().toString()+" Reward Points");
                     Glide.with(getContext())
                             .load(ref23.child("Image").getValue().toString())
                             .diskCacheStrategy(DiskCacheStrategy.ALL)
                             .into(Aimage23);


                     DataSnapshot ref24=dataSnapshot.child("Fashion6");
                     Aname24.setText(ref24.child("Name").getValue().toString());
                     Aprice24.setText(str+ref24.child("Price").getValue().toString());
                     Arewards24.setText(" "+ref24.child("Rewards").getValue().toString()+" Reward Points");
                     Glide.with(getContext())
                             .load(ref24.child("Image").getValue().toString())
                             .diskCacheStrategy(DiskCacheStrategy.ALL)
                             .into(Aimage24);

                 }

                 @Override
                 public void onCancelled(DatabaseError databaseError) {


                 }
             });



             DatabaseReference ref100=FirebaseDatabase.getInstance().getReference().child("Category");
             Query abc=ref100.orderByChild("Category").equalTo("Mobile");
             FirebaseRecyclerAdapter<Mobile, ViewHolder> firebaseRecyclerAdapter =
                     new FirebaseRecyclerAdapter<Mobile, ViewHolder>(
                             Mobile.class,
                             R.layout.cardview_mobile,
                             ViewHolder.class,
                             abc
                     ) {
                         @Override
                         protected void populateViewHolder(ViewHolder viewHolder, Mobile profile, int position) {
                             viewHolder.setDetails(getContext(), profile.Name, profile.Price, profile.Cashback, profile.Image1,profile.Category,profile.CategoryName,profile.Feature1);

                         }
                         @Override
                         public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                             ViewHolder viewHolder=super.onCreateViewHolder(parent,viewType);
                             viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
                                 @Override
                                 public void onItemClick(View v, int position) {
                                     TextView mobile_name=(TextView)v.findViewById(R.id.mobile_nameTV);

                                     String a=mobile_name.getText().toString();


                                     Bundle bundle=new Bundle();
                                     MobileDetailsFragment fragment= new MobileDetailsFragment();

                                     bundle.putString("name",a);
                                     fragment.setArguments(bundle);
                                     FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                                     fragmentManager.beginTransaction()
                                             .addToBackStack(null)
                                             .replace(R.id.frame_container, fragment).commit();
                                 }

                                 @Override
                                 public void onItemLongClick(View v, int position) {

                                 }
                             });
                             return viewHolder;
                         }
                         };

             recyclerViewmobiledetails.setAdapter(firebaseRecyclerAdapter);



                 linearLayout1.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         fragment=new MobileDetailsFragment();
                         bundle.putString("name",mobilename1.getText().toString());
                         fragment.setArguments(bundle);
                         FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                         fragmentManager.beginTransaction()
                                 .addToBackStack(null)
                                 .replace(R.id.frame_container,fragment).commit();
                     }
                 });

             linearLayout2.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     fragment=new MobileDetailsFragment();
                     bundle.putString("name",mobilename2.getText().toString());
                     fragment.setArguments(bundle);
                     FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                     fragmentManager.beginTransaction()
                             .addToBackStack(null)
                             .replace(R.id.frame_container,fragment).commit();
                 }
             });

             linearLayout3.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     fragment=new MobileDetailsFragment();
                     bundle.putString("name",mobilename3.getText().toString());
                     fragment.setArguments(bundle);
                     FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                     fragmentManager.beginTransaction()
                             .addToBackStack(null)
                             .replace(R.id.frame_container,fragment).commit();
                 }
             });


             linearLayout4.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     fragment=new MobileDetailsFragment();
                     bundle.putString("name",mobilename4.getText().toString());
                     fragment.setArguments(bundle);
                     FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                     fragmentManager.beginTransaction()
                             .addToBackStack(null)
                             .replace(R.id.frame_container,fragment).commit();
                 }
             });

             seemore.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     fragment=new MobileFragment();
                     session.setsub("MI");
                     FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                     fragmentManager.beginTransaction()
                             .addToBackStack(null)
                             .replace(R.id.frame_container,fragment).commit();
                 }
             });




                Linearlayout5.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     fragment=new NewArrivalDetails();
                     bundle.putString("name",Newname1.getText().toString());
                     fragment.setArguments(bundle);
                     FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                     fragmentManager.beginTransaction()
                             .addToBackStack(null)
                             .replace(R.id.frame_container,fragment).commit();
                 }
             });

             Linearlayout6.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     fragment=new NewArrivalDetails();
                     bundle.putString("name",Newname2.getText().toString());
                     fragment.setArguments(bundle);
                     FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                     fragmentManager.beginTransaction()
                             .addToBackStack(null)
                             .replace(R.id.frame_container,fragment).commit();
                 }
             });

             Linearlayout7.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     fragment=new NewArrivalDetails();
                     bundle.putString("name",Newname3.getText().toString());
                     fragment.setArguments(bundle);
                     FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                     fragmentManager.beginTransaction()
                             .addToBackStack(null)
                             .replace(R.id.frame_container,fragment).commit();
                 }
             });

             Linearlayout8.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     fragment=new NewArrivalDetails();
                     bundle.putString("name",Newname4.getText().toString());
                     fragment.setArguments(bundle);
                     FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                     fragmentManager.beginTransaction()
                             .addToBackStack(null)
                             .replace(R.id.frame_container,fragment).commit();
                 }
             });

             seemore1.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     fragment=new NewArrivalFragment();
                     bundle.putString("name","");
                     fragment.setArguments(bundle);
                     FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                     fragmentManager.beginTransaction()
                             .addToBackStack(null)
                             .replace(R.id.frame_container,fragment).commit();
                 }
             });




             Linearlayout9.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     fragment=new AccesoriesDetails();
                     bundle.putString("name",Aname1.getText().toString());
                     fragment.setArguments(bundle);
                     FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                     fragmentManager.beginTransaction()
                             .addToBackStack(null)
                             .replace(R.id.frame_container,fragment).commit();
                 }
             });

             Linearlayout10.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     fragment=new AccesoriesDetails();
                     bundle.putString("name",Aname2.getText().toString());
                     fragment.setArguments(bundle);
                     FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                     fragmentManager.beginTransaction()
                             .addToBackStack(null)
                             .replace(R.id.frame_container,fragment).commit();
                 }
             });

             Linearlayout11.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     fragment=new AccesoriesDetails();
                     bundle.putString("name",Aname3.getText().toString());
                     fragment.setArguments(bundle);
                     FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                     fragmentManager.beginTransaction()
                             .addToBackStack(null)
                             .replace(R.id.frame_container,fragment).commit();
                 }
             });


             Linearlayout12.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     fragment=new AccesoriesDetails();
                     bundle.putString("name",Aname4.getText().toString());
                     fragment.setArguments(bundle);
                     FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                     fragmentManager.beginTransaction()
                             .addToBackStack(null)
                             .replace(R.id.frame_container,fragment).commit();
                 }
             });

             Linearlayout13.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     fragment=new AccesoriesDetails();
                     bundle.putString("name",Aname5.getText().toString());
                     fragment.setArguments(bundle);
                     FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                     fragmentManager.beginTransaction()
                             .addToBackStack(null)
                             .replace(R.id.frame_container,fragment).commit();
                 }
             });


             Linearlayout14.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     fragment=new AccesoriesDetails();
                     bundle.putString("name",Aname6.getText().toString());
                     fragment.setArguments(bundle);
                     FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                     fragmentManager.beginTransaction()
                             .addToBackStack(null)
                             .replace(R.id.frame_container,fragment).commit();
                 }
             });


             seemore2.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     fragment=new AccesoriesFragment();
                     bundle.putString("name","");
                     fragment.setArguments(bundle);
                     FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                     fragmentManager.beginTransaction()
                             .addToBackStack(null)
                             .replace(R.id.frame_container,fragment).commit();
                 }
             });

             Linearlayout15.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     fragment=new ComputerDetails();
                     bundle.putString("name",Aname7.getText().toString());
                     fragment.setArguments(bundle);
                     FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                     fragmentManager.beginTransaction()
                             .addToBackStack(null)
                             .replace(R.id.frame_container,fragment).commit();
                 }
             });

             Linearlayout16.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     fragment=new ComputerDetails();
                     bundle.putString("name",Aname8.getText().toString());
                     fragment.setArguments(bundle);
                     FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                     fragmentManager.beginTransaction()
                             .addToBackStack(null)
                             .replace(R.id.frame_container,fragment).commit();
                 }
             });


             Linearlayout17.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     fragment=new ComputerDetails();
                     bundle.putString("name",Aname9.getText().toString());
                     fragment.setArguments(bundle);
                     FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                     fragmentManager.beginTransaction()
                             .addToBackStack(null)
                             .replace(R.id.frame_container,fragment).commit();
                 }
             });

             Linearlayout18.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     fragment=new ComputerDetails();
                     bundle.putString("name",Aname10.getText().toString());
                     fragment.setArguments(bundle);
                     FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                     fragmentManager.beginTransaction()
                             .addToBackStack(null)
                             .replace(R.id.frame_container,fragment).commit();
                 }
             });

             Linearlayout19.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     fragment=new ComputerDetails();
                     bundle.putString("name",Aname11.getText().toString());
                     fragment.setArguments(bundle);
                     FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                     fragmentManager.beginTransaction()
                             .addToBackStack(null)
                             .replace(R.id.frame_container,fragment).commit();
                 }
             });


             Linearlayout20.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     fragment=new ComputerDetails();
                     bundle.putString("name",Aname12.getText().toString());
                     fragment.setArguments(bundle);
                     FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                     fragmentManager.beginTransaction()
                             .addToBackStack(null)
                             .replace(R.id.frame_container,fragment).commit();
                 }
             });



             seemore3.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     fragment=new ComputerFragment();
                     bundle.putString("name","");
                     fragment.setArguments(bundle);
                     FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                     fragmentManager.beginTransaction()
                             .addToBackStack(null)
                             .replace(R.id.frame_container,fragment).commit();
                 }
             });
             
             
             
             Linearlayout21.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     fragment=new HomeAppliancesDetails();
                     bundle.putString("name",Aname13.getText().toString());
                     fragment.setArguments(bundle);
                     FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                     fragmentManager.beginTransaction()
                             .addToBackStack(null)
                             .replace(R.id.frame_container,fragment).commit();
                 }
             });


             Linearlayout22.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     fragment=new HomeAppliancesDetails();
                     bundle.putString("name",Aname14.getText().toString());
                     fragment.setArguments(bundle);
                     FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                     fragmentManager.beginTransaction()
                             .addToBackStack(null)
                             .replace(R.id.frame_container,fragment).commit();
                 }
             });


             Linearlayout23.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     fragment=new HomeAppliancesDetails();
                     bundle.putString("name",Aname15.getText().toString());
                     fragment.setArguments(bundle);
                     FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                     fragmentManager.beginTransaction()
                             .addToBackStack(null)
                             .replace(R.id.frame_container,fragment).commit();
                 }
             });


             Linearlayout24.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     fragment=new HomeAppliancesDetails();
                     bundle.putString("name",Aname16.getText().toString());
                     fragment.setArguments(bundle);
                     FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                     fragmentManager.beginTransaction()
                             .addToBackStack(null)
                             .replace(R.id.frame_container,fragment).commit();
                 }
             });


             Linearlayout25.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     fragment=new HomeAppliancesDetails();
                     bundle.putString("name",Aname17.getText().toString());
                     fragment.setArguments(bundle);
                     FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                     fragmentManager.beginTransaction()
                             .addToBackStack(null)
                             .replace(R.id.frame_container,fragment).commit();
                 }
             });


             Linearlayout26.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     fragment=new HomeAppliancesDetails();
                     bundle.putString("name",Aname18.getText().toString());
                     fragment.setArguments(bundle);
                     FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                     fragmentManager.beginTransaction()
                             .addToBackStack(null)
                             .replace(R.id.frame_container,fragment).commit();
                 }
             });


             seemore4.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     fragment=new HomeAppliancesFragment();
                     bundle.putString("name","");
                     fragment.setArguments(bundle);
                     FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                     fragmentManager.beginTransaction()
                             .addToBackStack(null)
                             .replace(R.id.frame_container,fragment).commit();
                 }
             });

             Linearlayout27.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     fragment=new FashionDetails();
                     bundle.putString("name",Aname19.getText().toString());
                     fragment.setArguments(bundle);
                     FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                     fragmentManager.beginTransaction()
                             .addToBackStack(null)
                             .replace(R.id.frame_container,fragment).commit();
                 }
             });


             Linearlayout28.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     fragment=new FashionDetails();
                     bundle.putString("name",Aname20.getText().toString());
                     fragment.setArguments(bundle);
                     FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                     fragmentManager.beginTransaction()
                             .addToBackStack(null)
                             .replace(R.id.frame_container,fragment).commit();
                 }
             });

             Linearlayout29.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     fragment=new FashionDetails();
                     bundle.putString("name",Aname21.getText().toString());
                     fragment.setArguments(bundle);
                     FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                     fragmentManager.beginTransaction()
                             .addToBackStack(null)
                             .replace(R.id.frame_container,fragment).commit();
                 }
             });


             Linearlayout30.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     fragment=new FashionDetails();
                     bundle.putString("name",Aname22.getText().toString());
                     fragment.setArguments(bundle);
                     FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                     fragmentManager.beginTransaction()
                             .addToBackStack(null)
                             .replace(R.id.frame_container,fragment).commit();
                 }
             });


             Linearlayout31.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     fragment=new FashionDetails();
                     bundle.putString("name",Aname23.getText().toString());
                     fragment.setArguments(bundle);
                     FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                     fragmentManager.beginTransaction()
                             .addToBackStack(null)
                             .replace(R.id.frame_container,fragment).commit();
                 }
             });


             Linearlayout32.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     fragment=new FashionDetails();
                     bundle.putString("name",Aname24.getText().toString());
                     fragment.setArguments(bundle);
                     FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                     fragmentManager.beginTransaction()
                             .addToBackStack(null)
                             .replace(R.id.frame_container,fragment).commit();
                 }
             });



             seemore5.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     fragment=new FashionFragment();
                     bundle.putString("name","");
                     fragment.setArguments(bundle);
                     FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                     fragmentManager.beginTransaction()
                             .addToBackStack(null)
                             .replace(R.id.frame_container,fragment).commit();
                 }
             });


             final DatabaseReference ref3 = database.child("AppContent").child("HomePage");
             ref3.child("Baner").addListenerForSingleValueEvent(new ValueEventListener() {
                 @Override
                 public void onDataChange(DataSnapshot dataSnapshot) {
                        Glide.with(getContext()).load(dataSnapshot.child("Baner1").getValue().toString())
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                               .into(baner1);
                        Glide.with(getContext()).load(dataSnapshot.child("Baner2").getValue().toString())
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .into(baner2);

                 }

                 @Override
                 public void onCancelled(DatabaseError databaseError) {

                 }
             });

             border.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     fragment=new OrdersFragment();
                     fragment.setArguments(bundle);
                     FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                     fragmentManager.beginTransaction()
                             .addToBackStack(null)
                             .replace(R.id.frame_container,fragment).commit();
                 }
             });

             bcheckout.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     fragment=new CartFragment();
                     fragment.setArguments(bundle);
                     FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                     fragmentManager.beginTransaction()
                             .addToBackStack(null)
                             .replace(R.id.frame_container,fragment).commit();
                 }
             });


             byes.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     Uri uri = Uri.parse("market://details?id=" + getContext().getPackageName());
                     Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                     // To count with Play market backstack, After pressing back button,
                     // to taken back to our application, we need to add following flags to intent.
                     goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                             Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                             Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                     try {
                         startActivity(goToMarket);
                     } catch (ActivityNotFoundException e) {
                         startActivity(new Intent(Intent.ACTION_VIEW,
                                 Uri.parse("http://play.google.com/store/apps/details?id=" + getContext().getPackageName())));
                     }
                 }
             });

             bno.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {

                     final String[] m_Text = {""};
                     AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                     builder.setTitle("FEEDBACK");

                    // Set up the input
                     final EditText input = new EditText(getContext());
                    // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                     input.setInputType(InputType.TYPE_CLASS_TEXT);
                     builder.setView(input);

                        // Set up the buttons
                     builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                         @Override
                         public void onClick(DialogInterface dialog, int which) {
                             m_Text[0] = input.getText().toString();
                             DatabaseReference ref=database.child("FeedBack").push();
                             ref.child("Feedback").setValue(m_Text[0]);
                         }
                     });
                     builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                         @Override
                         public void onClick(DialogInterface dialog, int which) {
                             dialog.cancel();
                         }
                     });

                     builder.show();
                 }
             });






                return v;
            }




            @Override
        public void onClick(View v){
//                mAuth.getInstance().signOut();
                Intent intent = new Intent(getActivity(), login.class);
                startActivity(intent);
            }

//    public void showInterstitial(){
//        if(mInterstitialAd.isLoaded())
//            mInterstitialAd.show();
//
//    }


    @Override
    public void onStart() {
        super.onStart();



//        showInterstitial();

       Query firebasequery=FirebaseDatabase.getInstance().getReference().child("Category");



        FirebaseRecyclerAdapter<Mobile, ViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<Mobile, ViewHolder>(
                        Mobile.class,
                        R.layout.cardview_mobile,
                        ViewHolder.class,
                        firebasequery
                ) {
                    @Override
                    protected void populateViewHolder(ViewHolder viewHolder, Mobile profile, int position) {
                        viewHolder.setDetails(getContext(), profile.Name, profile.Price, profile.Cashback, profile.Image1,profile.Category,profile.CategoryName,profile.Feature1);
                    }
                    @Override
                    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                        ViewHolder viewHolder=super.onCreateViewHolder(parent,viewType);
                        viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
                            @Override
                            public void onItemClick(View v, int position) {
                                TextView mobile_name=(TextView)v.findViewById(R.id.mobile_nameTV);

                                String a=mobile_name.getText().toString();


                                Bundle bundle=new Bundle();
                                MobileDetailsFragment fragment= new MobileDetailsFragment();

                                bundle.putString("name",a);
                                fragment.setArguments(bundle);
                                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                                fragmentManager.beginTransaction()
                                        .addToBackStack(null)
                                        .replace(R.id.frame_container, fragment).commit();
                            }

                            @Override
                            public void onItemLongClick(View v, int position) {

                            }
                        });
                        return viewHolder;
                    }

                };

        mRecyclerView.setAdapter(firebaseRecyclerAdapter);



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






    private  void firebasesearch(final String searchText)
    {
        Query firebasesearchquery = FirebaseDatabase.getInstance().getReference().child("Category");
        FirebaseRecyclerAdapter<Mobile,ViewHolder> firebaseRecyclerAdapter=
                new FirebaseRecyclerAdapter<Mobile, ViewHolder>(
                        Mobile.class,
                        R.layout.cardview_mobile,
                        ViewHolder.class,
                        firebasesearchquery
                ) {
                    @Override
                    protected void populateViewHolder(ViewHolder viewHolder, Mobile profile, int position) {
                        viewHolder.setDetails1(getContext(), profile.Name, profile.Price, profile.Cashback, profile.Image1, profile.Category,profile.CategoryName,searchText,profile.Feature1);
                    }

                    @Override
                    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

                        ViewHolder viewHolder=super.onCreateViewHolder(parent,viewType);
                        viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
                            @Override
                            public void onItemClick(View v, int position) {
                                TextView mobile_name = (TextView) v.findViewById(R.id.mobile_nameTV);

                                String a = mobile_name.getText().toString();


                                Bundle bundle = new Bundle();
                                MobileDetailsFragment fragment = new MobileDetailsFragment();

                                bundle.putString("name", a);
                                fragment.setArguments(bundle);
                                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                                fragmentManager.beginTransaction()
                                        .addToBackStack(null)
                                        .replace(R.id.frame_container, fragment).commit();
                            }

                            @Override
                            public void onItemLongClick(View v, int position) {

                            }
                        });
                        return viewHolder;
                    }
                };
        mRecyclerView.setAdapter(firebaseRecyclerAdapter);

    }




    @Override
    public  void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {


        inflater.inflate(R.menu.main_menu,menu);
        MenuItem itemMessages = menu.findItem(R.id.cart);
        MenuItem search=menu.findItem(R.id.search);
//        MenuItem signout=menu.findItem(R.id.signout);
        View badgeLayout = (RelativeLayout) itemMessages.getActionView();
        final TextView itemMessagesBadgeTextView = (TextView) badgeLayout.findViewById(R.id.badge_textView);
        LoginSession session=new LoginSession(getContext());
        DatabaseReference ref= FirebaseDatabase.getInstance().getReference("Users").child("+91"+session.getusename()).child("Cart");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // get total available quest
                int size = (int) dataSnapshot.getChildrenCount();
                itemMessagesBadgeTextView.setText(Integer.toString(size));
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        itemMessagesBadgeTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment=new CartFragment();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.frame_container, fragment).commit();

            }
        });


        MenuItem item=menu.findItem(R.id.search);
        final SearchView searchView= (SearchView) MenuItemCompat.getActionView(item);

        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                visibility();
            }
        });

        ImageView closeButton = (ImageView)searchView.findViewById(R.id.search_close_btn);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scroll.setVisibility(View.VISIBLE);
                mRecyclerView.setVisibility(View.GONE);
                searchView.setIconified(true);
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(!TextUtils.isEmpty(query))
                firebasesearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(!TextUtils.isEmpty(newText))
               firebasesearch(newText);
                return false;
            }
        });
    }

    public void visibility()
    {
        scroll.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);
        return;
    }



}







