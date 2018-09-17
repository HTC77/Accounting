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
public class Accounts {

    boolean isMoein = false;
    DataAccess da = new DataAccess();

    public int createDoc() {
        int docId;
        String sql;
        sql = "Select Max(id) FROM `daftar_rouzname`";
        String[][] data = da.Select(sql);
        if (data.length == 0) {
            docId = 1;
        } else {
            docId = (Integer.parseInt(data[0][0]) + 1);
        }
        return docId;
    }

//    public void removeEmptyDoc(int docId) {
//        String sql;
//        sql = "Select * FROM `daftar_rouzname` WHERE `id`='%s'";
//        sql = String.format(sql, docId);
//        String[][] data = da.Select(sql);
//        if (data[0][1] == null) {
//            sql = "DELETE FROM `daftar_rouzname` WHERE `id`='%s'";
//            sql = String.format(sql, docId);
//            da.Docommand(sql);
//        }
//
//    }
    public String getTotalById(int id) {
        String name;
        String sql = "Select * FROM `totalaccount` WHERE `id`='%s'";
        sql = String.format(sql, id);
        String[][] data = da.Select(sql);
        name = data[0][1];
        return name;
    }

    public String getTotalGroupById(int id) {
        String name;
        String sql = "Select * FROM `accountgroup` WHERE `id`='%s'";
        sql = String.format(sql, id);
        String[][] data = da.Select(sql);
        name = data[0][1];
        return name;
    }

    public int getTotalGroupId(int id) {
        String sql = "Select * FROM `totalaccount` WHERE `id`='%s'";
        sql = String.format(sql, id);
        String[][] data = da.Select(sql);
        id = Integer.parseInt(data[0][3]);

        return id;
    }

    public String getTfsiliGroup(int id) {
        String name;
        String sql = "Select * FROM `tafsiligroup` WHERE `id`='%s'";
        sql = String.format(sql, id);
        String[][] data = da.Select(sql);
        name = data[0][1];
        return name;
    }

    public String getMoein(int id) {
        String name;
        String sql = "Select * FROM `moeinaccount` WHERE `id`='%s'";
        sql = String.format(sql, id);
        String[][] data = da.Select(sql);
        name = data[0][1];
        return name;
    }

    public int getMoeinId(int id) {
        String sql;
        sql = "Select * FROM `tafsiliaccount` WHERE `id`='%s'";
        sql = String.format(sql, id);
        String[][] data = da.Select(sql);
        int moeinId = Integer.parseInt(data[0][3]);
        return moeinId;
    }

    public String getAccountName(int id) {
        String name;
        String sql;
        String[][] data;
        sql = "Select * FROM `moeinaccount` WHERE `id`='%s'";
        sql = String.format(sql, id);
        data = da.Select(sql);
        if (data.length != 0) {
            name = data[0][1];
            isMoein = true;
        } else {
            sql = "Select * FROM `tafsiliaccount` WHERE `id`='%s'";
            sql = String.format(sql, id);
            data = da.Select(sql);
            name = data[0][1];
            isMoein = false;

        }
        return name;
    }

