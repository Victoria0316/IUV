package bluemobi.iuv.network.request;

import com.android.volley.Response;

import java.util.HashMap;
import java.util.Map;

import bluemobi.iuv.network.IuwHttpJsonRequest;
import bluemobi.iuv.network.response.LoginResponse;


/**
 * 将第三方登陆账号与手机号进行绑定
 *
 */
public class ThirdBindPhoneRequest extends
        IuwHttpJsonRequest<LoginResponse>
{

    private static final String APIPATH = "mobi/adminuser/bindCellphone";
    private String userName	;//string	是	第三方登陆用户名
    private String	type	;//登陆类型 "qq"：QQ登陆 "wx":微信登陆 "weibo":微博登陆
    private String cellphone	;//string	是	手机号
    private String nickName	;//string	是	昵称
    public ThirdBindPhoneRequest(Response.Listener<LoginResponse> listener,
                                 Response.ErrorListener errorListener)
    {
        super(Method.POST, APIPATH, listener, errorListener);
    }

    public ThirdBindPhoneRequest(int method, String partUrl,
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
        map.put("cellphone", cellphone);
        map.put("nickName",nickName);
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }


}
