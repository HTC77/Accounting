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
public class AccountGroup {

    DataAccess da = new DataAccess();

    public String[][] getAccounts() {
      
        String sql = "select * from accountgroup";
        String[][] data = da.Select2(sql);
        return data;
    }

    public void saveAccount(int id, String name, String mahiat, String type) {
        String sql = "INSERT INTO `accountgroup`(`id`, `name`, `mahiat`, `type`) VALUES ('%s','%s','%s','%s')";
        sql = String.format(sql, id, name, mahiat, type);
        if (!(da.Docommand(sql))) {
            JOptionPane.showMessageDialog(null, "خطا در ذخیره اطلاعات");
        }
    }


    public void updateAccount(int id, String name, String mahiat, String type, int newId) {
        String sql = "UPDATE `accountgroup` SET `id`=%S,`name`='%S',`mahiat`='%S',`type`='%S' WHERE `id`=%S";
        sql = String.format(sql, newId, name, mahiat, type, id);
        if (da.Docommand(sql)) {
        } else {
            JOptionPane.showMessageDialog(null, "خطا در بروز رسانی اطلاعات");
        }
    }

    public void deleteAccount(int id) {
        String sql = "DELETE FROM `accountgroup` WHERE `id`='%s'";
        sql = String.format(sql, id);
        if (da.Docommand(sql)) {
            JOptionPane.showMessageDialog(null, "با موفقیت حذف شد");
        } else {
            JOptionPane.showMessageDialog(null, "خطا در حذف اطلاعات");
        }
    }

}
