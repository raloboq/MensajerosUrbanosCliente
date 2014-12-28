package co.mensajeros.cliente;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Rene on 9/22/14.
 */
public class Help_faqs extends Fragment {

    public Typeface font1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_help_faq, container, false);

        font1 = Typeface.createFromAsset(getActivity().getAssets(), "Raleway-Light.ttf");

        Button Contact_button = (Button)rootView.findViewById(R.id.Contact_button);
        Contact_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String url = "http://mensajerosurbanos.com/contactenos.mu";

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);

            }
        });

        Button terminos = (Button)rootView.findViewById(R.id.terminos_button);
        terminos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String url = "http://mensajerosurbanos.com/site/terminos.mu";

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);

            }
        });

        Button politicas = (Button)rootView.findViewById(R.id.politicas_button);
        politicas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String url = "http://mensajerosurbanos.com/site/politicas.mu";

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);

            }
        });

        Button califica = (Button)rootView.findViewById(R.id.califica_button);
        califica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                String url =  "https://play.google.com/store/apps/details?id=co.mensajeros.cliente";

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);

            }
        });


    return  rootView;
    }
}
