package laptrinhandroid.fpoly.dnnhm3.Adapter.AdapterHoaDon;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import laptrinhandroid.fpoly.dnnhm3.ConvertImg;
import laptrinhandroid.fpoly.dnnhm3.DAO.DAOSanPham;
import laptrinhandroid.fpoly.dnnhm3.DAO.Daochitiethoadon;
import laptrinhandroid.fpoly.dnnhm3.Entity.ChiTietHoaDonban;
import laptrinhandroid.fpoly.dnnhm3.Entity.SanPham;
import laptrinhandroid.fpoly.dnnhm3.R;

public class AdapterchitiethoadonSheet extends RecyclerView.Adapter<AdapterchitiethoadonSheet.viewholder> {
    Context context;
    List<ChiTietHoaDonban> list;
    Daochitiethoadon daochitiethoadon;
    List<SanPham> listsp;
    DAOSanPham daoSanPham;
    public AdapterchitiethoadonSheet(Context context, List<ChiTietHoaDonban> list) {
        this.context = context;
        this.list = list;
        daochitiethoadon = new Daochitiethoadon();
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.itembuttonsheet,parent,false);
        return new viewholder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        ChiTietHoaDonban chiTietHoaDonban= list.get(position);
        daoSanPham = new DAOSanPham();
        SanPham sanPham=daoSanPham.getIdSP(String.valueOf(chiTietHoaDonban.getMaSp()));
        holder.imganh.setImageBitmap(ConvertImg.convertBaseStringToBitmap(sanPham.getAnh()));
        holder.namesp.setText( chiTietHoaDonban.getTenSP());
        holder.soluong.setText("số lượng: "+(chiTietHoaDonban.getSoLuong()));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewholder extends RecyclerView.ViewHolder{
        TextView namesp,soluong;
        ImageView imganh;
        public viewholder(@NonNull View itemView) {
            super(itemView);

            namesp = itemView.findViewById(R.id.item_CTHD_tensp);
            imganh = itemView.findViewById(R.id.CTHD_IMGadpater);
            soluong=itemView.findViewById(R.id.item_CTHD_soluong1);
//



        }
    }
}
