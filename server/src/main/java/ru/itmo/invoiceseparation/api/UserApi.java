package ru.itmo.invoiceseparation.api;

import ru.itmo.invoiceseparation.model.Credentials;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Validated
@Api(value = "user", description = "the user API")
@RequestMapping(value = "")
public interface UserApi {

    @ApiOperation(value = "", nickname = "userLoginPost", notes = "", response = String.class, tags = {})
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Success", response = String.class),
        @ApiResponse(code = 401, message = "Invalid credentials"),
        @ApiResponse(code = 403, message = "No such user or password missmatch")})
    @RequestMapping(value = "/user/login",
        method = RequestMethod.POST)
    ResponseEntity<String> userLoginPost(@ApiParam(value = "", required = true) @Valid @RequestBody Credentials body);


    @ApiOperation(value = "", nickname = "userRegisterPost", notes = "", tags = {})
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Success"),
        @ApiResponse(code = 401, message = "Invalid credentials")})
    @RequestMapping(value = "/user/register",
        method = RequestMethod.POST)
    ResponseEntity<Void> userRegisterPost(@ApiParam(value = "", required = true) @Valid @RequestBody Credentials body);

}
