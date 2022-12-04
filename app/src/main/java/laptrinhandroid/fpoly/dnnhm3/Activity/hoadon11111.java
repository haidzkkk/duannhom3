package laptrinhandroid.fpoly.dnnhm3.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;

import java.sql.SQLException;
import java.util.List;

import laptrinhandroid.fpoly.dnnhm3.Adapter.AdapterSanPham.Sanphamadpter;
import laptrinhandroid.fpoly.dnnhm3.DAO.DAOSanPham;
import laptrinhandroid.fpoly.dnnhm3.Entity.SanPham;
import laptrinhandroid.fpoly.dnnhm3.R;

public class hoadon11111 extends AppCompatActivity {
    RecyclerView recyclerView;
Toolbar toolbar;
    DAOSanPham daoSanPham;
    Sanphamadpter sanphamadpter;
    List<SanPham> list;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoadon11111);
        recyclerView=findViewById(R.id.rcvnhanvien);
        toolbar=findViewById(R.id.toolbar2);
        toolbar.setTitle("don hang");
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_chevron_left_24);

        GridLayoutManager gridLayoutManager= new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(gridLayoutManager);
        daoSanPham= new DAOSanPham();
        try {
            list= daoSanPham.getListSanPham();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        sanphamadpter = new Sanphamadpter(getApplication(),list);
        recyclerView.setAdapter(sanphamadpter);


    }



}