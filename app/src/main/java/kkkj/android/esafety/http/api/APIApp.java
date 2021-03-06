package kkkj.android.esafety.http.api;


import kkkj.android.esafety.home.model.AddBillModel;
import kkkj.android.esafety.home.model.DownloadDataModel;
import kkkj.android.esafety.home.model.GetEmpTaskByQRCoderModel;
import kkkj.android.esafety.home.model.GetTaskBillMastersOverByQRCoderModel;
import kkkj.android.esafety.menu.bill.model.AddTaskSubjectModel;
import kkkj.android.esafety.menu.bill.model.DelSubResultModel;
import kkkj.android.esafety.menu.bill.model.DelTaskBillModel;
import kkkj.android.esafety.menu.bill.model.GetSubResultModel;
import kkkj.android.esafety.menu.bill.model.GetSubjectsModel;
import kkkj.android.esafety.menu.bill.model.GetTaskBillsModel;
import kkkj.android.esafety.menu.bill.model.GetTaskBillsOverModel;
import kkkj.android.esafety.menu.bill.model.GetTaskSuboverModel;
import kkkj.android.esafety.menu.bill.model.TaskBillOverModel;
import kkkj.android.esafety.menu.hidden.model.AddTroubleCtrFlowModel;
import kkkj.android.esafety.menu.hidden.model.ChangeDangerLevelModel;
import kkkj.android.esafety.menu.hidden.model.ChangeLevelModel;
import kkkj.android.esafety.menu.hidden.model.DelayFinishTimeModel;
import kkkj.android.esafety.menu.hidden.model.FiledModel;
import kkkj.android.esafety.menu.hidden.model.GetTroubleCtrModel;
import kkkj.android.esafety.menu.hidden.model.HandleCtrModel;
import kkkj.android.esafety.menu.hidden.model.QuickHandleCtrModel;
import kkkj.android.esafety.menu.hidden.model.TransferPrincipalModel;
import kkkj.android.esafety.menu.institution.model.GetDangerDictModel;
import kkkj.android.esafety.menu.institution.model.GetDocinsListModel;
import kkkj.android.esafety.menu.institution.model.GetdoCinsModel;
import kkkj.android.esafety.menu.institution.model.GetdocSolutionListModel;
import kkkj.android.esafety.menu.institution.model.GetdocSolutionModel;
import kkkj.android.esafety.menu.statistics.model.GetCtrMenuModel;
import kkkj.android.esafety.menu.statistics.model.GetDangerLevelsModel;
import kkkj.android.esafety.menu.statistics.model.GetDangerPointsPageModel;
import kkkj.android.esafety.menu.statistics.model.GetTimeOutTaskModel;
import kkkj.android.esafety.menu.statistics.model.GetTroubleCtrsPageModel;
import kkkj.android.esafety.menu.task.model.*;
import kkkj.android.esafety.http.ApiConfig;
import io.reactivex.Observable;
import kkkj.android.esafety.menu.temptask.model.AddTempTaskModel;
import kkkj.android.esafety.menu.temptask.model.GetDangerSelectorModel;
import kkkj.android.esafety.menu.temptask.model.GetSelectorModel;
import kkkj.android.esafety.menu.vedio.model.GetVideoListModel;
import kkkj.android.esafety.menu.work.model.BillFlowSetModel;
import kkkj.android.esafety.menu.work.model.GetCurrentListModel;
import kkkj.android.esafety.menu.work.model.GetOpreateFlowModel;
import kkkj.android.esafety.menu.work.model.GetOverListModel;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface APIApp {
    //APP - api/app/addbill ???????????????
    @POST(ApiConfig.BASE_URL + "api/app/addbill")
    Observable<AddBillModel.Response> addbill(@Body AddBillModel.Request request);
//
    //APP - api/app/gettasklist ?????????????????????????????????
    @GET(ApiConfig.BASE_URL + "api/app/gettasklist")
    Observable<GetTaskListModel.Response> gettasklist();
//
    //APP - api/app/gettimetask ????????????????????????????????????
    @GET(ApiConfig.BASE_URL + "api/app/gettimetask")
    Observable<GetTimeTaskModel.Response> gettimetask();
//
    //APP - api/app/addtasksubject	 ??????????????????????????????
    @POST(ApiConfig.BASE_URL + "api/app/addtasksubject")
    Observable<AddTaskSubjectModel.Response> addtasksubject(@Body AddTaskSubjectModel.Request request);
//
    //APIAuth - api/app/getsubjects ???????????????id?????????????????????
    @GET(ApiConfig.BASE_URL + "api/app/getsubjects/{billid}")
    Observable<GetSubjectsModel.Response> getsubjects(@Path("billid") String billid);
//
    //APIAuth - api/app/taskbillover ??????????????????
    @GET(ApiConfig.BASE_URL + "api/app/taskbillover/{billid}")
    Observable<TaskBillOverModel.Response> taskbillover(@Path("billid") String billid);
//
    //APIAuth - api/app/deltaskbill ??????????????????
    @GET(ApiConfig.BASE_URL + "api/app/deltaskbill/{billid}")
    Observable<DelTaskBillModel.Response> deltaskbill(@Path("billid") String billid);
//
    //APIAuth - api/app/gettaskbills ??????????????????????????????
    @GET(ApiConfig.BASE_URL + "api/app/gettaskbills")
    Observable<GetTaskBillsModel.Response> gettaskbills();
//
    //APIAuth - api/app/gettaskbillsover ?????????????????????????????? ??????????????????????????????
    @GET(ApiConfig.BASE_URL + "api/app/gettaskbillsover")
    Observable<GetTaskBillsOverModel.Response> gettaskbillsover();
//
    //APIAuth - api/app/gettasksubover ???????????????id????????????????????????????????????
    @GET(ApiConfig.BASE_URL + "api/app/gettasksubover/{taskbillid}")
    Observable<GetTaskSuboverModel.Response> gettasksubover(@Path("taskbillid") String taskbillid);
//
    //APIAuth - api/app/delsubresult ????????????ID?????????????????????
    @GET(ApiConfig.BASE_URL + "api/app/delsubresult/{subresultid}")
    Observable<DelSubResultModel.Response> delsubresult(@Path("subresultid") String subresultid);
//
    //APIAuth - api/app/getsubresult ????????????????????????
    @GET(ApiConfig.BASE_URL + "api/app/getsubresult/{subresultid}")
    Observable<GetSubResultModel.Response> getsubresult(@Path("subresultid") String subresultid);
//
    //APIAuth - api/app/downloaddata ????????????
    @GET(ApiConfig.BASE_URL + "api/app/downloaddata")
    Observable<DownloadDataModel.Response> downloaddata();
//
    //APIAuth - api/app/getcurrentlist ?????????????????????????????????
    @GET(ApiConfig.BASE_URL + "api/app/getcurrentlist")
    Observable<GetCurrentListModel.Response> getcurrentlist();
//
    //APIAuth - api/app/getoverlist ?????????????????????????????????
    @GET(ApiConfig.BASE_URL + "api/app/getoverlist")
    Observable<GetOverListModel.Response> getoverlist();
//
    //APIAuth - api/getopreateflowmodel/{opreateid} ???????????????ID???????????????????????????????????????
    @GET(ApiConfig.BASE_URL + "api/app/getopreateflowmodel/{opreateid}")
    Observable<GetOpreateFlowModel.Response> getopreateflowmodel(@Path("opreateid") String opreateid);
//
    //APIAuth - api/app/billflowset ???????????????????????????
    @POST(ApiConfig.BASE_URL + "api/app/billflowset")
    Observable<BillFlowSetModel.Response> billflowset(@Body BillFlowSetModel.Request request);

    //APIAuth - api/app/getdocinslist ??????????????????
    @GET(ApiConfig.BASE_URL + "api/app/getdocinslist")
    Observable<GetDocinsListModel.Response> getdocinslist();
//
    //APIAuth - api/app/getdocsolutionlist/{dangerlevelid} ??????????????????ID??????????????????,????????????00000000-0000-0000-0000-000000000000
    @GET(ApiConfig.BASE_URL + "api/app/getdocsolutionlist/{dangerlevelid}")
    Observable<GetdocSolutionListModel.Response> getdocsolutionlist(@Path("dangerlevelid") String opreateid);
//
//
    //APIAuth - api/dict/getdangerdict ????????????????????????
    @GET(ApiConfig.BASE_URL + "api/dict/getdangerdict")
    Observable<GetDangerDictModel.Response> getdangerdict();
//
    //APIAuth - api/app/getdocins/{insid} ????????????ID????????????
    @GET(ApiConfig.BASE_URL + "api/app/getdocins/{insid}")
    Observable<GetdoCinsModel.Response> getdocins(@Path("insid") String insid);
//
    //APIAuth - api/app/getdocsolutionmodel/{docsolutionid} ????????????ID??????????????????
    @GET(ApiConfig.BASE_URL + "api/app/getdocsolutionmodel/{docsolutionid}")
    Observable<GetdocSolutionModel.Response> getdocsolutionmodel(@Path("docsolutionid") String docsolutionid);
//
    //APIAuth - api/app/getvideolist ?????????????????????
    @GET(ApiConfig.BASE_URL + "api/app/getvideolist")
    Observable<GetVideoListModel.Response> getvideolist();
//
    //APP - api/app/addtemptask
    //???????????????????????????
    @POST(ApiConfig.BASE_URL + "api/app/addtemptask")
    Observable<AddTempTaskModel.Response> addtemptask(@Body AddTempTaskModel.Request request);
//
    //APIAuth - api/app/getselector ?????????????????????
    @GET(ApiConfig.BASE_URL + "api/app/getselector")
    Observable<GetSelectorModel.Response> getselector();
//
    //APP - api/app/getdangerselector
    //??????????????????????????????
    @POST(ApiConfig.BASE_URL + "api/app/getdangerselector")
    Observable<GetDangerSelectorModel.Response> getdangerselector(@Body GetDangerSelectorModel.Request request);
//
    //APIAuth - api/app/getEmpTaskByQRCoder/{dangerPointID} ???????????????????????????
    @GET(ApiConfig.BASE_URL + "api/app/getEmpTaskByQRCoder/{dangerPointID}")
    Observable<GetEmpTaskByQRCoderModel.Response> getEmpTaskByQRCoder(@Path("dangerPointID") String dangerPointID);
//
//
    //APIAuth - api/app/getEmpTaskTimeOutByQRCoder/{dangerPointID} ?????????????????????????????????
    @GET(ApiConfig.BASE_URL + "api/app/getEmpTaskTimeOutByQRCoder/{dangerPointID}")
    Observable<GetEmpTaskTimeOutByQRCoderModel.Response> getEmpTaskTimeOutByQRCoder(@Path("dangerPointID") String dangerPointID);
//
//
    //APIAuth - api/app/getTaskBillMastersOverByQRCoder/{pointID} ???????????????????????????
    @GET(ApiConfig.BASE_URL + "api/app/getTaskBillMastersOverByQRCoder/{pointID}")
    Observable<GetTaskBillMastersOverByQRCoderModel.Response> getTaskBillMastersOverByQRCoder(@Path("pointID") String dangerPointID);

    //APIAuth -  api/app/getTroubleCtr ????????????????????????
    @GET(ApiConfig.BASE_URL + "api/app/getTroubleCtr")
    Observable<GetTroubleCtrModel.Response> getTroubleCtr();

    //APP - api/app/delayFinishTime
    //????????????????????????
    @POST(ApiConfig.BASE_URL + "api/app/delayFinishTime")
    Observable<DelayFinishTimeModel.Response> delayFinishTime(@Body DelayFinishTimeModel.Request request);
//

    //APP - api/app/changeLevel
    //??????????????????
    @POST(ApiConfig.BASE_URL + "api/app/changeLevel")
    Observable<ChangeLevelModel.Response> changeLevel(@Body ChangeLevelModel.Request request);
//

    //APP - api/app/changeDangerLevel
    //??????????????????
    @POST(ApiConfig.BASE_URL + "api/app/changeDangerLevel")
    Observable<ChangeDangerLevelModel.Response> changeDangerLevel(@Body ChangeDangerLevelModel.Request request);
//

    //APP - api/app/addTroubleCtrFlow
    //????????????/??????
    @POST(ApiConfig.BASE_URL + "api/app/addTroubleCtrFlow")
    Observable<AddTroubleCtrFlowModel.Response> addTroubleCtrFlow(@Body AddTroubleCtrFlowModel.Request request);
//

    //APIAuth - api/app/filed/{ctrID}
    //??????
    @GET(ApiConfig.BASE_URL + "api/app/filed/{ctrID}")
    Observable<FiledModel.Response> filed(@Path("ctrID") String ctrID);

    //APP - api/app/handleCtr
    //???????????????
    @POST(ApiConfig.BASE_URL + "api/app/handleCtr")
    Observable<HandleCtrModel.Response> handleCtr(@Body HandleCtrModel.Request request);

    //APP - api/app/transferPrincipal
    //???????????????
    @POST(ApiConfig.BASE_URL + "api/app/transferPrincipal")
    Observable<TransferPrincipalModel.Response> transferPrincipal(@Body TransferPrincipalModel.Request request);
//

    //APP - api/app/quickHandleCtr
    //????????????
    @POST(ApiConfig.BASE_URL + "api/app/quickHandleCtr")
    Observable<QuickHandleCtrModel.Response> quickHandleCtr(@Body QuickHandleCtrModel.Request request);

    //APP - api/app/getDangerLevels
    //APP ?????????????????????
    @GET(ApiConfig.BASE_URL + "api/app/getDangerLevels")
    Observable<GetDangerLevelsModel.Response> getDangerLevels();
//
    //APP - api/app/getDangerPointsPage
    //APP ???????????????ID ??????????????????
    @POST(ApiConfig.BASE_URL + "api/app/getDangerPointsPage")
    Observable<GetDangerPointsPageModel.Response> getDangerPointsPage(@Body GetDangerPointsPageModel.Request request);
//
    //APP - api/app/getCtrMenu
    //APP???????????????????????????
    @GET(ApiConfig.BASE_URL + "api/app/getCtrMenu")
    Observable<GetCtrMenuModel.Response> getCtrMenu();

    //APP - api/app/getTroubleCtrsPage
    //APP ?????? ?????????????????????
    @POST(ApiConfig.BASE_URL + "api/app/getTroubleCtrsPage")
    Observable<GetTroubleCtrsPageModel.Response> getTroubleCtrsPage(@Body GetTroubleCtrsPageModel.Request request);

    //APP - api/app/getTimeOutTask
    //APP ?????? ???????????????????????? ????????????????????????
    @POST(ApiConfig.BASE_URL + "api/app/getTimeOutTask")
    Observable<GetTimeOutTaskModel.Response> getTimeOutTask(@Body GetTimeOutTaskModel.Request request);
}
