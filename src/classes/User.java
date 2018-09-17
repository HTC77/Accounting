/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import dataAccess.DataAccess;

/**
 *
 * @author HTC
 */
public class User {
    
    public int id;
    public String name;
    public String family;
    public String userName;
    public String password;
    
    DataAccess da = new DataAccess();
    
     public enum ColumnIndex {

        id(0), name(1), family(2), userName(3),password(4);
        private final int Code;

        ColumnIndex(int code) {
            Code = code;
        }

        public int getCode() {
            return Code;
        }
    }
     
        
     
       public boolean CanLogin() {
        boolean r = false;
        String sql = "Select * from user where username='%s' and password='%s'";
        sql = String.format(sql, this.userName, this.password);
        String[][] data = da.Select(sql);
        if (data != null) {
            if (data.length > 0) {
                this.name = data[0][ColumnIndex.name.getCode()];
                this.family = data[0][ColumnIndex.family.getCode()];
                this.userName = data[0][ColumnIndex.userName.getCode()];
                this.password = data[0][ColumnIndex.password.getCode()];
                this.id = Integer.parseInt(data[0][ColumnIndex.id.getCode()]);
                r = true;
            }
        }
        return r;
    }
    
}
