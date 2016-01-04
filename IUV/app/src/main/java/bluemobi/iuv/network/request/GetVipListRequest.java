package bluemobi.iuv.network.request;

import com.android.volley.Response;

import java.util.HashMap;
import java.util.Map;

import bluemobi.iuv.network.IuwHttpJsonRequest;
import bluemobi.iuv.network.response.GetVipListResponse;
import bluemobi.iuv.network.response.GetVipListResponse;


/**
 * p11-1
 * 获取vip列表
 *
 */
public class GetVipListRequest extends
        IuwHttpJsonRequest<GetVipListResponse>
{

    private static final String APIPATH = "mobi/customermembershipcard/findMembershipCardByPage";


    private String currentnum;

    private String currentpage;
    private String userId;

    public GetVipListRequest(Response.Listener<GetVipListResponse> listener,
                             Response.ErrorListener errorListener)
    {
        super(Method.POST, APIPATH, listener, errorListener);
    }

    public GetVipListRequest(int method, String partUrl,
                             Response.Listener<GetVipListResponse> listener,
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
        map.put("currentnum", currentnum);
        map.put("currentpage",currentpage);
        map.put("userId",userId);

        return map;
    }

    @Override
    public Class<GetVipListResponse> getResponseClass()
    {
        return GetVipListResponse.class;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
