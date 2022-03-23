package ru.itmo.invoiceseparation.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;

public class Body   {
  @JsonProperty("invoice")
  private Integer invoice = null;

  @JsonProperty("users")
  @Valid
  private List<String> users = null;

  public Body invoice(Integer invoice) {
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

  public Body users(List<String> users) {
    this.users = users;
    return this;
  }

  public Body addUsersItem(String usersItem) {
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
    Body body = (Body) o;
    return Objects.equals(this.invoice, body.invoice) &&
        Objects.equals(this.users, body.users);
  }

  @Override
  public int hashCode() {
    return Objects.hash(invoice, users);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Body {\n");
    
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

