/////////////////////////////////////////////////////////////////////////////
//
// © 2020 VNEXT TRAINING
//
/////////////////////////////////////////////////////////////////////////////

package com.BaoPT.api.utils;

import java.util.Random;

/**
 * [OVERVIEW] XXXXX.
 *
 * @author: (VNEXT) BaoPT
 * @version: 1.0
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2020/05/19      (VNEXT) BaoPT       Create new
*/
public class GenerateOtp {
    
    public static char[] generateOtpNumber(int length) {
        Random obj = new Random();
        
        char[] otp = new char[length];
        for (int i = 0; i < length; i++) {
            otp[i] = (char)(obj.nextInt(10) + 48);
        }
        return otp;
    }
    
}
