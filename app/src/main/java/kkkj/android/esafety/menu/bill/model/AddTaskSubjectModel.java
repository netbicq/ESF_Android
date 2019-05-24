package kkkj.android.esafety.menu.bill.model;

import android.text.TextUtils;

import com.orhanobut.logger.Logger;

import org.litepal.LitePal;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import kkkj.android.esafety.bean.AttachFileNew;
import kkkj.android.esafety.bean.Subject;
import kkkj.android.esafety.bean.TaskSubjectView;
import kkkj.android.esafety.http.ESafetyRequest;
import kkkj.android.esafety.http.ESafetyResponse;
import kkkj.android.esafety.model.UpLoadFileModel;
import kkkj.android.esafety.mvpInterface.MvpCallback;
import kkkj.android.esafety.mvpInterface.MvpModel;
import kkkj.android.esafety.utils.NetUtils;

public class AddTaskSubjectModel extends MvpModel<AddTaskSubjectModel.Request, AddTaskSubjectModel.Response> {
    @Override
    public void getResponse(Request request, MvpCallback<Response> callback) {
        if (!NetUtils.checkNetWork()) {
            try {
                Subject subject = new Subject();
                subject.setBillID(request.getBillID());
                subject.setTime(request.getTime());
                subject.setSubjectType(request.getSubjectType());
                subject.setTaskResult(request.getTaskResult());
                subject.setTaskResultMemo(request.getTaskResultMemo());
                subject.setSubjectID(request.getSubjectID());
                subject.setDangerID(request.getDangerID());
                subject.setAttachFiles(request.getAttachFiles());

                subject.setEval_WHYS(request.getEval_WHYS());
                subject.setEval_SGLX(request.getEval_SGLX());
                subject.setEval_SGJG(request.getEval_SGJG());
                subject.setEval_YXFW(request.getEval_YXFW());
                subject.setEval_Method(request.getEval_Method());
                subject.setDangerLevel(request.getDangerLevel());
                subject.setTroubleLevel(request.getTroubleLevel());
                subject.setPrincipalID(request.getPrincipalID());

                subject.setLECD_L(request.getLECD_L());
                subject.setLECD_E(request.getLECD_E());
                subject.setLECD_C(request.getLECD_C());
                subject.setLSD_L(request.getLSD_L());
                subject.setLSD_S(request.getLSD_S());
                subject.setDValue(request.getDValue());


                subject.saveOrUpdate("SubjectID = ? and BillID = ? and DangerID = ?", subject.getSubjectID(), subject.getBillID()
                        , subject.getDangerID());
                callback.onSuccess(new Response());
            } catch (Exception e) {
                callback.onError(e);
            }
        } else {
            List<AttachFileNew> mList = request.getAttachFiles();
            List<AttachFileNew> AttachFileNewList = new ArrayList<>();
            Observable<UpLoadFileModel.Response> source = Observable.create(new ObservableOnSubscribe<UpLoadFileModel.Response>() {
                @Override
                public void subscribe(ObservableEmitter<UpLoadFileModel.Response> emitter) throws Exception {
                    Logger.d(mList.size() + "____________________");
                    for (int i = 0; i < mList.size(); i++) {
                        try {
                            UpLoadFileModel.Request uploadFile = new UpLoadFileModel.Request();
                            File file;
                            if (mList.get(i).getFileType().equals("jpg")) {
                                uploadFile.setMediaType("image");
                            } else {
                                uploadFile.setMediaType("video");
                            }
                            file = new File(mList.get(i).getFileUrl());
                            uploadFile.setFile(file);
                            String url = apiAttachfile.uploadfileSynchro(uploadFile.getFiles()).execute().body().getData();
                            if (!TextUtils.isEmpty(url)) {
                                mList.get(i).setFileUrl(url);
                                AttachFileNewList.add(mList.get(i));
                            }
                        } catch (Exception e) {
                            callback.onError(e);
                        }

                    }
                    emitter.onComplete();
                }
            }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());

            source.subscribe(new Observer<UpLoadFileModel.Response>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(UpLoadFileModel.Response s) {
                }

                @Override
                public void onError(Throwable e) {
                    callback.onError(e);
                }

                @Override
                public void onComplete() {
                    request.setAttachFiles(AttachFileNewList);
                    apiApp.addtasksubject(request)
                            .subscribeOn(Schedulers.io())//IO线程加载数据
                            .observeOn(AndroidSchedulers.mainThread())//主线程显示数据
                            .subscribe(new Observer<Response>() {
                                @Override
                                public void onSubscribe(Disposable d) {
                                }

                                @Override
                                public void onNext(AddTaskSubjectModel.Response response) {
                                    if (response.getState() == RESPONSE_OK) {
                                        List<Subject> subjectList = LitePal.where("SubjectID = ? and BillID = ?and DangerID = ?"
                                                , request.getSubjectID(), request.getBillID()
                                                , request.getDangerID()).find(Subject.class);
                                        List<TaskSubjectView> taskSubjectViewList = LitePal.where("SubID = ? and BillID = ?and DangerID = ?"
                                                , request.getSubjectID(), request.getBillID()
                                                , request.getDangerID()).find(TaskSubjectView.class);

                                        for (int i = 0; i < subjectList.size(); i++) {
                                            subjectList.get(i).delete();
                                        }
                                        for (int j = 0; j < taskSubjectViewList.size(); j++) {
                                            taskSubjectViewList.get(j).delete();
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
            });
        }
    }

    public static class Response extends ESafetyResponse {
        boolean data = false;

        public boolean isData() {
            return data;
        }

        public void setData(boolean data) {
            this.data = data;
        }
    }

    public static class Request extends ESafetyRequest {
        String BillID = "";
        int SubjectType = 0;
        String SubjectID = "";
        int TaskResult = 0;
        String TaskResultMemo = "";
        String DangerID = "";
        List<AttachFileNew> AttachFiles = new ArrayList<>();
        String Time = "";
        /**
         * 2019/5/16新加参数
         */
        String Eval_WHYS = "";
        String Eval_SGLX = "";
        String Eval_SGJG = "";
        String Eval_YXFW = "";
        int Eval_Method = -1;
        String TroubleLevel = "";
        String DangerLevel = "";
        String PrincipalID = "";

        String LECD_L = "";
        String LECD_E = "";
        String LECD_C = "";
        String LSD_L = "";
        String LSD_S = "";
        int DValue ;


        public String getLECD_L() {
            return LECD_L;
        }

        public void setLECD_L(String LECD_L) {
            this.LECD_L = LECD_L;
        }

        public String getLECD_E() {
            return LECD_E;
        }

        public void setLECD_E(String LECD_E) {
            this.LECD_E = LECD_E;
        }

        public String getLECD_C() {
            return LECD_C;
        }

        public void setLECD_C(String LECD_C) {
            this.LECD_C = LECD_C;
        }

        public String getLSD_L() {
            return LSD_L;
        }

        public void setLSD_L(String LSD_L) {
            this.LSD_L = LSD_L;
        }

        public String getLSD_S() {
            return LSD_S;
        }

        public void setLSD_S(String LSD_S) {
            this.LSD_S = LSD_S;
        }

        public int getDValue() {
            return DValue;
        }

        public void setDValue(int DValue) {
            this.DValue = DValue;
        }

        public String getEval_WHYS() {
            if (TextUtils.isEmpty(Eval_WHYS)) {
                return "";
            }
            return Eval_WHYS;
        }

        public String getEval_SGLX() {
            if (TextUtils.isEmpty(Eval_SGLX)) {
                return "";
            }
            return Eval_SGLX;
        }

        public String getEval_SGJG() {
            if (TextUtils.isEmpty(Eval_SGJG)) {
                return "";
            }
            return Eval_SGJG;
        }

        public String getEval_YXFW() {
            if (TextUtils.isEmpty(Eval_YXFW)) {
                return "";
            }

            return Eval_YXFW;
        }

        public int getEval_Method() {

            return Eval_Method;
        }

        public String getTroubleLevel() {
            return TroubleLevel;
        }

        public String getDangerLevel() {
            if (TextUtils.isEmpty(DangerLevel)) {
                return "";
            }
            return DangerLevel;
        }

        public String getPrincipalID() {
            if (TextUtils.isEmpty(PrincipalID)) {
                return "";
            }
            return PrincipalID;
        }

        public void setEval_WHYS(String eval_WHYS) {

            Eval_WHYS = eval_WHYS;
        }

        public void setEval_SGLX(String eval_SGLX) {
            Eval_SGLX = eval_SGLX;
        }

        public void setEval_SGJG(String eval_SGJG) {
            Eval_SGJG = eval_SGJG;
        }

        public void setEval_YXFW(String eval_YXFW) {
            Eval_YXFW = eval_YXFW;
        }

        public void setEval_Method(int eval_Method) {
            Eval_Method = eval_Method;
        }

        public void setTroubleLevel(String troubleLevel) {
            TroubleLevel = troubleLevel;
        }

        public void setDangerLevel(String dangerLevel) {
            DangerLevel = dangerLevel;
        }

        public void setPrincipalID(String principalID) {
            PrincipalID = principalID;
        }

        public String getDangerID() {
            return DangerID;
        }

        public void setDangerID(String dangerID) {
            DangerID = dangerID;
        }

        public String getTime() {
            return Time;
        }

        public void setTime(String time) {
            Time = time;
        }

        public String getBillID() {
            return BillID;
        }

        public void setBillID(String billID) {
            BillID = billID;
        }

        public int getSubjectType() {
            return SubjectType;
        }

        public void setSubjectType(int subjectType) {
            SubjectType = subjectType;
        }

        public String getSubjectID() {
            return SubjectID;
        }

        public void setSubjectID(String subjectID) {
            SubjectID = subjectID;
        }

        public int getTaskResult() {
            return TaskResult;
        }

        public void setTaskResult(int taskResult) {
            TaskResult = taskResult;
        }

        public String getTaskResultMemo() {
            return TaskResultMemo;
        }

        public void setTaskResultMemo(String taskResultMemo) {
            TaskResultMemo = taskResultMemo;
        }

        public List<AttachFileNew> getAttachFiles() {
            return AttachFiles;
        }

        public void setAttachFiles(List<AttachFileNew> attachFiles) {
            AttachFiles = attachFiles;
        }
    }
}
