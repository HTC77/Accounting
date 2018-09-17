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
public class TotalAccount {

    DataAccess da = new DataAccess();

    public String[][] getAccounts() {

        String sql = "select * from totalaccount";
        String[][] data = da.Select2(sql);
        data = getGroups(data);
        return data;

    }

    public String[][] getGroups(String[][] data) {
        String groupId;
        for (int i = 0; i < data.length; i++) {
            groupId = data[i][4];
            String sql = "select * from accountgroup where `id`='%s' ";
            sql = String.format(sql, groupId);
            String[][] data2 = da.Select(sql);
            data[i][4] = data2[0][1];
        }
        return data;

    }

    public int getGroup(String groupName) {
        int groupId;
        String sql = "select * from accountgroup where `name`='%s' ";
        sql = String.format(sql, groupName);
        String[][] data = da.Select(sql);
        groupId=Integer.parseInt(data[0][0]) ;
        return groupId;
    }

    public void saveAccount(int id, String name, String mahiat, int groupId) {
        String sql = "INSERT INTO `totalaccount`(`id`, `name`, `mahiat`, `group`) VALUES ('%s','%s','%s','%s')";
        sql = String.format(sql, id, name, mahiat, groupId);
        if (!(da.Docommand(sql))) {
            JOptionPane.showMessageDialog(null, "خطا در ذخیره اطلاعات");
        }
    }

    public void updateAccount(int id, String name, String mahiat, int groupId, int newId) {
        String sql = "UPDATE `totalaccount` SET `id`=%S,`name`='%S',`mahiat`='%S',`group`='%S' WHERE `id`=%S";
        sql = String.format(sql, newId, name, mahiat, groupId, id);
        if (da.Docommand(sql)) {
        } else {
            JOptionPane.showMessageDialog(null, "خطا در بروز رسانی اطلاعات");
        }
    }

    public void deleteAccount(int id) {
        String sql = "DELETE FROM `totalaccount` WHERE `id`='%s'";
        sql = String.format(sql, id);
        if (da.Docommand(sql)) {
            JOptionPane.showMessageDialog(null, "با موفقیت حذف شد");
        } else {
            JOptionPane.showMessageDialog(null, "خطا در حذف اطلاعات");
        }
    }

}
