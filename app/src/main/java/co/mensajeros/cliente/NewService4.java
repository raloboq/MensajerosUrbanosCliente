package co.mensajeros.cliente;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ScrollView;
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
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import co.mensajeros.cliente.WorkaroundMapFragment;

/**
 * Created by Rene on 9/8/14.
 */
public class NewService4 extends Fragment implements OnNextClicked {

    private static final String ARG_SECTION_NUMBER = "section_number";
    Gson gson;

    public static NewService4 newInstance(int sectionNumber) {
        NewService4 fragment = new NewService4();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public NewService4() {
    }

    ServiceObject service = new ServiceObject();
    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    CameraPosition cameraPosition;
    private static View rootView;
    public Typeface font2;
    public Typeface font1;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    SharedPreferences pref2;
    SharedPreferences.Editor editor2;
    String useridpref;
    String userpass;
    boolean anonymous_user;
    String jsonn;

    SharedPreferences taskhour;
    SharedPreferences taskdate;
    SharedPreferences tasktype;
    String TASKHOUR;
    String TASKDATE;
    String TASKTYPE;

     ScrollView scroll;

    private InputMethodManager mIMEMgr;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        main.setOnNextClickListener(this);
        //View rootView = inflater.inflate(R.layout.fragment_new_service4, container, false);
        pref = getActivity().getSharedPreferences("UrbanosPref", 0); // 0 - for private mode
        editor = pref.edit();
        useridpref = pref.getString("USERID", "");
        userpass = pref.getString("PASS", "");
        anonymous_user = pref.getBoolean("ANONYMOUS",false);



        pref2 = getActivity().getSharedPreferences("MURBANOS", 0); // 0 - for private mode
        editor2 = pref2.edit();
        gson = new Gson();

        jsonn = pref2.getString("MyObject", "");
        service = gson.fromJson(jsonn, ServiceObject.class);

        mIMEMgr = (InputMethodManager) getActivity().getSystemService(getActivity().INPUT_METHOD_SERVICE);




        taskdate = getActivity().getSharedPreferences("TASKTIME",0);
        taskhour = getActivity().getSharedPreferences("TASKHOUR",0);
        tasktype = getActivity().getSharedPreferences("TASKTYPE",0);
        TASKTYPE = tasktype.getString("TaskType","");
        TASKDATE = taskdate.getString("TaskDate","pp");
        TASKHOUR = taskhour.getString("TaskHour","aa");

        service.setFecha_recogida(TASKDATE);
        service.setHora_recogida(TASKHOUR);
        service.setTipo_servicio(TASKTYPE);

        Log.i("tdate",TASKDATE+" "+TASKHOUR);


        Log.i("listdir","num= "+service.getDirecciones().size());

        Log.i("hora","s4 "+service.getHora_recogida());

        Log.i("shared","tipo 4 "+service.getDirecciones().get(0).getLatitude());


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

         scroll = (ScrollView)rootView.findViewById(R.id.scrollView4);

        mIMEMgr.hideSoftInputFromWindow(rootView.findViewById(R.id.scrollView4).getWindowToken(), 0);




        ///// lo quite porque no se

        /*Bundle extras = getArguments();

        if(extras.containsKey("serviceobjectregister")) // FALTA REVISAR ESTO
        {
            editor.putBoolean("PENDIENTE",false).commit();
            editor.putBoolean("ANONYMOUS",false).commit();
            anonymous_user=false;
            service = (ServiceObject) extras.getSerializable("serviceobjectregister");
            Log.i("serv","nservice4"+ service.getDescripcion());
        }
        if(extras.containsKey("serviceobject"))
        else
        {
            //service = (ServiceObject) extras.getSerializable("serviceobject");


            String json2 = pref.getString("MyObject", "");
            service = gson.fromJson(json2, ServiceObject.class);
//            Log.i("serv","nservice4"+ service.getDescripcion());
        }*/

        Bundle extra = getArguments();
        if(!anonymous_user){

            editor.putBoolean("PENDIENTE",false).commit();
            editor.putBoolean("ANONYMOUS",false).commit();
            //anonymous_user=false;
            Log.i("serv","nservice4"+ service.getDescripcion());


        }





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
        TextView checboxida = (TextView) rootView.findViewById(R.id.checkBoxida);

        TextView recargo_distancia = (TextView)rootView.findViewById(R.id.recargo_distancia);
        TextView recargo_paradas = (TextView)rootView.findViewById(R.id.recargo_paradas);
        TextView recargo_seguro = (TextView)rootView.findViewById(R.id.recargo_seguro);
        TextView ida_vuelta = (TextView)rootView.findViewById(R.id.ida_vuelta);
        TextView dist = (TextView)rootView.findViewById(R.id.dist);

        LinearLayout s1 = (LinearLayout)rootView.findViewById(R.id.linears1);
        LinearLayout s2 = (LinearLayout)rootView.findViewById(R.id.linears2);
        LinearLayout s3 = (LinearLayout)rootView.findViewById(R.id.linears3);
        LinearLayout s4 = (LinearLayout)rootView.findViewById(R.id.linears4);
        LinearLayout s5 = (LinearLayout)rootView.findViewById(R.id.linears5);


        if(service.getRecargo_distancia().equals("0"))
            s1.setVisibility(View.GONE);
        else
        recargo_distancia.setText(service.getRecargo_distancia());
        //recargo_distancia.setTypeface(font1);
        if(service.getRecargo_paradas().equals("0"))
            s2.setVisibility(View.GONE);
        else
        recargo_paradas.setText(service.getRecargo_paradas());
        //recargo_paradas.setTypeface(font1);
        if(service.getRecargo_seguro().equals("0"))
            s3.setVisibility(View.GONE);
        else
        recargo_seguro.setText(service.getRecargo_seguro());
        //recargo_seguro.setTypeface(font1);
        if(service.getRecargo_ida_vuelta().equals("0"))
            s4.setVisibility(View.GONE);
        else
            ida_vuelta.setText(service.getRecargo_ida_vuelta());
        //ida_vuelta.setTypeface(font1);
        if(service.getDistancia_total().equals("0"))
            s5.setVisibility(View.GONE);
        else
        dist.setText(service.getDistancia_total()+" Km");
        //dist.setTypeface(font1);




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

        TextView ts1 = (TextView)rootView.findViewById(R.id.textViews1);
        ts1.setTypeface(font1);
        TextView ts2 = (TextView)rootView.findViewById(R.id.textViews2);
        ts2.setTypeface(font1);
        TextView ts3 = (TextView)rootView.findViewById(R.id.textViews3);
        ts3.setTypeface(font1);
        TextView ts4 = (TextView)rootView.findViewById(R.id.textViews4);
        ts4.setTypeface(font1);
        TextView ts5 = (TextView)rootView.findViewById(R.id.textViews5);
        ts5.setTypeface(font1);



       // resumenTipo.setText(service.getTipo_servicio());


        resumenFecha.setText(TASKDATE+" "+TASKHOUR);
        resumenDireccionE.setText(service.getDirecciones().get(1).getAddress().toString());
        resumenDireccionR.setText(service.getDirecciones().get(0).getAddress().toString());
      //  resumenDescripcion.setText(service.getDescripcion());
//        resumenValor.setText(service.getValor_declarado());
        valorserv.setText(service.getValor_servicio());
      //  resumenCiudad.setText(service.getCiudad());
        Log.i("ida","ida = "+service.getIdayVuelta());
        if (service.getIdayVuelta().equals("1"))
            checboxida.setText("Si");
        else
            checboxida.setText("No");


        //resumenIdaVuelta.setText(service.getIdayVuelta());

/*        for (int i = 0; i < service.getDirecciones().size(); i++) {
            Log.i("resumen", "dir=" + service.getDirecciones().get(i).getAddress().toString());
            Log.i("resumen", "dir=" + service.getDirecciones().get(i).getLongitude());

        }*/


        Button solicitar = (Button) rootView.findViewById(R.id.Solicitarbutton);
        solicitar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //service = gson.fromJson(jsonn, ServiceObject.class);

                Log.i("type","ultimo "+ service.getTipo_servicio());


//                new BUY().execute(service);


                if(!anonymous_user){
                    new BUY().execute(service);
                    Log.i("pruebas",service.getDescripcion());
                }
                else{
                    editor.putBoolean("PENDIENTE",true).commit();
                    Log.i("pendiente",true+"");
                    Intent i = new Intent(getActivity(),Registro.class);
                    i.putExtra("serviceobject",service);
                    startActivity(i);
                }

            }
        });



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

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        try {
            SupportMapFragment f = (SupportMapFragment) getFragmentManager()
                    .findFragmentById(R.id.map);
            NewService4 nservice4 = new NewService4();


            if (f != null) {
                getFragmentManager().beginTransaction().remove(f).commit();
                getFragmentManager().beginTransaction().remove(nservice4).commit();

            }
        }
        catch(Exception ex){
                Log.i("Exception","map NewService4");
        }




    }



    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            //mMap = ((SupportMapFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.map))
            //        .getMap();

            SupportMapFragment mapFragment =  (SupportMapFragment)
                    getActivity().getSupportFragmentManager().findFragmentById(R.id.map);

            //mMap = mapFragment.getMap();

            mMap = ((WorkaroundMapFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.map)).getMap();

            ((WorkaroundMapFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.map)).setListener(new WorkaroundMapFragment.OnTouchListener() {
                @Override
                public void onTouch() {
                    scroll.requestDisallowInterceptTouchEvent(true);
                }
            });
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();

                for (int i = 0; i < service.getDirecciones().size(); i++) {


                    mMap.addMarker(new MarkerOptions().position(new LatLng(service.getDirecciones().get(i).getLatitude(), service.getDirecciones().get(i).getLongitude())).title(service.getDirecciones().get(i).getAddress()));
                   // markers.add(m);

                    Log.i("listdir","addr= "+service.getDirecciones().size()+" "+service.getDirecciones().get(i).getAddress());




                }
            }


        }
    }

    private void setUpMap() {

        mMap.clear();

        ArrayList<Marker> markers = new ArrayList<Marker>();



        CameraPosition cameraPosition = new CameraPosition.Builder().target(
                new LatLng(service.getDirecciones().get(0).getLatitude(), service.getDirecciones().get(0).getLongitude())).zoom(12).build();

        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));




        /* codigo del centrado que creo q servia pero en el grande

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
                        mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 0));
                    }
                });
            }
        }
*/

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

    @Override
    public void nextclicked() {


        if(!anonymous_user){
        new BUY().execute(service);
        Log.i("pruebas",service.getDescripcion());
        }
        else{
            editor.putBoolean("PENDIENTE",true).commit();
            Log.i("pendiente",true+"");
            Intent i = new Intent(getActivity(),Registro.class);
            i.putExtra("serviceobject",service);
            startActivity(i);
        }
    }




    private class BUY extends AsyncTask<ServiceObject, String, JSONObject> {


        private ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pDialog = new ProgressDialog(getActivity());
            pDialog.setTitle("Contactando");
            pDialog.setMessage("Comprando el servicio ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();


        }
        @Override
        protected JSONObject doInBackground(ServiceObject... args) {
            UserFunctions userFunction2 = new UserFunctions();
            //Log.i("address","arg="+args[1]);
            JSONObject json2 = userFunction2.Buy(args[0],useridpref,userpass);
            Log.i("checkp",json2.toString());
            // addresspos=Integer.parseInt(args[0]);

            return json2;
        }
        @Override
        protected void onPostExecute(JSONObject json3) {


            try {
                if(json3.getString("respuesta").equals("OK")){

                service.setId(json3.getString("id_servicio"));
                service.setFecha_creacion(json3.getString("fecha"));
                }
                else{



                }
                //Log.i("hora","s4 "+service.getHora_recogida()+" "+service.getId());

            } catch (Exception e) {
                e.printStackTrace();
            }

            //Log.i("ser5","a"+service.getId()+" "+service.getFecha_creacion());
            //Log.i("ser5","a c"+service.getHora_recogida()+" "+service.getFecha_creacion()+" "+service.getFecha_recogida()+" "+service.getId());

          /*  DialogFragment dialog = new UserAlertDialogFragment();
            Bundle args2 = new Bundle();
            args2.putString("title", "Costo");
            args2.putString("message",json3.toString());
            dialog.setArguments(args2);
            dialog.setTargetFragment(dialog, 0);
            dialog.show(getFragmentManager(), "tag");
            String[] cordinates=null;*/

            String json4 = gson.toJson(service);
            editor2.putString("MyObject", json4).commit();



            //Bundle data = new Bundle();
            //data.putSerializable("serviceobject", service);

            //mMap.clear();
            NewService5 nservice5 = new NewService5();
            //nservice5.setArguments(data);
            getActivity().getSupportFragmentManager().beginTransaction()
                    .add(R.id.content_frame, nservice5)
                    .addToBackStack("n1")
                    .commit();


            /*try {
                if (json3.getString("type").equals("Point")) {


                }




            } catch (JSONException e) {
                e.printStackTrace();
            }*/



            pDialog.dismiss();


        }

    }




}