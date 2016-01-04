package bluemobi.iuv.network.request;

import com.android.volley.Response;

import java.util.HashMap;
import java.util.Map;

import bluemobi.iuv.network.IuwHttpJsonRequest;
import bluemobi.iuv.network.response.LargeDepartmentStoresResponse;
import bluemobi.iuv.network.response.LoginResponse;


/**
 * P8_2商场列表-大型百货-修改
 * 接口名称：	P8-2 分页获取大型百货列表
 * 接口用途：	分页获取大型百货列表
 */
public class LargeDepartmentStoresRequest extends
        IuwHttpJsonRequest<LargeDepartmentStoresResponse>
{

    private static final String APIPATH = "mobi/shopsinfo/findLargeShopsByPage";

    private String shopCityCode	;//	否	商场所在城市编码（城市编码与区县编码二者必须选填其一）
    private String shopDistrictCode	;//	否	商场所在区县编码（城市编码与区县编码二者必须选填其一）
    private String longitude	;//float	是	当前经度
    private String latitude	;//float	是	当前纬度
    private String sortType	;//	否	排序方式 collectionNum: 收藏最多 praiseNum:点赞最多 commentNum:评论最多 distance:离我最近
    private String currentnum	;//	是	每页条数
    private String currentpage	;//	是	当前页数
    private String shopsTypeId; //商场种类ID

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    private String  userId;

    public static String getAPIPATH() {
        return APIPATH;
    }

    public LargeDepartmentStoresRequest(Response.Listener<LargeDepartmentStoresResponse> listener,
                                        Response.ErrorListener errorListener)
    {
        super(Method.POST, APIPATH, listener, errorListener);
    }

    public LargeDepartmentStoresRequest(int method, String partUrl,
                                        Response.Listener<LargeDepartmentStoresResponse> listener,
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
        map.put("shopCityCode", shopCityCode);
        map.put("shopDistrictCode",shopDistrictCode);
        map.put("longitude",longitude);
        map.put("latitude",latitude);
        map.put("sortType",sortType);
        map.put("currentnum",currentnum);
        map.put("currentpage",currentpage);
        map.put("shopsTypeId",shopsTypeId);
        return map;
    }

    @Override
    public Class<LargeDepartmentStoresResponse> getResponseClass()
    {
        return LargeDepartmentStoresResponse.class;
    }


    public String getShopCityCode() {
        return shopCityCode;
    }

    public void setShopCityCode(String shopCityCode) {
        this.shopCityCode = shopCityCode;
    }

    public String getShopDistrictCode() {
        return shopDistrictCode;
    }

    public void setShopDistrictCode(String shopDistrictCode) {
        this.shopDistrictCode = shopDistrictCode;
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

    public String getCurrentnum() {
        return currentnum;
    }

    public void setCurrentnum(String currentnum) {
        this.currentnum = currentnum;
    }

    public String getSortType() {
        return sortType;
    }

    public void setSortType(String sortType) {
        this.sortType = sortType;
    }

    public String getCurrentpage() {
        return currentpage;
    }

    public void setCurrentpage(String currentpage) {
        this.currentpage = currentpage;
    }

    public String getShopsTypeId() {
        return shopsTypeId;
    }

    public void setShopsTypeId(String shopsTypeId) {
        this.shopsTypeId = shopsTypeId;
    }
}
