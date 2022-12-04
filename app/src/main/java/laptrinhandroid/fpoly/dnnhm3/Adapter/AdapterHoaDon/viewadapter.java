package laptrinhandroid.fpoly.dnnhm3.Adapter.AdapterHoaDon;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import laptrinhandroid.fpoly.dnnhm3.Fragment.haodon1;
import laptrinhandroid.fpoly.dnnhm3.Fragment.hoadon2;


public class viewadapter extends FragmentStateAdapter {
    haodon1 hoadon1;
    public viewadapter(@NonNull FragmentActivity fragmentActivity, haodon1 hoadon1) {
        super(fragmentActivity);
        this.hoadon1=hoadon1;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
     switch (position){
         case 0:return  hoadon1;
         case 1:return  new hoadon2();

     }
        return new haodon1();
    }

    public int getItemCount() {
        return 2;
    }
}
