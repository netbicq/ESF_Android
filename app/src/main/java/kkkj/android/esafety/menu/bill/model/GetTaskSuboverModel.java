package kkkj.android.esafety.menu.bill.model;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import kkkj.android.esafety.bean.TaskSubjectView;
import kkkj.android.esafety.http.ESafetyRequest;
import kkkj.android.esafety.http.ESafetyResponse;
import kkkj.android.esafety.mvpInterface.MvpCallback;
import kkkj.android.esafety.mvpInterface.MvpModel;

import static kkkj.android.esafety.utils.NetUtils.checkNetWork;


public class GetTaskSuboverModel extends MvpModel<GetTaskSuboverModel.Request, GetTaskSuboverModel.Response> {
    @Override
    public void getResponse(Request request, MvpCallback<Response> callback) {
        if (!checkNetWork()) {
            try {
                GetTaskSuboverModel.Response response = new GetTaskSuboverModel.Response();
                List<TaskSubjectView> list = LitePal.where("BillID = ?", request.getBillid()).find(TaskSubjectView.class);
                if (list.size() > 0) {
                    response.setData(list);
                    callback.onSuccess(response);
                } else {
//                    callback.onFailure("未查询到相关数据");
                }
                callback.onComplete();
            } catch (Exception e) {
                callback.onError(e);
            }
        }else {
            apiApp.gettasksubover(request.getBillid()).subscribeOn(Schedulers.io())//IO线程加载数据
                    .observeOn(AndroidSchedulers.mainThread())//主线程显示数据
                    .subscribe(new Observer<Response>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                        }

                        @Override
                        public void onNext(GetTaskSuboverModel.Response response) {
                            if (response.getState() == RESPONSE_OK) {
                                if (response.getData().size() > 0) {
                                    for (int i = 0; i < response.getData().size(); i++) {
                                        response.getData().get(i).saveOrUpdate("KeyID = ?", response.getData().get(i).getKeyID());
                                    }
                                }
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

    }

    public static class Response extends ESafetyResponse {
        List<TaskSubjectView> data = new ArrayList<>();

        public List<TaskSubjectView> getData() {
            if(data==null)
            {
                return new ArrayList<>();
            }
            return data;
        }

        public void setData(List<TaskSubjectView> data) {
            this.data = data;
        }
    }

    public static class Request extends ESafetyRequest {
        String billid= "";

        public String getBillid() {
            return billid;
        }

        public void setBillid(String billid) {
            this.billid = billid;
        }
    }
}
