package com.comp2042.data;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

/**
 * The {@code ChinaStageDescriptionProvider} class loads Explore China stage metadata from a bundled properties
 * file and exposes immutable descriptions for use by the China stage manager.
 *
 * <p>See the source code at
 * <a href="https://github.com/JIMMY081105/CW2025/tree/master/src/main/java/com/comp2042/data/ChinaStageDescriptionProvider.java">
 * ChinaStageDescriptionProvider.java</a>
 */
public final class ChinaStageDescriptionProvider {

    public static final class ChinaStage {
        private final String name;
        private final String backgroundResource;
        private final String description;

        public ChinaStage(String name, String backgroundResource, String description) {
            this.name = name;
            this.backgroundResource = backgroundResource;
            this.description = description;
        }

        public String getName() {
            return name;
        }

        public String getBackgroundResource() {
            return backgroundResource;
        }

        public String getDescription() {
            return description;
        }

        @Override
        public String toString() {
            return "ChinaStage{" +
                    "name='" + name + '\'' +
                    ", backgroundResource='" + backgroundResource + '\'' +
                    '}';
        }
    }

    private static final String CONFIG_FILE = "china_stages.properties";
    private static final List<ChinaStage> STAGES = loadStages();

    private ChinaStageDescriptionProvider() {

    }

    public static List<ChinaStage> getStages() {
        return STAGES;
    }

    private static List<ChinaStage> loadStages() {
        Properties props = new Properties();

        System.out.println("[ChinaStageDescriptionProvider] Loading properties from: " + CONFIG_FILE);

        try (InputStream in = ChinaStageDescriptionProvider.class
                .getClassLoader()
                .getResourceAsStream(CONFIG_FILE)) {

            if (in == null) {
                throw new IllegalStateException("Missing resource: " + CONFIG_FILE);
            }

            props.load(in);
        } catch (IOException e) {
            throw new IllegalStateException("Failed to load " + CONFIG_FILE, e);
        }

        int count = Integer.parseInt(props.getProperty("stage.count", "0"));
        System.out.println("[ChinaStageDescriptionProvider] stage.count=" + count);

        if (count <= 0) {
            throw new IllegalStateException("Invalid or missing 'stage.count' in " + CONFIG_FILE);
        }

        List<ChinaStage> stages = new ArrayList<>(count);

        for (int i = 1; i <= count; i++) {
            String prefix = "stage." + i + ".";

            String name = props.getProperty(prefix + "name");
            String image = props.getProperty(prefix + "image");
            String description = props.getProperty(prefix + "description");

            if (name == null || image == null || description == null) {
                throw new IllegalStateException("Missing properties for " + prefix + " in " + CONFIG_FILE);
            }

            ChinaStage stage = new ChinaStage(name, image, description);
            System.out.println("[ChinaStageDescriptionProvider] Loaded " + stage);
            stages.add(stage);
        }

        return Collections.unmodifiableList(stages);
    }
}
