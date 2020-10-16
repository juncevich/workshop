package com.appdeveloperblog.ws.api.resourceserver.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRest {
    private String id;
    private String firstName;
    private String lastName;
}
