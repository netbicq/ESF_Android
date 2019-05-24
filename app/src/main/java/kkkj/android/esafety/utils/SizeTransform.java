package kkkj.android.esafety.utils;

import android.content.Context;

import kkkj.android.esafety.app.ESafety;


public class SizeTransform {
    public static int dip2px(Context context, float dpValue) {
        if(context==null)
        {
            context = ESafety.getInstance().getAppContext();
        }
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
    public static int dip2px( float dpValue) {
        final float scale = ESafety.getInstance().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
