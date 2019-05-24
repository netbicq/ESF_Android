package kkkj.android.esafety.bean;

public class SubjectSelectModel {
    int SubjectType = 0;
    String SubjectID = "";
    String DangerID = "";

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

    public String getDangerID() {
        return DangerID;
    }

    public void setDangerID(String dangerID) {
        DangerID = dangerID;
    }

    String SubTypeName = "";
    String EntityTypeName = "";
    TempTaskSelector.Sub.EntityType.Entity Entity = new TempTaskSelector.Sub.EntityType.Entity();

    @Override
    public boolean equals( Object obj) {
        if (obj instanceof SubjectSelectModel) {
            SubjectSelectModel u = (SubjectSelectModel) obj;
            return this.SubTypeName.equals(u.SubTypeName)
                    && this.EntityTypeName.equals(u.EntityTypeName)
                    &&this.Entity.getSubjectID().equals(u.Entity.getSubjectID());
        }
        return super.equals(obj);
    }

    public String getSubTypeName() {
        return SubTypeName;
    }

    public void setSubTypeName(String subTypeName) {
        SubTypeName = subTypeName;
    }

    public String getEntityTypeName() {
        return EntityTypeName;
    }

    public void setEntityTypeName(String entityTypeName) {
        EntityTypeName = entityTypeName;
    }

    public TempTaskSelector.Sub.EntityType.Entity getEntity() {
        return Entity;
    }

    public void setEntity(TempTaskSelector.Sub.EntityType.Entity entity) {
        Entity = entity;
    }
}
