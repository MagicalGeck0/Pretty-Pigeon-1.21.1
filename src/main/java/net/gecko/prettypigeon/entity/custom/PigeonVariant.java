package net.gecko.prettypigeon.entity.custom;

import java.util.Arrays;
import java.util.Comparator;

public enum PigeonVariant {
    DEFAULT(0),
    CARNEAU(1),
    SADDLEBACK(2),
    VERNANS(3),
    DRAGON(4),
    WARPED(5),
    CRIMSON(6),
    AETHER(7),
    BUMBLE(8),
    RIBBIT(9);

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
