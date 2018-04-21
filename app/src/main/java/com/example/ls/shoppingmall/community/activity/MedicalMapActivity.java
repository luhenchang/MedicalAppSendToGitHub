package com.example.ls.shoppingmall.community.activity;

import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.UiSettings;
import com.baidu.mapapi.model.LatLng;
import com.example.ls.shoppingmall.R;
import com.example.ls.shoppingmall.app.MyApplication;
import com.example.ls.shoppingmall.community.bean.MyInformation;
import com.example.ls.shoppingmall.community.utis.LBSCloudSearch;
import com.example.ls.shoppingmall.community.utis.LocationManager;
import com.example.ls.shoppingmall.community.utis.MyOrientationListener;
import com.example.ls.shoppingmall.community.utis.OverlayManager;
import com.example.ls.shoppingmall.community.utis.Utils;
import com.google.gson.Gson;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class MedicalMapActivity extends AppCompatActivity implements View.OnClickListener {
    public int statusBarHeight = 0;
    public static final int MSG_NET_TIMEOUT = 100;
    public static final int MSG_NET_STATUS_ERROR = 200;
    public static final int MSG_NET_SUCC = 1;
    private static boolean initSearchFlag = false;
    //定位：
    public MyLocationListenner myListener = new MyLocationListenner();
    private float mCurrentX;
    boolean useDefaultIcon = true, hasPlanRoute = false, isServiceLive = false;
    //自定义图标
    private BitmapDescriptor mIconLocation, dragLocationIcon, bikeIcon, nearestIcon;
    /*
     * 处理网络请求
     */
    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_NET_TIMEOUT:
                    break;
                case MSG_NET_STATUS_ERROR:
                    break;
                case MSG_NET_SUCC:
                    initSearchFlag = true;
                    String result = msg.obj.toString();
                    Log.e("result", result);
                    parser(result);
                    break;
            }
        }
    };
    private TextView mtv;
    public static MapView mMapView = null;
    public static BaiduMap mBaiduMap;


    private double currentLatitude;
    private double currentLongitude;
    private LatLng currentLL;
    public static boolean isFirstLoc = true; // 是否首次定位
    private double changeLatitude;
    private double changeLongitude;
    public static LocationClient mlocationClient;

    private MyInformation.ContentsEntity bInfo;
    private final int DISMISS_SPLASH = 0;
    private ImageView btn_locale, btn_refresh;
    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case DISMISS_SPLASH:
                    Animator animator = AnimatorInflater.loadAnimator(MedicalMapActivity.this, R.animator.splash);
                    animator.setTarget(R.mipmap.delete);
                    animator.start();
                    break;
            }
        }
    };
    private MyLocationConfiguration.LocationMode mCurrentMode;
    public static MyOrientationListener myOrientationListener;
    private ArrayList<MyInformation.ContentsEntity> myInformations;
    private MyInformation myInformation;
    private static final int BAIDU_READ_PHONE_STATE = 100;
    public static RelativeLayout medical_information_rl;
    private ImageView medical_header_iv;
    private TextView medical_name_tv, medical_tager_one, medical_tager_two, medical_tager_three, medical_informations;
    private LinearLayout bike_layout;
    private List<Marker> makes;
    //这个点击显示RecylerView所有的说！！！
    public static TextView myRecylerView_show_tv;
    private PopupWindow popwindow;
    private View view_pop_view;
    OverlayManager routeOverlay = null;
    MyApplication app;
    private ImageView mBack_iv;
    private BitmapDescriptor hospital;
    private LinearLayout baidumap_infowindow;
    private MarkerOnInfoWindowClickListener markerListener;
    private InfoWindow mInfoWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_medical_map);
        MyApplication.addActivity(this);
        //为了解决空指针问题提到最上面的
        Log.e("incurrent", "1");
        mMapView = (MapView) findViewById(R.id.id_bmapViews);
        mBaiduMap = mMapView.getMap();
        // 开启定位图层
        mBaiduMap.setMyLocationEnabled(true);
        app = (MyApplication) MyApplication.getInstance();
        makes = new ArrayList<>();
        myInformations = new ArrayList<>();
        BMapManager.init();
        showContacts();
    }


    //获取权限
    private void showContacts() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "没有权限,请手动开启定位权限", Toast.LENGTH_SHORT).show();
            // 申请一个（或多个）权限，并提供用于回调返回的获取码（用户定义）
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.READ_PHONE_STATE}, BAIDU_READ_PHONE_STATE);
        } else {
            starGetJW();

            initMapView();
        }
    }

    private void starGetJW() {
       /* mMapView = (MapView) findViewById(R.id.id_bmapViews);
        mBaiduMap = mMapView.getMap();
        // 开启定位图层
        mBaiduMap.setMyLocationEnabled(true);*/

        // 定位初始化
        mlocationClient = new LocationClient(this.getApplicationContext());
        mlocationClient.registerLocationListener(myListener);
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(1000);//设置onReceiveLocation()获取位置的频率
        option.setIsNeedAddress(true);//如想获得具体位置就需要设置为true
        mlocationClient.setLocOption(option);
        mlocationClient.start();

        BitmapDescriptor mCurrentMarker = BitmapDescriptorFactory
                .fromResource(R.drawable.baidu_map_self);
        Log.e("进度和维度1", "金纬度：" + changeLatitude + ":纬度" + changeLongitude);
        //这里的模式可以庚随地图或者null时候不会自己在中心
        mCurrentMode = MyLocationConfiguration.LocationMode.COMPASS;
       /* mBaiduMap.setMyLocationConfigeration(new MyLocationConfiguration(
                mCurrentMode, true, mCurrentMarker));*/
        mBaiduMap.setMyLocationConfigeration(new MyLocationConfiguration(
                null, true, mCurrentMarker));
        myOrientationListener = new MyOrientationListener(this);
      /*  Log.e("进度和维度1", "金纬度：" + changeLatitude + ":纬度" + changeLongitude);
        //这里的模式可以庚随地图或者null时候不会自己在中心
        mCurrentMode = MyLocationConfiguration.LocationMode.COMPASS;
        mBaiduMap.setMyLocationConfigeration(new MyLocationConfiguration(
                null, true, null));
        myOrientationListener = new MyOrientationListener(this);*/
        //通过接口回调来实现实时方向的改变
        myOrientationListener.setOnOrientationListener(new MyOrientationListener.OnOrientationListener() {
            @Override
            public void onOrientationChanged(float x) {
                mCurrentX = x;
            }
        });
        myOrientationListener.start();
        initMarkerClickEvent();
    }

    //这个设置点击事件来获取地图上面的所有标记物体。可以这里进行对应的详情
    private void initMarkerClickEvent() {
        // 对Marker的点击
        mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(final Marker marker) {
                // 获得marker中的数据
                if (marker != null && marker.getExtraInfo() != null) {
                    makes.add(marker);
                    MyInformation.ContentsEntity bikeInfo = (MyInformation.ContentsEntity) marker.getExtraInfo().get("info");
                    if (bikeInfo != null)
                        //这里首先将所有的图标还原为之前的:这样之前所有的图标就变回去了。地图上面就你一个具体选中的位置变图片。
                        resetInfosOverlays();
                    //这里给点击过的地方添加标记
                    // marker.setIcon(dragLocationIcon);
                    //TODO 这里进行弹窗的设置:例如设置医院的弹窗好个人的弹窗是不一样的。这里需要设置点击之后中心作坐标为这一个marker
                    updateBikeInfo(bikeInfo);
                    //TODO 这里用来将自己设置为中心点
                    try {
                        {
                            LatLng ll = new LatLng(bikeInfo.getLocation().get(1), bikeInfo.getLocation().get(0));

                            //   LatLng ll=new LatLng(location.getLatitude(),location.getLongitude());

                            createInfoWindow(baidumap_infowindow, bikeInfo);
                            mInfoWindow = new InfoWindow(BitmapDescriptorFactory.fromView(baidumap_infowindow), ll, -17, markerListener);

                            //显示InfoWindow
                            mBaiduMap.showInfoWindow(mInfoWindow);
                            updateBikeInfo(bikeInfo);

                            MapStatusUpdate update = MapStatusUpdateFactory.zoomTo(15f);
                            mBaiduMap.animateMapStatus(update);
                            update = MapStatusUpdateFactory.newLatLng(ll);
                            mBaiduMap.animateMapStatus(update);
                        /*//TODO 将地图移到到自己中心点。
                        MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(myLocation);
                        //这里报空指针
                        mBaiduMap.setMapStatus(u);*/
                        }
                    } catch (Exception e) {
                        Toast.makeText(MedicalMapActivity.this, "请稍后再试", Toast.LENGTH_SHORT).show();
                    }
                }
                return true;
            }
        });
    }
    /**
     * @param baidumap_infowindow
     * @param bean
     * @Description: 创建 弹出窗口
     * @Author:杨攀
     * @Since: 2016年1月20日上午11:18:33
     */
    private void createInfoWindow(LinearLayout baidumap_infowindow, MyInformation.ContentsEntity bean) {

       InfoWindowHolder holder = null;
        if (baidumap_infowindow.getTag() == null) {
            holder = new InfoWindowHolder();

            holder.tv_entname = (TextView) baidumap_infowindow.findViewById(R.id.tv_entname);
            holder.tv_checkdept = (TextView) baidumap_infowindow.findViewById(R.id.tv_checkdept);
            holder.tv_checkuser = (TextView) baidumap_infowindow.findViewById(R.id.tv_checkuser);
            holder.tv_checktime = (TextView) baidumap_infowindow.findViewById(R.id.tv_checktime);
            baidumap_infowindow.setTag(holder);
        }

        holder = (InfoWindowHolder) baidumap_infowindow.getTag();

        holder.tv_entname.setText(bean.getAddress() + "");
        holder.tv_checkdept.setText(bean.getCity() + "");
        holder.tv_checkuser.setText(bean.getTags() + "");
        holder.tv_checktime.setText(bean.getCoord_type() + "");
    }
    /**
     * @Description: 复用弹出面板mMarkerLy的控件
     * @Author:杨攀
     * @Since:2016年1月20日上午11:05:30
     */
    public class InfoWindowHolder {

        public TextView tv_entname;
        public TextView tv_checkdept;
        public TextView tv_checkuser;
        public TextView tv_checktime;

    }
    //这里来显示弹窗的哦！
    private void updateBikeInfo(MyInformation.ContentsEntity bikeInfo) {

        if (!hasPlanRoute) {
            myRecylerView_show_tv.setVisibility(GONE);
            medical_information_rl.setVisibility(VISIBLE);
            bike_layout.setVisibility(VISIBLE);
            medical_name_tv.setText(bikeInfo.getTags());
            medical_tager_one.setText(bikeInfo.getCity());
            medical_informations.setText(bikeInfo.getAddress());
            bInfo = bikeInfo;
        }
    }

    //还原之前点击添加到集合中的每一个图标样子
    public void resetInfosOverlays() {
        Log.e("myinoformation.size", myInformation.getSize() + "");
        Log.e("makes.size", makes.size() + "");
        //TODO 这里用来还原之前点击的图标哦
        for (int i = 0; i < myInformations.size(); i++) {
            if (myInformation.getContents().get(i).getTags().equals("王飞医生")) {
                makes.get(0).setIcon(bikeIcon);
            } else {
                makes.get(0).setIcon(hospital);
            }
        }
    }

    private void initMapView() {
        //点击每一个marcker出现的弹窗
        baidumap_infowindow = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.baidumap_infowindow, null);
        markerListener = new MarkerOnInfoWindowClickListener();

        mBack_iv = (ImageView) findViewById(R.id.baidu_back_iv);
        mBack_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        view_pop_view = findViewById(R.id.view_pop_view);
        medical_information_rl = (RelativeLayout) findViewById(R.id.medical_information_rl);
        medical_header_iv = (ImageView) findViewById(R.id.medical_header_iv);
        medical_name_tv = (TextView) findViewById(R.id.medical_name_tv);
        medical_tager_one = (TextView) findViewById(R.id.medical_tager_one);
        medical_tager_two = (TextView) findViewById(R.id.medical_tager_two);
        medical_tager_three = (TextView) findViewById(R.id.medical_tager_three);
        medical_informations = (TextView) findViewById(R.id.medical_informations);
        myRecylerView_show_tv = (TextView) findViewById(R.id.myRecylerView_show_tv);
        //这个是定位快速获取医生的按钮v
        bike_layout = (LinearLayout) findViewById(R.id.bike_layout);
        btn_locale = (ImageView) findViewById(R.id.btn_locale);
        btn_refresh = (ImageView) findViewById(R.id.btn_refresh);
        btn_locale.setOnClickListener(this);
        btn_refresh.setOnClickListener(this);
        myRecylerView_show_tv.setOnClickListener(this);

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Utils.dp2px(this, 50));
        layoutParams.setMargins(0, statusBarHeight, 0, 0);//4个参数按顺序分别是左上右下
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        Log.d("gaolei", "statusBarHeight---------------" + statusBarHeight);
        layoutParams2.setMargins(40, statusBarHeight + Utils.dp2px(this, 50), 0, 0);//4个参数按顺序分别是左上右下
        UiSettings uiSettings = mBaiduMap.getUiSettings();
        uiSettings.setScrollGesturesEnabled(true);
        uiSettings.setRotateGesturesEnabled(false);
        mBaiduMap.setOnMapStatusChangeListener(changeListener);
        mMapView.setOnClickListener(this);
        dragLocationIcon = BitmapDescriptorFactory.fromResource(R.mipmap.myselfss);
        bikeIcon = BitmapDescriptorFactory.fromResource(R.drawable.baidu_map_doctor);
        hospital = BitmapDescriptorFactory.fromResource(R.drawable.hospital_iv);
        handler.sendEmptyMessageDelayed(DISMISS_SPLASH, 2000);
    }

    private BaiduMap.OnMapStatusChangeListener changeListener = new BaiduMap.OnMapStatusChangeListener() {
        public void onMapStatusChangeStart(MapStatus mapStatus) {
        }

        public void onMapStatusChangeFinish(MapStatus mapStatus) {
            String _str = mapStatus.toString();
            String _regex = "target lat: (.*)\ntarget lng";
            String _regex2 = "target lng: (.*)\ntarget screen x";
            changeLatitude = Double.parseDouble(latlng(_regex, _str));
            changeLongitude = Double.parseDouble(latlng(_regex2, _str));
            LatLng changeLL = new LatLng(changeLatitude, changeLongitude);
            Log.d("gaolei", "changeLatitude-----change--------" + changeLatitude);
            Log.d("gaolei", "changeLongitude-----change--------" + changeLongitude);
        }

        public void onMapStatusChange(MapStatus mapStatus) {
        }
    };

    //这里隐藏窗口
    private final class MarkerOnInfoWindowClickListener implements InfoWindow.OnInfoWindowClickListener {

        @Override
        public void onInfoWindowClick() {
            //隐藏InfoWindow
            mBaiduMap.hideInfoWindow();
        }

    }

    private String latlng(String regexStr, String str) {
        Pattern pattern = Pattern.compile(regexStr);
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            str = matcher.group(1);
        }
        return str;
    }

    public void initDatas() {
        Log.e("进度和维度2", "金纬度：" + changeLatitude + ":纬度" + changeLongitude);

        // 发起搜索请求
        search();
        app.setHandler(mHandler);


    }

    /*
        * 云检索发起
        */
    private void search() {
        search(LBSCloudSearch.SEARCH_TYPE_LOCAL);
    }

    /*
     * 根据搜索类型发起检索
	 */
    private void search(int searchType) {
        if (myInformations != null) {
            if (myInformations.size() > 0) {
                myInformations.clear(); // 搜索钱清空列表
            }
        }
        // 云检索发起
        LBSCloudSearch.request(searchType, getRequestParams(), mHandler,
                MyApplication.networkType);
    }

    /*
     * 获取云检索参数
	 */
    private HashMap<String, String> getRequestParams() {
        HashMap<String, String> map = new HashMap<String, String>();
        try {
            String filter = "";
            // 附件，周边搜索
            map.put("search_name", URLEncoder.encode("天津市", "utf-8"));
            map.put("radius", "2000");
            if (app.currlocation != null) {
                map.put("location", app.currlocation.getLongitude() + ","
                        + app.currlocation.getLatitude());
            } else {
                // 无定位数据默认当前位置
                double cLat = currentLatitude;
                double cLon = currentLongitude;
                map.put("location", cLat + "," + cLon);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        app.setFilterParams(map);

        return map;
    }

    /*
        * 解析返回数据
        */
    private void parser(String json) {
        Gson gson = new Gson();
        myInformation = gson.fromJson(json, MyInformation.class);

        myInformations.addAll(myInformation.getContents());
        // Toast.makeText(MedicalMapActivity.this, "" + json.toString(), Toast.LENGTH_SHORT).show();
        Log.e("Myinformation", myInformation.getContents().toString());
        if (!isServiceLive && myInformations.size() > 0) {
            addOverLayout(currentLatitude, currentLongitude);
        }
    }

    private void addOverLayout(double _latitude, double _longitude) {
        //先清除图层
        mBaiduMap.clear();
        mlocationClient.requestLocation();
    /*    // 定义Maker坐标点
        LatLng point = new LatLng(_latitude, _longitude);
        // 构建MarkerOption，用于在地图上添加Marker:这个是那个大红点哦！！！！
        MarkerOptions options = new MarkerOptions().position(point)
                .icon(dragLocationIcon);
        // 在地图上添加Marker，并显示
        mBaiduMap.addOverlay(options);*/
        if (myInformations.size() > 0) {

           /* 下面这句是为了来获取最近的
            initNearestBike(bikeInfo, new LatLng(_latitude - 0.0005, _longitude - 0.0005));*/
            addInfosOverlay(myInformations);
        }
    }

    public void addInfosOverlay(List<MyInformation.ContentsEntity> infos) {
        LatLng latLng = null;
        OverlayOptions overlayOptions = null;
        Marker marker = null;
        for (int i = 0; i < infos.size(); i++) {//MyInformation.ContentsEntity info : infos
            MyInformation.ContentsEntity info = infos.get(i);
            if (info.getTags().equals("王飞医生")) {
                // 位置
                latLng = new LatLng(info.getLocation().get(1), info.getLocation().get(0));
                //TODO  图标TODO
                overlayOptions = new MarkerOptions().position(latLng)
                        .icon(bikeIcon).zIndex(5);
                //这个marker设置消息之后可以点击世纪之后显示哦！弹出的又来
                marker = (Marker) (mBaiduMap.addOverlay(overlayOptions));
            } else {
                // 位置
                latLng = new LatLng(info.getLocation().get(1), info.getLocation().get(0));
                //TODO  图标
                overlayOptions = new MarkerOptions().position(latLng)
                        .icon(hospital).zIndex(5);
                //这个marker设置消息之后可以点击世纪之后显示哦！弹出的又来
                marker = (Marker) (mBaiduMap.addOverlay(overlayOptions));
            }

            Bundle bundle = new Bundle();
            bundle.putSerializable("info", info);
            marker.setExtraInfo(bundle);
        }
        LatLng myLocation = new LatLng(currentLatitude, currentLongitude);
        try {
            // 将地图移到到自己中心点。
            MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(myLocation);
            //这里报空指针
            mBaiduMap.setMapStatus(u);
        } catch (Exception e) {
            Toast.makeText(this, "请稍后再试", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_locale:
                if (!Utils.isServiceWork(this, "com.biubike.service.RouteService"))
                    cancelBook();
                break;
            case R.id.btn_refresh:
                if (routeOverlay != null)
                    routeOverlay.removeFromMap();
                Log.d("gaolei", "changeLatitude-----btn_refresh--------" + changeLatitude);
                Log.d("gaolei", "changeLongitude-----btn_refresh--------" + changeLongitude);
                addOverLayout(currentLatitude, currentLongitude);
                break;
            case R.id.myRecylerView_show_tv:
                if (myInformations != null) {
                    if (myInformations.size() > 0) {
                        initBottomSheetDialog2();
                        myRecylerView_show_tv.setVisibility(GONE);
                    }
                }
                break;
        }
    }

    private void initBottomSheetDialog2() {
        //创建recyclerView
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        RecyclerView recyclerView = new RecyclerView(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        RecyclerViewAdapter recyclerAdapter = new RecyclerViewAdapter(myInformations, this);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerAdapter.setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(View item, int position) {
                // Toast.makeText(MedicalMapActivity.this, "item " + position, Toast.LENGTH_SHORT).show();
                bottomSheetDialog.dismiss();
            }
        });

        bottomSheetDialog.setContentView(recyclerView);
        bottomSheetDialog.show();

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    static class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

        private List<MyInformation.ContentsEntity> list;
        private Context context;
        private OnItemClickListener onItemClickListener;

        public RecyclerViewAdapter(List<MyInformation.ContentsEntity> list, Context context) {
            this.list = list;
            this.context = context;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.item_recyclerview_layout, null);
            return new ViewHolder(inflate);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {
            holder.tv.setText(list.get(position).getTags() + "");
            holder.tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClickListener(v, position);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        static class ViewHolder extends RecyclerView.ViewHolder {
            TextView tv;

            ViewHolder(View itemView) {
                super(itemView);
                tv = itemView.findViewById(R.id.medical_name_tv);
            }
        }

        void setOnItemClickListener(OnItemClickListener onItemClickListener) {
            this.onItemClickListener = onItemClickListener;
        }

        interface OnItemClickListener {
            void onItemClickListener(View item, int position);
        }
    }

    private void cancelBook() {

        if (routeOverlay != null)
            routeOverlay.removeFromMap();
        MapStatus.Builder builder = new MapStatus.Builder();
        //地图缩放比设置为18
        builder.target(currentLL).zoom(15.0f);
        mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
    }

    /**
     * 定位SDK监听函数
     */
    class MyLocationListenner implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation bdLocation){
            // map view 销毁后不在处理新接收的位置
            if (bdLocation == null || mMapView == null) {
                return;
            }
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(bdLocation.getRadius())
                    .direction(mCurrentX)//设定图标方向     // 此处设置开发者获取到的方向信息，顺时针0-360
                    .latitude(bdLocation.getLatitude())
                    .longitude(bdLocation.getLongitude()).build();
            mBaiduMap.setMyLocationData(locData);
            currentLatitude = bdLocation.getLatitude();
            currentLongitude = bdLocation.getLongitude();
            // current_addr.setText(bdLocation.getAddrStr());
            currentLL = new LatLng(bdLocation.getLatitude(),
                    bdLocation.getLongitude());

            LocationManager.getInstance().setCurrentLL(currentLL);
            LocationManager.getInstance().setAddress(bdLocation.getAddrStr());
            //  startNodeStr = PlanNode.withLocation(currentLL);
            //option.setScanSpan(5000)，每隔5000ms这个方法就会调用一次，而有些我们只想调用一次，所以要判断一下isFirstLoc
            if (isFirstLoc) {
                isFirstLoc = false;
                LatLng ll = new LatLng(bdLocation.getLatitude(),
                        bdLocation.getLongitude());
                MapStatus.Builder builder = new MapStatus.Builder();
                //地图缩放比设置为18
                builder.target(ll).zoom(15.0f);
                mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
                changeLatitude = bdLocation.getLatitude();
                changeLongitude = bdLocation.getLongitude();

                // Toast.makeText(MedicalMapActivity.this, "金纬度：" + changeLatitude + ":纬度" + changeLongitude, Toast.LENGTH_SHORT).show();
                Log.e("进度和维度0", "金纬度：" + changeLatitude + ":纬度" + changeLongitude);
                initDatas();
                /**/

            }
        }

    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onStop() {
        mBaiduMap.setMyLocationEnabled(false);
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (myRecylerView_show_tv != null) {
            myRecylerView_show_tv.setVisibility(VISIBLE);

        }
        if (medical_information_rl != null) {
            medical_information_rl.setVisibility(GONE);
        }
        if (mMapView != null) {
            mMapView.onResume();
        }
    }

    protected void onRestart() {
        super.onRestart();
        if (mBaiduMap != null) {
            mBaiduMap.setMyLocationEnabled(true);
        }
        if (mlocationClient != null) {
            mlocationClient.start();
        }
        if (myOrientationListener != null) {
            myOrientationListener.start();
        }
        if (mlocationClient != null) {
            mlocationClient.requestLocation();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mlocationClient != null) {
            // 退出时销毁定位
            mlocationClient.stop();
        }
        if (mMapView != null) {
            // 关闭定位图层
            mBaiduMap.setMyLocationEnabled(false);
        }
        if (mMapView != null) {
            mMapView.onDestroy();
            mMapView = null;
            isFirstLoc = true;
        }
    }
}
