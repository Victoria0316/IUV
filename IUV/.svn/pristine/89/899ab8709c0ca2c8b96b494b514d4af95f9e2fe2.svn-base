package bluemobi.iuv.activity;


import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.AMap.InfoWindowAdapter;
import com.amap.api.maps2d.AMap.OnInfoWindowClickListener;
import com.amap.api.maps2d.AMap.OnMapClickListener;
import com.amap.api.maps2d.AMap.OnMarkerClickListener;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;
import com.amap.api.maps2d.overlay.DrivingRouteOverlay;
import com.amap.api.maps2d.overlay.WalkRouteOverlay;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.poisearch.PoiItemDetail;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.amap.api.services.poisearch.PoiSearch.OnPoiSearchListener;
import com.amap.api.services.route.BusRouteResult;
import com.amap.api.services.route.DrivePath;
import com.amap.api.services.route.DriveRouteResult;
import com.amap.api.services.route.RouteSearch;
import com.amap.api.services.route.RouteSearch.BusRouteQuery;
import com.amap.api.services.route.RouteSearch.DriveRouteQuery;
import com.amap.api.services.route.RouteSearch.OnRouteSearchListener;
import com.amap.api.services.route.RouteSearch.WalkRouteQuery;
import com.amap.api.services.route.WalkPath;
import com.amap.api.services.route.WalkRouteResult;

import bluemobi.iuv.R;
import bluemobi.iuv.app.IuwApplication;
import bluemobi.iuv.base.utils.Logger;
import bluemobi.iuv.entity.Lonlat;
import bluemobi.iuv.util.AMapUtil;
import bluemobi.iuv.util.RouteSearchPoiDialog;
import bluemobi.iuv.util.SharedPreferencesUtil;
import bluemobi.iuv.util.ToastUtil;


/**
 * AMapV1地图中简单介绍route搜索
 */
