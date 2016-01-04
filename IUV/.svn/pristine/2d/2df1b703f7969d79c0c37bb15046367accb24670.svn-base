package bluemobi.iuv.network.request;

import com.android.volley.Response;

import java.util.HashMap;
import java.util.Map;

import bluemobi.iuv.network.IuwHttpJsonRequest;
import bluemobi.iuv.network.response.LoginResponse;
import bluemobi.iuv.network.response.StoreDetailsResponse;


/**
 * P8_4商场详情页面
 *
 */
public class StoreDetailsRequest extends
        IuwHttpJsonRequest<StoreDetailsResponse>
{

    private static final String APIPATH = "mobi/shopsinfo/findShopById";

    private String userId;

    private String id;

    public StoreDetailsRequest(Response.Listener<StoreDetailsResponse> listener,
                               Response.ErrorListener errorListener)
    {
        super(Method.POST, APIPATH, listener, errorListener);
    }

    public StoreDetailsRequest(int method, String partUrl,
                               Response.Listener<StoreDetailsResponse> listener,
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
        map.put("id", id);
        return map;
    }

    @Override
    public Class<StoreDetailsResponse> getResponseClass()
    {
        return StoreDetailsResponse.class;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


}
