/////////////////////////////////////////////////////////////////////////////
//
// © 2020 VNEXT TRAINING
//
/////////////////////////////////////////////////////////////////////////////

package com.BaoPT.api.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

@Service
public class Define {
    
    @Autowired
    private BankDao bankDao;

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
    
    public String defineBank(int id) {
        String bankName = null;
        BankEntity bankEntity = this.bankDao.getBank(id);
        bankName = bankEntity.getBankName();
        return bankName;
    }
    
    public double getFee(int id, int option) {
        double fee = 0;
        BankEntity bankEntity = this.bankDao.getBank(id);
        if (option == 1) {
            fee = bankEntity.getFeeFirst();
        } else if (option == 2) {
            fee = bankEntity.getFeeSecond();
        } else if (option == 3) {
            fee = bankEntity.getFeeThird();
        } else {
            fee = bankEntity.getFeeTransfer();
        }
        return fee;
    }

}
