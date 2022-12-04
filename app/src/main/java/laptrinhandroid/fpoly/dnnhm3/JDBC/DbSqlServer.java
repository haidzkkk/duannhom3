package laptrinhandroid.fpoly.dnnhm3.JDBC;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbSqlServer {
      Connection connection;

    public   Connection openConnect() {
        StrictMode.ThreadPolicy threadPolicy = new
                StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(threadPolicy);
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
            @SuppressLint("AuthLeak") String connectUrl = "jdbc:jtds:sqlserver://103.179.188.76:1433;databasename=CP17303_n03;user=CP17303_n03;password=N03Abc@123456";
          this.connection = DriverManager.getConnection(connectUrl);
            Log.d("sssssss", "openConnect: OK");
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("sssssss", "openConnect: OK" + e.getMessage());

        }
        return connection;
    }


}
