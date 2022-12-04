package laptrinhandroid.fpoly.dnnhm3.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import laptrinhandroid.fpoly.dnnhm3.Adapter.AdapterSanPham.ViewSanPhamAdapter;
import laptrinhandroid.fpoly.dnnhm3.DAO.DAOSanPham;
import laptrinhandroid.fpoly.dnnhm3.Entity.SanPham;
import laptrinhandroid.fpoly.dnnhm3.R;
import laptrinhandroid.fpoly.dnnhm3.addsp;

public class SanPhamActivity extends AppCompatActivity implements addsp {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    Toolbar toolbar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_san_pham);

        tabLayout = findViewById(R.id.tablayout_sanpham);
        viewPager = findViewById(R.id.view_pager);
        toolbar=findViewById(R.id.toolBar_sanpham);

        ViewSanPhamAdapter viewSanPhamAdapter= new ViewSanPhamAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(viewSanPhamAdapter);

        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_sanpham,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.search:
                Toast.makeText(getApplicationContext(),"123",Toast.LENGTH_LONG).show();
                break;
            case R.id.add:
                Toast.makeText(getApplicationContext(),"thêm thành công",Toast.LENGTH_LONG).show();
                break;
            case R.id.exit:
                finish();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void addSP(SanPham sanPham) {
       if(new DAOSanPham(). addSanPham( sanPham)){
           Toast.makeText(this, "ok", Toast.LENGTH_SHORT).show();
       }
    }
}