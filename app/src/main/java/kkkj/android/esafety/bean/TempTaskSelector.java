package kkkj.android.esafety.bean;

import android.text.TextUtils;

import com.contrarywind.interfaces.IPickerViewData;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TempTaskSelector {

    private List<Danger> DangerPoints = new ArrayList<>();
    private List<Post> Posts = new ArrayList<>();
    private List<Sub> Subs = new ArrayList<>();
    private List<SubType> SubTypes = new ArrayList<>();

    public List<SubType> getSubTypes() {
        if(SubTypes==null)
        {
            return new ArrayList<>();
        }
        return SubTypes;
    }

    public void setSubTypes(List<SubType> subTypes) {
        SubTypes = subTypes;
    }

    public static class SubType implements IPickerViewData
    {
        String SubTypeName="";
        int SubjectType = 0;

        public String getSubTypeName() {
            if(TextUtils.isEmpty(SubTypeName))
            {
                return  "";
            }
            return SubTypeName;
        }

        public void setSubTypeName(String subTypeName) {
            SubTypeName = subTypeName;
        }

        public int getSubjectType() {
            return SubjectType;
        }

        public void setSubjectType(int subjectType) {
            SubjectType = subjectType;
        }

        @Override
        public String getPickerViewText() {
            return SubTypeName;
        }
    }

    public List<Danger> getDangers() {
        if(DangerPoints==null)
        {
            return new ArrayList<>();
        }
        return DangerPoints;
    }

    public void setDangers(List<Danger> dangers) {
        DangerPoints = dangers;
    }

    public List<Post> getPosts() {
        if(Posts==null)
        {
            return new ArrayList<>();
        }
        return Posts;
    }

    public void setPosts(List<Post> posts) {
        Posts = posts;
    }

    public List<Sub> getSubs() {
        if(Subs==null)
        {
            return new ArrayList<>();
        }
        return Subs;
    }

    public void setSubs(List<Sub> subs) {
        Subs = subs;
    }

    public static class Sub implements IPickerViewData
    {
        private String SubTypeName = "";
        private List<EntityType> Subjects = new ArrayList<>();

        public String getSubTypeName() {
            if(SubTypeName==null)
            {
                return "";
            }
            return SubTypeName;
        }

        public void setSubTypeName(String subTypeName) {
            SubTypeName = subTypeName;
        }

        public List<EntityType> getSubjects() {
            if(Subjects==null)
            {
                return new ArrayList<>();
            }
            return Subjects;
        }

        public void setSubjects(List<EntityType> subjects) {
            Subjects = subjects;
        }

        @Override
        public String getPickerViewText() {
            if(this.SubTypeName==null)
            {
                return "";
            }
            return this.SubTypeName;
        }

        public static class EntityType
        {
            private String EntityTypeName =  "";
            private List<Entity> Entities = new ArrayList<>();

            public String getEntityTypeName() {
                if(EntityTypeName==null)
                {
                    return "";
                }
                return EntityTypeName;
            }

            public void setEntityTypeName(String entityTypeName) {
                EntityTypeName = entityTypeName;
            }

            public List<Entity> getEntities() {
                if(Entities==null)
                {
                    return new ArrayList<>();
                }
                return Entities;
            }

            public void setEntities(List<Entity> entities) {
                Entities = entities;
            }

            public static class Entity implements IPickerViewData, Serializable
            {
                private String SubjectID= "";
                private String SubName = "";

                public String getSubjectID() {
                    if(SubjectID==null)
                    {
                        return "";
                    }
                    return SubjectID;
                }

                public void setSubjectID(String subjectID) {
                    SubjectID = subjectID;
                }

                public String getSubName() {
                    if(SubName==null)
                    {
                        return "";
                    }
                    return SubName;
                }

                public void setSubName(String subName) {
                    SubName = subName;
                }

                @Override
                public String getPickerViewText() {
                    if(this.SubName==null)
                    {
                        return "";
                    }
                    return this.SubName;
                }
            }
        }
    }
    public class Post implements IPickerViewData
    {
        private String PostID = "";
        private String PostName = "";

        public String getPostID() {
            if(PostID==null)
            {
                return "";
            }
            return PostID;
        }

        public void setPostID(String postID) {
            PostID = postID;
        }

        public String getPostName() {
            if(PostName==null)
            {
                return "";
            }
            return PostName;
        }

        public void setPostName(String postName) {
            PostName = postName;
        }

        @Override
        public String getPickerViewText() {
            if(this.getPostName()==null)
            {
                return "";
            }
            return this.getPostName();
        }
    }
    public class Danger implements IPickerViewData {
        private String DangerPointID = "";
        private String DangerPointName = "";

        public String getDangerID() {
            if(DangerPointID==null)
            {
                return "";
            }
            return DangerPointID;
        }

        public void setDangerID(String dangerID) {
            DangerPointID = dangerID;
        }

        public String getDangerName() {
            if(DangerPointName==null)
            {
                return "";
            }
            return DangerPointName;
        }

        public void setDangerName(String dangerName) {
            DangerPointName = dangerName;
        }

        @Override
        public String getPickerViewText() {
            if(this.DangerPointName==null)
            {
                return "";
            }
            return this.DangerPointName;
        }
    }
}
