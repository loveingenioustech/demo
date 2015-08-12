package demo.hibernate;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import demo.dto.Event;

public class HibernateTest
{
    private SessionFactory sessionFactory = null;

    @Before
    public void setUp() throws Exception
    {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    @After
    public void tearDown() throws Exception
    {
    }

    @Test
    public void testSave()
    {
        // 测试时要先修改 hibernate.cfg.xml 文件，不用JNDI方式来获取数据库连接
        Session session = sessionFactory.getCurrentSession();

        Event testEvent = new Event();

        testEvent.setTitle("Test");
        testEvent.setTitlePound("Test #");
        testEvent.setStartDate(new Date());

        // Movie movie = new Movie();
        //
        // movie.setId(3);
        // movie.setDirector("Test insert");
        // movie.setTitle("Test");
        // movie.setSynopsis("A tale of a white shark!");

        session.beginTransaction();
        session.save(testEvent);
        // session.save(movie);
        session.getTransaction().commit();
    }

}
