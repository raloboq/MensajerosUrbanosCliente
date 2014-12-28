package co.mensajeros.cliente;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import co.mensajeros.cliente.Utils.ViewPagerFragment;

/**
 * Created by rene on 11/4/14.
 */
public class NewService5 extends Fragment{



    public NewService5() {
    }

    Typeface font1;
    Typeface font2;
    ServiceObject service = new ServiceObject();
    SharedPreferences pref3;
    SharedPreferences.Editor editor3;
    Gson gson;
    String jsonn;

    public TaskInfoObject taskinfo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_new_service5, container, false);
        Bundle extras = getArguments();

        font2 = Typeface.createFromAsset(getActivity().getAssets(), "Raleway-Medium.ttf");
        font1 = Typeface.createFromAsset(getActivity().getAssets(), "Raleway-Regular.ttf");

        //service= (ServiceObject) extras.getSerializable("serviceobject");

        pref3 = getActivity().getSharedPreferences("MURBANOS", 0); // 0 - for private mode
        editor3 = pref3.edit();
        gson = new Gson();

        jsonn = pref3.getString("MyObject", "");
        service = gson.fromJson(jsonn, ServiceObject.class);

        Log.i("ser5","b"+service.getHora_recogida()+" "+service.getFecha_creacion()+" "+service.getFecha_recogida()+" "+service.getId());
        Log.i("tipoo","s5 "+service.getHora_recogida()+" "+service.getTipo_servicio());

        TextView fechaCreacion = (TextView)rootView.findViewById(R.id.fecha_creacion);
        fechaCreacion.setTypeface(font2);

        fechaCreacion.setText(service.getFecha_creacion());


        TextView fechaInicio = (TextView)rootView.findViewById(R.id.fecha_inicio);
        fechaInicio.setTypeface(font2);

        fechaInicio.setText(service.getFecha_recogida()+" "+service.getHora_recogida());




        TextView servicioID = (TextView)rootView.findViewById(R.id.servicio_id);
        servicioID.setTypeface(font2);

        servicioID.setText(service.getId());

        TextView tipoServicio = (TextView)rootView.findViewById(R.id.tiposervicio);

        if(service.getTipo_servicio().equals("1"))
        {
            tipoServicio.setText("Express");
        }
        if(service.getTipo_servicio().equals("2"))
        {
            tipoServicio.setText("Estandar");
        }
        if(service.getTipo_servicio().equals("3"))
        {
            tipoServicio.setText("Diligencia");
        }

        TextView costo = (TextView)rootView.findViewById(R.id.costo);
        costo.setTypeface(font2);

        costo.setText(service.getValor_servicio());


        Button nuevoserv = (Button)rootView.findViewById(R.id.new_service1);
        nuevoserv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_frame, new ViewPagerFragment()).commit();

            }
        });

        Button verserv = (Button)rootView.findViewById(R.id.activeservices1);
        verserv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new CheckActiveServTask().execute(service.getId());

            }
        });



       // tipoServicio.setText(service.getTipo_servicio());





        return rootView;

        }




    private class CheckActiveServTask extends AsyncTask<String, String, JSONObject> {

        private ProgressDialog nDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            nDialog = new ProgressDialog(getActivity());
            nDialog.setTitle("Mensajeros Urbanos");
            nDialog.setMessage("Cargando servicio...");
            nDialog.setIndeterminate(false);
            nDialog.setCancelable(true);
            nDialog.show();

            //executed = true;

        }

        @Override
        protected JSONObject doInBackground(String... args) {

            UserFunctions userFunction = new UserFunctions();
            JSONObject json4 = userFunction.ViewService(args[0]);

            Log.i("views",json4.toString());

            return json4;
        }

        @Override
        protected void onPostExecute(JSONObject json) {

            try {

                taskinfo = new TaskInfoObject();

                if(json.getString("recurso").equals("0")){

                    taskinfo.setNombre_mensajero("No asignado");
                    taskinfo.setPlaca_mensajero("No asignado");
                    taskinfo.setCelular_mensajero("No asignado");
                    taskinfo.setCedula_mensajero("No asignado");

                }
                else{
                JSONObject recurso = new JSONObject(json.getString("recurso"));

                Log.i("actives", recurso.getString("nombre"));
                taskinfo.setNombre_mensajero(recurso.getString("nombre"));
                taskinfo.setPlaca_mensajero(recurso.getString("placa"));
                taskinfo.setCelular_mensajero(recurso.getString("celular"));
                taskinfo.setCedula_mensajero(recurso.getString("did"));
                }

                JSONObject detalles = new JSONObject(json.getString("detalle"));

                taskinfo.setId(detalles.getString("id"));
                taskinfo.setEstado_task(detalles.getString("estado"));
                taskinfo.setFecha_asignado(detalles.getString("fecha_inicio"));
                taskinfo.setFecha_creacion(detalles.getString("date_created"));
                taskinfo.setFecha_finalizado(detalles.getString("fecha_final"));
                taskinfo.setValor_total(detalles.getString("valor_total"));
                taskinfo.setDistancia(detalles.getString("distancia"));


                List<DetailsTaskObject> placeslist2 = new ArrayList<DetailsTaskObject>();
                JSONArray places = new JSONArray(json.getString("places"));



                for (int m = 0; m < places.length(); m++) {
                    JSONObject placesnumber = (JSONObject) places.get(m);
                    DetailsTaskObject detailsobject = new DetailsTaskObject();

                    detailsobject.setDireccion(placesnumber.getString("direccion"));
                    detailsobject.setId(placesnumber.getString("id"));
                    detailsobject.setLat(placesnumber.getString("lat"));
                    detailsobject.setLongitude(placesnumber.getString("long"));

                    // detailsobject.setEstado(placesnumber.getString("estado"));
                    placeslist2.add(detailsobject);
                }

                taskinfo.setAddress(placeslist2);

                Bundle data = new Bundle();

                // data.putString("taskcost", info.getString("valor_total"));
                //data.putString("USERID", Username);  revisar
                // data.putString("taskid", bobject.getTaskid());

                //editor.putString("TaskID", bobject.getTaskid()).commit();  revisar
                //Log.i("prefTask",bobject.getTaskid());
                data.putSerializable("taskinfo", taskinfo);


                android.support.v4.app.FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                InfoService infoserv = new InfoService();
                infoserv.setArguments(data);





                transaction.replace(R.id.content_frame, infoserv,"infoservice").addToBackStack("tasktaken");
                transaction.addToBackStack(null);
                transaction.commitAllowingStateLoss();


                nDialog.dismiss();


            } catch (JSONException e) {
                e.printStackTrace();


            }

        }

    }



    }
