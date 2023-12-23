package ru.gb;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


import java.sql.SQLException;
import java.util.stream.LongStream;

public class Main {
    public static void main(String[] args) {
        JDBC jdbc = new JDBC("Library");
        try {
            jdbc.dropSchema();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            jdbc.createSchema();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try{
            jdbc.addData(1,"Герой нашего времени","Лермонтов");
            jdbc.addData(2,"Евгений Онегин","Пушкин");
            jdbc.addData(3,"Мцыри","Лермонтов");
            jdbc.addData(4,"Му-му","Тургенев");
            jdbc.addData(5,"Капитанская дочка","Пушкин");
            jdbc.addData(6,"Ревизор","Гоголь");
            jdbc.addData(7,"Вий","Гоголь");
            jdbc.addData(8,"Выстрел","Пушкин");
            jdbc.addData(9,"Кому на руси жить хорошо","Некрасов");
            jdbc.addData(10,"Хорошее отношение к лошадям","Маяковский");
            jdbc.getData("Лермонтов");
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }

        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            String author = "Пушкин";
            List<Book> books = session.createQuery("select b from Book b where author = '" + author +"' order by id", Book.class)
                    .getResultList();
            session.getTransaction().commit();
            System.out.println(books);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}