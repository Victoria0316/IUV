package bluemobi.iuv.network.request;

import com.android.volley.Response;

import java.util.HashMap;
import java.util.Map;

import bluemobi.iuv.network.IuwHttpJsonRequest;
import bluemobi.iuv.network.response.QueryMyInfoResponse;

/**
 * Created by liufy on 2015/9/23.
 * P7 获取轮播图
 */
public class QueryMyInfoRequest extends IuwHttpJsonRequest<QueryMyInfoResponse> {

    private static final String APIPATH = "mobi/customerinfo/findByUserId";

    private String userId;

    public QueryMyInfoRequest(Response.Listener<QueryMyInfoResponse> listener, Response.ErrorListener errorListener) {
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
        return map;
    }

    @Override
    public Class<QueryMyInfoResponse> getResponseClass() {
        return QueryMyInfoResponse.class;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
