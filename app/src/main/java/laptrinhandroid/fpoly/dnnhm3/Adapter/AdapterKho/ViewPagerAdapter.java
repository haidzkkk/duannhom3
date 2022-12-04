package laptrinhandroid.fpoly.dnnhm3.Adapter.AdapterKho;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import laptrinhandroid.fpoly.dnnhm3.Activity.QuanLyKho;
import laptrinhandroid.fpoly.dnnhm3.Fragment.Kho.TabListBill_QLKho_Fragment;
import laptrinhandroid.fpoly.dnnhm3.Fragment.Kho.TabListProduct_QLKho_Fragment;


public class ViewPagerAdapter extends FragmentStateAdapter {
    public ViewPagerAdapter(@NonNull QuanLyKho fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:return new TabListProduct_QLKho_Fragment();
            case 1:return new TabListBill_QLKho_Fragment();
        }
        return new TabListProduct_QLKho_Fragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
