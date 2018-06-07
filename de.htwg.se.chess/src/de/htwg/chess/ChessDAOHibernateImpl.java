package de.htwg.chess;

import de.htwg.chess.model.IField;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ChessDAOHibernateImpl implements ChessDAO{
    private Configuration conf;
    private SessionFactory sessionFactory;

    public ChessDAOHibernateImpl() {
        conf = new Configuration().configure();
        try {
            sessionFactory = conf.buildSessionFactory();
        } catch (Exception e) {
            System.out.printf("Error creating session factory: %s%n", e.getMessage());
            System.out.printf("%s%n", e.getCause());
            /*
            for (StackTraceElement ste : e.getStackTrace()) {
                System.out.printf("%s%n", ste.toString());
            }
            */
        }
    }

    @Override
    public void saveField(IField field) {
        try (Session session = sessionFactory.openSession()) {
            session.save(field);
            System.out.printf("Successfully saved to database.%n");
        } catch (Exception e) {
            System.out.printf("Error creating session: %s%n", e.getMessage());
        }
    }

    @Override
    public void saveAllFields() {
    }
}
