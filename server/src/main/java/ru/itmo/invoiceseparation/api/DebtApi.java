/**
 * NOTE: This class is auto generated by the swagger code generator program (2.4.19).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package ru.itmo.invoiceseparation.api;

import ru.itmo.invoiceseparation.model.UsernameRequest;
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
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-05-26T02:26:17.516+03:00")

@Validated
@Api(value = "debt", description = "the debt API")
@RequestMapping(value = "")
public interface DebtApi {

    @ApiOperation(value = "", nickname = "debtGet", notes = "", response = Integer.class, authorizations = {
        @Authorization(value = "ApiKeyAuth")
    }, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "* Negative number: Provided user ows you * Positive number: You owe to requested user ", response = Integer.class),
        @ApiResponse(code = 400, message = "Cannot get debt to yourself"),
        @ApiResponse(code = 401, message = "You are not logged in"),
        @ApiResponse(code = 404, message = "No such user") })
    @RequestMapping(value = "/debt",
        method = RequestMethod.GET)
    ResponseEntity<Integer> debtGet(@NotNull @ApiParam(value = "User to get incoming debt from", required = true) @Valid @RequestParam(value = "user", required = true) String user,@ApiParam(value = "" ,required=true) @RequestHeader(value="X-Api-Key", required=true) String xApiKey);


    @ApiOperation(value = "", nickname = "debtPost", notes = "", response = Integer.class, authorizations = {
        @Authorization(value = "ApiKeyAuth")
    }, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "The amount of debt payed", response = Integer.class),
        @ApiResponse(code = 400, message = "Cannot pay debt to yourself or you owe nothing"),
        @ApiResponse(code = 401, message = "You are not logged in"),
        @ApiResponse(code = 404, message = "No such user") })
    @RequestMapping(value = "/debt",
        method = RequestMethod.POST)
    ResponseEntity<Integer> debtPost(@ApiParam(value = "User to pay debt to" ,required=true )  @Valid @RequestBody UsernameRequest body,@ApiParam(value = "" ,required=true) @RequestHeader(value="X-Api-Key", required=true) String xApiKey);

}