    public int getTotalId(int id) {
        String sql;
        String[][] data;
        sql = "Select * FROM `moeinaccount` WHERE `id`='%s'";
        sql = String.format(sql, id);
        data = da.Select(sql);
        id = Integer.parseInt(data[0][3]);
        return id;
    }

//       public int getTotalId(int id) {
//        String sql = "Select * FROM `totalaccount` WHERE `id`='%s'";
//        sql = String.format(sql, id);
//        String[][] data = da.Select(sql);
//        id = Integer.parseInt(data[0][0]);
//        return id;
//    }
    //for moein account
    public boolean deleteTheLast(int accountId) {
        boolean errorState = false;
        int eId;
        String sql;
        String[][] data;
        sql = "Select * FROM `daftar_rouzname` WHERE `account`='%s'";
        sql = String.format(sql, accountId);
        data = da.Select(sql);
   if(data.length!=0){
        for (String[] data1 : data) {
            eId = Integer.parseInt(data1[0]);
            sql = "Select * FROM `daftar_rouzname` WHERE `id`='%s' AND `account`='7001'";
            sql = String.format(sql, eId);
            data = da.Select(sql);
            if(data.length!=0){
                    sql = "DELETE FROM `daftar_rouzname` WHERE `id`='%s' ";
                    sql = String.format(sql, eId);
                    if (!(da.Docommand(sql))) {
                        errorState = true;
                        JOptionPane.showMessageDialog(null, "خطا در انجام عملیات");
                    }
                    sql = "DELETE FROM `daftar_moein` WHERE `sanad`='%s'";
                    sql = String.format(sql, eId);
                    if (!(da.Docommand(sql))) {
                        errorState = true;
                        JOptionPane.showMessageDialog(null, "خطا در انجام عملیات");
                    }
                    sql = "DELETE FROM `daftar_kol` WHERE `sanad`='%s'";
                    sql = String.format(sql, eId);
                    if (!(da.Docommand(sql))) {
                        errorState = true;
                        JOptionPane.showMessageDialog(null, "خطا در انجام عملیات");
                    }
                    sql = "DELETE FROM `mandehesab` WHERE `account`='%s'";
                    sql = String.format(sql, accountId);
                    if (!(da.Docommand(sql))) {
                        errorState = true;
                        JOptionPane.showMessageDialog(null, "خطا در انجام عملیات");
                    }
                          
                    
            }
        }
    }
        return errorState;
    }

