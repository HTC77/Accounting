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
public class TafsiliAccounts {

    DataAccess da = new DataAccess();

    public String[][] getAccounts() {

        String sql = "SELECT * FROM `tafsiliaccount`";
        String[][] data = da.Select2(sql);
        data = getGroups(data);
        data = getMoeins(data);
        return data;

    }
    public String[][] getAccounts2() {

        String sql = "SELECT * FROM `tafsiliaccount`";
        String[][] data = da.Select2(sql);
        return data;

    }

    public String[][] getGroups(String[][] data) {
        String groupId;
        for (int i = 0; i < data.length; i++) {
            groupId = data[i][3];
            String sql = "SELECT `id`, `name` FROM `tafsiligroup` WHERE `id`='%s' ";
            sql = String.format(sql, groupId);
            String[][] data2 = da.Select(sql);
            data[i][3] = data2[0][1];

        }
        return data;

    }

    public String[][] getMoeins(String[][] data) {
        String moeinId;
        for (int i = 0; i < data.length; i++) {
            moeinId = data[i][4];
            String sql = "select * from moeinaccount where `id`='%s' ";
            sql = String.format(sql, moeinId);
            String[][] data2 = da.Select(sql);
            data[i][4] = data2[0][1];

        }
        return data;

    }

    public int getGroup(String groupName) {
        int groupId;
        String sql = "select * from tafsiligroup where `name`='%s' ";
        sql = String.format(sql, groupName);
        String[][] data = da.Select(sql);
        groupId = Integer.parseInt(data[0][0]);
        return groupId;
    }
    public int getMoein(String moeinName) {
        int moeinId;
        String sql = "select * from moeinaccount where `name`='%s' ";
        sql = String.format(sql, moeinName);
        String[][] data = da.Select(sql);
        moeinId = Integer.parseInt(data[0][0]);
        return moeinId;
    }

    public void saveAccount(int id, String name, int groupId,int moeinId) {
        String sql = "INSERT INTO `tafsiliaccount`(`id`, `name`, `group`, `moein`) VALUES ('%s','%s','%s','%s')";
        sql = String.format(sql, id, name,groupId,moeinId);
        if (!(da.Docommand(sql))) {
            JOptionPane.showMessageDialog(null, "خطا در ذخیره اطلاعات");
        }
    }

    public void updateAccount(int id, String name,  int groupId,int moeinId, int newId) {
        String sql = "UPDATE `tafsiliaccount` SET `id`=%S,`name`='%S',`group`='%S',`moein`='%S' WHERE `id`=%S";
        sql = String.format(sql, newId, name,  groupId,moeinId, id);
        if (da.Docommand(sql)) {
        } else {
            JOptionPane.showMessageDialog(null, "خطا در بروز رسانی اطلاعات");
        }
    }

    public void deleteAccount(int id) {
        String sql = "DELETE FROM `tafsiliaccount` WHERE `id`='%s'";
        sql = String.format(sql, id);
        if (da.Docommand(sql)) {
            JOptionPane.showMessageDialog(null, "با موفقیت حذف شد");
        } else {
            JOptionPane.showMessageDialog(null, "خطا در حذف اطلاعات");
        }
    }

}
