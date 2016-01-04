package bluemobi.iuv.network.request;

import com.android.volley.Response;

import java.util.HashMap;
import java.util.Map;

import bluemobi.iuv.network.IuwHttpJsonRequest;
import bluemobi.iuv.network.response.GetCodeResponse;



/**
 * 获取验证码
 *
 */
public class GetCodeRequest extends
        IuwHttpJsonRequest<GetCodeResponse>
{

    private static final String APIPATH = "mobi/cascode/identifyingCode";

    private String cellphone;

    /**
     * 验证码用途 register：注册；login：登录；bind：绑定（邮箱、或手机）；forgotpassowrd：忘记密码；
     */
    private String type;


    public GetCodeRequest(Response.Listener<GetCodeResponse> listener,
            Response.ErrorListener errorListener)
    {
        super(Method.POST, APIPATH, listener, errorListener);
    }

    public GetCodeRequest(int method, String partUrl,
            Response.Listener<GetCodeResponse> listener,
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
        map.put("cellphone", cellphone);
        map.put("type",type);
        map.put("pass","n");
        return map;
    }

    @Override
    public Class<GetCodeResponse> getResponseClass()
    {
        return GetCodeResponse.class;
    }


    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
