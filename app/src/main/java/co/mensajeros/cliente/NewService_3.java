package co.mensajeros.cliente;

import android.app.ProgressDialog;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Rene on 9/5/14.
 */
public class NewService_3 extends Fragment {

    public NewService_3() {
    }

    ServiceObject service = new ServiceObject();
    EditText valorDeclarado;
    EditText descripcion;
    Typeface font1;
    Typeface font2;
    TextView Descripcionl;
    TextView Valordeclaradol;
    TextView promcodel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_new_service3, container, false);
        Bundle extras = getArguments();

        font2 = Typeface.createFromAsset(getActivity().getAssets(), "Raleway-Medium.ttf");
        font1 = Typeface.createFromAsset(getActivity().getAssets(), "Raleway-Regular.ttf");

        service= (ServiceObject) extras.getSerializable("serviceobject");

        Descripcionl = (TextView)rootView.findViewById(R.id.Descripcionlabel);
        Descripcionl.setTypeface(font1);

        Valordeclaradol = (TextView)rootView.findViewById(R.id.valor_declaradolabel);
        Valordeclaradol.setTypeface(font1);

        promcodel = (TextView)rootView.findViewById(R.id.promcodelabel);
        promcodel.setTypeface(font1);








       // for(int i = 0; i<service.getDirecciones().size();i++)
      //      Log.i("direcciones", service.getDirecciones().get(i));

        valorDeclarado = (EditText)rootView.findViewById(R.id.valor_declarado);
        valorDeclarado.setTypeface(font1);


        descripcion = (EditText)rootView.findViewById(R.id.descripcion);
        descripcion.setTypeface(font1);




        CheckBox idayvuelta = (CheckBox)rootView.findViewById(R.id.idavuelta);
        idayvuelta.setTypeface(font1);
        service.setIdayVuelta(idayvuelta.isEnabled()+"");

        Button next = (Button)rootView.findViewById(R.id.Next3);
        next.setTypeface(font2);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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

                    service.setValor_declarado(valorDeclarado.getText().toString());
                    service.setDescripcion(descripcion.getText().toString());

                    new CheckPrice().execute(service);
                }




            }
        });



        return rootView;
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
            } catch (JSONException e) {
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

            Bundle data = new Bundle();
            data.putSerializable("serviceobject",service);
            NewService4 nservice4 = new NewService4();
            nservice4.setArguments(data);
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, nservice4)
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
