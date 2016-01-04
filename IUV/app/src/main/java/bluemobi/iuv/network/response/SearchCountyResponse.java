package bluemobi.iuv.network.response;

import java.util.ArrayList;
import java.util.List;

import bluemobi.iuv.network.IuwHttpResponse;

/**
 * Created by liufy on 2015/9/29.
 */
public class SearchCountyResponse extends IuwHttpResponse {

    public ArrayList<SearchCountyData> data = new ArrayList<SearchCountyData>();

    public static class SearchCountyData
    {
        public String id;//主键
        public String divisionCode;//行政区划编码
        public String divisionName;//行政区划名称全称
        public String divisionNameJp;//名称简称
        public String divisionPy;//名称拼音
        public String divisionJp;//名称字母
        public String divisionType;//区划类型：0国家1省份2地市3区县
        public String recommendStatus;//首页推荐（0未推荐，1推荐）
        public String shopsLongitude;//经度
        public String shopsLatitude;//纬度
        public String pid;//父ID
        public String areaName;//区域名称"

    }




}
