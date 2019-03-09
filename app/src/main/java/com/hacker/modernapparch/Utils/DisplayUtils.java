package com.hacker.modernapparch.Utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;

import androidx.appcompat.app.AlertDialog;

public class DisplayUtils {

    private static ProgressDialog pd;

    public static void showAlert(Context context, String title, String message)
    {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(context, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(context);
        }
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                    }
                })

                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    public static void showProgressDialog(Context context)
    {
        pd = new ProgressDialog(context);
        pd.setMessage("loading");
        pd.show();
    }

    public static void dismissProgress()
    {
        if(pd!= null)
            if(pd.isShowing())
                pd.dismiss();

    }

    public static void changeProgress(String message)
    {
        if (pd != null)
        {
            pd.setMessage(message);
        }
    }
}
