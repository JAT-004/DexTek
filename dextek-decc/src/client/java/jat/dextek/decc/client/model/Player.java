package jat.dextek.decc.client.model;

import net.minecraft.ChatFormatting;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

/**
 * Represents a minecraft player.
 * <p>
 * The uuid identifies the player.<br>
 * The name must be unique.<br>
 * A formatting is optional and can be null.<br>
 * A nickname is optional and can be null<br>
 * A rank is optional and can be null.<br>
 * A status is optional and can be null.
 */
public class Player {
    private final UUID uuid;
    private final String name;
    private final ChatFormatting formatting;

    private final String nickname;
    private final String rank;
    private final String status;

    public Player(@NotNull Builder builder) {
        uuid = builder.uuid;
        name = builder.name;
        formatting = builder.formatting;

        nickname = builder.nickname;
        rank = builder.rank;
        status = builder.status;
    }

    /**
     * Builder used in player constructor.
     */
    public static class Builder {
        private UUID uuid;
        private String name;
        private ChatFormatting formatting;

        private String nickname;
        private String rank;
        private String status;

        public Builder uuid(@NotNull UUID uuid) {
            this.uuid = uuid;
            return this;
        }

        public Builder name(@NotNull String name) {
            this.name = name;
            return this;
        }

        public Builder formatting(@Nullable ChatFormatting formatting) {
            this.formatting = formatting;
            return this;
        }

        public Builder nickname(@Nullable String nickname) {
            this.nickname = nickname;
            return this;
        }

        public Builder rank(@Nullable String rank) {
            this.rank = rank;
            return this;
        }

        public Builder status(@Nullable String status) {
            this.status = status;
            return this;
        }
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public ChatFormatting getFormatting() {
        return formatting;
    }

    public String getNickname() {
        return nickname;
    }

    public String getRank() {
        return rank;
    }

    public String getStatus() {
        return status;
    }
}
