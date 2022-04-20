/**
 * NOTE: This class is auto generated by the swagger code generator program (2.4.19).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package ru.itmo.invoiceseparation.api;

import ru.itmo.invoiceseparation.model.Body;
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
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-04-20T21:31:56.700+03:00")

@Validated
@Api(value = "invoice-separation", description = "the invoice-separation API")
@RequestMapping(value = "")
public interface InvoiceSeparationApi {

    @ApiOperation(value = "", nickname = "invoiceSeparationPost", notes = "", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Ok"),
        @ApiResponse(code = 403, message = "Unauthorized"),
        @ApiResponse(code = 404, message = "No such user. If any user in list is missing") })
    @RequestMapping(value = "/invoice-separation",
        method = RequestMethod.POST)
    ResponseEntity<Void> invoiceSeparationPost(@ApiParam(value = "" ,required=true )  @Valid @RequestBody Body body);

}
