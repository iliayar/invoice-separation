package ru.itmo.invoiceseparation.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.itmo.invoiceseparation.api.ContactsApi;

import java.util.List;

@RestController
public class ContactsApiController implements ContactsApi {

    public List<String> contactsGet() {
        return List.of("1", "2", "3", "aboba", "AMOGUS2");
    }

    public ResponseEntity<Void> contactsPost(List<String> contacts) {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
