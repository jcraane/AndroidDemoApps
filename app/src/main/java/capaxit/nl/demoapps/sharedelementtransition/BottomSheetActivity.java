package capaxit.nl.demoapps.sharedelementtransition;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import capaxit.nl.demoapps.BaseAppCompatActivity;
import capaxit.nl.demoapps.R;

public class BottomSheetActivity extends BaseAppCompatActivity {

    private CustomBottomSheetBehavior<View> bottomSheetBehavior;
    private BottomSheetVisibilityState state = BottomSheetVisibilityState.SMALL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_sheet);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setHomeAsUpEnabled();
        setSupportActionBar(toolbar);

        final View bottomSheet = findViewById(R.id.bottomSheet);
        toolbar.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                int statusBarHeight = 0;
                int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
                if (resourceId > 0) {
                    statusBarHeight = getResources().getDimensionPixelSize(resourceId);
                }

                toolbar.getViewTreeObserver().removeOnPreDrawListener(this);
                final ViewGroup.LayoutParams params = bottomSheet.getLayoutParams();
                DisplayMetrics metrics = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(metrics);
                params.height = metrics.heightPixels - toolbar.getLayoutParams().height - statusBarHeight;
                bottomSheet.setLayoutParams(params);
                return true;
            }
        });
        bottomSheetBehavior = (CustomBottomSheetBehavior<View>) CustomBottomSheetBehavior.from(bottomSheet);
        bottomSheetBehavior.setAllowUserDragging(false);
        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull final View bottomSheet, final int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_EXPANDED:
                        System.out.println("Expanded");

                        break;
                    case BottomSheetBehavior.STATE_COLLAPSED:
                        System.out.println("Collapsed");
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull final View bottomSheet, final float slideOffset) {

            }
        });
    }

    private enum BottomSheetVisibilityState {
        SMALL, HALF, FULL;
    }
}
