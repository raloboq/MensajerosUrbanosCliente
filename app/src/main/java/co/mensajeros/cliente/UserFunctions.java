package co.mensajeros.cliente;

import android.util.Log;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rene on 9/5/14.
 */
public class UserFunctions {

    private JSONParser jsonParser;

    private static String createURL = "http://api.mensajerosurbanos.com//murbanos_api/";
   private static String loginURL = "http://api.mensajerosurbanos.com//site/loginPost/";
    private static String addressURL = "http://api.mensajerosurbanos.com/direcciones";
    private static String costURL = "http://api.mensajerosurbanos.com/task/calculo";
    //private static String buyURL = "http://api.mensajerosurbanos.com/client/create";

    //private static String buyURL = "http://10.0.0.77/murbanos_api/client/create";

    private static String buyURL = "http://dev.api.mensajerosurbanos.co/client/create";


    //private static String loginURL = "http://dev.mensajerosurbanos.co/murbanos_api/site/loginPost/";
    //private static String loginURL = "http://10.0.0.186/murbanos_api/site/loginPost/";

    private static String registerURL = "http://dev.mensajerosurbanos.co/murbanos_api/site/loginPost/";

    //private static String taskListProceso = "http://dev.mensajerosurbanos.co/murbanos_api/task/listenproceso";
    private static String taskListProceso = "http://dev.api.mensajerosurbanos.co/client/list";
    private static String taskasignados = "http://dev.api.mensajerosurbanos.co/task/asignados";

    //private static String taskListProceso = "http://api.mensajerosurbanos.com/client/list";
    //private static String taskasignados = "http://api.mensajerosurbanos.com/task/asignados";




    //private static String ViewURL = "http://dev.mensajerosurbanos.co/murbanos_api/client/view/?model=task&id=";
    private static String ViewURL = "http://dev.api.mensajerosurbanos.co/client/view/?model=task&id=";

    //private static String ViewURL = "http://renelobo@10.0.0.77/murbanos_api/client/view/?model=task&id=";


;
    public UserFunctions(){
        jsonParser = new JSONParser();
    }

    public JSONObject ViewService (String id){

        JSONObject jsonview;

        jsonview = jsonParser.getJSONFromUrlGET(ViewURL+id);
        //jsonview = jsonParser.getJSONFromUrlGET(ViewURL+2670);

        Log.i("view",ViewURL+id);


        return  jsonview;
    }

    public JSONObject CreateTask( String latitude, String longitude,String idtask, String userid){
        // Building Parameters
        Log.i("create", "buying task");
        JSONObject jsonbuy=null;
        List params2 = new ArrayList();

        String jsonb;
        try {

            // 3. build jsonObject
            JSONObject jsonObject = new JSONObject();
            jsonObject.accumulate("task_id", idtask);
            jsonObject.accumulate("recurso_id", userid);
            jsonObject.accumulate("lat", latitude);
            jsonObject.accumulate("long", longitude);

            // 4. convert JSONObject to JSON to String
            jsonb = jsonObject.toString();

            params2.add(new BasicNameValuePair("Buytask",jsonb));
            Log.i("create","json mejor "+ jsonb);
            Log.i("create", "params= " + params2.toString());
            jsonbuy = jsonParser.getJSONFromUrl(createURL, params2);
            if(jsonbuy!=null)
                Log.i("create", "buy json"+jsonbuy.toString());
            else
                Log.i("create","no se pudo crear el task");
        }
        catch  (JSONException e) {

            e.printStackTrace();

            Log.i("create","no se pudo crear el task");
        }

        return jsonbuy;
    }


    public JSONObject loginUser(String email, String password){
        // Building Parameters
        Log.i("login", "entrando login");
        List params = new ArrayList();
        // params.add(new BasicNameValuePair("tag", login_tag));
        params.add(new BasicNameValuePair("username", email));
        params.add(new BasicNameValuePair("password", password));
        Log.i("login", params.toString());
        JSONObject json = jsonParser.getJSONFromUrl(loginURL, params);
        if(json!=null)
            Log.i("login", "json" + json.toString());

        return json;
    }

    public JSONObject registerUser(String email, String nombre, String password, String cedula , String celular, String fbid, String tipo){
        // Building Parameters
        Log.i("registro", "entrando login");
        List paramsRegistro = new ArrayList();
        // params.add(new BasicNameValuePair("tag", login_tag));
        paramsRegistro.add(new BasicNameValuePair("username", email));
        paramsRegistro.add(new BasicNameValuePair("password", password));
        paramsRegistro.add(new BasicNameValuePair("nombre", nombre));
        paramsRegistro.add(new BasicNameValuePair("cedula", cedula));
        paramsRegistro.add(new BasicNameValuePair("celuar", celular));
        paramsRegistro.add(new BasicNameValuePair("tipo", tipo));
        paramsRegistro.add(new BasicNameValuePair("fbid", fbid));


        Log.i("registro", paramsRegistro.toString());

        //JSONObject json = new JSONObject() ;

        JSONObject json = jsonParser.getJSONFromUrl(registerURL, paramsRegistro);


        if(json!=null)
            Log.i("registro", "json" + json.toString());

        return json;
    }

