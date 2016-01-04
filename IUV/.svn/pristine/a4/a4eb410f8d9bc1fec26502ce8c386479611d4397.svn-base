package bluemobi.iuv.network.request;

import com.android.volley.Response;

import java.util.HashMap;
import java.util.Map;

import bluemobi.iuv.network.IuwHttpJsonRequest;
import bluemobi.iuv.network.response.CollectionGoodResponse;


/**
 * 收藏商品
 *
 */
public class CollectionGoodRequest extends
        IuwHttpJsonRequest<CollectionGoodResponse>
{

    private static final String APIPATH = "mobi/productinfo/findCollectProductByPage";

    private String userId;
    private String currentnum;
    private String currentpage;

    public CollectionGoodRequest(Response.Listener<CollectionGoodResponse> listener,
                                 Response.ErrorListener errorListener)
    {
        super(Method.POST, APIPATH, listener, errorListener);
    }

    public CollectionGoodRequest(int method, String partUrl,
                                 Response.Listener<CollectionGoodResponse> listener,
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
        map.put("userId", userId);
        map.put("currentpage",currentpage);
        map.put("currentnum",currentnum);
        return map;
    }

    @Override
    public Class<CollectionGoodResponse> getResponseClass()
    {
        return CollectionGoodResponse.class;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

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
}
