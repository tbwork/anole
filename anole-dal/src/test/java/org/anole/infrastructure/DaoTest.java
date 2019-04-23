package org.anole.infrastructure;

import java.util.List;

import org.anole.infrastructure.dao.AnoleConfigItemMapper;
import org.anole.infrastructure.example.AnoleConfigItemExample;
import org.anole.infrastructure.model.AnoleConfigItem;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class DaoTest  
{
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(
        		"spring/spring-context.xml",
        		"spring/spring-database.xml"
        		);
		
		AnoleConfigItemMapper dao = context.getBean(AnoleConfigItemMapper.class);
		List<AnoleConfigItem> items = dao.selectByExample(new AnoleConfigItemExample());
		System.out.println(items.size());
	}
}
