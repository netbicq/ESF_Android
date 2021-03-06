package kkkj.android.esafety.app;

import android.app.Activity;
import android.content.Context;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.tencent.bugly.Bugly;
import com.tencent.smtt.sdk.QbSdk;
import com.uuzuche.lib_zxing.ZApplication;

import org.litepal.LitePal;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import kkkj.android.esafety.R;
import kkkj.android.esafety.bean.Auth_UserProfile;
import kkkj.android.esafety.utils.SharedPreferenceUtil;


public class ESafety extends ZApplication {
    private static ESafety instance;
    private Set<Activity> allActivities;
    private static Context mContext;
    private Map<String, String> commonparts;

    public Auth_UserProfile getUserProfile() {
        Auth_UserProfile userProfile = new Auth_UserProfile();
        userProfile.setLogin(SharedPreferenceUtil.getString(SharedPreferenceUtil.SP_UserProfile_Login));
        userProfile.setCNName(SharedPreferenceUtil.getString(SharedPreferenceUtil.SP_UserProfile_CNName));
        userProfile.setTel(SharedPreferenceUtil.getString(SharedPreferenceUtil.SP_UserProfile_Tel));
        userProfile.setID(SharedPreferenceUtil.getString(SharedPreferenceUtil.SP_UserProfile_ID));
        userProfile.setHeadIMG(SharedPreferenceUtil.getString(SharedPreferenceUtil.SP_UserProfile_HeadIMG));
        return userProfile;
    }

    public void setUserProfile(Auth_UserProfile userProfile) {
        SharedPreferenceUtil.setString(SharedPreferenceUtil.SP_UserProfile_Login, userProfile.getLogin());
        SharedPreferenceUtil.setString(SharedPreferenceUtil.SP_UserProfile_CNName, userProfile.getCNName());
        SharedPreferenceUtil.setString(SharedPreferenceUtil.SP_UserProfile_Tel, userProfile.getTel());
        SharedPreferenceUtil.setString(SharedPreferenceUtil.SP_UserProfile_ID, userProfile.getID());
        SharedPreferenceUtil.setString(SharedPreferenceUtil.SP_UserProfile_HeadIMG, userProfile.getHeadIMG());
    }

    static {
        //???????????????Header?????????
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                layout.setPrimaryColorsId(R.color.theme, android.R.color.white);//????????????????????????
                return new MaterialHeader(context);//.setTimeFormat(new DynamicTimeFormat("????????? %s"));//???????????????Header???????????? ???????????????Header
            }
        });
        //???????????????Footer?????????
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //???????????????Footer???????????? BallPulseFooter
                return new ClassicsFooter(context).setDrawableSize(20);
            }
        });
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;

        Thread.setDefaultUncaughtExceptionHandler(new CrashHandler(mContext));
        LitePal.initialize(this);
        instance = this;
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .tag("ESafety")
                .build();
        //????????????
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));
//        FormatStrategy formatStrategy = CsvFormatStrategy.newBuilder()
//                .tag("NDRestructure")
//                .build()
//        ???????????????????????????logger
//        Logger.addLogAdapter(new DiskLogAdapter(formatStrategy));
//        Bugly.init(getApplicationContext(), "3dee6816b9", false);
        Logger.d("Logger???????????????");
        //????????????debug?????????true????????????debug?????????false????????????????????????
        Bugly.init(mContext, "b001877a61", false);//  0776eeab1a
        initX5WebView();
    }
    public static synchronized ESafety getInstance() {
        return instance;
    }

    public Map<String, String> getCommonParts() {
        if(commonparts==null)
        {
            commonparts = new HashMap<String, String>();
        }
        return commonparts;
    }

    public void setCommonParts(String token, String accountID) {
        if(commonparts==null)
        {
            commonparts = new HashMap<>();
        }
        commonparts.put("Token", token);
        commonparts.put("AccountID",accountID);
    }

    public void addActivity(Activity act) {
        if (allActivities == null) {
            allActivities = new HashSet<>();
        }
        allActivities.add(act);
    }

    public void initX5WebView() {
        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {

            @Override
            public void onViewInitFinished(boolean arg0) {
                // TODO Auto-generated method stub
                //x5????????????????????????????????????true??????x5?????????????????????????????????x5??????????????????????????????????????????????????????
                Logger.d(" onViewInitFinished is " + arg0);
            }

            @Override
            public void onCoreInitFinished() {
                // TODO Auto-generated method stub
                Logger.d(" onCoreInitFinished is ");
            }
        };
        //x5?????????????????????
        QbSdk.initX5Environment(getApplicationContext(), cb);
//        Logger.d("initX5Environment");
    }

    public void removeActivity(Activity act) {
        if (allActivities != null) {
            allActivities.remove(act);
        }
    }

    public Context getAppContext() {
        return this;
    }

    /**
     * ??????
     */
    public void exitApp() {
        if (allActivities != null) {
            synchronized (allActivities) {
                for (Activity act : allActivities) {
                    act.finish();
                }
            }
        }
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }
}
