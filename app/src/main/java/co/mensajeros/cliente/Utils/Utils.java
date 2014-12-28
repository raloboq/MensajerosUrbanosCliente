package co.mensajeros.cliente.Utils;

import android.content.Context;

import co.mensajeros.cliente.R;

/**
 * Created by rene on 12/3/14.
 */
public class Utils {

    //Set all the navigation icons and always to set "zero 0" for the item is a category
    public static int[] iconNavigation = new int[] {
            R.drawable.btn_plus, R.drawable.btn_lupa,R.drawable.compartir, R.drawable.btn_ayuda, R.drawable.apagar, R.drawable.ic_action_help};

    //get title of the item navigation
    public static String getTitleItem(Context context, int posicao){
        String[] titulos = context.getResources().getStringArray(R.array.nav_menu_items);
        return titulos[posicao];
    }

    public static int[] colors = new int[] {
            R.color.blue2, R.color.blue2, R.color.blue2, R.color.blue2,
            R.color.blue2, R.color.blue2, R.color.blue2, R.color.blue2,
            R.color.blue2, R.color.blue2};

    //public static int[] colors = new int[] {R.color.blue2};

}
