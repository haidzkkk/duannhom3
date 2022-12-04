package laptrinhandroid.fpoly.dnnhm3.Fragment.NhanVien;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import laptrinhandroid.fpoly.dnnhm3.Activity.GiaoDienChinh;
import laptrinhandroid.fpoly.dnnhm3.Entity.BangLuong;
import laptrinhandroid.fpoly.dnnhm3.Entity.ChamCong;
import laptrinhandroid.fpoly.dnnhm3.Entity.NhanVien;
import laptrinhandroid.fpoly.dnnhm3.R;

public class FragmentLuong extends Fragment {
    View view;
    TextView txtSoGio, txtNgayThuong, txtChuNhat, txtLuongCb, txtUngLuong, txtSoTienThuong, txtTong;
    Spinner spinner;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_luong, null);
        txtSoGio = view.findViewById(R.id.txtSoGio);
        txtNgayThuong = view.findViewById(R.id.txtNgayThuong);
        txtLuongCb = view.findViewById(R.id.txtLuongCb);
        txtChuNhat = view.findViewById(R.id.txtChuNhat);
        txtUngLuong = view.findViewById(R.id.txtUngLuong);
        txtSoTienThuong = view.findViewById(R.id.txtSoTienThuong);
        txtTong = view.findViewById(R.id.txtTong);
        spinner = view.findViewById(R.id.spn);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1);
        Bundle bundle = getArguments();
        NhanVien nhanVien = (NhanVien) bundle.getSerializable("nv");

        try {
            List<BangLuong> bangLuong = (List<BangLuong>) GiaoDienChinh.bangLuong.getListBangLuong(1);
            List<String> list = new ArrayList<>();
            for (int i = 0; i < bangLuong.size(); i++) {
                list.add("Bảng lương:" + bangLuong.get(i).getNgayThang());
            }
            arrayAdapter.addAll(list);
            spinner.setAdapter(arrayAdapter);
            spinner.setSelection(list.size() - 1);
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
                    float soH = 0f;
                    try {
                        Calendar calendar = Calendar.getInstance();
                        float hChuNhat = 0;
                          for (ChamCong chamCong : GiaoDienChinh.daoChamCong.getListChamCong(nhanVien.getMaNv(), bangLuong.get(i).getNgayThang())) {
                            if (chamCong.getXacNhanChamCong()==1) {
                                 soH += chamCong.getGioKetThuc().getTime() - chamCong.getGioBatDau().getTime();
                                calendar.setTime(chamCong.getNgay());
                                if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {

                                    hChuNhat += (float) (chamCong.getGioKetThuc().getTime() - chamCong.getGioBatDau().getTime()) / (1000 * 60 * 60);
                                }

                            }
                              Log.d("ddddd", "onItemSelected: "+String.format("%.0f",soH));
                        }
                        float h = (float) soH / (1000 * 60 * 60);
                        float v = bangLuong.get(i).getLuongCB();
                        float congMotNgay = v / 26;
                        float congMotGio = congMotNgay / 8;
                        float tongLuong=((h-hChuNhat)+(hChuNhat*2))*congMotGio;
                        txtLuongCb.setText(v+" ₫");
                        txtSoGio.setText(h + " giờ");
                        txtChuNhat.setText(bangLuong.get(i).getChuNhat() + " ca");
                        txtNgayThuong.setText((bangLuong.get(i).getNgayCong() - bangLuong.get(i).getChuNhat()) + " ca");
                        txtUngLuong.setText(bangLuong.get(i).getUngLuong() + " ₫");
                        txtSoTienThuong.setText(bangLuong.get(i).getThuong() + " ₫");
                        txtTong.setText((tongLuong-bangLuong.get(i).getUngLuong()+bangLuong.get(i).getThuong()) +" ₫");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

        } catch (SQLException e) {
            Log.d("gggggggggg", "onCreateView: " + e.toString());
            e.printStackTrace();
        }
        return view;
    }
}
