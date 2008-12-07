package com.ak.e2pc.service.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ak.e2pc.dao.UniversalDao;
import com.ak.e2pc.service.UniversalManager;

/**
 * Base class for Business Services - use this class for utility methods and
 * generic CRUD methods.
 */
public class UniversalManagerImpl implements UniversalManager {
    /**
     * Log instance for all child classes. Uses LogFactory.getLog(getClass()) from Commons Logging
     */
    protected final Log log = LogFactory.getLog(getClass());

    /**
     * UniversalDao instance, ready to charge forward and persist to the database
     */
    protected UniversalDao dao;
 
    public void setDao(UniversalDao dao) {
        this.dao = dao;
    }

    /**
     * {@inheritDoc}
     */
    public Object get(Class clazz, Serializable id) {
        return dao.get(clazz, id);
    }

    /**
     * {@inheritDoc}
     */
    public List getAll(Class clazz) {
        return dao.getAll(clazz);
    }

    /**
     * {@inheritDoc}
     */
    public void remove(Class clazz, Serializable id) {
        dao.remove(clazz, id);
    }

    /**
     * {@inheritDoc}
     */
    public Object save(Object o) {
        return dao.save(o);
    }
}
