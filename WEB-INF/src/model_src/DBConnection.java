package com.models;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
public class DBConnection {
    public static Connection con = null;

	public static Connection getConnection() {
        // if(con!=null){
        //     return con;
        // }
		Properties props = new Properties();
		FileInputStream fis = null;
		try {
			//Dont forgot to set the KEYS_PATH environment variable to actual path of the keys;
			fis = new FileInputStream(System.getenv("KEYS_PATH"));
			props.load(fis);
			Class.forName(props.getProperty("DB_DRIVER_CLASS"));
			con = DriverManager.getConnection(props.getProperty("DB_URL"),
					props.getProperty("DB_USERNAME"),
					props.getProperty("DB_PASSWORD"));
		} catch (IOException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
}
