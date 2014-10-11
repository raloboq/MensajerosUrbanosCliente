package co.mensajeros.cliente;

import android.app.ProgressDialog;
import android.content.Intent;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Rene on 9/3/14.
 */
public class NewService_2 extends Fragment implements View.OnFocusChangeListener, OnDirectionChecked {

    public NewService_2() {
    }


    ServiceObject service = new ServiceObject();
    ArrayList<EditText> AddresList = new ArrayList<EditText>();
    ArrayList<Direcciones> direcciones = new ArrayList<Direcciones>();

    EditText dirRecogida;
    ImageView dirRecogidaIcon;
    EditText dirEntrega;
    ImageView dirEntregaIcon;

    int ListIndex=0;
    int actualResId;
    int actualResIdIcon;
    int addresspos;

    EditText direccion3;
    EditText direccion4;
    EditText direccion5;
    EditText direccion6;
    EditText direccion7;
    EditText direccion8;
    EditText direccion9;
    EditText direccion10;
    EditText direccion11;
    EditText direccion12;
    EditText direccion13;
    EditText direccion14;
    EditText direccion15;
    EditText direccion16;
    EditText direccion17;
    EditText direccion18;
    EditText direccion19;
    EditText direccion20;

    int totalapproved=0;
    String ultima;

    boolean asyncfinsh=false;
    boolean usernext = false;
    public OnDirectionChecked DirectionChecked;
    Button removedirections;
    Button moredirections;
    Typeface font1;
    Typeface font2;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_new_service2, container, false);

        font2 = Typeface.createFromAsset(getActivity().getAssets(), "Raleway-Medium.ttf");
        font1 = Typeface.createFromAsset(getActivity().getAssets(), "Raleway-Regular.ttf");

        direccion3 = (EditText)rootView.findViewById(R.id.dirEntregaText3);
        direccion3.setOnFocusChangeListener(this);

        direccion4 = (EditText)rootView.findViewById(R.id.dirEntregaText4);
        direccion4.setOnFocusChangeListener(this);
        direccion5 = (EditText)rootView.findViewById(R.id.dirEntregaText5);
        direccion5.setOnFocusChangeListener(this);
        direccion6 = (EditText)rootView.findViewById(R.id.dirEntregaText6);
        direccion6.setOnFocusChangeListener(this);
        direccion7 = (EditText)rootView.findViewById(R.id.dirEntregaText7);
        direccion7.setOnFocusChangeListener(this);
        direccion8 = (EditText)rootView.findViewById(R.id.dirEntregaText8);
        direccion8.setOnFocusChangeListener(this);
        direccion9 = (EditText)rootView.findViewById(R.id.dirEntregaText9);
        direccion9.setOnFocusChangeListener(this);
        direccion10 = (EditText)rootView.findViewById(R.id.dirEntregaText10);
        direccion10.setOnFocusChangeListener(this);
        direccion11 = (EditText)rootView.findViewById(R.id.dirEntregaText11);
        direccion11.setOnFocusChangeListener(this);
        direccion12 = (EditText)rootView.findViewById(R.id.dirEntregaText12);
        direccion12.setOnFocusChangeListener(this);
        direccion13 = (EditText)rootView.findViewById(R.id.dirEntregaText13);
        direccion13.setOnFocusChangeListener(this);
        direccion14 = (EditText)rootView.findViewById(R.id.dirEntregaText14);
        direccion14.setOnFocusChangeListener(this);
        direccion15 = (EditText)rootView.findViewById(R.id.dirEntregaText15);
        direccion15.setOnFocusChangeListener(this);
        direccion16 = (EditText)rootView.findViewById(R.id.dirEntregaText16);
        direccion16.setOnFocusChangeListener(this);
        direccion17 = (EditText)rootView.findViewById(R.id.dirEntregaText17);
        direccion17.setOnFocusChangeListener(this);
        direccion18 = (EditText)rootView.findViewById(R.id.dirEntregaText18);
        direccion18.setOnFocusChangeListener(this);
        direccion19 = (EditText)rootView.findViewById(R.id.dirEntregaText19);
        direccion19.setOnFocusChangeListener(this);
        direccion20 = (EditText)rootView.findViewById(R.id.dirEntregaText20);
        direccion20.setOnFocusChangeListener(this);

        AddresList.add(direccion3);
        AddresList.add(direccion4);
        AddresList.add(direccion5);
        AddresList.add(direccion6);
        AddresList.add(direccion7);
        AddresList.add(direccion8);
        AddresList.add(direccion9);
        AddresList.add(direccion10);
        AddresList.add(direccion11);
        AddresList.add(direccion12);
        AddresList.add(direccion13);
        AddresList.add(direccion14);
        AddresList.add(direccion15);
        AddresList.add(direccion16);
        AddresList.add(direccion17);
        AddresList.add(direccion18);
        AddresList.add(direccion19);
        AddresList.add(direccion20);




        Bundle extras = getArguments();

        service= (ServiceObject) extras.getSerializable("serviceobject");
      //  Log.i("service",service.getFecha_recogida());

        Spinner city = (Spinner)rootView.findViewById(R.id.cityspinner);

        service.setCiudad(city.getPrompt().toString());


        dirRecogida = (EditText)rootView.findViewById(R.id.dirRecogidaText);
        dirRecogida.setTypeface(font1);
        dirRecogidaIcon = (ImageView)rootView.findViewById(R.id.dirRecogidaIcon);
        dirRecogidaIcon.setBackgroundResource(R.drawable.edit_gray);


        dirEntrega = (EditText)rootView.findViewById(R.id.dirEntregaText);
        dirEntrega.setTypeface(font1);
        dirEntregaIcon = (ImageView)rootView.findViewById(R.id.dirEntregaIcon);
        dirEntregaIcon.setBackgroundResource(R.drawable.edit_gray);

        TextView dirRecogidat = (TextView)rootView.findViewById(R.id.dirRecogida);
        dirRecogidat.setTypeface(font1);

        TextView dirEntregat = (TextView)rootView.findViewById(R.id.dirEntrega);
        dirEntregat.setTypeface(font1);

        dirRecogida.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {

                if(!b&&!dirRecogida.getText().toString().isEmpty()) {
                    actualResId=dirRecogida.getId();
                    actualResIdIcon=dirRecogidaIcon.getId();
                    new CheckAddress().execute("0",dirRecogida.getText().toString());
                    //RevisarAddress("0",dirRecogida.getText().toString());
                }
                if(!b&&dirRecogida.getText().toString().isEmpty()){

                    Log.i("direccion","alerta recogida vacia");
                    DialogFragment dialog = new UserAlertDialogFragment();
                    Bundle args = new Bundle();
                    args.putString("title", "Alerta");
                    args.putString("message", "La dirección de recogida esta vacia");
                    dialog.setArguments(args);
                    dialog.setTargetFragment(dialog,0);
                    dialog.show(getFragmentManager(), "tag");
                }
            }
        });

        dirEntrega.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {

                if(!b&&!dirEntrega.getText().toString().isEmpty()) {
                    ultima="1";
                    actualResId=dirEntrega.getId();
                    actualResIdIcon=dirEntregaIcon.getId();
                    new CheckAddress().execute("1",dirEntrega.getText().toString(), ultima);
                    //RevisarAddress("1",dirEntrega.getText().toString());
                }
                if(!b&&dirEntrega.getText().toString().isEmpty()){

                    Log.i("direccion","alerta entrega vacia");
                    DialogFragment dialog = new UserAlertDialogFragment();
                    Bundle args = new Bundle();
                    args.putString("title", "Alerta");
                    args.putString("message", "La dirección de entrega esta vacia");
                    dialog.setArguments(args);
                    dialog.setTargetFragment(dialog,0);
                    dialog.show(getFragmentManager(), "tag");
                }

            }
        });




        //direcciones de entrega !!!!!
        moredirections = (Button)rootView.findViewById(R.id.more_directions);
        moredirections.setTypeface(font1);
        moredirections.setVisibility(View.VISIBLE);

                moredirections.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        Log.i("list","add ="+ListIndex);

                        if(ListIndex<18 && ListIndex>=0) {

                            AddresList.get(ListIndex).setVisibility(View.VISIBLE);
                            AddresList.get(ListIndex).requestFocus();
                            ListIndex++;




                        }

                    }
                });

        removedirections = (Button)rootView.findViewById(R.id.remove_direction);
        removedirections.setTypeface(font1);
        removedirections.setVisibility(View.VISIBLE);

        removedirections.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("list","remove ="+ListIndex);

                if(ListIndex>=0) {
                    if(ListIndex!=0)
                        ListIndex--;
                    AddresList.get(ListIndex).setVisibility(View.GONE);

                }
                //LinearLayout secondStepremove = (LinearLayout)rootView.findViewById(R.id.add_remove_directions);
                //LinearLayout.LayoutParams mRparamsremove = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                //secondStep.removeView();
            }
        });




        final Button next = (Button)rootView.findViewById(R.id.Next2);
        next.setTypeface(font2);

        next.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {

                if(b) {
                    usernext = true;

                    Log.i("next","click");
                }
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               // usernext=true;
                //Log.i("next","click");
                next.requestFocus();

                Log.i("next","on click next");

                if(dirRecogida.getText().toString().isEmpty()){

                    Log.i("direccion","alerta recogida vacia");
                    DialogFragment dialog = new UserAlertDialogFragment();
                    Bundle args = new Bundle();
                    args.putString("title", "Alerta");
                    args.putString("message", "La dirección de recogida esta vacia");
                    dialog.setArguments(args);
                    dialog.setTargetFragment(dialog,0);
                    dialog.show(getFragmentManager(), "tag");
                }

                if(dirEntrega.getText().toString().isEmpty()){

                    Log.i("direccion","alerta entrega vacia");
                    DialogFragment dialog = new UserAlertDialogFragment();
                    Bundle args = new Bundle();
                    args.putString("title", "Alerta");
                    args.putString("message", "La dirección de entrega esta vacia");
                    dialog.setArguments(args);
                    dialog.setTargetFragment(dialog,0);
                    dialog.show(getFragmentManager(), "tag");
                }







              /*  if(!dirEntrega.getText().equals(""))
                {
                    direcciones.add(dirEntrega.getText().toString());
                    Log.i("direccion","no alerta "+dirEntrega.getText().toString());
                }
                else
                {
                Log.i("direccion","alerta entrega vacia");
                    DialogFragment dialog = new UserAlertDialogFragment();
                    Bundle args = new Bundle();
                    args.putString("title", "Alerta");
                    args.putString("message", "La dirección de entrega esta vacia");
                    dialog.setArguments(args);
                    dialog.setTargetFragment(dialog,0);
                    //dialog.show(getFragmentManager(), "tag");
                }


                if(!dirRecogida.getText().toString().isEmpty())
                direcciones.add(dirRecogida.getText().toString());
                else {


                    Log.i("direccion", "alerta entrega vacia");
                    DialogFragment dialog = new UserAlertDialogFragment();
                    Bundle args = new Bundle();
                    args.putString("title", "Alerta");
                    args.putString("message", "La dirección de entrega esta vacia");
                    dialog.setArguments(args);
                    dialog.setTargetFragment(dialog, 0);
                    //dialog.show(getFragmentManager(), "tag");
                }

                for(int h = 0;h<ListIndex;h++) {
                    AddresList.get(ListIndex).getText().toString();
                    Log.i("direcciones","lista="+AddresList.get(h).getText().toString());
                    direcciones.add(AddresList.get(h).getText().toString());
                }

                service.setDirecciones(direcciones);


*/


     //           for(int i = 0; i<ListIndex+2;i++)
//                    Log.i("direcciones", "lista def="+service.getDirecciones().get(i).toString());


                /*service.setDirecciones(direcciones);
                int totalapproved=0;
                for(int j=0;j<direcciones.size();j++) {
                    if (direcciones.get(j).isApproved())
                    {
                        totalapproved++;
                    }

                }*/


            }
        });



        return rootView;
    }

    @Override
    public void onFocusChange(View view, boolean b) {
        Log.i("switch",view.getId()+"");
        switch(view.getId()) {



            case R.id.dirEntregaText3:
                if (!b && !direccion3.getText().toString().isEmpty()) {
                    actualResId=direccion3.getId();
                    new CheckAddress().execute("2",direccion3.getText().toString());
                    //RevisarAddress("2",direccion3.getText().toString());
                }
            break;
            case R.id.dirEntregaText4:
                if (!b && !direccion4.getText().toString().isEmpty()) {
                    actualResId=direccion4.getId();
                    new CheckAddress().execute("3",direccion4.getText().toString());
                    //RevisarAddress("3",direccion4.getText().toString());
                }
                break;
            case R.id.dirEntregaText5:
                if (!b && !direccion5.getText().toString().isEmpty()) {
                    actualResId=direccion5.getId();
                    new CheckAddress().execute("4",direccion5.getText().toString());
                    //RevisarAddress("4",direccion5.getText().toString());
                }
                break;
            case R.id.dirEntregaText6:
                if (!b && !direccion6.getText().toString().isEmpty()) {
                    actualResId=direccion6.getId();
                    new CheckAddress().execute("5",direccion6.getText().toString());
                    //RevisarAddress("5",direccion6.getText().toString());
                }
                break;case R.id.dirEntregaText7:
                if (!b && !direccion7.getText().toString().isEmpty()) {
                    actualResId=direccion7.getId();
                    new CheckAddress().execute("6",direccion7.getText().toString());
                    //RevisarAddress("6",direccion7.getText().toString());
                }
                break;case R.id.dirEntregaText8:
                if (!b && !direccion8.getText().toString().isEmpty()) {
                    actualResId=direccion8.getId();
                    new CheckAddress().execute("7",direccion8.getText().toString());
                    //RevisarAddress("7",direccion8.getText().toString());
                }
                break;
            case R.id.dirEntregaText9:
                if (!b && !direccion9.getText().toString().isEmpty()) {
                    actualResId=direccion9.getId();
                    new CheckAddress().execute("8",direccion9.getText().toString());
                    //RevisarAddress("8",direccion9.getText().toString());
                }
                break;
            case R.id.dirEntregaText10:
                if (!b && !direccion10.getText().toString().isEmpty()) {
                    actualResId=direccion10.getId();
                    new CheckAddress().execute("9",direccion10.getText().toString());
                    //RevisarAddress("9",direccion10.getText().toString());
                }
                break;
            case R.id.dirEntregaText11:
                if (!b && !direccion11.getText().toString().isEmpty()) {
                    actualResId=direccion11.getId();
                    new CheckAddress().execute("10",direccion11.getText().toString());
                    //RevisarAddress("10",direccion11.getText().toString());
                }
                break;
            case R.id.dirEntregaText12:
                if (!b && !direccion12.getText().toString().isEmpty()) {
                    actualResId=direccion12.getId();
                    new CheckAddress().execute("11",direccion12.getText().toString());
                    //RevisarAddress("11",direccion12.getText().toString());
                }
                break;
            case R.id.dirEntregaText13:
                if (!b && !direccion13.getText().toString().isEmpty()) {
                    actualResId=direccion13.getId();
                    new CheckAddress().execute("12",direccion13.getText().toString());
                    //RevisarAddress("12",direccion13.getText().toString());
                }
                break;
            case R.id.dirEntregaText14:
                if (!b && !direccion14.getText().toString().isEmpty()) {
                    actualResId=direccion14.getId();
                    new CheckAddress().execute("13",direccion14.getText().toString());
                    //RevisarAddress("13",direccion14.getText().toString());
                }
                break;
            case R.id.dirEntregaText15:
                if (!b && !direccion15.getText().toString().isEmpty()) {
                    actualResId=direccion15.getId();
                    new CheckAddress().execute("14",direccion15.getText().toString());
                    //RevisarAddress("14",direccion15.getText().toString());
                }
                break;
            case R.id.dirEntregaText16:
                if (!b && !direccion16.getText().toString().isEmpty()) {
                    actualResId=direccion16.getId();
                    new CheckAddress().execute("15",direccion16.getText().toString());
                    //RevisarAddress("15",direccion16.getText().toString());
                }
                break;
            case R.id.dirEntregaText17:
                if (!b && !direccion17.getText().toString().isEmpty()) {
                    actualResId=direccion17.getId();
                    new CheckAddress().execute("16",direccion17.getText().toString());
                    //RevisarAddress("16",direccion17.getText().toString());
                }
                break;
            case R.id.dirEntregaText18:
                if (!b && !direccion18.getText().toString().isEmpty()) {
                    actualResId=direccion18.getId();
                    new CheckAddress().execute("17",direccion18.getText().toString());
                    //RevisarAddress("17",direccion18.getText().toString());
                }
                break;
            case R.id.dirEntregaText19:
                if (!b && !direccion19.getText().toString().isEmpty()) {
                    actualResId=direccion19.getId();
                    new CheckAddress().execute("18",direccion19.getText().toString());
                    //RevisarAddress("18",direccion19.getText().toString());
                }
                break;
            case R.id.dirEntregaText20:
                if (!b && !direccion20.getText().toString().isEmpty()) {
                    actualResId=direccion20.getId();
                    new CheckAddress().execute("19",direccion20.getText().toString());
                    //RevisarAddress("19",direccion20.getText().toString());
                }
                break;






        }

    }

    /*public String RevisarAddress (String position, String direccion)
    {
        /*ProgressDialog pDialog;
        pDialog = new ProgressDialog(getActivity());
        pDialog.setTitle("Contacting Servers");
        pDialog.setMessage("Logging in ...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(true);
        pDialog.show();*/
