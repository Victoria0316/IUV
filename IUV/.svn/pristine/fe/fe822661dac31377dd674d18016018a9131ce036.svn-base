package bluemobi.iuv.network.request;

import com.android.volley.Response;

import java.util.HashMap;
import java.util.Map;

import bluemobi.iuv.network.IuwHttpJsonRequest;
import bluemobi.iuv.network.response.LoginResponse;


/**
 * denglu
 *
 */
public class LoginRequest extends
        IuwHttpJsonRequest<LoginResponse>
{

    private static final String APIPATH = "mobi/adminuser/login";

    private String userName;
    private String password;

    public LoginRequest(Response.Listener<LoginResponse> listener,
                        Response.ErrorListener errorListener)
    {
        super(Method.POST, APIPATH, listener, errorListener);
    }

    public LoginRequest(int method, String partUrl,
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
        map.put("password",password);
        return map;
    }

    @Override
    public Class<LoginResponse> getResponseClass()
    {
        return LoginResponse.class;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
