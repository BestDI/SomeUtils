package com.tong.util.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ToastManager
{

    public static void show(Context mContext, int resId)
    {
        show(mContext, resId, Toast.LENGTH_SHORT);
    }

    public static void show(Context mContext, String textStr)
    {
        show(mContext, textStr, Toast.LENGTH_SHORT);
    }

    public static void show(Context mContext, int resId, int duration)
    {
        show(mContext, mContext.getResources()
                               .getString(resId), duration);
    }

    public static void show(Context mContext, String textStr, int duration)
    {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.toast, null);
        TextView toastView = (TextView) view.findViewById(R.id.hb_toast);
        toastView.setText(textStr);

        Toast toast = new Toast(mContext);
        toast.setView(view);
        toast.setDuration(duration);
        toast.show();
    }

}
