package co.mensajeros.cliente;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.TypefaceSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.ArrayAdapter;
import android.widget.TextView;


public class main extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {


    public Typeface font2;
    public Typeface font3;

    SharedPreferences pref;
    SharedPreferences.Editor editor;


    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ffffff")));

        font2 = Typeface.createFromAsset(getApplicationContext().getAssets(), "Analogue45Light.ttf");
        font3 = Typeface.createFromAsset(getApplicationContext().getAssets(), "Analogue56Oblique.ttf");

        int titleId = getResources().getIdentifier("action_bar_title", "id", "android");
        TextView textView = (TextView) findViewById(titleId);
        //Typeface typeface = Typeface.create("sans-serif-light", Typeface.ITALIC); // add your typeface
        textView.setTypeface(font3);
        textView.setTextColor(Color.parseColor("#20AAC2"));

        pref = getApplicationContext().getSharedPreferences("UrbanosPref", 0); // 0 - for private mode
        editor = pref.edit();


        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();

        switch (position) {
            //

            case 0:
                //Info de usuario
                NewService nservice = new NewService();
                fragmentManager.beginTransaction()
                        .replace(R.id.container, nservice)
                        .commit();
                break;
            case 1:

                ListService lservice = new ListService();
                fragmentManager.beginTransaction()
                        .replace(R.id.container, lservice)
                        .commit();
                break;

            case 2:

                ShareFragment sfragment = new ShareFragment();
                fragmentManager.beginTransaction()
                        .replace(R.id.container, sfragment)
                        .commit();

                break;

            case 3:

                Help_faqs helpfrag = new Help_faqs();
                fragmentManager.beginTransaction()
                        .replace(R.id.container, helpfrag)
                        .commit();

                break;

            case 4:

                editor.putString("USERID", null).commit(); // Storing string
                editor.putString("SALDO", null).commit();
                finish();
                break;
        }
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
               // mTitle = getString(R.string.title_section1);
                break;
            case 2:
               // mTitle = getString(R.string.title_section2);
                break;
            case 3:
               // mTitle = getString(R.string.title_section3);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((main) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

}
