package co.mensajeros.cliente;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import co.mensajeros.cliente.Utils.SlidingTabLayout;

/**
 * Created by Rene on 9/5/14.
 */
public class NewService_3 extends Fragment implements OnNextClicked, OnPageChangedL {

    private static final String ARG_SECTION_NUMBER = "section_number";

    SharedPreferences pref;
    SharedPreferences.Editor editor;

    Gson gson;
    String USERNAME = "";
    SharedPreferences userpref;
    String jsonpaso2;

    public static NewService_3 newInstance(int sectionNumber) {
        NewService_3 fragment = new NewService_3();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public NewService_3() {
    }

    ServiceObject service = new ServiceObject();
    EditText valorDeclarado;
    EditText descripcion;
    Typeface font1;
    Typeface font2;
    TextView Descripcionl;
    TextView Valordeclaradol;
    String TASKTYPE;
    SharedPreferences tasktype;

    //TextView promcodel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_new_service3, container, false);
        //Bundle extras = getArguments();
        pref = getActivity().getSharedPreferences("MURBANOS", 0); // 0 - for private mode
        editor = pref.edit();
        gson = new Gson();


        userpref = getActivity().getSharedPreferences("UrbanosPref", 0); // 0 - for private mode
        USERNAME = userpref.getString("USERNAME", "");

        jsonpaso2 = pref.getString("MyObject", "");
        service = gson.fromJson(jsonpaso2, ServiceObject.class);
//        Log.i("hora","s3 "+service.getHora_recogida());

        tasktype = getActivity().getSharedPreferences("TASKTYPE",0);
        TASKTYPE = tasktype.getString("TaskType","");



        TextView Username = (TextView)rootView.findViewById(R.id.UserName);
        Username.setText(USERNAME);


        main.setOnNextClickListener(this);
        SlidingTabLayout slide = new SlidingTabLayout(getActivity());
        slide.setOnPageChangedL(this);

        font2 = Typeface.createFromAsset(getActivity().getAssets(), "Raleway-Medium.ttf");
        font1 = Typeface.createFromAsset(getActivity().getAssets(), "Raleway-Regular.ttf");

        //service= (ServiceObject) extras.getSerializable("serviceobject");

        Descripcionl = (TextView)rootView.findViewById(R.id.Descripcionlabel);
        Descripcionl.setTypeface(font1);

        Valordeclaradol = (TextView)rootView.findViewById(R.id.valor_declaradolabel);
        Valordeclaradol.setTypeface(font1);

        //promcodel = (TextView)rootView.findViewById(R.id.promcodelabel);
        //promcodel.setTypeface(font1);








       // for(int i = 0; i<service.getDirecciones().size();i++)
      //      Log.i("direcciones", service.getDirecciones().get(i));

        valorDeclarado = (EditText)rootView.findViewById(R.id.valor_declarado);
        valorDeclarado.setTypeface(font1);


        descripcion = (EditText)rootView.findViewById(R.id.descripcion);
        descripcion.setTypeface(font1);




        //CheckBox idayvuelta = (CheckBox)rootView.findViewById(R.id.idavuelta);
        //idayvuelta.setTypeface(font1);

/*        if(idayvuelta.isEnabled())  QUITADO PARA QUE SIRVA EL DRAWER
        service.setIdayVuelta("1");
        else
            service.setIdayVuelta("0");*/

        Button next = (Button)rootView.findViewById(R.id.Next3);
        next.setTypeface(font2);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Log.i("type","paso "+ service.getTipo_servicio());

                if(descripcion.getText().length()==0){
                    DialogFragment dialog = new UserAlertDialogFragment();
                    Bundle args = new Bundle();
                    args.putString("title", "Direccion");
                    args.putString("message","la descripción no puede estar vacia");
                    dialog.setArguments(args);
                    dialog.setTargetFragment(dialog, 0);
                    dialog.show(getFragmentManager(), "tag");
                }
                else {

                    /*pref = getActivity().getSharedPreferences("MURBANOS", 0); // 0 - for private mode
                    editor = pref.edit();
                    gson = new Gson();*/

                    //jsonpaso2 = pref.getString("MyObject", "");
                    //Log.i("jsonn",jsonpaso2+"   zzzz");
                    //service = gson.fromJson(jsonpaso2, ServiceObject.class);

                    service.setValor_declarado("100");
                    service.setDescripcion("aaaaaaaa");
                    service.setTipo_servicio(TASKTYPE);

                 //   Log.i("tipoo","s3 "+service.getHora_recogida()+" "+service.getTipo_servicio());

                    new CheckPrice().execute(service);
                }




            }
        });



        return rootView;
    }

    @Override
    public void nextclicked() {

        String json2 = pref.getString("MyObject", "");
        service = gson.fromJson(json2, ServiceObject.class);

        if(descripcion.getText().length()==0){
            DialogFragment dialog = new UserAlertDialogFragment();
            Bundle args = new Bundle();
            args.putString("title", "Direccion");
            args.putString("message","la descripción no puede estar vacia");
            dialog.setArguments(args);
            dialog.setTargetFragment(dialog, 0);
            dialog.show(getActivity().getSupportFragmentManager(), "tag");
        }
        else {

            service.setValor_declarado(valorDeclarado.getText().toString());
            service.setDescripcion(descripcion.getText().toString());

            String json = gson.toJson(service);
            editor.putString("MyObject", json).commit();

            new CheckPrice().execute(service);
        }
    }

    @Override
    public int onpagechange(int pos) {

        Log.i("page","cccc"+pos);

        pref = getActivity().getSharedPreferences("MURBANOS", 0); // 0 - for private mode
        editor = pref.edit();


        String json = pref.getString("MyObject", "");
        service = gson.fromJson(json, ServiceObject.class);

        return 0;
    }

    private class CheckPrice extends AsyncTask<ServiceObject, String, JSONObject> {


        private ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();



            pDialog = new ProgressDialog(getActivity());
            pDialog.setTitle("Contactando");
            pDialog.setMessage("Calculando valor ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();


        }
        @Override
        protected JSONObject doInBackground(ServiceObject... args) {
            UserFunctions userFunction2 = new UserFunctions();
            //Log.i("address","arg="+args[1]);
            JSONObject json2 = userFunction2.CHeckPrice(args[0]);
            Log.i("checkp",json2.toString());
           // addresspos=Integer.parseInt(args[0]);

            return json2;
        }
        @Override
        protected void onPostExecute(JSONObject json3) {


            try {
                service.setValor_servicio(json3.getString("valor_servicio"));
                service.setRecargo_ida_vuelta(json3.getString("recargo_ida_vuelta"));
                service.setRecargo_distancia(json3.getString("recargo_distancia"));
                service.setRecargo_paradas(json3.getString("recargo_paradas"));
                service.setValor_base(json3.getString("valor_base"));
                service.setParadas(json3.getString("paradas"));
                service.setRecargos_totales(json3.getString("recargos_totales"));
                service.setRecargo_seguro(json3.getString("recargo_seguro"));
                service.setIdayVuelta(json3.getString("ida_vuelta"));
                service.setDistancia_total(json3.getString("distancia_total"));
                service.setValor_declarado(json3.getString("valor_declarado"));

                String json = gson.toJson(service);
                editor.putString("MyObject", json).commit();


            } catch (Exception e) {
                e.printStackTrace();
            }

          /*  DialogFragment dialog = new UserAlertDialogFragment();
            Bundle args2 = new Bundle();
            args2.putString("title", "Costo");
            args2.putString("message",json3.toString());
            dialog.setArguments(args2);
            dialog.setTargetFragment(dialog, 0);
            dialog.show(getFragmentManager(), "tag");
            String[] cordinates=null;*/

            //Bundle data = new Bundle();
            //data.putSerializable("serviceobject",service);
            NewService4 nservice4 = new NewService4();
            String json = gson.toJson(service);
            editor.putString("MyObject", json).commit();
            //nservice4.setArguments(data);
            getActivity().getSupportFragmentManager().beginTransaction()
                    .add(R.id.content_frame, nservice4)
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
