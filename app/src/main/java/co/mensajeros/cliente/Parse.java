package co.mensajeros.cliente;

import android.app.Application;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.provider.Settings;
import android.util.Log;

import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.PushService;
import com.parse.SaveCallback;



import android.app.Application;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.provider.Settings;
import android.util.Log;

import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.PushService;
import com.parse.SaveCallback;

/**
 * Created by Rene on 9/2/14.
 */
public class Parse extends Application {

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    String Userid;

    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onCreate()
    {

        super.onCreate();

        pref = getSharedPreferences("UrbanosPref", 0);
        Userid = pref.getString("USERID", "");

        String  android_id = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
        Log.e("LOG", "android id >>" + android_id);


        com.parse.Parse.initialize(getApplicationContext(), "OEvkcD6jAJkRbCkhLk9yEfJ6nrRkLm9Lr8vtUHM1", "pi9epTn3ZzYZJhHWsfvwu2IQUMSxa1waEGUsyvba");
        //PushService.setDefaultPushCallback(this, main.class);
        ParseInstallation installation = ParseInstallation.getCurrentInstallation();
        installation.put("UniqueId",android_id);
        installation.put("userID", Userid);

        Log.i("usern",Userid);
        //installation.saveInBackground();

        ParseInstallation.getCurrentInstallation().saveInBackground(new SaveCallback()
        {
            @Override
            public void done(ParseException e)
            {
                PushService.setDefaultPushCallback(getApplicationContext(), main.class);

            }
        });


    }

    @Override
    public void onLowMemory()
    {
        // TODO Auto-generated method stub
        super.onLowMemory();
    }

    @Override
    public void onTerminate()
    {
        // TODO Auto-generated method stub
        super.onTerminate();
    }



}
