package bluemobi.iuv.eventbus;

/**
 * Created by liufy on 2015/10/16.
 * 被评价详情
 * */
public class CommentDetailEvent extends BaseEvent {

    /**
     * 被评价商品ID
     */
    private String productID;
    /**
     * 评价类型(1 商品 2 商场 3 宝藏)
     */
    private String sourceType;

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }
}
