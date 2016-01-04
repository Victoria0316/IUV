package bluemobi.iuv.network.model;

import java.util.List;

/**
 * Created by gaoxy on 2015/9/28.
 * 通知
 */
public class NotifyModel {

    private List<NotifyInfo> info;
    String currentpage;
    String pageTime;
    String totalnum;
    String totalpage;

    public String getCurrentpage() {
        return currentpage;
    }

    public void setCurrentpage(String currentpage) {
        this.currentpage = currentpage;
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

    public List<NotifyInfo> getInfo() {
        return info;
    }

    public void setInfo(List<NotifyInfo> info) {
        this.info = info;
    }
}