/*
        UserFunctions userFunction = new UserFunctions();
        Log.i("address","arg="+direccion);
        JSONObject json = userFunction.CHeckDirections(direccion);
        Log.i("address",json.toString());
        addresspos=Integer.parseInt(position);


        DialogFragment dialog = new UserAlertDialogFragment();
        Bundle args = new Bundle();
        args.putString("title", "Direccion");
        args.putString("message",json.toString());
        dialog.setArguments(args);
        dialog.setTargetFragment(dialog, 0);
        dialog.show(getFragmentManager(), "tag");
        String[] cordinates=null;
        try {
            if (json.getString("type").equals("Point")) {

                setDirectionCheckedListener(this);

                String coor = json.getString("coordinates");
                coor = coor.replace("[", "");
                coor = coor.replace("]", "");
                cordinates = coor.split(",");
                Log.i("jsond", cordinates[0] + " " + cordinates[1]);

                JSONObject prop = (JSONObject) json.get("properties");
                String address = prop.get("address").toString();
                Log.i("jsond", address);


                getActivity().findViewById(actualResId).setBackgroundResource(R.drawable.new_edit_text_green);
                getActivity().findViewById(actualResIdIcon).setBackgroundResource(R.drawable.edit_green);

                Direcciones direccion2 = new Direcciones(Double.parseDouble(cordinates[0]), Double.parseDouble(cordinates[1]), address, true);
                direcciones.add(addresspos,direccion2);


                if (ultima != null){

                    if (ultima.equals("1")) {

                        service.setDirecciones(direcciones);
                        totalapproved = 0;
                        for (int j = 0; j < direcciones.size(); j++) {
                            if (direcciones.get(j).isApproved()) {
                                totalapproved++;
                            }

                        }

                        if((totalapproved==direcciones.size())&&direcciones.size()>=2) {
                            Bundle data = new Bundle();
                            data.putSerializable("serviceobject", service);
                            NewService_3 nservice3 = new NewService_3();
                            nservice3.setArguments(data);
                            getActivity().getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.container, nservice3)
                                    .addToBackStack("n1")
                                    .commit();

                        }
                    }
                }


            }
            else
            {
                //if((actualResIdIcon != null) && actualResId != null)
                getActivity().findViewById(actualResId).setBackgroundResource(R.drawable.new_edit_text_red);
                getActivity().findViewById(actualResIdIcon).setBackgroundResource(R.drawable.edit_red);
            }






        } catch (JSONException e) {
            e.printStackTrace();
        }




        return null;
    }*/

    public void setDirectionCheckedListener(OnDirectionChecked CheckedCompletedListener){
        this.DirectionChecked = CheckedCompletedListener;

        Log.i("async","direction checked");

        service.setDirecciones(direcciones);
        totalapproved = 0;
        for (int j = 0; j < direcciones.size(); j++) {
            if (direcciones.get(j).isApproved()) {
                totalapproved++;
            }

        }

        //asyncfinsh = true;

        if(usernext) {
            Log.i("next","true");
            if ((totalapproved == direcciones.size()) && direcciones.size() >= 2) {
                Log.i("next","approved");
                Bundle data = new Bundle();
                data.putSerializable("serviceobject", service);
                NewService_3 nservice3 = new NewService_3();
                nservice3.setArguments(data);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, nservice3)
                        .addToBackStack("n1")
                        .commit();

            }
        }
    }

    @Override
    public void directionchecked() {

        Log.i("direction","finish");

    }

    private class CheckAddress extends AsyncTask<String, String, JSONObject> {

        OnDirectionChecked dircheck;
        private ProgressDialog pDialog;
        String user,pass;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            asyncfinsh=false;

            pDialog = new ProgressDialog(getActivity());
            pDialog.setTitle("Contacting Servers");
            pDialog.setMessage("Logging in ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();


        }
        @Override
        protected JSONObject doInBackground(String... args) {
            UserFunctions userFunction = new UserFunctions();
            Log.i("address","arg="+args[1]);
            JSONObject json = userFunction.CHeckDirections(args[1]);
            Log.i("address",json.toString());
            addresspos=Integer.parseInt(args[0]);

            return json;
        }
        @Override
        protected void onPostExecute(JSONObject json) {



            DialogFragment dialog = new UserAlertDialogFragment();
            Bundle args = new Bundle();
            args.putString("title", "Direccion");
            args.putString("message",json.toString());
            dialog.setArguments(args);
            dialog.setTargetFragment(dialog, 0);
            dialog.show(getFragmentManager(), "tag");
            String[] cordinates=null;
            try {
                if (json.getString("type").equals("Point")) {

                    String coor = json.getString("coordinates");
                    coor = coor.replace("[", "");
                    coor = coor.replace("]", "");
                    cordinates = coor.split(",");
                    Log.i("jsond", cordinates[0] + " " + cordinates[1]);

                    JSONObject prop = (JSONObject) json.get("properties");
                    String address = prop.get("address").toString();
                    Log.i("jsond", address);


                    getActivity().findViewById(actualResId).setBackgroundResource(R.drawable.new_edit_text_green);
                    getActivity().findViewById(actualResIdIcon).setBackgroundResource(R.drawable.edit_green);

                    Direcciones direccion = new Direcciones(Double.parseDouble(cordinates[0]), Double.parseDouble(cordinates[1]), address, true);



                    if(actualResId==dirEntrega.getId())
                    {
                        Log.i("async","direntrega");
                        removedirections.setVisibility(View.VISIBLE);
                        moredirections.setVisibility(View.VISIBLE);

                        try{


                            if ((direcciones.get(addresspos - 1).isApproved()))
                            {
                                direcciones.add(addresspos, direccion);

                                setDirectionCheckedListener(dircheck);
                                Log.i("async","setlistener");
                            }


                        }
                        catch ( IndexOutOfBoundsException e )
                        {
                        Log.i("alert","no deje direcciones vacias");
                            direcciones.add(direccion);

                            setDirectionCheckedListener(dircheck);
                            Log.i("async","setlistener");
                        }
                    }
                    else{
                        direcciones.add(direccion);

                        setDirectionCheckedListener(dircheck);
                        Log.i("async","setlistener");
                    }








                }
                else
                {
                    //if((actualResIdIcon != null) && actualResId != null)
                    getActivity().findViewById(actualResId).setBackgroundResource(R.drawable.new_edit_text_red);
                    getActivity().findViewById(actualResIdIcon).setBackgroundResource(R.drawable.edit_red);
                }






            } catch (JSONException e) {
                e.printStackTrace();
            }



            pDialog.dismiss();


        }

    }



}
