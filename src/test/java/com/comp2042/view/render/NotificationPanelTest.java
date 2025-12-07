package com.comp2042.view.render;

import com.comp2042.testutil.JavaFxTestUtil;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotificationPanelTest {

    @BeforeAll
    static void initFx() {
        JavaFxTestUtil.initFx();
    }

    @Test
    void constructor_SetsCenterLabelWithTextAndStyle() {
        String message = "+1 BOMB";
        NotificationPanel panel = new NotificationPanel(message);

        assertTrue(panel instanceof BorderPane);

        Node center = panel.getCenter();
        assertNotNull(center, "Center node should not be null");
        assertTrue(center instanceof Label, "Center node should be a Label");

        Label label = (Label) center;
        assertEquals(message, label.getText(), "Label text should match constructor argument");
        assertTrue(label.getStyleClass().contains("bonusStyle"),
                "Label should have 'bonusStyle' CSS class");
    }

    @Test
    void constructor_SetsMinimumSize() {
        NotificationPanel panel = new NotificationPanel("test");

        assertTrue(panel.getMinWidth() > 0, "Panel should have a positive minimum width");
        assertTrue(panel.getMinHeight() > 0, "Panel should have a positive minimum height");
    }
}
