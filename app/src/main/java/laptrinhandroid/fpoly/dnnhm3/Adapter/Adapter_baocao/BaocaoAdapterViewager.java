package laptrinhandroid.fpoly.dnnhm3.Adapter.Adapter_baocao;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import laptrinhandroid.fpoly.dnnhm3.Fragment.Fragment_baocao.FragmentCuaHang;
import laptrinhandroid.fpoly.dnnhm3.Fragment.Fragment_baocao.FragmentKhoHang;
import laptrinhandroid.fpoly.dnnhm3.Fragment.Fragment_baocao.FragmentLaiLo;
import laptrinhandroid.fpoly.dnnhm3.Fragment.Fragment_baocao.FragmentThuChi;

public class BaocaoAdapterViewager extends FragmentStateAdapter {

    public BaocaoAdapterViewager(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    //gửi dữ liệu bằng cách này
    public void setData(){

    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0: return new FragmentThuChi();
            case 1: return new FragmentLaiLo();
            case 2: return new FragmentCuaHang();
            case 3: return new FragmentKhoHang();
        }
        return new FragmentThuChi();
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
