package bluemobi.iuv.network.request;

import com.android.volley.Response;

import java.util.HashMap;
import java.util.Map;

import bluemobi.iuv.network.IuwHttpJsonRequest;
import bluemobi.iuv.network.response.QueryThreeHotGoodsResponse;
import bluemobi.iuv.network.response.QueryThreeHotGoodsResponse;


/**
 * p8-3-1 热门商品
 *
 */
public class QueryThreeHotGoodsRequest extends
        IuwHttpJsonRequest<QueryThreeHotGoodsResponse>
{

    private static final String APIPATH = "mobi/productinfo/findProducts";

    private String userId;
    private String shopsId;
    private String sortType;//排序方式 collection: 收藏最多 praise:点赞最多 comment:评论最多 minPrice:价格最低 maxPrice:价格最高
    private String currentnum;
    private String currentpage;

    public QueryThreeHotGoodsRequest(Response.Listener<QueryThreeHotGoodsResponse> listener,
                                     Response.ErrorListener errorListener)
    {
        super(Method.POST, APIPATH, listener, errorListener);
    }

    public QueryThreeHotGoodsRequest(int method, String partUrl,
                                     Response.Listener<QueryThreeHotGoodsResponse> listener,
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
        map.put("shopsId", shopsId);
        map.put("userId",userId);
        map.put("sortType",sortType);
        map.put("currentnum",currentnum);
        map.put("currentpage",currentpage);
        return map;
    }

    @Override
    public Class<QueryThreeHotGoodsResponse> getResponseClass()
    {
        return QueryThreeHotGoodsResponse.class;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getShopsId() {
        return shopsId;
    }

    public void setShopsId(String shopsId) {
        this.shopsId = shopsId;
    }

    public String getSortType() {
        return sortType;
    }

    public void setSortType(String sortType) {
        this.sortType = sortType;
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
