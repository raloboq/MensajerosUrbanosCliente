package co.mensajeros.cliente;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import co.mensajeros.cliente.Utils.Constant;
import co.mensajeros.cliente.Utils.NavigationAdapter;
import co.mensajeros.cliente.Utils.NavigationList;
import co.mensajeros.cliente.Utils.Utils;
import co.mensajeros.cliente.Utils.ViewPagerFragment;

/**
 * Created by rene on 12/3/14.
 */
public class MainActivityPager extends ActionBarActivity {

    private int lastPosition = 0;
    private ListView listDrawer;

    private int counterItemDownloads;

    private DrawerLayout layoutDrawer;
    private LinearLayout linearDrawer;
    //private RelativeLayout userDrawer;

    private NavigationAdapter navigationAdapter;
    private ActionBarDrawerToggleCompat drawerToggle;

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    public Typeface font3;

    ServiceObject serv;
    boolean servicio_pendiente;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setIcon(R.drawable.ic_action_paperplane2);

        pref = getApplicationContext().getSharedPreferences("UrbanosPref", 0); // 0 - for private mode
        editor = pref.edit();

        setContentView(R.layout.navigation_main);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ffffff")));

        font3 = Typeface.createFromAsset(getApplicationContext().getAssets(), "Analogue56Oblique.ttf");

        int titleId = getResources().getIdentifier("action_bar_title", "id", "android");
        TextView textView = (TextView) findViewById(titleId);
        Typeface typeface = Typeface.create("sans-serif-light", Typeface.ITALIC); // add your typeface
        textView.setTypeface(font3);
              textView.setTextColor(Color.parseColor("#20AAC2"));


        listDrawer = (ListView) findViewById(R.id.listDrawer);
        linearDrawer = (LinearLayout) findViewById(R.id.linearDrawer);
        layoutDrawer = (DrawerLayout) findViewById(R.id.layoutDrawer);

        //userDrawer = (RelativeLayout) findViewById(R.id.userDrawer);
        //userDrawer.setOnClickListener(userOnClick);

        if (listDrawer != null) {
            navigationAdapter = NavigationList.getNavigationAdapter(this);
        }

        listDrawer.setAdapter(navigationAdapter);
        listDrawer.setOnItemClickListener(new DrawerItemClickListener());

        drawerToggle = new ActionBarDrawerToggleCompat(this, layoutDrawer);
        layoutDrawer.setDrawerListener(drawerToggle);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (savedInstanceState != null) {
            setLastPosition(savedInstanceState.getInt(Constant.LAST_POSITION));

            if (lastPosition < 5){
                navigationAdapter.resetarCheck();
                navigationAdapter.setChecked(lastPosition, true);
            }

        }else{
            setLastPosition(lastPosition);
            setFragmentList(lastPosition);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // TODO Auto-generated method stub
        super.onSaveInstanceState(outState);
        outState.putInt(Constant.LAST_POSITION, lastPosition);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        switch (item.getItemId()) {
            /*case Menus.HOME:
                if (layoutDrawer.isDrawerOpen(linearDrawer)) {
                    layoutDrawer.closeDrawer(linearDrawer);
                } else {
                    layoutDrawer.openDrawer(linearDrawer);
                }
                return true;*/
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        hideMenus(menu, lastPosition);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    public void setTitleActionBar(CharSequence informacao) {
        getSupportActionBar().setTitle(informacao);
    }

    public void setSubtitleActionBar(CharSequence informacao) {
        getSupportActionBar().setSubtitle(informacao);
    }

    public void setIconActionBar(int icon) {
        getSupportActionBar().setIcon(icon);
    }

    public void setLastPosition(int posicao){
        this.lastPosition = posicao;
    }

    private class ActionBarDrawerToggleCompat extends ActionBarDrawerToggle {

        public ActionBarDrawerToggleCompat(Activity mActivity, DrawerLayout mDrawerLayout){
            super(
                    mActivity,
                    mDrawerLayout,
                    R.drawable.ic_action_navigation_drawer,
                    R.string.drawer_open,
                    R.string.drawer_close);
        }

        @Override
        public void onDrawerClosed(View view) {
            supportInvalidateOptionsMenu();
        }

        @Override
        public void onDrawerOpened(View drawerView) {
            navigationAdapter.notifyDataSetChanged();
            supportInvalidateOptionsMenu();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        // TODO Auto-generated method stub
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int posicao, long id) {
            setLastPosition(posicao);
            setFragmentList(lastPosition);
            layoutDrawer.closeDrawer(linearDrawer);
        }
    }

    private View.OnClickListener userOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            layoutDrawer.closeDrawer(linearDrawer);
        }
    };

