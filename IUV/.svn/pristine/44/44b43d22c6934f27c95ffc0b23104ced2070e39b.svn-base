package bluemobi.iuv.network.request;

import com.android.volley.Response;

import java.util.HashMap;
import java.util.Map;

import bluemobi.iuv.network.IuwHttpJsonRequest;
import bluemobi.iuv.network.response.LoginResponse;


/**
 * 判断该用户是否首次进行第三方登陆
 *
 */
public class CheckThirdInfoRequest extends
        IuwHttpJsonRequest<LoginResponse>
{

    private static final String APIPATH = "mobi/adminuser/checkThirdInfo";

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private String userName	;//string	是	第三方登陆用户名
    private String	type	;//登陆类型 "qq"：QQ登陆 "wx":微信登陆 "weibo":微博登陆

    public CheckThirdInfoRequest(Response.Listener<LoginResponse> listener,
                                 Response.ErrorListener errorListener)
    {
        super(Method.POST, APIPATH, listener, errorListener);
    }

    public CheckThirdInfoRequest(int method, String partUrl,
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
        map.put("userName", userName);
        map.put("type",type);
        return map;
    }

    @Override
    public Class<LoginResponse> getResponseClass()
    {
        return LoginResponse.class;
    }


}
