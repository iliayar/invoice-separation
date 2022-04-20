/**
 * NOTE: This class is auto generated by the swagger code generator program (2.4.19).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package ru.itmo.invoiceseparation.api;

import ru.itmo.invoiceseparation.model.Credentials;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-04-20T23:25:57.807+03:00")

@Validated
@Api(value = "user", description = "the user API")
@RequestMapping(value = "")
public interface UserApi {

    @ApiOperation(value = "", nickname = "userLoginPost", notes = "", response = String.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Success", response = String.class),
        @ApiResponse(code = 401, message = "Invalid credentials"),
        @ApiResponse(code = 403, message = "No such user or password missmatch") })
    @RequestMapping(value = "/user/login",
        method = RequestMethod.POST)
    ResponseEntity<String> userLoginPost(@ApiParam(value = "" ,required=true )  @Valid @RequestBody Credentials body);


    @ApiOperation(value = "", nickname = "userRegisterPost", notes = "", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Success"),
        @ApiResponse(code = 401, message = "Invalid credentials") })
    @RequestMapping(value = "/user/register",
        method = RequestMethod.POST)
    ResponseEntity<Void> userRegisterPost(@ApiParam(value = "" ,required=true )  @Valid @RequestBody Credentials body);

}
