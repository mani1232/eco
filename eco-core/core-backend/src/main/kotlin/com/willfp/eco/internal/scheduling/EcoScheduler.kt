package com.willfp.eco.internal.scheduling

import com.willfp.eco.core.scheduling.Scheduler
import io.papermc.paper.threadedregions.scheduler.ScheduledTask
import org.bukkit.Location
import org.bukkit.plugin.java.JavaPlugin
import java.util.concurrent.TimeUnit

class EcoScheduler(private val plugin: JavaPlugin) : Scheduler {
    override fun runLater(runnable: Runnable, ticksLater: Long): ScheduledTask {
        return plugin.server.globalRegionScheduler.runDelayed(plugin, {
            runnable.run()
        }, ticksLater)
    }

    override fun runLater(runnable: Runnable, ticksLater: Long, location: Location): ScheduledTask {
        return plugin.server.regionScheduler.runDelayed(plugin, location, {
            runnable.run()
        }, ticksLater)
    }

    override fun runTimer(runnable: Runnable, delay: Long, repeat: Long): ScheduledTask {
        return plugin.server.globalRegionScheduler.runAtFixedRate(plugin, {
            runnable.run()
        }, delay, repeat)
    }

    override fun runTimer(runnable: Runnable, delay: Long, repeat: Long, location: Location): ScheduledTask {
        return plugin.server.regionScheduler.runAtFixedRate(plugin, location, {
            runnable.run()
        }, delay, repeat)
    }

    override fun runAsyncTimer(runnable: Runnable, delay: Long, repeat: Long): ScheduledTask {
        return plugin.server.asyncScheduler.runAtFixedRate(plugin, {
            runnable.run()
        }, delay*50, repeat*50, TimeUnit.MILLISECONDS)
    }

    override fun runNow(runnable: Runnable) {
        plugin.server.globalRegionScheduler.execute(plugin, runnable)
    }

    override fun runNow(runnable: Runnable, location: Location) {
        plugin.server.regionScheduler.execute(plugin, location, runnable)
    }

    override fun run(runnable: Runnable): ScheduledTask {
        return plugin.server.globalRegionScheduler.run(plugin) {
            runnable.run()
        }
    }

    override fun run(runnable: Runnable, location: Location): ScheduledTask {
        return plugin.server.regionScheduler.run(plugin, location) {
            runnable.run()
        }
    }

    override fun runAsync(runnable: Runnable): ScheduledTask {
        return plugin.server.asyncScheduler.runNow(plugin) {
            runnable.run()
        }
    }


    override fun cancelAll() {
        plugin.server.globalRegionScheduler.cancelTasks(plugin)
        plugin.server.asyncScheduler.cancelTasks(plugin)
    }
}