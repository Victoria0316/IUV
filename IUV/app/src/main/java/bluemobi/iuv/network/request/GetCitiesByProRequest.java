package bluemobi.iuv.network.request;

import com.android.volley.Response;

import java.util.HashMap;
import java.util.Map;

import bluemobi.iuv.network.IuwHttpJsonRequest;
import bluemobi.iuv.network.response.GetCitiesByCondResponse;

/**Coun
 * P8-1-2 根据省查询城市列表
 */
public class GetCitiesByProRequest extends IuwHttpJsonRequest<GetCitiesByCondResponse> {

    private static final String APIPATH = "mobi/divisioninfo/findDistrictsByPage";


    private String  pid	;//string城市的ID

    public GetCitiesByProRequest(Response.Listener<GetCitiesByCondResponse> listener, Response.ErrorListener errorListener) {
        super(Method.POST, APIPATH, listener, errorListener);
    }

    @Override
    public String GetApiPath() {
        return APIPATH;
    }

    @Override
    public Map<String, String> GetParameters() {

        Map<String, String> map = new HashMap<String, String>();
        map.put("pid",pid);
        map.put("currentnum","1000");
        map.put("currentpage","0");
        return map;
    }

    @Override
    public Class<GetCitiesByCondResponse> getResponseClass() {
        return GetCitiesByCondResponse.class;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }
}
