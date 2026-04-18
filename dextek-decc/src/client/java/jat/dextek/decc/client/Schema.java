package jat.dextek.decc.client;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Defines the database.
 */
public class Schema {
    /**
     * Creates all tables if not present.
     *
     * @throws SQLException on database access error
     */
    public static void createTables() throws SQLException {
        @SuppressWarnings("resource") // connection managed by DatabaseManager
        Connection connection = DatabaseManager.get().connection();

        try(Statement statement = connection.createStatement()) {
            statement.execute("""
                    CREATE TABLE IF NOT EXISTS player (
                        uuid TEXT PRIMARY KEY,
                        name TEXT NOT NULL UNIQUE,
                        formatting TEXT,
                        nickname TEXT,
                        rank TEXT,
                        status TEXT
                    );
                    """);
        }
    }
}
