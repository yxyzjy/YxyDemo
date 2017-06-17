package com.example.yxy.yxydemo.bean;

/**
 * 测试显示数据
 * Created by WZG on 2016/7/16.
 */
public class SubjectResulte {

    /**
     * Data : {"CreateTime":"2016-06-29T18:14:58.4721813+08:00","UserRole":519,"IsVVIP":true,"UserID":"ea623578-691c-44cd-a30d-f778d74a3020","UserNumber":22,"UserName":"第一","CellPhoneNumber":"15500000001","UserLogo":"http://static.v7.com/avatars/ea623578691c44cda30df778d74a3020-200x200.jpg","UserToken":"D0473546CE9122DDCA1802CF03918CE70065F6D2AA4FC107D30DE1D011030D4F1B1D8483C4A2D03D4B7A5D0109102B60A2A35E6BE0DE932101553D0CB4D951360573AB6376B40540C5C32E80AB218D772FA507C5D64EAD0D0D8BD89230DCE8F90DE3825975560CEDB49F63A424A0E8FFDCD9339BFF83783F9E7B85D510578884555D1E38E4F9BC4CD66B31CC43F7864712211746C0A0205D97D6BDDBB7B11DC2B311B25B6060CEE430623FFB7B632A6D1A577A41BC776E07366FFB719F1A1922CD4C57B329D5B922343C0FA4D9736B3F45F5F121BCB942ED6A7F8661FE21026489DC4D47639C41ECD6ABB694FD781A437327013D87224784BCCD3C36442CC48E4E74DF2A"}
     */

    private DataBean Data;

    public DataBean getData() {
        return Data;
    }

    public void setData(DataBean Data) {
        this.Data = Data;
    }

    public static class DataBean {
        /**
         * CreateTime : 2016-06-29T18:14:58.4721813+08:00
         * UserRole : 519
         * IsVVIP : true
         * UserID : ea623578-691c-44cd-a30d-f778d74a3020
         * UserNumber : 22
         * UserName : 第一
         * CellPhoneNumber : 15500000001
         * UserLogo : http://static.v7.com/avatars/ea623578691c44cda30df778d74a3020-200x200.jpg
         * UserToken : D0473546CE9122DDCA1802CF03918CE70065F6D2AA4FC107D30DE1D011030D4F1B1D8483C4A2D03D4B7A5D0109102B60A2A35E6BE0DE932101553D0CB4D951360573AB6376B40540C5C32E80AB218D772FA507C5D64EAD0D0D8BD89230DCE8F90DE3825975560CEDB49F63A424A0E8FFDCD9339BFF83783F9E7B85D510578884555D1E38E4F9BC4CD66B31CC43F7864712211746C0A0205D97D6BDDBB7B11DC2B311B25B6060CEE430623FFB7B632A6D1A577A41BC776E07366FFB719F1A1922CD4C57B329D5B922343C0FA4D9736B3F45F5F121BCB942ED6A7F8661FE21026489DC4D47639C41ECD6ABB694FD781A437327013D87224784BCCD3C36442CC48E4E74DF2A
         */

        private String CreateTime;
        private int UserRole;
        private boolean IsVVIP;
        private String UserID;
        private int UserNumber;
        private String UserName;
        private String CellPhoneNumber;
        private String UserLogo;
        private String UserToken;

        public String getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(String CreateTime) {
            this.CreateTime = CreateTime;
        }

        public int getUserRole() {
            return UserRole;
        }

        public void setUserRole(int UserRole) {
            this.UserRole = UserRole;
        }

        public boolean isIsVVIP() {
            return IsVVIP;
        }

        public void setIsVVIP(boolean IsVVIP) {
            this.IsVVIP = IsVVIP;
        }

        public String getUserID() {
            return UserID;
        }

        public void setUserID(String UserID) {
            this.UserID = UserID;
        }

        public int getUserNumber() {
            return UserNumber;
        }

        public void setUserNumber(int UserNumber) {
            this.UserNumber = UserNumber;
        }

        public String getUserName() {
            return UserName;
        }

        public void setUserName(String UserName) {
            this.UserName = UserName;
        }

        public String getCellPhoneNumber() {
            return CellPhoneNumber;
        }

        public void setCellPhoneNumber(String CellPhoneNumber) {
            this.CellPhoneNumber = CellPhoneNumber;
        }

        public String getUserLogo() {
            return UserLogo;
        }

        public void setUserLogo(String UserLogo) {
            this.UserLogo = UserLogo;
        }

        public String getUserToken() {
            return UserToken;
        }

        public void setUserToken(String UserToken) {
            this.UserToken = UserToken;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "CreateTime='" + CreateTime + '\'' +
                    ", UserRole=" + UserRole +
                    ", IsVVIP=" + IsVVIP +
                    ", UserID='" + UserID + '\'' +
                    ", UserNumber=" + UserNumber +
                    ", UserName='" + UserName + '\'' +
                    ", CellPhoneNumber='" + CellPhoneNumber + '\'' +
                    ", UserLogo='" + UserLogo + '\'' +
                    ", UserToken='" + UserToken + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "SubjectResulte{" +
                "Data=" + Data +
                '}';
    }
}
