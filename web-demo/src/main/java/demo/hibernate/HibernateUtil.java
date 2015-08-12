package demo.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * @author Robin
 * 
 */
public class HibernateUtil {

	private static final SessionFactory sessionFactory;

	/* Hibernate 3.x 初始化方式
	static {
		try {
			Configuration configuration = new Configuration().configure();
			sessionFactory = configuration.buildSessionFactory();
		} catch (Throwable e) {
			e.printStackTrace();
			throw new ExceptionInInitializerError(e);
		}
	}
	*/
	
	// Hibernate 4.x 初始化方式
	static{
        Configuration config = new Configuration().configure();

        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(
                config.getProperties()).buildServiceRegistry();

        sessionFactory = config.buildSessionFactory(serviceRegistry);	    
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
