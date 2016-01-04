package bluemobi.iuv.network.response;


import bluemobi.iuv.network.IuwHttpResponse;
import bluemobi.iuv.network.model.GoodsMoel;
import bluemobi.iuv.network.model.VersionMoel;

public class CheckVersionResponse extends IuwHttpResponse
{

    private VersionMoel data;

    public VersionMoel getData() {
        return data;
    }

    public void setData(VersionMoel data) {
        this.data = data;
    }
}
