package com.localsearch.controllers;

import com.localsearch.models.BusinessEntry;
import com.localsearch.services.BusinessesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class BusinessesController {

    @Autowired
    BusinessesService businessesService;

    @GetMapping("/business-details/{businessEntryId}")
    public ResponseEntity<List<String>> getBusinessDetails(@PathVariable String businessEntryId) {
        return ResponseEntity.ok(businessesService.getBusinessNames(businessEntryId));
    }

    @GetMapping("/business-addresses/{businessEntryId}")
    public ResponseEntity<List<Map<String, Object>>> getBusinessAddresses(@PathVariable String businessEntryId) {
        return ResponseEntity.ok(businessesService.getBusinessAddresses(businessEntryId));
    }
}
