package bluemobi.iuv.network.request;

import com.android.volley.Response;

import java.util.HashMap;
import java.util.Map;

import bluemobi.iuv.network.IuwHttpJsonRequest;
import bluemobi.iuv.network.response.GetCitiesByCondResponse;

/**Coun
 * P8-1 根据国家查询省列表
 */
public class GetCitiesByCondRequest extends IuwHttpJsonRequest<GetCitiesByCondResponse> {

    private static final String APIPATH = "mobi/divisioninfo/findCitiesByPage";


    private String  pid	;//string	是	所选择国家的ID

    public GetCitiesByCondRequest(Response.Listener<GetCitiesByCondResponse> listener, Response.ErrorListener errorListener) {
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
