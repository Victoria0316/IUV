package bluemobi.iuv.network.model;

/**
 * Created by gaoxy on 2015/9/28.
 * 通知实体
 */
public class NotifyInfo {

    private String id;//
    private String shopsId;//商场ID
    private String shopsNo;//商场编号
    private String shopsName;//商场名称
    private String noticeInfo       ;//通知信息
    private String createTime;//时间

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShopsId() {
        return shopsId;
    }

    public void setShopsId(String shopsId) {
        this.shopsId = shopsId;
    }

    public String getShopsNo() {
        return shopsNo;
    }

    public void setShopsNo(String shopsNo) {
        this.shopsNo = shopsNo;
    }

    public String getShopsName() {
        return shopsName;
    }

    public void setShopsName(String shopsName) {
        this.shopsName = shopsName;
    }

    public String getNoticeInfo() {
        return noticeInfo;
    }

    public void setNoticeInfo(String noticeInfo) {
        this.noticeInfo = noticeInfo;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
