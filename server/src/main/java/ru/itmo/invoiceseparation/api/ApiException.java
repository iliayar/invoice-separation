package ru.itmo.invoiceseparation.api;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-05-26T02:26:17.516+03:00")

public class ApiException extends Exception{
    private int code;
    public ApiException (int code, String msg) {
        super(msg);
        this.code = code;
    }
}
