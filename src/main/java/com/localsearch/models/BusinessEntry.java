package com.localsearch.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Getter;

import java.sql.Timestamp;
import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Getter
public class BusinessEntry {
    private String _class;
    private String language;
    private String entry_type;
    private String local_entry_id;
    private Object source;
    private Boolean favorited;
    private Object place_feedback_summary;
    private List<Adresses> addresses;
    private List<String> tags;
    private String displayed_what;
    private String displayed_where;
    private Object opening_hours;
    private Timestamp creation_date;
    private Timestamp modified_date;
    private String _update_process_type;
}