    public void enteghalAvalDore(int docId, int accountId, double bedehkar, double bestankar, String tozih, int tarikh) {
        int totalId;
        String sql;
        boolean error = false;
        double mande = 0;
        double eMande = 0;
        double eBedehkar = 0;
        double eBestankar = 0;
        String eTozih = getAccountName(accountId);
        if (isMoein) {
            error = deleteTheLast(accountId);
        } else {
            int moeinId = getMoeinId(accountId);
            error = deleteTheLast(moeinId,accountId);
        }

        if (bedehkar != 0) {
            mande = mande + bedehkar;
            eBestankar = bedehkar;
            eMande = eMande - eBestankar;

        } else {
            mande = mande - bestankar;
            eBedehkar = bestankar;
            eMande = eMande + eBedehkar;
        }
if(!error){
        if (isMoein) {
            sql = "INSERT INTO `daftar_rouzname`(`id`,`account`, `bedehkar`, `bestankar`, `tozih`, `tarikh`) VALUES ('%s','7001','%s','%s','***انتقال از سیستم قبل***(%s)','%s')";
            sql = String.format(sql, docId, eBedehkar, eBestankar, eTozih, tarikh);
            if (!(da.Docommand(sql))) {
                error = true;
                JOptionPane.showMessageDialog(null, "خطا در ذخیره اطلاعات");
            }

            sql = "INSERT INTO `daftar_rouzname`(`id`,`account`, `bedehkar`, `bestankar`, `tozih`, `tarikh`) VALUES ('%s','%s','%s','%s','***انتقال از سیستم قبل***(%s)','%s')";
            sql = String.format(sql, docId, accountId, bedehkar, bestankar, tozih, tarikh);
            if (!(da.Docommand(sql))) {
                error = true;
                JOptionPane.showMessageDialog(null, "خطا در ذخیره اطلاعات");
            }

            sql = "INSERT INTO `daftar_moein`( `account`, `tarikh`, `sanad`, `tozih`, `bedehkar`, `bestankar`, `mande`) VALUES ('7001','%s','%s','***انتقال از سیستم قبل***(%s)','%s','%s','%s')";
            sql = String.format(sql, tarikh, docId, eTozih, eBedehkar, eBestankar, eMande);
            if (!(da.Docommand(sql))) {
                error = true;
                JOptionPane.showMessageDialog(null, "خطا در ذخیره اطلاعات");
            }
            sql = "INSERT INTO `daftar_moein`( `account`, `tarikh`, `sanad`, `tozih`, `bedehkar`, `bestankar`, `mande`) VALUES('%s','%s','%s','***انتقال از سیستم قبل***(%s)','%s','%s','%s')";
            sql = String.format(sql, accountId, tarikh, docId, tozih, bedehkar, bestankar, mande);
            if (!(da.Docommand(sql))) {
                error = true;
                JOptionPane.showMessageDialog(null, "خطا در ذخیره اطلاعات");
            }

            totalId = getTotalId(7001);
            sql = "INSERT INTO `daftar_kol`(`total`, `tarikh`, `sanad`, `moein`,`tozih`, `bedehkar`, `bestankar`, `mande`) VALUES('%s','%s','%s','7001','***انتقال از سیستم قبل***(%s)','%s','%s','%s')";
            sql = String.format(sql, totalId, tarikh, docId, eTozih, eBedehkar, eBestankar, eMande);
            if (!(da.Docommand(sql))) {
                error = true;
                JOptionPane.showMessageDialog(null, "خطا در ذخیره اطلاعات");
            }

            totalId = getTotalId(accountId);

            sql = "INSERT INTO `daftar_kol`(`total`, `tarikh`, `sanad`, `moein`,`tozih`, `bedehkar`, `bestankar`, `mande`) VALUES('%s','%s','%s','%s','***انتقال از سیستم قبل***(%s)','%s','%s','%s')";
            sql = String.format(sql, totalId, tarikh, docId, accountId, tozih, bedehkar, bestankar, mande);
            if (!(da.Docommand(sql))) {
                error = true;
                JOptionPane.showMessageDialog(null, "خطا در ذخیره اطلاعات");
            }

            sql = "INSERT INTO `mandehesab` (`mande`,`account`)VALUES('%s','%s')";
            sql = String.format(sql, mande, accountId);
            if (!(da.Docommand(sql))) {
                error = true;
                JOptionPane.showMessageDialog(null, "خطا در بروز رسانی اطلاعات292929 ");
            }
            isMoein = false;
        } else {////////////////////////////////////////////ELSE IF IS TAFSILI////////////////////////////
            
            int moeinId = getMoeinId(accountId);
       
            sql = "INSERT INTO `daftar_rouzname`(`id`,`account`, `bedehkar`, `bestankar`, `tozih`, `tarikh`) VALUES ('%s','7001','%s','%s','***انتقال از سیستم قبل***(%s)','%s')";
            sql = String.format(sql, docId, eBedehkar, eBestankar, eTozih, tarikh);
            if (!(da.Docommand(sql))) {
                error = true;
                JOptionPane.showMessageDialog(null, "خطا در ذخیره اطلاعات");
            }
            sql = "INSERT INTO `daftar_rouzname`(`id`,`account`, `bedehkar`, `bestankar`, `tozih`, `tarikh`) VALUES ('%s','%s','%s','%s','***انتقال از سیستم قبل***(%s)','%s')";
            sql = String.format(sql, docId, moeinId, bedehkar, bestankar, tozih, tarikh);
            if (!(da.Docommand(sql))) {
                error = true;
                JOptionPane.showMessageDialog(null, "خطا در ذخیره اطلاعات");
            }

            sql = "INSERT INTO `daftar_moein`( `account`, `tarikh`, `sanad`, `tozih`, `bedehkar`, `bestankar`, `mande`) VALUES ('7001','%s','%s','***انتقال از سیستم قبل***(%s)','%s','%s','%s')";
            sql = String.format(sql, tarikh, docId, eTozih, eBedehkar, eBestankar, eMande);
            if (!(da.Docommand(sql))) {
                error = true;
                JOptionPane.showMessageDialog(null, "خطا در ذخیره اطلاعات");
            }
            sql = "INSERT INTO `daftar_moein`( `account`, `tarikh`, `sanad`, `tozih`, `bedehkar`, `bestankar`, `mande`) VALUES('%s','%s','%s','***انتقال از سیستم قبل***(%s)','%s','%s','%s')";
            sql = String.format(sql, moeinId, tarikh, docId, tozih, bedehkar, bestankar, mande);
            if (!(da.Docommand(sql))) {
                error = true;
                JOptionPane.showMessageDialog(null, "خطا در ذخیره اطلاعات");
            }

            totalId = getTotalId(7001);
            sql = "INSERT INTO `daftar_kol`(`total`, `tarikh`, `sanad`, `moein`, `tafsiliha`,`tozih`, `bedehkar`, `bestankar`, `mande`) VALUES('%s','%s','%s','7001','%s','***انتقال از سیستم قبل***(%s)','%s','%s','%s')";
            sql = String.format(sql, totalId, tarikh, docId, accountId, eTozih, eBedehkar, eBestankar, eMande);
            if (!(da.Docommand(sql))) {
                error = true;
                JOptionPane.showMessageDialog(null, "خطا در ذخیره اطلاعات111");
            }

            totalId = getTotalId(moeinId);
            sql = "INSERT INTO `daftar_kol`(`total`, `tarikh`, `sanad`, `moein` , `tafsiliha`,`tozih`, `bedehkar`, `bestankar`, `mande`) VALUES('%s','%s','%s','%s','%s','***انتقال از سیستم قبل***(%s)','%s','%s','%s')";
            sql = String.format(sql, totalId, tarikh, docId, moeinId, accountId, tozih, bedehkar, bestankar, mande);
            if (!(da.Docommand(sql))) {
                error = true;
                JOptionPane.showMessageDialog(null, "خطا در ذخیره اطلاعات2222");
            }

            sql = "INSERT INTO `mandehesab_tafsili` (`mande`,`account`)VALUES('%s','%s')";
            sql = String.format(sql, mande, accountId);
            if (!(da.Docommand(sql))) {
                error = true;
                JOptionPane.showMessageDialog(null, "خطا در بروز رسانی اطلاعات  تفصیلی");
            }

            mande += getMande(moeinId);
            sql = "UPDATE `mandehesab` SET `mande`='%s' WHERE `account`='%s'";
            sql = String.format(sql, mande, moeinId);
            if (!(da.Docommand(sql))) {
                error = true;
                JOptionPane.showMessageDialog(null, "خطا در بروز رسانی اطلاعات معین ");
            }
        }
    }
        if (!error) {
            JOptionPane.showMessageDialog(null, "با موفقیت ذخیره شد");
        } else {
            error = false;
        }
    }

