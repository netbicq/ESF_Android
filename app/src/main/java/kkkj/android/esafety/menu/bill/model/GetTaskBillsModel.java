package kkkj.android.esafety.menu.bill.model;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import kkkj.android.esafety.bean.TaskBillModel;
import kkkj.android.esafety.http.ESafetyRequest;
import kkkj.android.esafety.http.ESafetyResponse;
import kkkj.android.esafety.mvpInterface.MvpCallback;
import kkkj.android.esafety.mvpInterface.MvpModel;

import static kkkj.android.esafety.utils.NetUtils.checkNetWork;

/**
 * 当前任务单据
 */
public class GetTaskBillsModel extends MvpModel<GetTaskBillsModel.Request, GetTaskBillsModel.Response> {
    @Override
    public void getResponse(Request request, MvpCallback<Response> callback) {
        if (!checkNetWork()) {
            try {
                GetTaskBillsModel.Response response = new GetTaskBillsModel.Response();
                List<TaskBillModel> list = LitePal.where("State = ?", "待检查完成").find(TaskBillModel.class);
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
        } else {
            apiApp.gettaskbills()
                    .subscribeOn(Schedulers.io())//IO线程加载数据
                    .observeOn(AndroidSchedulers.mainThread())//主线程显示数据
                    .subscribe(new Observer<Response>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                        }

                        @Override
                        public void onNext(Response response) {
                            if (response.getState() == RESPONSE_OK) {
                                if (response.getData().size() > 0) {
                                    for (int i = 0; i < response.getData().size(); i++) {
                                        response.getData().get(i).saveOrUpdateAsync("BillID = ?", response.getData().get(i).getBillID());
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
        public List<TaskBillModel> getData() {
            if(data ==null)
            {
                return new ArrayList<>();
            }
            return data;
        }

        public void setData(List<TaskBillModel> data) {
            this.data = data;
        }

        List<TaskBillModel> data = new ArrayList<>();

    }

    public static class Request extends ESafetyRequest {
        int type = 0;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}
