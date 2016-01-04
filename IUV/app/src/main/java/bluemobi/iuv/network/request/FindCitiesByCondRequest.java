package bluemobi.iuv.network.request;

import com.android.volley.Response;

import java.util.HashMap;
import java.util.Map;

import bluemobi.iuv.network.IuwHttpJsonRequest;
import bluemobi.iuv.network.response.BannerImageResponse;
import bluemobi.iuv.network.response.SearchCountyResponse;

/**Coun
 * Created by liufy on 2015/9/23.
 * P8 根据名称查询城市列表
 */
public class FindCitiesByCondRequest extends IuwHttpJsonRequest<SearchCountyResponse> {

    private static final String APIPATH = "mobi/divisioninfo/findCitiesByCond";

    private String  DIVISION_NAME	;//string	是	城市名称

    private String  id	;//string	是	所选择国家的ID

    public FindCitiesByCondRequest(Response.Listener<SearchCountyResponse> listener, Response.ErrorListener errorListener) {
        super(Method.POST, APIPATH, listener, errorListener);
    }

    @Override
    public String GetApiPath() {
        return APIPATH;
    }

    @Override
    public Map<String, String> GetParameters() {

        Map<String, String> map = new HashMap<String, String>();
        map.put("DIVISION_NAME",DIVISION_NAME);
        map.put("id",id);
        return map;
    }

    @Override
    public Class<SearchCountyResponse> getResponseClass() {
        return SearchCountyResponse.class;
    }

    public String getDIVISION_NAME() {
        return DIVISION_NAME;
    }

    public void setDIVISION_NAME(String DIVISION_NAME) {
        this.DIVISION_NAME = DIVISION_NAME;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
