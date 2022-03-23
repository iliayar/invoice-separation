package ru.itmo.invoiceseparation.api;

import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itmo.invoiceseparation.model.Credentials;

import javax.validation.Valid;

@Validated
@Api(value = "user")
@RequestMapping(value = "/user")
public interface UserApi {

    @ApiOperation(value = "", nickname = "userLoginPost", tags = {})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 401, message = "Invalid credentials"),
            @ApiResponse(code = 403, message = "No such user or password missmatch")})
    @PostMapping(value = "/login")
    ResponseEntity<Void> userLoginPost(@ApiParam(required = true) @Valid @RequestBody Credentials body);


    @ApiOperation(value = "", nickname = "userRegisterPost", tags = {})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 401, message = "Invalid credentials")})
    @PostMapping(value = "/register")
    ResponseEntity<Void> userRegisterPost(@ApiParam(required = true) @Valid @RequestBody Credentials body);

}
