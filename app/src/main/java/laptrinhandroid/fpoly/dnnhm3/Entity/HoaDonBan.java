package laptrinhandroid.fpoly.dnnhm3.Entity;

import java.io.Serializable;
import java.sql.Date;

public class HoaDonBan implements Serializable {
    private int maHDBan;
    private int maNV, maKH;
    private Date ngayBan;
    private float tongTien;

    public HoaDonBan() {
    }


    public void setMaNV(int maNV) {
        this.maNV = maNV;
    }

    public void setMaKH(int maKH) {
        this.maKH = maKH;
    }

    public HoaDonBan(int maNV, int maKH, int anInt, Date ngayBan, float tongTien) {
        this.maNV = maNV;
        this.maKH = maKH;
        this.ngayBan = ngayBan;
        this.tongTien = tongTien;
    }

    public int getMaHDBan() {
        return maHDBan;
    }

    public void setMaHDBan(int maHDBan) {
        this.maHDBan = maHDBan;
    }

    public int getMaNV() {
        return maNV;
    }

    public int getMaKH() {
        return maKH;
    }

    public Date getNgayBan() {
        return ngayBan;
    }

    public void setNgayBan(Date ngayBan) {
        this.ngayBan = ngayBan;
    }

    public float getTongTien() {
        return tongTien;
    }

    public void setTongTien(float tongTien) {
        this.tongTien = tongTien;
    }

}
