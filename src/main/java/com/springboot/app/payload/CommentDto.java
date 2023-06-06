package com.springboot.app.payload;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;



@Api(value = "Comment model information")
@Data
public class CommentDto {

    public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	@ApiModelProperty(value = "Comment id")
    private long id;
    // name should not be null or empty
    @ApiModelProperty(value = "Comment name")
    @NotEmpty(message = "Name should not be null or empty")
    private String name;

    // email should not be null or empty
    // email field validation
    @ApiModelProperty(value = "Comment email")
    @NotEmpty(message = "Email should not be null or empty")
    @Email
    private String email;

    // comment body should not be bull or empty
    // Comment body must be minimum 10 characters
    @NotEmpty
    @ApiModelProperty(value = "Comment body")
    @Size(min = 10, message = "Comment body must be minimum 10 characters")
    private String body;
}
