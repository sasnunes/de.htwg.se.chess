package de.htwg.chess;

import de.htwg.chess.model.Color;
import de.htwg.chess.model.IChessboard;
import de.htwg.chess.model.IField;
import de.htwg.chess.model.impl.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

public class ChessDAOHibernateImpl implements ChessDAO{
    private SessionFactory sessionFactory;

    public ChessDAOHibernateImpl() {
        try {
            Configuration conf = new Configuration().addAnnotatedClass(PersistentField.class).configure();
            System.out.printf("Hibernate DAO: Configuration of database done.%n");
            StandardServiceRegistryBuilder sb = new StandardServiceRegistryBuilder();
            sb.applySettings(conf.getProperties());
            StandardServiceRegistry standardServiceRegistry = sb.build();
            sessionFactory = conf.buildSessionFactory(standardServiceRegistry);
            System.out.printf("Hibernate DAO: Session-Factory created.%n");
        } catch (Exception e) {
            System.out.printf("ERROR: configuring DAOHibernateImpl: %s%n%s%n", e.getMessage(), e.getCause());
        }
    }

    @Override
    public void saveBoard(IChessboard board) {
        Session session;
        try {
            session = sessionFactory.openSession();
            System.out.printf("Hibernate DAO: Session opened.%n");
            for (PersistentField pField : preparePersistentFields(board.getAllFields())) {
                session.save(pField);
            }
            System.out.printf("Hibernate DAO: Fields saved.%n");
            session.close();
            System.out.printf("Hibernate DAO: Session closed.%n");
        } catch (HibernateException e) {
            System.out.printf("ERROR: In DAO-session: %s%n", e.getMessage());
        }
    }

    public List<IField> loadBoard() {
        Session session;
        List<PersistentField> list = new ArrayList<>();
        try {
            session = sessionFactory.openSession();
            System.out.printf("Hibernate DAO: Session opened.%n");
            List queryResult = session.createQuery("FROM PersistentField").list();
            for (Object aQueryResult : queryResult) {
                PersistentField pField = (PersistentField) aQueryResult;
                list.add(pField);
            }
            System.out.printf("Hibernate DAO: Fields loaded.%n");
            session.close();
            System.out.printf("Hibernate DAO: Session closed.%n");
        } catch (HibernateException e) {
            System.out.printf("ERROR: In DAO-session: %s%n", e.getMessage());
        }
        return translatePersistentFields(list);
    }

    @Override
    public void shutdown() {
        sessionFactory.close();
        System.out.printf("Hibernate DAO: Session-Factory closed.%n");
    }

    private List<PersistentField> preparePersistentFields(List<IField> fieldList) {
        List<PersistentField> list = new ArrayList<>();
        for (IField field : fieldList) {
            if (field.getChesspiece() == null) {
                list.add(new PersistentField(field.getX(), field.getY(), null));
            } else {
                list.add(new PersistentField(field.getX(), field.getY(), field.getChesspiece().toString()));
            }
        }
        return list;
    }

    private List<IField> translatePersistentFields(List<PersistentField> pFieldList) {
        List<IField> list = new ArrayList<>();
        for (PersistentField pField : pFieldList) {
            IField newField = new Field(pField.getX(), pField.getY());
            switch (pField.getCp()) {
                case "Pawn WHITE": newField.setChesspiece(new Pawn(Color.WHITE, newField)); break;
                case "Pawn BLACK": newField.setChesspiece(new Pawn(Color.BLACK, newField)); break;
                case "Rook WHITE": newField.setChesspiece(new Rook(Color.WHITE, newField)); break;
                case "Rook BLACK": newField.setChesspiece(new Rook(Color.BLACK, newField)); break;
                case "Knight WHITE": newField.setChesspiece(new Knight(Color.WHITE, newField)); break;
                case "Knight BLACK": newField.setChesspiece(new Knight(Color.BLACK, newField)); break;
                case "Bishop WHITE": newField.setChesspiece(new Bishop(Color.WHITE, newField)); break;
                case "Bishop BLACK": newField.setChesspiece(new Bishop(Color.BLACK, newField)); break;
                case "Queen WHITE": newField.setChesspiece(new Queen(Color.WHITE, newField)); break;
                case "Queen BLACK": newField.setChesspiece(new Queen(Color.BLACK, newField)); break;
                case "King WHITE": newField.setChesspiece(new King(Color.WHITE, newField)); break;
                case "King BLACK": newField.setChesspiece(new King(Color.BLACK, newField)); break;
                default: break;
            }
            list.add(newField);
        }
        return list;
    }

}
