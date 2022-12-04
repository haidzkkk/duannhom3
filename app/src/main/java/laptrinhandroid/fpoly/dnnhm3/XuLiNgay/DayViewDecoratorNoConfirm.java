package laptrinhandroid.fpoly.dnnhm3.XuLiNgay;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.util.Log;

import androidx.fragment.app.Fragment;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.spans.DotSpan;

import java.sql.Connection;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import laptrinhandroid.fpoly.dnnhm3.Entity.ChamCong;
import laptrinhandroid.fpoly.dnnhm3.R;

public class DayViewDecoratorNoConfirm implements com.prolificinteractive.materialcalendarview.DayViewDecorator {
    @SuppressLint("SimpleDateFormat")
     List<CalendarDay> strings;


    public DayViewDecoratorNoConfirm(List<CalendarDay> strings ) {
         this.strings = strings;

    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return strings.contains(day);


    }

    @Override
    public void decorate(DayViewFacade view) {
        view.addSpan(new DotSpan(5, Color.parseColor("#CD3100")));
     }

}
