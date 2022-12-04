package laptrinhandroid.fpoly.dnnhm3.Entity;

import java.sql.Date;
public class HoaDonNhapKho {
    private int maHDNhap;
    private int maNV, maSP;
    private Date ngayNhap;
    private float tongTien;
    public HoaDonNhapKho() {
    }

    public HoaDonNhapKho(int maNV, int maSP, Date ngayNhap,  float tongTien) {
        this.maNV = maNV;
        this.maSP = maSP;
        this.ngayNhap = ngayNhap;
        this.tongTien = tongTien;
    }
    public HoaDonNhapKho(int maHDNhap, int maNV, int maSP, Date ngayNhap,  float tongTien) {
        this.maHDNhap = maHDNhap;
        this.maNV = maNV;
        this.maSP = maSP;
        this.ngayNhap = ngayNhap;
        this.tongTien = tongTien;
    }

    public int getMaNV() {
        return maNV;
    }

    public void setMaNV(int maNV) {
        this.maNV = maNV;
    }

    public int getMaSP() {
        return maSP;
    }

    public void setMaSP(int maSP) {
        this.maSP = maSP;
    }

    public int getMaHDNhap() {
        return maHDNhap;
    }

    public void setMaHDNhap(int maHDNhap) {
        this.maHDNhap = maHDNhap;
    }

    public Date getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(Date ngayNhap) {
        this.ngayNhap = ngayNhap;
    }


    public float getTongTien() {
        return tongTien;
    }

    public void setTongTien(float tongTien) {
        this.tongTien = tongTien;
    }
}
