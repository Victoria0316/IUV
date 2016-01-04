package bluemobi.iuv.eventbus;

/**
 * Created by liufy on 2015/9/29.
 */
public class CountySpinnerBean extends BaseEvent {

    private String id;

    private String name;

    private String divisionCode;

    private String divisionType;

    private boolean isTreasure;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isTreasure() {
        return isTreasure;
    }

    public void setIsTreasure(boolean isTreasure) {
        this.isTreasure = isTreasure;
    }

    public String getDivisionType() {
        return divisionType;
    }

    public void setDivisionType(String divisionType) {
        this.divisionType = divisionType;
    }

    public String getDivisionCode() {
        return divisionCode;
    }

    public void setDivisionCode(String divisionCode) {
        this.divisionCode = divisionCode;
    }
}
