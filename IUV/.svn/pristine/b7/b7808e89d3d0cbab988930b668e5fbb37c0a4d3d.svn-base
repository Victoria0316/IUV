package bluemobi.iuv.network.request;

import com.android.volley.Response;

import java.util.HashMap;
import java.util.Map;

import bluemobi.iuv.network.IuwHttpJsonRequest;
import bluemobi.iuv.network.response.PraiseGoodResponse;
import bluemobi.iuv.network.response.PraiseGoodResponse;


/**
 * 商品点赞
 *
 */
public class PraiseGoodRequest extends
        IuwHttpJsonRequest<PraiseGoodResponse>
{

    private static final String APIPATH = "mobi/productinfo/praiseProduct";

    private String id;//商pinID
    private String userId;//用户ID

    public PraiseGoodRequest(Response.Listener<PraiseGoodResponse> listener,
                             Response.ErrorListener errorListener)
    {
        super(Method.POST, APIPATH, listener, errorListener);
    }

    public PraiseGoodRequest(int method, String partUrl,
                             Response.Listener<PraiseGoodResponse> listener,
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
        map.put("id", id);
        map.put("userId", userId);
        return map;
    }

    @Override
    public Class<PraiseGoodResponse> getResponseClass()
    {
        return PraiseGoodResponse.class;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
