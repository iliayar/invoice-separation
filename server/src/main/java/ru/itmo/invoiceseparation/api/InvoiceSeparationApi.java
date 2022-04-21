package ru.itmo.invoiceseparation.api;

import ru.itmo.invoiceseparation.model.InvoiceSeparationRequest;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Validated
@Api(value = "invoice-separation", description = "the invoice-separation API")
@RequestMapping(value = "")
public interface InvoiceSeparationApi {

    @ApiOperation(value = "", nickname = "invoiceSeparationPost", notes = "", authorizations = {
        @Authorization(value = "ApiKeyAuth")
    }, tags = {})
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Ok"),
        @ApiResponse(code = 403, message = "Unauthorized"),
        @ApiResponse(code = 404, message = "No such user. If any user in list is missing")})
    @RequestMapping(value = "/invoice-separation",
        method = RequestMethod.POST)
    ResponseEntity<Void> invoiceSeparationPost(@ApiParam(value = "", required = true) @Valid @RequestBody InvoiceSeparationRequest body);

}
