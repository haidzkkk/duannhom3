package laptrinhandroid.fpoly.dnnhm3.Fragment.Fragment_baocao;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.io.Serializable;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import laptrinhandroid.fpoly.dnnhm3.Activity.BaoCaoDonhangActivity;
import laptrinhandroid.fpoly.dnnhm3.Adapter.Adapter_baocao.BaocaoAdapterLich;
import laptrinhandroid.fpoly.dnnhm3.Adapter.Adapter_baocao.TopSanPhamAdapter;
import laptrinhandroid.fpoly.dnnhm3.DAO.DAOBaoCao;
import laptrinhandroid.fpoly.dnnhm3.Entity.BaoCao;
import laptrinhandroid.fpoly.dnnhm3.Entity.HoaDonBan;
import laptrinhandroid.fpoly.dnnhm3.R;

public class FragmentCuaHang extends Fragment implements BaocaoAdapterLich.IsenDataTime{

    int i =0;
    double doanhthu;

    CardView cvTime, cvDonHang, cvDonhuy;
    TextView tvTime,tvDoanhThu, tvDonHang, tvDonHuy;
    GraphView graphView;
    RecyclerView recyclerView;

    TopSanPhamAdapter topSanPhamAdapter;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    List<HoaDonBan> listHoaDonBan;
    List<BaoCao> listTopSp;
    Context mcontext;
    DAOBaoCao daoBaoCao;
    BottomSheetDialog sheetDialogLich;
    private LineGraphSeries<DataPoint> line1;

    public FragmentCuaHang() {
        // Required empty public constructor
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cua_hang, container, false);
        anhxa(view);

        getAllDataByDate(0, Calendar.getInstance().getTime());

        cvTime.setOnClickListener(v->{
            showButtonSheetDialog();
        });

        cvDonHang.setOnClickListener(v ->{
            Intent intent = new Intent(getContext(), BaoCaoDonhangActivity.class);
            Bundle bundle = new Bundle();
            if (listHoaDonBan.size() > 0)  bundle.putSerializable("data", (Serializable) listHoaDonBan);
            else  bundle.putSerializable("data", null);
            bundle.putBoolean("is", true);
            intent.putExtras(bundle);
            startActivity(intent);
        });

        cvDonhuy.setOnClickListener(v ->{
            Intent intent = new Intent(getContext(), BaoCaoDonhangActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("data", null);
            bundle.putBoolean("is", false);
            intent.putExtras(bundle);
            startActivity(intent);
        });

        return view;
    }

    private void anhxa(View view){
        tvTime = view.findViewById(R.id.frg_cuahang_tv_time);
        cvTime = view.findViewById(R.id.frg_cuahang_cardview_time);
        cvDonHang = view.findViewById(R.id.cuahang_cv_donhang);
        cvDonhuy = view.findViewById(R.id.cuahang_cv_donhuy);
        tvDoanhThu = view.findViewById(R.id.cuahang_tv_doanhthu);
        tvDonHang = view.findViewById(R.id.cuahang_tv_soluongdonhang);
        tvDonHuy = view.findViewById(R.id.cuahang_tv_soluongdonhuy);
        graphView = view.findViewById(R.id.cuahang_graphview);
        recyclerView = view.findViewById(R.id.cuahang_rcv_top);

        listHoaDonBan = new ArrayList<>();
        daoBaoCao = new DAOBaoCao();
        listTopSp = new ArrayList<>();
    }

