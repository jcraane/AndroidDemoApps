package capaxit.nl.demoapps.sharedelementtransition;

import android.content.Context;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by jamiecraane on 08/11/2016.
 */

public class CustomNestedScrollView extends NestedScrollView {
    public CustomNestedScrollView(final Context context) {
        super(context);
    }

    public CustomNestedScrollView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(final MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }
}
