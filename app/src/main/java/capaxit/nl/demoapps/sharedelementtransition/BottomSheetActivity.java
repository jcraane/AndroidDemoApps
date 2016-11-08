package capaxit.nl.demoapps.sharedelementtransition;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import capaxit.nl.demoapps.BaseAppCompatActivity;
import capaxit.nl.demoapps.R;

public class BottomSheetActivity extends BaseAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_sheet);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setHomeAsUpEnabled();
        setSupportActionBar(toolbar);
    }
}
