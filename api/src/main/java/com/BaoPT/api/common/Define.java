/////////////////////////////////////////////////////////////////////////////
//
// © 2020 VNEXT TRAINING
//
/////////////////////////////////////////////////////////////////////////////

package com.BaoPT.api.common;

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

}
