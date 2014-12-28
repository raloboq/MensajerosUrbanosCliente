package co.mensajeros.cliente;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Rene on 9/9/14.
 */


public class AddressErrorDialogFragment2 extends DialogFragment
{
    public AddressErrorDialogFragment2()
    {

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Bundle args = getArguments();

        String title = args.getString("title");
        String message = args.getString("message");


        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        //AlertDialog.Builder builder = new AlertDialog.Builder()

        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_address_error, null);

        TextView text = (TextView)view.findViewById(R.id.AddressErrorMessage);
        text.setText(message);

        Button ingresar = (Button)view.findViewById(R.id.ingresarButton);

        ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });




        //view.setBackgroundResource(android.graphics.Color.TRANSPARENT);
        //view.setBackgroundColor(android.graphics.Color.TRANSPARENT);

        //dialog.getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
       // dialog.getActivity().getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));


        builder.setView(view);

        return builder.create();


    }

}