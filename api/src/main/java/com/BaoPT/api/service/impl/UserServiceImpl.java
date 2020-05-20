/////////////////////////////////////////////////////////////////////////////
//
// © 2020 VNEXT TRAINING
//
/////////////////////////////////////////////////////////////////////////////

package com.BaoPT.api.service.impl;

import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.BaoPT.api.bean.OtpEntity;
import com.BaoPT.api.bean.UserEntity;
import com.BaoPT.api.common.EncodeDecode;
import com.BaoPT.api.dao.OtpDao;
import com.BaoPT.api.dao.UserDao;
import com.BaoPT.api.dao.UserRepository;
import com.BaoPT.api.model.JwtResponse;
import com.BaoPT.api.model.UserInfo;
import com.BaoPT.api.service.UserService;
import com.BaoPT.api.utils.ApiValidateExeption;
import com.BaoPT.api.utils.Constant;
import com.BaoPT.api.utils.GenerateOtp;
import com.BaoPT.api.utils.JwtTokenUtil;
import com.BaoPT.api.utils.Validate;

/**
 * [OVERVIEW] UserServiceImpl.
 *
 * @author: (VNEXT) BaoPT
 * @version: 1.0
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2020/04/14      (VNEXT) BaoPT       Create new
*/

@Service
public class UserServiceImpl implements UserService {

    private static final Log log = LogFactory.getLog(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Autowired
    private EncodeDecode encodeDecode;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired(required = true)
    private JwtTokenUtil jwtTokenUtil;
    
    @Autowired
    public JavaMailSender emailSender;
    
    @Autowired
    public OtpDao otpDao;

    @Override
    public List<UserEntity> getAll() {
        List<UserEntity> userList = userDao.getAll();
        return userList;
    }

    /**
     * @author (VNEXT) BaoPT
     * @param json
     * @return Register User
     */
    @Override
    public UserEntity register(String json) throws ApiValidateExeption {
        log.debug("### Register Start ###");
        // Check If User Exist => throw Exception
        JSONObject userJson = new JSONObject(json);
        if (userJson.isEmpty()) {
            throw new ApiValidateExeption(Constant.BAD_REQUEST, "Please Enter All Field");
        } else {
            UserEntity userEntity = new UserEntity();
            if (!userJson.getString("password").matches(Validate.PASSWORD)) {
                throw new ApiValidateExeption(Constant.BAD_REQUEST,
                        "Password Must Have Less Then 8 Character, Must Have Character, Number And Special Character. Ex: Bao@123");
            }
            userEntity.setUsername(userJson.getString("name"));
            userEntity.setSdt(null);
            userEntity.setDayOfBirth(null);
            userEntity.setPassword(this.encodeDecode.encode(userJson.getString("password")));
            userEntity.setMonney(0);
            userEntity.setIdBank(0);
            userEntity.setEmail(userJson.getString("email"));
            userEntity.setStatusUser(0);
            userDao.register(userEntity);
            log.debug("### Register End ###");
            return userEntity;
        }
    }

    /**
     * @author (VNEXT) BaoPT
     * @param id
     * @param json
     * @param Token (UUID)
     * @return Update User Info
     */
    @Override
    public UserEntity update(String json, int id) throws ApiValidateExeption {
        log.debug("### Update User Start ###");
        JSONObject userJson = new JSONObject(json);
        if (userJson.isEmpty()) {
            throw new ApiValidateExeption(Constant.BAD_REQUEST, "Please Enter All Field");
        } else {
            UserEntity userUpdate = userDao.getUserById(id);

            if (!userJson.getString("name").trim().matches(Validate.USER_NAME)) {
                throw new ApiValidateExeption(Constant.BAD_REQUEST, "Name Have To Be Less Than 10 Character");
            } else if (userJson.getString("sdt").length() < 10 || userJson.getString("sdt").length() > 11) {
                throw new ApiValidateExeption(Constant.BAD_REQUEST, "Phone Number Is Between 10 and 11 Number");
            } else if (!userJson.getString("day_of_birth").trim().matches(Validate.DATE)) {
                throw new ApiValidateExeption(Constant.BAD_REQUEST, "Day of birth must like ex: 1999/08/27");
            } else {
                userUpdate.setUsername(userJson.getString("name"));
                userUpdate.setSdt(userJson.getString("sdt"));
                userUpdate.setDayOfBirth(userJson.getString("day_of_birth"));
            }

            userDao.update(userUpdate);
            log.debug("### Update User End ###");
            return userUpdate;
        }
    }

    /**
     * @author (VNEXT) BaoPT
     * @param id
     * @param Token (UUID)
     * @return Get User Info
     */
    @Override
    public UserInfo getInfoUser(int id) throws ApiValidateExeption {
        log.debug("### Get User Info Start ###");
        UserInfo userInfo = userDao.getInforUser(id);
        if (userInfo == null) {
            throw new ApiValidateExeption(Constant.BAD_REQUEST, "User Is Not Exist");
        }
        String dobFormat = userInfo.getDob().replace("-", "/");
        userInfo.setDob(dobFormat);
        log.debug("### Get User Info End ###");
        return userInfo;
    }

    /**
     * @author (VNEXT) BaoPT
     * @param id
     * @param json
     * @param Token (UUID)
     * @return Change Password User
     */
    @Override
    public UserEntity changePassword(int id, String json) throws ApiValidateExeption {
        log.debug("### Change Password Start ###");
        UserEntity userUpdatePassword = userDao.getUserById(id);
        JSONObject userJson = new JSONObject(json);
        if (userUpdatePassword == null) {
            throw new ApiValidateExeption(Constant.BAD_REQUEST, "User Is Not Exist");
        } else if (userJson.isEmpty()) {
            throw new ApiValidateExeption(Constant.BAD_REQUEST, "Please Enter All Field");
        } else if (!userJson.getString("password").matches(Validate.PASSWORD)) {
            throw new ApiValidateExeption(Constant.BAD_REQUEST,
                    "Password Must Have Less Then 8 Character, Must Have Character, Number And Special Character. Ex: Bao@123");
        } else {
            userUpdatePassword.setPassword(this.encodeDecode.encode(userJson.getString("password")));
            userDao.update(userUpdatePassword);
            log.debug("### Change Password End ###");
            return userUpdatePassword;
        }
    }

    /**
     * @author (VNEXT) BaoPT
     * @param username
     * @return UserEntity
     */
    @Override
    public UserEntity  getByUsername(String username) {
        log.debug("### getByUsername Start ###");
        Optional<UserEntity> userOptional = userRepository.findByUsername(username);
        log.debug("### getByUsername End ###");
        return userOptional.orElse(null);
    }

    /**
     * login
     * @author (VNEXT) BaoPT
     * @param json
     * @return JwtResponse
     */
    @Override
    public JwtResponse login(String json) throws ApiValidateExeption, Exception {
        log.debug("### login START ###");
        JSONObject jsonObject = new JSONObject(json);
        if (jsonObject.isEmpty()) {
            throw new ApiValidateExeption(Constant.NOT_FOUND, "faild", "not found");
        }
        String email = jsonObject.getString("email");
//        System.out.println(email);
//        int id = jsonObject.getInt("id");
        UserEntity userLogin = this.userDao.getUserByEmail(email);
        if (userLogin == null) {
            throw new ApiValidateExeption(Constant.BAD_REQUEST, "User Is Not Exits");
        }
        String username = userLogin.getUsername();
        String password = this.encodeDecode.encode(jsonObject.getString("password"));
        //        authenticate(username, password);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (!password.equals(userDetails.getPassword())) {
            throw new ApiValidateExeption(Constant.BAD_REQUEST, "Password Is Not Right");
        } else {
            String token = jwtTokenUtil.generateToken(userDetails);
            UserEntity user = this.getByUsername(username);
            UserInfo userRes = this.userDao.getInforUser(user.getIdUser());
            JwtResponse jwtResponse = new JwtResponse(token, userRes);
            log.debug("### login END ###");
            return jwtResponse;
        }
    }

