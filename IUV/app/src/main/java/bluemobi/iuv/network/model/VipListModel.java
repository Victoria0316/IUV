package bluemobi.iuv.network.model;

import java.util.List;

/**
 * gaoxy
 */
public class VipListModel {

        String currentpage;
         List<VipInfo> info;
        String pageTime;
        String totalnum;
        String totalpage;

        public String getCurrentpage() {
                return currentpage;
        }

        public void setCurrentpage(String currentpage) {
                this.currentpage = currentpage;
        }

        public List<VipInfo> getInfo() {
                return info;
        }

        public void setInfo(List<VipInfo> info) {
                this.info = info;
        }

        public String getPageTime() {
                return pageTime;
        }

        public void setPageTime(String pageTime) {
                this.pageTime = pageTime;
        }

        public String getTotalnum() {
                return totalnum;
        }

        public void setTotalnum(String totalnum) {
                this.totalnum = totalnum;
        }

        public String getTotalpage() {
                return totalpage;
        }

        public void setTotalpage(String totalpage) {
                this.totalpage = totalpage;
        }
}
