package bluemobi.iuv.network.response;


import bluemobi.iuv.network.IuwHttpResponse;
import bluemobi.iuv.network.model.GoodsMoel;
import bluemobi.iuv.network.model.HotGoods;

public class QueryThreeHotGoodsResponse extends IuwHttpResponse
{

    private HotGoods data;

    public HotGoods getData() {
        return data;
    }

    public void setData(HotGoods data) {
        this.data = data;
    }
}
