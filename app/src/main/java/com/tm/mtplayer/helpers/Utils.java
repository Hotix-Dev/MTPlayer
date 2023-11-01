package com.tm.mtplayer.helpers;

import android.graphics.Color;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;

public final class Utils {

    private Utils() {

    }

    public static void showSnackbar(View view, String msg) {
        final Snackbar snackBar = Snackbar.make(view, msg, Snackbar.LENGTH_INDEFINITE);
        snackBar.setAction("OK", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                snackBar.dismiss();
            }
        }).setActionTextColor(Color.WHITE).show();
    }

    public static Boolean stringIsNullOrEmpty(String text) {
        try {
            return ((text == null) || (text.isEmpty()) || (text.trim().equals("")));

        } catch (Exception ex) {
            ex.printStackTrace();
            return true;
        }
    }

}
