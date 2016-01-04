package bluemobi.iuv.activity;

import android.app.Activity;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.LocationManagerProxy;
import com.amap.api.location.LocationProviderProxy;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.LocationSource;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.LatLngBounds;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import bluemobi.iuv.R;
import bluemobi.iuv.app.IuwApplication;
import bluemobi.iuv.base.utils.Logger;
import bluemobi.iuv.entity.MarketInfoBean;

/**
 * Created by liufy on 2015/9/17.
 * P8_5地图信息页面
 */
public class MapInfoActicity extends Activity implements ImageView.OnClickListener, LocationSource, AMapLocationListener, AMap.OnMarkerClickListener,
        AMap.OnInfoWindowClickListener, AMap.OnMapLoadedListener, AMap.InfoWindowAdapter {
    private MapView mapView;
    private AMap aMap;
    private ImageView iv_map_back;
    private OnLocationChangedListener mListener;
    private LocationManagerProxy mAMapLocationManager;
    private ArrayList<MarketInfoBean> mMarketInfoBeans;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acticity_map_info);
        mapView = (MapView) findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);// 此方法必须重写
        iv_map_back = (ImageView) findViewById(R.id.iv_map_back);
        iv_map_back.setOnClickListener(this);
        mMarketInfoBeans = IuwApplication.getInstance().iuwMarketInfoBeans;
        init();
    }

    /**
     * 初始化AMap对象
     */
    private void init() {
        mMarketInfoBeans = IuwApplication.getInstance().iuwMarketInfoBeans;
        if (aMap == null) {
            aMap = mapView.getMap();
            setUpMap();
        }
    }

    /**
     * 设置一些amap的属性
     */
    private void setUpMap() {
       /* // 自定义系统定位小蓝点
        MyLocationStyle myLocationStyle = new MyLocationStyle();
        myLocationStyle.myLocationIcon(BitmapDescriptorFactory
                .fromResource(R.drawable.location_marker));// 设置小蓝点的图标
        myLocationStyle.strokeColor(Color.BLACK);// 设置圆形的边框颜色
        myLocationStyle.radiusFillColor(Color.argb(100, 0, 0, 180));// 设置圆形的填充颜色
        // myLocationStyle.anchor(int,int)//设置小蓝点的锚点
        myLocationStyle.strokeWidth(1.0f);// 设置圆形的边框粗细
        aMap.setMyLocationStyle(myLocationStyle);
        aMap.setLocationSource(this);// 设置定位监听
        aMap.getUiSettings().setMyLocationButtonEnabled(true);// 设置默认定位按钮是否显示
        aMap.setMyLocationEnabled(true);// 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false*/
        // aMap.setMyLocationType()
        aMap.setOnMarkerClickListener(this);// 设置点击marker事件监听器
        aMap.setOnInfoWindowClickListener(this);// 设置点击infoWindow事件监听器
        aMap.setOnMapLoadedListener(this);// 设置amap加载成功事件监听器
        aMap.setInfoWindowAdapter(this);// 设置自定义InfoWindow样式
        addMarkersToMap();// 往地图上添加marker
    }

    private List<LatLng> lists = new ArrayList<LatLng>();

    /**
     * 在地图上添加marker
     */
    private void addMarkersToMap() {
        drawMarkers();
    }


    public void drawMarkers() {

        for (int i = 0; i < mMarketInfoBeans.size(); i++) {
            LatLng latlng = new LatLng(Double.parseDouble(mMarketInfoBeans.get(i).getShopLat()), Double.parseDouble(mMarketInfoBeans.get(i).getShopLon()));

            Marker marker = aMap.addMarker(new MarkerOptions()
                    .position(latlng)
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.poi))
                    .draggable(true));
            marker.setTitle(mMarketInfoBeans.get(i).getShopName());
            marker.setSnippet(mMarketInfoBeans.get(i).getDistance());
            marker.showInfoWindow();// 设置默认显示一个infowinfow
        }

    }


    @Override
    public View getInfoWindow(Marker marker) {
        View infoWindow = getLayoutInflater().inflate(
                R.layout.custom_info_contents, null);
        render(marker, infoWindow);
        return infoWindow;
    }

    /**
     * 自定义infowinfow窗口
     */
    public void render(Marker marker, View view) {

        TextView tv_point_up = ((TextView) view.findViewById(R.id.tv_point_up));
        TextView tv_point_down = ((TextView) view.findViewById(R.id.tv_point_down));
        tv_point_up.setText(marker.getTitle());
        tv_point_down.setText(marker.getSnippet());

    }


    @Override
    public View getInfoContents(Marker marker) {

        return null;
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
        deactivate();
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_map_back:
                finish();
                break;
        }
    }

    /**
     * 此方法已经废弃
     */
    @Override
    public void onLocationChanged(Location location) {
    }

    @Override
    public void onProviderDisabled(String provider) {
    }

    @Override
    public void onProviderEnabled(String provider) {
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    /**
     * 定位成功后回调函数
     */
    @Override
    public void onLocationChanged(AMapLocation aLocation) {
        if (mListener != null && aLocation != null) {
            mListener.onLocationChanged(aLocation);// 显示系统小蓝点
        }
    }

    /**
     * 激活定位
     */
    @Override
    public void activate(OnLocationChangedListener listener) {
        mListener = listener;
        if (mAMapLocationManager == null) {
            mAMapLocationManager = LocationManagerProxy.getInstance(this);
            /*
             * mAMapLocManager.setGpsEnable(false);
			 * 1.0.2版本新增方法，设置true表示混合定位中包含gps定位，false表示纯网络定位，默认是true Location
			 * API定位采用GPS和网络混合定位方式
			 * ，第一个参数是定位provider，第二个参数时间最短是2000毫秒，第三个参数距离间隔单位是米，第四个参数是定位监听者
			 */
            mAMapLocationManager.requestLocationData(
                    LocationProviderProxy.AMapNetwork, 2000, 10, this);
        }
    }

    /**
     * 停止定位
     */
    @Override
    public void deactivate() {
        mListener = null;
        if (mAMapLocationManager != null) {
            mAMapLocationManager.removeUpdates(this);
            mAMapLocationManager.destroy();
        }
        mAMapLocationManager = null;
    }

    @Override
    public void onInfoWindowClick(Marker marker) {

    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }

    @Override
    public void onMapLoaded() {
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        for (int i = 0; i < lists.size(); i++) {

            builder.include(lists.get(i));
        }
        LatLngBounds build = builder.build();
        aMap.moveCamera(CameraUpdateFactory.newLatLngBounds(build, 10));
    }
}
