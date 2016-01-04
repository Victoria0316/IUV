package bluemobi.iuv.network.request;

import com.android.volley.Response;

import java.util.HashMap;
import java.util.Map;

import bluemobi.iuv.network.IuwHttpJsonRequest;
import bluemobi.iuv.network.response.VipInfoResponse;


/**
 *VIP卡详情
 *
 */
public class VipInfoRequest extends
        IuwHttpJsonRequest<VipInfoResponse>
{

    private static final String APIPATH = "mobi/membershipcardinfo/findAllByPage";

    private String currentnum;
    private String currentpage;

    public VipInfoRequest(Response.Listener<VipInfoResponse> listener,
                          Response.ErrorListener errorListener)
    {
        super(Method.POST, APIPATH, listener, errorListener);
    }

    public VipInfoRequest(int method, String partUrl,
                          Response.Listener<VipInfoResponse> listener,
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
        map.put("currentpage", currentpage);
        map.put("currentnum", currentnum);
        return map;
    }

    @Override
    public Class<VipInfoResponse> getResponseClass()
    {
        return VipInfoResponse.class;
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
