package bluemobi.iuv.network.model;

/**
 * Created by gaoxy on 2015/9/28.
 * 商店实体
 */
public class StoreInfo {

    private String id;//
    private String shopsTypeId;//店铺类型ID
    private String shopsTypeName;//店铺类型名称
    private String shopNo;//店铺编号
    private String shopName       ;//店铺名称
    private String collectionNum;//被收藏数
    private String praiseNum;//被点赞数
    private String commentNum;//评论数
    private String shopImgPath;//商场图片路径
    private String distance;//距离
    private String shopsLongitude;//店铺经度
    private String shopsLatitude;//店铺纬度

    private String shopAddress;
    private String shopCellphone;
    private String collectionId;

    private String praiseId;


    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    public String getShopCellphone() {
        return shopCellphone;
    }

    public void setShopCellphone(String shopCellphone) {
        this.shopCellphone = shopCellphone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShopsTypeId() {
        return shopsTypeId;
    }

    public void setShopsTypeId(String shopsTypeId) {
        this.shopsTypeId = shopsTypeId;
    }

    public String getShopsTypeName() {
        return shopsTypeName;
    }

    public void setShopsTypeName(String shopsTypeName) {
        this.shopsTypeName = shopsTypeName;
    }

    public String getShopNo() {
        return shopNo;
    }

    public void setShopNo(String shopNo) {
        this.shopNo = shopNo;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getCollectionNum() {
        return collectionNum;
    }

    public void setCollectionNum(String collectionNum) {
        this.collectionNum = collectionNum;
    }

    public String getPraiseNum() {
        return praiseNum;
    }

    public void setPraiseNum(String praiseNum) {
        this.praiseNum = praiseNum;
    }

    public String getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(String commentNum) {
        this.commentNum = commentNum;
    }

    public String getShopImgPath() {
        return shopImgPath;
    }

    public void setShopImgPath(String shopImgPath) {
        this.shopImgPath = shopImgPath;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getShopsLongitude() {
        return shopsLongitude;
    }

    public void setShopsLongitude(String shopsLongitude) {
        this.shopsLongitude = shopsLongitude;
    }

    public String getShopsLatitude() {
        return shopsLatitude;
    }

    public void setShopsLatitude(String shopsLatitude) {
        this.shopsLatitude = shopsLatitude;
    }


    public String getPraiseId() {
        return praiseId;
    }

    public void setPraiseId(String praiseId) {
        this.praiseId = praiseId;
    }

    public String getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(String collectionId) {
        this.collectionId = collectionId;
    }
}
