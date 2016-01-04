package bluemobi.iuv.network.request;

import com.android.volley.Response;

import java.util.HashMap;
import java.util.Map;

import bluemobi.iuv.network.IuwHttpJsonRequest;
import bluemobi.iuv.network.response.UpdatePwdResponse;


/**
 * 修改密码
 *
 */
public class UpdatePwdRequest extends
        IuwHttpJsonRequest<UpdatePwdResponse>
{

    private static final String APIPATH = "mobi/adminuser/updatePassword";

    private String userName;//用户名（手机号）
    private String oldPassword;
    private String newPassword;

    public UpdatePwdRequest(Response.Listener<UpdatePwdResponse> listener,
                            Response.ErrorListener errorListener)
    {
        super(Method.POST, APIPATH, listener, errorListener);
    }

    public UpdatePwdRequest(int method, String partUrl,
                            Response.Listener<UpdatePwdResponse> listener,
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
        map.put("oldPassword",oldPassword);
        map.put("newPassword",newPassword);
        return map;
    }

    @Override
    public Class<UpdatePwdResponse> getResponseClass()
    {
        return UpdatePwdResponse.class;
    }

    public String getUsername() {
        return userName;
    }

    public void setUsername(String username) {
        this.userName = username;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
