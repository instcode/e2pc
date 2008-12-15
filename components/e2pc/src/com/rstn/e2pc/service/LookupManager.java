package com.rstn.e2pc.service;

import java.util.List;

import com.rstn.e2pc.model.LabelValue;

/**
 * Business Service Interface to talk to persistence layer and
 * retrieve values for drop-down choice lists.
 *
 */
public interface LookupManager extends UniversalManager {
    /**
     * Retrieves all possible roles from persistence layer
     * @return List of LabelValue objects
     */
    List<LabelValue> getAllRoles();
}
