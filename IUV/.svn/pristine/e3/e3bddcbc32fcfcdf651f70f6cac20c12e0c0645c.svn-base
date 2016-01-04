package bluemobi.iuv.network.request;

import com.android.volley.Response;

import java.util.HashMap;
import java.util.Map;

import bluemobi.iuv.network.IuwHttpJsonRequest;
import bluemobi.iuv.network.response.SearchCountyResponse;

/**
 * Created by liufy on 2015/9/29.
 * P8 获取国家列表
 */
public class SearchCountyRequest extends IuwHttpJsonRequest<SearchCountyResponse> {

    private static final String APIPATH = "mobi/divisioninfo/findAllCounties";

    public SearchCountyRequest(Response.Listener<SearchCountyResponse> listener, Response.ErrorListener errorListener) {
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
    public Class<SearchCountyResponse> getResponseClass() {
        return SearchCountyResponse.class;
    }


}
