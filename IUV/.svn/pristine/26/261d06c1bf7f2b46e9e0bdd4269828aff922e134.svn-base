package bluemobi.iuv.network.request;

import com.android.volley.Response;

import java.util.HashMap;
import java.util.Map;

import bluemobi.iuv.network.IuwHttpJsonRequest;
import bluemobi.iuv.network.response.HotShopListResponse;


/**
 * 分页获取热门商场列表--P8-3 分页获取热门商场列表
 *
 */
public class HotShopListRequest extends
        IuwHttpJsonRequest<HotShopListResponse>
{
    private String shopCityCode;//商场所在城市编码（城市编码与区县编码二者必须选填其一）
    private String shopDistrictCode;//商场所在区县编码（城市编码与区县编码二者必须选填其一
    private String shopsTypeId;//商场种类ID
    private String longitude;//当前经度
    private String latitude;//当前纬度
    private String sortType;//排序方式 collectionNum: 收藏最多 praiseNum:点赞最多 commentNum:评论最多 distance:离我最近
    private String currentnum;//
    private String currentpage;//
    private String userId;

    private static final String APIPATH = "mobi/shopsinfo/findLargeShopsByPage";


    public HotShopListRequest(Response.Listener<HotShopListResponse> listener,
                              Response.ErrorListener errorListener)
    {
        super(Method.POST, APIPATH, listener, errorListener);
    }

    public HotShopListRequest(int method, String partUrl,
                              Response.Listener<HotShopListResponse> listener,
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
        map.put("shopCityCode", shopCityCode);
        map.put("shopDistrictCode", shopDistrictCode);
        map.put("shopsTypeId", shopsTypeId);
        map.put("longitude", longitude);
        map.put("latitude", latitude);
        map.put("sortType", sortType);
        map.put("currentnum", currentnum);
        map.put("currentpage", currentpage);
        map.put("userId", userId);


        return map;
    }

    @Override
    public Class<HotShopListResponse> getResponseClass()
    {
        return HotShopListResponse.class;
    }

    public String getShopCityCode() {
        return shopCityCode;
    }

    public void setShopCityCode(String shopCityCode) {
        this.shopCityCode = shopCityCode;
    }



    public String getShopsTypeId() {
        return shopsTypeId;
    }

    public void setShopsTypeId(String shopsTypeId) {
        this.shopsTypeId = shopsTypeId;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getShopDistrictCode() {
        return shopDistrictCode;
    }

    public void setShopDistrictCode(String shopDistrictCode) {
        this.shopDistrictCode = shopDistrictCode;
    }

}
