package kkkj.android.esafety.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import kkkj.android.esafety.app.ESafety;

public class NetWorkUtils {

    public static boolean isNetWorkAvailable() {

        ConnectivityManager manager = (ConnectivityManager) ESafety.getInstance().getAppContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();

        if (networkInfo != null) {

            if (networkInfo.getDetailedState().equals(NetworkInfo.DetailedState.CONNECTED)) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }


}
