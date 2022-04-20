package ru.itmo.invoiceseparation.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * InvoiceSeparationRequest
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-04-20T23:25:57.807+03:00")


public class InvoiceSeparationRequest   {
  @JsonProperty("invoice")
  private Integer invoice = null;

  @JsonProperty("users")
  @Valid
  private List<String> users = null;

  public InvoiceSeparationRequest invoice(Integer invoice) {
    this.invoice = invoice;
    return this;
  }

  /**
   * Amount of money spent
   * @return invoice
  **/
  @ApiModelProperty(value = "Amount of money spent")


  public Integer getInvoice() {
    return invoice;
  }

  public void setInvoice(Integer invoice) {
    this.invoice = invoice;
  }

  public InvoiceSeparationRequest users(List<String> users) {
    this.users = users;
    return this;
  }

  public InvoiceSeparationRequest addUsersItem(String usersItem) {
    if (this.users == null) {
      this.users = new ArrayList<String>();
    }
    this.users.add(usersItem);
    return this;
  }

  /**
   * Users to split money between
   * @return users
  **/
  @ApiModelProperty(value = "Users to split money between")


  public List<String> getUsers() {
    return users;
  }

  public void setUsers(List<String> users) {
    this.users = users;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InvoiceSeparationRequest invoiceSeparationRequest = (InvoiceSeparationRequest) o;
    return Objects.equals(this.invoice, invoiceSeparationRequest.invoice) &&
        Objects.equals(this.users, invoiceSeparationRequest.users);
  }

  @Override
  public int hashCode() {
    return Objects.hash(invoice, users);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InvoiceSeparationRequest {\n");
    
    sb.append("    invoice: ").append(toIndentedString(invoice)).append("\n");
    sb.append("    users: ").append(toIndentedString(users)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

