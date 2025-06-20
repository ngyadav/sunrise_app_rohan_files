package rohan.groups.sunrisedelivery.Fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;
import rohan.groups.sunrisedelivery.Functionality.Session;
import rohan.groups.sunrisedelivery.R;


public class ProfileFragment extends Fragment {


    private static final int REQUEST_CAMERA = 3;
    private static final int REQUEST_CODE = 5;

    private static final int SELECT_FILE = 2;
    private Session session;
    private final int RESULT_CROP = 400;
    private StorageReference mstorageReference;
    private ProgressBar progressBar2;
    String a[] = new String[100];
    int i = 0;
    Uri imageHoldUri = null;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    Fragment fragment = null;
    CircleImageView user;

    public ProfileFragment() {
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
        View v=inflater.inflate(R.layout.fragment_profile, container, false);
        TextView namedisplay = v.findViewById(R.id.namedisplay);
        EditText name = v.findViewById(R.id.name);
        EditText number = v.findViewById(R.id.number);
        EditText password = v.findViewById(R.id.password);

        session = new Session(getContext());
        mstorageReference= FirebaseStorage.getInstance().getReference();

        namedisplay.setText(session.getname());
        name.setText(session.getname());
        number.setText(session.getusername());
        password.setText(session.getpassword());

        user=v.findViewById(R.id.pp);


        if (!TextUtils.isEmpty(session.getpp())) {
            Glide.with(getContext())
                    .load(session.getpp())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop()
                    .into(user);
        }


        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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

            }
        });



        return v;
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
                user.setImageBitmap(scaled);
                user.getLayoutParams().height = 400;
                user.getLayoutParams().width = 500;
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (imageHoldUri != null) {
                session = new Session(getActivity());
                Date c = Calendar.getInstance().getTime();
                System.out.println("Current time => " + c);

                String path = "Users/" + session.getusername() + ".jpg";
                StorageReference riversRef = mstorageReference.child(path);
                final ProgressDialog progressDialog = new ProgressDialog(getContext());
                progressDialog.setTitle("Updating....!");
                progressDialog.show();
                riversRef.putFile(imageHoldUri)
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                // Get a URL to the uploaded content
                                session.setpp("");
                                StorageReference storageRef = FirebaseStorage.getInstance().getReference();


                                final String[] u = new String[1];

                                storageRef.child("Users/" + session.getusername() + ".jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        u[0] = uri.toString();
                                        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Users").child(session.getusername());
                                        ref.child("PP").setValue(u[0]);

                                        session.setpp(u[0]);

//                                                ImageView Hpp=(ImageView)getActivity().findViewById(R.id.Hpp);
                                        Glide.with(getContext()).load(u[0]).into(user);
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
}
