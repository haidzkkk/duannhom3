package laptrinhandroid.fpoly.dnnhm3.Activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;


import laptrinhandroid.fpoly.dnnhm3.Adapter.AdapterHoaDon.viewadapter;
import laptrinhandroid.fpoly.dnnhm3.R;

public class MainActivityhoadon extends AppCompatActivity {
    ViewPager2 viewPager;
    TabLayout tabLayout;
    viewadapter viewadapter;
    Toolbar toolbar;
    ImageView imageView1, imageView2, img3;
    EditText editText;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activityhoadon);
        viewPager = findViewById(R.id.viewpager2);
        tabLayout = findViewById(R.id.tablayout);
        toolbar = findViewById(R.id.toobar1);
        toolbar.setTitle("don hang");
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_chevron_left_24);
        editText = findViewById(R.id.edttimkiem);
        viewadapter = new viewadapter(this);
        viewPager.setAdapter(viewadapter);
        new TabLayoutMediator(tabLayout, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0:
                        tab.setText("don hang");
                        break;
                    case 1:
                        tab.setText("dang cho");
                        break;
                    case 2:
                        tab.setText("dang theo doi");
                        break;
                }
            }
        }).attach();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.item, menu);
        MenuItem menuItem = menu.findItem(R.id.menuseach);
        final android.widget.SearchView searchView = (android.widget.SearchView) MenuItemCompat.getActionView(menuItem);
        return super.onCreateOptionsMenu(menu);
    }


}