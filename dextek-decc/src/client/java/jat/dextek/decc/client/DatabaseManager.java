package jat.dextek.decc.client;

import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Manages the project database.
 * <p>
 * Opens the only connection for the project on initialization. Closes the connection at the end.
 */
public class DatabaseManager {
    private final static String FILE_NAME = "decc.sqlite";

    private static DatabaseManager instance;
    private Connection connection;

    private DatabaseManager() {}

    /**
     * Returns the database manager.
     *
     * @return the database manager
     */
    public static DatabaseManager get() {
        if(instance == null) instance = new DatabaseManager();
        return instance;
    }

    /**
     * Opens the database connection and creates database if necessary, should be called on client startup.
     *
     * @throws SQLException on connection error
     */
    public void init() throws SQLException {
        Path path = DeccClient.CONFIG_PATH.resolve(FILE_NAME);
        path.toFile().getParentFile().mkdirs();

        connection = DriverManager.getConnection("jdbc:sqlite:" + path);
        connection.createStatement().execute("PRAGMA foreign_keys = ON");
        Schema.createTables();
        DeccClient.LOGGER.info("opened database connection");
    }

    /**
     * Closes the connection, should be called on client shutdown.
     */
    public void close() {
        if(connection != null) try {
            connection.close();
            DeccClient.LOGGER.info("closed database connection");
        } catch(SQLException exception) {
            DeccClient.LOGGER.error("failed  closing database connection", exception);
        }
    }

    /**
     * Returns the database connection.
     *
     * @return the database connection
     */
    public Connection connection() {
        return connection;
    }
}
