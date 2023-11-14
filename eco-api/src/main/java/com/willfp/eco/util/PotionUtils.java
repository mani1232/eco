package com.willfp.eco.util;

import org.bukkit.potion.PotionType;
import org.jetbrains.annotations.NotNull;

/**
 * Utilities / API methods for potions.
 */
public final class PotionUtils {
    /**
     * Get the duration (in ticks) for potion data.
     *
     * @param data The data.
     * @return The duration.
     */
    public static int getDuration(@NotNull final PotionType data) {
        if (data.isExtendable()) {
            return switch (data) {
                case INSTANT_DAMAGE, INSTANT_HEAL:
                    yield 0;
                case POISON, REGEN:
                    yield 1800;
                case SLOW_FALLING, WEAKNESS, SLOWNESS:
                    yield 4800;
                case TURTLE_MASTER:
                    yield 800;
                default:
                    yield 9600;
            };
        }

        if (data.isUpgradeable()) {
            return switch (data) {
                case INSTANT_DAMAGE, INSTANT_HEAL:
                    yield 0;
                case POISON, REGEN:
                    yield 420;
                case SLOW_FALLING, WEAKNESS, SLOWNESS:
                    yield 440;
                case TURTLE_MASTER:
                    yield 400;
                default:
                    yield 1800;
            };
        }

        return switch (data) {
            case INSTANT_DAMAGE, INSTANT_HEAL:
                yield 0;
            case POISON, REGEN:
                yield 900;
            case SLOW_FALLING, WEAKNESS, SLOWNESS:
                yield 400;
            case TURTLE_MASTER:
                yield 1800;
            default:
                yield 3600;
        };
    }

    private PotionUtils() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}
