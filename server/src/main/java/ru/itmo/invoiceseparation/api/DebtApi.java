package ru.itmo.invoiceseparation.api;

import ru.itmo.invoiceseparation.model.UsernameRequest;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.*;

@Validated
@Api(value = "debt", description = "the debt API")
@RequestMapping(value = "")
public interface DebtApi {

    @ApiOperation(value = "", nickname = "debtGet", notes = "", response = Integer.class, authorizations = {
        @Authorization(value = "ApiKeyAuth")
    }, tags = {})
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Ok.", response = Integer.class),
        @ApiResponse(code = 403, message = "Unauthorized"),
        @ApiResponse(code = 404, message = "No such user. If any user in list is missing")})
    @RequestMapping(value = "/debt",
        method = RequestMethod.GET)
    ResponseEntity<Integer> debtGet(@NotNull @ApiParam(value = "User Id", required = true) @Valid @RequestParam(value = "user", required = true) String user);


    @ApiOperation(value = "", nickname = "debtPost", notes = "", authorizations = {
        @Authorization(value = "ApiKeyAuth")
    }, tags = {})
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Ok"),
        @ApiResponse(code = 403, message = "Unauthorized"),
        @ApiResponse(code = 404, message = "No such user. If any user in list is missing")})
    @RequestMapping(value = "/debt",
        method = RequestMethod.POST)
    ResponseEntity<Void> debtPost(@ApiParam(value = "", required = true) @Valid @RequestBody UsernameRequest body);

}
