package life.happyholiday.fragments;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import butterknife.BindView;
import butterknife.ButterKnife;
import life.happyholiday.R;
import life.happyholiday.utils.PermissionHelper;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeMapFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeMapFragment extends Fragment {
    public static final int MY_LOCATION_REQUEST_CODE = 999;

    @BindView(R.id.mapView)
    MapView mMapView;
    private GoogleMap googleMap;

    public HomeMapFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment HomeMapFragment.
     */
    public static HomeMapFragment newInstance() {
        HomeMapFragment fragment = new HomeMapFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_map, container, false);
        ButterKnife.bind(this, view);

        mMapView.onCreate(savedInstanceState);
        mMapView.onResume(); // needed to get the map to display immediately

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                googleMap = mMap;

                checkPermissionAndShowMyLocation();

                // For dropping a marker at a point on the Map
                LatLng ccs = new LatLng(103.760958, 1.311206);
                googleMap.addMarker(new MarkerOptions().position(ccs).title("CCS").snippet("Cut Cook Serve"));

                // For zooming automatically to the location of the marker
                CameraPosition cameraPosition = new CameraPosition.Builder().target(ccs).zoom(12).build();
                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            }
        });

        return view;
    }

    private void checkPermissionAndShowMyLocation() {
        PermissionHelper.checkPermission(getActivity(), PermissionHelper.MY_PERMISSIONS_REQUEST_LOCATION, new PermissionHelper.CheckPermissionAction() {
            @Override
            public void doAction() {
                enableMyLocationOnMap();
            }

            @Override
            public void requestPermission() {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PermissionHelper.MY_PERMISSIONS_REQUEST_LOCATION);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == MY_LOCATION_REQUEST_CODE) {
            if (permissions.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                PermissionHelper.handlePermissionResponse(getContext(), permissions, grantResults, new PermissionHelper.PermissionResponseAction() {
                    @Override
                    public void doAction() {
                        enableMyLocationOnMap();
                    }
                });
            }
        }
    }

    @SuppressWarnings({"MissingPermission"})
    private void enableMyLocationOnMap() {
        googleMap.setMyLocationEnabled(true);
    }
}