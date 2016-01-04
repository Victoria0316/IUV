package bluemobi.iuv.network.request;

import com.android.volley.Response;

import java.util.HashMap;
import java.util.Map;

import bluemobi.iuv.network.IuwHttpJsonRequest;
import bluemobi.iuv.network.response.TopSortResponse;


/**
 * 根据父分类获取分类
 *
 */
public class SpecStoreCategoryRequest extends
        IuwHttpJsonRequest<TopSortResponse>
{

    private static final String APIPATH = "mobi/shopstypeinfo/findTypesByPid";

    private String pid;


    public SpecStoreCategoryRequest(Response.Listener<TopSortResponse> listener,
                                    Response.ErrorListener errorListener)
    {
        super(Method.POST, APIPATH, listener, errorListener);
    }

    public SpecStoreCategoryRequest(int method, String partUrl,
                                    Response.Listener<TopSortResponse> listener,
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
        map.put("pid", pid);

        return map;
    }

    @Override
    public Class<TopSortResponse> getResponseClass()
    {
        return TopSortResponse.class;
    }


    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }
}
