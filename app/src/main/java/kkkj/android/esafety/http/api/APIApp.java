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
    //APP - api/app/addbill 新建任务单
    @POST(ApiConfig.BASE_URL + "api/app/addbill")
    Observable<AddBillModel.Response> addbill(@Body AddBillModel.Request request);
//
    //APP - api/app/gettasklist 获取当前用户的任务列表
    @GET(ApiConfig.BASE_URL + "api/app/gettasklist")
    Observable<GetTaskListModel.Response> gettasklist();
//
    //APP - api/app/gettimetask 获取当前用户超期任务列表
    @GET(ApiConfig.BASE_URL + "api/app/gettimetask")
    Observable<GetTimeTaskModel.Response> gettimetask();
//
    //APP - api/app/addtasksubject	 新建任务主体检查结果
    @POST(ApiConfig.BASE_URL + "api/app/addtasksubject")
    Observable<AddTaskSubjectModel.Response> addtasksubject(@Body AddTaskSubjectModel.Request request);
//
    //APIAuth - api/app/getsubjects 根据任务单id获取待检查主体
    @GET(ApiConfig.BASE_URL + "api/app/getsubjects/{billid}")
    Observable<GetSubjectsModel.Response> getsubjects(@Path("billid") String billid);
//
    //APIAuth - api/app/taskbillover 完成任务单据
    @GET(ApiConfig.BASE_URL + "api/app/taskbillover/{billid}")
    Observable<TaskBillOverModel.Response> taskbillover(@Path("billid") String billid);
//
    //APIAuth - api/app/deltaskbill 删除任务单据
    @GET(ApiConfig.BASE_URL + "api/app/deltaskbill/{billid}")
    Observable<DelTaskBillModel.Response> deltaskbill(@Path("billid") String billid);
//
    //APIAuth - api/app/gettaskbills 获取当前任务单据列表
    @GET(ApiConfig.BASE_URL + "api/app/gettaskbills")
    Observable<GetTaskBillsModel.Response> gettaskbills();
//
    //APIAuth - api/app/gettaskbillsover 获取历史任务单据列表 获取历史任务单据列表
    @GET(ApiConfig.BASE_URL + "api/app/gettaskbillsover")
    Observable<GetTaskBillsOverModel.Response> gettaskbillsover();
//
    //APIAuth - api/app/gettasksubover 根据任务单id获取已检查了的主体的集合
    @GET(ApiConfig.BASE_URL + "api/app/gettasksubover/{taskbillid}")
    Observable<GetTaskSuboverModel.Response> gettasksubover(@Path("taskbillid") String taskbillid);
//
    //APIAuth - api/app/delsubresult 根据结果ID，删除检查结果
    @GET(ApiConfig.BASE_URL + "api/app/delsubresult/{subresultid}")
    Observable<DelSubResultModel.Response> delsubresult(@Path("subresultid") String subresultid);
//
    //APIAuth - api/app/getsubresult 获取检查结果模型
    @GET(ApiConfig.BASE_URL + "api/app/getsubresult/{subresultid}")
    Observable<GetSubResultModel.Response> getsubresult(@Path("subresultid") String subresultid);
//
    //APIAuth - api/app/downloaddata 下载数据
    @GET(ApiConfig.BASE_URL + "api/app/downloaddata")
    Observable<DownloadDataModel.Response> downloaddata();
//
    //APIAuth - api/app/getcurrentlist 获取当前人能做的作业单
    @GET(ApiConfig.BASE_URL + "api/app/getcurrentlist")
    Observable<GetCurrentListModel.Response> getcurrentlist();
//
    //APIAuth - api/app/getoverlist 获取当前人已做的作业单
    @GET(ApiConfig.BASE_URL + "api/app/getoverlist")
    Observable<GetOverListModel.Response> getoverlist();
//
    //APIAuth - api/getopreateflowmodel/{opreateid} 根据作业单ID，获取带处理节点的单据模型
    @GET(ApiConfig.BASE_URL + "api/app/getopreateflowmodel/{opreateid}")
    Observable<GetOpreateFlowModel.Response> getopreateflowmodel(@Path("opreateid") String opreateid);
