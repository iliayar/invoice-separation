package ru.itmo.invoiceseparation.api;

import ru.itmo.invoiceseparation.model.ApiToken;
import ru.itmo.invoiceseparation.model.ApiTokenRepository;
import ru.itmo.invoiceseparation.model.Credentials;
import ru.itmo.invoiceseparation.model.User;
import ru.itmo.invoiceseparation.model.UserRepository;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;

@Controller
public class UserApiController implements UserApi {

    private static final Logger log = LoggerFactory.getLogger(UserApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ApiTokenRepository apiTokenRepository;

    @Autowired
    public UserApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<String> userLoginPost(
        @ApiParam(value = "", required = true) @Valid @RequestBody Credentials body) {
        User user = userRepository.findByUsername(body.getLogin());
        if (user != null && user.validatePassword(body.getPassword())) {
            if (user.getApiToken() == null) {
                ApiToken token = new ApiToken(user);
                user.setApiToken(token);
                apiTokenRepository.save(token);
                userRepository.save(user);
            }
            return new ResponseEntity<String>(user.getApiToken().getToken(), HttpStatus.OK);
        }
        return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
    }

    public ResponseEntity<Void> userRegisterPost(@ApiParam(value = "", required = true) @Valid @RequestBody Credentials body) {
        if (userRepository.findByUsername(body.getLogin()) != null) {
            return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
        }
        User user = new User(body.getLogin(), body.getPassword());
        userRepository.save(user);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
