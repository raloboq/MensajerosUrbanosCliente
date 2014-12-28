package co.mensajeros.cliente;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.doomonafireball.betterpickers.calendardatepicker.CalendarDatePickerDialog;
import com.doomonafireball.betterpickers.datepicker.DatePickerBuilder;
import com.doomonafireball.betterpickers.radialtimepicker.RadialPickerLayout;
import com.doomonafireball.betterpickers.radialtimepicker.RadialTimePickerDialog;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import co.mensajeros.cliente.Utils.SlidingTabLayout;
import co.mensajeros.cliente.Utils.ViewPagerAdapter;
import co.mensajeros.cliente.Utils.ViewPagerFragment;

/**
 * Created by Rene on 9/3/14.
 */
public class NewService extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final String FRAG_TAG_TIME_PICKER = "timePickerDialogFragment";

    String selectedDate;
    String selectedTime;
    TextView selecteddateText;
    Button next;

    private static OnServiceSelected serviceselected;

    public Typeface font2;
    public Typeface font1;
    ServiceObject serviceobject = new ServiceObject();
    SharedPreferences pref;
    SharedPreferences taskhour;
    SharedPreferences.Editor houreditor;
    SharedPreferences.Editor dateeditor;
    SharedPreferences taskdate;
    SharedPreferences userpref;
    SharedPreferences tasttype;
    SharedPreferences.Editor typeeditor;
    SharedPreferences.Editor editor;
    Gson gson;
    String USERNAME = "";

    int mYear;
    int mMonth;
    int mDay;
    int mHour;
    int mMinute;

    Button TimeDate;



    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static NewService newInstance(int sectionNumber) {
        NewService fragment = new NewService();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public NewService() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_new_service_new, container, false);
        pref = getActivity().getSharedPreferences("MURBANOS", 0); // 0 - for private mode



        userpref = getActivity().getSharedPreferences("UrbanosPref", 0); // 0 - for private mode
        USERNAME = userpref.getString("USERNAME", "");
        editor = pref.edit();
        gson = new Gson();

        taskdate = getActivity().getSharedPreferences("TASKTIME",0);
        dateeditor = taskdate.edit();
        taskhour = getActivity().getSharedPreferences("TASKHOUR",0);
        houreditor = taskhour.edit();
        tasttype = getActivity().getSharedPreferences("TASKTYPE",0);
        typeeditor = tasttype.edit();

      //  String json = pref.getString("MyObject", "");
      //  serviceobject = gson.fromJson(json, ServiceObject.class);





       // main.setOnNextClickListener(this);

        //SlidingTabLayout slide0 = new SlidingTabLayout(getActivity());
        //slide0.setOnPageChangedL(this);

       // new VIEW().execute();

       /* rootView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {


                        try{

                            if(serviceobject.getTipo_servicio().equals("1") || serviceobject.getTipo_servicio().equals("2") || serviceobject.getTipo_servicio().equals("3"))
                                Log.i("touch","si esta");
                            return false;
                        }
                        catch (Exception e){
                            Log.i("touch","noooo");
                            return true;
                        }




            }
        });*/

        font2 = Typeface.createFromAsset(getActivity().getAssets(), "Raleway-Medium.ttf");
        font1 = Typeface.createFromAsset(getActivity().getAssets(), "Raleway-Regular.ttf");

        final Button b1 = (Button) rootView.findViewById(R.id.type_service_b1);
        //b1.setBackgroundResource(R.drawable.button_shape_blue);
        b1.setTypeface(font1);
        final Button b2 = (Button) rootView.findViewById(R.id.type_service_b2);
        //b2.setBackgroundResource(R.drawable.button_shape_nocolor);
        b2.setTypeface(font1);
        final Button b3 = (Button) rootView.findViewById(R.id.type_service_b3);
        //b3.setBackgroundResource(R.drawable.button_shape_nocolor);
        b3.setTypeface(font1);





        /*final TextView servicetext = (TextView) rootView.findViewById(R.id.type_service_text);
        servicetext.setVisibility(View.VISIBLE);
        servicetext.setText(getString(R.string.Express_text));*/


        TextView Username = (TextView)rootView.findViewById(R.id.UserName);
        Username.setText(USERNAME);



        float scale = getResources().getDisplayMetrics().density;
        final int ocho = (int) (8*scale + 0.5f);
        final int diez = (int) (10*scale + 0.5f);
        final int diezynueve = (int) (19*scale + 0.5f);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                b1.setBackgroundResource(R.drawable.button_shape_blue);
                b1.setTextColor(Color.parseColor("#FFFFFF"));
                b1.setPadding(ocho,0,0,0);
                b2.setBackgroundResource(R.drawable.onlyborders);
                b2.setTextColor(Color.parseColor("#000000"));
                b2.setPadding(diez,0,0,0);
                b3.setBackgroundResource(R.drawable.button_shape_nocolor_bottom);
                b3.setTextColor(Color.parseColor("#000000"));
                b3.setPadding(diezynueve,0,0,0);
                //serviceobject.setTipo_servicio("1");


                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Servicio Express");
                builder.setMessage("Si lo que necesitas es rapidez, un mensajero llegará en menos de 60 minutos para realizar tu servicio");
                builder.setPositiveButton("Seleccionar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        typeeditor.putString("TaskType","1").commit();
                        TimeDate.setVisibility(View.VISIBLE);

                    }
                });
                builder.setNegativeButton("Regresar",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });


                builder.show();
                //serviceselected.serviceselected();


                //String json = gson.toJson(serviceobject);
                //editor.putString("MyObject", json).commit();


                //servicetext.setVisibility(View.VISIBLE);
                //servicetext.setText(getString(R.string.Express_text));

            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                b2.setBackgroundResource(R.drawable.button_shape_blue);
                b2.setTextColor(Color.parseColor("#FFFFFF"));
                b2.setPadding(diez,0,0,0);
                b1.setBackgroundResource(R.drawable.button_shape_nocolor_top);
                b1.setTextColor(Color.parseColor("#000000"));
                b1.setPadding(ocho,0,0,0);
                b3.setBackgroundResource(R.drawable.button_shape_nocolor_bottom);
                b3.setTextColor(Color.parseColor("#000000"));
                b3.setPadding(diezynueve,0,0,0);
                TimeDate.setVisibility(View.VISIBLE);

                //typeeditor.putString("TaskType","2").commit();

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Servicio Estándar");
                builder.setMessage("Es un servicio NO urgente, se solicita antes de las 12:00 M para ser realizado el mismo dia, de lo contrario es realizado al dia siguiente ");
                builder.setPositiveButton("Seleccionar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        typeeditor.putString("TaskType","2").commit();
                        TimeDate.setVisibility(View.VISIBLE);

                    }
                });
                builder.setNegativeButton("Regresar",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });


                builder.show();

                //serviceobject.setTipo_servicio("2");
                //servicetext.setVisibility(View.VISIBLE);
                //servicetext.setText(getString(R.string.Standard_text));
                //serviceselected.serviceselected();

                //String json = gson.toJson(serviceobject);
                //editor.putString("MyObject", json).commit();
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                b3.setBackgroundResource(R.drawable.button_shape_blue);
                b3.setTextColor(Color.parseColor("#FFFFFF"));
                b3.setPadding(diezynueve,0,0,0);
                b1.setBackgroundResource(R.drawable.button_shape_nocolor_top);
                b1.setTextColor(Color.parseColor("#000000"));
                b1.setPadding(ocho,0,0,0);
                b2.setBackgroundResource(R.drawable.onlyborders);
                b2.setTextColor(Color.parseColor("#000000"));
                b2.setPadding(diez,0,0,0);
                //serviceobject.setTipo_servicio("3");
                //typeeditor.putString("TaskType","3").commit();
                TimeDate.setVisibility(View.VISIBLE);


                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Tipo diligencia");
                builder.setMessage("Tramites legales, radicaciones, pago de impuestos, EPS. LLegamos en menos de 60 minutos. Despues de 15 min de espera recargo por tiempo");
                builder.setPositiveButton("Seleccionar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        typeeditor.putString("TaskType","3").commit();
                        TimeDate.setVisibility(View.VISIBLE);

                    }
                });
                builder.setNegativeButton("Regresar",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });


                builder.show();
                //serviceselected.serviceselected();

                //String json = gson.toJson(serviceobject);
                //editor.putString("MyObject", json).commit();
            }
        });


        TextView title = (TextView) rootView.findViewById(R.id.typeservice_Selection);
        title.setTypeface(font1);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String currentDateandTime = sdf.format(new Date());

        //TextView date = (TextView) rootView.findViewById(R.id.SelectedDateText);
        //date.setText("Fecha:" + currentDateandTime);
        //date.setTypeface(font1);

        //  LA FECHA !!
        //serviceobject.setFecha_recogida(String.valueOf(currentDateandTime));
        //




        TimeDate = (Button)rootView.findViewById(R.id.TimeDatebutton);

        TimeDate.setVisibility(View.GONE);





        TimeDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMinute = c.get(Calendar.MINUTE);

                DatePickerDialog dpd = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {


                                TimePickerDialog tpd = new TimePickerDialog(getActivity(),
                                        new TimePickerDialog.OnTimeSetListener() {

                                            @Override
                                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                                  int minute) {
                                                String selected_time = hourOfDay+":"+minute+":00";
                                                //Log.i("selectedt","time"+selected_time+" "+serviceobject.getTipo_servicio());

                                                houreditor.putString("TaskHour",selected_time).commit();

                                                //serviceobject.setHora_recogida(selected_time);
                                                //String json = gson.toJson(serviceobject);
                                                //editor.putString("MyObject", json).commit();

                                                //Log.i("tipoo","s "+serviceobject.getHora_recogida()+" "+serviceobject.getTipo_servicio());


                                                serviceselected.serviceselected();

                                                //txtTime.setText(hourOfDay + ":" + minute);
                                            }
                                        }, mHour, mMinute, false);
                                tpd.show();


                                String selected_date = year + "-" + (monthOfYear+1) + "-" + dayOfMonth;
                                Log.i("selectedt","date"+selected_date);

                                dateeditor.putString("TaskDate",selected_date).commit();
                                //serviceobject.setFecha_recogida(selected_date);
                                //String json = gson.toJson(serviceobject);
                                //editor.putString("MyObject", json).commit();
                                //txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        }, mYear, mMonth, mDay);


                dpd.show();
            }
        });






        //Button PickDateTime = (Button) rootView.findViewById(R.id.datetime_button);
        //PickDateTime.setTypeface(font1);
        /*selecteddateText = (TextView) rootView.findViewById(R.id.SelectedDateText);

        Button Pick2 = (Button)rootView.findViewById(R.id.datetime_button2);

        Pick2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ChangeTime ct = new ChangeTime();
                //String json = gson.toJson(ct);
                //editor.putString("MyObject", json).commit();
                //nservice4.setArguments(data);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .add(R.id.content_frame, ct)
                        .addToBackStack("n1")
                        .commit();
            }
        });
        //next = (Button) rootView.findViewById(R.id.Next1);
        //next.setTypeface(font2);
        //next.setVisibility(View.VISIBLE);

        PickDateTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                int currentYear = Integer.parseInt(new SimpleDateFormat("yyyy").format(new Date()));
                int currentMonth = Integer.parseInt(new SimpleDateFormat("MM").format(new Date()));
                int currentDay = Integer.parseInt(new SimpleDateFormat("dd").format(new Date()));

                final CalendarDatePickerDialog datepicker = CalendarDatePickerDialog.newInstance(NewService.this, currentYear, currentMonth, currentDay);
               // datepicker.setStyle();

               // DatePickerBuilder dpb = new DatePickerBuilder().setFragmentManager(getActivity().getSupportFragmentManager()).setStyleResId(R.style.MyCustomBetterPickerTheme);

                //dpb.show();


                //datepicker.setStyle(R.style.MyCustomBetterPickerTheme,R.style.Theme_AppCompat_Light_DarkActionBar);
                datepicker.show(getFragmentManager(), FRAG_TAG_TIME_PICKER);


            }
        });*/

        /*next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("object", serviceobject.getFecha_recogida());

                Bundle data = new Bundle();
                data.putSerializable("serviceobject", serviceobject);
                NewService_2 nservice2 = new NewService_2();
                nservice2.setArguments(data);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, nservice2)
                        .addToBackStack("n1")
                        .commit();

            }
        });*/


        return rootView;

    }






    /*@Override
    public void nextclicked() {
    Log.i("nextl","clicked");

        Log.i("object", serviceobject.getFecha_recogida());

        Bundle data = new Bundle();
        data.putSerializable("serviceobject", serviceobject);
        NewService_2 nservice2 = new NewService_2();
        nservice2.setArguments(data);
        /*getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, nservice2)
                .addToBackStack("n1")
                .commit();*/

    //}

    public  void setonServiceSelected(OnServiceSelected service){
        serviceselected = service;
        Log.i("listener", "setlistener");
//        String json = gson.toJson(serviceobject);
//        editor.putString("MyObject", json).commit();

    }



}