//
    //APIAuth - api/app/billflowset 处理作业单流程节点
    @POST(ApiConfig.BASE_URL + "api/app/billflowset")
    Observable<BillFlowSetModel.Response> billflowset(@Body BillFlowSetModel.Request request);

    //APIAuth - api/app/getdocinslist 获取所有制度
    @GET(ApiConfig.BASE_URL + "api/app/getdocinslist")
    Observable<GetDocinsListModel.Response> getdocinslist();
//
    //APIAuth - api/app/getdocsolutionlist/{dangerlevelid} 根据风险等级ID获取预案列表,默认值为00000000-0000-0000-0000-000000000000
    @GET(ApiConfig.BASE_URL + "api/app/getdocsolutionlist/{dangerlevelid}")
    Observable<GetdocSolutionListModel.Response> getdocsolutionlist(@Path("dangerlevelid") String opreateid);
//
//
    //APIAuth - api/dict/getdangerdict 获取风险等级词典
    @GET(ApiConfig.BASE_URL + "api/dict/getdangerdict")
    Observable<GetDangerDictModel.Response> getdangerdict();
//
    //APIAuth - api/app/getdocins/{insid} 根据制度ID获取制度
    @GET(ApiConfig.BASE_URL + "api/app/getdocins/{insid}")
    Observable<GetdoCinsModel.Response> getdocins(@Path("insid") String insid);
//
    //APIAuth - api/app/getdocsolutionmodel/{docsolutionid} 根据预案ID获取预案详情
    @GET(ApiConfig.BASE_URL + "api/app/getdocsolutionmodel/{docsolutionid}")
    Observable<GetdocSolutionModel.Response> getdocsolutionmodel(@Path("docsolutionid") String docsolutionid);
//
    //APIAuth - api/app/getvideolist 获取摄像头列表
    @GET(ApiConfig.BASE_URL + "api/app/getvideolist")
    Observable<GetVideoListModel.Response> getvideolist();
//
    //APP - api/app/addtemptask
    //移动端新建临时任务
    @POST(ApiConfig.BASE_URL + "api/app/addtemptask")
    Observable<AddTempTaskModel.Response> addtemptask(@Body AddTempTaskModel.Request request);
//
    //APIAuth - api/app/getselector 获取选择器信息
    @GET(ApiConfig.BASE_URL + "api/app/getselector")
    Observable<GetSelectorModel.Response> getselector();
//
    //APP - api/app/getdangerselector
    //获取风控项选择器信息
    @POST(ApiConfig.BASE_URL + "api/app/getdangerselector")
    Observable<GetDangerSelectorModel.Response> getdangerselector(@Body GetDangerSelectorModel.Request request);
//
    //APIAuth - api/app/getEmpTaskByQRCoder/{dangerPointID} 根据二维码获取任务
    @GET(ApiConfig.BASE_URL + "api/app/getEmpTaskByQRCoder/{dangerPointID}")
    Observable<GetEmpTaskByQRCoderModel.Response> getEmpTaskByQRCoder(@Path("dangerPointID") String dangerPointID);
//
//
    //APIAuth - api/app/getEmpTaskTimeOutByQRCoder/{dangerPointID} 根据二维码获取超时任务
    @GET(ApiConfig.BASE_URL + "api/app/getEmpTaskTimeOutByQRCoder/{dangerPointID}")
    Observable<GetEmpTaskTimeOutByQRCoderModel.Response> getEmpTaskTimeOutByQRCoder(@Path("dangerPointID") String dangerPointID);
