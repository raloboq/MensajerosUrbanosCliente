package co.mensajeros.cliente;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

/**
 * Created by Rene on 9/8/14.
 */
public class NewService4 extends Fragment {

    public NewService4() {
    }

    ServiceObject service = new ServiceObject();
    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    CameraPosition cameraPosition;
    private static View rootView;
    public Typeface font2;
    public Typeface font1;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //View rootView = inflater.inflate(R.layout.fragment_new_service4, container, false);


        if (rootView != null) {
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if (parent != null)
                parent.removeView(rootView);
        }
        try {
            rootView = inflater.inflate(R.layout.fragment_new_service4, container, false);
        } catch (InflateException e) {
        /* map is already there, just return view as it is */
        }


        Bundle extras = getArguments();


        service = (ServiceObject) extras.getSerializable("serviceobject");

        font2 = Typeface.createFromAsset(getActivity().getAssets(), "Raleway-Medium.ttf");
        font1 = Typeface.createFromAsset(getActivity().getAssets(), "Raleway-Regular.ttf");


//        TextView resumenTipo = (TextView) rootView.findViewById(R.id.ResumenTipo);
        //resumenTipo.setTypeface(font1);
        TextView resumenFecha = (TextView) rootView.findViewById(R.id.ResumenFecha);
        //resumenFecha.setTypeface(font1);
        TextView resumenDireccionE = (TextView) rootView.findViewById(R.id.ResumenDireccionEntrega);
        //resumenDireccionE.setTypeface(font1);
        TextView resumenDireccionR = (TextView) rootView.findViewById(R.id.ResumenDireccionRecogida);
        //resumenDireccionR.setTypeface(font1);
       // TextView resumenDescripcion = (TextView) rootView.findViewById(R.id.ResumenDescripcion);
        //resumenDescripcion.setTypeface(font1);
        TextView resumenValor = (TextView) rootView.findViewById(R.id.ResumenValorServ);
        //resumenValor.setTypeface(font1);
        TextView valorserv = (TextView) rootView.findViewById(R.id.ResumenValorServ);
        //resumenCiudad.setTypeface(font1);
        //TextView resumenIdaVuelta = (TextView) rootView.findViewById(R.id.Resumenidavuelta);
        CheckBox checboxida = (CheckBox) rootView.findViewById(R.id.checkBoxida);
        //resumenIdaVuelta.setTypeface(font1);


        TextView linear2 = (TextView) rootView.findViewById(R.id.textView);
        linear2.setTypeface(font1);

        TextView linear4 = (TextView) rootView.findViewById(R.id.textView3);
        linear4.setTypeface(font1);
        TextView linear5 = (TextView) rootView.findViewById(R.id.textView4);
        linear5.setTypeface(font1);
        TextView linear6 = (TextView) rootView.findViewById(R.id.textView5);
        linear6.setTypeface(font1);

        TextView linear9 = (TextView) rootView.findViewById(R.id.textView8);
        linear9.setTypeface(font1);


       // resumenTipo.setText(service.getTipo_servicio());
        resumenFecha.setText(service.getFecha_recogida());
        resumenDireccionE.setText(service.getDirecciones().get(0).getAddress().toString());
        resumenDireccionR.setText(service.getDirecciones().get(1).getAddress().toString());
      //  resumenDescripcion.setText(service.getDescripcion());
        resumenValor.setText(service.getValor_declarado());
        valorserv.setText(service.getValor_servicio());
      //  resumenCiudad.setText(service.getCiudad());
        if (service.getIdayVuelta() == "1")
            checboxida.setChecked(true);

        //resumenIdaVuelta.setText(service.getIdayVuelta());

        for (int i = 0; i < service.getDirecciones().size(); i++) {
            Log.i("resumen", "dir=" + service.getDirecciones().get(i).getAddress().toString());
            Log.i("resumen", "dir=" + service.getDirecciones().get(i).getLongitude());

        }

        //     Log.i("resumen", "dir1=" +service.getDirecciones().get(0).getAddress().toString());
        //     Log.i("resumen", "dir2=" +service.getDirecciones().get(1).getAddress().toString());
        //     Log.i("resumen", "size=" +service.getDirecciones().size());

        return rootView;

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setUpMapIfNeeded();
    }

    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    private void setUpMap() {

        ArrayList<Marker> markers = new ArrayList<Marker>();

        for (int i = 0; i < service.getDirecciones().size(); i++) {


            Marker m = mMap.addMarker(new MarkerOptions().position(new LatLng(service.getDirecciones().get(i).getLatitude(), service.getDirecciones().get(i).getLongitude())));
            markers.add(m);
        /*CameraPosition cameraPosition = new CameraPosition.Builder().target(
                new LatLng(service.getDirecciones().get(0).getLatitude(), service.getDirecciones().get(0).getLongitude())).zoom(12).build();

        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));*/


        }
        final LatLngBounds.Builder builder = new LatLngBounds.Builder();
        for (Marker marker : markers) {
            builder.include(marker.getPosition());
        }
        final LatLngBounds bounds = builder.build();
        Log.i("maps", bounds.toString());
        //int padding = 1; // offset from edges of the map in pixels
        //CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, padding,500,500);
        //mMap.moveCamera(cu);
      //  mMap.animateCamera(cu);


        try {
            this.mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 50));
        } catch (IllegalStateException e) {
            // layout not yet initialized
            final View mapView = getFragmentManager().findFragmentById(R.id.map).getView();
            if (mapView.getViewTreeObserver().isAlive()) {
                mapView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

                    @SuppressWarnings("deprecation")
                    @SuppressLint("NewApi")
                    // We check which build version we are using.
                    @Override
                    public void onGlobalLayout() {
                        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                            mapView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                        } else {
                            mapView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        }
                        mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 100));
                    }
                });
            }
        }


       /*

        mMap.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {

            @Override
            public void onCameraChange(CameraPosition arg0) {
                // Move camera.
                mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), 10));
                // Remove listener to prevent position reset on camera move.
                mMap.setOnCameraChangeListener(null);
            }
        });
*/
    }
}