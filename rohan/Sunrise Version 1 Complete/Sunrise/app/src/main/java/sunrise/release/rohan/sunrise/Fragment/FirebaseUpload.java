package sunrise.release.rohan.sunrise.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.storage.StorageManager;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import sunrise.release.rohan.sunrise.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

public class FirebaseUpload extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 234;
    private Button choose,upload;
    private ImageView image;
    private Uri filepath;
    private StorageReference mstorageReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_upload);
        choose = (Button) findViewById(R.id.choose);
        upload = (Button) findViewById(R.id.upload);
        image = (ImageView) findViewById(R.id.image);
        mstorageReference= FirebaseStorage.getInstance().getReference();
        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showfilechooser();
            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(filepath!=null) {
                    StorageReference riversRef = mstorageReference.child("images/rivers.jpg");
                    final ProgressDialog progressDialog=new ProgressDialog(FirebaseUpload.this);
                    progressDialog.setTitle("Uploading....!");
                    progressDialog.show();
                    riversRef.putFile(filepath)
                            .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                    // Get a URL to the uploaded content
                                    progressDialog.dismiss();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception exception) {
                                    // Handle unsuccessful uploads
                                    Toast.makeText(getApplicationContext(),exception.getMessage(),Toast.LENGTH_SHORT).show();
                                }
                            })
                            .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                                    double progress =(100.0*taskSnapshot.getBytesTransferred())/taskSnapshot.getTotalByteCount();
                                    progressDialog.setMessage((int)progress+"%Uploaded");
                                }
                            });
                }
                else
                {
                    Toast.makeText(FirebaseUpload.this,"File Path Null",Toast.LENGTH_SHORT).show();
                }
            }

        });
    }

    public void showfilechooser()
    {
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select an Image"),PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==PICK_IMAGE_REQUEST&&resultCode==RESULT_OK&&data!=null&&data.getData()!=null){
            filepath=data.getData();
            try {
                Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),filepath);
                int nh = (int) ( bitmap.getHeight() * (512.0 / bitmap.getWidth()) );
                Bitmap scaled = Bitmap.createScaledBitmap(bitmap, 512, nh, true);
                image.setImageBitmap(scaled);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
