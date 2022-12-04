package laptrinhandroid.fpoly.dnnhm3.Fragment.Kho;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.sql.SQLException;
import java.util.ArrayList;

import laptrinhandroid.fpoly.dnnhm3.Adapter.AdapterHoaDon.HoaDonNhapAdapter;
import laptrinhandroid.fpoly.dnnhm3.DAO.DAOHoaDonNhap;
import laptrinhandroid.fpoly.dnnhm3.Entity.HoaDonNhapKho;
import laptrinhandroid.fpoly.dnnhm3.R;

public class TabListBill_QLKho_Fragment extends Fragment {
    LayoutInflater inflater;
    FloatingActionButton floatingActionButton;
    Context mContext;
    View viewDialogAddPhieuMuon;
    ArrayList<HoaDonNhapKho> arrHDN = new ArrayList<>();
    RecyclerView rcyPm;
    DAOHoaDonNhap daoHoaDonNhap;
    HoaDonNhapAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_list_bill__q_l_kho_, container, false);
        return view;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }
    @Override
    public void onViewCreated(@NonNull  View view, @Nullable  Bundle savedInstanceState) {
        inflater = getLayoutInflater();
        rcyPm= view.findViewById(R.id.recyclerview_lsBill);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mContext);
        rcyPm.setLayoutManager(layoutManager);

        daoHoaDonNhap = new DAOHoaDonNhap();

        try {
            arrHDN = (ArrayList<HoaDonNhapKho>) daoHoaDonNhap.getListHoaDonNhap();
        } catch (SQLException e) {
            e.printStackTrace();
            Log.d("loiii", "onViewCreated: "+e.getMessage());
        }
        adapter = new HoaDonNhapAdapter(mContext, arrHDN);
        rcyPm.setAdapter(adapter);

        Log.e("ListSuze", arrHDN.size() + "");

    }
}