package bluemobi.iuv.network.request;

import com.android.volley.Response;

import java.util.HashMap;
import java.util.Map;

import bluemobi.iuv.network.IuwHttpJsonRequest;
import bluemobi.iuv.network.response.AddCommentyResponse;
import bluemobi.iuv.network.response.LoginResponse;


/**
 * P8-2-2-1-1 添加评价
 *
 */
public class AddCommentyRequest extends
        IuwHttpJsonRequest<AddCommentyResponse>
{

    private static final String APIPATH = "mobi/commentinfo/addComment";



    private String userId	;//string	是	评价用户
    private String productId	;//string	是	被评价对象ID
    private String sourceType	;//int	是	评价类型(1 商品 2 商场 3 宝藏)
    private String content;//	string	是	评价内容
    private String app	;//string	是	调用app[android,ios]
    private String appKey	;//string	是	app唯一标示[hwg]
    private String picArray;//	string	是	base64图片字节流
    private String picType	;//string	是	图片类型[jpg,jpeg,png]

    public AddCommentyRequest(Response.Listener<AddCommentyResponse> listener,
                              Response.ErrorListener errorListener)
    {
        super(Method.POST, APIPATH, listener, errorListener);
    }

    public AddCommentyRequest(int method, String partUrl,
                              Response.Listener<AddCommentyResponse> listener,
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
        map.put("content",content);
        map.put("productId",productId);
        map.put("sourceType",sourceType);
        map.put("app","android");
        map.put("appKey","hwg");
        map.put("picArray",picArray);
        map.put("picType",picType);

        return map;
    }

    @Override
    public Class<AddCommentyResponse> getResponseClass()
    {
        return AddCommentyResponse.class;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getPicArray() {
        return picArray;
    }

    public void setPicArray(String picArray) {
        this.picArray = picArray;
    }

    public String getPicType() {
        return picType;
    }

    public void setPicType(String picType) {
        this.picType = picType;
    }


}
