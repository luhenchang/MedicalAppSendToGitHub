package com.example.ls.shoppingmall.community.utis;

import android.util.Log;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.example.ls.shoppingmall.app.MyApplication;

public class LBSLocation {

    private static LBSLocation location = null;
    private static MyApplication app = null;
    private MyLocationListenner myListener = new MyLocationListenner();
    public  LocationClient mLocationClient = null;

    public static LBSLocation getInstance(MyApplication application) {
        app = application;
        if (location == null) {
            location = new LBSLocation(app);
        }

        return location;
    }

    private LBSLocation(MyApplication app) {
        mLocationClient = new LocationClient(app);
        mLocationClient.registerLocationListener(myListener);
        mLocationClient.start();
    }

    /**
     */
    public void startLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true);
        option.setAddrType("all");
        option.setCoorType("bd09ll");
        option.disableCache(true);
        mLocationClient.setLocOption(option);
        mLocationClient.requestLocation();
    }

    /**
     *
     */
    public class MyLocationListenner implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            if (location == null) {
                return;
            }
            app.currlocation = location;
        //    Toast.makeText(app.getApplicationContext(), "经度=" + location.getLatitude() + "纬度=" + location.getLongitude() + "", Toast.LENGTH_SHORT).show();
            Log.e("Tag", "经度app=" + location.getLatitude() + "纬度=" + location.getLongitude() + "");

           // mLocationClient.stop();

        }

        public void onReceivePoi(BDLocation poiLocation) {
        }
    }
}
