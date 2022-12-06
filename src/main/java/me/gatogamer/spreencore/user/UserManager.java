package me.gatogamer.spreencore.user;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * This code has been created by
 * gatogamer#6666 A.K.A. gatogamer.
 * If you want to use my code, please
 * ask first, and give me the credits.
 * Arigato! n.n
 */
public class UserManager {
    private final Map<UUID, User> users = new ConcurrentHashMap<>();

    public User getUser(UUID uuid) {
        return users.computeIfAbsent(uuid, User::new);
    }

    public void invalidate(UUID uuid) {
        users.remove(uuid);
    }
}