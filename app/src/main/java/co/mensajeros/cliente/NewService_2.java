package co.mensajeros.cliente;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import co.mensajeros.cliente.Utils.ServiceObjectChanged;
import co.mensajeros.cliente.Utils.SlidingTabLayout;

/**
 * Created by Rene on 9/3/14.
 */
public class NewService_2 extends Fragment implements View.OnFocusChangeListener, OnDirectionChecked, OnPageChangedL {

     ServiceObject service = new ServiceObject();


    private static final String ARG_SECTION_NUMBER = "section_number";

    public static NewService_2 newInstance(int sectionNumber) {
        NewService_2 fragment = new NewService_2();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);

        Log.i("paso2", "instance paso 2");


        //service= (ServiceObject) extras.getSerializable("serviceobject");


        fragment.setArguments(args);
        return fragment;
    }

    public NewService_2() {
    }



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

    boolean remove = false;

    public OnDirectionChecked dircheck2;

     Button next;

    SharedPreferences pref;
    SharedPreferences.Editor editor;

    Gson gson;

    CheckBox idayvuelta;
    SharedPreferences userpref;
    String USERNAME = "";

    private static OnStep2Next step2;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_new_service2, container, false);

        Log.i("paso2", "createview paso 2");

        gson = new Gson();

        pref = getActivity().getSharedPreferences("MURBANOS", 0); // 0 - for private mode
        editor = pref.edit();


        userpref = getActivity().getSharedPreferences("UrbanosPref", 0); // 0 - for private mode
        USERNAME = userpref.getString("USERNAME", "");



        //String json = pref.getString("MyObject", "");
        //service = gson.fromJson(json, ServiceObject.class);





        SlidingTabLayout slide = new SlidingTabLayout(getActivity());
        slide.setOnPageChangedL(this);

        font2 = Typeface.createFromAsset(getActivity().getAssets(), "Raleway-Medium.ttf");
        font1 = Typeface.createFromAsset(getActivity().getAssets(), "Raleway-Regular.ttf");

        TextView Username = (TextView)rootView.findViewById(R.id.UserName);
        Username.setText(USERNAME);

        direccion3 = (EditText)rootView.findViewById(R.id.dirEntregaText3);
        direccion3.setOnFocusChangeListener(this);
        direccion3.setVisibility(View.GONE);
        direccion4 = (EditText)rootView.findViewById(R.id.dirEntregaText4);
        direccion4.setOnFocusChangeListener(this);
        direccion4.setVisibility(View.GONE);
        direccion5 = (EditText)rootView.findViewById(R.id.dirEntregaText5);
        direccion5.setOnFocusChangeListener(this);
        direccion5.setVisibility(View.GONE);
        direccion6 = (EditText)rootView.findViewById(R.id.dirEntregaText6);
        direccion6.setOnFocusChangeListener(this);
        direccion6.setVisibility(View.GONE);
        direccion7 = (EditText)rootView.findViewById(R.id.dirEntregaText7);
        direccion7.setOnFocusChangeListener(this);
        direccion7.setVisibility(View.GONE);
        direccion8 = (EditText)rootView.findViewById(R.id.dirEntregaText8);
        direccion8.setOnFocusChangeListener(this);
        direccion8.setVisibility(View.GONE);
        direccion9 = (EditText)rootView.findViewById(R.id.dirEntregaText9);
        direccion9.setOnFocusChangeListener(this);
        direccion9.setVisibility(View.GONE);
        direccion10 = (EditText)rootView.findViewById(R.id.dirEntregaText10);
        direccion10.setOnFocusChangeListener(this);
        direccion10.setVisibility(View.GONE);
        direccion11 = (EditText)rootView.findViewById(R.id.dirEntregaText11);
        direccion11.setOnFocusChangeListener(this);
        direccion11.setVisibility(View.GONE);
        direccion12 = (EditText)rootView.findViewById(R.id.dirEntregaText12);
        direccion12.setOnFocusChangeListener(this);
        direccion12.setVisibility(View.GONE);
        direccion13 = (EditText)rootView.findViewById(R.id.dirEntregaText13);
        direccion13.setOnFocusChangeListener(this);
        direccion13.setVisibility(View.GONE);
        direccion14 = (EditText)rootView.findViewById(R.id.dirEntregaText14);
        direccion14.setOnFocusChangeListener(this);
        direccion14.setVisibility(View.GONE);
        direccion15 = (EditText)rootView.findViewById(R.id.dirEntregaText15);
        direccion15.setOnFocusChangeListener(this);
        direccion15.setVisibility(View.GONE);
        direccion16 = (EditText)rootView.findViewById(R.id.dirEntregaText16);
        direccion16.setOnFocusChangeListener(this);
        direccion16.setVisibility(View.GONE);
        direccion17 = (EditText)rootView.findViewById(R.id.dirEntregaText17);
        direccion17.setOnFocusChangeListener(this);
        direccion17.setVisibility(View.GONE);
        direccion18 = (EditText)rootView.findViewById(R.id.dirEntregaText18);
        direccion18.setOnFocusChangeListener(this);
        direccion18.setVisibility(View.GONE);
        direccion19 = (EditText)rootView.findViewById(R.id.dirEntregaText19);
        direccion19.setOnFocusChangeListener(this);
        direccion19.setVisibility(View.GONE);
        direccion20 = (EditText)rootView.findViewById(R.id.dirEntregaText20);
        direccion20.setOnFocusChangeListener(this);
        direccion20.setVisibility(View.GONE);

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



        idayvuelta = (CheckBox)rootView.findViewById(R.id.idavuelta);
        idayvuelta.setTypeface(font1);





      //  Log.i("service",service.getFecha_recogida());

        //Spinner city = (Spinner)rootView.findViewById(R.id.cityspinner);

