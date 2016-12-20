package capaxit.nl.demoapps.wifi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;

import java.util.List;

/**
 * Created by jamiecraane on 19/12/2016.
 */

public class WifiBroadCastReceiver extends BroadcastReceiver {
    private final WifiManager wifiManager;

    public WifiBroadCastReceiver(final WifiManager wifiManager) {
        this.wifiManager = wifiManager;
    }

    @Override
    public void onReceive(final Context context, final Intent intent) {
        if (intent.getAction().equals(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION)) {
            List<ScanResult> scanResults = wifiManager.getScanResults();
            // add your logic here
        }
    }
}
