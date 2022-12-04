package laptrinhandroid.fpoly.dnnhm3.Adapter.Adapter_baocao;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import laptrinhandroid.fpoly.dnnhm3.R;

public class BaocaoAdapterLich extends RecyclerView.Adapter<BaocaoAdapterLich.ViewHolder> {

    Context context;
    List<String> list;
    IsenDataTime isenDataTime;


    public BaocaoAdapterLich(Context context, List<String> list, IsenDataTime isenDataTime) {
        this.context = context;
        this.list = list;
        this.isenDataTime = isenDataTime;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_lich2, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.textView.setText(list.get(position));
        holder.textView.setOnClickListener(v -> {
            isenDataTime.sendData(holder.getAdapterPosition());
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.item_lich2_tvName);
        }
    }

    public interface IsenDataTime{
        void sendData(int time);
    }

}
