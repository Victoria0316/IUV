package bluemobi.iuv.network.response;

import java.util.ArrayList;
import java.util.List;

import bluemobi.iuv.network.IuwHttpResponse;
import bluemobi.iuv.network.model.HotShopTypeModel;

/**
 */
public class HotShopTypeResponse extends IuwHttpResponse {


    private List<HotShopTypeModel> data;


    public List<HotShopTypeModel> getData() {
        return data;
    }

    public void setData(List<HotShopTypeModel> data) {
        this.data = data;
    }
}
