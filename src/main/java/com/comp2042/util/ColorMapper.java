package com.comp2042.util;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.HashMap;
import java.util.Map;

public final class ColorMapper {
    
    private static final Map<Integer, Paint> COLOR_MAP = new HashMap<>();
    
    static {
        COLOR_MAP.put(0, Color.TRANSPARENT);
        COLOR_MAP.put(1, Color.AQUA);
        COLOR_MAP.put(2, Color.BLUEVIOLET);
        COLOR_MAP.put(3, Color.DARKGREEN);
        COLOR_MAP.put(4, Color.YELLOW);
        COLOR_MAP.put(5, Color.RED);
        COLOR_MAP.put(6, Color.BEIGE);
        COLOR_MAP.put(7, Color.BURLYWOOD);
        COLOR_MAP.put(8, Color.HOTPINK);
    }
    
    public static Paint getColor(int colorCode) {
        return COLOR_MAP.getOrDefault(colorCode, Color.WHITE);
    }
    
    private ColorMapper() {
    }
}

