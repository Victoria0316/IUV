package bluemobi.iuv.network;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonRequest;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import bluemobi.iuv.app.IuwApplication;
import bluemobi.iuv.base.utils.Logger;
import bluemobi.iuv.network.exception.CustomResponseError;
import bluemobi.iuv.network.exception.TokenInvalid;
import bluemobi.iuv.network.model.UserInfo;
import bluemobi.iuv.network.util.DefaultTranslateErrorToCn;
import bluemobi.iuv.network.util.TranslateErrorToCn;
import bluemobi.iuv.util.Constants;
import bluemobi.iuv.util.StringUtils;


public abstract class IuwHttpJsonRequest<T extends IuwHttpResponse> extends
        JsonRequest<T> implements IuwHttpBase<T>, IuwHttpError {

    private static final String PROTOCOL_CHARSET = "utf-8";

    private final Gson mGson;

    private final Response.Listener<T> mListener;

    private String mRequestBody;

    private static final int TOKENINVALID = 5;

    private static final int RESPONSE_SUCCESS = 0;

    private boolean showToast = true;

    private boolean handleCustomErr = true;

    protected RetryPolicy retryPolicy;

    private TranslateErrorToCn translateErrorToCn;

    private NetWorkResponseListener netWorkResponseListener;


    public IuwHttpJsonRequest(String partUrl, Response.Listener<T> listener,
                               Response.ErrorListener errorListener) {
        this(Method.POST, Constants.SERVER_URL + partUrl, null, listener,
                errorListener, false);
    }

    public IuwHttpJsonRequest(String partUrl, Response.Listener<T> listener,
                               Response.ErrorListener errorListener, boolean noRetryPolicy) {
        this(Method.POST, Constants.SERVER_URL + partUrl, null, listener,
                errorListener, noRetryPolicy);
    }

    public IuwHttpJsonRequest(int method, String partUrl,
                               Response.Listener<T> listener, Response.ErrorListener errorListener) {
        this(method, Constants.SERVER_URL + partUrl, null, listener,
                errorListener, false);
    }

    public IuwHttpJsonRequest(int method, String partUrl,
                               Response.Listener<T> listener, Response.ErrorListener errorListener, boolean noRetryPolicy) {
        this(method, Constants.SERVER_URL + partUrl, null, listener,
                errorListener, noRetryPolicy);
    }
    /**
     *
     * @param method
     * @param url
     * @param requestBody
     * @param listener
     * @param errorListener
     * @param noRetryPolicy
     */
    public IuwHttpJsonRequest(int method, String url, String requestBody,
                               Response.Listener<T> listener, Response.ErrorListener errorListener, boolean noRetryPolicy) {
        super(method, url , null, listener,
                errorListener);
        if(noRetryPolicy){
            retryPolicy = new DefaultRetryPolicy(mCurrentTimeoutMs, 0, mMaxNumRetries);
        }else{
            retryPolicy = new DefaultRetryPolicy(mCurrentTimeoutMs, 1, mMaxNumRetries);
        }
        setRetryPolicy(retryPolicy);
        translateErrorToCn = new DefaultTranslateErrorToCn();
        this.mListener = listener;
        mGson = new Gson();
    }

    public String requestUrl() {
        return Constants.SERVER_URL + GetApiPath();
    }

    abstract public String GetApiPath();


    abstract public Map<String, String> GetParameters();


    abstract public Class<T> getResponseClass();

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return GetParameters();
    }


    @Override
    protected void deliverResponse(T response) {
        mListener.onResponse(response);
    }


    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {

            Logger.d("IuwHttpJsonRequest", requestUrl() + "?"
                    + new String(getBody(), "utf-8"));
            String json = new String(response.data,
                    HttpHeaderParser.parseCharset(response.headers));
            Logger.dl("IuwHttpJsonRequest", StringUtils.unicodeToChinese(json));
            return doAnalyse(json, response);
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            return Response.error(new ParseError(e));
        }
    }

    private Response<T> doAnalyse(String json, NetworkResponse response) {
        JSONObject obj = null;
        try {
            obj = new JSONObject(json);

            if(obj.has("status") && netWorkResponseListener != null){
                netWorkResponseListener.processResponseStatus(obj.getString("status"));
            }
            if (obj.has("status") && TOKENINVALID == obj.getInt("status")) {
                return Response.error(new TokenInvalid());
            } else if (handleCustomErr && obj.has("status")
                    && RESPONSE_SUCCESS != obj.getInt("status")) {
                return Response.error(new CustomResponseError(
                        obj.has("msg") ? obj.getString("msg") : "服务器错误", showToast,obj.getInt("status")));
            }




           /* if (obj.has("status") && TOKENINVALID == obj.getInt("status")) {// �û���֤ʧЧ
                return Response.error(new TokenInvalid());
            } else if (handleCustomErr && obj.has("status")
                    && RESPONSE_SUCCESS != obj.getInt("status")) {
                return Response.error(new CustomResponseError(
                        obj.has("msg") ? obj.getString("msg") : "服务器繁忙", showToast,obj.getInt("status")));
            }*/
        } catch (JSONException e) {
            e.printStackTrace();
            return Response.error(new ParseError(e));
        }
        return Response.success(mGson.fromJson(json, getResponseClass()),
                HttpHeaderParser.parseCacheHeaders(response));
    }

    protected Map<String, Object> getExtParams(){
        return null;
    }

    @Override
    public byte[] getBody() {
        try {
            Map<String, String> map = GetParameters();
            IuwApplication application = IuwApplication.getInstance();
            UserInfo userInfo = application.getMyUserInfo();
            if (userInfo!=null) {
                map.put("ssid", userInfo.getSsid());
                map.put("checkUserId", userInfo.getUserId());
            } else {
                map.put("pass", "n");
            }
            map.put("pageTime",String.valueOf(IuwApplication.getInstance().getPageTime()));
            JSONObject obj = new JSONObject(map);
            com.google.gson.JsonObject gsonObj = new com.google.gson.JsonObject();
            Map<String, Object> extMap = getExtParams();
            if(extMap == null){
                extMap = new HashMap<String, Object>();
            }
            for (String key : map.keySet()) {
                System.out.println("key= "+ key + " and value= " + map.get(key));
                extMap.put(key, map.get(key));
            }
            Gson gson = new Gson();
            mRequestBody = gson.toJson(extMap);
            return mRequestBody == null ? null : mRequestBody
                    .getBytes(PROTOCOL_CHARSET);
        } catch (UnsupportedEncodingException uee) {
            VolleyLog
                    .wtf("Unsupported Encoding while trying to get the bytes of %s using %s",
                            mRequestBody, PROTOCOL_CHARSET);
            return null;
        }
    }

    @Override
    public String translateErrorToCn(int errorCode) {
        if(translateErrorToCn != null){
            translateErrorToCn = new DefaultTranslateErrorToCn();
        }
        return translateErrorToCn.translateErrorToCn(errorCode);
    }

    public boolean isShowToast() {
        return showToast;
    }

    public void setShowToast(boolean showToast) {
        this.showToast = showToast;
    }

    public boolean isHandleCustomErr() {
        return handleCustomErr;
    }

    public void setHandleCustomErr(boolean handleCustomErr) {
        this.handleCustomErr = handleCustomErr;
    }

    public TranslateErrorToCn getTranslateErrorToCn() {
        return translateErrorToCn;
    }
    public void setTranslateErrorToCn(TranslateErrorToCn translateErrorToCn) {
        this.translateErrorToCn = translateErrorToCn;
    }

    public void setNetWorkResponseListener(NetWorkResponseListener netWorkResponseListener) {
        this.netWorkResponseListener = netWorkResponseListener;
    }


}