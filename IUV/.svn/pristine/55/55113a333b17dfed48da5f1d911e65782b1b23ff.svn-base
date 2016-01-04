package bluemobi.iuv.network.request;

import com.android.volley.Response;

import java.util.HashMap;
import java.util.Map;

import bluemobi.iuv.network.IuwHttpJsonRequest;
import bluemobi.iuv.network.response.LoginResponse;
import bluemobi.iuv.network.response.TopSortResponse;


/**
 * 获取置顶分类--获取置顶分类
 *
 */
public class TopSortRequest extends
        IuwHttpJsonRequest<TopSortResponse>
{

    private static final String APIPATH = "mobi/shopstypeinfo/findTopTypes";

    public TopSortRequest(Response.Listener<TopSortResponse> listener,
                          Response.ErrorListener errorListener)
    {
        super(Method.POST, APIPATH, listener, errorListener);
    }

    public TopSortRequest(int method, String partUrl,
                          Response.Listener<TopSortResponse> listener,
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
    public Class<TopSortResponse> getResponseClass()
    {
        return TopSortResponse.class;
    }


}
