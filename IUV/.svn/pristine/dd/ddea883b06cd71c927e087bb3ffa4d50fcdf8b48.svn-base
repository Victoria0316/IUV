package bluemobi.iuv.network.request;

import com.android.volley.Response;

import java.util.HashMap;
import java.util.Map;

import bluemobi.iuv.network.IuwHttpJsonRequest;
import bluemobi.iuv.network.response.UploadPicResponse;

/**
 * 上传头像
 */
public class UploadPicRequest extends IuwHttpJsonRequest<UploadPicResponse> {

    private static final String APIPATH = "mobi/personalcenter/updateHeadPicUrl";

    private String userId;
    private String picArray;
    private String picType;
    private String app;

    public UploadPicRequest(Response.Listener<UploadPicResponse> listener, Response.ErrorListener errorListener) {
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
        map.put("picArray",picArray);
        map.put("picType","jpg");
        map.put("app","android");
        return map;
    }

    @Override
    public Class<UploadPicResponse> getResponseClass() {
        return UploadPicResponse.class;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPicArray() {
        return picArray;
    }

    public void setPicArray(String picArray) {
        this.picArray = picArray;
    }
}
