package laptrinhandroid.fpoly.dnnhm3.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.sql.SQLException;
import java.util.List;

import laptrinhandroid.fpoly.dnnhm3.DAO.DAOChamCong;
import laptrinhandroid.fpoly.dnnhm3.Entity.ChamCong;
import laptrinhandroid.fpoly.dnnhm3.R;

public class List_Cham_Cong extends AppCompatActivity {
RecyclerView recyclerView;
Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_cham_cong);
        recyclerView=findViewById(R.id.rcy);
        intent=getIntent();
        DAOChamCong chamCong=new DAOChamCong();
     int maNV=   intent.getIntExtra("maNV",0);
        try {
           List<ChamCong>chamCongs= chamCong.getListChamCong(maNV);
           if(chamCongs!=null){

           }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));

    }
}