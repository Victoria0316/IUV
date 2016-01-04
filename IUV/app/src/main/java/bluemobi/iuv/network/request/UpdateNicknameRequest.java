package bluemobi.iuv.network.request;

import com.android.volley.Response;

import java.util.HashMap;
import java.util.Map;

import bluemobi.iuv.network.IuwHttpJsonRequest;
import bluemobi.iuv.network.response.LoginResponse;


/**
 * xiugai mingzi
 *
 */
public class UpdateNicknameRequest extends
        IuwHttpJsonRequest<LoginResponse>
{

    private static final String APIPATH = "mobi/personalcenter/updateNickName";

    private String userId;
    private String nickName;

    public UpdateNicknameRequest(Response.Listener<LoginResponse> listener,
                                 Response.ErrorListener errorListener)
    {
        super(Method.POST, APIPATH, listener, errorListener);
    }

    public UpdateNicknameRequest(int method, String partUrl,
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
        map.put("nickName",nickName);
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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
