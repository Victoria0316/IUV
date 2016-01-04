package bluemobi.iuv.network.request;

import com.android.volley.Response;

import java.util.HashMap;
import java.util.Map;

import bluemobi.iuv.network.IuwHttpJsonRequest;
import bluemobi.iuv.network.response.CommentDetailsResponse;
import bluemobi.iuv.network.response.LoginResponse;


/**
 * P8-2-2-1 分页获取评价信息
 *
 */
public class CommentDetailsRequest extends
        IuwHttpJsonRequest<CommentDetailsResponse>
{

    private static final String APIPATH = "mobi/commentinfo/findCommentsByPage";

    private String productId	;//string	是	被评价对象ID
    private String currentnum	;//string	是	每页条数
    private String currentpage	;//string	是	当前页数

    public CommentDetailsRequest(Response.Listener<CommentDetailsResponse> listener,
                                 Response.ErrorListener errorListener)
    {
        super(Method.POST, APIPATH, listener, errorListener);
    }

    public CommentDetailsRequest(int method, String partUrl,
                                 Response.Listener<CommentDetailsResponse> listener,
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
        map.put("productId", productId);
        map.put("currentnum",currentnum);
        map.put("currentpage",currentpage);
        return map;
    }

    @Override
    public Class<CommentDetailsResponse> getResponseClass()
    {
        return CommentDetailsResponse.class;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getCurrentnum() {
        return currentnum;
    }

    public void setCurrentnum(String currentnum) {
        this.currentnum = currentnum;
    }

    public String getCurrentpage() {
        return currentpage;
    }

    public void setCurrentpage(String currentpage) {
        this.currentpage = currentpage;
    }

}
