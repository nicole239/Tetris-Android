package tec.tetris;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum BlockColor {
    RED,
    GREEN,
    BLUE,
    LIGHT_BLUE,
    ORANGE,
    YELLOW,
    PURPLE,
    GREY,
    BLACK;

    private static final List<BlockColor> VALUES =
            Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size() - 2;
    private static final Random RANDOM = new Random();

    public static BlockColor getRandom()  {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
}
