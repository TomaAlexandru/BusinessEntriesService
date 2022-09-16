package com.localsearch.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.localsearch.models.BusinessEntry;
import com.localsearch.models.Where;
import okhttp3.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class BusinessesService {

    @Autowired
    RestApiService restApiService;

    @Autowired
    CacheService cacheService;

    private final Logger logger = LogManager.getLogger(BusinessesService.class);

    /**
     *
     * @param businessId
     * @return
     */
    public List<String> getBusinessNames(final String businessId) {
        BusinessEntry businessEntry = this.getEntry(businessId);
        return businessEntry
                .getAddresses()
                .stream()
                .map(
                    address -> {
                        List<String> names = address
                            .getBusiness()
                            .getIdentities()
                            .stream()
                            .map(a -> a.get("name").toString())
                            .collect(Collectors.toList());
                        return String.join(",", names);
                    }
                )
                .collect(Collectors.toList());
    }

    public List<Map<String, Object>> getBusinessAddresses(final String businessId) {
        BusinessEntry businessEntry = this.getEntry(businessId);
        return businessEntry
                .getAddresses()
                .stream()
                .map(
                    address -> {
                        Where where = address.getWhere();
                        return new HashMap<String, Object>() {{
                            put("street", where.getStreet());
                            put("city", where.getCity());
                            put("state", where.getState());
                            put("zipcode", where.getZipcode());
                        }};
                    }
                )
                .collect(Collectors.toList());
    }

    public BusinessEntry getEntry(final String businessId) {
        String entryDetails = this.cacheService
                                .getKeyValue(businessId)
                                .orElseGet(() -> {
                                    String businessEntry = this.restApiService.getEntry(businessId);
                                    this.cacheService.setKeyValue(businessId, businessEntry);
                                    return businessEntry;
                                });

        try {
            return new ObjectMapper().readValue(entryDetails, BusinessEntry.class);
        } catch (IOException e) {
            logger.error("IOException: local storage request could not have been performed");
            e.printStackTrace();
            return null;
        }
    }
}
