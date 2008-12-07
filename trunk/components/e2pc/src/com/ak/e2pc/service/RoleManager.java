package com.ak.e2pc.service;

import java.util.List;

import com.ak.e2pc.model.Role;

/**
 * Business Service Interface to handle communication between web and
 * persistence layer.
 *
 */
public interface RoleManager extends UniversalManager {
    /**
     * {@inheritDoc}
     */
    List getRoles(Role role);

    /**
     * {@inheritDoc}
     */
    Role getRole(String rolename);

    /**
     * {@inheritDoc}
     */
    Role saveRole(Role role);

    /**
     * {@inheritDoc}
     */
    void removeRole(String rolename);
}
