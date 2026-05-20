package net.gecko.prettypigeon.entity.custom;

import java.util.Arrays;
import java.util.Comparator;

public enum PigeonCore {

    NONE(0),
    RAD(1),
    ECHO(2),
    AMETHYST(3);


    private static final PigeonCore[] BY_ID = Arrays.stream(values()).sorted(Comparator.comparingInt(PigeonCore::getId)).toArray(PigeonCore[]::new);

    private final int id;

    PigeonCore(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public static PigeonCore byid(int id) {
        return BY_ID[id % BY_ID.length];
    }
}
