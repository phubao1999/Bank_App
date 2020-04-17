/////////////////////////////////////////////////////////////////////////////
//
// © 2020 VNEXT TRAINING
//
/////////////////////////////////////////////////////////////////////////////

package com.BaoPT.api.common;

import org.springframework.beans.factory.annotation.Autowired;

import com.BaoPT.api.bean.BankEntity;
import com.BaoPT.api.dao.BankDao;

/**
 * [OVERVIEW] Define.
 *
 * @author: (VNEXT) BaoPT
 * @version: 1.0
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2020/04/18      (VNEXT) BaoPT       Create new
*/
public class Define {

    @Autowired
    private static BankDao bankDao;

    public static String defineStatus(int status) {
        String mean = null;
        if (status == 1) {
            mean = "Add Money";
        } else if (status == 2) {
            mean = "Withdrawal";
        } else if (status == 3) {
            mean = "Send Money";
        } else {
            mean = "Be Transferred Money";
        }
        return mean;
    }

    public static String defineBank(int idBank) {
        String bankName = null;
        BankEntity bankEntity = new BankEntity();
        bankEntity = bankDao.getBank(idBank);
        bankName = bankEntity.getBankName();
        return bankName;
    }

}
