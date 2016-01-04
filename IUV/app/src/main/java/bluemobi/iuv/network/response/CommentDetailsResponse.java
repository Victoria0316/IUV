package bluemobi.iuv.network.response;

import java.util.ArrayList;

import bluemobi.iuv.network.IuwHttpResponse;

/**
 * Created by liufy on 2015/10/16.
 */
public class CommentDetailsResponse extends IuwHttpResponse {

    public  CommentDetailsData data;

    public static class  CommentDetailsData
    {

        public String getCurrentpage() {
            int i = Integer.parseInt(currentpage)-1;
            return String.valueOf(i);
        }

        private String currentpage;
        public String pageTime;
        public String totalnum;
        public String totalpage;
        public ArrayList<CommentDetailsInfo> info = new ArrayList<CommentDetailsInfo>();

    }


    public static class CommentDetailsInfo
    {
        public String id;//主键
        public String userId;//评价用户ID
        public String userName;//评价用户名称
        public String userIp;//评价用户IP
        public String userNickName;//评价用户昵称
        public String productId;//被评价对象ID
        public String productName;//被评价对象名称
        public String createTime;//评价时间
        public ArrayList<ImgListBean> imgList = new ArrayList<ImgListBean>();//图片列表
        public String content ;//评论
    }


    public static class ImgListBean
    {

        public String imgPath;
    }



  

}
