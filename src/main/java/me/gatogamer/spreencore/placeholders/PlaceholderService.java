package me.gatogamer.spreencore.placeholders;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.gatogamer.spreencore.SpreenCore;
import me.gatogamer.spreencore.commands.GrefgCommand;
import me.gatogamer.spreencore.commands.ResetEliminationsCommand;
import me.gatogamer.spreencore.tasks.TickTask;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.Duration;

/**
 * This code has been created by
 * gatogamer#6666 A.K.A. gatogamer.
 * If you want to use my code, please
 * don't remove this messages and
 * give me the credits. Arigato! n.n
 */
public class PlaceholderService extends PlaceholderExpansion {

    @Override
    public @Nullable String onRequest(OfflinePlayer player, @NotNull String params) {
        if (params.equals("grefg_timer")) {
            return formatTime(Duration.ofSeconds(GrefgCommand.TIME));
        } else if (params.equals("grefg_reason")) {
            return GrefgCommand.REASON;
        } else if (params.equals("players_not_staff")) {
            return String.valueOf(TickTask.players);
        }else if (params.equals("eliminations")) {
            return String.valueOf(ResetEliminationsCommand.ELIMINATIONS);
        }

        return "invalid";
    }

    @Override
    public @NotNull String getIdentifier() {
        return "spreencore";
    }

    @Override
    public @NotNull String getAuthor() {
        return "gatogamer_";
    }

    @Override
    public @NotNull String getVersion() {
        return SpreenCore.getInstance().getDescription().getVersion();
    }

    public String formatTime(final Duration duration) {
        final StringBuilder builder = new StringBuilder();

        long eventSeconds = duration.getSeconds();
        long hours = eventSeconds / (60 * 60);
        long minutes = (eventSeconds / 60) - (hours * 60);
        long seconds = eventSeconds % 60;

        if (hours > 0) {
            return hours + ":" + (minutes < 10 ? "0" + minutes : minutes) + ":" + (seconds < 10 ? "0" + seconds : seconds);
        } else {
            return (minutes < 10 ? "0" + minutes : minutes) + ":" + (seconds < 10 ? "0" + seconds : seconds);
        }
    }
}