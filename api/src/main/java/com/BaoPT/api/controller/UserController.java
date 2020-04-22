/////////////////////////////////////////////////////////////////////////////
//
// © 2020 VNEXT TRAINING
//
/////////////////////////////////////////////////////////////////////////////

package com.BaoPT.api.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.BaoPT.api.bean.ResultBean;
import com.BaoPT.api.bean.UserEntity;
import com.BaoPT.api.model.JwtResponse;
import com.BaoPT.api.model.UserInfo;
import com.BaoPT.api.service.UserService;
import com.BaoPT.api.service.impl.UserServiceImpl;
import com.BaoPT.api.utils.ApiValidateExeption;
import com.BaoPT.api.utils.Constant;

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

    private static final Log log = LogFactory.getLog(UserServiceImpl.class);

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
        resultBean = new ResultBean(userEntity, Constant.OK, "Done");
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
        log.debug("### Register Start ###");
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
        resultBean = new ResultBean(userEntity, Constant.OK, "Create Account Success");
        log.debug("### Register End ###");
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
    public @ResponseBody ResultBean updateUser(@RequestBody String json, @RequestParam Integer id) {
        log.debug("### update user Start ###");
        UserEntity userUpdate = null;
        ResultBean resultBean = null;
        try {
            userUpdate = userService.update(json, id);
        } catch (ApiValidateExeption e) {
            resultBean = new ResultBean(e.getCode(), e.getField(), e.getMessage());
            return resultBean;
        } catch (Exception e) {
            e.printStackTrace();
        }
        resultBean = new ResultBean(userUpdate, Constant.OK, "Update Successfully");
        log.debug("### update user End ###");
        return resultBean;
    }

    /**
     * @author (VNEXT) BaoPT
     * @param req.header token
     * @param req.param id_user
     * @return Get Info Of User
     */

    @RequestMapping(value = "/info", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    public @ResponseBody ResultBean getUserInfo(@RequestParam Integer id) {
        log.debug("### Get Info User Start ###");
        UserInfo userInfo = null;
        ResultBean resultBean = null;
        try {
            userInfo = userService.getInfoUser(id);
        } catch (ApiValidateExeption e) {
            return resultBean = new ResultBean(e.getCode(), e.getField(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        resultBean = new ResultBean(userInfo, Constant.OK, "Done");
        log.debug("### Get Info User End ###");
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
    public @ResponseBody ResultBean changePassword(@RequestParam Integer id, @RequestBody String json) {
        log.debug("### Change Password User Start ###");
        UserEntity userEntity = null;
        ResultBean resultBean = null;
        try {
            userEntity = userService.changePassword(id, json);
        } catch (ApiValidateExeption e) {
            return resultBean = new ResultBean(e.getCode(), e.getField(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        resultBean = new ResultBean(userEntity, Constant.OK, "Change Password Successfully");
        log.debug("### Change Password User End ###");
        return resultBean;
    }

    /**
     * loginToken
     * @author: (VNEXT) BaoPT
     * @param json
     * @return Return User With Token
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResultBean> loginToken(@RequestBody String json) {
        log.debug("### loginToken START ###");
        JwtResponse jwtResponse = null;
        ResultBean resultBean = null;
        try {
            jwtResponse = userService.login(json);
        } catch (ApiValidateExeption e) {
            resultBean = new ResultBean(e.getCode(), e.getField(), e.getMessage());
            return new ResponseEntity<ResultBean>(resultBean, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            resultBean = new ResultBean("401", e.getMessage());
            return new ResponseEntity<ResultBean>(resultBean, HttpStatus.UNAUTHORIZED);
        }
        resultBean = new ResultBean(jwtResponse, "200", "OK");
        log.debug("### loginToken END ###");
        return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
    }

}
