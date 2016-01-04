package bluemobi.iuv.network.response;

import java.util.ArrayList;

import bluemobi.iuv.fragment.page.LargeDepartmentStoresPage;
import bluemobi.iuv.network.IuwHttpResponse;

/**
 * Created by liufy on 2015/10/8.
 */
public class LargeDepartmentStoresResponse extends IuwHttpResponse {


    public LargeDepartmentStoresData data;

    public static  class LargeDepartmentStoresData
    {
        public String getCurrentpage() {
            int i = Integer.parseInt(currentpage)-1;
            return String.valueOf(i);
        }

        public String currentpage;
        public String pageTime;
        public String totalnum;
        public String totalpage;
        public ArrayList<LargeDepartmentStoresDto> info = new ArrayList<LargeDepartmentStoresDto>();
    }

    public static class LargeDepartmentStoresDto

    {
        public String id;//商场主键
        public String shopsTypeId;//店铺类型ID
        public String shopsTypeName;//店铺类型名称
        public String shopNo;//店铺编号
        public String shopName;//店铺名称
        public String collectionNum;// 被收藏数
        public String praiseNum;//被点赞数
        public String commentNum;//评论数
        public String shopImgPath;//商场图片路径
        public String distance;//距离

        public String  preciousId;//宝藏主键
        public String  preciousCode;//宝藏编号
        public String preciousName;//宝藏名称
        public String preciousType;//宝藏类型
        public String preciousDescription;//宝藏描述
        public String preciousImgPath;//宝藏图片路径
        public String shopsLatitude;// 2.0,
        public String shopsLongitude;// 1.0,

        public String praiseId;//点赞ID,未点赞为空;
        public String collectionId;//收藏ID,未收藏为空
    }


    
    
}
