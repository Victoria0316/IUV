package bluemobi.iuv.network.request;

import com.android.volley.Response;

import java.util.HashMap;
import java.util.Map;

import bluemobi.iuv.network.IuwHttpJsonRequest;
import bluemobi.iuv.network.response.GetMyNotifyResponse;
import bluemobi.iuv.network.response.GetMyNotifyResponse;


/**
 * 11-4 通知
 *
 */
public class GetMyNotifyRequest extends
        IuwHttpJsonRequest<GetMyNotifyResponse>
{

    private static final String APIPATH = "mobi/shopsnoticeinfo/findByPage";

    private String currentnum;
    private String currentpage;

    public GetMyNotifyRequest(Response.Listener<GetMyNotifyResponse> listener,
                              Response.ErrorListener errorListener)
    {
        super(Method.POST, APIPATH, listener, errorListener);
    }

    public GetMyNotifyRequest(int method, String partUrl,
                              Response.Listener<GetMyNotifyResponse> listener,
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
        return map;
    }

    @Override
    public Class<GetMyNotifyResponse> getResponseClass()
    {
        return GetMyNotifyResponse.class;
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
