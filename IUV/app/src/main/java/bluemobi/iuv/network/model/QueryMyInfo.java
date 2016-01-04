package bluemobi.iuv.network.model;

/**
 * 我的个人信息
 */
public class QueryMyInfo {
        public String nickName;//昵称;
        public String headPicUrl;//头像地址
        public String customerGender;// 性别 1:男，2:女，0为未知

        public String getNickName() {
                return nickName;
        }

        public void setNickName(String nickName) {
                this.nickName = nickName;
        }

        public String getHeadPicUrl() {
                return headPicUrl;
        }

        public void setHeadPicUrl(String headPicUrl) {
                this.headPicUrl = headPicUrl;
        }

        public String getCustomerGender() {
                return customerGender;
        }

        public void setCustomerGender(String customerGender) {
                this.customerGender = customerGender;
        }
}
