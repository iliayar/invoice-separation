package ru.itmo.invoiceseparation.api;

import java.util.List;

import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Validated
@Api(value = "contacts", description = "the contacts API")
@RequestMapping(value = "")
public interface ContactsApi {

    @ApiOperation(value = "", nickname = "contactsGet", notes = "", response = String.class, responseContainer = "List", tags = {})
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "List of all contacts", response = String.class, responseContainer = "List"),
        @ApiResponse(code = 403, message = "Unauthorized")})
    @RequestMapping(value = "/contacts",
        method = RequestMethod.GET)
    ResponseEntity<List<String>> contactsGet(@RequestHeader(value="X-Api-Key") String apiToken);


    @ApiOperation(value = "", nickname = "contactsPost", notes = "", tags = {})
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Ok"),
        @ApiResponse(code = 403, message = "Unauthorized"),
        @ApiResponse(code = 404, message = "No such user")})
    @RequestMapping(value = "/contacts",
        method = RequestMethod.POST)
    ResponseEntity<Void> contactsPost(@RequestHeader(value="X-Api-Key") String apiToken,
                                      @ApiParam(value = "", required = true) @Valid @RequestBody List<String> body);

}
