package laptrinhandroid.fpoly.dnnhm3.Activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import laptrinhandroid.fpoly.dnnhm3.Adapter.AdapterKho.ViewPagerAdapter;
import laptrinhandroid.fpoly.dnnhm3.R;

public class QuanLyKho extends AppCompatActivity {
    TabLayout tabLayout_kho;
    ViewPager2 viewPager_kho;
    ViewPagerAdapter adapter;
    Toolbar toolbar_kho;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_kho);
        tabLayout_kho=findViewById(R.id.tablayout);
        viewPager_kho=findViewById(R.id.viewpager);
        adapter=new ViewPagerAdapter(this);
        viewPager_kho.setAdapter(adapter);
        toolbar_kho=findViewById(R.id.toolBar_kho);
        toolbar_kho.setTitleTextColor(Color.WHITE);
        toolbar_kho.setTitle("Kho hàng");
        setSupportActionBar(toolbar_kho);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_ios_24);


        new TabLayoutMediator(tabLayout_kho, viewPager_kho, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position){
                    case 0:tab.setText("Tồn kho");
                        break;
                    case 1:tab.setText("Đơn Nhập");
                        break;
                }
            }
        }).attach();
    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==android.R.id.home){
            finish();
        }
        return true;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.item_kho, menu);
        MenuItem searchViewItem = menu.findItem(R.id.app_bar_search);
        final android.widget.SearchView searchView = (android.widget.SearchView) MenuItemCompat.getActionView(searchViewItem);

        return super.onCreateOptionsMenu(menu);
    }
}

