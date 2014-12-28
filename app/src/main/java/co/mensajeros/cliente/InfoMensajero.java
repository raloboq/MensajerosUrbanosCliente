package co.mensajeros.cliente;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by rene on 11/5/14.
 */
public class InfoMensajero extends Fragment {



    public InfoMensajero(){

    }

    Typeface font1;
    Typeface font2;
    private GoogleMap mMap; // Might be null if Google Play services APK is not available.

    TaskInfoObject taskinfo = new TaskInfoObject();
    ArrayList<DetailsTaskObject> itemlist = new ArrayList<DetailsTaskObject>();

    Bitmap bitmap;
    ProgressDialog pDialog;
    ImageView foto;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_info_recurso, container, false);
        Bundle extras = getArguments();

        taskinfo= (TaskInfoObject) extras.getSerializable("taskinfo");

        font2 = Typeface.createFromAsset(getActivity().getAssets(), "Raleway-Medium.ttf");
        font1 = Typeface.createFromAsset(getActivity().getAssets(), "Raleway-Regular.ttf");

        //service= (ServiceObject) extras.getSerializable("serviceobject");

        TextView Nombre = (TextView)rootView.findViewById(R.id.NOmbreRecurso);
        TextView Cedula = (TextView)rootView.findViewById(R.id.CedulaRecurso);
        TextView Placa = (TextView)rootView.findViewById(R.id.PlacaMotoRecurso);
        Button contactar = (Button)rootView.findViewById(R.id.contactRecurso);

        contactar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + taskinfo.getCelular_mensajero()));
                startActivity(callIntent);


            }
        });

        foto = (ImageView)rootView.findViewById(R.id.imageRecurso);

        Nombre.setText(taskinfo.getNombre_mensajero());  // tomar la distancia en el paso anterior
        Cedula.setText("C.C. "+taskinfo.getCedula_mensajero()); // tomat valor en el paso anterior
        Placa.setText("Placa: "+taskinfo.getPlaca_mensajero());

        new LoadImage().execute("http://networkcultures.org/unlikeus/wp-content/uploads/sites/2/2012/03/who1.png");




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

    private class LoadImage extends AsyncTask<String, String, Bitmap> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //pDialog = new ProgressDialog(getActivity());
            //pDialog.setMessage("Loading Image ....");
            //pDialog.show();
        }
        protected Bitmap doInBackground(String... args) {
            try {
                bitmap = BitmapFactory.decodeStream((InputStream) new URL(args[0]).getContent());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }
        protected void onPostExecute(Bitmap image) {
            if(image != null){
                foto.setImageBitmap(image);
                //pDialog.dismiss();
            }else{
                //pDialog.dismiss();
                Toast.makeText(getActivity(), "Image Does Not exist or Network Error", Toast.LENGTH_SHORT).show();
            }
        }
    }






}
