package com.willfp.eco.core.scheduling;

import com.willfp.eco.core.EcoPlugin;
import io.papermc.paper.threadedregions.scheduler.ScheduledTask;
import org.bukkit.Location;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.NotNull;

/**
 * Thread scheduler to handle tasks and asynchronous code.
 */
public interface Scheduler {
    /**
     * Run the task after a specified tick delay.
     *
     * @param runnable   The lambda to run.
     * @param ticksLater The amount of ticks to wait before execution.
     * @return The created {@link BukkitTask}.
     */
    ScheduledTask runLater(@NotNull Runnable runnable,
                           long ticksLater);

    /**
     * Run the task after a specified tick delay.
     * <p>
     * Reordered for better kotlin interop.
     *
     * @param runnable   The lambda to run.
     * @param ticksLater The amount of ticks to wait before execution.
     * @return The created {@link BukkitTask}.
     */
    default ScheduledTask runLater(long ticksLater,
                                @NotNull Runnable runnable) {
        return runLater(runnable, ticksLater);
    }

    /**
     * Run the task after a specified tick delay.
     *
     * @param runnable   The lambda to run.
     * @param ticksLater The amount of ticks to wait before execution.
     * @return The created {@link BukkitTask}.
     */
    ScheduledTask runLater(@NotNull Runnable runnable,
                           long ticksLater, Location location);

    /**
     * Run the task after a specified tick delay.
     * <p>
     * Reordered for better kotlin interop.
     *
     * @param runnable   The lambda to run.
     * @param ticksLater The amount of ticks to wait before execution.
     * @return The created {@link BukkitTask}.
     */
    default ScheduledTask runLater(long ticksLater,
                                   @NotNull Runnable runnable, Location location) {
        return runLater(runnable, ticksLater, location);
    }

    /**
     * Run the task repeatedly on a timer.
     *
     * @param runnable The lambda to run.
     * @param delay    The amount of ticks to wait before the first execution.
     * @param repeat   The amount of ticks to wait between executions.
     * @return The created {@link BukkitTask}.
     */
    ScheduledTask runTimer(@NotNull Runnable runnable,
                        long delay,
                        long repeat);

    /**
     * Run the task repeatedly on a timer.
     * <p>
     * Reordered for better kotlin interop.
     *
     * @param runnable The lambda to run.
     * @param delay    The amount of ticks to wait before the first execution.
     * @param repeat   The amount of ticks to wait between executions.
     * @return The created {@link BukkitTask}.
     */
    default ScheduledTask runTimer(long delay,
                                long repeat,
                                @NotNull Runnable runnable) {
        return runTimer(runnable, delay, repeat);
    }

    /**
     * Run the task repeatedly on a timer.
     *
     * @param runnable The lambda to run.
     * @param delay    The amount of ticks to wait before the first execution.
     * @param repeat   The amount of ticks to wait between executions.
     * @return The created {@link BukkitTask}.
     */
    ScheduledTask runTimer(@NotNull Runnable runnable,
                           long delay,
                           long repeat, Location location);

    /**
     * Run the task repeatedly on a timer.
     * <p>
     * Reordered for better kotlin interop.
     *
     * @param runnable The lambda to run.
     * @param delay    The amount of ticks to wait before the first execution.
     * @param repeat   The amount of ticks to wait between executions.
     * @return The created {@link BukkitTask}.
     */
    default ScheduledTask runTimer(long delay,
                                   long repeat,
                                   @NotNull Runnable runnable, Location location) {
        return runTimer(runnable, delay, repeat, location);
    }

    /**
     * Run the task repeatedly and asynchronously on a timer.
     *
     * @param runnable The lambda to run.
     * @param delay    The amount of ticks to wait before the first execution.
     * @param repeat   The amount of ticks to wait between executions.
     * @return The created {@link BukkitTask}.
     */
    ScheduledTask runAsyncTimer(@NotNull Runnable runnable,
                             long delay,
                             long repeat);

    /**
     * Run the task repeatedly and asynchronously on a timer.
     * <p>
     * Reordered for better kotlin interop.
     *
     * @param runnable The lambda to run.
     * @param delay    The amount of ticks to wait before the first execution.
     * @param repeat   The amount of ticks to wait between executions.
     * @return The created {@link BukkitTask}.
     */
    default ScheduledTask runAsyncTimer(long delay,
                                     long repeat,
                                     @NotNull Runnable runnable) {
        return runAsyncTimer(runnable, delay, repeat);
    }

    /**
     * Run the task now.
     *
     * @param runnable The lambda to run.
     * @return The created {@link BukkitTask}.
     */
    void runNow(@NotNull Runnable runnable);

    /**
     * Run the task now.
     *
     * @param runnable The lambda to run.
     * @return The created {@link BukkitTask}.
     */
    void runNow(@NotNull Runnable runnable, Location location);

    /**
     * Run the task in next tick.
     *
     * @param runnable The lambda to run.
     * @return The created {@link BukkitTask}.
     */
    ScheduledTask run(@NotNull Runnable runnable);

    /**
     * Run the task in next tick.
     *
     * @param runnable The lambda to run.
     * @return The created {@link BukkitTask}.
     */
    ScheduledTask run(@NotNull Runnable runnable, Location location);

    /**
     * Run the task asynchronously.
     *
     * @param runnable The lambda to run.
     * @return The created {@link BukkitTask}.
     */
    ScheduledTask runAsync(@NotNull Runnable runnable);

    /**
     * Cancel all running tasks from the linked {@link EcoPlugin}.
     */
    void cancelAll();
}
