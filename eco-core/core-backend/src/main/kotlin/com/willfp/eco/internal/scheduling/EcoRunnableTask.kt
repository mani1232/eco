package com.willfp.eco.internal.scheduling

import com.willfp.eco.core.scheduling.RunnableTask
import io.papermc.paper.threadedregions.scheduler.ScheduledTask
import org.bukkit.Location
import org.bukkit.plugin.java.JavaPlugin
import java.util.concurrent.TimeUnit

abstract class EcoRunnableTask(protected val plugin: JavaPlugin) : RunnableTask {

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
        return plugin.server.regionScheduler.run(plugin, location) {
            this.run()
        }
    }

    @Synchronized
    override fun runTask(): ScheduledTask {
        return plugin.server.globalRegionScheduler.run(plugin) {
            this.run()
        }
    }

    @Synchronized
    override fun runTaskAsynchronously(): ScheduledTask {
        return plugin.server.asyncScheduler.runNow(plugin) {
            this.run()
        }
    }

    @Synchronized
    override fun runTaskLater(delay: Long, location: Location): ScheduledTask {
        return plugin.server.regionScheduler.runDelayed(plugin, location, {
            this.run()
        }, delay)
    }

    @Synchronized
    override fun runTaskLater(delay: Long): ScheduledTask {
        return plugin.server.globalRegionScheduler.runDelayed(plugin, {
            this.run()
        }, delay)
    }

    @Synchronized
    override fun runTaskLaterAsynchronously(delay: Long): ScheduledTask {
        return plugin.server.asyncScheduler.runDelayed(plugin, {
            this.run()
        }, delay * 50, TimeUnit.MILLISECONDS)
    }

    @Synchronized
    override fun runTaskTimer(delay: Long, period: Long, location: Location): ScheduledTask {
        return plugin.server.regionScheduler.runDelayed(plugin, location, {
            this.run()
        }, delay)
    }

    @Synchronized
    override fun runTaskTimer(delay: Long, period: Long): ScheduledTask {
        return plugin.server.globalRegionScheduler.runAtFixedRate(plugin, {
            this.run()
        }, delay, period)
    }

    @Synchronized
    override fun runTaskTimerAsynchronously(delay: Long, period: Long): ScheduledTask {
        return plugin.server.asyncScheduler.runAtFixedRate(plugin, {
            this.run()
        }, delay * 50, period * 50, TimeUnit.MILLISECONDS)
    }
}