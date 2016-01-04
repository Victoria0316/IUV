package bluemobi.iuv.network.response;


import bluemobi.iuv.network.IuwHttpResponse;
import bluemobi.iuv.network.model.TruesureInfo;
import bluemobi.iuv.network.model.TruesureModel;

public class GetTreasureInfoResponse extends IuwHttpResponse
{

    public TruesureInfo data;

    public TruesureInfo getData() {
        return data;
    }

    public void setData(TruesureInfo data) {
        this.data = data;
    }
}
