package bluemobi.iuv.network.request;

import com.android.volley.Response;

import java.util.HashMap;
import java.util.Map;

import bluemobi.iuv.network.IuwHttpJsonRequest;
import bluemobi.iuv.network.response.HomeTreasureListResponse;

/**
 * Created by liufy on 2015/9/23.
 * 获取首页宝藏的图片及其他信息--P7 获取首页宝藏
 */
public class HomeTreasureListRequest extends IuwHttpJsonRequest<HomeTreasureListResponse> {

    private static final String APIPATH = "mobi/preciousinfo/findTopPrecious";

    public HomeTreasureListRequest(Response.Listener<HomeTreasureListResponse> listener, Response.ErrorListener errorListener) {
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
    public Class<HomeTreasureListResponse> getResponseClass() {
        return HomeTreasureListResponse.class;
    }
}
