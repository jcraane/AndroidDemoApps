package capaxit.nl.demoapps.recognition;

import com.google.android.gms.location.DetectedActivity;

import java.util.List;

/**
 * Created by jamiecraane on 20/12/2016.
 */

public final class DetectedActivitiesEvent {
    private final List<DetectedActivity> detectedActivities;

    public DetectedActivitiesEvent(final List<DetectedActivity> detectedActivities) {
        this.detectedActivities = detectedActivities;
    }

    public List<DetectedActivity> getDetectedActivities() {
        return detectedActivities;
    }
}
