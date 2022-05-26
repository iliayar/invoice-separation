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
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;


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

    @Autowired
    public InvoiceSeparationApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Void> invoiceSeparationPost(InvoiceSeparationRequest body, String xApiKey) {
        ApiToken token = apiTokenRepository.findById(xApiKey);
        if (token == null) {
            return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
        }

        User user = token.getUser();
        if (user == null) {
            return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
        }

        if (body.getInvoice() < 0) {
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }
        if (body.getUsers().size() == 0) {
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }

        Integer debtAmount = (int) Math.ceil(body.getInvoice().doubleValue() / (body.getUsers().size() + 1));

        for (String username : body.getUsers()) {
            User toUser = userRepository.findByUsername(username);
            if (toUser == null || toUser.equals(user)) {
                return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
            }
            Debt debt = new Debt(user, toUser, debtAmount);
            user.addIncomingDebt(debt);
            toUser.addOutcomingDebt(debt);

            debtRepository.save(debt);
            userRepository.save(toUser);
        }
        userRepository.save(user);

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
