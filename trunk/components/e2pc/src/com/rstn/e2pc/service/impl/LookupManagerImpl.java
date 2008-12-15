package com.rstn.e2pc.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.rstn.e2pc.dao.LookupDao;
import com.rstn.e2pc.model.LabelValue;
import com.rstn.e2pc.model.Role;
import com.rstn.e2pc.service.LookupManager;


/**
 * Implementation of LookupManager interface to talk to the persistence layer.
 *
 */
public class LookupManagerImpl extends UniversalManagerImpl implements LookupManager {
    private LookupDao dao;

    /**
     * Method that allows setting the DAO to talk to the data store with.
     * @param dao the dao implementation
     */
    public void setLookupDao(LookupDao dao) {
        super.dao = dao;
        this.dao = dao;
    }

    /**
     * {@inheritDoc}
     */
    public List<LabelValue> getAllRoles() {
        List<Role> roles = dao.getRoles();
        List<LabelValue> list = new ArrayList<LabelValue>();

        for (Role role1 : roles) {
            list.add(new LabelValue(role1.getName(), role1.getName()));
        }

        return list;
    }
}