    private void setFragmentList(int position){

        pref = getApplicationContext().getSharedPreferences("UrbanosPref", 0); // 0 - for private mode

        Bundle extras = getIntent().getExtras();

        if(extras!=null){

            if (extras.containsKey("serviceobject")) {
                serv = new  ServiceObject();

                serv = (ServiceObject) extras.getSerializable("serviceobject");
//            Log.i("serv","main + contain "+ serv.getDescripcion());
            }
        }

        servicio_pendiente = pref.getBoolean("PENDIENTE", false);

        if(servicio_pendiente){

//            Log.i("pendiente","drawerfunc "+position +" "+ serv.getDescripcion());

            Bundle data = new Bundle();
            data.putSerializable("serviceobjectregister",serv);
            NewService4 nservice4 = new NewService4();
            nservice4.setArguments(data);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.content_frame, nservice4)
                    .addToBackStack("n1")
                    .commit();
        }else {

            FragmentManager fragmentManager = getSupportFragmentManager();

            switch (position) {
                case 0:
                    fragmentManager.beginTransaction().replace(R.id.content_frame, new ViewPagerFragment()).commit();
                    break;
                case 1:
                    fragmentManager.beginTransaction().replace(R.id.content_frame, new FragmentHistorial_active()).commit();
                    break;

                case 2:
                    fragmentManager.beginTransaction().replace(R.id.content_frame, new ShareFragment()).commit();
                    break;
                case 3:
                    fragmentManager.beginTransaction().replace(R.id.content_frame, new Help_faqs()).commit();
                    break;
                case 4:

                    editor.putString("USERID", null).commit(); // Storing string  FALTAN SHARED EN EL DRAWER NUEVO
                    editor.putString("SALDO", null).commit();
                    finish();
                    break;

                default:
                    Toast.makeText(getApplicationContext(), "implement other fragments here", Toast.LENGTH_SHORT).show();
                    break;
            }

            if (position < 5) {
                navigationAdapter.resetarCheck();
                navigationAdapter.setChecked(position, true);
            }
        }
    }

    private void hideMenus(Menu menu, int posicao) {

        boolean drawerOpen = layoutDrawer.isDrawerOpen(linearDrawer);

        switch (posicao) {
            case 1:
                //menu.findItem(Menus.ADD).setVisible(!drawerOpen);
                //menu.findItem(Menus.UPDATE).setVisible(!drawerOpen);
                //menu.findItem(Menus.SEARCH).setVisible(!drawerOpen);
                break;

            case 2:
                //menu.findItem(Menus.ADD).setVisible(!drawerOpen);
                //menu.findItem(Menus.SEARCH).setVisible(!drawerOpen);
                break;
            //implement other fragments here
        }
    }

    public void setTitleFragments(int position){
        setIconActionBar(Utils.iconNavigation[position]);
        setSubtitleActionBar(Utils.getTitleItem(MainActivityPager.this, position));
    }

    public int getCounterItemDownloads() {
        return counterItemDownloads;
    }

    public void setCounterItemDownloads(int value) {
        this.counterItemDownloads = value;
    }
}
