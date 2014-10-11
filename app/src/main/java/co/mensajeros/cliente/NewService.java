package co.mensajeros.cliente;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.doomonafireball.betterpickers.calendardatepicker.CalendarDatePickerDialog;
import com.doomonafireball.betterpickers.radialtimepicker.RadialPickerLayout;
import com.doomonafireball.betterpickers.radialtimepicker.RadialTimePickerDialog;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Rene on 9/3/14.
 */
public class NewService extends Fragment implements RadialTimePickerDialog.OnTimeSetListener, CalendarDatePickerDialog.OnDateSetListener {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final String FRAG_TAG_TIME_PICKER = "timePickerDialogFragment";

    String selectedDate;
    TextView selecteddateText;
    Button next;

    public Typeface font2;
    public Typeface font1;
    ServiceObject serviceobject=null;
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
        View rootView = inflater.inflate(R.layout.fragment_new_service, container, false);

         serviceobject = new ServiceObject();

        font2 = Typeface.createFromAsset(getActivity().getAssets(), "Raleway-Medium.ttf");
        font1 = Typeface.createFromAsset(getActivity().getAssets(), "Raleway-Regular.ttf");

        final Button b1 = (Button)rootView.findViewById(R.id.type_service_b1);
        b1.setBackgroundResource(R.drawable.button_shape_blue);
        b1.setTypeface(font1);
        final Button b2 = (Button)rootView.findViewById(R.id.type_service_b2);
        b2.setBackgroundResource(R.drawable.button_shape_nocolor);
        b2.setTypeface(font1);
        final Button b3 = (Button)rootView.findViewById(R.id.type_service_b3);
        b3.setBackgroundResource(R.drawable.button_shape_nocolor);
        b3.setTypeface(font1);

        final TextView servicetext = (TextView)rootView.findViewById(R.id.type_service_text);
        servicetext.setVisibility(View.GONE);

        serviceobject.setTipo_servicio("1");

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                b1.setBackgroundResource(R.drawable.button_shape_blue);
                b2.setBackgroundResource(R.drawable.button_shape_nocolor);
                b3.setBackgroundResource(R.drawable.button_shape_nocolor);
                serviceobject.setTipo_servicio("1");

                servicetext.setVisibility(View.VISIBLE);
                servicetext.setText(getString(R.string.Express_text));

            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                b2.setBackgroundResource(R.drawable.button_shape_blue);
                b1.setBackgroundResource(R.drawable.button_shape_nocolor);
                b3.setBackgroundResource(R.drawable.button_shape_nocolor);
                serviceobject.setTipo_servicio("2");
                servicetext.setVisibility(View.VISIBLE);
                servicetext.setText(getString(R.string.Standard_text));
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                b3.setBackgroundResource(R.drawable.button_shape_blue);
                b1.setBackgroundResource(R.drawable.button_shape_nocolor);
                b2.setBackgroundResource(R.drawable.button_shape_nocolor);
                serviceobject.setTipo_servicio("3");
            }
        });



        TextView title = (TextView)rootView.findViewById(R.id.typeservice_Selection);
        title.setTypeface(font1);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String currentDateandTime = sdf.format(new Date());

        TextView date = (TextView)rootView.findViewById(R.id.SelectedDateText);
        date.setText("Fecha:"+currentDateandTime);
        date.setTypeface(font1);
        serviceobject.setFecha_recogida(String.valueOf(currentDateandTime));

        Button PickDateTime = (Button)rootView.findViewById(R.id.datetime_button);
        PickDateTime.setTypeface(font1);
        selecteddateText = (TextView)rootView.findViewById(R.id.SelectedDateText);
        next = (Button)rootView.findViewById(R.id.Next1);
        next.setTypeface(font2);
        next.setVisibility(View.VISIBLE);

        PickDateTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                int currentYear = Integer.parseInt(new SimpleDateFormat("yyyy").format(new Date()));
                int currentMonth = Integer.parseInt(new SimpleDateFormat("MM").format(new Date()));
                int currentDay = Integer.parseInt(new SimpleDateFormat("dd").format(new Date()));

                final CalendarDatePickerDialog datepicker = CalendarDatePickerDialog.newInstance(NewService.this,currentYear,currentMonth,currentDay);

                datepicker.show(getFragmentManager(), FRAG_TAG_TIME_PICKER);




            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("object",serviceobject.getFecha_recogida());

                Bundle data = new Bundle();
                data.putSerializable("serviceobject",serviceobject);
                NewService_2 nservice2 = new NewService_2();
                nservice2.setArguments(data);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, nservice2)
                        .addToBackStack("n1")
                        .commit();
                
            }
        });


        return rootView;

    }


    @Override
    public void onTimeSet(RadialPickerLayout radialPickerLayout, int i, int i2) {

        Log.i("Date",i+" "+i2+"");

        selectedDate=selectedDate.concat("  "+i+":"+i2);

        selecteddateText.setText(selectedDate);
        serviceobject.setFecha_recogida(selectedDate);
        next.setVisibility(View.VISIBLE);


    }

    @Override
    public void onDateSet(CalendarDatePickerDialog calendarDatePickerDialog, int i, int i2, int i3) {

        Log.i("Date",i+" "+i2+""+i3);

        selectedDate=i+"-"+i2+"-"+i3;

        int currentHour = Integer.parseInt(new SimpleDateFormat("HH").format(new Date()));
        int currentMin = Integer.parseInt(new SimpleDateFormat("mm").format(new Date()));

         final RadialTimePickerDialog timePickerDialog = RadialTimePickerDialog.newInstance(NewService.this, currentHour, currentMin, false);

        timePickerDialog.show(getFragmentManager(), FRAG_TAG_TIME_PICKER);

    }
}