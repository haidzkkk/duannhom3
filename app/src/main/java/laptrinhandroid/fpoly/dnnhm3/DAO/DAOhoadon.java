package laptrinhandroid.fpoly.dnnhm3.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import laptrinhandroid.fpoly.dnnhm3.Entity.HoaDonBan;
import laptrinhandroid.fpoly.dnnhm3.Entity.NhanVien;
import laptrinhandroid.fpoly.dnnhm3.JDBC.DbSqlServer;

public class DAOhoadon {
    Connection connection;
    public DAOhoadon() {
        DbSqlServer db = new DbSqlServer(); // hàm khởi tạo để mở kết nối
        connection = db.openConnect(); // tạo mới DAO thì mở kết nối CSDL
    }
    public boolean Insert(HoaDonBan objUser) throws SQLException {
        Statement statement = connection.createStatement();// Tạo đối tượng Statement.
        String sql = "insert into HoaDonBan(MaHDBan,Manv,Makh,ngayban,tongtien) values(" + objUser.getMaHDBan() + objUser.getMaNV()+objUser.getMaKH()+objUser.getNgayBan()+objUser.getTongTien()+")";
        if (statement.executeUpdate(sql) > 0) { // Dùng lệnh executeUpdate cho các lệnh CRUD
            connection.close();
            return true;
        } else {
            connection.close();
            return false;
        }
    }

    public boolean update(HoaDonBan hdb) throws SQLException{
        Statement statement=connection.createStatement();
        String sql="Update Hoadonban set MaHDBan="+hdb.getMaHDBan()+ hdb.getMaNV()+hdb.getMaKH()+hdb.getNgayBan()+hdb.getTongTien()+"";
         if(statement.executeUpdate(sql)>0){
             connection.close();
             return true;
         }else {
             connection.close();
             return false;
         }
    }
    public boolean Delete(HoaDonBan objUser) throws SQLException {
        Statement statement = connection.createStatement();// Tạo đối tượng Statement.
        String sql = "delete from HoaDonBan where MaHDBan = " + objUser.getMaHDBan();
        if (statement.executeUpdate(sql) > 0){
            connection.close();
            return true;
        }
        else
            connection.close();
        return false;
    }



    public List<HoaDonBan> getListHoadonban() throws SQLException {
        List<HoaDonBan> list = new ArrayList<>();
        Statement statement = connection.createStatement();// Tạo đối tượng Statement.
        String sql = " SELECT * FROM  Hoadonban";
        // Thực thi câu lệnh SQL trả về đối tượng ResultSet. // Mọi kết quả trả về sẽ được lưu trong ResultSet
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            list.add(new HoaDonBan(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getDate(4), rs.getFloat(5)));// Đọc dữ liệu từ ResultSet
        }
        connection.close();// Đóng kết nối
        return list;
    }


}
