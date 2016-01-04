package bluemobi.iuv.network.response;

import java.util.ArrayList;

import bluemobi.iuv.network.IuwHttpResponse;

/**
 * Created by liufy on 2015/11/2.
 */
public class TopSortResponse extends IuwHttpResponse {


    public ArrayList<TopSortData> data = new ArrayList<TopSortData>();

    public static class TopSortData {
        public String id;//主键
        public String shopsTypeCode;//类型编码
        public String shopsTypeName;//类型名称
    }
}
