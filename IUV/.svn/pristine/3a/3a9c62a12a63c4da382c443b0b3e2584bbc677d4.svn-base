package bluemobi.iuv.network.request;

import com.android.volley.Response;

import java.util.HashMap;
import java.util.Map;

import bluemobi.iuv.network.IuwHttpJsonRequest;
import bluemobi.iuv.network.response.GetTreasureInfoResponse;


/**
 * p9
 * 宝藏详情
 *
 */
public class GetTreasureInfoRequest extends
        IuwHttpJsonRequest<GetTreasureInfoResponse>
{

    private static final String APIPATH = "mobi/preciousinfo/findByPreciousId";

    private String id;
    private String userId;


    public GetTreasureInfoRequest(Response.Listener<GetTreasureInfoResponse> listener,
                                  Response.ErrorListener errorListener)
    {
        super(Method.POST, APIPATH, listener, errorListener);
    }

    public GetTreasureInfoRequest(int method, String partUrl,
                                  Response.Listener<GetTreasureInfoResponse> listener,
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
    public Class<GetTreasureInfoResponse> getResponseClass()
    {
        return GetTreasureInfoResponse.class;
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
