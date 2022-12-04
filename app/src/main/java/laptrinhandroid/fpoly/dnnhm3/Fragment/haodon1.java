package laptrinhandroid.fpoly.dnnhm3.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import laptrinhandroid.fpoly.dnnhm3.Activity.hoadon11111;
import laptrinhandroid.fpoly.dnnhm3.Adapter.AdapterHoaDon.HoadonAdapter;
import laptrinhandroid.fpoly.dnnhm3.Adapter.Spinernhanvien;
import laptrinhandroid.fpoly.dnnhm3.DAO.DAONhanVien;
import laptrinhandroid.fpoly.dnnhm3.DAO.DAOhoadon;
import laptrinhandroid.fpoly.dnnhm3.Entity.HoaDonBan;
import laptrinhandroid.fpoly.dnnhm3.Entity.NhanVien;
import laptrinhandroid.fpoly.dnnhm3.R;

public class haodon1 extends Fragment {

    RecyclerView recyclerView;
    FloatingActionButton faa;
    DAOhoadon daohoadon;
    HoadonAdapter hoadonAdapter;
    List<HoaDonBan> list;
    List<NhanVien>listnhanvien;
    DAONhanVien daoNhanVien;
    Spinner spinnermaNV,spinnermaKH;
    SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd");
    Spinernhanvien spinernhanvien;
    int maMV;
    public haodon1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view= inflater.inflate(R.layout.fragment_haodon1,container,false);
       recyclerView=view.findViewById(R.id.Relvhoadon);
        LinearLayoutManager manager= new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);
        faa=view.findViewById(R.id.floating1);
        daohoadon= new DAOhoadon();
        try {
            list=daohoadon.getListHoadonban();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        hoadonAdapter= new HoadonAdapter(getContext(),list);
        recyclerView.setAdapter(hoadonAdapter);
        faa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getActivity(), hoadon11111.class);
                startActivity(intent);

            }
        });
        return view;

    }

    private void inserthoadon() {

//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//        LayoutInflater inflater = getLayoutInflater();
//        View view = inflater.inflate(R.layout.inserthoadon, null);
//        builder.setView(view);
//        Dialog dialog = builder.create();
//        dialog.show();
//        TextView txtdate, txttongtien;
//        Button btnthem, btngiaosau;
//
//
//        spinnermaNV = view.findViewById(R.id.spnnhanvien);
//        spinnermaKH = view.findViewById(R.id.spnkhachhang);
//        txtdate = view.findViewById(R.id.txtdate);
//        txttongtien = view.findViewById(R.id.txttongtien);
//        txtdate.setText("ngaythue:" + format.format(new Date()));
//        btnthem = view.findViewById(R.id.hdbannhanh);
//
//        listnhanvien = new ArrayList<>();
//        daoNhanVien = new DAONhanVien(getActivity());
//        try {
//            listnhanvien=(List<NhanVien>) daoNhanVien.getListNhanVien();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        spinernhanvien = new Spinernhanvien(getContext(),listnhanvien);
//        spinnermaNV.setAdapter(spinernhanvien);
//
//        spinnermaNV.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                maMV = listnhanvien.get(i).getMaNv();
////                String nameloaisach = listloaisach.get(i).getTenloai();
//                Toast.makeText(getContext(), "chon:"+listnhanvien.get(i).getHoTen(), Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
//
//        btnthem.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                HoaDonBan hoaDonBan = new HoaDonBan();
//                hoaDonBan.setTongTien(Float.parseFloat(txttongtien.getText().toString()));
//
//                try {
//                    if (daohoadon.Insert(hoaDonBan)) {
//                        Toast.makeText(getContext(), "thanh cong", Toast.LENGTH_SHORT).show();
//                         dialog.dismiss();
////                        listsach.clear();
////                        sachdao = new sachdao(getContext());
////                        listsach = (List<sach>)sachdao.getAllsach();
////                        sachadapter=new sachadapter(getActivity(),listsach);
////                        rcv.setAdapter(sachadapter);
////                        sachadapter.notifyDataSetChanged();
//                    }
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//        });

    }

}