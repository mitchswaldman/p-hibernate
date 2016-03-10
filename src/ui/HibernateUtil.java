package ui;
import java.io.PrintStream;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
	private static SessionFactory sessionFactory;
    private static ServiceRegistry serviceRegistry;

    private static SessionFactory configureSessionFactory() throws HibernateException 
    {
    	try {
    	Configuration configuration = new Configuration().configure();
    	StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().
    	applySettings(configuration.getProperties());
    	sessionFactory = configuration.buildSessionFactory(builder.build());
    	}catch(Exception ex){
    		ex.printStackTrace(new PrintStream(System.out));
    	}

    	return sessionFactory;
    }


    public static SessionFactory getSessionFactory() {
        return configureSessionFactory();

    }
}
