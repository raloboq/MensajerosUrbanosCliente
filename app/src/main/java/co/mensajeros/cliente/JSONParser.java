package co.mensajeros.cliente;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by Rene on 9/5/14.
 */
public class JSONParser {
    static InputStream is = null;
    static JSONObject jObj = null;
    static JSONObject jObjc = null;
    static JSONArray jArray = null;
    static String json = "";
    static String jsonc = "";
    // constructor
    public JSONParser() {
    }

    public JSONObject getJSONFromUrlAuth(String url, List params,String id,String pass) {
        // Making HTTP request
        try {
            // defaultHttpClient
            Log.i("checkcreate","ids="+id+" "+pass);
            DefaultHttpClient httpClient = new DefaultHttpClient();

            HttpPost httpPost = new HttpPost(url);
            httpPost.addHeader(BasicScheme.authenticate(new UsernamePasswordCredentials(id, pass), "UTF-8", false));
            httpPost.setEntity(new UrlEncodedFormEntity(params));

            Log.i("create", "enviando request");
            HttpResponse httpResponse = httpClient.execute(httpPost);
            Log.i("create","obteniendo response "+httpResponse);
            HttpEntity httpEntity = httpResponse.getEntity();
            is = httpEntity.getContent();
            Log.i("parser","is="+is.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    is, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "n");
                Log.i("parser","line= "+line);
            }
            is.close();
            jsonc = sb.toString();

            Log.e("JSON", jsonc);
        } catch (Exception e) {
            Log.e("Buffer Error", "Error converting result " + e.toString());
        }
        // try parse the string to a JSON object
        try {
            jObjc = new JSONObject(jsonc);
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }
        // return JSON String
        return jObjc;
    }


    public JSONObject getJSONFromUrl(String url, List params) {
        // Making HTTP request
        try {
            // defaultHttpClient
            DefaultHttpClient httpClient = new DefaultHttpClient();

            HttpPost httpPost = new HttpPost(url);



            httpPost.setEntity(new UrlEncodedFormEntity(params));

            Log.i("buy", "enviando request");
            HttpResponse httpResponse = httpClient.execute(httpPost);
            Log.i("buy","obteniendo response");
            HttpEntity httpEntity = httpResponse.getEntity();
            is = httpEntity.getContent();
            Log.i("parser","is="+is.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    is, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "n");
                Log.i("parser","line= "+line);
            }
            is.close();
            json = sb.toString();

            Log.e("JSON", json);
        } catch (Exception e) {
            Log.e("Buffer Error", "Error converting result " + e.toString());
        }
        // try parse the string to a JSON object
        try {
            jObj = new JSONObject(json);
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }
        // return JSON String
        return jObj;
    }

    public JSONArray getJSONFromUrl2(String url, List params) {
        // Making HTTP request
        try {
            // defaultHttpClient
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);
            httpPost.setEntity(new UrlEncodedFormEntity(params));
            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            is = httpEntity.getContent();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    is, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "n");
            }
            is.close();
            json = sb.toString();
            Log.e("JSON", json);
        } catch (Exception e) {
            Log.e("Buffer Error", "Error converting result " + e.toString());
        }
        // try parse the string to a JSON object
        try {
            //jObj = new JSONObject(json);
            jArray = new JSONArray(json);
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }
        // return JSON String
        return jArray;
    }



    public JSONObject getJSONFromUrlGET(String url) {
        // Making HTTP request
        try {
            // defaultHttpClient
            DefaultHttpClient httpClient = new DefaultHttpClient();

            HttpGet httpGET = new HttpGet(url);
           // httpGET.addHeader("PHP_AUTH_USER", "rene.lobo");
           // httpGET.addHeader("PHP_AUTH_PW", "1234");

            httpGET.addHeader(BasicScheme.authenticate(new UsernamePasswordCredentials("rsocarras80", "1234"), "UTF-8", false));

            //httpGET.setEntity(new UrlEncodedFormEntity(params));
            Log.i("GET", "enviando request " + url);
            HttpResponse httpResponse = httpClient.execute(httpGET);
            Log.i("GET","obteniendo response");
            HttpEntity httpEntity = httpResponse.getEntity();
            is = httpEntity.getContent();
            Log.i("GET","is="+is.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    is, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "n");
                Log.i("parser","line= "+line);
            }
            is.close();
            json = sb.toString();

            Log.e("JSON", json);
        } catch (Exception e) {
            Log.e("Buffer Error", "Error converting result " + e.toString());
        }
        // try parse the string to a JSON object
        try {
            jObj = new JSONObject(json);
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }
        // return JSON String
        return jObj;
    }


}

