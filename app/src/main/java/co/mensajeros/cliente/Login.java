package co.mensajeros.cliente;

/**
 * Created by Rene on 7/24/14.
 */
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.model.GraphUser;
import com.facebook.widget.LoginButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;


public class Login extends ActionBarActivity {

    private static String KEY_SUCCESS = "result";
    private static String KEY_UID = "uid";
    private static String KEY_USERNAME = "user";
    private static String KEY_TASK = "task";
    private static String KEY_FIRSTNAME = "fname";
    private static String KEY_LASTNAME = "lname";
    private static String KEY_EMAIL = "email";
    private static String KEY_CREATED_AT = "created_at";

    EditText UserName;
    EditText Password;
    Button Loginbutton;
    TextView resultText;

    String userid;
    String name_user;
    String saldo;

    SharedPreferences pref;
    SharedPreferences.Editor editor;

    private LoginButton loginBtn;
    private Button registerbutton;
    private Button continuenologin;
    private UiLifecycleHelper uiHelper;
    private TextView userName;

    String pass;
    public Typeface font3;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Crashlytics.start(this);
        setContentView(R.layout.activity_login);



        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ffffff")));

        font3 = Typeface.createFromAsset(getApplicationContext().getAssets(), "Analogue56Oblique.ttf");

        int titleId = getResources().getIdentifier("action_bar_title", "id", "android");
        TextView textView = (TextView) findViewById(titleId);
        Typeface typeface = Typeface.create("sans-serif-light", Typeface.ITALIC); // add your typeface
        textView.setTypeface(font3);
        textView.setTextColor(Color.parseColor("#20AAC2"));

        uiHelper = new UiLifecycleHelper(this, statusCallback);
        uiHelper.onCreate(savedInstanceState);

        pref = getApplicationContext().getSharedPreferences("UrbanosPref", 0); // 0 - for private mode
        editor = pref.edit();

        final String useridpref = pref.getString("USERID", null);

        if(useridpref !=null) {

            Log.i("login", "ya logueado " + useridpref);
            Intent main = new Intent(getApplicationContext(), MainActivityPager.class);
            startActivity(main);
            finish();

        }
        userName = (TextView) findViewById(R.id.userName);
        loginBtn = (LoginButton) findViewById(R.id.fb_login_button);
        loginBtn.setReadPermissions(Arrays.asList("email"));
        registerbutton = (Button) findViewById(R.id.Registrobutton);

        registerbutton.setVisibility(View.GONE);

        continuenologin = (Button)findViewById(R.id.continue_nologin);

        continuenologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent main = new Intent(getApplicationContext(), MainActivityPager.class);
                editor.putBoolean("ANONYMOUS",true).commit();
                startActivity(main);
            }
        });


        registerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(),Registro.class);
                startActivity(i);
            }
        });


        loginBtn.setUserInfoChangedCallback(new LoginButton.UserInfoChangedCallback() {
            @Override
            public void onUserInfoFetched(GraphUser user) {


                if (user != null ) {
                    Log.i("face",user.getProperty("email").toString());
                    RegisterObject registro = new RegisterObject();

                    registro.setNombre(user.getName());
                    registro.setEmail(user.getProperty("email").toString());
                    registro.setFbId(user.getId());

                    Intent i = new Intent(getApplicationContext(),Registro.class);
                    i.putExtra("registro",registro);
                    startActivity(i);

                    //userName.setText("Hello, " + user.getName()+ ""+ user.getId()+" "+ user.getLink()+" "+user.getBirthday()+" "+user.getFirstName()+" "+user.getLastName()+" "+user.getMiddleName()+" "+user.getLocation()+" "+user.getInnerJSONObject());
                } else {

                    Log.i("face","paila");
                   // userName.setText("You are not logged");
                }
            }
        });



        UserName = (EditText)findViewById(R.id.UserText);
        Password = (EditText)findViewById(R.id.PasswordText);
        Loginbutton = (Button)findViewById(R.id.Loginbutton);
        resultText = (TextView)findViewById(R.id.resultText);

        Loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!UserName.getText().toString().equals("") && !Password.getText().toString().equals(""))
                {
                    new ProcessLogin().execute();

                }
                else{
                    DialogFragment dialog = new UserAlertDialogFragment();
                    Bundle args = new Bundle();
                    args.putString("title", "Login");
                    args.putString("message","Usuario o contraseña no pueden estar vacios");
                    dialog.setArguments(args);
                    dialog.setTargetFragment(dialog, 0);
                    dialog.show(getSupportFragmentManager(), "tag");
                }
            }
        });
    }

    private Session.StatusCallback statusCallback = new Session.StatusCallback() {
        @Override
        public void call(Session session, SessionState state,
                         Exception exception) {
            if (state.isOpened()) {
                //buttonsEnabled(true);
                Log.d("FacebookSampleActivity", "Facebook session opened");
            } else if (state.isClosed()) {
                //buttonsEnabled(false);
                Log.d("FacebookSampleActivity", "Facebook session closed");
            }
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        uiHelper.onResume();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        uiHelper.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onPause() {
        super.onPause();
        uiHelper.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        uiHelper.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        uiHelper.onSaveInstanceState(outState);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);
        return true;
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
     * Async Task to get and send data to My Sql database through JSON respone.
     **/
    private class ProcessLogin extends AsyncTask<String, String, JSONObject> {
        private ProgressDialog pDialog;
        String user;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            user = UserName.getText().toString();
            pass = Password.getText().toString();
            Log.i("login",user+" "+pass);
            pDialog = new ProgressDialog(Login.this);
            pDialog.setTitle("Contactando servidor");
            pDialog.setMessage("Conectando ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }
        @Override
        protected JSONObject doInBackground(String... args) {
            UserFunctions userFunction = new UserFunctions();
            JSONObject json = userFunction.loginUser(user, pass);
//            Log.i("login1",json.toString());
            return json;
        }
        @Override
        protected void onPostExecute(JSONObject json) {
            try {
                /*try {
                  //  Log.i("login", "onpostex" + json.getString(KEY_SUCCESS));
                } catch (JSONException e1) {
                    e1.printStackTrace();
                }*/
                //Log.i("login","onpostex"+json.getJSONObject("user")+"");

                //if (json.getString(KEY_SUCCESS) != null) {
                //String res = json.getString(KEY_SUCCESS);
                if (!json.isNull("Error")) {
                    //Log.i("error", json.getString("Error"));
                    //resultText.setText(json.getString("Error"));

                    pDialog.dismiss();
                    DialogFragment dialog = new UserAlertDialogFragment();
                    Bundle args = new Bundle();
                    args.putString("title", "Error");
                    args.putString("message",json.getString("Error"));
                    dialog.setArguments(args);
                    dialog.setTargetFragment(dialog, 0);
                    dialog.show(getSupportFragmentManager(), "tag");


                }
                else
                {

                    String res = null;
                try {
                    res = json.getString("estado");
                } catch (JSONException e1) {
                    e1.printStackTrace();
                }
                Log.i("login", "onpostex res=" + res);

                if (res != null) {
                    if (res.equals("1")) {
                        editor.putBoolean("PENDIENTE",false).commit();
                        editor.putBoolean("ANONYMOUS",false).commit();

                        pDialog.setMessage("Loading User Space");
                        pDialog.setTitle("Getting Data");

                        //resultText.setText(json.getString(KEY_USERNAME)+json.getString(KEY_UID)+json.getString(KEY_CREATED_AT));ç
                        resultText.setText("Bienvenido");
                        /**
                         *If JSON array details are stored in SQlite it launches the User Panel.
                         **/
                        Intent main = new Intent(getApplicationContext(), MainActivityPager.class);
                        userid = json.getString("user");
                        name_user = json.getString("nombre");
                        saldo = json.getString("saldo");
                        Log.i("userid", "login=" + userid);
                        editor.putString("USERID", userid).commit(); // Storing string
                        editor.putString("USERNAME", name_user).commit(); // Storing string
                        editor.putString("PASS",pass).commit();
                        editor.putString("SALDO", saldo).commit();
                        String USERID = pref.getString("USERID", null); // getting String
                        Log.i("userid", "onlogin=" + USERID);


                        // sharedPref.edit().putString("userid", userid).commit();


                        pDialog.dismiss();
                        // new GetAllTasks().execute();

                        main.putExtra("userdata", userid);
                        //main.putExtra("tasks", arraytask);
                        main.putExtra("name_user", name_user);
                        main.putExtra("saldo", saldo);
                        startActivity(main);
                        finish();

                        // finish();

                    }
                }

            }


                }catch(Exception e){
                    e.printStackTrace();

                pDialog.dismiss();

                DialogFragment dialog = new UserAlertDialogFragment();
                Bundle args = new Bundle();
                args.putString("title", "Error");
                args.putString("message","Error conectando con el servidor, por favor revise su conexión a internet");
                dialog.setArguments(args);
                dialog.setTargetFragment(dialog, 0);
                dialog.show(getSupportFragmentManager(), "tag");
                }
            }

    }


    ////////////


}



