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

        TextView rate = (TextView)rootView.findViewById(R.id.ratetextView);
        rate.setTypeface(font1);
        TextView faq = (TextView)rootView.findViewById(R.id.faqtextView2);
        faq.setTypeface(font1);
        TextView call = (TextView)rootView.findViewById(R.id.calltextView3);
        call.setTypeface(font1);
        TextView feedback = (TextView)rootView.findViewById(R.id.feedbacktextView4);
        feedback.setTypeface(font1);
        TextView work = (TextView)rootView.findViewById(R.id.worktextView5);
        work.setTypeface(font1);
        TextView privacy = (TextView)rootView.findViewById(R.id.privacytextView6);
        privacy.setTypeface(font1);
        TextView terms = (TextView)rootView.findViewById(R.id.termstextView7);
        terms.setMovementMethod(LinkMovementMethod.getInstance());
        terms.setTypeface(font1);

        rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        faq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        work.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        privacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });



    return  rootView;
    }
}
