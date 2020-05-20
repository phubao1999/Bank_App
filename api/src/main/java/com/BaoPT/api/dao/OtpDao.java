/////////////////////////////////////////////////////////////////////////////
//
// © 2020 VNEXT TRAINING
//
/////////////////////////////////////////////////////////////////////////////

package com.BaoPT.api.dao;

import com.BaoPT.api.bean.OtpEntity;

/**
 * [OVERVIEW] OtpDao.
 *
 * @author: (VNEXT) BaoPT
 * @version: 1.0
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2020/05/20      (VNEXT) BaoPT       Create new
*/
public interface OtpDao {

    /**
     * 
     * @author: (VNEXT) BaoPT
     * @param otpEntity
     */
    public void createOtp(OtpEntity otpEntity);

    /**
     * 
     * @author: (VNEXT) BaoPT
     * @param idUser
     * @return OtpEntity
     */
    public OtpEntity getOtpCode(int idUser);

}
