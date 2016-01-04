package bluemobi.iuv.eventbus;

/**
 * Created by liufy on 2015/10/8.
 * 大型百货
 */
public class LargeDeptEvent extends BaseEvent
{
    private String divisionCode;//行政区划编码
    private String divisionName;//行政区划名称全称
    private String divisionType;//区划类型：0国家1省份2地市3区县


    /**
     * 根据这个id 查询 大型百货 热门商场数据
     */
    private String id;

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

    public String getDivisionName() {
        return divisionName;
    }

    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
