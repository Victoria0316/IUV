package bluemobi.iuv.network.request;

import com.android.volley.Response;

import java.util.HashMap;
import java.util.Map;

import bluemobi.iuv.network.IuwHttpJsonRequest;
import bluemobi.iuv.network.response.SendRegisterResponse;


/**
 * yanzheng验证码
 *
 */
public class ForgetPwdRequest extends
        IuwHttpJsonRequest<SendRegisterResponse>
{

    private static final String APIPATH = "mobi/adminuser/updatePwdFromForget";

    private String userName;

    private String password;

    public ForgetPwdRequest(Response.Listener<SendRegisterResponse> listener,
                            Response.ErrorListener errorListener)
    {
        super(Method.POST, APIPATH, listener, errorListener);
    }

    public ForgetPwdRequest(int method, String partUrl,
                            Response.Listener<SendRegisterResponse> listener,
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
        map.put("pass","n");
        map.put("password",password);
        return map;
    }

    @Override
    public Class<SendRegisterResponse> getResponseClass()
    {
        return SendRegisterResponse.class;
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
