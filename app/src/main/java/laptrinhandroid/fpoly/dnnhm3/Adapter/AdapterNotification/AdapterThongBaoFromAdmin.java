package laptrinhandroid.fpoly.dnnhm3.Adapter.AdapterNotification;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import laptrinhandroid.fpoly.dnnhm3.Activity.GiaoDienChinh;
import laptrinhandroid.fpoly.dnnhm3.Activity.List_Cham_Cong;
import laptrinhandroid.fpoly.dnnhm3.Activity.ThongBao;
import laptrinhandroid.fpoly.dnnhm3.ConvertImg;
import laptrinhandroid.fpoly.dnnhm3.Entity.NhanVien;
import laptrinhandroid.fpoly.dnnhm3.R;
import laptrinhandroid.fpoly.dnnhm3.Entity.ThongBaoAdmin;

public class AdapterThongBaoFromAdmin extends RecyclerView.Adapter<AdapterThongBaoFromAdmin.ThongBaoViewHolder> {
    List<ThongBaoAdmin> thongBaoFromAdmins;
    NhanVien mNV;
    Context context;
    public AdapterThongBaoFromAdmin(NhanVien mNV, Context context) {
        this.context = context;
            this.mNV = mNV;
    }

    public void setData(List<ThongBaoAdmin> thongBaoFromAdmins) {
        this.thongBaoFromAdmins = thongBaoFromAdmins;
    }

    @NonNull
    @Override
    public ThongBaoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ThongBaoViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_noti_to_admin, null));
    }

    @Override
    public void onBindViewHolder(@NonNull ThongBaoViewHolder holder, int position) {
        ThongBaoAdmin thongBaoAdmin = thongBaoFromAdmins.get(position);
        try {
            NhanVien nhanVien = GiaoDienChinh.nhanVien1.getListNhanVien(thongBaoAdmin.getMaNV());
            holder.anh.setImageBitmap(ConvertImg.convertBaseStringToBitmap(nhanVien.getAnh()));
            holder.xacNhan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        ThongBao.thongBaoAdminDAO.updateThongBaoAdmin(true,thongBaoAdmin.getId());
                        Intent intent=new Intent(context, List_Cham_Cong.class);
                        intent.putExtra("maNV",nhanVien.getMaNv());
                        context.startActivity(intent);
                     } catch (SQLException e) {
                        e.printStackTrace();
                    }


                }
            });
            holder.message.setText("Nhân viên " + nhanVien.getHoTen() + " yêu cầu xác nhận công");
            if(thongBaoAdmin.getNgay().equals(new Date(System.currentTimeMillis()))){
                holder.ngay.setText("Hôm nay");
            }else{
                holder.ngay.setText(thongBaoAdmin.getNgay());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    public int getItemCount() {
        return thongBaoFromAdmins.size();
    }

    public class ThongBaoViewHolder extends RecyclerView.ViewHolder {
        TextView ngay, message, xacNhan;
        ImageView anh;

        public ThongBaoViewHolder(@NonNull View itemView) {
            super(itemView);
            anh = itemView.findViewById(R.id.anh);
            message = itemView.findViewById(R.id.message);
            xacNhan = itemView.findViewById(R.id.xacNhan);
            ngay = itemView.findViewById(R.id.ngay);
        }
    }
}
