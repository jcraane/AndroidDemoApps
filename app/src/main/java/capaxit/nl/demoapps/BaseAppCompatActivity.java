package capaxit.nl.demoapps;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.transition.Transition;
import android.view.MenuItem;

/**
 * Created by jamiecraane on 21/10/2016.
 */
public class BaseAppCompatActivity extends AppCompatActivity {
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        final Transition transition = TransitionInflater.from(this).inflateTransition(R.transition.sh_default);
//        getWindow().setSharedElementExitTransition(transition);
//        getWindow().setSharedElementReenterTransition(transition);
//        getWindow().setSharedElementReturnTransition(transition);
//        getWindow().setSharedElementEnterTransition(transition);
    }

    @Override
    public void setContentView(@LayoutRes final int layoutResID) {
        super.setContentView(layoutResID);
        attachAndConfigureToolBar();
    }

    protected void disableFadeOnToolStatusAndNavigationBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Transition fade = new Fade();
            fade.excludeTarget(android.R.id.statusBarBackground, true);
            fade.excludeTarget(android.R.id.navigationBarBackground, true);
            fade.excludeTarget(R.id.toolbar, true);
            getWindow().setExitTransition(fade);
            getWindow().setEnterTransition(fade);
//            getWindow().setSharedElementExitTransition(fade);
//            getWindow().setSharedElementReenterTransition(fade);
//            getWindow().setSharedElementReturnTransition(fade);
//            getWindow().setSharedElementEnterTransition(fade);
        }
    }

    protected final void setHomeAsUpEnabled() {
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_white_24dp);
    }

    protected void attachAndConfigureToolBar() {
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                ActivityCompat.finishAfterTransition(this);
//                    finish();
                return true;
        }

        return false;
    }
}
