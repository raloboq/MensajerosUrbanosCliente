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
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;




public class Login extends Activity {

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Crashlytics.start(this);
        setContentView(R.layout.activity_login);



        pref = getApplicationContext().getSharedPreferences("UrbanosPref", 0); // 0 - for private mode
        editor = pref.edit();

        String useridpref = pref.getString("USERID", null);
        if(useridpref !=null) {
            Log.i("login", "ya logueado " + useridpref);
            Intent main = new Intent(getApplicationContext(), main.class);
            startActivity(main);
            finish();

        }
       // else
       // Log.i("login","login pref = null");
        //ActionBar actionBar = getSupportActionBar();
        //actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#37B0C8")));


        UserName = (EditText)findViewById(R.id.UserText);
        Password = (EditText)findViewById(R.id.PasswordText);
        Loginbutton = (Button)findViewById(R.id.Loginbutton);
        resultText = (TextView)findViewById(R.id.resultText);

        Loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!UserName.getText().equals("") && !Password.getText().equals(""))
                {
                    new ProcessLogin().execute();

                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
        String user,pass;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            user = UserName.getText().toString();
            pass = Password.getText().toString();
            Log.i("login",user+" "+pass);
            pDialog = new ProgressDialog(Login.this);
            pDialog.setTitle("Contacting Servers");
            pDialog.setMessage("Logging in ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }
        @Override
        protected JSONObject doInBackground(String... args) {
            UserFunctions userFunction = new UserFunctions();
            JSONObject json = userFunction.loginUser(user, pass);
            //Log.i("login1",json.toString());
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
                    Log.i("error", json.getString("Error"));
                    resultText.setText(json.getString("Error"));
                    pDialog.dismiss();
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


                        pDialog.setMessage("Loading User Space");
                        pDialog.setTitle("Getting Data");

                        //resultText.setText(json.getString(KEY_USERNAME)+json.getString(KEY_UID)+json.getString(KEY_CREATED_AT));ç
                        resultText.setText("Bienvenido");
                        /**
                         *If JSON array details are stored in SQlite it launches the User Panel.
                         **/
                        Intent main = new Intent(getApplicationContext(), main.class);
                        userid = json.getString("user");
                        name_user = json.getString("nombre");
                        saldo = json.getString("saldo");
                        Log.i("userid", "login=" + userid);
                        editor.putString("USERID", userid).commit(); // Storing string
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


                }catch(JSONException e){
                    e.printStackTrace();
                }
            }

    }


    ////////////


}



