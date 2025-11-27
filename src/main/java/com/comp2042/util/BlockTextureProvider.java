package com.comp2042.util;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public final class BlockTextureProvider {

    private static final int IMAGE_PADDING = 4;
    private static final Map<Integer, Paint> PATTERNS = new HashMap<>();
    private static final String[] IMAGE_NAMES = {
            null, "IBrick", "JBrick", "LBrick", "OBrick", "SBrick", "TBrick", "ZBrick", "PlusBrick"
    };

    static {
        PATTERNS.put(0, Color.TRANSPARENT);
        for (int id = 1; id < IMAGE_NAMES.length; id++) {
            PATTERNS.put(id, loadPattern(id));
        }
    }

    private static Paint loadPattern(int id) {
        String fileName = IMAGE_NAMES[id];
        if (fileName == null) {
            return Color.TRANSPARENT;
        }
        String path = String.format("images/%s.png", fileName);
        URL resource = BlockTextureProvider.class.getClassLoader().getResource(path);
        if (resource == null) {
            return Color.GRAY;
        }
        Image image = new Image(resource.toExternalForm());

        int targetSize = GameConstants.BRICK_SIZE;
        int imgWidth = (int) image.getWidth();
        int imgHeight = (int) image.getHeight();

        if (imgWidth > targetSize && imgHeight > targetSize) {
            PixelReader reader = image.getPixelReader();
            int cropWidth = imgWidth - IMAGE_PADDING * 2;
            int cropHeight = imgHeight - IMAGE_PADDING * 2;
            WritableImage cropped = new WritableImage(reader, IMAGE_PADDING, IMAGE_PADDING, cropWidth, cropHeight);
            return new ImagePattern(cropped);
        }

        return new ImagePattern(image);
    }

    public static Paint getPattern(int id) {
        return PATTERNS.getOrDefault(id, Color.TRANSPARENT);
    }

    private BlockTextureProvider() {
    }
}

