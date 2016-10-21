package capaxit.nl.demoapps.sharedelementtransition;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
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
        thumbnail = (ImageView) findViewById(R.id.thumbnail);
        thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                navigateToSecondActivity();
            }
        });
    }

    private void navigateToSecondActivity() {
        final ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, thumbnail, "image");
        ActivityCompat.startActivity(this, new Intent(this, SecondActivity.class), options.toBundle());
    }
}
