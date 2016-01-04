package bluemobi.iuv.network.request;

import com.android.volley.Response;

import java.util.HashMap;
import java.util.Map;

import bluemobi.iuv.network.IuwHttpJsonRequest;
import bluemobi.iuv.network.response.RecommendCountyResponse;

/**
 * Created by liufy on 2015/9/23.
 * P7 获取首页推荐国家
 */
public class RecommendCountyRequest extends IuwHttpJsonRequest<RecommendCountyResponse> {

    private static final String APIPATH = "mobi/divisioninfo/findRecommendDivisions";

    public RecommendCountyRequest(Response.Listener<RecommendCountyResponse> listener, Response.ErrorListener errorListener) {
        super(Method.POST, APIPATH, listener, errorListener);
    }

    @Override
    public String GetApiPath() {
        return APIPATH;
    }

    @Override
    public Map<String, String> GetParameters() {

        Map<String, String> map = new HashMap<String, String>();
        return map;
    }

    @Override
    public Class<RecommendCountyResponse> getResponseClass() {
        return RecommendCountyResponse.class;
    }
}
