package ru.itmo.invoiceseparation.api;

import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@Api(value = "contacts")
@RequestMapping(value = "")
public interface ContactsApi {

    @ApiOperation(value = "", nickname = "contactsGet", response = String.class, responseContainer = "List", tags = {})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List of all contacts", response = String.class, responseContainer = "List"),
            @ApiResponse(code = 403, message = "Unauthorized")})
    @GetMapping(value = "/contacts")
    List<String> contactsGet();


    @ApiOperation(value = "", nickname = "contactsPost", tags = {})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 403, message = "Unauthorized"),
            @ApiResponse(code = 404, message = "No such user")})
    @PostMapping(value = "/contacts")
    ResponseEntity<Void> contactsPost(@ApiParam(required = true) @Valid @RequestBody List<String> contacts);
}
