package bluemobi.iuv.network.request;

import com.android.volley.Response;

import java.util.HashMap;
import java.util.Map;

import bluemobi.iuv.network.IuwHttpJsonRequest;
import bluemobi.iuv.network.response.GetTruesureListResponse;

/**
 * 我的宝藏
 */
public class GetTruesureListRequest extends IuwHttpJsonRequest<GetTruesureListResponse> {

    private static final String APIPATH = "mobi/preciousinfo/findMyPreciousByPage";


    private String userId;

    private String currentnum;

    private String currentpage;
    public GetTruesureListRequest(Response.Listener<GetTruesureListResponse> listener, Response.ErrorListener errorListener) {
        super(Method.POST, APIPATH, listener, errorListener);
    }

    @Override
    public String GetApiPath() {
        return APIPATH;
    }

    @Override
    public Map<String, String> GetParameters() {

        Map<String, String> map = new HashMap<String, String>();
        map.put("userId",userId);
        map.put("currentnum",currentnum);
        map.put("currentpage",currentpage);
        return map;
    }

    @Override
    public Class<GetTruesureListResponse> getResponseClass() {
        return GetTruesureListResponse.class;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCurrentnum() {
        return currentnum;
    }

    public void setCurrentnum(String currentnum) {
        this.currentnum = currentnum;
    }

    public String getCurrentpage() {
        return currentpage;
    }

    public void setCurrentpage(String currentpage) {
        this.currentpage = currentpage;
    }
}
