package bluemobi.iuv.network.response;

import java.util.List;

import bluemobi.iuv.network.IuwHttpResponse;
import bluemobi.iuv.network.model.HotShopListMoedl;
import bluemobi.iuv.network.model.HotShopTypeModel;

/**
 */
public class HotShopListResponse extends IuwHttpResponse {


    private HotShopListMoedl data;


    public HotShopListMoedl getData() {
        return data;
    }

    public void setData(HotShopListMoedl data) {
        this.data = data;
    }
}
