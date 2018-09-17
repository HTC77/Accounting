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
public class TafsiliGroups {
    DataAccess da = new DataAccess();

    public String[][] getAccounts() {
      
        String sql = "select * from tafsiligroup";
        String[][] data = da.Select2(sql);
        return data;
    }

    public void saveAccount(int id, String name) {
        String sql = "INSERT INTO `tafsiligroup`(`id`, `name`) VALUES ('%s','%s')";
        sql = String.format(sql, id, name);
        if (!(da.Docommand(sql))) {
            JOptionPane.showMessageDialog(null, "خطا در ذخیره اطلاعات");
        }
    }


    public void updateAccount(int id, String name, int newId) {
        String sql = "UPDATE `tafsiligroup` SET `id`=%S,`name`='%S' WHERE `id`=%S";
        sql = String.format(sql, newId, name, id);
        if (da.Docommand(sql)) {
        } else {
            JOptionPane.showMessageDialog(null, "خطا در بروز رسانی اطلاعات");
        }
    }

    public void deleteAccount(int id) {
        String sql = "DELETE FROM `tafsiligroup` WHERE `id`='%s'";
        sql = String.format(sql, id);
        if (da.Docommand(sql)) {
            JOptionPane.showMessageDialog(null, "با موفقیت حذف شد");
        } else {
            JOptionPane.showMessageDialog(null, "خطا در حذف اطلاعات");
        }
    }

}
