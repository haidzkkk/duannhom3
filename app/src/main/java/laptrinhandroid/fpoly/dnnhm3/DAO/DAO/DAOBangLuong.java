package laptrinhandroid.fpoly.dnnhm3.DAO.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import laptrinhandroid.fpoly.dnnhm3.Entity.BangLuong;
import laptrinhandroid.fpoly.dnnhm3.JDBC.DbSqlServer;

public class DAOBangLuong {
    Connection objConn;

    public DAOBangLuong() {
        DbSqlServer db = new DbSqlServer(); // hàm khởi tạo để mở kết nối
        objConn = db.openConnect(); // tạo mới DAO thì mở kết nối CSDL
    }

    public boolean addBangLuong(BangLuong bangLuong) throws SQLException {

        String s1 = "Insert into NhanVien(id,maNV, luongCB,ngayCong,chuNhat,ungLuong,ngayThang) values ( ?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement=objConn.prepareStatement(s1);
        preparedStatement.setInt(1,bangLuong.getId());
        preparedStatement.setInt(2,bangLuong.getMaNV());
        preparedStatement.setFloat(3,bangLuong.getLuongCB());
        preparedStatement.setInt(4,bangLuong.getNgayCong());
        preparedStatement.setInt(5,bangLuong.getChuNhat());
        preparedStatement.setFloat(6,bangLuong.getUngLuong());
        preparedStatement.setString(7, bangLuong.getNgayThang());

        if (preparedStatement.executeUpdate() > 0) {
            preparedStatement.close();
            return true;
        }
        preparedStatement.close();
        return false;
    }

    public boolean updateBangLuong(BangLuong bangLuong, int maNV) throws SQLException {
        String sql = "UPDATE NhanVien  SET " + " luongCB =?," + "ngayCong=?" + ",chuNhat=?" + ",ungLuong=?" + ",thuong=?" +",ngayThang=?" + " WHERE maNv='" + maNV + "';";
        PreparedStatement preparedStatement = objConn.prepareStatement(sql);
        preparedStatement.setFloat(1, bangLuong.getLuongCB());
        preparedStatement.setInt(2, bangLuong.getNgayCong());
        preparedStatement.setInt(3, bangLuong.getChuNhat());
        preparedStatement.setFloat(4, bangLuong.getUngLuong());
        preparedStatement.setFloat(5, bangLuong.getThuong());
        preparedStatement.setString(7, bangLuong.getNgayThang());
        if (preparedStatement.executeUpdate(sql) > 0) {
            preparedStatement.close();
            return true;
        }
        return false;
    }

    public boolean deleteBangLuong(int maNv) throws SQLException {
        Statement statement = objConn.createStatement();
        String sql = "Delete from NhanVien where maNv='" + maNv + "'";
        if (statement.executeUpdate(sql) > 0) {
            statement.close();
            return true;
        }
        return false;
    }
    public BangLuong getBangLuong(int maNV) throws SQLException {
        BangLuong chamCong = null;
        if (objConn != null) {
            Statement statement = objConn.createStatement();// Tạo đối tượng Statement.
            String sql = " SELECT  * FROM  ChamCong where maNV='" + maNV + "'";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {

                chamCong = new BangLuong(rs.getInt(1), rs.getInt(2), rs.getFloat(3), rs.getInt(4), rs.getInt(5), rs.getFloat(6),rs.getFloat(7) ,rs.getString(8) );
            }
            statement.close();
            if (chamCong != null) {
                return chamCong;
            }
        }


        return null;
    }
    public BangLuong getBangLuong(int maNV,String ngayThang) throws SQLException {
        BangLuong chamCong = null;
        if (objConn != null) {
            Statement statement = objConn.createStatement();// Tạo đối tượng Statement.
            String sql = " SELECT  * FROM  ChamCong where maNV='"+maNV+"' ,ngayThang='"+ngayThang+"'";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {

                chamCong = new BangLuong(rs.getInt(1), rs.getInt(2), rs.getFloat(3), rs.getInt(4), rs.getInt(5), rs.getFloat(6),rs.getFloat(7) ,rs.getString(8) );
            }
            statement.close();
            if (chamCong != null) {
                return chamCong;
            }
        }


        return null;
    }

    public List<BangLuong> getListBangLuong(int maNV) throws SQLException {
        List<BangLuong> list = new ArrayList<>();
        if (objConn != null) {
            Statement statement = objConn.createStatement();// Tạo đối tượng Statement.
            String sql = "SELECT * FROM  BangLuong where maNV='" + maNV + "'";
            // Thực thi câu lệnh SQL trả về đối tượng ResultSet. // Mọi kết quả trả về sẽ được lưu trong ResultSet
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                list.add(new BangLuong(rs.getInt(1), rs.getInt(2), rs.getFloat(3), rs.getInt(4),
                        rs.getInt(5), rs.getFloat(6), rs.getFloat(7), rs.getString(8)));// Đọc dữ liệu từ ResultSet
            }
            statement.close();// Đóng kết nối
            return list;
        }
        return null;
    }

}
