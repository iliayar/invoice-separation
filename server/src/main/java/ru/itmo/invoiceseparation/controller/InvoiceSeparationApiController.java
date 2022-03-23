package ru.itmo.invoiceseparation.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itmo.invoiceseparation.api.InvoiceSeparationApi;
import ru.itmo.invoiceseparation.model.Body;

@RestController
public class InvoiceSeparationApiController implements InvoiceSeparationApi {

    public ResponseEntity<Void> invoiceSeparationPost(Body body) {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
