package bluemobi.iuv.network.response;


import bluemobi.iuv.network.IuwHttpResponse;
import bluemobi.iuv.network.model.GoodsMoel;
import bluemobi.iuv.network.model.NotifyModel;

public class GetMyNotifyResponse extends IuwHttpResponse
{

    private NotifyModel data;

    public NotifyModel getData() {
        return data;
    }

    public void setData(NotifyModel data) {
        this.data = data;
    }
}
