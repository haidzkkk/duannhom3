package laptrinhandroid.fpoly.dnnhm3.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.sql.SQLException;
import java.util.List;

import laptrinhandroid.fpoly.dnnhm3.Adapter.AdapterNotification.AdapterThongBaoFromAdmin;
import laptrinhandroid.fpoly.dnnhm3.Adapter.AdapterNotification.AdapterThongBaoFromNhanVien;
import laptrinhandroid.fpoly.dnnhm3.DAO.ThongBaoAdminDAO;
import laptrinhandroid.fpoly.dnnhm3.DAO.ThongBaoNVDAO;
import laptrinhandroid.fpoly.dnnhm3.Entity.ChamCong;
import laptrinhandroid.fpoly.dnnhm3.Entity.NhanVien;
import laptrinhandroid.fpoly.dnnhm3.Entity.ThongBaoAdmin;
import laptrinhandroid.fpoly.dnnhm3.Entity.ThongBaoNV;
import laptrinhandroid.fpoly.dnnhm3.R;

public class ThongBao extends AppCompatActivity {
    RecyclerView recyclerView;
    public static ThongBaoNVDAO thongBaoNVDAO = new ThongBaoNVDAO();
    public static ThongBaoAdminDAO thongBaoAdminDAO = new ThongBaoAdminDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_bao);
        recyclerView = findViewById(R.id.rcy);
        Intent intent = getIntent();
        NhanVien mNV = (NhanVien) intent.getSerializableExtra("NV");
        recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        try {
            if (mNV != null) {
                List<ThongBaoNV> thongBaoNVS = thongBaoNVDAO.getListNotiNhanVien(mNV.getMaNv());
                AdapterThongBaoFromNhanVien adapterThongBao = new AdapterThongBaoFromNhanVien(mNV);
                recyclerView.setAdapter(adapterThongBao);
                if (thongBaoNVS != null) {
                    adapterThongBao.setData(thongBaoNVS);
                }
            } else {
                List<ThongBaoAdmin> listThongBaoAdmin = thongBaoAdminDAO.getListThongBaoAdmin();
                AdapterThongBaoFromAdmin adapterThongBaoFromAdmin = new AdapterThongBaoFromAdmin(mNV,this);

                recyclerView.setAdapter(adapterThongBaoFromAdmin);

                if (listThongBaoAdmin != null) {
                    adapterThongBaoFromAdmin.setData(listThongBaoAdmin);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}