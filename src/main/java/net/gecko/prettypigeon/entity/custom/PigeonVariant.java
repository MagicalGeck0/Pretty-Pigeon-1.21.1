package net.gecko.prettypigeon.entity.custom;

import java.util.Arrays;
import java.util.Comparator;

public enum PigeonVariant {
    /*natural*/
    COMMON(0),
    CARNEAU(1),
    SADDLEBACK(2),
    VERNANS(3),
    LUZON(4),
    WOMPOO(5),
    SOMBRE(6),

    /*mutated*/
    DRAGON(7),
    WARPED(8),
    CRIMSON(9),

    /*modded*/
    AETHER(10),
    BUMBLE(11),
    RIBBIT(12);

    private static final PigeonVariant[] BY_ID = Arrays.stream(values()).sorted(Comparator.comparingInt(PigeonVariant::getId)).toArray(PigeonVariant[]::new);

    private final int id;

    PigeonVariant(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public static PigeonVariant byid(int id) {
        return BY_ID[id % BY_ID.length];
    }
}
