package com.localsearch.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Getter;

import java.util.Map;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Getter
public class Where {
    private String street;
    private String city;
    private String state;
    private Map<String, Object> geography;
    private String house_number;
    private Integer zipcode;
}
