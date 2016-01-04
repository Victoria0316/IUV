package bluemobi.iuv.network.request;

import com.android.volley.Response;

import java.util.HashMap;
import java.util.Map;

import bluemobi.iuv.network.IuwHttpJsonRequest;
import bluemobi.iuv.network.response.ConfirmCodeResponse;


/**
 * yanzheng验证码
 *
 */
public class ConfirmCodeRequest extends
        IuwHttpJsonRequest<ConfirmCodeResponse>
{

    private static final String APIPATH = "mobi/cascode/checkIdentifyingCode";

    private String cellphone;

    /**
     * 验证码用途 register：注册；login：登录；bind：绑定（邮箱、或手机）；forgotpassowrd：忘记密码；
     */
    private String type;

    private String validationCode;




    public ConfirmCodeRequest(Response.Listener<ConfirmCodeResponse> listener,
                              Response.ErrorListener errorListener)
    {
        super(Method.POST, APIPATH, listener, errorListener);
    }

    public ConfirmCodeRequest(int method, String partUrl,
                              Response.Listener<ConfirmCodeResponse> listener,
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
        map.put("pass","n");
        map.put("type",type);
        map.put("validationCode",validationCode);


        return map;
    }

    @Override
    public Class<ConfirmCodeResponse> getResponseClass()
    {
        return ConfirmCodeResponse.class;
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

    public String getValidationCode() {
        return validationCode;
    }

    public void setValidationCode(String validationCode) {
        this.validationCode = validationCode;
    }
}
