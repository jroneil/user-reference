package com.oneil.users.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.Field;

import org.junit.jupiter.api.Test;

class PermissionTest {

	 @Test
	    void dependentAssertions() {
		
		
		 Permission perm1=new Permission("execute");
		 assertEquals("execute", perm1.getName(), " name  Did not Match");
		
	      
	    }

	 @Test
	 void newObj() {
		 Permission perm=new Permission();
		 assertEquals(true, perm.isNew());
		 perm.setId(1l);
		 assertEquals(false, perm.isNew());
		 
	 }
	 
	 @Test
	    public void testSetter_setsProperly() throws NoSuchFieldException, IllegalAccessException {
	        //given
	        final Permission pojo = new Permission();

	        //when
	        pojo.setName("foo");
	     
	        //then
	        final Field field = pojo.getClass().getDeclaredField("name");
	        field.setAccessible(true);
	        assertEquals( "foo",field.get(pojo), "Name Fields didn't match");
	      
	    }
}