public class MapInfoRouteActicity extends Activity implements OnMarkerClickListener,
        OnMapClickListener, OnInfoWindowClickListener, InfoWindowAdapter
        , OnRouteSearchListener {
    private AMap aMap;
    private MapView mapView;

    private ProgressDialog progDialog = null;// 搜索时进度条

    private int drivingMode = RouteSearch.DrivingDefault;// 驾车默认模式

    private BusRouteResult busRouteResult;// 公交模式查询结果
    private DriveRouteResult driveRouteResult;// 驾车模式查询结果
    private WalkRouteResult walkRouteResult;// 步行模式查询结果
    private int routeType = 1;// 1代表公交模式，2代表驾车模式，3代表步行模式
    private String strStart;
    private String strEnd;
    private LatLonPoint startPoint = null;
    private LatLonPoint endPoint = null;
    private PoiSearch.Query startSearchQuery;
    private PoiSearch.Query endSearchQuery;

    private boolean isClickStart = false;
    private boolean isClickTarget = false;
    private Marker startMk, targetMk;
    private RouteSearch routeSearch;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.acticity_map_info);
        mapView = (MapView) findViewById(R.id.map);
        mapView.onCreate(bundle);// 此方法必须重写
        ImageView iv_map_back = (ImageView) findViewById(R.id.iv_map_back);
        iv_map_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        init();
    }

    /**
     * 初始化AMap对象
     */
    private void init() {
        if (aMap == null) {
            aMap = mapView.getMap();
            registerListener();
        }
        routeSearch = new RouteSearch(this);
        routeSearch.setRouteSearchListener(this);
        searchRoute();
    }


    /**
     * 点击搜索按钮开始Route搜索
     */
    public void searchRoute() {
        routeType = 2;// 标识为驾车模式
        String startLat = SharedPreferencesUtil.getFromFileByDefault(this, "geoLat", "41.927618");
        String startLon = SharedPreferencesUtil.getFromFileByDefault(this, "geoLng", "123.40706");
        Lonlat mLonlat = IuwApplication.getInstance().lonlat;
        String endLat = mLonlat.getLat();
        String endLon = mLonlat.getLon();
        Logger.e("tag===>",endLat+"---"+endLon);
        LatLonPoint mStartPoint = new LatLonPoint(Double.parseDouble(startLat), Double.parseDouble(startLon));
        LatLonPoint mEndPoint = new LatLonPoint(Double.parseDouble(endLat), Double.parseDouble(endLon));
        searchRouteResult(mStartPoint, mEndPoint);

    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        isClickStart = false;
        isClickTarget = false;
        if (marker.equals(startMk)) {

            startPoint = AMapUtil.convertToLatLonPoint(startMk.getPosition());
            startMk.hideInfoWindow();
            startMk.remove();
        } else if (marker.equals(targetMk)) {

            endPoint = AMapUtil.convertToLatLonPoint(targetMk.getPosition());
            targetMk.hideInfoWindow();
            targetMk.remove();
        }
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        if (marker.isInfoWindowShown()) {
            marker.hideInfoWindow();
        } else {
            marker.showInfoWindow();
        }
        return false;
    }

    @Override
    public void onMapClick(LatLng latng) {
        if (isClickStart) {
            startMk = aMap.addMarker(new MarkerOptions()
                    .anchor(0.5f, 1)
                    .icon(BitmapDescriptorFactory
                            .fromResource(R.drawable.point)).position(latng)
                    .title("点击选择为起点"));
            startMk.showInfoWindow();
        } else if (isClickTarget) {
            targetMk = aMap.addMarker(new MarkerOptions()
                    .anchor(0.5f, 1)
                    .icon(BitmapDescriptorFactory
                            .fromResource(R.drawable.point)).position(latng)
                    .title("点击选择为目的地"));
            targetMk.showInfoWindow();
        }
    }

    @Override
    public View getInfoContents(Marker marker) {
        return null;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    /**
     * 注册监听
     */
    private void registerListener() {
        aMap.setOnMapClickListener(MapInfoRouteActicity.this);
        aMap.setOnMarkerClickListener(MapInfoRouteActicity.this);
        aMap.setOnInfoWindowClickListener(MapInfoRouteActicity.this);
        aMap.setInfoWindowAdapter(MapInfoRouteActicity.this);
    }

    /**
     * 显示进度框
     */
    private void showProgressDialog() {
        if (progDialog == null)
            progDialog = new ProgressDialog(this);
        progDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progDialog.setIndeterminate(false);
        progDialog.setCancelable(true);
        progDialog.setMessage("正在搜索");
        progDialog.show();
    }

    /**
     * 隐藏进度框
     */
    private void dissmissProgressDialog() {
        if (progDialog != null) {
            progDialog.dismiss();
        }
    }

    /**
     * 开始搜索路径规划方案
     */
    public void searchRouteResult(LatLonPoint startPoint, LatLonPoint endPoint) {
        showProgressDialog();
        final RouteSearch.FromAndTo fromAndTo = new RouteSearch.FromAndTo(
                startPoint, endPoint);


        DriveRouteQuery query = new DriveRouteQuery(fromAndTo, drivingMode,
                null, null, "");// 第一个参数表示路径规划的起点和终点，第二个参数表示驾车模式，第三个参数表示途经点，第四个参数表示避让区域，第五个参数表示避让道路
        routeSearch.calculateDriveRouteAsyn(query);// 异步路径规划驾车模式查询

    }


    /**
     * 公交路线查询回调
     */
    @Override
    public void onBusRouteSearched(BusRouteResult result, int rCode) {

    }

    /**
     * 驾车结果回调
     */
    @Override
    public void onDriveRouteSearched(DriveRouteResult result, int rCode) {
        dissmissProgressDialog();
        if (rCode == 0) {
            if (result != null && result.getPaths() != null
                    && result.getPaths().size() > 0) {
                driveRouteResult = result;
                DrivePath drivePath = driveRouteResult.getPaths().get(0);
                aMap.clear();// 清理地图上的所有覆盖物
                DrivingRouteOverlay drivingRouteOverlay = new DrivingRouteOverlay(
                        this, aMap, drivePath, driveRouteResult.getStartPos(),
                        driveRouteResult.getTargetPos());
                drivingRouteOverlay.removeFromMap();
                drivingRouteOverlay.addToMap();
                drivingRouteOverlay.zoomToSpan();
            } else {
                ToastUtil.show(MapInfoRouteActicity.this, R.string.no_result);
            }
        } else if (rCode == 27) {
            ToastUtil.show(MapInfoRouteActicity.this, R.string.error_network);
        } else if (rCode == 32) {
            ToastUtil.show(MapInfoRouteActicity.this, R.string.error_key);
        } else {
            ToastUtil.show(MapInfoRouteActicity.this, getString(R.string.error_other)
                    + rCode);
        }
    }

    /**
     * 步行路线结果回调
     */
    @Override
    public void onWalkRouteSearched(WalkRouteResult result, int rCode) {
        dissmissProgressDialog();
        if (rCode == 0) {
            if (result != null && result.getPaths() != null
                    && result.getPaths().size() > 0) {
                walkRouteResult = result;
                WalkPath walkPath = walkRouteResult.getPaths().get(0);
                aMap.clear();// 清理地图上的所有覆盖物
                WalkRouteOverlay walkRouteOverlay = new WalkRouteOverlay(this,
                        aMap, walkPath, walkRouteResult.getStartPos(),
                        walkRouteResult.getTargetPos());
                walkRouteOverlay.removeFromMap();
                walkRouteOverlay.addToMap();
                walkRouteOverlay.zoomToSpan();
            } else {
                ToastUtil.show(MapInfoRouteActicity.this, R.string.no_result);
            }
        } else if (rCode == 27) {
            ToastUtil.show(MapInfoRouteActicity.this, R.string.error_network);
        } else if (rCode == 32) {
            ToastUtil.show(MapInfoRouteActicity.this, R.string.error_key);
        } else {
            ToastUtil.show(MapInfoRouteActicity.this, getString(R.string.error_other)
                    + rCode);
        }
    }


    /**
     * 方法必须重写
     */
    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }


}
