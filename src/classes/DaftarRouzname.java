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
public class DaftarRouzname {

    DataAccess da = new DataAccess();

    public String[][] getReports(int date) {
        int moeinId;
        int totalId;
        String moeinName;
        String totalName;
        Accounts accounts = new Accounts();
        String sql = "SELECT * FROM `daftar_rouzname` WHERE tarikh='%s'";
        sql = String.format(sql, date);
        String[][] data = da.Select2(sql);
        String[][] data2 = new String[data.length][7];
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < 7; j++) {
                if (j == 2) {
                    moeinId =getMoeinId(Integer.parseInt(data[i][j])) ;
                    System.out.println("moein "+moeinId);
                    totalId = accounts.getTotalId(moeinId);
                    totalName = totalId + "-" + accounts.getTotalById(totalId);
                    data2[i][j] = totalName;

                    moeinName = moeinId + "-" + accounts.getMoein(moeinId);
                    data2[i][++j] = moeinName;
                }
                if (j == 3) {
                    data2[i][5] = data[i][j];
                }
                if (j == 4) {
                    data2[i][6] = data[i][j];
                }
                if (j == 5) {
                    data2[i][4] = data[i][j];
                }
                if (j < 2 && j >= 0) {
                    data2[i][j] = data[i][j];
                }
            }
        }

        return data2;

    }
    
    
    
    public int getMoeinId(int id){
        String sql;
        String[][] data;
        sql = "Select * FROM `moeinaccount` WHERE `id`='%s'";
        sql = String.format(sql, id);
        data = da.Select(sql);
        if (data.length != 0) {
            id =  Integer.parseInt(data[0][0]);
        } else {
            sql = "Select * FROM `tafsiliaccount` WHERE `id`='%s'";
            sql = String.format(sql, id);
            data = da.Select(sql);
            id = Integer.parseInt(data[0][3]);
        }
        return id;
    }
    

}