    public double getMande(int accountId) {
        double mande;
        String sql;
        String[][] data;
        sql = "Select * FROM `mandehesab_tafsili` WHERE `account`='%s'";
        sql = String.format(sql, accountId);
        data = da.Select(sql);
        if (data.length != 0) {
            mande = Double.valueOf(data[0][1]);
        } else {
            sql = "Select * FROM `mandehesab` WHERE `account`='%s'";
            sql = String.format(sql, accountId);
            data = da.Select(sql);
            mande = Double.valueOf(data[0][1]);
        }
        return mande;
    }

    public void sabteRouzname(int docId, int bedehkarId, int bestankarId, double bedehkarMande, double bestankarMande, double mablagh, String tozih, int tarikh) {
        int totalId;
        int tafsiliBedehkarId = 0;
        int tafsiliBestankarId = 0;
        String test;
        boolean error = false;
        String sql;
        bedehkarMande += mablagh;
        bestankarMande -= mablagh;

        test = getAccountName(bedehkarId);
        if (isMoein) {
            test = getAccountName(bestankarId);
        } else {
            tafsiliBedehkarId = bedehkarId;
        }

        if (isMoein) {
            sql = "INSERT INTO `daftar_rouzname`(`id`,`account`, `bedehkar`, `bestankar`, `tozih`, `tarikh`) VALUES ('%s','%s','%s','%s','%s','%s')";
            sql = String.format(sql, docId, bedehkarId, mablagh, 0, tozih, tarikh);
            if (!(da.Docommand(sql))) {
                error = true;
                JOptionPane.showMessageDialog(null, "خطا در ذخیره اطلاعات");
            }

            sql = "INSERT INTO `daftar_rouzname`(`id`,`account`, `bedehkar`, `bestankar`, `tozih`, `tarikh`) VALUES ('%s','%s','%s','%s','%s','%s')";
            sql = String.format(sql, docId, bestankarId, 0, mablagh, tozih, tarikh);
            if (!(da.Docommand(sql))) {
                error = true;
                JOptionPane.showMessageDialog(null, "خطا در ذخیره اطلاعات");
            }

            sql = "INSERT INTO `daftar_moein`( `account`, `tarikh`, `sanad`, `tozih`, `bedehkar`, `bestankar`, `mande`) VALUES('%s','%s','%s','%s','%s','%s','%s')";
            sql = String.format(sql, bedehkarId, tarikh, docId, tozih, mablagh, 0, bedehkarMande);
            if (!(da.Docommand(sql))) {
                error = true;
                JOptionPane.showMessageDialog(null, "خطا در ذخیره اطلاعات");
            }

            sql = "INSERT INTO `daftar_moein`( `account`, `tarikh`, `sanad`, `tozih`, `bedehkar`, `bestankar`, `mande`) VALUES('%s','%s','%s','%s','%s','%s','%s')";
            sql = String.format(sql, bestankarId, tarikh, docId, tozih, 0, mablagh, bestankarMande);
            if (!(da.Docommand(sql))) {
                error = true;
                JOptionPane.showMessageDialog(null, "خطا در ذخیره اطلاعات");
            }

            totalId = getTotalId(bedehkarId);

            sql = "INSERT INTO `daftar_kol`(`total`, `tarikh`, `sanad`, `moein`,`tozih`, `bedehkar`, `bestankar`, `mande`) VALUES('%s','%s','%s','%s','%s','%s','%s','%s')";
            sql = String.format(sql, totalId, tarikh, docId, bedehkarId, tozih, mablagh, 0, bedehkarMande);
            if (!(da.Docommand(sql))) {
                error = true;
                JOptionPane.showMessageDialog(null, "خطا در ذخیره اطلاعات");
            }
            totalId = getTotalId(bestankarId);

            sql = "INSERT INTO `daftar_kol`(`total`, `tarikh`, `sanad`, `moein`,`tozih`, `bedehkar`, `bestankar`, `mande`) VALUES('%s','%s','%s','%s','%s','%s','%s','%s')";
            sql = String.format(sql, totalId, tarikh, docId, bestankarId, tozih, 0, mablagh, bestankarMande);
            if (!(da.Docommand(sql))) {
                error = true;
                JOptionPane.showMessageDialog(null, "خطا در ذخیره اطلاعات");
            }

            sql = "UPDATE `mandehesab` SET `mande`='%s' WHERE  `account`='%s'";
            sql = String.format(sql, bedehkarMande, bedehkarId);
            if (!(da.Docommand(sql))) {
                error = true;
                JOptionPane.showMessageDialog(null, "خطا در بروز رسانی اطلاعات ");
            }
            sql = "UPDATE `mandehesab` SET `mande`='%s' WHERE  `account`='%s'";
            sql = String.format(sql, bestankarMande, bestankarId);
            if (!(da.Docommand(sql))) {
                error = true;
                JOptionPane.showMessageDialog(null, "خطا در بروز رسانی اطلاعات ");
            }
            isMoein = false;
        } else {
            System.out.println("into else");

            test = getAccountName(bestankarId);
            if (!isMoein) {
                tafsiliBestankarId = bestankarId;
            }

            if (tafsiliBedehkarId != 0) {
                System.out.println("into first if");
                bedehkarId = getMoeinId(tafsiliBedehkarId);
                System.out.println("BEDEH: " + bedehkarId + "   tafsili:" + tafsiliBedehkarId);
                double bedehkarMandeMoein = getMande(bedehkarId);
                bedehkarMandeMoein += mablagh;
                System.out.println("BEDEH: " + bedehkarMandeMoein);

                sql = "INSERT INTO `daftar_rouzname`(`id`,`account`, `bedehkar`, `bestankar`, `tozih`, `tarikh`) VALUES ('%s','%s','%s','%s','%s','%s')";
                sql = String.format(sql, docId, bedehkarId, mablagh, 0, tozih, tarikh);
                if (!(da.Docommand(sql))) {
                    error = true;
                    JOptionPane.showMessageDialog(null, "خطا در ذخیره اطلاعات11");
                }

                sql = "INSERT INTO `daftar_moein`( `account`, `tarikh`, `sanad`, `tozih`, `bedehkar`, `bestankar`, `mande`) VALUES('%s','%s','%s','%s','%s','%s','%s')";
                sql = String.format(sql, bedehkarId, tarikh, docId, tozih, mablagh, 0, bedehkarMandeMoein);
                if (!(da.Docommand(sql))) {
                    error = true;
                    JOptionPane.showMessageDialog(null, "خطا در ذخیره اطلاعات22");
                }

                totalId = getTotalId(bedehkarId);

                sql = "INSERT INTO `daftar_kol`(`total`, `tarikh`, `sanad`, `moein`,`tafsiliha`,`tozih`, `bedehkar`, `bestankar`, `mande`) VALUES('%s','%s','%s','%s','%s','%s','%s','%s','%s')";
                sql = String.format(sql, totalId, tarikh, docId, bedehkarId, tafsiliBedehkarId, tozih, mablagh, 0, bedehkarMandeMoein);
                if (!(da.Docommand(sql))) {
                    error = true;
                    JOptionPane.showMessageDialog(null, "خطا در ذخیره اطلاعات");
                }

                sql = "UPDATE `mandehesab` SET `mande`='%s' WHERE  `account`='%s'";
                sql = String.format(sql, bedehkarMandeMoein, bedehkarId);
                if (!(da.Docommand(sql))) {
                    error = true;
                    JOptionPane.showMessageDialog(null, "خطا در بروز رسانی اطلاعات ");
                }
                sql = "UPDATE `mandehesab_tafsili` SET `mande`='%s' WHERE `account`='%s'";
                sql = String.format(sql, bedehkarMande, tafsiliBedehkarId);
                if (!(da.Docommand(sql))) {
                    error = true;
                    JOptionPane.showMessageDialog(null, "خطا در بروز رسانی اطلاعات ");
                }

            } else {
                System.out.println("into first else");

                sql = "INSERT INTO `daftar_rouzname`(`id`,`account`, `bedehkar`, `bestankar`, `tozih`, `tarikh`) VALUES ('%s','%s','%s','%s','%s','%s')";
                sql = String.format(sql, docId, bedehkarId, mablagh, 0, tozih, tarikh);
                if (!(da.Docommand(sql))) {
                    error = true;
                    JOptionPane.showMessageDialog(null, "خطا در ذخیره اطلاعات");
                }

                sql = "INSERT INTO `daftar_moein`( `account`, `tarikh`, `sanad`, `tozih`, `bedehkar`, `bestankar`, `mande`) VALUES('%s','%s','%s','%s','%s','%s','%s')";
                sql = String.format(sql, bedehkarId, tarikh, docId, tozih, mablagh, 0, bedehkarMande);
                if (!(da.Docommand(sql))) {
                    error = true;
                    JOptionPane.showMessageDialog(null, "خطا در ذخیره اطلاعات");
                }

                totalId = getTotalId(bedehkarId);

                sql = "INSERT INTO `daftar_kol`(`total`, `tarikh`, `sanad`, `moein`,`tozih`, `bedehkar`, `bestankar`, `mande`) VALUES('%s','%s','%s','%s','%s','%s','%s','%s')";
                sql = String.format(sql, totalId, tarikh, docId, bedehkarId, tozih, mablagh, 0, bedehkarMande);
                if (!(da.Docommand(sql))) {
                    error = true;
                    JOptionPane.showMessageDialog(null, "خطا در ذخیره اطلاعات");
                }

                sql = "UPDATE `mandehesab` SET `mande`='%s' WHERE  `account`='%s'";
                sql = String.format(sql, bedehkarMande, bedehkarId);
                if (!(da.Docommand(sql))) {
                    error = true;
                    JOptionPane.showMessageDialog(null, "خطا در بروز رسانی اطلاعات ");
                }
            }
            if (tafsiliBestankarId != 0) {
                System.out.println("into second if");

                bestankarId = getMoeinId(tafsiliBestankarId);
                double bestankarMandeMoein = getMande(bestankarId);
                bestankarMandeMoein -= mablagh;

                sql = "INSERT INTO `daftar_rouzname`(`id`,`account`, `bedehkar`, `bestankar`, `tozih`, `tarikh`) VALUES ('%s','%s','%s','%s','%s','%s')";
                sql = String.format(sql, docId, bestankarId, 0, mablagh, tozih, tarikh);
                if (!(da.Docommand(sql))) {
                    error = true;
                    JOptionPane.showMessageDialog(null, "خطا در ذخیره اطلاعات1");
                }

                sql = "INSERT INTO `daftar_moein`( `account`, `tarikh`, `sanad`, `tozih`, `bedehkar`, `bestankar`, `mande`) VALUES('%s','%s','%s','%s','%s','%s','%s')";
                sql = String.format(sql, bestankarId, tarikh, docId, tozih, 0, mablagh, bestankarMandeMoein);
                if (!(da.Docommand(sql))) {
                    error = true;
                    JOptionPane.showMessageDialog(null, "خطا در ذخیره اطلاعات2");
                }

                totalId = getTotalId(bestankarId);

                sql = "INSERT INTO `daftar_kol`(`total`, `tarikh`, `sanad`, `moein`,`tafsiliha`,`tozih`, `bedehkar`, `bestankar`, `mande`) VALUES('%s','%s','%s','%s','%s','%s','%s','%s','%s')";
                sql = String.format(sql, totalId, tarikh, docId, bestankarId, tafsiliBestankarId, tozih, 0, mablagh, bestankarMandeMoein);
                if (!(da.Docommand(sql))) {
                    error = true;
                    JOptionPane.showMessageDialog(null, "خطا در ذخیره اطلاعات3");
                }

                sql = "UPDATE `mandehesab` SET `mande`='%s' WHERE  `account`='%s'";
                sql = String.format(sql, bestankarMandeMoein, bestankarId);
                if (!(da.Docommand(sql))) {
                    error = true;
                    JOptionPane.showMessageDialog(null, "خطا در بروز رسانی اطلاعات ");
                }
                sql = "UPDATE `mandehesab_tafsili` SET `mande`='%s' WHERE `account`='%s'";
                sql = String.format(sql, bestankarMande, tafsiliBestankarId);
                if (!(da.Docommand(sql))) {
                    error = true;
                    JOptionPane.showMessageDialog(null, "خطا در بروز رسانی اطلاعات ");
                }

            } else {
                System.out.println("into second else");

                sql = "INSERT INTO `daftar_rouzname`(`id`,`account`, `bedehkar`, `bestankar`, `tozih`, `tarikh`) VALUES ('%s','%s','%s','%s','%s','%s')";
                sql = String.format(sql, docId, bestankarId, 0, mablagh, tozih, tarikh);
                if (!(da.Docommand(sql))) {
                    error = true;
                    JOptionPane.showMessageDialog(null, "خطا در ذخیره اطلاعات");
                }

                sql = "INSERT INTO `daftar_moein`( `account`, `tarikh`, `sanad`, `tozih`, `bedehkar`, `bestankar`, `mande`) VALUES('%s','%s','%s','%s','%s','%s','%s')";
                sql = String.format(sql, bestankarId, tarikh, docId, tozih, 0, mablagh, bestankarMande);
                if (!(da.Docommand(sql))) {
                    error = true;
                    JOptionPane.showMessageDialog(null, "خطا در ذخیره اطلاعات");
                }

                totalId = getTotalId(bestankarId);

                sql = "INSERT INTO `daftar_kol`(`total`, `tarikh`, `sanad`, `moein`,`tozih`, `bedehkar`, `bestankar`, `mande`) VALUES('%s','%s','%s','%s','%s','%s','%s','%s')";
                sql = String.format(sql, totalId, tarikh, docId, bestankarId, tozih, 0, mablagh, bestankarMande);
                if (!(da.Docommand(sql))) {
                    error = true;
                    JOptionPane.showMessageDialog(null, "خطا در ذخیره اطلاعات");
                }

                sql = "UPDATE `mandehesab` SET `mande`='%s' WHERE  `account`='%s'";
                sql = String.format(sql, bestankarMande, bestankarId);
                if (!(da.Docommand(sql))) {
                    error = true;
                    JOptionPane.showMessageDialog(null, "خطا در بروز رسانی اطلاعات ");
                }

            }
            isMoein = false;
//            sql = "Select * FROM `tafsiliaccount` WHERE `id`='%s'";
//            sql = String.format(sql, accountId);
//            String[][] data = da.Select(sql);
//            int tafsiliId = Integer.parseInt(data[0][0]);
//            int moeinId = Integer.parseInt(data[0][3]);
        }

        if (!error) {
            JOptionPane.showMessageDialog(null, "با موفقیت ثبت شد");
        } else {
            error = false;
        }

    }

