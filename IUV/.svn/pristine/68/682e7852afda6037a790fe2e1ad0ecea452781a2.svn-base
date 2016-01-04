package bluemobi.iuv.network.request;

import com.android.volley.Response;

import java.util.HashMap;
import java.util.Map;

import bluemobi.iuv.network.IuwHttpJsonRequest;
import bluemobi.iuv.network.response.BrandListResponse;
import bluemobi.iuv.network.response.GivePraiseResponse;


/**
 * 获取品牌列表--P_8_2_1 获取品牌列表
 *
 */
public class BrandListRequest extends
        IuwHttpJsonRequest<BrandListResponse>
{

    private static final String APIPATH = "mobi/productattributevalue/findBrandsAndProductsByPage";

    public String getCurrentnum() {
        return currentnum;
    }

    public void setCurrentnum(String currentnum) {
        this.currentnum = currentnum;
    }

    public String getCurrentpage() {
        return currentpage;
    }

    public void setCurrentpage(String currentpage) {
        this.currentpage = currentpage;
    }

    public String getShopsId() {
        return shopsId;
    }

    public void setShopsId(String shopsId) {
        this.shopsId = shopsId;
    }

    private String shopsId;//商场ID
    private String currentnum	;//string	是	每页条数
    private String currentpage	;//stringstring	是	当前页数

    public BrandListRequest(Response.Listener<BrandListResponse> listener,
                            Response.ErrorListener errorListener)
    {
        super(Method.POST, APIPATH, listener, errorListener);
    }

    public BrandListRequest(int method, String partUrl,
                            Response.Listener<BrandListResponse> listener,
                            Response.ErrorListener errorListener)
    {
        super(method, partUrl, listener, errorListener);
    }

    @Override
    public String GetApiPath()
    {
        return APIPATH;
    }

    @Override
    public Map<String, String> GetParameters()
    {
        Map<String, String> map = new HashMap<String, String>();
        map.put("shopsId", shopsId);
        map.put("currentnum", currentnum);
        map.put("currentpage", currentpage);
        return map;
    }

    @Override
    public Class<BrandListResponse> getResponseClass()
    {
        return BrandListResponse.class;
    }


}
