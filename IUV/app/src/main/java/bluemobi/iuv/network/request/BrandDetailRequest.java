package bluemobi.iuv.network.request;

import com.android.volley.Response;

import java.util.HashMap;
import java.util.Map;

import bluemobi.iuv.network.IuwHttpJsonRequest;
import bluemobi.iuv.network.response.BannerImageResponse;
import bluemobi.iuv.network.response.BrandDetailResponse;

/**
 * Created by liufy on 2015/9/23.
 * P8-2-2 获取商品详情
 */
public class BrandDetailRequest extends IuwHttpJsonRequest<BrandDetailResponse> {

    private static final String APIPATH = "mobi/productinfo/findProductById";

    private String id	;//string	是	商品ID
    private String userId ;//	string	是	用户ID


    public BrandDetailRequest(Response.Listener<BrandDetailResponse> listener, Response.ErrorListener errorListener) {
        super(Method.POST, APIPATH, listener, errorListener);
    }

    @Override
    public String GetApiPath() {
        return APIPATH;
    }

    @Override
    public Map<String, String> GetParameters() {

        Map<String, String> map = new HashMap<String, String>();
        map.put("id",id);
        map.put("userId",userId);
        return map;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public Class<BrandDetailResponse> getResponseClass() {
        return BrandDetailResponse.class;
    }
}
