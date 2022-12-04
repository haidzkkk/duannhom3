package laptrinhandroid.fpoly.dnnhm3.Adapter.AdapterHoaDon;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import laptrinhandroid.fpoly.dnnhm3.DAO.DAOSanPham;
import laptrinhandroid.fpoly.dnnhm3.DAO.DAOhoadon;
import laptrinhandroid.fpoly.dnnhm3.DAO.Daochitiethoadon;
import laptrinhandroid.fpoly.dnnhm3.Entity.ChiTietHoaDonban;
import laptrinhandroid.fpoly.dnnhm3.Entity.HoaDonBan;
import laptrinhandroid.fpoly.dnnhm3.Entity.SanPham;
import laptrinhandroid.fpoly.dnnhm3.R;

public class Adapterchitiethoadonban extends RecyclerView.Adapter<Adapterchitiethoadonban.viewholder> {

    Context context;
    List<SanPham> listsp;
    List<ChiTietHoaDonban> listhd;
    DAOSanPham daoSanPham;
    DAOhoadon daOhoadon;
    Daochitiethoadon daochitiethoadon;

    public Adapterchitiethoadonban(Context context, List<ChiTietHoaDonban> listhd) {
        this.context = context;
        this.listhd = listhd;
        daochitiethoadon= new Daochitiethoadon();
    }

    @NonNull
    @Override

    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.buttonsheet,parent,false);
        return new viewholder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        ChiTietHoaDonban chiTietHoaDonban=listhd.get(position);
        HoaDonBan hoaDonBan=daOhoadon.getIdhoadonban(String.valueOf(chiTietHoaDonban.getMaHD()));
        SanPham sanPham=daoSanPham.getIdSP(String.valueOf(chiTietHoaDonban.getMaSp()));
        holder.soluong.setText(String.valueOf(sanPham.getSoLuong()));
        holder.name.setText(String.valueOf(hoaDonBan.getTongTien()));
        holder.tongtien.setText(String.valueOf(hoaDonBan.getTongTien()));
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                BottomSheetdigloghoadon bottomSheetdigloghoadon= BottomSheetdigloghoadon.getInstance(chiTietHoaDonban);
//                bottomSheetdigloghoadon.show(((FragmentActivity)context).getSupportFragmentManager(),bottomSheetdigloghoadon.getTag());
            }
        });


    }

    @Override
    public int getItemCount() {
        return listhd.size();
    }

    public class viewholder extends RecyclerView.ViewHolder{
        TextView name,soluong,tongtien;
        CardView cardView;
        public viewholder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.item_CTHD_tensp);
            soluong = itemView.findViewById(R.id.item_CTHD_Soluong);
            tongtien=itemView.findViewById(R.id.item_CTHD_thanhtien);

//



        }
    }
}
