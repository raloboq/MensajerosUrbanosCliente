package co.mensajeros.cliente;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.TextView;

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
 * Created by rene on 11/5/14.
 */
public class InfoService extends Fragment {



    public InfoService(){

    }

    Typeface font1;
    Typeface font2;
    private GoogleMap mMap; // Might be null if Google Play services APK is not available.

    TaskInfoObject taskinfo = new TaskInfoObject();
    ArrayList<DetailsTaskObject> itemlist = new ArrayList<DetailsTaskObject>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_info_service2, container, false);
        Bundle extras = getArguments();

        taskinfo= (TaskInfoObject) extras.getSerializable("taskinfo");

        font2 = Typeface.createFromAsset(getActivity().getAssets(), "Raleway-Medium.ttf");
        font1 = Typeface.createFromAsset(getActivity().getAssets(), "Raleway-Regular.ttf");

        //service= (ServiceObject) extras.getSerializable("serviceobject");

        TextView Distancia = (TextView)rootView.findViewById(R.id.distancia_text);
        TextView Valor = (TextView)rootView.findViewById(R.id.valor_text);
        TextView Recurso = (TextView)rootView.findViewById(R.id.recurso_text);

        Distancia.setText(taskinfo.getDistancia()+" Km recorridos");  // tomar la distancia en el paso anterior

        String valor = taskinfo.getValor_total();
        String[] valor2;
        valor2 = valor.split("\\.");

        Valor.setText("$"+valor2[0]); // tomat valor en el paso anterior
     //   Valor.setText(valor);
        Recurso.setText(taskinfo.getNombre_mensajero());

        final Bundle data = new Bundle();

        data.putSerializable("taskinfo", taskinfo);

        Recurso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.i("onclicrecu","clic");



                android.support.v4.app.FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                InfoMensajero inforecurso = new InfoMensajero();
                inforecurso.setArguments(data);
                transaction.add(R.id.content_frame, inforecurso,"inforecurso").addToBackStack("inforecurso");
                transaction.addToBackStack(null);
                transaction.commitAllowingStateLoss();
            }
        });



        /*TextView nombreRecurso = (TextView)rootView.findViewById(R.id.recursoNombre);
        nombreRecurso.setTypeface(font2);
        TextView cedulaRecurso = (TextView)rootView.findViewById(R.id.recursoCedula);
        cedulaRecurso.setTypeface(font2);

        TextView placaRecurso = (TextView)rootView.findViewById(R.id.recursoPlacaMoto);
        placaRecurso.setTypeface(font2);
        TextView creation_date = (TextView)rootView.findViewById(R.id.creation_date);
        creation_date.setTypeface(font2);
        TextView assigned_date = (TextView)rootView.findViewById(R.id.assigned_date);
        assigned_date.setTypeface(font2);
        TextView finish_date = (TextView)rootView.findViewById(R.id.finish_date);
        finish_date.setTypeface(font2);
        TextView status = (TextView)rootView.findViewById(R.id.TaskStatus);
        status.setTypeface(font2);

        nombreRecurso.setText(taskinfo.getNombre_mensajero());
        cedulaRecurso.setText(taskinfo.getCedula_mensajero());

        placaRecurso.setText(taskinfo.getPlaca_mensajero());
        creation_date.setText(taskinfo.getFecha_creacion());
        assigned_date.setText(taskinfo.getFecha_asignado());

        if(taskinfo.getFecha_finalizado().equals("null"))
            finish_date.setText("En curso");
            else
        finish_date.setText(taskinfo.getFecha_finalizado());

        if(taskinfo.getEstado_task().equals("1"))
            status.setText("Express");
        if(taskinfo.getEstado_task().equals("2"))
            status.setText("Estandar");
        if(taskinfo.getEstado_task().equals("3"))
            status.setText("Diligencia");


    */

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setUpMapIfNeeded();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        SupportMapFragment f = (SupportMapFragment) getFragmentManager()
                .findFragmentById(R.id.mapActiveService);
        if (f != null)
            getFragmentManager().beginTransaction().remove(f).commit();
    }

    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
           // mMap = ((SupportMapFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.mapActiveService))
           //         .getMap();

            SupportMapFragment mapFragment =  (SupportMapFragment)
                    getActivity().getSupportFragmentManager().findFragmentById(R.id.mapActiveService);

            mMap = mapFragment.getMap();


            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
                for(int i=0;i<taskinfo.getAddress().size();i++)
                {

                   mMap.addMarker(new MarkerOptions().position(new LatLng(Double.parseDouble(taskinfo.address.get(i).getLat()),Double.parseDouble(taskinfo.address.get(i).getLongitude()))).title(taskinfo.address.get(i).getDireccion()));

                }

            }
        }
    }

    private void setUpMap() {

        ArrayList<Marker> markers = new ArrayList<Marker>();

        //for (int i = 0; i < service.getDirecciones().size(); i++) {


          //  Marker m = mMap.addMarker(new MarkerOptions().position(new LatLng(service.getDirecciones().get(i).getLatitude(), service.getDirecciones().get(i).getLongitude())));
          //  markers.add(m);


        CameraPosition cameraPosition = new CameraPosition.Builder().target(
                new LatLng(Double.parseDouble(taskinfo.address.get(0).getLat()), Double.parseDouble(taskinfo.address.get(0).getLongitude()))).zoom(12).build();

        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));



    }
}
