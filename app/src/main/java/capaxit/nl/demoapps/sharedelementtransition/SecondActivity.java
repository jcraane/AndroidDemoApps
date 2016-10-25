package capaxit.nl.demoapps.sharedelementtransition;

import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.transition.Slide;
import android.view.MenuItem;

import capaxit.nl.demoapps.BaseAppCompatActivity;
import capaxit.nl.demoapps.R;

public class SecondActivity extends BaseAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        setTitle(R.string.second_activity_title);
        setHomeAsUpEnabled();
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            final Slide slide = new Slide();
            slide.excludeTarget(android.R.id.statusBarBackground, true);
            slide.excludeTarget(android.R.id.navigationBarBackground, true);
            slide.excludeTarget(R.id.toolbar, true);
            getWindow().setExitTransition(slide);
            getWindow().setEnterTransition(slide);
        }

//        disableFadeOnToolStatusAndNavigationBar();
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
