package com.willfp.eco.core.scheduling;

import io.papermc.paper.threadedregions.scheduler.ScheduledTask;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

/**
 * Bukkit Task handler.
 */
public interface RunnableTask extends Runnable {
    /**
     * Run the task.
     */
    void runNow();
    /**
     * Run the task by location.
     *
     * @param location location
     */
    void runNow(Location location);

    /**
     * Run the task on the next tick by location.
     *
     * @param location location
     * @return The created {@link ScheduledTask}.
     */
    @NotNull ScheduledTask runTask(Location location);
    /**
     * Run the task on the next tick.
     *
     * @return The created {@link ScheduledTask}.
     */
    @NotNull ScheduledTask runTask();

    /**
     * Run the task asynchronously.
     *
     * @return The created {@link ScheduledTask}
     */
    @NotNull ScheduledTask runTaskAsynchronously();

    /**
     * Run the task after a specified number of ticks by location.
     *
     * @param location location
     * @param delay The number of ticks to wait.
     * @return The created {@link ScheduledTask}
     */
    @NotNull ScheduledTask runTaskLater(long delay, Location location);

    /**
     * Run the task after a specified number of ticks.
     *
     * @param delay The number of ticks to wait.
     * @return The created {@link ScheduledTask}
     */
    @NotNull ScheduledTask runTaskLater(long delay);

    /**
     * Run the task asynchronously after a specified number of ticks.
     *
     * @param delay The number of ticks to wait.
     * @return The created {@link ScheduledTask}
     */
    @NotNull ScheduledTask runTaskLaterAsynchronously(long delay);

    /**
     * Run the task repeatedly on a timer.
     *
     * @param delay  The delay before the task is first ran (in ticks).
     * @param period The ticks elapsed before the task is ran again.
     * @param location location
     * @return The created {@link ScheduledTask}
     */
    @NotNull ScheduledTask runTaskTimer(long delay, long period, Location location);

    /**
     * Run the task repeatedly on a timer.
     *
     * @param delay  The delay before the task is first ran (in ticks).
     * @param period The ticks elapsed before the task is ran again.
     * @return The created {@link ScheduledTask}
     */
    @NotNull ScheduledTask runTaskTimer(long delay, long period);

    /**
     * Run the task repeatedly on a timer asynchronously.
     *
     * @param delay  The delay before the task is first ran (in ticks).
     * @param period The ticks elapsed before the task is ran again.
     * @return The created {@link ScheduledTask}
     */
    @NotNull ScheduledTask runTaskTimerAsynchronously(long delay, long period);

    /**
     * Cancel the task.
     */
    void cancel();
}
