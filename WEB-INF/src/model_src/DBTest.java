package com.models;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSetMetaData;
import java.sql.PreparedStatement;
public class DBTest {
	public static Statement stmt;
	public static Connection con;
	public static String s;
	public static void print(){
		try{
			ResultSet rs=stmt.executeQuery("select * from dummy");
			while(rs.next()){
				String name = rs.getString("email");
                String password=rs.getString("name");
				System.out.println(password+ "," +name);
			}
			System.out.println();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

    public static void createTable(){
		try{
			
			boolean is_altered=stmt.execute("create table if not exists students(name varchar(20),id int);");
			System.out.println("Table creted successfully");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void insert(){
		try{
			int no_of_rows=stmt.executeUpdate("insert into dummy values('chella',"+s+");");
			System.out.println("The no of rows inserted are  :"+no_of_rows);


		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
    public static void  showDesc(){
		try{
		ResultSet rs = stmt.executeQuery("select * from students");
	    ResultSetMetaData rsmd = rs.getMetaData();
		System.out.println("Structure of the table is " +rsmd.getColumnCount());
		for(int i=1;i<=rsmd.getColumnCount();i++){
		  System.out.print("|"+rsmd.getColumnName(i)+"|");
		}
		System.out.println();
		}
		 catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void insertAndReturn(){
		try{
			// int no_of_rows=stmt.executeUpdate("insert into dummy values('chella222',"+s+");", Statement.RETURN_GENERATED_KEYS);
			// System.out.println("The no of rows inserted are  :"+no_of_rows);
			// System.out.println("The no of rows inserted are  :"+stmt.getGeneratedKeys());
			// ResultSet rs = stmt.getGeneratedKeys();


			PreparedStatement ps = con.prepareStatement("insert into dummy values(?,'dominic',?);", Statement.RETURN_GENERATED_KEYS);
			{
			ps.setString(1,null);
			ps.setString(2,null);
			int no_of_rows=ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			System.out.println("The no of rows inserted are  :"+no_of_rows);
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnsNumber = rsmd.getColumnCount();
			int columnValue;
			while (rs.next()) {
				for (int i = 1; i <= columnsNumber; i++) {
					if (i > 1) System.out.print(",  ");
					columnValue = rs.getInt(i);
					System.out.print(columnValue + " " + rsmd.getColumnName(i));
				}
				System.out.println("");
			}
		    }
			// ps.close();

			//prepared statement
			if(true){
			ps = con.prepareStatement("insert into dummy values(?,'dominic',?);", Statement.RETURN_GENERATED_KEYS);
			ps.setString(1,null);
			ps.setString(2,null);
			int no_of_rows=ps.executeUpdate();
			System.out.println(ps.executeUpdate());
			// ps.close();
                ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			System.out.println("The no of rows inserted are  :"+no_of_rows);
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnsNumber = rsmd.getColumnCount();
			int columnValue;
			while (rs.next()) {
				for (int i = 1; i <= columnsNumber; i++) {
					if (i > 1) System.out.print(",  ");
					columnValue = rs.getInt(i);
					System.out.print(columnValue + " " + rsmd.getColumnName(i));
				}
				System.out.println("");
			}
	    	}

		} catch (SQLException e) {
			e.printStackTrace();
	}
}

	public static void main(String[] args) throws Exception {
		con = DBConnection.getConnection();
		// Connection con2 = DBConnection.getConnection();
        // System.out.println(con+""+con2);	
		
		stmt = con.createStatement();
		
		insertAndReturn();
		// print();
}
}