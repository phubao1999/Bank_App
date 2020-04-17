/////////////////////////////////////////////////////////////////////////////
//
// © 2020 VNEXT TRAINING
//
/////////////////////////////////////////////////////////////////////////////

package com.BaoPT.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.BaoPT.api.bean.ResultBean;
import com.BaoPT.api.bean.TransEntity;
import com.BaoPT.api.service.TransService;
import com.BaoPT.api.utils.ApiValidateExeption;

/**
 * [OVERVIEW] TransController.
 *
 * @author: (VNEXT) BaoPT
 * @version: 1.0
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2020/04/14      (VNEXT) BaoPT       Create new
*/

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping("/transaction")
@RestController
public class TransController {

    @Autowired
    private TransService transService;

    /**
     * @return Create New Transaction
     */

    @RequestMapping(value = "/trans", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody ResultBean create(@RequestBody String json) {
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
        resultBean = new ResultBean(transEntity, "200", "Create Transaction Success");
        return resultBean;
    }

    /**
     * 
     * @param id
     * @return Get History Trans
     */
    @RequestMapping(value = "/trans", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResultBean getAll(@RequestParam Integer id) {
        List<TransEntity> transEntity = null;
        ResultBean resultBean = null;
        try {
            transEntity = transService.getAllById(id);
        } catch (ApiValidateExeption e) {
            return resultBean = new ResultBean(e.getCode(), e.getField(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        resultBean = new ResultBean(transEntity, "200", "Done");
        return resultBean;
    }

    /**
     * 
     * @param id
     * @return Filter History Trans
     */
    @RequestMapping(value = "/trans/filter", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResultBean filter(@RequestParam Integer id, @RequestBody String json) {
        List<TransEntity> transEntity = null;
        ResultBean resultBean = null;
        try {
            transEntity = transService.filter(id, json);
        } catch (ApiValidateExeption e) {
            return resultBean = new ResultBean(e.getCode(), e.getField(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        resultBean = new ResultBean(transEntity, "200", "Done");
        return resultBean;
    }

}
