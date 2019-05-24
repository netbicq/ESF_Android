package kkkj.android.esafety.menu.task.model;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import kkkj.android.esafety.bean.InsepctTaskByEmployee;
import kkkj.android.esafety.http.ESafetyRequest;
import kkkj.android.esafety.http.ESafetyResponse;
import kkkj.android.esafety.mvpInterface.MvpCallback;
import kkkj.android.esafety.mvpInterface.MvpModel;

import static kkkj.android.esafety.utils.NetUtils.checkNetWork;

public class GetTaskListModel extends MvpModel<GetTaskListModel.Request, GetTaskListModel.Response> {
    @Override
    public void getResponse(Request request, final MvpCallback<Response> callback) {
        if (!checkNetWork()) {
            //没有网络的话 就访问本地数据库
            try {
                GetTaskListModel.Response response = new GetTaskListModel.Response();
                List<InsepctTaskByEmployee> list= LitePal.where("TimeOutHours < ?","1").find(InsepctTaskByEmployee.class);
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
            apiApp.gettasklist()
                    .subscribeOn(Schedulers.io())//IO线程加载数据
                    .observeOn(AndroidSchedulers.mainThread())//主线程显示数据
                    .subscribe(new Observer<Response>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                        }

                        @Override
                        public void onNext(GetTaskListModel.Response response) {
                            if (response.getState() == RESPONSE_OK) {
                                if(response.getData().size()>0)
                                {
                                    for (int i = 0 ;i<response.getData().size();i++)
                                    {
                                        response.getData().get(i).saveOrUpdate("TaskID = ?",response.getData().get(i).getTaskID());
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
        List<InsepctTaskByEmployee> data = new ArrayList<>();

        public List<InsepctTaskByEmployee> getData() {
            if(data==null)
            {
                return new ArrayList<>();
            }
            return data;
        }

        public void setData(List<InsepctTaskByEmployee> data) {
            this.data = data;
        }
    }

    public static class Request extends ESafetyRequest {

    }
}
