package ru.itmo.invoiceseparation.api;

import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.*;

@Api(value = "debt")
@RequestMapping(value = "/debt")
public interface DebtApi {

    @ApiOperation(value = "", nickname = "debtGet", response = Integer.class, tags = {})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok.", response = Integer.class),
            @ApiResponse(code = 403, message = "Unauthorized"),
            @ApiResponse(code = 404, message = "No such user. If any user in list is missing")})
    @GetMapping(value = "/{userId}")
    String debtGet(@NotNull @ApiParam(value = "User Id", required = true) @Valid @PathVariable String userId);

    @ApiOperation(value = "", nickname = "debtPost", tags = {})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 403, message = "Unauthorized"),
            @ApiResponse(code = 404, message = "No such user. If any user in list is missing")})
    @PostMapping(value = "")
    ResponseEntity<Void> debtPost(@ApiParam(required = true) @Valid @RequestBody String body);
}
