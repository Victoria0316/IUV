package bluemobi.iuv.network.request;

import com.android.volley.Response;

import java.util.HashMap;
import java.util.Map;

import bluemobi.iuv.network.IuwHttpJsonRequest;
import bluemobi.iuv.network.response.ConfirmCodeResponse;


/**
 * 领取宝藏
 *
 */
public class ReceiveTreasureRequest extends
        IuwHttpJsonRequest<ConfirmCodeResponse>
{

    private static final String APIPATH = "mobi/customerprecious/addPrecious";

    private String userId;
    private String preciousId;
    private String longitude ;//	float 	是 	当前经度
    private String latitude ;//	float 	是 	当前纬度


    public ReceiveTreasureRequest(Response.Listener<ConfirmCodeResponse> listener,
                                  Response.ErrorListener errorListener)
    {
        super(Method.POST, APIPATH, listener, errorListener);
    }

    public ReceiveTreasureRequest(int method, String partUrl,
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
        map.put("userId", userId);
        map.put("preciousId",preciousId);
        map.put("longitude", longitude);
        map.put("latitude",latitude);
        return map;
    }

    @Override
    public Class<ConfirmCodeResponse> getResponseClass()
    {
        return ConfirmCodeResponse.class;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPreciousId() {
        return preciousId;
    }

    public void setPreciousId(String preciousId) {
        this.preciousId = preciousId;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
}
