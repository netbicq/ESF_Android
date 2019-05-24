package kkkj.android.esafety.menu.temptask.model;

import android.text.TextUtils;

import com.orhanobut.logger.Logger;

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
import kkkj.android.esafety.bean.InspectTaskSubjectNew;
import kkkj.android.esafety.http.ESafetyRequest;
import kkkj.android.esafety.http.ESafetyResponse;
import kkkj.android.esafety.model.UpLoadFileModel;
import kkkj.android.esafety.mvpInterface.MvpCallback;
import kkkj.android.esafety.mvpInterface.MvpModel;

public class AddTempTaskModel extends MvpModel<AddTempTaskModel.Request, AddTempTaskModel.Response> {
    @Override
    public void getResponse(Request request, MvpCallback<Response> callback) {
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
                apiApp.addtemptask(request)
                        .subscribeOn(Schedulers.io())//IO线程加载数据
                        .observeOn(AndroidSchedulers.mainThread())//主线程显示数据
                        .subscribe(new Observer<Response>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                            }

                            @Override
                            public void onNext(AddTempTaskModel.Response response) {
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
        });


    }

    public static class Response extends ESafetyResponse {
        boolean data=false;

        public boolean isData() {
            return data;
        }

        public void setData(boolean data) {
            this.data = data;
        }
    }

    public static class Request extends ESafetyRequest {
        String Name = "";
        String DangerPointID = "";
        String StartTime = "";
        String EndTime = "";
        String ExecutePostID = "";
        String TaskDescription = "";
        List<InspectTaskSubjectNew> TaskSubjects = new ArrayList<>();
        List<AttachFileNew> AttachFiles = new ArrayList<>();

        public String getName() {
            return Name;
        }

        public void setName(String name) {
            Name = name;
        }

        public String getDangerID() {
            return DangerPointID;
        }

        public void setDangerID(String dangerID) {
            DangerPointID = dangerID;
        }

        public String getStartTime() {
            return StartTime;
        }

        public void setStartTime(String startTime) {
            StartTime = startTime;
        }

        public String getEndTime() {
            return EndTime;
        }

        public void setEndTime(String endTime) {
            EndTime = endTime;
        }

        public String getExecutePostID() {
            return ExecutePostID;
        }

        public void setExecutePostID(String executePostID) {
            ExecutePostID = executePostID;
        }

        public String getTaskDescription() {
            return TaskDescription;
        }

        public void setTaskDescription(String taskDescription) {
            TaskDescription = taskDescription;
        }

        public List<InspectTaskSubjectNew> getTaskSubjects() {
            return TaskSubjects;
        }

        public void setTaskSubjects(List<InspectTaskSubjectNew> taskSubjects) {
            TaskSubjects = taskSubjects;
        }

        public List<AttachFileNew> getAttachFiles() {
            return AttachFiles;
        }

        public void setAttachFiles(List<AttachFileNew> attachFiles) {
            AttachFiles = attachFiles;
        }
    }
}
