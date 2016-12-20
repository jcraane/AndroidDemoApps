package capaxit.nl.demoapps.sharedelementtransition;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import capaxit.nl.demoapps.R;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ListWifiDetails extends AppCompatActivity {
    public static final int WIFI_SCANNING_REQUEST_CODE = 101;
    private RecyclerView wifiDetails;
    private WifiManager wifiManager;
    private BroadcastReceiver wifiReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(final Context context, final Intent intent) {
            System.out.println("ON receive");
            if (intent.getAction().equals(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION)) {
                List<ScanResult> scanResults = wifiManager.getScanResults();
                wifiDetails.setAdapter(new ScanResultRecyclerAdapter(scanResults));
            }
        }
    };
    private Timer wifiScanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_wifi_details);
        wifiDetails = (RecyclerView) findViewById(R.id.wifiDetails);
        wifiDetails.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    private void requestWifiPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, WIFI_SCANNING_REQUEST_CODE);
            }
            // Get the result in onRequestPermissionsResult(int, String[], int[])
        } else {
            startWifiScanning();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull final String[] permissions, @NonNull final int[] grantResults) {
        if (requestCode == WIFI_SCANNING_REQUEST_CODE) {
            for (int grantResult : grantResults) {
                if (grantResult != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
            }
            startWifiScanning();
        }
    }

    private void startWifiScanning(){
        wifiDetails = (RecyclerView) findViewById(R.id.wifiDetails);
        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        final List<ScanResult> results = wifiManager.getScanResults();
        wifiDetails.setAdapter(new ScanResultRecyclerAdapter(results));
        registerReceiver(wifiReceiver, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        wifiScanner = new Timer();
        wifiScanner.schedule(new TimerTask() {
            @Override
            public void run() {
                wifiManager.startScan();
            }
        }, 15000, 15000);
    }

    @Override
    protected void onResume() {
        super.onResume();
        requestWifiPermissions();
    }

    @Override
    protected void onPause() {
        unregisterReceiver(wifiReceiver);
        if (wifiScanner != null) {
            wifiScanner.cancel();
            wifiScanner.purge();
        }
        super.onPause();
    }

    static class ScanResultRecyclerAdapter extends RecyclerView.Adapter<ScanResultRecyclerAdapter.ScanResultHolder> {
        private final List<ScanResult> results;

        ScanResultRecyclerAdapter(final List<ScanResult> results) {
            Collections.sort(results, new Comparator<ScanResult>() {
                @Override
                public int compare(final ScanResult scanResult, final ScanResult t1) {
                    return t1.level - scanResult.level;
                }
            });
            this.results = results;
        }

        @Override
        public ScanResultHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
            final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.scanresult_row, null);
            return new ScanResultHolder(view);
        }

        @Override
        public void onBindViewHolder(final ScanResultHolder holder, final int position) {
            final ScanResult scanResult = results.get(position);
            holder.ssid.setText(scanResult.SSID);
            final int level = WifiManager.calculateSignalLevel(scanResult.level, 5);
            holder.level.setText("" + level);
            holder.macAddress.setText(scanResult.BSSID);
        }

        @Override
        public int getItemCount() {
            return results.size();
        }

        static class ScanResultHolder extends RecyclerView.ViewHolder {
            TextView ssid;
            TextView macAddress;
            TextView level;

            public ScanResultHolder(final View itemView) {
                super(itemView);
                ssid = (TextView) itemView.findViewById(R.id.ssid);
                level = (TextView) itemView.findViewById(R.id.level);
                macAddress = (TextView) itemView.findViewById(R.id.macAddress);
            }
        }
    }
}
