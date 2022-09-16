package com.localsearch.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Getter
public class Business {
    private List<Map<String, Object>> identities;
    private List<Map<String, Object>> categories;
    private Map<String, Object> labels;
}
