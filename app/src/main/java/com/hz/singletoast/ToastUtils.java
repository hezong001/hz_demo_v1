package com.hz.singletoast;

import android.content.Context;
import android.widget.Toast;

/**
 * @createMan: hezong
 * @createTime: 2017/1/1 22:54
 * @describe: ${TODO}
 */

public class ToastUtils {

    private static Toast sToast;

    public static void showToast(Context context, String msg) {
        if (sToast == null) {
            sToast = Toast.makeText(context, msg, Toast.LENGTH_LONG);
        }
        sToast.setText(msg);
        sToast.show();
    }
}
