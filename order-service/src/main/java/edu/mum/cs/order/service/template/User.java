package edu.mum.cs.order.service.template;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

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
