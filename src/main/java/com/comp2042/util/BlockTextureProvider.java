package com.comp2042.util;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;

import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * The {@code BlockTextureProvider} class loads and caches paint patterns for each brick type, cropping images
 * where necessary to remove padding. It serves the rendering layer by mapping brick IDs to reusable
 * {@link Paint} instances.
 *
 * <p>See the source code at
 * <a href="https://github.com/JIMMY081105/CW2025/tree/master/src/main/java/com/comp2042/util/BlockTextureProvider.java">
 * BlockTextureProvider.java</a>
 */
public final class BlockTextureProvider {

    private static final int IMAGE_PADDING = 4;
    private static final String BRICK_IMAGE_PATH_FORMAT = "images/Bricks/%s.png";

    private static final String[] IMAGE_NAMES = {
            null, "IBrick", "JBrick", "LBrick", "OBrick", "SBrick", "TBrick", "ZBrick", "PlusBrick"
    };

    private static final Map<Integer, Paint> PATTERNS;

    static {
        Map<Integer, Paint> patterns = new HashMap<>();
        patterns.put(0, Color.TRANSPARENT);
        for (int id = 1; id < IMAGE_NAMES.length; id++) {
            patterns.put(id, loadPattern(id));
        }
        PATTERNS = Collections.unmodifiableMap(patterns);
    }

    private static Paint loadPattern(int id) {
        String fileName = IMAGE_NAMES[id];
        if (fileName == null) {
            return Color.TRANSPARENT;
        }

        String path = String.format(BRICK_IMAGE_PATH_FORMAT, fileName);
        URL resource = BlockTextureProvider.class.getClassLoader().getResource(path);
        if (resource == null) {
            return Color.GRAY;
        }

        Image image = new Image(resource.toExternalForm());
        int imgWidth = (int) image.getWidth();
        int imgHeight = (int) image.getHeight();

        if (imgWidth > IMAGE_PADDING * 2 && imgHeight > IMAGE_PADDING * 2) {
            PixelReader reader = image.getPixelReader();
            int cropWidth = imgWidth - IMAGE_PADDING * 2;
            int cropHeight = imgHeight - IMAGE_PADDING * 2;
            WritableImage cropped = new WritableImage(reader, IMAGE_PADDING, IMAGE_PADDING, cropWidth, cropHeight);
            return new ImagePattern(cropped);
        }

        return new ImagePattern(image);
    }

    /**
     * Returns a cached paint pattern corresponding to the given brick identifier.
     *
     * @param id brick identifier used in board matrices.
     * @return paint pattern for rendering; transparent if unknown.
     */
    public static Paint getPattern(int id) {
        return PATTERNS.getOrDefault(id, Color.TRANSPARENT);
    }

    private BlockTextureProvider() {
    }
}
