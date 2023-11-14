package com.willfp.eco.internal.scheduling

import com.willfp.eco.core.scheduling.RunnableTask
import io.papermc.paper.threadedregions.scheduler.ScheduledTask
import org.bukkit.Location
import org.bukkit.plugin.java.JavaPlugin
import java.util.concurrent.TimeUnit

abstract class EcoRunnableTask(protected val plugin: JavaPlugin) : RunnableTask {

    private lateinit var task: ScheduledTask

    @Synchronized
    override fun runNow() {
        plugin.server.globalRegionScheduler.execute(plugin, this)
    }

    @Synchronized
    override fun runNow(location: Location) {
        plugin.server.regionScheduler.execute(plugin, location, this)
    }

    @Synchronized
    override fun runTask(location: Location): ScheduledTask {
        task = plugin.server.regionScheduler.run(plugin, location) {
            this.run()
        }
        return task
    }

    @Synchronized
    override fun runTask(): ScheduledTask {
        task = plugin.server.globalRegionScheduler.run(plugin) {
            this.run()
        }
        return task
    }

    @Synchronized
    override fun runTaskAsynchronously(): ScheduledTask {
        task = plugin.server.asyncScheduler.runNow(plugin) {
            this.run()
        }
        return task
    }

    @Synchronized
    override fun runTaskLater(delay: Long, location: Location): ScheduledTask {
        task = plugin.server.regionScheduler.runDelayed(plugin, location, {
            this.run()
        }, delay)
        return task
    }

    @Synchronized
    override fun runTaskLater(delay: Long): ScheduledTask {
        task = plugin.server.globalRegionScheduler.runDelayed(plugin, {
            this.run()
        }, delay)
        return task
    }

    @Synchronized
    override fun runTaskLaterAsynchronously(delay: Long): ScheduledTask {
        task = plugin.server.asyncScheduler.runDelayed(plugin, {
            this.run()
        }, delay * 50, TimeUnit.MILLISECONDS)
        return task
    }

    @Synchronized
    override fun runTaskTimer(delay: Long, period: Long, location: Location): ScheduledTask {
        task = plugin.server.regionScheduler.runDelayed(plugin, location, {
            this.run()
        }, delay)
        return task
    }

    @Synchronized
    override fun runTaskTimer(delay: Long, period: Long): ScheduledTask {
        task = plugin.server.globalRegionScheduler.runAtFixedRate(plugin, {
            this.run()
        }, delay, period)
        return task
    }

    @Synchronized
    override fun runTaskTimerAsynchronously(delay: Long, period: Long): ScheduledTask {
        task = plugin.server.asyncScheduler.runAtFixedRate(plugin, {
            this.run()
        }, delay * 50, period * 50, TimeUnit.MILLISECONDS)
        return task
    }

    @Synchronized
    override fun cancel() {
        if (task.isCancelled) {
            task.cancel()
        }
    }
}