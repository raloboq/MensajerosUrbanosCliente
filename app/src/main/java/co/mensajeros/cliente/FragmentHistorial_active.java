package co.mensajeros.cliente;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rene on 12/16/14.
 */
public class FragmentHistorial_active extends Fragment {


    SharedPreferences pref;
    SharedPreferences.Editor editor;
    String userid;
    String saldo;
    ArrayList<TaskObject> activeservices_list;
    ArrayList<TaskObject> historial_list;
    Active_Service_custom_list2 historial_adapter;
    Active_Service_custom_list2 active_adapter;
    public TaskInfoObject taskinfo;
    private View myFragmentView;
    public static ListView activelist;
    public static ListView historiallist;

    TextView activeNumber;

    public static FragmentHistorial_active newInstance(String param1, String param2) {
        FragmentHistorial_active fragment = new FragmentHistorial_active();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;   //task¿¿¿
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    private ArrayAdapter<String> listAdapter ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        pref = getActivity().getSharedPreferences("UrbanosPref", 0); // 0 - for private mode
        userid = pref.getString("USERID", "");
        Log.i("pref", userid);
        saldo = pref.getString("SALDO", "");





        //saldotext.setText("Créditos "+String.valueOf(saldo));
        //((main) getActivity()).changeActionBarTitle("Servicios activos");

        activeservices_list = new ArrayList<TaskObject>();
        historial_list = new ArrayList<TaskObject>();
        /*Criteria criteria = new Criteria();
        String best = mgr.getBestProvider(criteria, true);
        Location location = mgr.getLastKnownLocation(best);
        location.getLatitude();
        location.getLongitude();

        new GetHistory().execute(userid, String.valueOf(location.getLatitude()), String.valueOf(location.getLongitude()));*/





        myFragmentView = inflater.inflate(R.layout.fragment_new_historial, container, false);



        new GetActiveServices().execute(userid);


        Log.i("rene", "historial");

        if(activeservices_list!=null) {

            active_adapter = new Active_Service_custom_list2(getActivity(), activeservices_list);

            activelist = (ListView) myFragmentView.findViewById(R.id.ActiveServicelistView);
            activelist.setAdapter(active_adapter);

            activelist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    new CheckActiveServTask().execute(activeservices_list.get(i).getId());


                    //new CheckActiveServTask().execute("3217","243");
                }
            });
        }

        if(historial_list!=null) {

            historial_adapter = new Active_Service_custom_list2(getActivity(), historial_list);

            historiallist = (ListView) myFragmentView.findViewById(R.id.historial_service_list);
            historiallist.setAdapter(historial_adapter);

            historiallist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    new CheckActiveServTask().execute(activeservices_list.get(i).getId());


                    //new CheckActiveServTask().execute("3217","243");
                }
            });
        }


        return myFragmentView;

    }


    private class GetActiveServices extends AsyncTask<String, String, JSONArray> {
        private ProgressDialog pDialog;
        String user,pass;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();



            Log.i("login",user+" "+pass);
            pDialog = new ProgressDialog(getActivity());
            pDialog.setTitle("Cargando servicios activos");
            pDialog.setMessage("Conectando...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }
        @Override
        protected JSONArray doInBackground(String... args) {
            UserFunctions userFunction = new UserFunctions();
            JSONArray json = userFunction.ListActiveServ(args[0]);
//            Log.i("cassig","json = "+json.toString());
            return json;
        }
        @Override
        protected void onPostExecute(JSONArray jsonAll) {
            try {

                int activos=0;

                activeNumber = (TextView)myFragmentView.findViewById(R.id.ActiveNumber);
                activeNumber.setBackgroundResource(R.drawable.circle_shape_red);



                for (int i = 0;i<jsonAll.length();i++){

                    JSONObject json = (JSONObject)jsonAll.get(i);

                    Log.i("fhisto",json.toString());
                    //JSONObject object2 = (JSONObject) json.get("lista");
                    // if(!json.isNull(json.getString("lista"))) {
                    //if(json.length()>0) {
//                    saldotext.setText(json.get("saldo").toString());
                    //JSONArray listserv = json.getJSONArray("lista");
                    //                  saldotext.setText(json.get("saldo").toString());


                    // for (int i = 0; i < listserv.length(); i++) {

                    //   JSONObject object = (JSONObject) listserv.get(i);


                    //Log.i("cassig","object "+object2.toString());

                    TaskObject atask = new TaskObject();


                    atask.setId(json.getString("id"));
                    atask.setType(json.getString("type_task_id")); //revisar
                    atask.setValor(json.getString("valor_total"));
                    atask.setStatus(json.getString("estado"));

                    //atask.setDistancia(object.getString("distancia"));

                    //atask.setTaskplaces(json.getString("address"));
                    //atask.setComision(object.getString("comision"));
                    //atask.setDistancia(object.getString("distancia"));
                    //atask.setIda(object.getString("ida"));

                    atask.setHora_recogida(json.getString("hora_inicio"));
                    atask.setFecha_recogida(json.getString("fecha_inicio"));
                    //Log.i("cassig", "comision=" + atask.getId());


                    if(json.getString("estado").equals("3")) {  //revisar estado activo

                        activeservices_list.add(atask);

                        active_adapter.notifyDataSetChanged();
                        activos++;
                        activeNumber.setText(activos+"");
                    }

                    if(json.getString("estado").equals("1")) {//revisar estado terminado

                        historial_list.add(atask);

                        historial_adapter.notifyDataSetChanged();
                    }
                    // }
                    //}
                    activeNumber.setText(activos+"");
                    pDialog.dismiss();
                    //}
                }

            }catch(Exception e){

                try {
//                    editor.putString("SALDO", (json.get("saldo").toString()));
                    //   saldotext.setText(json.get("saldo").toString());
                } catch (Exception e1) {
                    e1.printStackTrace();
                    Toast.makeText(getActivity(), "Error conectando con el servidor", Toast.LENGTH_SHORT).show();
                }
                //saldotext.setText("Créditos " + jsonchecktask.get("saldo").toString());

                e.printStackTrace();
                pDialog.dismiss();
            }
        }

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

            return json4;
        }

        @Override
        protected void onPostExecute(JSONObject json) {

            try {

                taskinfo = new TaskInfoObject();

                JSONObject recurso = new JSONObject(json.getString("recurso"));

                Log.i("actives",recurso.getString("nombre"));
                taskinfo.setNombre_mensajero(recurso.getString("nombre"));
                taskinfo.setPlaca_mensajero(recurso.getString("placa"));
                taskinfo.setCelular_mensajero(recurso.getString("celular"));
                taskinfo.setCedula_mensajero(recurso.getString("did"));


                JSONObject detalles = new JSONObject(json.getString("detalle"));

                taskinfo.setId(detalles.getString("id"));
                taskinfo.setEstado_task(detalles.getString("estado"));
                taskinfo.setDistancia(detalles.getString("distancia"));
                taskinfo.setValor_total(detalles.getString("valor_total"));
                taskinfo.setFecha_asignado(detalles.getString("fecha_inicio"));
                taskinfo.setFecha_creacion(detalles.getString("date_created"));
                taskinfo.setFecha_finalizado(detalles.getString("fecha_final"));


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
