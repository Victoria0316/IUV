package bluemobi.iuv.network.response;

import java.util.ArrayList;

import bluemobi.iuv.network.IuwHttpResponse;

/**
 * Created by liufy on 2015/9/23.
 */
public class BrandDetailResponse extends IuwHttpResponse {

    public BrandDetailData data;

    public static class BrandDetailData {
        public String id;//主键
        public String shopsId;//商场ID
        public String shopsNo;//商场编号
        public String shopsName;//商场名称
        public String productNo;//商品编号
        public String productName;//商品名称
        public String description;//商品描述
        public String praiseNum;//点赞数
        public String collectionNum;//收藏数
        public String commentNum;//评价数
        public String customerPrice;//价格
        public String praiseId;//点赞ID,未点赞为空;
        public String collectionId;//收藏ID,未收藏为空;

        public ArrayList<ImageListDTO> imgList = new ArrayList<ImageListDTO>();

    }

    public static class ImageListDTO {

        public String actTag;// 1,
        public String articlId;// 8aba20f0506564860150658b5ee90004,
        public String id;// 8aba20f0506564860150658b61970006,
        public String imgBelongs;// 4,
        public String imgPath;// http;////101.200.193.187;//8888/Fload/hwg/2015-10-14/E7EE650EE6BC47F5AE41323950A06A75.jpg?id=E7EE650EE6BC47F5AE41323950A06A75&fn=2015-10-14&fs=jpg&appkey=hwg,
        public String imgType;// p,
        public String optTime;// 2015-10-14T16;//50;//45+08;//00,
        public String status;// 0

    }

}
