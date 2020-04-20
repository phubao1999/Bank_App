/////////////////////////////////////////////////////////////////////////////
//
// © 2020 VNEXT TRAINING
//
/////////////////////////////////////////////////////////////////////////////

package com.BaoPT.api.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.BaoPT.api.bean.ResultBean;
import com.BaoPT.api.bean.UserEntity;
import com.BaoPT.api.model.UserInfo;
import com.BaoPT.api.service.UserService;
import com.BaoPT.api.utils.ApiValidateExeption;

/**
 * [OVERVIEW] UserController.
 *
 * @author: (VNEXT) BaoPT
 * @version: 1.0
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2020/04/14      (VNEXT) BaoPT       Create new
*/

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * @author BaoPT
     * @return Get All Of User
     */
    @RequestMapping(value = "/user", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResultBean getAll() {
        List<UserEntity> userEntity = null;
        ResultBean resultBean = null;
        try {
            userEntity = userService.getAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        resultBean = new ResultBean(userEntity, "200", "Done");
        return resultBean;
    }

    /**
     * @author (VNEXT) BaoPT
     * @param req.body String Json
     * @param "id"
     * @param "password"
     * @return Login
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody ResultBean login(@RequestBody String json) {
        UserEntity userEntity = null;
        ResultBean resultBean = null;
        try {
            userEntity = userService.loginById(json);
        } catch (ApiValidateExeption e) {
            resultBean = new ResultBean(e.getCode(), e.getField(), e.getMessage());
            return resultBean;
        } catch (Exception e) {
            e.printStackTrace();
        }
        resultBean = new ResultBean(userEntity, "200", "Login Successfully");
        return resultBean;
    }

    /**
     * @author (VNEXT) BaoPT
     * @param req.body String json
     * @param "name"
     * @param "sdt"
     * @param "day_of_birth"
     * @param "id_bank"
     * @param "monney"
     * @return Register User
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody ResultBean register(@RequestBody String json) {
        UserEntity userEntity = null;
        ResultBean resultBean = null;
        try {
            userEntity = userService.register(json);
        } catch (ApiValidateExeption e) {
            resultBean = new ResultBean(e.getCode(), e.getField(), e.getMessage());
            return resultBean;
        } catch (Exception e) {
            e.printStackTrace();
        }
        resultBean = new ResultBean(userEntity, "200", "Create Account Success");
        return resultBean;
    }

    /**
     * @author (VNEXT) BaoPT
     * @param req.header token
     * @param req.param id_user
     * @param req.body String Json
     * @param "name"
     * @param "sdt"
     * @param "day_of_birth"
     * @return Update User By Id
     */

    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = "application/json")
    public @ResponseBody ResultBean updateUser(@RequestHeader UUID token, @RequestBody String json, @RequestParam Integer id) {
        UserEntity userUpdate = null;
        ResultBean resultBean = null;
        try {
            userUpdate = userService.update(json, id, token);
        } catch (ApiValidateExeption e) {
            resultBean = new ResultBean(e.getCode(), e.getField(), e.getMessage());
            return resultBean;
        } catch (Exception e) {
            e.printStackTrace();
        }
        resultBean = new ResultBean(userUpdate, "200", "Update Successfully");
        return resultBean;
    }

    /**
     * @author (VNEXT) BaoPT
     * @param req.header token
     * @param req.param id_user
     * @return Get Info Of User
     */

    @RequestMapping(value = "/info", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    public @ResponseBody ResultBean getUserInfo(@RequestHeader UUID token, @RequestParam Integer id) {
        UserInfo userInfo = null;
        ResultBean resultBean = null;
        try {
            userInfo = userService.getInfoUser(id, token);
        } catch (ApiValidateExeption e) {
            return resultBean = new ResultBean(e.getCode(), e.getField(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        resultBean = new ResultBean(userInfo, "200", "Done");
        return resultBean;
    }

    /**
     * @author (VNEXT) BaoPT
     * @param req.header token
     * @param req.param id_user
     * @param req.body String Json
     * @param "password"
     * @return Change Password Of User
     */

    @RequestMapping(value = "/change-password", method = RequestMethod.PUT, produces = { MediaType.APPLICATION_JSON_VALUE })
    public @ResponseBody ResultBean changePassword(@RequestHeader UUID token, @RequestParam Integer id, @RequestBody String json) {
        UserEntity userEntity = null;
        ResultBean resultBean = null;
        try {
            userEntity = userService.changePassword(id, json, token);
        } catch (ApiValidateExeption e) {
            return resultBean = new ResultBean(e.getCode(), e.getField(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        resultBean = new ResultBean(userEntity, "200", "Change Password Successfully");
        return resultBean;
    }

}
