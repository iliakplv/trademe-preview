package com.iliakplv.trademepreview.common;


import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import com.iliakplv.trademepreview.R;

public final class UiUtils {

    public static void showSnackbar(@NonNull View view, @StringRes int stringId) {
        final Snackbar snackbar = Snackbar.make(view, stringId, Snackbar.LENGTH_LONG);
        final TextView textView = (TextView) snackbar.getView()
                .findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                view.getContext().getResources().getDimension(R.dimen.snackbar_text_size));
        snackbar.show();
    }

}
