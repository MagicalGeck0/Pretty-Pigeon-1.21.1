package net.gecko.prettypigeon.entity.custom;

import java.util.Arrays;
import java.util.Comparator;

public enum PigeonHat {
    DEFAULT(0),
    FEZ(1),
    GLASSES(2),
    HEADPHONES(3),
    RAM(4),
    CROWN(5),
    FUNNYHAT(6),
    FEATHERCROWN(7);

    private static final PigeonHat[] BY_ID = Arrays.stream(values()).sorted(Comparator.comparingInt(PigeonHat::getId)).toArray(PigeonHat[]::new);

    private final int id;

    PigeonHat(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public static PigeonHat byid(int id) {
        return BY_ID[id % BY_ID.length];
    }
}
