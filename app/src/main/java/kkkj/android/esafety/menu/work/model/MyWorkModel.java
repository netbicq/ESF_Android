package kkkj.android.esafety.menu.work.model;

import java.util.ArrayList;
import java.util.List;

import kkkj.android.esafety.http.ESafetyResponse;

public class MyWorkModel {
    public static class Response extends ESafetyResponse
    {
        public List<Data> data = new ArrayList<>();

        public List<Data> getData() {
            return data;
        }

        public void setData(List<Data> data) {
            this.data = data;
        }

        public static class Data
        {
            String name= "";
            String date= "";
            String num= "";
            String total= "";

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getNum() {
                return num;
            }

            public void setNum(String num) {
                this.num = num;
            }

            public String getTotal() {
                return total;
            }

            public void setTotal(String total) {
                this.total = total;
            }
        }
    }
}
