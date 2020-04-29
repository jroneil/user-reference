package com.oneil.users.entity;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.junit.jupiter.api.Test;

import com.oneil.users.EntityTests;

class UserTest implements EntityTests {
	@Test
	void dependentAssertions() {
		Set<Permission> permSet = new HashSet<Permission>();
		permSet.add(new Permission("view"));
		permSet.add(new Permission("read"));
		permSet.add(new Permission("execute"));
		Set<Permission> permSet1 = new HashSet<Permission>();
		permSet1.add(new Permission("view"));
		permSet1.add(new Permission("read"));
		Set<Permission> permSet3 = new HashSet<Permission>();
		permSet3.add(new Permission("view"));

		Set<Role> roleSet = new HashSet<Role>();
		roleSet.add(new Role("Admin", permSet));

		User user1 = new User("j@gmail.com", "j@gmail.com", "xxxx", true, false, false, false, roleSet);

		assertAll("Properties Test",
				() -> assertAll("User Properties",
						() -> assertEquals("j@gmail.com", user1.getEmail(), "Email Did not Match"),
						() -> assertEquals("j@gmail.com", user1.getUsername(), "UserName did not Match"),
						() -> assertEquals("xxxx", user1.getPassword()), () -> assertAll("Roles Properties",
								() -> assertEquals(1, user1.getRoles().size(), "Roles size does not equals"))));

		Iterator<Role> itr = user1.getRoles().iterator();
		while (itr.hasNext()) {
			assertEquals(3, ((Role) itr.next()).getPermissions().size());
		}

	}

	@Test
	void newObj() {
		User user = new User();
		assertEquals(true, user.isNew());
		user.setId(1l);
		assertEquals(false, user.isNew());

	}

	@Test
	public void testSetter_setsProperly() throws NoSuchFieldException, IllegalAccessException {
		// given
		final User pojo = new User();

		// when
		pojo.setAccountNonLocked(false);
		pojo.setAccountNonExpired(false);
		pojo.setCredentialsNonExpired(false);
		pojo.setEmail("j@gmail.com");
		pojo.setUsername("Joe");
		pojo.setEnabled(true);
		pojo.setPassword("xxx123");
		pojo.setId(1l);
		pojo.setVersion(2l);;
		Set<Permission> permSet = new HashSet<Permission>();
		permSet.add(new Permission("view"));
		permSet.add(new Permission("read"));
		permSet.add(new Permission("execute"));
		Set<Role> roleSet = new HashSet<Role>();
		roleSet.add(new Role("Admin", permSet));
		pojo.setRoles(roleSet);
		final User pojo2 = new User();
		pojo2.setAccountNonLocked(true);
		pojo2.setAccountNonExpired(true);
		pojo2.setCredentialsNonExpired(true);

		// then
		assertEquals(1l,pojo.getId());
		assertEquals(2l,pojo.getVersion());
		assertEquals("Joe",pojo.getUsername());
		assertTrue(pojo.isAccountNonLocked());
		assertTrue(pojo.isAccountNonExpired());
		assertTrue(pojo.isCredentialsNonExpired());
		assertEquals("j@gmail.com", pojo.getEmail());
		assertTrue(pojo.isEnabled());
		assertEquals(1, pojo.getRoles().size());
		assertFalse(pojo2.isAccountNonLocked());
		assertFalse(pojo2.isAccountNonExpired());
		assertFalse(pojo2.isCredentialsNonExpired());

	}
}
