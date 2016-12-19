package capaxit.nl.demoapps.sharedelementtransition;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.transition.Slide;
import android.view.View;
import android.widget.ImageView;

import capaxit.nl.demoapps.BaseAppCompatActivity;
import capaxit.nl.demoapps.R;

public class MainActivity extends BaseAppCompatActivity {

    private ImageView thumbnail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(R.string.main_activity_title);
        thumbnail = (ImageView) findViewById(R.id.thumbnail);
        thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                navigateToSecondActivity();
            }
        });
//        disableFadeOnToolStatusAndNavigationBar();
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            final Slide slide = new Slide();
            slide.excludeTarget(android.R.id.statusBarBackground, true);
            slide.excludeTarget(android.R.id.navigationBarBackground, true);
            slide.excludeTarget(R.id.toolbar, true);
            getWindow().setExitTransition(slide);
            getWindow().setEnterTransition(slide);
        }
        findViewById(R.id.navigateToBottomSheetActivity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                startActivity(new Intent(MainActivity.this, BottomSheetActivity.class));
            }
        });
        findViewById(R.id.navigateToSlidingPanalActivity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                startActivity(new Intent(MainActivity.this, SlidingUpPanelActivity.class));
            }
        });
        findViewById(R.id.listWifi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                toListWifiActivity();
            }
        });
    }

    private void toListWifiActivity() {
        startActivity(new Intent(this, ListWifiDetails.class));
    }

    private void navigateToSecondActivity() {
        final ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, thumbnail, "image");
        ActivityCompat.startActivity(this, new Intent(this, SecondActivity.class), options.toBundle());
    }
}
