package co.mensajeros.cliente;

import android.app.Activity;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by rene on 11/10/14.
 */

public class Active_Service_custom_list2 extends ArrayAdapter<TaskObject> {

    private ArrayList<TaskObject> objects;
    private final Activity context;
    String ida;


    public Active_Service_custom_list2(Activity context, ArrayList<TaskObject> objects) {
        super(context, R.layout.active_service_item, objects);
        this.context = context;
        this.objects=objects;

    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.active_historial_item, null, true);

        TaskObject taskitem = objects.get(position);
        //Log.i("pos", position + "");

        //TextView ServiceAddress = (TextView) rowView.findViewById(R.id.ServiceAddress);
        //TextView ServiceCost = (TextView) rowView.findViewById(R.id.ServiceCost);
        //TextView ServiceDistance = (TextView) rowView.findViewById(R.id.ServiceDistance);
        //TextView ServiceBuyCost = (TextView) rowView.findViewById(R.id.ServiceBuyCost);
        //TextView ServiceType = (TextView)rowView.findViewById(R.id.ServiceType);

        //TextView ServiceFecha = (TextView) rowView.findViewById(R.id.ServiceFechaRecogida);
        //TextView ServiceHora = (TextView) rowView.findViewById(R.id.ServiceHoraRecogida);

        ImageView circle = (ImageView)rowView.findViewById(R.id.circle_image);
        TextView task_address = (TextView)rowView.findViewById(R.id.task_adrress);

        if(taskitem.getStatus().equals("activo"))// verificar lo del estado
        {

            circle.setBackgroundResource(R.drawable.circle_shape_red);
        }
        else{
            circle.setBackgroundResource(R.drawable.circle_shape_gray);

        }

        task_address.setText(taskitem.getFecha_recogida());


        //ServiceFecha.setText(taskitem.getFecha_recogida());
        //ServiceHora.setText(taskitem.getHora_recogida());

        //Log.i("jaa1", objects.get(0).getPlaces());

        //ServiceAddress.setText(objects.get(0).getPlaces());
        //ServiceAddress.setText(taskitem.getTaskplaces());
        //ServiceCost.setText(taskitem.getValor());




        /*Log.i("tipo", "tipo " + taskitem.getType());
        if(taskitem.getType().equals("1"))
        {
            ServiceType.setText("Express");
            ServiceType.setBackgroundColor(Color.parseColor("#37B0C8"));
            ServiceType.setTextColor(Color.parseColor("#ffffff"));
        }
        if(taskitem.getType().equals("2"))
        {
            ServiceType.setText("Standard");
            ServiceType.setBackgroundColor(Color.parseColor("#FF0000"));
            ServiceType.setTextColor(Color.parseColor("#ffffff"));
        }*/


        /*String dist;
        if(Integer.parseInt(taskitem.getDistancia())<10)
            dist = "<10";
        if(Integer.parseInt(taskitem.getDistancia())>10)
            dist=">10";
        if(Integer.parseInt(taskitem.getDistancia())<10)




            if(taskitem.getIda().equals("0"))
                ida="Solo ir";
        if(taskitem.getIda().equals("1"))
            ida="Ida y vuelta";*/




        /*ServiceDistance.setText(ida +"  distancia "+taskitem.getDistancia()+" Km");
        ServiceBuyCost.setText("Comprar "+taskitem.getComision());
        if(taskitem.getType().equals("1"))
        {ServiceType.setText("Express");}
        if(taskitem.getType().equals("2"))
        {ServiceType.setText("Standard");}
        if(taskitem.getType().equals("3"))
        {ServiceType.setText("Por horas");}*/

        //}

        return rowView;
    }
}
