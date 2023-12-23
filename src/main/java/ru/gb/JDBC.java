package ru.gb;

import java.sql.*;
public class JDBC {

    private static final String URL = "jdbc:mysql://localhost:3306";
    private static final String USER = "root";
    private static final String PASSWORD = "qwer1234";
    private String schema;
    public String getSchema() {
        return schema;
    }
    public JDBC(String schema){
        this.schema = schema;
    }
    public void dropSchema() throws SQLException {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            Statement statement = con.createStatement();
            statement.execute("DROP SCHEMA " + schema);
        }
    }
    public void createSchema() throws SQLException {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            Statement statement = con.createStatement();
            statement.execute("CREATE SCHEMA " + schema);
            statement.execute("CREATE TABLE " + schema + ".BOOKS" +
                    " (id BIGINT NOT NULL, bookName VARCHAR(100) NOT NULL, author varchar(45) NULL, primary key (id))");
        }
    }
    public void addData(int id, String book, String author) throws SQLException {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            Statement statement = con.createStatement();
            String insert = "insert into " + schema + ".books (id, bookname, author) values (" + id + ", '" + book +"', '" + author +"')";
            statement.execute(insert);
        }

    }
    public void getData(String author) throws SQLException {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)){
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("select id, bookName, author from " + schema + ".books " +
                    "where author = '" + author + "'");
            while (resultSet.next()){
                System.out.printf("id = %d, %s - %s \n", resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3));
            }
        }
    }
}