    private void showButtonSheetDialog() {
        sheetDialogLich = new BottomSheetDialog(getContext());
        sheetDialogLich.setContentView(getLayoutInflater().inflate(R.layout.dialog_button_sheet_baocao_lich, null));
        sheetDialogLich.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        sheetDialogLich.show();

        RecyclerView recyclerView = sheetDialogLich.findViewById(R.id.dialog_buttomshett_baocao_lich_rcv);

        List<String> listlich = new ArrayList<>();
        listlich.add("Hôm nay");
        listlich.add("Tuần này");
        listlich.add("Tháng này");
        listlich.add("Tuần trước");
        listlich.add("Tháng trước");
        listlich.add("Thời gian khác");

        BaocaoAdapterLich baocaoAdapterLich = new BaocaoAdapterLich(getActivity(), listlich, this);
        recyclerView.setAdapter(baocaoAdapterLich);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);   //dòng kẻ giữa mỗi item
        recyclerView.addItemDecoration(itemDecoration);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void sendData(int time) {
        setChoise(time);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void setChoise(int choice) {
        Calendar calendar = Calendar.getInstance();
        switch (choice) {
            case 0:
                tvTime.setText("Hôm nay");
                getAllDataByDate(0, Calendar.getInstance().getTime());
                break;
            case 1:
                tvTime.setText("Tuần này");
                getAllDataByDate(1, Calendar.getInstance().getTime());
                break;
            case 2:
                tvTime.setText("Tháng này");
                getAllDataByDate(2, Calendar.getInstance().getTime());
                break;
            case 3:
                tvTime.setText("Tuần trước");
                getAllDataByDate(3, Calendar.getInstance().getTime());
                break;
            case 4:
                tvTime.setText("Tháng trước");
                getAllDataByDate(4, Calendar.getInstance().getTime());
                break;
            case 5:
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendar.set(year, month,dayOfMonth);
                        tvTime.setText(simpleDateFormat.format(calendar.getTime()));
                        getAllDataByDate(5, calendar.getTime());
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();

//                materialDatePicker.show(getActivity().getSupportFragmentManager(), "ALO");

                break;
        }
        sheetDialogLich.dismiss();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void getAllDataByDate(int positon , Date date){
        try {
            listHoaDonBan.clear();
            listTopSp.clear();
            listHoaDonBan.addAll(daoBaoCao.getListHoaDonBanByDay(positon ,date));
            listTopSp.addAll(daoBaoCao.getListTopSanPham(positon ,date));
            setUpData();
            setUpChart();
            setUpTopSP();
            Log.i("lengthListlailo",  "Length " + listHoaDonBan.size() );
            Log.i("lengthListlailo",  "Length TOP SP " + listTopSp.toString() );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void setUpData() {
        getSumDonHang();
        getSumDonHuy();
        doanhthu = getDoanhThu();
    }

    private Double getDoanhThu(){
        double tong = 0;
        for (HoaDonBan hd: listHoaDonBan){
            tong += hd.getTongTien();
        }
        runTien(tong);
        return tong;
    }

    private void runTien(double tong){
        i = 0;
        Handler handler1 = new Handler();
        handler1.post(new Runnable() {
            @Override
            public void run() {
                if (i <= tong){
                    i = i + 5234;
                    tvDoanhThu.setText(forMatNumber((double) i)+ " ₫");
                }else {
                    tvDoanhThu.setText(forMatNumber(tong) + " ₫");
                    return;
                }
                handler1.postDelayed(this, 1);
            }
        });
        tvDoanhThu.setText(forMatNumber(tong) + " ₫");
    }

    private int getSumDonHang(){
        int sum = 0;
        for (HoaDonBan hd: listHoaDonBan){
            if (hd.getTongTien() > 0){
                ++sum;
            }
        }
        tvDonHang.setText(String.valueOf(sum));
        return sum;
    }

    private int getSumDonHuy(){
        int sum = 0;
        for (HoaDonBan hd: listHoaDonBan){
            if (hd.getTongTien() == 0){
                ++sum;
            }
        }
        tvDonHuy.setText(String.valueOf(sum));
        return sum;
    }

    private String forMatNumber(Double aDouble){
        DecimalFormat formatter  = (DecimalFormat) NumberFormat.getInstance(Locale.US);
        DecimalFormatSymbols symbols = formatter.getDecimalFormatSymbols();

        symbols.setGroupingSeparator(',');
        formatter.setDecimalFormatSymbols(symbols);

        return formatter.format(aDouble);
    }

    private void setUpTopSP(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        topSanPhamAdapter = new TopSanPhamAdapter(getContext(), listTopSp, 0);
        recyclerView.setAdapter(topSanPhamAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));   //kẻ ngang

    }

    private void setUpChart(){

        line1 = new LineGraphSeries<>(getValuesChart());

        graphView.addSeries(line1);
        line1.setColor(getResources().getColor(R.color.teal_200));
        graphView.getViewport().setXAxisBoundsManual(true);     // cho biểu đồ rộng bằng item

    }

    private DataPoint[] getValuesChart (){
        DataPoint[] dataPoint = new DataPoint[]{
                new DataPoint(0, 21),
                new DataPoint(1, 12),
                new DataPoint(2, 34),
                new DataPoint(3, 56),
                new DataPoint(4, 23),
                new DataPoint(5, 63),
                new DataPoint(6, 23),
                new DataPoint(7, 74),
                new DataPoint(8, 12),
                new DataPoint(9, 75),
                new DataPoint(10, 45),
                new DataPoint(11, 23),
        };
        return dataPoint;
    }
}