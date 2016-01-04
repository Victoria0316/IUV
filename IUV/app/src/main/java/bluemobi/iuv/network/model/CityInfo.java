package bluemobi.iuv.network.model;

/**
 * Created by gaoxy on 2015/9/29.
 */
public class CityInfo {
    private String id;//主键
    private String divisionCode;//行政区划编码
    private String divisionName;//行政区划名称全称
    private String divisionNameJp;//名称简称
    private String divisionPy;//名称拼音
    private String divisionJp;//名称字母
    private String divisionType;//区划类型：0国家1省份2地市3区县
    private String recommendStatus;//首页推荐（0未推荐，1推荐）
    private String shopsLongitude;//经度
    private String shopsLatitude;//纬度
    private String pid;//父ID
    private String areaName;//区域名称"

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getDivisionNameJp() {
        return divisionNameJp;
    }

    public void setDivisionNameJp(String divisionNameJp) {
        this.divisionNameJp = divisionNameJp;
    }

    public String getDivisionPy() {
        return divisionPy;
    }

    public void setDivisionPy(String divisionPy) {
        this.divisionPy = divisionPy;
    }

    public String getDivisionJp() {
        return divisionJp;
    }

    public void setDivisionJp(String divisionJp) {
        this.divisionJp = divisionJp;
    }

    public String getDivisionType() {
        return divisionType;
    }

    public void setDivisionType(String divisionType) {
        this.divisionType = divisionType;
    }

    public String getRecommendStatus() {
        return recommendStatus;
    }

    public void setRecommendStatus(String recommendStatus) {
        this.recommendStatus = recommendStatus;
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

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }
}
