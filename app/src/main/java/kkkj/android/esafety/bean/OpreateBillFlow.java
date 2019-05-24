package kkkj.android.esafety.bean;

import java.util.ArrayList;
import java.util.List;

import kkkj.android.esafety.menu.work.model.OpreateFlowUEModel;

public class OpreateBillFlow {

    private String ID = "";
    private String OpreationFlowID = "";
    private String OpreationID = "";
    private String PointName = "";
    private String PostID = "";
    private String PostName = "";
    private int PointIndex = 0;
    private OpreateFlowUEModel FlowUEModel = new OpreateFlowUEModel();
    private List<OpreateBillFlowDetials> detials = new ArrayList<>();

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getOpreationFlowID() {
        return OpreationFlowID;
    }

    public void setOpreationFlowID(String opreationFlowID) {
        OpreationFlowID = opreationFlowID;
    }

    public String getOpreationID() {
        return OpreationID;
    }

    public void setOpreationID(String opreationID) {
        OpreationID = opreationID;
    }

    public String getPointName() {
        return PointName;
    }

    public void setPointName(String pointName) {
        PointName = pointName;
    }

    public String getPostID() {
        return PostID;
    }

    public void setPostID(String postID) {
        PostID = postID;
    }

    public String getPostName() {
        return PostName;
    }

    public void setPostName(String postName) {
        PostName = postName;
    }

    public int getPointIndex() {
        return PointIndex;
    }

    public void setPointIndex(int pointIndex) {
        PointIndex = pointIndex;
    }

    public OpreateFlowUEModel getFlowUEModel() {
        if (FlowUEModel == null) {
            return new OpreateFlowUEModel();
        } else {
            return FlowUEModel;
        }

    }

    public void setFlowUEModel(OpreateFlowUEModel flowUEModel) {
        FlowUEModel = flowUEModel;
    }

    public List<OpreateBillFlowDetials> getDetials() {
        if(detials==null)
        {
            return new ArrayList<>();
        }
        return detials;
    }

    public void setDetials(List<OpreateBillFlowDetials> detials) {
        this.detials = detials;
    }

}
