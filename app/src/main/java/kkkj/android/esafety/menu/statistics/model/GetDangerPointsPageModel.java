package kkkj.android.esafety.menu.statistics.model;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import kkkj.android.esafety.bean.PagerOfAPPDangerPointView;
import kkkj.android.esafety.http.ESafetyRequest;
import kkkj.android.esafety.http.ESafetyResponse;
import kkkj.android.esafety.mvpInterface.MvpCallback;
import kkkj.android.esafety.mvpInterface.MvpModel;

public class GetDangerPointsPageModel extends MvpModel<GetDangerPointsPageModel.Request, GetDangerPointsPageModel.Response> {
    @Override
    public void getResponse(Request request, MvpCallback<Response> callback) {
        apiApp.getDangerPointsPage(request)
                .subscribeOn(Schedulers.io())//IO线程加载数据
                .observeOn(AndroidSchedulers.mainThread())//主线程显示数据
                .subscribe(new Observer<Response>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(GetDangerPointsPageModel.Response response) {
                        if (response.getState() == RESPONSE_OK) {
                            callback.onSuccess(response);
                        } else {
                            callback.onFailure(response.getMsg());
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        callback.onError(t);
                    }

                    @Override
                    public void onComplete() {
                        callback.onComplete();
                    }
                });
    }

    public static class Response extends ESafetyResponse {
        PagerOfAPPDangerPointView data;

        public PagerOfAPPDangerPointView getData() {
            if(data==null)
            {
                return new PagerOfAPPDangerPointView();
            }
            return data;
        }
    }

    public static class Request extends ESafetyRequest {
        int PageSize;//每页返回条数
        int PageIndex;//页数
        String Query;//风险点id

        public void setPageSize(int pageSize) {
            PageSize = pageSize;
        }

        public void setPageIndex(int pageIndex) {
            PageIndex = pageIndex;
        }

        public void setQuery(String query) {
            Query = query;
        }
    }
}
