package com.first.LoadMoreAddRefresh;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cn.baselib.base.BaseActivity;
import com.cn.baselib.utils.LogUtils;
import com.cn.baselib.utils.NetworkUtils;
import com.first.R;

import butterknife.Bind;


/**
 * 还没用上
 */
public class RefreshActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.tv)
    TextView tv;
    @Bind(R.id.btn)
    Button btn;
    @Bind(R.id.btn2)
    Button btn2;
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_load_more;
    }

    @Override
    protected void initView() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btn.setOnClickListener(this);
        btn2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn:
                tv.setText("wifi是否可用 true 为可用\n现在是：" + NetworkUtils.isWifiAvailable());
                LogUtils.i("wifi是否可用 true 为可用\n现在是：" + NetworkUtils.isWifiAvailable()+"\ngetWifiEnabled:"+NetworkUtils.getWifiEnabled()+"\nisAvailableByPing:"+NetworkUtils.isAvailableByPing());
                break;
            case R.id.btn2:
                WifiManager wifiManager = (WifiManager) this.getSystemService(Context.WIFI_SERVICE);
                WifiInfo wifiInfo = wifiManager.getConnectionInfo();
                String status =getStatus(wifiManager.getWifiState());
                LogUtils.i("wifi状态：" + IsStartWifi(this));
                tv.setText("wifi状态：" + IsStartWifi(this));
//
//                LogUtils.i("wifi状态：" + status + "\n连接的wifi名字：" + wifiInfo.getSSID() + "\n获取IP地址:" + wifiInfo.getIpAddress() + "\n获取网络ID:" + wifiInfo.getNetworkId()
//                        + "\n获取RSSI，RSSI就是接受信号强度指示:" + wifiInfo.getRssi() + "\n获取连接速度，可以让用户获知这一信息:" + wifiInfo.getLinkSpeed());
//
//                tv.setText("连接的wifi名字：" + wifiInfo.getSSID() + "\n获取IP地址:" + wifiInfo.getIpAddress() + "\n获取网络ID:" + wifiInfo.getNetworkId()
//                        + "\n获取RSSI，RSSI就是接受信号强度指示:" + wifiInfo.getRssi() + "\n获取连接速度，可以让用户获知这一信息:" + wifiInfo.getLinkSpeed());
                break;
            default:
                break;
        }
    }
    public static boolean IsStartWifi(Context context)  {
        ConnectivityManager connectivityManager = (ConnectivityManager) context .getSystemService(Context.CONNECTIVITY_SERVICE);
        boolean isWifiDataEnable = false;
        isWifiDataEnable = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnectedOrConnecting();
        return isWifiDataEnable;
    }
    private String getStatus(int wifiState) {
        String str = "";
        switch (wifiState) {
            case WifiManager.WIFI_STATE_DISABLED:
                str = "WIFI_STATE_DISABLED";
                break;
            case WifiManager.WIFI_STATE_DISABLING:
                str = "WIFI_STATE_DISABLING";
                break;
            case WifiManager.WIFI_STATE_ENABLED:
                str = "WIFI_STATE_ENABLED";
                break;
            case WifiManager.WIFI_STATE_ENABLING:
                str = "WIFI_STATE_ENABLING";
                break;
        }
        return str;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
