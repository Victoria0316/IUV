package bluemobi.iuv.network.request;

import com.android.volley.Response;

import java.util.HashMap;
import java.util.Map;

import bluemobi.iuv.network.IuwHttpJsonRequest;
import bluemobi.iuv.network.IuwHttpResponse;
import bluemobi.iuv.network.response.BannerImageResponse;

/**
 * Created by liufy on 2015/9/23.
 * P7 获取轮播图
 */
public class BannerImageRequest extends IuwHttpJsonRequest<BannerImageResponse> {

    private static final String APIPATH = "mobi/articleinfo/findBanners";


    public BannerImageRequest(Response.Listener<BannerImageResponse> listener, Response.ErrorListener errorListener) {
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
    public Class<BannerImageResponse> getResponseClass() {
        return BannerImageResponse.class;
    }
}
