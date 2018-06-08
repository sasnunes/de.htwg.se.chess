package de.htwg.chess;

import de.htwg.chess.model.IChessboard;
import de.htwg.chess.model.IField;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class ChessDAOHibernateImpl implements ChessDAO{
    private Configuration conf;
    private SessionFactory sessionFactory;

    public ChessDAOHibernateImpl() {
        try {
            conf = new Configuration().configure();
            System.out.printf("Hibernate DAO: Configuration of database done.%n");
            StandardServiceRegistryBuilder sb = new StandardServiceRegistryBuilder();
            sb.applySettings(conf.getProperties());
            StandardServiceRegistry standardServiceRegistry = sb.build();
            sessionFactory = conf.buildSessionFactory(standardServiceRegistry);
            System.out.printf("Hibernate DAO: Session-Factory created.%n");
        } catch (Exception e) {
            System.out.printf("Error configuring DAOHibernateImpl: %s%n%s%n", e.getMessage(), e.getCause());
        }
    }

    @Override
    public void saveAllFields(IChessboard board) {
        Session session;
        try {
            session = sessionFactory.openSession();
            System.out.printf("Session opened.%n");
            for (IField[] field : board.getAllFields()) {
                session.save(field);
            }
            session.close();
        } catch (HibernateException e) {
            System.out.printf("Error opening session: %s%n", e.getMessage());
        }
    }

}
