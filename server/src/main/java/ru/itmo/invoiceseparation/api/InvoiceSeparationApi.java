package ru.itmo.invoiceseparation.api;

import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itmo.invoiceseparation.model.Body;

import javax.validation.Valid;

@Api(value = "invoice-separation")
@RequestMapping(value = "")
public interface InvoiceSeparationApi {

    @ApiOperation(value = "", nickname = "invoiceSeparationPost", tags = {})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 403, message = "Unauthorized"),
            @ApiResponse(code = 404, message = "No such user. If any user in list is missing")})
    @PostMapping(value = "/invoice-separation")
    ResponseEntity<Void> invoiceSeparationPost(@ApiParam(required = true) @Valid @RequestBody Body body);
}
