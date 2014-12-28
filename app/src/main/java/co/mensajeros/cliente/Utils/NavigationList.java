package co.mensajeros.cliente.Utils;

/**
 * Created by rene on 12/3/14.
 */
import android.content.Context;

import co.mensajeros.cliente.R;
import co.mensajeros.cliente.Utils.NavigationAdapter;
import co.mensajeros.cliente.Utils.NavigationItemAdapter;
import co.mensajeros.cliente.Utils.Utils;



public class NavigationList {

    public static NavigationAdapter getNavigationAdapter(Context context){

        NavigationAdapter navigationAdapter = new NavigationAdapter(context);
        String[] menuItems = context.getResources().getStringArray(R.array.nav_menu_items);

        for (int i = 0; i < menuItems.length; i++) {

            String title = menuItems[i];
            NavigationItemAdapter itemNavigation;
            itemNavigation = new NavigationItemAdapter(title, Utils.iconNavigation[i]);
            navigationAdapter.addItem(itemNavigation);
        }
        return navigationAdapter;
    }
}
