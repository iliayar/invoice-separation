/**
 * NOTE: This class is auto generated by the swagger code generator program (2.4.19).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package ru.itmo.invoiceseparation.api;

import ru.itmo.invoiceseparation.model.InvoiceSeparationRequest;
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
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-05-26T01:45:00.234+03:00")

@Validated
@Api(value = "invoice-separation", description = "the invoice-separation API")
@RequestMapping(value = "")
public interface InvoiceSeparationApi {

    @ApiOperation(value = "", nickname = "invoiceSeparationPost", notes = "", authorizations = {
        @Authorization(value = "ApiKeyAuth")
    }, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Ok"),
        @ApiResponse(code = 400, message = "One of: * Invoice is negative number * List of users is empty "),
        @ApiResponse(code = 401, message = "You are not logged in"),
        @ApiResponse(code = 404, message = "One of: * One or more users in list not found * Current user is presented in the list ") })
    @RequestMapping(value = "/invoice-separation",
        method = RequestMethod.POST)
    ResponseEntity<Void> invoiceSeparationPost(@ApiParam(value = "" ,required=true )  @Valid @RequestBody InvoiceSeparationRequest body,@ApiParam(value = "" ,required=true) @RequestHeader(value="X-Api-Key", required=true) String xApiKey);

}