    @Override
    public String otpNumber(String json) throws ApiValidateExeption, Exception {
        SimpleMailMessage message = new SimpleMailMessage();
        JSONObject userJson = new JSONObject(json);
        String email = userJson.getString("email");
        char[] otp = GenerateOtp.generateOtpNumber(6);
        int otpSender = Integer.parseInt(String.valueOf(otp));
        message.setTo(email);
        message.setSubject("Your OTP From Our Website");
        message.setText("Hello, " + email + ", Your OTP Is: " + otpSender);
        this.emailSender.send(message);
        OtpEntity otpEntity = new OtpEntity();
        otpEntity.setIdUser(userJson.getInt("id_user"));
        otpEntity.setOtpNumber(otpSender);
        this.otpDao.createOtp(otpEntity);
        return "Send Otp Success. Please Check Your Email";
    }

    @Override
    public UserEntity activeUser(String json) throws ApiValidateExeption, Exception {
        JSONObject otpJson = new JSONObject(json);
        int id = otpJson.getInt("id_user");
        UserEntity activeUser = userDao.getUserById(id);
        OtpEntity otpEntity = this.otpDao.getOtpCode(id);
        int otpCode = otpEntity.getOtpNumber();
        if (activeUser == null) {
            throw new ApiValidateExeption(Constant.BAD_REQUEST, "User Is Not Exist");
        } else if (otpJson.isEmpty()) {
            throw new ApiValidateExeption(Constant.BAD_REQUEST, "Please Enter All Field");
        } else if (otpJson.getInt("otp") != otpCode) {
            throw new ApiValidateExeption(Constant.BAD_REQUEST, "Otp Code Is Not Right");
        }
        activeUser.setStatusUser(1);
        userDao.update(activeUser);
        return activeUser;
    }
}
