package capaxit.nl.demoapps.recognition;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import capaxit.nl.demoapps.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.ActivityRecognition;
import com.google.android.gms.location.DetectedActivity;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import static android.R.attr.type;

public class RecognitionActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private GoogleApiClient googleApiClient;

    private TextView currentActivities;
    private TextView state;
    private boolean started;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recognition);
        findViewById(R.id.start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                startActivityRecognition();
            }
        });
        state = (TextView) findViewById(R.id.state);
        currentActivities = (TextView) findViewById(R.id.currentActivities);
    }

    private void startActivityRecognition() {
        state.setText("Connecting");
        googleApiClient.connect();
    }

    @Override
    protected void onStart() {
        super.onStart();
        googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(ActivityRecognition.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Subscribe
    public void onEvent(final DetectedActivitiesEvent event) {
        final StringBuilder sb = new StringBuilder();
        if (event.getDetectedActivities().isEmpty()) {
            sb.append("No activities detected");
        }
        for (final DetectedActivity detectedActivity : event.getDetectedActivities()) {
            sb.append("activity: ");
            sb.append(getActivityString(detectedActivity.getType())).append(" ");
            sb.append(", confidence: ");
            sb.append(detectedActivity.getConfidence());
        }
        currentActivities.setText(sb.toString());
    }

    @Override
    public void onConnected(@Nullable final Bundle bundle) {
        System.out.println("RecognitionActivity.onConnected");
        state.setText("Started");
        started = true;
        final PendingIntent pendingIntent = PendingIntent.getService(this, 0, new Intent(this, DetectedActivitiesIntentService.class), PendingIntent.FLAG_UPDATE_CURRENT);
        ActivityRecognition.ActivityRecognitionApi.requestActivityUpdates(googleApiClient, 0, pendingIntent);
    }

    @Override
    public void onConnectionSuspended(final int i) {
        System.out.println("RecognitionActivity.onConnectionSuspended");
    }

    @Override
    public void onConnectionFailed(@NonNull final ConnectionResult connectionResult) {
        System.out.println("RecognitionActivity.onConnectionFailed");
    }

    private String getActivityString(final int detectedActivity) {
        switch (detectedActivity) {
            case DetectedActivity.IN_VEHICLE:
                return getString(R.string.activity_in_vehicle);
            case DetectedActivity.ON_BICYCLE:
                return getString(R.string.activity_on_bicycle);
            case DetectedActivity.ON_FOOT:
                return getString(R.string.activity_on_foot);
            case DetectedActivity.RUNNING:
                return getString(R.string.activity_running);
            case DetectedActivity.STILL:
                return getString(R.string.activity_still);
            case DetectedActivity.TILTING:
                return getString(R.string.activity_tilting);
            case DetectedActivity.UNKNOWN:
                return getString(R.string.activity_unknown);
            case DetectedActivity.WALKING:
                return getString(R.string.activity_walking);
            default:
                return getString(R.string.activity_unidentifiable_activity);
        }
    }
}
