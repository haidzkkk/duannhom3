package laptrinhandroid.fpoly.dnnhm3.Adapter.AdapterNotification;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.sql.Date;
import java.util.List;

import laptrinhandroid.fpoly.dnnhm3.Entity.NhanVien;
import laptrinhandroid.fpoly.dnnhm3.Entity.ThongBaoNV;
import laptrinhandroid.fpoly.dnnhm3.Activity.MainActivity;
import laptrinhandroid.fpoly.dnnhm3.R;

public class AdapterThongBaoFromNhanVien extends RecyclerView.Adapter<AdapterThongBaoFromNhanVien.ThongBaoViewHolder> {
    List<ThongBaoNV> ThongBaoFromNhanVien;
    NhanVien mNV;
    public AdapterThongBaoFromNhanVien(NhanVien mNV) {
       this.mNV= mNV;
    }

    public void setData(List<ThongBaoNV> ThongBaoFromNhanVien) {
        this.ThongBaoFromNhanVien = ThongBaoFromNhanVien;
    }

    @NonNull
    @Override
    public ThongBaoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ThongBaoViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_noti_to_nhanvien, null));
    }

    @Override
    public void onBindViewHolder(@NonNull ThongBaoViewHolder holder, int position) {
        ThongBaoNV nhanVien=ThongBaoFromNhanVien.get(position);
        if(nhanVien.getNgay()!=null){
            if(nhanVien.getNgay().equals(String.valueOf(new Date(System.currentTimeMillis())))){
                holder.ngay.setText("Ngày hôm nay");
            }
            holder.ngay.setText("Ngày " + nhanVien.getNgay());

            holder.trangthai.setText("Trạng thái :" + nhanVien.getTrangThai());
            holder.xacNhan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=  new Intent(view.getContext(), MainActivity.class);
                    intent.putExtra("NV",mNV);
                    view.getContext().startActivity(intent);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return ThongBaoFromNhanVien.size();
    }

    public class ThongBaoViewHolder extends RecyclerView.ViewHolder {
        TextView ngay, trangthai,message,xacNhan;

        public ThongBaoViewHolder(@NonNull View itemView) {
            super(itemView);
            trangthai = itemView.findViewById(R.id.trangthai);
            message = itemView.findViewById(R.id.message);
            xacNhan = itemView.findViewById(R.id.xacNhan);
            ngay = itemView.findViewById(R.id.ngay);
        }
    }
}
