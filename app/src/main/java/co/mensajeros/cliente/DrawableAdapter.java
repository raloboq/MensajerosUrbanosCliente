package co.mensajeros.cliente;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Rene on 9/3/14.
 */
public class DrawableAdapter extends ArrayAdapter<DrawableItems> {

    final private DrawableItems[] objects;
    final private  int resource;
    final private Context context;
    public Typeface font1;
    public Typeface font2;

    public DrawableAdapter(Context context, int resource, DrawableItems[] objects) {
        super(context, resource, objects);
        this.objects=objects;
        this.resource=resource;
        this.context=context;
        //font1 = Typeface.createFromAsset(getContext().getAssets(), "Analogue75Bold.ttf");
        font1 = Typeface.createFromAsset(getContext().getAssets(), "Raleway-Light.ttf");
        font2 = Typeface.createFromAsset(getContext().getAssets(), "Analogue45Light.ttf");
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View rowView = convertView;
        if (rowView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(resource, parent, false);
        }

        TextView titleDrawer = (TextView) rowView.findViewById(R.id.tv_title_drawable);
        titleDrawer.setTypeface(font1);
        titleDrawer.setTextColor(Color.parseColor("#ffffff"));
        ImageView iconDrawer = (ImageView)rowView.findViewById(R.id.iv_icon_drawable);

        iconDrawer.setBackgroundResource(objects[position].getIcon());

        titleDrawer.setText(objects[position].getText1());

        return rowView;
    }
}
