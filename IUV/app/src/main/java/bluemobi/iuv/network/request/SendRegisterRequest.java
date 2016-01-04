package bluemobi.iuv.network.request;

import com.android.volley.Response;

import java.util.HashMap;
import java.util.Map;

import bluemobi.iuv.network.IuwHttpJsonRequest;
import bluemobi.iuv.network.response.ConfirmCodeResponse;
import bluemobi.iuv.network.response.SendRegisterResponse;


/**
 * zhuce
 *
 */
public class SendRegisterRequest extends
        IuwHttpJsonRequest<SendRegisterResponse>
{

    private static final String APIPATH = "mobi/adminuser/register";

    private String userName;//手机号

    private String password;

    private String nickName;//昵称




    public SendRegisterRequest(Response.Listener<SendRegisterResponse> listener,
                               Response.ErrorListener errorListener)
    {
        super(Method.POST, APIPATH, listener, errorListener);
    }

    public SendRegisterRequest(int method, String partUrl,
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
        map.put("password",password);
        map.put("pass","n");
        map.put("nickName",nickName);
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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
