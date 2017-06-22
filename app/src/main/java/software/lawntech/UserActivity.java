package software.lawntech;

/**
 * Created by Joyce Amore on 6/18/2017.
 */

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;


public class UserActivity extends FragmentActivity implements OnMapReadyCallback{
    private String mockAddress = "";
    private GoogleMap mMap;
    private static final String TAG = "UserActivity";

    private TextView greetingTextView;
    private Button btnLogOut, btnManageLawn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        Bundle bundle = getIntent().getExtras();
        //String user = bundle.getString("username");
        mockAddress = bundle.getString("address");
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        greetingTextView = (TextView) findViewById(R.id.greeting_text_view);
        btnLogOut = (Button) findViewById(R.id.logout_button);
        btnManageLawn = (Button) findViewById(R.id.button5);
        greetingTextView.setText("Hello "+ mockAddress);
        // Progress dialog

        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(i);

            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        // Add a marker in Sydney and move the camera
        float zoomLevel = 16; //This goes up to 21
        LatLng addressNode = getLocationFromAddress(getApplicationContext(),mockAddress);
        LatLng sanAntonioMower1 = new LatLng(29.424122, -98.493629);
        LatLng sanAntonioMower2 = new LatLng(29.3, -98.3);
        LatLng sanAntonioMower3 = new LatLng(29.5, -98.5);
        mMap.addMarker(new MarkerOptions().position(addressNode).title("Your Lawn"));
        mMap.addMarker(new MarkerOptions().position(sanAntonioMower1).title("Mower1"));
        mMap.addMarker(new MarkerOptions().position(sanAntonioMower2).title("Mower2"));
        mMap.addMarker(new MarkerOptions().position(sanAntonioMower3).title("Mower3"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(addressNode, zoomLevel));
    }
    public void manageLawn(View view) {
        Intent getNameScreenIntent = new Intent(this, ManageLawn.class);
        final int result = 1;
        getNameScreenIntent.putExtra("callingActivity", "test");
        startActivityForResult(getNameScreenIntent, result);
    }
    public void signOut(View view) {
        Intent getNameScreenIntent = new Intent(this, LoginActivity.class);
        final int result = 1;
        getNameScreenIntent.putExtra("callingActivity", "test");
        startActivityForResult(getNameScreenIntent, result);
        finish();
    }
    public void accountSettings(View view) {
        Intent getNameScreenIntent = new Intent(this, AccountSettings.class);
        final int result = 1;
        getNameScreenIntent.putExtra("callingActivity", "test");
        startActivityForResult(getNameScreenIntent, result);
    }

    public LatLng getLocationFromAddress(Context context, String strAddress) {

        Geocoder coder = new Geocoder(context);
        List<Address> address;
        LatLng p1 = null;

        try {
            // May throw an IOException
            address = coder.getFromLocationName(strAddress, 5);
            if (address == null) {
                return null;
            }
            Address location = address.get(0);
            location.getLatitude();
            location.getLongitude();

            p1 = new LatLng(location.getLatitude(), location.getLongitude() );

        } catch (IOException ex) {

            ex.printStackTrace();
        }

        return p1;
    }
}