package laptrinhandroid.fpoly.dnnhm3.Adapter.AdapterHoaDon;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import laptrinhandroid.fpoly.dnnhm3.Fragment.haodon1;
import laptrinhandroid.fpoly.dnnhm3.Fragment.hoadon2;
import laptrinhandroid.fpoly.dnnhm3.Fragment.hoadon3;


public class viewadapter extends FragmentStateAdapter {
    public viewadapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
     switch (position){
         case 0:return  new haodon1();
         case 1:return  new hoadon2();
         case 2:return  new hoadon3();
     }
        return new haodon1();
    }

    public int getItemCount() {
        return 3;
    }
}
