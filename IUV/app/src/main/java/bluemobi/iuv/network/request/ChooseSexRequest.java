package bluemobi.iuv.network.request;

import com.android.volley.Response;

import java.util.HashMap;
import java.util.Map;

import bluemobi.iuv.network.IuwHttpJsonRequest;
import bluemobi.iuv.network.response.LoginResponse;


/**
 * 选择性别
 *
 */
public class ChooseSexRequest extends
        IuwHttpJsonRequest<LoginResponse>
{

    private static final String APIPATH = "mobi/personalcenter/updateCustomerGender";

    private String userId;
    private String customerGender;

    public ChooseSexRequest(Response.Listener<LoginResponse> listener,
                            Response.ErrorListener errorListener)
    {
        super(Method.POST, APIPATH, listener, errorListener);
    }

    public ChooseSexRequest(int method, String partUrl,
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
        map.put("customerGender",customerGender);
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

    public String getCustomerGender() {
        return customerGender;
    }

    public void setCustomerGender(String customerGender) {
        this.customerGender = customerGender;
    }
}
