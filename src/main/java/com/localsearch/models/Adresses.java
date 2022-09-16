package com.localsearch.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Getter
public class Adresses {
    private String _class;
    private List<Object> contacts;
    private String address_id;
    private List<Object> address_types;
    private Where where;
    private List<Object> place_collections;
    private Business business;
}
