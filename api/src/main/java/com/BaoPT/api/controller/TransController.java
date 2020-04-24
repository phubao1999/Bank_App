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
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.BaoPT.api.bean.ResultBean;
import com.BaoPT.api.bean.TransEntity;
import com.BaoPT.api.service.TransService;
import com.BaoPT.api.service.impl.UserServiceImpl;
import com.BaoPT.api.utils.ApiValidateExeption;
import com.BaoPT.api.utils.Constant;

/**
 * [OVERVIEW] TransController.
 *
 * @author: (VNEXT) BaoPT
 * @version: 1.0
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2020/04/18      (VNEXT) BaoPT       Create new
*/

@RequestMapping("/transaction")
@RestController
public class TransController {

    private static final Log log = LogFactory.getLog(UserServiceImpl.class);

    @Autowired
    private TransService transService;

    /**
     * @author (VNEXT) BaoPT
     * @param req.body String json
     * @return Create New Transaction
     */

    @RequestMapping(value = "/trans", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody ResultBean create(@RequestBody String json) {
        log.debug("### Create Transaction Start ###");
        List<TransEntity> transEntity = null;
        ResultBean resultBean = null;
        try {
            transEntity = transService.create(json);
        } catch (ApiValidateExeption e) {
            resultBean = new ResultBean(e.getCode(), e.getField(), e.getMessage());
            return resultBean;
        } catch (Exception e) {
            e.printStackTrace();
        }
        resultBean = new ResultBean(transEntity, Constant.OK, "Create Transaction Success");
        log.debug("### Create Transaction End ###");
        return resultBean;
    }

    /**
     * @author (VNEXT) BaoPT
     * @param req.param id_user
     * @param req.header token
     * @return Get History Trans
     */
    @RequestMapping(value = "/trans", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResultBean getAll(@RequestParam Integer id) {
        log.debug("### Get Transaction Start ###");
        List<TransEntity> transEntity = null;
        ResultBean resultBean = null;
        try {
            transEntity = transService.getAllById(id);
        } catch (ApiValidateExeption e) {
            return resultBean = new ResultBean(e.getCode(), e.getField(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        resultBean = new ResultBean(transEntity, Constant.OK, "Done");
        log.debug("### Get Transaction End ###");
        return resultBean;
    }

    /**
     * @author (VNEXT) BaoPT
     * @param req.header token
     * @param req.param id_user
     * @param req.body String json
     * @param "from"
     * @param "to"
     * @return Filter History Trans By Date and Id
     */
    @RequestMapping(value = "/trans/filter", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResultBean filter(@RequestParam Integer id, @RequestBody String json) {
        log.debug("### Get Transaction by date Start ###");
        List<TransEntity> transEntity = null;
        ResultBean resultBean = null;
        try {
            transEntity = transService.filter(id, json);
        } catch (ApiValidateExeption e) {
            return resultBean = new ResultBean(e.getCode(), e.getField(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        resultBean = new ResultBean(transEntity, Constant.OK, "Done");
        log.debug("### Get Transaction by date End ###");
        return resultBean;
    }

    /**
     * @author (VNEXT) BaoPT
     * @param req.param id_user
     * @param req.header token
     * @return Export to csv file
     */
    @RequestMapping(value = "/trans/get", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResultBean exportCsv(@RequestParam Integer id) {
        log.debug("### Export Transaction Start ###");
        List<TransEntity> transEntity = null;
        ResultBean resultBean = null;
        try {
            transEntity = transService.csvWriterByUserId(id);
        } catch (ApiValidateExeption e) {
            return resultBean = new ResultBean(e.getCode(), e.getField(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        resultBean = new ResultBean(transEntity, Constant.OK, "Done");
        log.debug("### Export Transaction End ###");
        return resultBean;
    }

}
