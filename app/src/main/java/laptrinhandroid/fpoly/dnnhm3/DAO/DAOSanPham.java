package laptrinhandroid.fpoly.dnnhm3.DAO;

import android.util.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import laptrinhandroid.fpoly.dnnhm3.Entity.SanPham;
import laptrinhandroid.fpoly.dnnhm3.JDBC.DbSqlServer;

public class DAOSanPham {
    Connection objConn;

    public DAOSanPham() {
        DbSqlServer db = new DbSqlServer(); // hàm khởi tạo để mở kết nối
        objConn = db.openConnect(); // tạo mới DAO thì mở kết nối CSDL
    }

    public boolean addSanPham(SanPham sanPham) {
        Statement statement = null;
        try {
            statement = objConn.createStatement();
            String s1 = "Insert into SanPham(loaiSP, tenSP,giaNhap,giaBan,anh,soLuongDaBan,soLuong,ghiChu ) values (" +
                    "'" + sanPham.getLoaiSP() + "'," +
                    "'" + sanPham.getTenSP() + "'," +
                    "'" + sanPham.getGiaNhap() + "'," +
                    "'" + sanPham.getGiaBan() + "'," +
                    "'" + sanPham.getAnh() + "'," +
                    "'" + sanPham.getSoLuongDaBan() + "'," +
                    "'" + sanPham.getSoLuong() + "'," +
                    "'" + sanPham.getGhiChu() + "')";
            if (statement.executeUpdate(s1) > 0) {
                statement.close();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //-----------------------------------------------
//    public List<SanPham> getlistSP(String sql) throws SQLException {
//        ArrayList<SanPham> list = new ArrayList<>();
//        Statement statement = objConn.createStatement();// Tạo đối tượng Statement.
//        // Thực thi câu lệnh SQL trả về đối tượng ResultSet. // Mọi kết quả trả về sẽ được lưu trong ResultSet
//        ResultSet rs = statement.executeQuery(sql);
//        while (rs.next()) {
//            list.add(new SanPham(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getFloat(4), rs.getFloat(5), rs.getString(6), rs.getInt(7), rs.getInt(8), rs.getString(9)));// Đọc dữ liệu từ ResultSet
//        }
////        connection.close();// Đóng kết nối
//        return (List<SanPham>) list.get(0);
//    }
//
//    public List<SanPham> getIdSP(@NonNull SanPham sanPham) throws SQLException {
//        String sql = "SELECT * FROM SanPham WHERE maSP = "+sanPham.getMaSP()+"";
//        try {
//            return getlistSP(sql);
//        }catch (SQLException e){
//            e.printStackTrace();
//        }
//        return null;
//    }
    public List<SanPham> getlistSP(String sql, String...selectionArgs) throws SQLException {
        List<SanPham> list = new ArrayList<>();
        Statement statement = objConn.createStatement();
        ResultSet rs = statement.executeQuery(sql);
 while (rs.next()) {
            list.add(new SanPham(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getFloat(4), rs.getFloat(5), rs.getString(6), rs.getInt(7), rs.getInt(8), rs.getString(9)));// Đọc dữ liệu từ ResultSet
        }

        return list;
    }
    public SanPham getIdSP(String id) {
        String sql = "SELECT * FROM SanPham WHERE maSP="+id+"";
        List<SanPham> listSP = null;
        try {
            listSP = getlistSP(sql,id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listSP.get(0);
    }



    public boolean updateSanPham(SanPham sanPham)throws SQLException {
        String sql = "UPDATE SanPham  SET " + "loaiSP = ?," + " tenSP =?," + "giaNhap=?" +
                ",giaBan=?" + ",anh=?" + ",soLuongDaBan=?" + ",soLuong=?" + ",ghiChu=?" + " WHERE maSP='"+sanPham.getMaSP()+"';";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = objConn.prepareStatement(sql);
            preparedStatement.setInt(1, sanPham.getLoaiSP());
            preparedStatement.setString(2, sanPham.getTenSP());
            preparedStatement.setFloat(3, sanPham.getGiaNhap());
            preparedStatement.setFloat(4, sanPham.getGiaBan());
            preparedStatement.setString(5, sanPham.getAnh());
            preparedStatement.setInt(6, sanPham.getSoLuongDaBan());
            preparedStatement.setInt(7, sanPham.getSoLuong());
            preparedStatement.setString(8, sanPham.getGhiChu());

            if (preparedStatement.executeUpdate() > 0) {
                preparedStatement.close();
                return true;
            }
        } catch (SQLException e) {
            Log.d("ddddd", "updateSanPham: "+e.
                    getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteSanPham(int maSP) {
        Statement statement = null;
        try {
            statement = objConn.createStatement();
            String sql = "Delete from SanPham where maSP='" + maSP + "'";
            if (statement.executeUpdate(sql) > 0) {
                statement.close();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<SanPham> getListSanPham() throws SQLException {
        List<SanPham> list = new ArrayList<>();
        Statement statement = objConn.createStatement();
        String sql = " SELECT * FROM  SanPham";
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            list.add(new SanPham(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getFloat(4), rs.getFloat(5), rs.getString(6), rs.getInt(7), rs.getInt(8), rs.getString(9)));// Đọc dữ liệu từ ResultSet
        }
        objConn.close();
        return list;
    }
    //-------------------------

    public List<SanPham> getSearchSP(String kqtk) {
        String sql = "SELECT * FROM SanPham where tenSP like '%"+ kqtk +"%'";
        List<SanPham> listSearch=null;
        try {
            listSearch= getlistSP(sql,kqtk);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listSearch;
    }

    public List<SanPham> getAllsanpham() throws SQLException{
        String sql = "SELECT * FROM SanPham"  ;
        try {
            return getlistSP(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public Collection<? extends SanPham> getListSanPhamBaoCao(int i) {
        return null;
    }
}