//
//
    //APIAuth - api/app/getTaskBillMastersOverByQRCoder/{pointID} 根据二维码获取历史
    @GET(ApiConfig.BASE_URL + "api/app/getTaskBillMastersOverByQRCoder/{pointID}")
    Observable<GetTaskBillMastersOverByQRCoderModel.Response> getTaskBillMastersOverByQRCoder(@Path("pointID") String dangerPointID);

    //APIAuth -  api/app/getTroubleCtr 获取管控信息详情
    @GET(ApiConfig.BASE_URL + "api/app/getTroubleCtr")
    Observable<GetTroubleCtrModel.Response> getTroubleCtr();

    //APP - api/app/delayFinishTime
    //延长管控完成时间
    @POST(ApiConfig.BASE_URL + "api/app/delayFinishTime")
    Observable<DelayFinishTimeModel.Response> delayFinishTime(@Body DelayFinishTimeModel.Request request);
//

    //APP - api/app/changeLevel
    //改变隐患等级
    @POST(ApiConfig.BASE_URL + "api/app/changeLevel")
    Observable<ChangeLevelModel.Response> changeLevel(@Body ChangeLevelModel.Request request);
//

    //APP - api/app/changeDangerLevel
    //改变风险等级
    @POST(ApiConfig.BASE_URL + "api/app/changeDangerLevel")
    Observable<ChangeDangerLevelModel.Response> changeDangerLevel(@Body ChangeDangerLevelModel.Request request);
//

    //APP - api/app/addTroubleCtrFlow
    //新建申请/验收
    @POST(ApiConfig.BASE_URL + "api/app/addTroubleCtrFlow")
    Observable<AddTroubleCtrFlowModel.Response> addTroubleCtrFlow(@Body AddTroubleCtrFlowModel.Request request);
//

    //APIAuth - api/app/filed/{ctrID}
    //归档
    @GET(ApiConfig.BASE_URL + "api/app/filed/{ctrID}")
    Observable<FiledModel.Response> filed(@Path("ctrID") String ctrID);

    //APP - api/app/handleCtr
    //处理管控项
    @POST(ApiConfig.BASE_URL + "api/app/handleCtr")
    Observable<HandleCtrModel.Response> handleCtr(@Body HandleCtrModel.Request request);

    //APP - api/app/transferPrincipal
    //转让责任人
    @POST(ApiConfig.BASE_URL + "api/app/transferPrincipal")
    Observable<TransferPrincipalModel.Response> transferPrincipal(@Body TransferPrincipalModel.Request request);
//

    //APP - api/app/quickHandleCtr
    //快速处理
    @POST(ApiConfig.BASE_URL + "api/app/quickHandleCtr")
    Observable<QuickHandleCtrModel.Response> quickHandleCtr(@Body QuickHandleCtrModel.Request request);

    //APP - api/app/getDangerLevels
    //APP 端获取风险等级
    @GET(ApiConfig.BASE_URL + "api/app/getDangerLevels")
    Observable<GetDangerLevelsModel.Response> getDangerLevels();
//
    //APP - api/app/getDangerPointsPage
    //APP 根据风险点ID 端获取风险点
    @POST(ApiConfig.BASE_URL + "api/app/getDangerPointsPage")
    Observable<GetDangerPointsPageModel.Response> getDangerPointsPage(@Body GetDangerPointsPageModel.Request request);
//
    //APP - api/app/getCtrMenu
    //APP端获取统计管控菜单
    @GET(ApiConfig.BASE_URL + "api/app/getCtrMenu")
    Observable<GetCtrMenuModel.Response> getCtrMenu();

    //APP - api/app/getTroubleCtrsPage
    //APP 统计 分页获取管控项
    @POST(ApiConfig.BASE_URL + "api/app/getTroubleCtrsPage")
    Observable<GetTroubleCtrsPageModel.Response> getTroubleCtrsPage(@Body GetTroubleCtrsPageModel.Request request);

    //APP - api/app/getTimeOutTask
    //APP 统计 当前人组织架构下 获取所有超期任务
    @POST(ApiConfig.BASE_URL + "api/app/getTimeOutTask")
    Observable<GetTimeOutTaskModel.Response> getTimeOutTask(@Body GetTimeOutTaskModel.Request request);
}
