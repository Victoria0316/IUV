package bluemobi.iuv.network.request;

import com.android.volley.Response;

import java.util.HashMap;
import java.util.Map;

import bluemobi.iuv.network.IuwHttpJsonRequest;
import bluemobi.iuv.network.response.CheckVersionResponse;


/**
 * 版本更新
 *
 */
public class CheckVersionRequest extends
        IuwHttpJsonRequest<CheckVersionResponse>
{

    private static final String APIPATH = "mobi/trendversion/getLastVersion";

    private String app;

    public CheckVersionRequest(Response.Listener<CheckVersionResponse> listener,
                               Response.ErrorListener errorListener)
    {
        super(Method.POST, APIPATH, listener, errorListener);
    }

    public CheckVersionRequest(int method, String partUrl,
                               Response.Listener<CheckVersionResponse> listener,
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
        map.put("app", "android");
        return map;
    }

    @Override
    public Class<CheckVersionResponse> getResponseClass()
    {
        return CheckVersionResponse.class;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }
}
