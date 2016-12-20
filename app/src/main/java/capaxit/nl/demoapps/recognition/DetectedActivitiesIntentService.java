package capaxit.nl.demoapps.recognition;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import com.google.android.gms.location.ActivityRecognitionResult;
import com.google.android.gms.location.DetectedActivity;
import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by jamiecraane on 20/12/2016.
 */

public class DetectedActivitiesIntentService extends IntentService {
    private static final String TAG = DetectedActivitiesIntentService.class.getSimpleName();
    public DetectedActivitiesIntentService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(@Nullable final Intent intent) {
        ActivityRecognitionResult result = ActivityRecognitionResult.extractResult(intent);
        final List<DetectedActivity> detectedActivities = result.getProbableActivities();
        EventBus.getDefault().post(new DetectedActivitiesEvent(detectedActivities));
    }
}
