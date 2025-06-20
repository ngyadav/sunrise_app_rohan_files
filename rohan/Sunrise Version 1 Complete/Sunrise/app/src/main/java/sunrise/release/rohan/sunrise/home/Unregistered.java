package sunrise.release.rohan.sunrise.home;

    import android.content.ActivityNotFoundException;
    import android.content.Intent;
    import android.net.Uri;
    import android.support.v7.app.AppCompatActivity;
    import android.os.Bundle;
    import android.view.View;
    import android.widget.ImageView;

    import sunrise.release.rohan.sunrise.Functionality.LoginSession;
    import sunrise.release.rohan.sunrise.R;
    import com.google.android.gms.maps.CameraUpdate;
    import com.google.android.gms.maps.CameraUpdateFactory;
    import com.google.android.gms.maps.GoogleMap;
    import com.google.android.gms.maps.OnMapReadyCallback;
    import com.google.android.gms.maps.SupportMapFragment;
    import com.google.android.gms.maps.model.BitmapDescriptorFactory;
    import com.google.android.gms.maps.model.LatLng;
    import com.google.android.gms.maps.model.MarkerOptions;

public class Unregistered extends AppCompatActivity implements OnMapReadyCallback {
    private LoginSession session;
    private GoogleMap mMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unregistered);
        ImageView call=(ImageView)findViewById(R.id.call);
        ImageView mail=(ImageView)findViewById(R.id.mail);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:09945599388"));
                startActivity(intent);
            }
        });
        mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                session = new LoginSession(Unregistered.this);
                Intent intent=new Intent(Intent.ACTION_SEND);
                String[] recipients={"info@sunrise.com"};
                intent.putExtra(Intent.EXTRA_EMAIL, recipients);
                intent.putExtra(Intent.EXTRA_SUBJECT,"Regarding Registrattion of Account:"+session.getusename());
                intent.putExtra(Intent.EXTRA_TEXT,"Please Activate my account as soon as possible.\nThank You");
                intent.setType("text/html");
//                intent.setPackage("com.google.android.gm");
                startActivity(Intent.createChooser(intent, "Send mail"));
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(13.6138329,77.51766410000005);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sunrise Enterprises"));
        CameraUpdate center=
                CameraUpdateFactory.newLatLng(sydney);
        CameraUpdate zoom=CameraUpdateFactory.zoomTo(18);
        mMap.moveCamera(center);
        mMap.animateCamera(zoom);


    }
}