//        service.setCiudad(city.getPrompt().toString()); //QUITADO PARA QUE SIRVA EL DRAWER


        dirRecogida = (EditText)rootView.findViewById(R.id.dirRecogidaText);
        dirRecogida.setTypeface(font1);
        dirRecogidaIcon = (ImageView)rootView.findViewById(R.id.dirRecogidaIcon);
        dirRecogidaIcon.setBackgroundResource(R.drawable.edit_gray);


        dirEntrega = (EditText)rootView.findViewById(R.id.dirEntregaText);
        dirEntrega.setTypeface(font1);
        dirEntregaIcon = (ImageView)rootView.findViewById(R.id.dirEntregaIcon);
        dirEntregaIcon.setBackgroundResource(R.drawable.edit_gray);



        dirRecogida.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {

                if(!b&&!dirRecogida.getText().toString().isEmpty()) {
                    actualResId=dirRecogida.getId();
                    actualResIdIcon=dirRecogidaIcon.getId();
                    Log.i("res","res "+actualResId);
                    new CheckAddress().execute("0",dirRecogida.getText().toString());
                    //RevisarAddress("0",dirRecogida.getText().toString());
                }
                if(!b&&dirRecogida.getText().toString().isEmpty()){

                    Log.i("direccion","alerta recogida vacia");
                    DialogFragment dialog = new AddressErrorDialogFragment2();
                    Bundle args = new Bundle();
                    args.putString("title", "Alerta");
                    args.putString("message", "La dirección de recogida esta vacia");
                    dialog.setArguments(args);
                    //dialog.setStyle(DialogFragment.STYLE_NO_TITLE, R.style.MyDialog);

                    dialog.setTargetFragment(dialog,0);
                    dialog.show(getFragmentManager(), "tag");







                }
            }
        });

        dirEntrega.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {

                if(!b && !dirEntrega.getText().toString().isEmpty()) {
                    ultima="1";
                    actualResId=dirEntrega.getId();
                    Log.i("res","res "+actualResId);
                    actualResIdIcon=dirEntregaIcon.getId();
                    new CheckAddress().execute("1",dirEntrega.getText().toString(), ultima);
                    //RevisarAddress("1",dirEntrega.getText().toString());
                }
                if(!b&&dirEntrega.getText().toString().isEmpty()){

                    Log.i("direccion","alerta entrega vacia");
                    DialogFragment dialog = new AddressErrorDialogFragment2();
                    Bundle args = new Bundle();
                    args.putString("title", "Alerta");
                    args.putString("message", "La dirección de entrega esta vacia");
                    dialog.setArguments(args);
                    Log.i("res", "res error");
                    dialog.setTargetFragment(dialog, 0);
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
                            Log.i("list","add ="+ListIndex+" "+AddresList.get(ListIndex).getId());

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
                remove=true;
                if(ListIndex>=0) {
                    Log.i("list","remove ="+ListIndex);
                    AddresList.get(ListIndex).setVisibility(View.GONE);
                    if(ListIndex!=0)
                    {
                        Log.i("list","remove ="+ListIndex);

                    //Log.i("rmdireccion ",direcciones.get(ListIndex-1).getAddress()+" "+(ListIndex-1));
                    try{
                        direcciones.remove(ListIndex-1);
                    }
                    catch (Exception E){


                    }
                    ListIndex--;

                        Log.i("list","remove ="+ListIndex);
                    }
                    //direcciones.remove(ListIndex+2);}

                }
                //LinearLayout secondStepremove = (LinearLayout)rootView.findViewById(R.id.add_remove_directions);
                //LinearLayout.LayoutParams mRparamsremove = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                //secondStep.removeView();
            }
        });




        next = (Button)rootView.findViewById(R.id.Next2);
        next.setVisibility(View.VISIBLE);
        next.setTypeface(font2);

        next.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {

                if(b) {
                    usernext = true;

                    Log.i("next","click");
                    setDirectionCheckedListener(dircheck2);
                }
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Log.i("type",service.getTipo_servicio());

//                Log.i("tipoo","s2 "+service.getHora_recogida()+" "+service.getFecha_recogida());

//                Log.i("listdir","num= "+service.getDirecciones().size());

                usernext=true;
                //Log.i("next","click");
                next.requestFocus();
                setDirectionCheckedListener(dircheck2);

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

                step2.step2next();





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
                if (!b && !direccion3.getText().toString().isEmpty() && !remove ) {
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


        try {
            if (idayvuelta.isChecked())
                service.setIdayVuelta("1");
            else
                service.setIdayVuelta("0");

            service.setDirecciones(direcciones);
        }catch (Exception e){

            e.printStackTrace();
            Log.i("pficti","paila");
        }

        String json = gson.toJson(service);
        editor.putString("MyObject", json).commit();




        totalapproved = 0;
        Log.i("dsize ",""+direcciones.size());
        for (int j = 0; j < direcciones.size(); j++) {
            if (direcciones.get(j).isApproved()) {
                totalapproved++;
                Log.i("dsize ",""+totalapproved);
            }

        }

        //asyncfinsh = true;

        if(usernext) {
            Log.i("next","true");
            if ((totalapproved == direcciones.size()) && direcciones.size() >= 2) {
                Log.i("next","approved");
                //Bundle data = new Bundle();
                //data.putSerializable("serviceobject", service);hh
                //NewService_3 nservice3 = new NewService_3();
                //nservice3.setArguments(data);








                /*getActivity().getSupportFragmentManager().beginTransaction()
                        .add(R.id.container, nservice3)
                        .addToBackStack("n1")
                        .commit();*/

            }
        }
    }

    @Override
    public void directionchecked() {

        Log.i("direction","finish");

    }

    /*@Override
    public void nextclicked() {

        usernext=true;
        //Log.i("next","click");
        next.requestFocus();
        setDirectionCheckedListener(dircheck2);

        Log.i("next","on click next");

        if(dirRecogida.getText().toString().isEmpty()){

            Log.i("direccion","alerta recogida vacia");
            DialogFragment dialog = new AddressErrorDialogFragment2();
            Bundle args = new Bundle();
            args.putString("title", "Alerta");
            args.putString("message", "La dirección de recogida esta vacia");
            dialog.setArguments(args);
            dialog.setTargetFragment(dialog,0);
            dialog.show(getFragmentManager(), "tag");
        }

        if(dirEntrega.getText().toString().isEmpty()){

            Log.i("direccion","alerta entrega vacia");
            DialogFragment dialog = new AddressErrorDialogFragment2();
            Bundle args = new Bundle();
            args.putString("title", "Alerta");
            args.putString("message", "La dirección de entrega esta vacia");
            dialog.setArguments(args);
            dialog.setTargetFragment(dialog,0);
            dialog.show(getFragmentManager(), "tag");


        }

    }*/

    @Override
    public int onpagechange(int pos) {

        //Log.i("paso2", "page change 2");

        //Log.i("page","bbbbb"+pos);

        //pref = getActivity().getSharedPreferences("MURBANOS", 0); // 0 - for private mode
        //editor = pref.edit();


        //String json = pref.getString("MyObject", "");
        //service = gson.fromJson(json, ServiceObject.class);

        //Log.i("shared","tipo "+service.getTipo_servicio());

        //Bundle a = new Bundle();
        //service = (ServiceObject) a.getSerializable("serviceobject");
        //Log.i("object",service.getFecha_recogida());



        return 0;
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
           // Log.i("address",json.toString());
            addresspos=Integer.parseInt(args[0]);
           // Log.i("pos","pos "+addresspos);

            return json;
        }
        @Override
        protected void onPostExecute(JSONObject json) {


            try {

                if(json.getString("type").equals("NO")){

            DialogFragment dialog = new AddressErrorDialogFragment2();
            Bundle args = new Bundle();
            args.putString("title", "Direccion");
            args.putString("message",json.toString());
            dialog.setArguments(args);
            dialog.setTargetFragment(dialog, 0);
            dialog.show(getFragmentManager(), "tag");

                }


            String[] cordinates=null;

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
                                //direcciones.add(addresspos, direccion);
                                if(direcciones.size()>0) {
                                    direcciones.set(addresspos, direccion);
                                }
                                else{
                                    direcciones.add(0, null);
                                    direcciones.set(1,direccion);
                                }
                                Log.i("position","pos1 "+addresspos+" "+direccion.getAddress());

                                setDirectionCheckedListener(dircheck);
                                Log.i("async","setlistener");
                            }


                        }
                        catch ( IndexOutOfBoundsException e )
                        {
                        Log.i("alert","no deje direcciones vacias");
                            direcciones.add(direccion);
                            Log.i("position","pos2 "+addresspos+" "+direccion.getAddress());

                            setDirectionCheckedListener(dircheck);
                            Log.i("async","setlistener");
                        }
                    }
                    else{

                        if(direcciones.size()>0) {
                            direcciones.set(addresspos, direccion);
                        }
                        else{
                            direcciones.add(addresspos,direccion);
                        }
                        Log.i("position","pos3 "+addresspos+" "+direccion.getAddress()+" "+actualResId+" "+dirRecogida.getId() );

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






            } catch (Exception e) {
                e.printStackTrace();

                pDialog.dismiss();

                DialogFragment dialog2 = new AddressErrorDialogFragment2();
                Bundle argsdialog = new Bundle();
                argsdialog.putString("title", "Error");
                argsdialog.putString("message","Error conectando con el servidor, por favor revise su conexión a internet");
                dialog2.setArguments(argsdialog);
                dialog2.setTargetFragment(dialog2, 0);
                dialog2.show(getFragmentManager(), "tag");
            }


            remove=false;
            pDialog.dismiss();


        }

    }

    public static void setonStep2Next(OnStep2Next step){
        step2 = step;

       // Log.i("tipoo","s2 "+service.getHora_recogida()+" "+service.getFecha_recogida());
        Log.i("paso2", "setlistene paso 2");
    }



}
