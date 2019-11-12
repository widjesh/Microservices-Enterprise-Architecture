package edu.mum.cs.product.service.template;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * The type User.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    private long id;

    private String firstname;

    private String lastname;

    private String email;

    private String password;

    private String address;

}
