package laptrinhandroid.fpoly.dnnhm3.Fragment;


import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import laptrinhandroid.fpoly.dnnhm3.R;

public class BottomSheetDialog extends BottomSheetDialogFragment {
    private RecyclerView recyclerView;
    private TextView close, edit;

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add, null);
        recyclerView = view.findViewById(R.id.rcy);
        edit = view.findViewById(R.id.edit);
        close = view.findViewById(R.id.close);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        //      onViewCreatedđược gọi ngay sau onCreateView(phương thức bạn khởi tạo và tạo tất cả các đố
//                i tượng của mình, bao gồm cả của bạn TextView)
        super.onViewCreated(view, savedInstanceState);
        ((View) view.getParent()).setBackgroundColor(Color.TRANSPARENT);
    }
}