    public JSONObject CHeckDirections(String Direction){
        // Building Parameters
        Log.i("login", "entrando login");
        List params = new ArrayList();
        params.add(new BasicNameValuePair("", Direction));

        Log.i("login", params.toString());
        //String URL="http://dev.mensajerosurbanos.co/murbanos_api/direcciones?address=calle+52+A+25+79&address=calle+34+13+34";
        String URL=addressURL+"?address="+Direction.replace(" ","+");
        Log.i("checkDirections",URL);
        JSONObject json = jsonParser.getJSONFromUrlGET(URL);
        if(json!=null)
            Log.i("login", "json" + json.toString());

        return json;
    }



    public JSONObject CHeckPrice(ServiceObject sobject){
        // Building Parameters
        Log.i("checkp", "checking price");
        JSONObject jsonbuy=null;
        List params2 = new ArrayList();
        // params.add(new BasicNameValuePair("tag", login_tag));
        String jsonb;
        try {

            // 3. build jsonObject
            JSONObject jsonObject = new JSONObject();
            jsonObject.accumulate("tipo_servicio", sobject.getTipo_servicio());
            //jsonObject.accumulate("tipo_servicio", "1");
            jsonObject.accumulate("ida_vuelta", sobject.getIdayVuelta());
            //jsonObject.accumulate("ida_vuelta", "0");
            jsonObject.accumulate("valor_declarado", sobject.getValor_declarado());
            //jsonObject.accumulate("valor_declarado", "1000");
            jsonObject.accumulate("codigo_prom", "1"); //o el codigo que escriba el man
            JSONArray address=new JSONArray();
            JSONObject dir;
            for(int i=0;i<sobject.getDirecciones().size();i++)
            {
                dir = new JSONObject();
               // dir.accumulate("lat",sobject.getDirecciones().get(i).getLatitude());
               // dir.accumulate("lng",sobject.getDirecciones().get(i).getLongitude());
               //
                Log.i("dirgrrr",sobject.getDirecciones().size()+" i= "+i+sobject.getDirecciones().get(i).getAddress()+" "+sobject.getDirecciones().get(i).getLatitude()+" "+sobject.getDirecciones().get(i).getLongitude() );
                dir.put("lat", sobject.getDirecciones().get(i).getLatitude());
                dir.put("lng",sobject.getDirecciones().get(i).getLongitude());
                address.put(dir);
            }
            /*for(int i=0;i<2;i++)
            {


                dir.put("lat","4.6915503");
                dir.put("lng","-74.0711358");
                address.put(dir);
            }*/

            for(int z=0;z<address.length();z++){
            Log.i("aaaaa","a ver "+address.get(z)+"");

            }

            jsonObject.accumulate("direcciones", address);



            // 4. convert JSONObject to JSON to String
            jsonb = jsonObject.toString();

            params2.add(new BasicNameValuePair("calculo",jsonb));
            Log.i("checkp","json mejor "+ jsonb);
            Log.i("checkp", "params= " + params2.toString());
            jsonbuy = jsonParser.getJSONFromUrl(costURL, params2);
            if(jsonbuy!=null)
                Log.i("checkp", "checkp json"+jsonbuy.toString());
            else
                Log.i("checkp","no se pudo traer el valor");
        }
        catch  (JSONException e) {

            e.printStackTrace();

            Log.i("checkp","no se pudo pedir el task");
        }

        return jsonbuy;
    }


