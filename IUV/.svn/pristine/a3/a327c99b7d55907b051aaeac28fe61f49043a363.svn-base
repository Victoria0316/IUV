package bluemobi.iuv.network.request;

import com.android.volley.Response;

import java.util.HashMap;
import java.util.Map;

import bluemobi.iuv.network.IuwHttpJsonRequest;
import bluemobi.iuv.network.response.GetVipCardResponse;


/**
 * p10-10领取vip卡卷
 *
 */
public class GetVipCardRequest extends
        IuwHttpJsonRequest<GetVipCardResponse>
{

    private static final String APIPATH = "mobi/customermembershipcard/addMembershipCard";

    private String userId;
    private String membershipCardId;//卡ID

    public GetVipCardRequest(Response.Listener<GetVipCardResponse> listener,
                             Response.ErrorListener errorListener)
    {
        super(Method.POST, APIPATH, listener, errorListener);
    }

    public GetVipCardRequest(int method, String partUrl,
                             Response.Listener<GetVipCardResponse> listener,
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
        map.put("membershipCardId", membershipCardId);
        return map;
    }

    @Override
    public Class<GetVipCardResponse> getResponseClass()
    {
        return GetVipCardResponse.class;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMembershipCardId() {
        return membershipCardId;
    }

    public void setMembershipCardId(String membershipCardId) {
        this.membershipCardId = membershipCardId;
    }
}
