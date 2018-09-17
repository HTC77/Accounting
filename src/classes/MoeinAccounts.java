/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import dataAccess.DataAccess;
import javax.swing.JOptionPane;

/**
 *
 * @author HTC
 */
public class MoeinAccounts {
     DataAccess da = new DataAccess();

    public String[][] getAccounts() {

        String sql = "SELECT * FROM `moeinaccount`";
        String[][] data = da.Select2(sql);
        data = getTotals(data);
        return data;

    }

    public String[][] getTotals(String[][] data) {
        String totalId;
        for (int i = 0; i < data.length; i++) {
            totalId = data[i][4];
            String sql = "select * from totalaccount where `id`='%s' ";
            sql = String.format(sql, totalId);
            String[][] data2 = da.Select(sql);
           data[i][4] = data2[0][0];
            
        }
        return data;

    }
     public String[][] getMoeinByTotal(int id){
         String data[][];
            String sql = "select * from moeinaccount where `total`='%s' ";
             sql = String.format(sql, id);
            data = da.Select2(sql);
          return data;
     }

    public int getTotal(String totalName) {
        int totalId;
        String sql = "select * from totalaccount where `name`='%s' ";
        sql = String.format(sql, totalName);
        String[][] data = da.Select(sql);
        totalId=Integer.parseInt(data[0][0]) ;
        return totalId;
    }

    public void saveAccount(int id, String name, String mahiat, int totalId) {
        String sql = "INSERT INTO `moeinaccount`(`id`, `name`, `mahiat`, `total`) VALUES ('%s','%s','%s','%s')";
        sql = String.format(sql, id, name, mahiat, totalId);
        if (!(da.Docommand(sql))) {
            JOptionPane.showMessageDialog(null, "خطا در ذخیره اطلاعات");
        }
    }

    public void updateAccount(int id, String name, String mahiat, int totalId, int newId) {
        String sql = "UPDATE `moeinaccount` SET `id`=%S,`name`='%S',`mahiat`='%S',`total`='%S' WHERE `id`=%S";
        sql = String.format(sql, newId, name, mahiat, totalId, id);
        if (da.Docommand(sql)) {
        } else {
            JOptionPane.showMessageDialog(null, "خطا در بروز رسانی اطلاعات");
        }
    }

    public void deleteAccount(int id) {
        String sql = "DELETE FROM `moeinaccount` WHERE `id`='%s'";
        sql = String.format(sql, id);
        if (da.Docommand(sql)) {
            JOptionPane.showMessageDialog(null, "با موفقیت حذف شد");
        } else {
            JOptionPane.showMessageDialog(null, "خطا در حذف اطلاعات");
        }
    }

}
