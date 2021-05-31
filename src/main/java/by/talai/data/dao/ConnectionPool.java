package by.talai.data.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class ConnectionPool {

    private String dbUrl;
    private String dbUser;
    private String dbPassword;

    private int maxPoolSize;
    private int idlePoolSize;
    private int connectionsCounter = 0;

    public static final String SQL_VERIFY_CONNECTION = "select 1";

    Stack<Connection> freeConnections = new Stack<>();
    Set<Connection> occupiedConnections = new HashSet<>();

    private ConnectionPool() {
    }

    public ConnectionPool(String dbUrl, String dbUser, String dbPassword, int maxPoolSize, int idlePoolSize) throws SQLException {
        this.dbUrl = dbUrl;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
        this.maxPoolSize = maxPoolSize;
        this.idlePoolSize = idlePoolSize;
        initialize();
    }

    private void initialize() throws SQLException {
        if (idlePoolSize > maxPoolSize) {
            throw new IllegalArgumentException("The idle pool size can not be more than maximum pool size!");
            //TODO logging
        }
        if (maxPoolSize < 0 || idlePoolSize < 0) {
            throw new IllegalArgumentException("The pool size can not be negative!");
            //TODO logging
        }
        if (maxPoolSize == 0) {
            throw new IllegalArgumentException("The maximum pool size can not be equal to zero!");
            //TODO logging
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Driver loading error");
            //TODO logging, EH
        }

        while (connectionsCounter < idlePoolSize) {
            createNewConnectionForPool();
        }

    }

    public synchronized Connection getConnection() throws SQLException {
        Connection connection = null;

        if (poolIsFull()) {
            throw new SQLException("The pool of connections is full!");
            //TODO logging
        }
        connection = getConnectionFromPool();
        if (connection == null) {
            connection = createNewConnectionForPool();
        }
        connection = makeAvailable(connection);
        return connection;
    }

    public synchronized void returnConnection(Connection connection) throws Exception {
        if (connection == null) {
            throw new NullPointerException();
            //TODO logging
        }
        if (!occupiedConnections.remove(connection)) {
           throw new Exception("The connection has been already returned or it is not present in this pool");
            //TODO logging
        }
        freeConnections.push(connection);
    }

    private synchronized boolean poolIsFull() {
        return ((freeConnections.size() == 0) && (connectionsCounter >= maxPoolSize));
    }

    private Connection createNewConnectionForPool() throws SQLException {
        Connection connection = createNewConnection();
        connectionsCounter++;
        occupiedConnections.add(connection);
        return connection;
    }

    private Connection createNewConnection() throws SQLException {
        Connection connection = null;
        connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
        return connection;
    }

    private Connection getConnectionFromPool() {
        Connection connection = null;
        if (freeConnections.size() > 0) {
            connection = freeConnections.pop();
            occupiedConnections.add(connection);
        }
        return connection;
    }

    private Connection makeAvailable(Connection connection) throws SQLException {
        if (connectionIsAvailable(connection)) {
            return connection;
        }
        occupiedConnections.remove(connection);
        connectionsCounter--;
        connection.close();

        connection = createNewConnection();
        occupiedConnections.add(connection);
        connectionsCounter++;
        return connection;
    }

    private boolean connectionIsAvailable(Connection connection) {
        try (Statement statement = connection.createStatement()) {
            statement.executeQuery(SQL_VERIFY_CONNECTION);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

}
