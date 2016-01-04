package bluemobi.iuv.network.request;

import com.android.volley.Response;

import java.util.HashMap;
import java.util.Map;

import bluemobi.iuv.network.IuwHttpJsonRequest;
import bluemobi.iuv.network.response.HotShopTypeResponse;


/**
 * 获取热门商场分类--P8-3 获取热门商场分类
 *
 */
public class HotShopTypeRequest extends
        IuwHttpJsonRequest<HotShopTypeResponse>
{

    private static final String APIPATH = "mobi/shopstypeinfo/findAllSecType";


    public HotShopTypeRequest(Response.Listener<HotShopTypeResponse> listener,
                              Response.ErrorListener errorListener)
    {
        super(Method.POST, APIPATH, listener, errorListener);
    }

    public HotShopTypeRequest(int method, String partUrl,
                              Response.Listener<HotShopTypeResponse> listener,
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
        return map;
    }

    @Override
    public Class<HotShopTypeResponse> getResponseClass()
    {
        return HotShopTypeResponse.class;
    }

}
