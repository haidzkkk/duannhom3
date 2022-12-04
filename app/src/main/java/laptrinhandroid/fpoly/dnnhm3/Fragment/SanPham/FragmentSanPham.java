package laptrinhandroid.fpoly.dnnhm3.Fragment.SanPham;

 import android.app.AlertDialog;
 import android.os.Bundle;

 import androidx.fragment.app.Fragment;
 import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;

 import android.view.LayoutInflater;
 import android.view.View;
import android.view.ViewGroup;
 import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

 import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

 import laptrinhandroid.fpoly.dnnhm3.Activity.SanPhamActivity;
import laptrinhandroid.fpoly.dnnhm3.Adapter.AdapterSanPham.SanPhamadapter;
import laptrinhandroid.fpoly.dnnhm3.DAO.DAOLoaiSanPham;
 import laptrinhandroid.fpoly.dnnhm3.DAO.DAOSanPham;
 import laptrinhandroid.fpoly.dnnhm3.Entity.LoaiSP;
import laptrinhandroid.fpoly.dnnhm3.Entity.SanPham;

 import laptrinhandroid.fpoly.dnnhm3.R;


public class FragmentSanPham extends Fragment {
    FloatingActionButton floatingActionButton;
    RecyclerView recyclerView;
     private ArrayList<SanPham> list = new ArrayList<>();
    private DAOSanPham daoSanPham;
    private SanPhamadapter adapter;
    private DAOLoaiSanPham daoLoaiSanPham;
 


    public FragmentSanPham() {
        // Required empty public constructor
    }


     @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sanpham, container, false);
//        daoLoaiSanPham = new DAOLoaiSanPham();
//        daoSanPham = GiaoDienChinh.sanPhamq;
        recyclerView = view.findViewById(R.id.rcv_sanpham);
         floatingActionButton = view.findViewById(R.id.flbtn_sanpham);
        try {
            list = (ArrayList<SanPham>) daoSanPham.getListSanPham();
            adapter = new SanPhamadapter(getActivity(), list);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            floatingActionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialogSanPham();
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return view;

    }

    public void dialogSanPham() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getLayoutInflater();
        View v = inflater.inflate(R.layout.dialog_sanpham, null);
        builder.setView(v);
        TextInputEditText ed_tenSanPham, ed_giaban, ed_giavon;
        Spinner spn_loaiSP;
        Button btn_them, btn_huy;
        ed_giaban = v.findViewById(R.id.ed_giaban);
        ed_tenSanPham = v.findViewById(R.id.ed_tensanpham);
        ed_giavon = v.findViewById(R.id.ed_giavon);
        spn_loaiSP = v.findViewById(R.id.spn_danhmuc);
        btn_them = v.findViewById(R.id.btn_themsp);
        btn_huy = v.findViewById(R.id.btn_huy);
        List<String> loaiSP = new ArrayList<>();


        AlertDialog alertDialog = builder.create();
        alertDialog.show();


        try {
            for (LoaiSP listLoaiSP : daoLoaiSanPham.getListLoaiSanPham()) {
                loaiSP.add(listLoaiSP.getMaLoai() + "." + listLoaiSP.getTenLoai());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ArrayAdapter<String> adapterLoaiSP = new ArrayAdapter<>(getActivity(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, loaiSP);
        spn_loaiSP.setAdapter(adapterLoaiSP);

        btn_them.setOnClickListener(v1 -> {
            SanPham sanPham = new SanPham();
            String LoaiSP = (String) spn_loaiSP.getSelectedItem();
            String[] maloai = LoaiSP.split("\\.");

            sanPham.setTenSP(ed_tenSanPham.getText().toString());
            sanPham.setGiaNhap(Float.parseFloat(ed_giavon.getText().toString()));
            sanPham.setGiaBan(Float.parseFloat(ed_giaban.getText().toString()));
            sanPham.setLoaiSP(Integer.parseInt(maloai[0]));
            sanPham.setAnh("a");
            Log.d("sssssssssssss", "dialogSanPham: "+sanPham);
            ((SanPhamActivity) getContext()).addSP(sanPham);
            alertDialog.cancel();
            adapter.notifyDataSetChanged();
        });
        btn_huy.setOnClickListener(v1 -> {
            alertDialog.cancel();
        });


    }

    public void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();


    }
}