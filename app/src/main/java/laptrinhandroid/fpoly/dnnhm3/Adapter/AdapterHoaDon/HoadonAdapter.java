package laptrinhandroid.fpoly.dnnhm3.Adapter.AdapterHoaDon;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import laptrinhandroid.fpoly.dnnhm3.DAO.DAOhoadon;
import laptrinhandroid.fpoly.dnnhm3.Entity.HoaDonBan;
import laptrinhandroid.fpoly.dnnhm3.R;

public class HoadonAdapter extends RecyclerView.Adapter<HoadonAdapter.viewholder>  {
private Context context;
List<HoaDonBan> list;
DAOhoadon daOhoadon;
//    SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd");

    public HoadonAdapter(Context context, List<HoaDonBan> list) {
        this.context = context;
        this.list = list;
        daOhoadon = new DAOhoadon();
    }

    public HoadonAdapter() {
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.itemhoadon,parent,false);
        return new viewholder(view);

    }


    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        HoaDonBan hd= list.get(position);
        holder.txtmakh.setText(String.valueOf(hd.getMaKH()));
        holder.txtngayban.setText(String.valueOf(hd.getNgayBan()));
        holder.txttongtien.setText(String.valueOf(hd.getTongTien()));
       holder.txtmanv.setText(String.valueOf(hd.getMaNV()));
//           holder.edgiathue.setText(String.valueOf(loaisach.getGiathue()));


    }


    @Override
    public int getItemCount() {
        return list.size();
    }
    public class viewholder extends RecyclerView.ViewHolder{
    TextView txtid,txtmanv,txtmakh,txtngayban,txttongtien,txtthanhtoan;
    public viewholder(@NonNull View itemView) {
        super(itemView);

        txtmanv=itemView.findViewById(R.id.hoadonmanv);

        txtmakh=itemView.findViewById(R.id.hoadonmakh);
        txtngayban=itemView.findViewById(R.id.hoadonngayban);
        txttongtien=itemView.findViewById(R.id.hoadontongtien);
        txtthanhtoan=itemView.findViewById(R.id.hoadonthanhtoan);



    }
}

}
