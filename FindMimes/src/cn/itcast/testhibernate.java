package cn.itcast;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import org.junit.Test;

import cn.itcast.loginperson.Person;

public class testhibernate {
	@Test
	public void testhiber() throws Exception{
		Person person=new Person();
		person.setName("张三");
		person.setScore("70");
		person.setPassword("aaaaaaaaaaa");
		//获取加载配置文件的管理类对象
		Configuration configuration=new Configuration();
		configuration.configure();
		//创建session的工厂对象
		SessionFactory sf=configuration.buildSessionFactory();
		Session session=sf.openSession();
		Transaction tx=session.beginTransaction();
		session.save(person);
		//Query query=session.createQuery("SELECT *from personID");
		tx.commit();
		//System.out.println(query.list());
		session.close();
		sf.close();
	}
}
