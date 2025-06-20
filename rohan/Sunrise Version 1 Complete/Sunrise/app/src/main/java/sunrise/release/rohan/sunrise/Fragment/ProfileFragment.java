package sunrise.release.rohan.sunrise.Fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import sunrise.release.rohan.sunrise.Cart.CartFragment;
import sunrise.release.rohan.sunrise.Functionality.LoginSession;
import sunrise.release.rohan.sunrise.Orders.OrdersFragment;
import sunrise.release.rohan.sunrise.R;
import sunrise.release.rohan.sunrise.home.home;
import sunrise.release.rohan.sunrise.login.login;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import org.w3c.dom.Text;

import java.io.File;
import java.io.IOException;

import sunrise.release.rohan.sunrise.Cart.CartFragment;
import sunrise.release.rohan.sunrise.Orders.OrdersFragment;

import static android.support.constraint.Constraints.TAG;

public class ProfileFragment extends Fragment implements View.OnClickListener {
    TextView ppname, ppmobile, ppemail;
    ImageView pp,porder,pcart;
    Button Blogout;
    private static final int REQUEST_CAMERA = 3;
    private static final int REQUEST_CODE = 5;

    private static final int SELECT_FILE = 2;
    private LoginSession session;
    private final int RESULT_CROP = 400;
    private StorageReference mstorageReference;
    private ProgressBar progressBar2;
    String a[] = new String[100];
    int i = 0;
    Uri imageHoldUri = null;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    Fragment fragment = null;
    private AdView mAdView;
    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MobileAds.initialize(getContext(), "ca-app-pub-5167989951191945~5928872897");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_profile, container, false);
        mAdView = v.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        ppname = v.findViewById(R.id.ppname);
        ppmobile = v.findViewById(R.id.ppmobile);
        ppemail = v.findViewById(R.id.ppemail);
        pp = v.findViewById(R.id.pp);
        porder=v.findViewById(R.id.porder);
        pcart=v.findViewById(R.id.pcart);

        Blogout = (Button) v.findViewById(R.id.Blogout);
        progressBar2=(ProgressBar)v.findViewById(R.id.progressBar2);
        progressBar2.setVisibility(View.VISIBLE);
        session = new LoginSession(getActivity());
        mstorageReference= FirebaseStorage.getInstance().getReference();
        Blogout.setOnClickListener(this);
        pp.setOnClickListener(this);
        porder.setOnClickListener(this);
        pcart.setOnClickListener(this);


          String b=getArguments().getString("image");

          if(!b.equalsIgnoreCase("")) {
              Glide.with(getContext())
                      .load(b)
                      .diskCacheStrategy(DiskCacheStrategy.ALL)
                      .centerCrop()
                      .into(pp);
          }
                progressBar2.setVisibility(View.GONE);
                ppname.setText(getArguments().getString("name"));
                ppemail.setText(getArguments().getString("email"));
                ppmobile.setText(getArguments().getString("number"));
                return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Blogout:
                session = new LoginSession(getActivity());
                session.setusename("");
                session.setstatus("");
                Intent intent = new Intent(getContext(), login.class);
                startActivity(intent);
                getActivity().finish();

                break;

            case R.id.pp:
                final CharSequence[] items = {"Choose from Library",
                        "Cancel"};
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Add Photo!");

                //SET ITEMS AND THERE LISTENERS
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {

//                                if (items[item].equals("Take Photo")) {
//                                    cameraIntent();
//                                }
                        if (items[item].equals("Choose from Library")) {
                            galleryIntent();
                        } else if (items[item].equals("Cancel")) {
                            dialog.dismiss();
                        }
                    }
                });
                builder.show();
                break;
            case R.id.porder:
                fragment=new OrdersFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.frame_container,fragment).commit();
                break;

            case R.id.pcart:
                fragment=new CartFragment();
                FragmentManager fragmentManager1 = getActivity().getSupportFragmentManager();
                fragmentManager1.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.frame_container,fragment).commit();
                break;
            default:
                break;

        }

    }


    private void cameraIntent() {

        //CHOOSE CAMERA
        Log.d("gola", "entered here");
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA);
    }

        private void galleryIntent() {

            //CHOOSE IMAGE FROM GALLERY
    //        Log.d("gola", "entered here");
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            startActivityForResult(intent, REQUEST_CODE);


        }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        //SAVE URI FROM GALLERY
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {

            imageHoldUri = data.getData();
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), imageHoldUri);
                int nh = (int) (bitmap.getHeight() * (512.0 / bitmap.getWidth()));
                Bitmap scaled = Bitmap.createScaledBitmap(bitmap, 512, nh, true);
                pp.setImageBitmap(scaled);
                pp.getLayoutParams().height = 400;
                pp.getLayoutParams().width = 500;
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (imageHoldUri != null) {
                session = new LoginSession(getActivity());
                String path = "Users/" + session.getusename() + ".jpg";
                StorageReference riversRef = mstorageReference.child(path);
                final ProgressDialog progressDialog = new ProgressDialog(getContext());
                progressDialog.setTitle("Updating....!");
                progressDialog.show();
                riversRef.putFile(imageHoldUri)
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                // Get a URL to the uploaded content
                                session.seturl("");
                                StorageReference storageRef = FirebaseStorage.getInstance().getReference();


                                final String[] u = new String[1];

                                storageRef.child("Users/" + session.getusename() + ".jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        u[0] = uri.toString();
                                        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Users").child("+91" + session.getusename());
                                        ref.child("ProfileUrl").setValue(u[0]);
                                        session.seturl(u[0]);
//                                                ImageView Hpp=(ImageView)getActivity().findViewById(R.id.Hpp);
//                                               Glide.with(getContext()).load(u).into(Hpp);
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception exception) {
                                        // Handle any errors
                                    }
                                });


                                progressDialog.dismiss();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                                // Handle unsuccessful uploads
                                Toast.makeText(getContext(), exception.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                                double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                                progressDialog.setMessage((int) progress + "%Uploaded");
                            }
                        });

            } else {
                Toast.makeText(getContext(), "File Path Null", Toast.LENGTH_SHORT).show();
            }
        }
    }


    public void ImageCropFunction()
    {
        try{
                Intent CropIntent=new Intent("com.android.camera.action.CROP");
                CropIntent.setDataAndType(imageHoldUri,"image/*");
                CropIntent.putExtra("crop",true);
                CropIntent.putExtra("OutputX",180);
                CropIntent.putExtra("OutputY",180);
                CropIntent.putExtra("aspectX",3);
                CropIntent.putExtra("aspectX",4);
                CropIntent.putExtra("scaleUpIfneeded",true);
                CropIntent.putExtra("return-data",true);
                startActivityForResult(CropIntent,1);
        }catch (ActivityNotFoundException e)
        {

        }
    }

}
