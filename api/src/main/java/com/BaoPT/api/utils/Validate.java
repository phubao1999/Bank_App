/////////////////////////////////////////////////////////////////////////////
//
// © 2020 VNEXT TRAINING
//
/////////////////////////////////////////////////////////////////////////////

package com.BaoPT.api.utils;

/**
 * [OVERVIEW] Regex.
 *
 * @author: (VNEXT) BaoPT
 * @version: 1.0
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2020/04/15      (VNEXT) BaoPT       Create new
*/
public class Validate {

    public static final String USER_NAME = "[^0-9][A-Za-z0-9]{4,10}";
    public static final String DATE = "^[0-9]{4}/[0-1][0-9]/[0-3][0-9]$";
    public static final String EMAIL = "[A-Za-z1-9]+@[g][m][a][i][l].[c][o][m]$";
    public static final String AMOUNT = "[0-9]+";
    public static final String BANK_ID = "[123]";
    public static final String PHONE_NUMBER = "^[0]([0-9]+){9,10}$";
    public static final String PASSWORD = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,8}$";

}
