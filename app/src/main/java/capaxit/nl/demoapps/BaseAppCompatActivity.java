package capaxit.nl.demoapps;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.Fade;
import android.transition.Transition;

/**
 * Created by jamiecraane on 21/10/2016.
 */
public class BaseAppCompatActivity extends AppCompatActivity {
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        disableFadeOnToolStatusAndNavigationBar();
    }

    private void disableFadeOnToolStatusAndNavigationBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Transition fade = new Fade();
            getWindow().setExitTransition(fade);
            fade.excludeTarget(android.R.id.statusBarBackground, true);
            fade.excludeTarget(android.R.id.navigationBarBackground, true);
            fade.excludeTarget(R.id.toolbar, true);
            getWindow().setEnterTransition(fade);
        }
    }
}
