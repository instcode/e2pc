package com.rstn.e2pc.dao;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertNotNull;
import static org.testng.AssertJUnit.assertTrue;
import static org.testng.AssertJUnit.fail;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.testng.annotations.Test;

import com.rstn.e2pc.Constants;
import com.rstn.e2pc.dao.RoleDao;
import com.rstn.e2pc.dao.UserDao;
import com.rstn.e2pc.model.Address;
import com.rstn.e2pc.model.Role;
import com.rstn.e2pc.model.User;

public class UserDaoTest extends BaseDaoTestCase {
    private UserDao dao = null;
    private RoleDao rdao = null;
    
    public void setUserDao(UserDao dao) {
        this.dao = dao;
    }
    
    public void setRoleDao(RoleDao rdao) {
        this.rdao = rdao;
    }

    @Test
    public void testGetUserInvalid() throws Exception {
        try {
            dao.get(1000L);
            fail("'badusername' found in database, failing test...");
        } catch (DataAccessException d) {
            assertTrue(d != null);
        }
    }

    @Test
    public void testGetUser() throws Exception {
        User user = dao.get(-1L);

        assertNotNull(user);
        assertEquals(1, user.getRoles().size());
        assertTrue(user.isEnabled());
    }

    @Test
    public void testGetUserPassword() throws Exception {
        User user = dao.get(-1L);
        String password = dao.getUserPassword(user.getUsername());
        assertNotNull(password);
        log.debug("password: " + password);
    }

    @Test
    public void testUpdateUser() throws Exception {
        User user = dao.get(-1L);

        Address address = user.getAddress();
        address.setAddress("new address");

        dao.saveUser(user);
        flush();

        user = dao.get(-1L);
        assertEquals(address, user.getAddress());
        assertEquals("new address", user.getAddress().getAddress());
        
        // verify that violation occurs when adding new user with same username
        user.setId(null);

        endTransaction();

        try {
            dao.saveUser(user);
            flush();
            fail("saveUser didn't throw DataIntegrityViolationException");
        } catch (DataIntegrityViolationException e) {
            assertNotNull(e);
            log.debug("expected exception: " + e.getMessage());
        }
    }

    @Test
    public void testAddUserRole() throws Exception {
        User user = dao.get(-1L);
        assertEquals(1, user.getRoles().size());

        Role role = rdao.getRoleByName(Constants.ADMIN_ROLE);
        user.addRole(role);
        user = dao.saveUser(user);
        flush();

        user = dao.get(-1L);
        assertEquals(2, user.getRoles().size());

        //add the same role twice - should result in no additional role
        user.addRole(role);
        dao.saveUser(user);
        flush();

        user = dao.get(-1L);
        assertEquals("more than 2 roles", 2, user.getRoles().size());

        user.getRoles().remove(role);
        dao.saveUser(user);
        flush();

        user = dao.get(-1L);
        assertEquals(1, user.getRoles().size());
    }

    @Test
    public void testUserExists() throws Exception {
        boolean b = dao.exists(-1L);
        assertTrue(b);
    }
    
    @Test
    public void testUserNotExists() throws Exception {
        boolean b = dao.exists(111L);
        assertFalse(b);
    }
}
