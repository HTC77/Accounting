/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author HTC
 */
public class DataAccess {

    public String serverName = "localhost";
    public int port = 3306;
    public String DBName = "accounting";
    public String userName = "root";
    public String password = "";
    public int rowCount = -1;
    private Connection con;
    private Statement st;

    public DataAccess() {

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception e) {
            System.err.println("خطا در " + e.getMessage());
        }
    }

    public boolean getConnection() {
        boolean connected = false;
        if (connect()) {
            connected = true;
        }
        return connected;
    }

    private boolean connect() {
        boolean cennected = false;
        try {

            //System.out.format("user: %s password: %s",this.userName,this.password);
            String url = "jdbc:mysql://%s:%s/%s?user=%s&password=%s&characterEncoding=UTF-8";
            url = String.format(url, this.serverName, this.port, this.DBName, this.userName, this.password);
            con = DriverManager.getConnection(url);
            st = con.createStatement();
            cennected = true;

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return cennected;

    }

    private void Disconnect() {
        try {
            st.close();
            con.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public String[][] Select(String sql) {
        String[][] data = null;
        connect();
        try {
            st.executeQuery(sql);
            ResultSet rs = st.getResultSet();
            int col = rs.getMetaData().getColumnCount();
            rs.last();
            int row = rs.getRow();
            rowCount = row;
            rs.beforeFirst();

            data = new String[row][col];
            int i = 0;
            
            while (rs.next()) {
                for (int j = 0; j < col; j++) {
                    
                    data[i][j] = rs.getString(j + 1);
                }
               
                i++;
            }
            rs.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        Disconnect();
        return data;
    }
    
    public String[][] Select2(String sql) {
        String[][] data = null;
        connect();
        try {
            st.executeQuery(sql);
            ResultSet rs = st.getResultSet();
            int col = rs.getMetaData().getColumnCount();
            col++;
            rs.last();
            int row = rs.getRow();
            rowCount = row;
            rs.beforeFirst();

            data = new String[row][col];
            int i = 0;
            int no=1;
            while (rs.next()) {
                for (int j = 0; j < col; j++) {
                    if(j==0){
                        data[i][j] =no+"";
                    }
                    else{
                        data[i][j] = rs.getString(j);
                    }
                }
                no++;
                i++;
            }
            rs.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        Disconnect();
        return data;
    }

   

    public boolean Docommand(String sql) {
        boolean r = false;
        connect();
        try {
            st.execute(sql);
            
            r=true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        Disconnect();
        return r;
    }

    public int getRow() {
        return rowCount;
    }

}
