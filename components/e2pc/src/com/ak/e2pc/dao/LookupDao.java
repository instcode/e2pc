package com.ak.e2pc.dao;

import java.util.List;

import com.ak.e2pc.model.Role;

/**
 * Lookup Data Access Object (GenericDao) interface.  This is used to lookup values in
 * the database (i.e. for drop-downs).
 */
public interface LookupDao extends UniversalDao {
    //~ Methods ================================================================

    /**
     * Returns all Roles ordered by name
     * @return populated list of roles
     */
    List<Role> getRoles();
}
