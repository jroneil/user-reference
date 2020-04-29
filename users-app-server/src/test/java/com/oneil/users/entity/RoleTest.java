package com.oneil.users.entity;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

class RoleTest {

	 @Test
	    void dependentAssertions() {
		 Set<Permission> permSet=new HashSet<Permission>();
		 permSet.add(new Permission("view"));
		 permSet.add(new Permission("read"));
		 permSet.add(new Permission("execute"));
		
		
		
		 
		 Set<Role> roleSet=new HashSet<Role>();
		 roleSet.add(new Role("Admin", permSet));
		 
		 
		 Role role1 = new Role("Admin", permSet);
	        

	        assertAll("Properties Test",
	                () -> assertAll("Role Properties",
	                        () -> assertEquals("Admin", role1.getName(), "Role name  Did not Match"),
	                        () -> assertEquals(3, role1.getPermissions().size(), "Permission size does not equals")));
	                       
	                
	       List<String>list=new ArrayList<String>();
	        Iterator<Permission> itr = role1.getPermissions().iterator();
	         while(itr.hasNext()){ 
	        	list.add(((Permission)itr.next()).getName());
	        	}

	         assertEquals(true,list.contains("view"));
	         assertEquals(true,list.contains("read"));
	         assertEquals(true,list.contains("execute"));
	    }

	 @Test
	 void newObj() {
		 Role role=new Role();
		 assertEquals(true, role.isNew());
		 role.setId(1l);
		 assertEquals(false, role.isNew());
		 
	 }
	 
	 @Test
	    public void testSetter_setsProperly() throws NoSuchFieldException, IllegalAccessException {
	        //given
	        final Permission pem = new Permission();
	        pem.setName("read");
	        Set<Permission>pemSet=new HashSet<Permission>();
	        pemSet.add(pem);
	        //when
	        final Role pojo= new Role();
	        pojo.setName("Admin");
	        pojo.setPermissions(pemSet);
	     

	        //then
	        final Field field = pojo.getClass().getDeclaredField("name");
	        field.setAccessible(true);
	        assertEquals( "Admin",field.get(pojo), "Name Fields didn't match");
	        assertEquals( 1,pojo.getPermissions().size());
	        
	    }
}
