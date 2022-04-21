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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-04-20T23:25:57.807+03:00")

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

    @org.springframework.beans.factory.annotation.Autowired
    public DebtApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Integer> debtGet(@NotNull @ApiParam(value = "User Id", required = true) @Valid @RequestParam(value = "user", required = true) String user) {
        String apiToken = request.getHeader("X-Api-Key");

        ApiToken token = apiTokenRepository.findById(apiToken);
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

        List<Debt> debts = debtRepository.findByFromAndTo(toUser, fromUser);
        Integer resultDebt = 0;

        for (Debt debt : debts) {
            resultDebt += debt.getAmount();
        }

        return new ResponseEntity<Integer>(resultDebt, HttpStatus.OK);
    }

    public ResponseEntity<Void> debtPost(@ApiParam(value = "" ,required=true )  @Valid @RequestBody UsernameRequest body) {
        String apiToken = request.getHeader("X-Api-Key");

        ApiToken token = apiTokenRepository.findById(apiToken);
        if (token == null) {
            return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
        }

        User fromUser = token.getUser();
        if (fromUser == null) {
            return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
        }

        log.info("Trying find toUser by username " + body.getUsername());
        User toUser = userRepository.findByUsername(body.getUsername());
        if (toUser == null) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }

        if (fromUser.getUsername().equals(toUser.getUsername())) {
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }

        debtRepository.deleteByFromAndTo(toUser, fromUser);

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
