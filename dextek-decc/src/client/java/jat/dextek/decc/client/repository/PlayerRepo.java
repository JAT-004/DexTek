package jat.dextek.decc.client.repository;

import jat.dextek.decc.client.DatabaseManager;
import jat.dextek.decc.client.DeccClient;
import jat.dextek.decc.client.model.Player;
import net.minecraft.ChatFormatting;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Repository for Player data.
 */
public class PlayerRepo {
    /**
     * Insert or update player entry.
     *
     * @param player the player
     */
    public void upsert(@NotNull Player player) {
        String sql = """
                INSERT INTO player(uuid, name, formatting, rank, status)
                VALUES (?, ?, ?, ?, ?)
                ON CONFLICT(uuid) DO UPDATE SET
                name = excluded.name,
                formatting = excluded.formatting,
                rank = excluded.rank,
                status = excluded.status
                """;

        @SuppressWarnings("resource") // connection managed by DatabaseManager
        Connection connection = DatabaseManager.get().connection();
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            ChatFormatting chatFormatting = player.getFormatting();
            String formatting = chatFormatting != null ? chatFormatting.getName() : null;

            statement.setString(1, player.getUuid().toString());
            statement.setString(2, player.getName());
            statement.setString(3, formatting);
            statement.setString(4, player.getRank());
            statement.setString(5, player.getStatus());

            statement.execute();
        } catch(SQLException exception) {
            DeccClient.LOGGER.error("failed sql Player upsert", exception);
        }
    }

    /**
     * Returns the player for the given uuid or null if not present.
     *
     * @param uuid the uuid
     * @return the player or null
     */
    public Optional<Player> byUuid(@NotNull UUID uuid) {
        String sql = "SELECT * FROM player WHERE uuid = ?";

        @SuppressWarnings("resource") // connection managed by DatabaseManager
        Connection connection = DatabaseManager.get().connection();
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, uuid.toString());

            try(ResultSet result = statement.executeQuery()) {
                if(result.next()) return Optional.of(map(result));
            }
        } catch(SQLException exception) {
            DeccClient.LOGGER.error("failed sql Player byUuid", exception);
        }
        return Optional.empty();
    }

    /**
     * Returns the player for the given name or null if not present.
     *
     * @param name the name
     * @return the player or null
     */
    public Optional<Player> byName(@NotNull String name) {
        String sql = "SELECT * FROM player WHERE name = ?";

        @SuppressWarnings("resource") // connection managed by DatabaseManager
        Connection connection = DatabaseManager.get().connection();
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);

            try(ResultSet result = statement.executeQuery()) {
                if(result.next()) return Optional.of(map(result));
            }
        } catch(SQLException exception) {
            DeccClient.LOGGER.error("failed sql Player byName", exception);
        }
        return Optional.empty();
    }

    /**
     * Returns all players with the given rank, accepts null as no rank.
     *
     * @param rank the rank
     * @return a list of players, may be empty
     */
    public List<Player> byRank(@Nullable String rank) {
        String sql;
        if(rank == null) sql = "SELECT * FROM player WHERE rank IS NULL";
        else sql = "SELECT * FROM player WHERE rank = ?";
        List<Player> resultList = new ArrayList<>();

        @SuppressWarnings("resource") // connection managed by DatabaseManager
        Connection connection = DatabaseManager.get().connection();
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            if(rank != null) statement.setString(1, rank);

            try(ResultSet result = statement.executeQuery()) {
                while(result.next()) resultList.add(map(result));
            }
        } catch(SQLException exception) {
            DeccClient.LOGGER.error("failed sql Player byRank", exception);
        }
        return resultList;
    }

    /**
     * Returns all players with the given status, accepts null as no status.
     *
     * @param status the status
     * @return a list of players, may be empty
     */
    public List<Player> byStatus(@Nullable String status) {
        String sql;
        if(status == null) sql = "SELECT * FROM player WHERE status IS NULL";
        else sql = "SELECT * FROM player WHERE status = ?";
        List<Player> resultList = new ArrayList<>();

        @SuppressWarnings("resource") // connection managed by DatabaseManager
        Connection connection = DatabaseManager.get().connection();
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            if(status != null) statement.setString(1, status);

            try(ResultSet result = statement.executeQuery()) {
                while(result.next()) resultList.add(map(result));
            }
        } catch(SQLException exception) {
            DeccClient.LOGGER.error("failed sql Player byStatus", exception);
        }
        return resultList;
    }

    /**
     * Maps a result set for a player.
     *
     * @param result the result set
     * @return the player
     * @throws SQLException column label not valid
     */
    private Player map(@NotNull ResultSet result) throws SQLException {
        return new Player(new Player.Builder()
                .uuid(UUID.fromString(result.getString("uuid")))
                .name(result.getString("name"))
                .formatting(ChatFormatting.getByName(result.getString("formatting")))
                .rank(result.getString("rank"))
                .status(result.getString("status")));
    }
}
