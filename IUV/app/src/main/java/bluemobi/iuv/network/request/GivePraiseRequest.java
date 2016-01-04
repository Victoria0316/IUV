package bluemobi.iuv.network.request;

import com.android.volley.Response;

import java.util.HashMap;
import java.util.Map;

import bluemobi.iuv.network.IuwHttpJsonRequest;
import bluemobi.iuv.network.response.GivePraiseResponse;


/**
 * 商场点赞
 *
 */
public class GivePraiseRequest extends
        IuwHttpJsonRequest<GivePraiseResponse>
{

    private static final String APIPATH = "mobi/shopsinfo/praiseShop";

    private String id;//商场ID
    private String userId;//用户ID

    public GivePraiseRequest(Response.Listener<GivePraiseResponse> listener,
                             Response.ErrorListener errorListener)
    {
        super(Method.POST, APIPATH, listener, errorListener);
    }

    public GivePraiseRequest(int method, String partUrl,
                             Response.Listener<GivePraiseResponse> listener,
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
    public Class<GivePraiseResponse> getResponseClass()
    {
        return GivePraiseResponse.class;
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
