package bluemobi.iuv.network.request;

import com.android.volley.Response;

import java.util.HashMap;
import java.util.Map;

import bluemobi.iuv.network.IuwHttpJsonRequest;
import bluemobi.iuv.network.response.HotShopTypeResponse;
import bluemobi.iuv.network.response.LoginResponse;


/**
 * 获取除热门商场外所有一级分类--获取除热门商场外所有一级分类
 *
 */
public class LargerDeptTypeListRequest extends
        IuwHttpJsonRequest<HotShopTypeResponse> {

    private static final String APIPATH = "mobi/shopstypeinfo/findAll1stType";


    public LargerDeptTypeListRequest(Response.Listener<HotShopTypeResponse> listener,
                                     Response.ErrorListener errorListener) {
        super(Method.POST, APIPATH, listener, errorListener);
    }

    public LargerDeptTypeListRequest(int method, String partUrl,
                                     Response.Listener<HotShopTypeResponse> listener,
                                     Response.ErrorListener errorListener) {
        super(method, partUrl, listener, errorListener);
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
    public Class<HotShopTypeResponse> getResponseClass() {
        return HotShopTypeResponse.class;
    }

}
