package kkkj.android.esafety.menu.bill.model;

import com.orhanobut.logger.Logger;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import kkkj.android.esafety.bean.Subject;
import kkkj.android.esafety.bean.TaskSubjectView;
import kkkj.android.esafety.http.ESafetyRequest;
import kkkj.android.esafety.http.ESafetyResponse;
import kkkj.android.esafety.mvpInterface.MvpCallback;
import kkkj.android.esafety.mvpInterface.MvpModel;

import static kkkj.android.esafety.utils.NetUtils.checkNetWork;


public class GetSubjectsModel extends MvpModel<GetSubjectsModel.Request, GetSubjectsModel.Response> {
    @Override
    public void getResponse(Request request, MvpCallback<Response> callback) {
        if (!checkNetWork()) {
            try {
                GetSubjectsModel.Response response = new GetSubjectsModel.Response();
                List<TaskSubjectView> list = LitePal.where("BillID = ?", request.getBillid()).find(TaskSubjectView.class);
                for(int i = 0 ;i<list.size();i++)
                {
                    List<Subject> subjectList = LitePal.where("SubjectID = ? and BillID = ?and DangerID = ?"
                            ,list.get(i).getSubID(),list.get(i).getBillID(),list.get(i).getDangerID()).find(Subject.class);
                    Logger.d(subjectList.size());
                    if(subjectList.size()>0)
                    {
                        list.get(i).setState(1);
                    }
                    Logger.d("state"+list.get(i).getState());
                }
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

            apiApp.getsubjects(request.getBillid())
                    .subscribeOn(Schedulers.io())//IO线程加载数据
                    .observeOn(AndroidSchedulers.mainThread())//主线程显示数据
                    .subscribe(new Observer<Response>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                        }

                        @Override
                        public void onNext(GetSubjectsModel.Response response) {
                            if (response.getState() == RESPONSE_OK) {
                                if (response.getData().size() > 0) {
                                    for (int i = 0; i < response.getData().size(); i++) {
                                        List<Subject> subjectList = LitePal.where("SubjectID = ? and BillID = ? and DangerID=?"
                                                ,response.getData().get(i).getSubID(),response.getData().get(i).getBillID(),response.getData().get(i).getDangerID()).find(Subject.class);
                                        if(subjectList.size()>0)
                                        {
                                            response.getData().get(i).setState(1);
                                        }
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
