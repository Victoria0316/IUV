package bluemobi.iuv.eventbus;

/**
 * Created by gaoxy on 2015/9/29.
 */
public class RefreshMyInfoEvent extends  BaseEvent {

    private boolean isRefresh=false;

    public boolean isRefresh() {
        return isRefresh;
    }


    public void setIsRefresh(boolean isRefresh) {
        this.isRefresh = isRefresh;
    }
}
