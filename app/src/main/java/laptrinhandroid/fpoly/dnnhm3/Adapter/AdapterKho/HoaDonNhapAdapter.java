package laptrinhandroid.fpoly.dnnhm3.Adapter.AdapterKho;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import laptrinhandroid.fpoly.dnnhm3.DAO.DAOHoaDonNhap;
import laptrinhandroid.fpoly.dnnhm3.Entity.HoaDonNhapKho;
import laptrinhandroid.fpoly.dnnhm3.R;

public class HoaDonNhapAdapter extends RecyclerView.Adapter<HoaDonNhapAdapter.HoaDonNhapViewHolder> {
    Context context;
    ArrayList<HoaDonNhapKho> arrHDN = new ArrayList<>();
    DAOHoaDonNhap daoHoaDonNhap;
    View viewAlert;
    LayoutInflater inflater;

    public HoaDonNhapAdapter(Context context, ArrayList<HoaDonNhapKho> arrHDN) {
        this.context = context;
        this.arrHDN = arrHDN;
    }
    @NonNull
    @Override
    public HoaDonNhapViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View viewItem = inflater.inflate(R.layout.custom_hoadonnhap, parent, false);
        HoaDonNhapViewHolder hoaDonNhapViewHolder = new HoaDonNhapViewHolder(viewItem);
        viewAlert = parent;
        return hoaDonNhapViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HoaDonNhapViewHolder holder, int position) {
        daoHoaDonNhap = new DAOHoaDonNhap();
        HoaDonNhapKho hoaDonNhapKho = arrHDN.get(position);
        if(hoaDonNhapKho != null) {
            holder.tv_IdHDN.setText(hoaDonNhapKho.getMaHDNhap()+"");
            holder.tv_ngay_HDN.setText(hoaDonNhapKho.getNgayNhap().toString()+"");
            holder.tv_tien_HDN.setText(String.format("%.0f", hoaDonNhapKho.getTongTien())  + " vnđ");
            inflater = LayoutInflater.from(context);
        }

    }


    @Override
    public int getItemCount() {
        return arrHDN.size();
    }
    public static class HoaDonNhapViewHolder extends RecyclerView.ViewHolder{
        TextView tv_IdHDN, tv_tien_HDN, tv_ngay_HDN;
        public HoaDonNhapViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_IdHDN = itemView.findViewById(R.id.tv_id_HDN);
            tv_tien_HDN = itemView.findViewById(R.id.tv_tienHDN);
            tv_ngay_HDN = itemView.findViewById(R.id.tv_ngayHDN);
        }
    }
}
