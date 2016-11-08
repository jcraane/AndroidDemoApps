package capaxit.nl.demoapps.sharedelementtransition;

import android.os.Bundle;
import android.transition.Slide;

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
}
