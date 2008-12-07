package com.ak.e2pc.dao.hibernate;

import java.util.List;

import com.ak.e2pc.dao.LookupDao;
import com.ak.e2pc.model.Role;

/**
 * Hibernate implementation of LookupDao.
 *
 */
public class LookupDaoHibernate extends UniversalDaoHibernate implements LookupDao {

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<Role> getRoles() {
        log.debug("Retrieving all role names...");

        return getHibernateTemplate().find("from Role order by name");
    }
}
