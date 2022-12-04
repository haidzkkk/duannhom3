package laptrinhandroid.fpoly.dnnhm3.Fragment;

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

import laptrinhandroid.fpoly.dnnhm3.Adapter.AdapterHoaDon.HoadonAdapter;

import laptrinhandroid.fpoly.dnnhm3.Adapter.Spinernhanvien;
import laptrinhandroid.fpoly.dnnhm3.DAO.DAONhanVien;
import laptrinhandroid.fpoly.dnnhm3.DAO.DAOhoadon;
import laptrinhandroid.fpoly.dnnhm3.Entity.HoaDonBan;
import laptrinhandroid.fpoly.dnnhm3.Entity.NhanVien;
import laptrinhandroid.fpoly.dnnhm3.R;
import laptrinhandroid.fpoly.dnnhm3.XuLiNgay.send;


public class haodon1 extends Fragment implements send {

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

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view= inflater.inflate(R.layout.fragment_haodon1,container,false);
       recyclerView=view.findViewById(R.id.Relvhoadon);
        LinearLayoutManager manager= new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);

        daohoadon= new DAOhoadon();
        try {
            list=daohoadon.getAllhoadon();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        hoadonAdapter= new HoadonAdapter(getContext(),list);
        recyclerView.setAdapter(hoadonAdapter);

        return view;

    }

    private void inserthoadon() {

    }
//
    @Override
    public void onResume() {
        super.onResume();
         list.clear();

        try {
            list.addAll(daohoadon.getAllhoadon());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        hoadonAdapter.notifyDataSetChanged();
    }

    @Override
    public void send(String s) {
        hoadonAdapter.getFilter().filter(s);

    }
}