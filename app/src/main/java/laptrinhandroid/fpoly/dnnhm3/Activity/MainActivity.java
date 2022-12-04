package laptrinhandroid.fpoly.dnnhm3.Activity;


import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.firebase.messaging.FirebaseMessaging;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.util.List;

import laptrinhandroid.fpoly.dnnhm3.Adapter.AdpaterNhanVien.AdapterPagerNhanVien;
import laptrinhandroid.fpoly.dnnhm3.Entity.NhanVien;
import laptrinhandroid.fpoly.dnnhm3.R;
import laptrinhandroid.fpoly.dnnhm3.notification.FcmNotificationsSender;


public class MainActivity extends AppCompatActivity {


    List<CalendarDay> list0;
    List<CalendarDay> list1;
    List<CalendarDay> list2;
    TabLayout layout;
    ViewPager2 viewPager2;
    MaterialCalendarView calendarView;

    @SuppressLint("MissingInflatedId")
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layout = findViewById(R.id.tabLayout);
        viewPager2 = findViewById(R.id.viewPager2);
        setToolBar();
        Intent intent = getIntent();
        NhanVien nhanVien = (NhanVien) intent.getSerializableExtra("nv");
         viewPager2.setAdapter(new AdapterPagerNhanVien(this, nhanVien));

        new TabLayoutMediator(layout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                if (position == 0) {
                    tab.setText("Bảng công");
                } else {
                    tab.setText("Bảng lương");
                }
            }
        }).attach();

//if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
//Intent intent = new Intent();
//intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
//intent.setType("image/*");
//activityResultLauncher.launch(Intent.createChooser(intent, "Select Picture"));
//}
//        getCong();

    }

    @SuppressLint("RestrictedApi")
    public void setToolBar() {
        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);//set icon tren toolbar
        actionbar.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24);//set icon menu
    }


    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if (result.getResultCode() == RESULT_OK) {
            // imageView.setImageBitmap(ConvertImg.convertBaseStringToBitmap(ConvertImg.convertBitmapToBaseString((Bitmap) result.getData().getExtras().get("data"))));

        }
    });

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_nhan_vien, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}
