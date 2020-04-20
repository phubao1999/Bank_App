/////////////////////////////////////////////////////////////////////////////
//
// © 2020 VNEXT TRAINING
//
/////////////////////////////////////////////////////////////////////////////

package com.BaoPT.api.controller;

import java.util.List;
import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
import com.BaoPT.api.model.TransfferMoney;
import com.BaoPT.api.service.TranfferService;
import com.BaoPT.api.service.impl.UserServiceImpl;
import com.BaoPT.api.utils.ApiValidateExeption;

/**
 * [OVERVIEW] TransferController.
 *
 * @author: (VNEXT) BaoPT
 * @version: 1.0
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2020/04/15      (VNEXT) BaoPT       Create new
*/

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping("/transfer")
@RestController
public class TransferController {

    private static final Log log = LogFactory.getLog(UserServiceImpl.class);

    @Autowired
    private TranfferService tranfferService;

    /**
     * @author (VNEXT) BaoPT
     * @param req.header token
     * @param req.param id_user
     * @param req.body String Json
     * @param "monney"
     * @return Update Money of user and send Data To Call Api Create Transaction (Add Monney)
     */
    @RequestMapping(value = "/add-monney", method = RequestMethod.PUT, produces = { MediaType.APPLICATION_JSON_VALUE })
    public @ResponseBody ResultBean updateMonney(@RequestHeader UUID token, @RequestParam Integer id, @RequestBody String json) {
        log.debug("### Add Money Start ###");
        ResultBean resultBean = null;
        List<TransfferMoney> tranffer = null;
        try {
            tranffer = tranfferService.addMonney(id, json, token);
        } catch (ApiValidateExeption e) {
            return resultBean = new ResultBean(e.getCode(), e.getField(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        resultBean = new ResultBean(tranffer, "200", "Update Monney Success");
        log.debug("### Add Money End ###");
        return resultBean;
    }

    /**
     * @author (VNEXT) BaoPT
     * @param req.header token
     * @param req.param id_user
     * @param req.body String Json
     * @param "monney"
     * @return Update Money of user and send Data To Call Api Create Transaction (Minus Money)
     */
    @RequestMapping(value = "/tranffer-monney", method = RequestMethod.PUT, produces = { MediaType.APPLICATION_JSON_VALUE })
    public @ResponseBody ResultBean tranfferMonney(@RequestHeader UUID token, @RequestParam Integer id, @RequestBody String json) {
        log.debug("### Tranffer Money Start ###");
        ResultBean resultBean = null;
        List<TransfferMoney> tranffer = null;
        try {
            tranffer = tranfferService.tranfferMonney(id, json, token);
        } catch (ApiValidateExeption e) {
            return resultBean = new ResultBean(e.getCode(), e.getField(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        resultBean = new ResultBean(tranffer, "200", "Update Monney Success");
        log.debug("### Tranffer Money End ###");
        return resultBean;
    }

    /**
     * @author (VNEXT) BaoPT
     * @param req.header token
     * @param req.param id_user
     * @param req.body String Json
     * @param "monney"
     * @return Update Money of user and send Data To Call Api Create Transaction (Send Money)
     */
    @RequestMapping(value = "/send-monney", method = RequestMethod.PUT, produces = { MediaType.APPLICATION_JSON_VALUE })
    public @ResponseBody ResultBean sendMonney(@RequestHeader UUID token, @RequestParam Integer id, @RequestBody String json) {
        log.debug("### Send Money Start ###");
        ResultBean resultBean = null;
        List<TransfferMoney> tranffer = null;
        try {
            tranffer = tranfferService.sendMonney(id, json, token);
        } catch (ApiValidateExeption e) {
            return resultBean = new ResultBean(e.getCode(), e.getField(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        resultBean = new ResultBean(tranffer, "200", "Send Money Successfully");
        log.debug("### Send Money End ###");
        return resultBean;
    }

}
