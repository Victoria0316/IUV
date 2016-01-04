package bluemobi.iuv.network.request;

import com.android.volley.Response;

import java.util.HashMap;
import java.util.Map;

import bluemobi.iuv.network.IuwHttpJsonRequest;
import bluemobi.iuv.network.response.LoginResponse;


/**
 * 11-5 我的意见
 *
 */
public class AddAdviceRequest extends
        IuwHttpJsonRequest<LoginResponse>
{

    private static final String APIPATH = "mobi/feedback/addFeedBack";

    private String userId;
    private String contactWay;
    private String content;

    public AddAdviceRequest(Response.Listener<LoginResponse> listener,
                            Response.ErrorListener errorListener)
    {
        super(Method.POST, APIPATH, listener, errorListener);
    }

    public AddAdviceRequest(int method, String partUrl,
                            Response.Listener<LoginResponse> listener,
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
        map.put("content",content);
        map.put("contactWay",contactWay);
        return map;
    }

    @Override
    public Class<LoginResponse> getResponseClass()
    {
        return LoginResponse.class;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getContactWay() {
        return contactWay;
    }

    public void setContactWay(String contactWay) {
        this.contactWay = contactWay;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
