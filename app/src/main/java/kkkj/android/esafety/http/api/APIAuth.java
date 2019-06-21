package kkkj.android.esafety.http.api;


import io.reactivex.Observable;
import kkkj.android.esafety.http.ApiConfig;
import kkkj.android.esafety.login.model.SignInModel;
import kkkj.android.esafety.menu.mine.model.UserSetProfileModel;
import retrofit2.http.Body;
import retrofit2.http.POST;
//app/appUserSignin
public interface APIAuth {
    //APIAuth - api/auth/signin
    // 用户登录
    @POST(ApiConfig.BASE_URL +"api/app/appUserSignin")
    Observable<SignInModel.Response> signin(@Body SignInModel.Request request);

    //APIAuth - api/auth/setprofile
    // 设置用户Profile
    @POST(ApiConfig.BASE_URL +"api/auth/setprofile")
    Observable<UserSetProfileModel.Response> setprofile(@Body UserSetProfileModel.Request request);
}
