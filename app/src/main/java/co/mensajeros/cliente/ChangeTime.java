package co.mensajeros.cliente;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.gson.Gson;

/**
 * Created by rene on 12/9/14.
 */
public class ChangeTime extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    SharedPreferences userpref;
    String USERNAME = "";
    ServiceObject serviceobject = null;
    SharedPreferences pref;
    SharedPreferences.Editor editor;

    Gson gson;

    public static ChangeTime newInstance(int sectionNumber) {
        ChangeTime fragment = new ChangeTime();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);



        //service= (ServiceObject) extras.getSerializable("serviceobject");


        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        userpref = getActivity().getSharedPreferences("UrbanosPref", 0); // 0 - for private mode
        USERNAME = userpref.getString("USERNAME", "");

        pref = getActivity().getSharedPreferences("MURBANOS", 0); // 0 - for private mode
        editor = pref.edit();
        gson = new Gson();


        serviceobject = new ServiceObject();

        String json2 = pref.getString("MyObject", "");
        serviceobject = gson.fromJson(json2, ServiceObject.class);


        View rootView = inflater.inflate(R.layout.fragment_service_time, container, false);

        TextView Username = (TextView)rootView.findViewById(R.id.UserName);
        Username.setText(USERNAME);

        DatePicker date = (DatePicker)rootView.findViewById(R.id.datePicker);

        date.setMinDate(System.currentTimeMillis() - 1000);  //API 11 AAAAA

        int day = date.getDayOfMonth();
        int month = date.getMonth() + 1;
        int year = date.getYear();

        TimePicker time = (TimePicker)rootView.findViewById(R.id.timePicker);

        time.clearFocus();
// re-read the values, in my case i put them in a Time object.
        int hour   = time.getCurrentHour();
        int minute = time.getCurrentMinute();

        String selected_date = year + "-" + month + "-" + day;
        String selected_time = hour+":"+minute+":00";

        serviceobject.setHora_recogida(selected_time);
        serviceobject.setFecha_recogida(selected_date);

        Log.i("newtime","time= "+selected_date+" "+selected_time);


    return rootView;
    }
}
