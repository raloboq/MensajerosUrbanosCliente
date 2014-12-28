package co.mensajeros.cliente;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * Created by rene on 11/20/14.
 */
public class Initial extends ActionBarActivity {

    public Typeface font3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_initial);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ffffff")));

        font3 = Typeface.createFromAsset(getApplicationContext().getAssets(), "Analogue56Oblique.ttf");

        int titleId = getResources().getIdentifier("action_bar_title", "id", "android");
        TextView textView = (TextView) findViewById(titleId);
        Typeface typeface = Typeface.create("sans-serif-light", Typeface.ITALIC); // add your typeface
        textView.setTypeface(font3);
        textView.setTextColor(Color.parseColor("#20AAC2"));

        LinearLayout login_layout = (LinearLayout)findViewById(R.id.login_layout);

        LinearLayout register_layout = (LinearLayout)findViewById(R.id.register_layout);

        ImageView login = (ImageView)findViewById(R.id.login_image);

        login_layout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                Intent i = new Intent(Initial.this, Login.class);
                startActivity(i);

                overridePendingTransition(R.anim.upanimation, R.anim.upanimation2);
                return false;
            }
        });

       /*login.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

           }
       });*/

       ImageView register = (ImageView)findViewById(R.id.register_image);

        register_layout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                Intent i = new Intent(Initial.this,Registro.class);
                startActivity(i);

                overridePendingTransition(R.anim.bottomanimation2,R.anim.bottomanimation);
                return false;
            }
        });

        /*register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });*/

    }





}
