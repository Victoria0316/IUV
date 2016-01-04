package bluemobi.iuv.eventbus;

import java.util.ArrayList;

import bluemobi.iuv.network.response.SearchCountyResponse;

/**
 * Created by liufy on 2015/9/29.
 */
public class CountyInfoEvent extends BaseEvent {

    private  ArrayList<SearchCountyResponse.SearchCountyData> data ;

    public ArrayList<SearchCountyResponse.SearchCountyData> getData() {
        return data;
    }

    public void setData(ArrayList<SearchCountyResponse.SearchCountyData> data) {
        this.data = data;
    }
}
