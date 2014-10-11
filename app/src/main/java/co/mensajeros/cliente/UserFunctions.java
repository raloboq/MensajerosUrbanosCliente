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

    public UserFunctions(){
        jsonParser = new JSONParser();
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

    public JSONObject CHeckDirections(String Direction){
        // Building Parameters
        Log.i("login", "entrando login");
        List params = new ArrayList();
        // params.add(new BasicNameValuePair("tag", login_tag));
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
            jsonObject.accumulate("ida_vuelta", sobject.getIdayVuelta());
            jsonObject.accumulate("valor_declarado", sobject.getValor_declarado());
            jsonObject.accumulate("codigo_prom", "1");
            JSONArray address=new JSONArray();
            JSONObject dir = new JSONObject();
            for(int i=0;i<sobject.getDirecciones().size();i++)
            {
               // dir.accumulate("lat",sobject.getDirecciones().get(i).getLatitude());
               // dir.accumulate("lng",sobject.getDirecciones().get(i).getLongitude());
               //
                dir.put("lat",sobject.getDirecciones().get(i).getLatitude());
                dir.put("lng",sobject.getDirecciones().get(i).getLongitude());
                address.put(dir);
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
}