    public JSONObject Buy(ServiceObject sobject,String uid, String pass){
        // Building Parameters
        Log.i("Buytask", "But task "+uid+" "+pass);
        JSONObject jsoncreate=null;
        List params3 = new ArrayList();
        // params.add(new BasicNameValuePair("tag", login_tag));
        String jsonc;
        try {

            // 3. build jsonObject
            JSONObject jsonObject = new JSONObject();
            //jsonObject.accumulate("userid",uid);
            jsonObject.accumulate("tipo_servicio", sobject.getTipo_servicio());
            jsonObject.accumulate("ida_vuelta", sobject.getIdayVuelta());
            if(!sobject.getValor_declarado().equals(""))
            jsonObject.accumulate("valor_declarado", sobject.getValor_declarado());
            else
                jsonObject.accumulate("valor_declarado", "0");
            jsonObject.accumulate("codigo_prom", "1"); //o el codigo que escriba el man
            jsonObject.accumulate("descripcion",sobject.getDescripcion());
            jsonObject.accumulate("fecha_inicio",sobject.getFecha_recogida());
            jsonObject.accumulate("hora_inicio",sobject.getHora_recogida());
            JSONArray create=new JSONArray();
            JSONObject dir = new JSONObject();
            for(int i=0;i<sobject.getDirecciones().size();i++)
            {

                int index = i+1;
                dir.put(""+index,sobject.getDirecciones().get(i).getAddress());
                //dir.put("lng",sobject.getDirecciones().get(i).getLongitude());

            }

            create.put(dir);
            jsonObject.accumulate("direcciones", create);

            // 4. convert JSONObject to JSON to String
            jsonc = jsonObject.toString();
            jsonc=jsonc.replace("[","");
            jsonc=jsonc.replace("]","");

            params3.add(new BasicNameValuePair("create",jsonc));

            Log.i("checkcreate","json mejor "+ jsonc);
            Log.i("checkcreate", "params= " + params3.toString());
            jsoncreate = jsonParser.getJSONFromUrlAuth(buyURL, params3,"rsocarras80","1234");
            if(jsoncreate!=null)
                Log.i("checkcreate", "checkp json"+jsoncreate.toString());
            else
                Log.i("checkcreate","no se pudo traer el valor");
        }
        catch  (JSONException e) {

            e.printStackTrace();

            Log.i("checkcreate","no se pudo pedir el task");
        }

        return jsoncreate;
    }

    public JSONArray ListActiveServ(String userid){
        // Building Parameters
        Log.i("lista","listando");

        JSONArray jsonactive=null;

        List paramsa = new ArrayList();


        String jsonba;

        try {

            // 3. build jsonObject
            JSONObject jsonObjectActive = new JSONObject();


            jsonObjectActive.accumulate("model","task");
            jsonObjectActive.accumulate("id_user",userid);


            // 4. convert JSONObject to JSON to String
            jsonba = jsonObjectActive.toString();


            paramsa.add(new BasicNameValuePair("lista",jsonba));

            Log.i("lista","json mejor "+ jsonba);
            Log.i("lista", "params= " + paramsa.toString());

            //String rtaprueba= "[{\"id\":\"1813\",\"type_task_id\":\"1\",\"valor_total\":\"1000\",\"estado\":\"activo\",\"hora_inicio\":\"11:00\",\"fecha_inicio\":\"17-12-2014\"},{\"id\":\"1813\",\"type_task_id\":\"1\",\"valor_total\":\"1000\",\"estado\":\"activo\",\"hora_inicio\":\"11:00\",\"fecha_inicio\":\"17-12-2014\"},{\"id\":\"1813\",\"type_task_id\":\"1\",\"valor_total\":\"1000\",\"estado\":\"finalizado\",\"hora_inicio\":\"11:00\",\"fecha_inicio\":\"17-12-2014\"},{\"id\":\"1813\",\"type_task_id\":\"1\",\"valor_total\":\"1000\",\"estado\":\"finalizado\",\"hora_inicio\":\"11:00\",\"fecha_inicio\":\"17-12-2014\"}]";
            //Log.i("lista","prueba "+rtaprueba);

            //jsonactive = new JSONArray(rtaprueba);




            jsonactive = jsonParser.getJSONFromUrl2(taskListProceso, paramsa);

            if(jsonactive!=null)
                Log.i("lista", "lista json"+jsonactive.toString());
            else
                Log.i("lista","no se pudo listar");
        }
        catch  (JSONException e) {

            e.printStackTrace();

            Log.i("lista","checking task error ");
        }



        return jsonactive;
    }

    public JSONArray CheckAsigBuyTask(String userid, String idtask){
        // Building Parameters

        JSONArray jsonbuy=null;

        List params2 = new ArrayList();
        String jsonb;

        try {

            // 3. build jsonObject
            JSONObject jsonObject = new JSONObject();
            jsonObject.accumulate("recurso_id", userid);
            jsonObject.accumulate("task_id", idtask);

            // 4. convert JSONObject to JSON to String
            jsonb = jsonObject.toString();

            params2.add(new BasicNameValuePair("Info",jsonb));

            Log.i("cbuyactive","json mejor "+ jsonb);
            Log.i("cbuyactive", "params= " + params2.toString());

            jsonbuy = jsonParser.getJSONFromUrl2(taskasignados, params2);

            if(jsonbuy!=null)
                Log.i("cbuyactive", "buy json"+jsonbuy.toString());
            else
                Log.i("cbuyactive","no se pudo enviar el track");
        }
        catch  (JSONException e) {

            e.printStackTrace();

            Log.i("cbuyactive","checking task error ");
        }

        return jsonbuy;
    }


}