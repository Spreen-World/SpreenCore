package me.gatogamer.spreencore.user;

import lombok.Data;

import java.util.UUID;

/**
 * This code has been created by
 * gatogamer#6666 A.K.A. gatogamer.
 * If you want to use my code, please
 * ask first, and give me the credits.
 * Arigato! n.n
 */
@Data
public class User {
    private final UUID uuid;

    private long lastMove = 0L;
}