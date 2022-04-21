package ru.itmo.invoiceseparation.api;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import ru.itmo.invoiceseparation.model.ApiToken;
import ru.itmo.invoiceseparation.model.ApiTokenRepository;
import ru.itmo.invoiceseparation.model.User;
import ru.itmo.invoiceseparation.model.UserRepository;


import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.util.stream.Collectors;

@Controller
public class ContactsApiController implements ContactsApi {

    private static final Logger log = LoggerFactory.getLogger(ContactsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ApiTokenRepository apiTokenRepository;

    @Autowired
    public ContactsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<List<String>> contactsGet(String apiToken) {
        ApiToken token = apiTokenRepository.findById(apiToken);
        if (token == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        User user = token.getUser();
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        List<String> contactUsernames = user.getContacts()
            .stream()
            .map(User::getUsername)
            .collect(Collectors.toList());
        return ResponseEntity.ok(contactUsernames);
    }

    public ResponseEntity<Void> contactsPost(String apiToken, List<String> body) {
        ApiToken token = apiTokenRepository.findById(apiToken);
        if (token == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        User user = token.getUser();
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        List<User> userContacts = user.getContacts();
        for (String username : body) {
            User contact = userRepository.findByUsername(username);
            if (contact == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            if (!userContacts.contains(contact) && !user.getUsername().equals(username)) {
                userContacts.add(contact);
            }
        }
        userRepository.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
