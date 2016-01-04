package bluemobi.iuv.eventbus;

/**
 * Created by liufy on 2015/11/2.
 *
 */
public class TopSortEvent extends BaseEvent {

    /**
     * 购物车 特产店 超市等ID
     */
    private String id;

    private String divisionCode;

    private String shopsTypeId;

    private String divisionType;

    private String shopsTypeName;

    private String sortType;

    public String getDivisionCode() {
        return divisionCode;
    }

    public void setDivisionCode(String divisionCode) {
        this.divisionCode = divisionCode;
    }

    public String getShopsTypeId() {
        return shopsTypeId;
    }

    public void setShopsTypeId(String shopsTypeId) {
        this.shopsTypeId = shopsTypeId;
    }

    public String getSortType() {
        return sortType;
    }

    public void setSortType(String sortType) {
        this.sortType = sortType;
    }
    public String getShopsTypeName() {
        return shopsTypeName;
    }

    public void setShopsTypeName(String shopsTypeName) {
        this.shopsTypeName = shopsTypeName;
    }

    public String getDivisionType() {
        return divisionType;
    }

    public void setDivisionType(String divisionType) {
        this.divisionType = divisionType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
