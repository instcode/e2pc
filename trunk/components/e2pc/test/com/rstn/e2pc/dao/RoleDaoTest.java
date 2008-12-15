package com.rstn.e2pc.dao;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;
import static org.testng.AssertJUnit.assertNull;
import static org.testng.AssertJUnit.assertTrue;

import java.util.HashMap;
import java.util.List;

import org.testng.annotations.Test;

import com.rstn.e2pc.Constants;
import com.rstn.e2pc.model.Role;

public class RoleDaoTest extends BaseDaoTestCase {
    private RoleDao dao;

    public void setRoleDao(RoleDao dao) {
        this.dao = dao;
    }

    @Test
    public void testGetRoleInvalid() throws Exception {
        Role role = dao.getRoleByName("badrolename");
        assertNull(role);
    }

    @Test
    public void testGetRole() throws Exception {
        Role role = dao.getRoleByName(Constants.USER_ROLE);
        assertNotNull(role);
    }

    @Test
    public void testUpdateRole() throws Exception {
        Role role = dao.getRoleByName("ROLE_USER");
        role.setDescription("test descr");
        dao.save(role);
        flush();
        
        role = dao.getRoleByName("ROLE_USER");
        assertEquals("test descr", role.getDescription());
    }

    /**
     * Tests the generic findByNamedQuery method
     * @throws Exception
     */
    @Test
    public void testFindByNamedQuery() throws Exception {
        HashMap<String, Object> queryParams = new HashMap<String, Object>();
        queryParams.put("name", Constants.USER_ROLE);
        List<Role> roles = dao.findByNamedQuery("findRoleByName", queryParams);
        assertNotNull(roles);
        assertTrue(roles.size() > 0);
    }
}
