package laptrinhandroid.fpoly.dnnhm3.Activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import laptrinhandroid.fpoly.dnnhm3.Adapter.AdapterHoaDon.Adapterchitiet;
import laptrinhandroid.fpoly.dnnhm3.Adapter.AdapterHoaDon.Spinerkhachhang;

import laptrinhandroid.fpoly.dnnhm3.Adapter.Spinernhanvien;
import laptrinhandroid.fpoly.dnnhm3.ConvertImg;
import laptrinhandroid.fpoly.dnnhm3.DAO.DAONhanVien;
import laptrinhandroid.fpoly.dnnhm3.DAO.DAOSanPham;
import laptrinhandroid.fpoly.dnnhm3.DAO.DAOhoadon;
import laptrinhandroid.fpoly.dnnhm3.DAO.Daochitiethoadon;
import laptrinhandroid.fpoly.dnnhm3.DAO.Daokhachhang;
import laptrinhandroid.fpoly.dnnhm3.Entity.ChiTietHoaDonban;
import laptrinhandroid.fpoly.dnnhm3.Entity.HoaDonBan;
import laptrinhandroid.fpoly.dnnhm3.Entity.KhachHang;
import laptrinhandroid.fpoly.dnnhm3.Entity.NhanVien;
import laptrinhandroid.fpoly.dnnhm3.Entity.SanPham;
import laptrinhandroid.fpoly.dnnhm3.R;

public class Chitiethoadon extends AppCompatActivity {
    Context context;
    Toolbar toolbar;
    Adapterchitiet adapterchitiet;
    RecyclerView recyclerView;
    //list
    List<HoaDonBan> hoaDonBanList = new ArrayList<>();
    List<KhachHang> listkh;
    List<NhanVien> listnv = new ArrayList<>();
    List<SanPham> listsp;
    List<ChiTietHoaDonban> listchitiethoadon;
    //dao
    DAOhoadon daOhoadon;
    Daokhachhang daokhachhang;
    DAONhanVien daoNhanVien;
    DAOSanPham daoSanPham;
    Daochitiethoadon daochitiethoadon;
    //spiner
    Spinernhanvien spinernhanvien;
    Spinerkhachhang spinerkhachhang;
    Spinner spinner1, spinner2;
    long l = 0;
    long f=0;
    TextView date;
    int makh, manv, soluongsp, masp, mahd, position;
    String namesp;
    int soluona,soluongb,tongsl;
    int positon;
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
  boolean check= false;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitiethoadon);
        toolbar = findViewById(R.id.toolbarCTHD);
        TextView tongsanpham =findViewById(R.id.CTHD_tongsanpham);
        Button Thanhtoan = findViewById(R.id.CTHD_thanhtoan);
        TextView tontien = findViewById(R.id.CTHD_tongtien);
        TextView khachhang = findViewById(R.id.CTHD_TaoKhachhang);
        date = findViewById(R.id.CTHD_DATE);
        date.setText(format.format(new Date()));
        spinner1 = findViewById(R.id.spinernhanvienhd);
        spinner2 = findViewById(R.id.CTHD_khachhang);
        toolbar.setTitle("xac nhan don hang ");
        daochitiethoadon = new Daochitiethoadon();
        daOhoadon = new DAOhoadon();
        daokhachhang = new Daokhachhang();
        daoSanPham= new DAOSanPham();
        try {
            listchitiethoadon = daochitiethoadon.getAllchitiethoadon();
            hoaDonBanList = daOhoadon.getAllhoadon();
            listkh = daokhachhang.getAllkhachang();
            listsp = daoSanPham.getAllsanpham();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        recyclerView = findViewById(R.id.Recychodon);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        //spinernhanvien
        listnv = new ArrayList<NhanVien>();
        daoNhanVien = new DAONhanVien();
        try {
            listnv = daoNhanVien.getAllNhanvien();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        spinernhanvien = new Spinernhanvien(getApplicationContext(), listnv);
        spinner1.setAdapter(spinernhanvien);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                manv = listnv.get(i).getMaNv();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //spiner khachhang
        listkh = new ArrayList<KhachHang>();
        daokhachhang = new Daokhachhang();
        try {
            listkh = daokhachhang.getAllkhachang();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        spinerkhachhang = new Spinerkhachhang(getApplicationContext(), listkh);
        spinner2.setAdapter(spinerkhachhang);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                makh = listkh.get(i).getMaKhach();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
//       listsp = new ArrayList<>();
//        soluongb=listsp.get(positon).getSoLuong();



        Log.d("ppppp", "onClick: "+soluongb);
        List<SanPham> sanPhams = (List<SanPham>) getIntent().getSerializableExtra("key");
        adapterchitiet = new Adapterchitiet(getApplicationContext(), sanPhams);
        recyclerView.setAdapter(adapterchitiet);
        for (SanPham sanPham : sanPhams) {
            l+= sanPham.getSoLuong()*sanPham.getGiaBan();
            f+=sanPham.getSoLuong();
        }
       tongsanpham.setText(f+"");
        String tongtien = l + "";
        tontien.setText(tongtien);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_chevron_left_24);
        KhachHang kh = new KhachHang();

        khachhang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertkhachhang();
            }
        });

        Thanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



