package org.meicode.mymovie;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.widget.TextView;

public class LoadingDialogBar {
    Context context;
    Dialog dialog;

    public LoadingDialogBar(Context context) {
        this.context = context;
    }
    public void showDialog(String title){
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        TextView textView = dialog.findViewById(R.id.textview);
        textView.setText(title);
        dialog.create();;
        dialog.show();
    }
    public void hideDialog(){
        dialog.dismiss();
    }
}
