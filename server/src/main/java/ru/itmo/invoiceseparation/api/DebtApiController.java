package ru.itmo.invoiceseparation.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import ru.itmo.invoiceseparation.model.ApiToken;
import ru.itmo.invoiceseparation.model.ApiTokenRepository;
import ru.itmo.invoiceseparation.model.Debt;
import ru.itmo.invoiceseparation.model.DebtRepository;
import ru.itmo.invoiceseparation.model.User;
import ru.itmo.invoiceseparation.model.UserRepository;
import ru.itmo.invoiceseparation.model.UsernameRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class DebtApiController implements DebtApi {

    private static final Logger log = LoggerFactory.getLogger(DebtApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ApiTokenRepository apiTokenRepository;

    @Autowired
    private DebtRepository debtRepository;

    @Autowired
    public DebtApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    private Integer getDebt(User from, User to) {
        Integer resultDebt = 0;

        for (Debt debt : debtRepository.findByFromAndTo(to, from)) {
            resultDebt += debt.getAmount();
        }

        for (Debt debt : debtRepository.findByFromAndTo(from, to)) {
            resultDebt -= debt.getAmount();
        }

        return resultDebt;
    }

    public ResponseEntity<Integer> debtGet(String user, String xApiKey) {
        ApiToken token = apiTokenRepository.findById(xApiKey);
        if (token == null) {
            return new ResponseEntity<Integer>(HttpStatus.UNAUTHORIZED);
        }

        User fromUser = token.getUser();
        if (fromUser == null) {
            return new ResponseEntity<Integer>(HttpStatus.UNAUTHORIZED);
        }

        User toUser = userRepository.findByUsername(user);
        if (toUser == null) {
            return new ResponseEntity<Integer>(HttpStatus.NOT_FOUND);
        }

        if (fromUser.getUsername().equals(toUser.getUsername())) {
            return new ResponseEntity<Integer>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<Integer>(getDebt(fromUser, toUser), HttpStatus.OK);
    }

    public ResponseEntity<Integer> debtPost(UsernameRequest body, String xApiKey) {
        ApiToken token = apiTokenRepository.findById(xApiKey);
        if (token == null) {
            return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
        }

        User fromUser = token.getUser();
        if (fromUser == null) {
            return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
        }

        User toUser = userRepository.findByUsername(body.getUsername());
        if (toUser == null) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }

        if (fromUser.getUsername().equals(toUser.getUsername())) {
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }

        Integer debtAmount = getDebt(fromUser, toUser);
        if (debtAmount > 0) {
            debtRepository.deleteByFromAndTo(toUser, fromUser);
            debtRepository.deleteByFromAndTo(fromUser, toUser);
        } else {
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<Integer>(debtAmount, HttpStatus.OK);
    }

}
