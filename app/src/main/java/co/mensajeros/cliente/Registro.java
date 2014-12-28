package co.mensajeros.cliente;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by rene on 11/5/14.
 */
public class Registro extends Activity {

    RegisterObject registroobj=null;
    EditText Tipo_usuario;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    ServiceObject serv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_registro);

        pref = getApplicationContext().getSharedPreferences("UrbanosPref", 0); // 0 - for private mode
        editor = pref.edit();





        final EditText correo = (EditText)findViewById(R.id.Correo);

        final EditText nombre = (EditText)findViewById(R.id.Nombreusuario);

        final EditText contraseña = (EditText)findViewById(R.id.Password);

        final EditText Cedula = (EditText)findViewById(R.id.Cedula);

        final EditText Celular = (EditText)findViewById(R.id.Celular);

        /*Tipo_usuario = (EditText)findViewById(R.id.TipoUsuario);

        Tipo_usuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Showdialog();
            }
        });

        Tipo_usuario.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                Showdialog();
            }
        });

*/


        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            if(extras.containsKey("registro")) {
                registroobj = new RegisterObject();
                registroobj = (RegisterObject) extras.getSerializable("registro");


                correo.setText(registroobj.getEmail());
                nombre.setText(registroobj.getNombre());
            }
            if(extras.containsKey("serviceobject")) {

                Log.i("serv","contain");
                serv = new ServiceObject();

                serv = (ServiceObject) extras.getSerializable("serviceobject");
                Log.i("serv","contain"+serv.getDescripcion());
            }
        }



        final Button registrar = (Button)findViewById(R.id.Registerbutton);

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!correo.getText().equals("") && !nombre.getText().equals("") && !contraseña.getText().equals("") && !Cedula.getText().equals("") && !Celular.getText().equals("")){

                    if(registroobj!=null) {
                        Log.i("register","facebook");
                        new ProcessRegistro().execute(correo.getText().toString(), nombre.getText().toString(), contraseña.getText().toString(), Cedula.getText().toString(), Celular.getText().toString(), "facebook", registroobj.getFbId());
                    }
                    else {
                        Log.i("register","normal");
                        new ProcessRegistro().execute(correo.getText().toString(), nombre.getText().toString(), contraseña.getText().toString(), Cedula.getText().toString(), Celular.getText().toString(), "normal");
                    }
                }



            }
        });









    }

    /*public void Showdialog(){

        AlertDialog.Builder builder = new AlertDialog.Builder(Registro.this);

        builder.setTitle(R.string.tipousuario)
                .setItems(R.array.tipousuarios, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // The 'which' argument contains the index position
                        // of the selected item
                        if(which==0)
                            Tipo_usuario.setText("Persona natural");
                        if(which==1)
                            Tipo_usuario.setText("Empresa");
                    }
                });

        AlertDialog dialog = builder.create();

        dialog.show();


    }*/

    private class ProcessRegistro extends AsyncTask<String, String, JSONObject> {
        private ProgressDialog pDialog;
        String user;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();



           /* pDialog = new ProgressDialog(Registro.this);
            pDialog.setTitle("Contactando servidor");
            pDialog.setMessage("Registrando ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();*/
        }
        @Override
        protected JSONObject doInBackground(String... args) {
            UserFunctions userFunction = new UserFunctions();
            JSONObject json;
            if(args[5].equals("normal"))
            {
                json = userFunction.registerUser(args[0], args[1], args[2], args[3], args[4],args[5],"");
            }
            else
            {
                json = userFunction.registerUser(args[0], args[1], args[2], args[3], args[4],args[5],args[6]);
            }

            //Log.i("login1",json.toString());
            return json;
        }
        @Override
        protected void onPostExecute(JSONObject json) {


            Intent main = new Intent(getApplicationContext(), MainActivityPager.class);

            main.putExtra("serviceobject",serv);
            //main.putExtra("registrado","true");
            editor.putBoolean("ANONYMOUS",false).commit();
            startActivity(main);
            finish();


            /*try {

                if (!json.isNull("Error")) {
                    Log.i("error", json.getString("Error"));
                    //resultText.setText(json.getString("Error"));
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


                            boolean servicio_pendiente = pref.getBoolean("PENDIENTE", false);

                            Intent main = new Intent(getApplicationContext(), main.class);
                                //userid = json.getString("user");
                                //name_user = json.getString("nombre");
                                //saldo = json.getString("saldo");
                                //Log.i("userid", "login=" + userid);
                                //editor.putString("USERID", userid).commit(); // Storing string
                                //editor.putString("USERNAME", name_user).commit(); // Storing string
                                //editor.putString("PASS",pass).commit();
                                //editor.putString("SALDO", saldo).commit();
                                //String USERID = pref.getString("USERID", null); // getting String
                                //Log.i("userid", "onlogin=" + USERID);


                            // sharedPref.edit().putString("userid", userid).commit();


                            //pDialog.dismiss();
                            // new GetAllTasks().execute();

                                //main.putExtra("userdata", userid);
                                //main.putExtra("tasks", arraytask);
                                //main.putExtra("name_user", name_user);
                                //main.putExtra("saldo", saldo);
                            startActivity(main);
                            finish();
                        }
                            else{

                           // Bundle data = new Bundle();
                            //data.putSerializable("serviceobject",service);
                            //NewService4 nservice4 = new NewService4();
                            //nservice4.setArguments(data);
                            //getApplicationContext().getSupportFragmentManager().beginTransaction()
                             //       .add(R.id.container, nservice4)
                             //       .addToBackStack("n1")
                             //       .commit();
                        }

                            // finish();

                        }
                    }

                }


            catch(JSONException e){
                e.printStackTrace();
            }*/
        }

    }

}
