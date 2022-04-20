package ru.itmo.invoiceseparation.api;

import ru.itmo.invoiceseparation.model.ApiToken;
import ru.itmo.invoiceseparation.model.ApiTokenRepository;
import ru.itmo.invoiceseparation.model.Debt;
import ru.itmo.invoiceseparation.model.DebtRepository;
import ru.itmo.invoiceseparation.model.InvoiceSeparationRequest;
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
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-04-21T00:58:09.160+03:00")

@Controller
public class InvoiceSeparationApiController implements InvoiceSeparationApi {

    private static final Logger log = LoggerFactory.getLogger(InvoiceSeparationApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ApiTokenRepository apiTokenRepository;

    @Autowired
    private DebtRepository debtRepository;

    @org.springframework.beans.factory.annotation.Autowired
    public InvoiceSeparationApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Void> invoiceSeparationPost(@ApiParam(value = "" ,required=true )  @Valid @RequestBody InvoiceSeparationRequest body) {
        String accept = request.getHeader("Accept");
        String apiToken = request.getHeader("X-Api-Key");

        ApiToken token = apiTokenRepository.findById(apiToken);
        if (token == null) {
            return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
        }

        User user = token.getUser();
        if (user == null) {
            return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
        }

        log.info("Splitting invoice of user " + user.getUsername());

        if (body.getInvoice() < 0) {
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }
        if (body.getUsers().size() == 0) {
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }

        Integer debt = (int) Math.ceil(body.getInvoice().doubleValue() / body.getUsers().size());

        log.info("Adding debt " + debt + " to each user");
        for (String username : body.getUsers()) {
            User toUser = userRepository.findByUsername(username);
            if (toUser == null || toUser.equals(user)) {
                return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
            }
            log.info("Adding debt to user " + toUser.getUsername());
            debtRepository.save(new Debt(user, toUser, debt));
        }

        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

}