    public boolean hasTafsili(int accountId) {
        boolean result = false;
        String sql;
        sql = "Select * FROM `tafsiliaccount` WHERE `moein`='%s'";
        sql = String.format(sql, accountId);
        String[][] data = da.Select(sql);
        if (data.length != 0) {
            result = true;
        }
        return result;
    }

    private boolean deleteTheLast(int moeinId, int accountId) {
       boolean errorState = false;
        int eId;
        String sql;
        String[][] data;
        
   //Tafsili
        double mande = 0;
   
        sql = "Select * FROM `daftar_kol` WHERE `tafsiliha`='%s'";
        sql = String.format(sql, accountId);
        data = da.Select(sql);
   if(data.length!=0){
        for (String[] data1 : data) {
            eId = Integer.parseInt(data1[3]);
            sql = "Select * FROM `daftar_rouzname` WHERE `id`='%s' AND `account`='7001'";
            sql = String.format(sql, eId);
            data = da.Select(sql);
            if(data.length!=0){
                    //get back before die☻
                    for (String[] data2 : data) {
                        if(data2[2].equals("0")){
                            mande=getMande(moeinId);
                            mande-=Double.parseDouble(data2[3].toString());
                            sql = "UPDATE `mandehesab` SET `mande`='%s' WHERE `account`='%s'";
                            sql = String.format(sql, mande, moeinId);
                            if (!(da.Docommand(sql))) {
                                errorState = true;
                                JOptionPane.showMessageDialog(null, "خطا در بروز رسانی اطلاعات1 ");
                               }   
                        }
                        else{
                            mande=getMande(moeinId);
                            mande+=Double.parseDouble(data2[2].toString());
                            sql = "UPDATE `mandehesab` SET `mande`='%s' WHERE `account`='%s'";
                            sql = String.format(sql, mande, moeinId);
                            if (!(da.Docommand(sql))) {
                                errorState = true;
                                JOptionPane.showMessageDialog(null, "خطا در بروز رسانی اطلاعات 2");
                               }
                        }
                    }
                
                
                    sql = "DELETE FROM `daftar_rouzname` WHERE `id`='%s' ";
                    sql = String.format(sql, eId);
                    if (!(da.Docommand(sql))) {
                        errorState = true;
                        JOptionPane.showMessageDialog(null, "خطا در انجام عملیات");
                    }
                    sql = "DELETE FROM `daftar_moein` WHERE `sanad`='%s'";
                    sql = String.format(sql, eId);
                    if (!(da.Docommand(sql))) {
                        errorState = true;
                        JOptionPane.showMessageDialog(null, "خطا در انجام عملیات");
                    }
                    sql = "DELETE FROM `daftar_kol` WHERE `sanad`='%s'";
                    sql = String.format(sql, eId);
                    if (!(da.Docommand(sql))) {
                        errorState = true;
                        JOptionPane.showMessageDialog(null, "خطا در انجام عملیات");
                    }
                    sql = "DELETE FROM `mandehesab_tafsili` WHERE `account`='%s'";
                    sql = String.format(sql, accountId);
                    if (!(da.Docommand(sql))) {
                        errorState = true;
                        JOptionPane.showMessageDialog(null, "خطا در انجام عملیات");
                    }
                                     
            }
        }
    }
            
        return errorState;
  
    }
}