///////////////////////////////////////
                //hoa don ban
                HoaDonBan hoaDonBan = new HoaDonBan();
                hoaDonBan.setMaNV(manv);
                hoaDonBan.setMaKH(makh);
                hoaDonBan.setNgayBan(new Date());
                hoaDonBan.setTongTien(Float.parseFloat(tongtien));
                try {
                    if (daOhoadon.Insert(hoaDonBan)) {
                        check=true;
                        hoaDonBanList.clear();
                        daOhoadon = new DAOhoadon();

                        try {
                            hoaDonBanList = daOhoadon.getAllhoadon();

                        } catch (SQLException e) {
                            e.printStackTrace();
                            Log.d("ssssssss", "onClick: " + e.getMessage());
                        }
                    }else {
                        check=false;
                    }
////////////////////////
                        for (SanPham sanPham : sanPhams) {
                            ChiTietHoaDonban chitiethoadon = new ChiTietHoaDonban();
                            chitiethoadon.setMaHD(hoaDonBanList.get(hoaDonBanList.size()-1).getMaHDBan());
                            chitiethoadon.setMaSp(sanPham.getMaSP());
                            chitiethoadon.setTenSP(sanPham.getTenSP());
                            chitiethoadon.setAnh(String.valueOf(ConvertImg.convertBaseStringToBitmap(sanPham.getAnh())));
                            chitiethoadon.setSoLuong(sanPham.getSoLuong());
                            chitiethoadon.setThanhTien(Float.parseFloat((sanPham.getGiaBan()*sanPham.getSoLuong())+""));
                            if (daochitiethoadon.Insertchitiethoadon(chitiethoadon)) {
                                check=true;

                                listchitiethoadon.clear();
                                daochitiethoadon = new Daochitiethoadon();
                                try {
                                    listchitiethoadon = (ArrayList<ChiTietHoaDonban>) daochitiethoadon.getAllchitiethoadon();
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                    Log.d("ssssssss", "onClick: " + e.getMessage());

                                }
                            } else {
                                check=false;
                            }
                        }

                } catch (SQLException e) {
                    e.printStackTrace();
                    Log.d("ssssssss", "onClick: " + e.getMessage());
                }
///////////////////////////////////
                String id;
                for (SanPham sanPham : sanPhams) {
                    SanPham sanPham1 = new SanPham();

                    id = String.valueOf(sanPham.getMaSP());
                    sanPham1 = daoSanPham.getIdSP(id);
                    sanPham.setSoLuong(sanPham1.getSoLuong()-sanPham.getSoLuong());
                    sanPham.setSoLuongDaBan(sanPham1.getSoLuongDaBan() + sanPham.getSoLuong());
                    Log.d("updatesanpham", "onClick: " + sanPham.getSoLuong());
                    try {
                        if (daoSanPham.updateSanPham(sanPham)) {
                            check=true;
                        } else {
                            check=false;
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                }
                Checkinsertdathang(check);
            }
        });

    }

    private void Checkinsertdathang(boolean check1) {
        if(check1==true){
            Toast.makeText(Chitiethoadon.this, "dat hang thanh cong", Toast.LENGTH_SHORT).show();
            finish();

        }else {
            Toast.makeText(Chitiethoadon.this, "dat hang that bai", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();

        }
        return super.onOptionsItemSelected(item);

    }


    @SuppressLint("MissingInflatedId")
    public void insertkhachhang() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialogkhachhang, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();
        EditText txtname, txtdiachi, txtSdt;
        ImageView txtimg;
        Button btnthem, btngiaosau;


        txtname = view.findViewById(R.id.DIGLOG_name);
        txtdiachi = view.findViewById(R.id.DIGLOG_diachi);
        txtSdt = view.findViewById(R.id.DIGLOG_SDT);
        txtimg = view.findViewById(R.id.DIGLOG_Img);
        btnthem = view.findViewById(R.id.DIALOG_Save);
        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int check=1;
                if(txtname.getText().toString().isEmpty()){
                    Toast.makeText(Chitiethoadon.this, "Họ tên không được để trống", Toast.LENGTH_SHORT).show();
                }else if(txtdiachi.getText().toString().isEmpty()){
                    Toast.makeText(Chitiethoadon.this, " địa chỉ không được để trống", Toast.LENGTH_SHORT).show();
                }else if(txtSdt.getText().toString().isEmpty()){
                    Toast.makeText(Chitiethoadon.this, "Sđt không được để trống", Toast.LENGTH_SHORT).show();
                }else if(check>0){
                    KhachHang khachHang = new KhachHang();

                    khachHang.setHoTen(txtname.getText().toString());
                    khachHang.setDiaChi(txtdiachi.getText().toString());
                    khachHang.setSoDT(txtSdt.getText().toString());
                    try {
                        if (daokhachhang.addKhachhang(khachHang)) {
                            Log.e("sssss", "onClick: ");
                            Toast.makeText(Chitiethoadon.this, "thanh cong", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                            listsp.clear();
                            daokhachhang = new Daokhachhang();
                            try {
                                listkh = (ArrayList<KhachHang>) daokhachhang.getAllkhachang();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }

                        } else {
                            Toast.makeText(Chitiethoadon.this, "khong thanh cong" +
                                    "", Toast.LENGTH_SHORT).show();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }



            }
        });
//
    }
}
