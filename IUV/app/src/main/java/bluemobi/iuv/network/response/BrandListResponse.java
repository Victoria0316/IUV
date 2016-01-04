package bluemobi.iuv.network.response;

import java.util.ArrayList;

import bluemobi.iuv.network.IuwHttpResponse;

/**
 * Created by liufy on 2015/10/13.
 */
public class BrandListResponse extends IuwHttpResponse
{
    public BrandListData data;

    public static class  BrandListData
    {
        public ArrayList<BrandListDto> info = new ArrayList<BrandListDto>();
    }


    public static class BrandListDto
    {
        public String id;//品牌主键
        public String shopsId;//所属商场
        public String shopsNo;//商场编号
        public String shopsName;//商场名称
        public String attributeValueCode;//品牌编号
        public String attributeValue;//品牌名称
        public String shopsImgPath;//品牌图片路径
        public ArrayList<ProductList> productList = new ArrayList<ProductList>();


    }

    /**
     * 商品列表
     */
    public static class  ProductList
    {
        public String id;//商品ID;
        public String  prodectNo;//商品编码;
        public String productName;//商品名称;
        public String customerPrice;//价格;
        public String productImgPath;//商品图片;
    }

}
