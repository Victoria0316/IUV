package bluemobi.iuv.callback;

/**
 * Created by liufy on 2015/6/28.
 * bar 上各种按钮的监听
 */
public interface TitleBarCallBack
{
    /**
     * 点击右边的bar 按钮
     */
    public void clickBarRight();

    /**
     * 点击左边的bar 按钮
     */
    public void clickBarleft();

    /**
     * 点击搜索bar 按钮
     */
    public void clickBarSearch();

    /**
     * 点击提示信息数量 bar 按钮
     */
    public void clickBarHint();

    /**
     * 点击右边图片按钮
     */
    public void clickImageRight();

    public void clickImageLeft();

    /**
     * 右侧带文字
     */
    public void clickTextRight();

    /**
     * 右侧左边
     */
    public void clickTextRightLeft();

    /**
     * 右边搜索按钮
     */
    public void clickBarRightSearch();

    public void clickRightMapSearch();

    public void clickDetailRightMap();

    public void clickSearchBar();

    /**
     * 点击点评
     */
    public void clickCommentRight();


    public void clickRightNaviSearch();
}
