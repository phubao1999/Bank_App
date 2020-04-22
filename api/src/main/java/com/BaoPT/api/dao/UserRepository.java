/////////////////////////////////////////////////////////////////////////////
//
// © 2020 VNEXT TRAINING
//
/////////////////////////////////////////////////////////////////////////////

package com.BaoPT.api.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.BaoPT.api.bean.UserEntity;

/**
 * [OVERVIEW] XXXXX.
 *
 * @author: (VNEXT) BaoPT
 * @version: 1.0
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2020/04/21      (VNEXT) BaoPT       Create new
*/
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    /**
     * findByUsername
     * @author: (VNEXT) BaoPT
     * @param username
     * @return Optional UserEntity
     */
    Optional<UserEntity> findByUsername(String username);

}
