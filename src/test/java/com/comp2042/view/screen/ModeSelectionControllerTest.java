package com.comp2042.view.screen;

import com.comp2042.view.HomeSelection;
import com.comp2042.testutil.JavaFxTestUtil;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.media.MediaView;
import javafx.scene.layout.StackPane;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;

class ModeSelectionControllerTest {

    @BeforeAll
    static void initToolkit() {
        JavaFxTestUtil.initFx();
    }

    private static class ControllerFixture {
        final ModeSelectionController controller;
        final Label optionOneTitle;
        final Label optionTwoTitle;
        final Label optionThreeTitle;
        final Label badgeOne;
        final Label badgeTwo;
        final Label badgeThree;

        ControllerFixture(ModeSelectionController controller,
                          Label optionOneTitle,
                          Label optionTwoTitle,
                          Label optionThreeTitle,
                          Label badgeOne,
                          Label badgeTwo,
                          Label badgeThree) {
            this.controller = controller;
            this.optionOneTitle = optionOneTitle;
            this.optionTwoTitle = optionTwoTitle;
            this.optionThreeTitle = optionThreeTitle;
            this.badgeOne = badgeOne;
            this.badgeTwo = badgeTwo;
            this.badgeThree = badgeThree;
        }
    }

    private ControllerFixture createFixture() {
        ModeSelectionController controller = new ModeSelectionController();

        StackPane selectionRoot = new StackPane();
        MediaView backgroundVideo = new MediaView();

        Button optionOne = new Button();
        Button optionTwo = new Button();
        Button optionThree = new Button();

        Label badgeOne = new Label();
        Label badgeTwo = new Label();
        Label badgeThree = new Label();

        Label optionOneTitle = new Label();
        Label optionTwoTitle = new Label();
        Label optionThreeTitle = new Label();

        Label optionOneSubtitle = new Label();
        Label optionTwoSubtitle = new Label();
        Label optionThreeSubtitle = new Label();

        setPrivateField(controller, "selectionRoot", selectionRoot);
        setPrivateField(controller, "backgroundVideo", backgroundVideo);

        setPrivateField(controller, "optionOne", optionOne);
        setPrivateField(controller, "optionTwo", optionTwo);
        setPrivateField(controller, "optionThree", optionThree);

        setPrivateField(controller, "badgeOne", badgeOne);
        setPrivateField(controller, "badgeTwo", badgeTwo);
        setPrivateField(controller, "badgeThree", badgeThree);

        setPrivateField(controller, "optionOneTitle", optionOneTitle);
        setPrivateField(controller, "optionTwoTitle", optionTwoTitle);
        setPrivateField(controller, "optionThreeTitle", optionThreeTitle);

        setPrivateField(controller, "optionOneSubtitle", optionOneSubtitle);
        setPrivateField(controller, "optionTwoSubtitle", optionTwoSubtitle);
        setPrivateField(controller, "optionThreeSubtitle", optionThreeSubtitle);


        return new ControllerFixture(
                controller,
                optionOneTitle,
                optionTwoTitle,
                optionThreeTitle,
                badgeOne,
                badgeTwo,
                badgeThree
        );
    }

    private void setPrivateField(Object target, String fieldName, Object value) {
        try {
            Field f = target.getClass().getDeclaredField(fieldName);
            f.setAccessible(true);
            f.set(target, value);
        } catch (Exception e) {
            throw new RuntimeException("Failed to set field: " + fieldName, e);
        }
    }

    private void invokePrivateNoArgs(Object target, String methodName) {
        try {
            Method m = target.getClass().getDeclaredMethod(methodName);
            m.setAccessible(true);
            m.invoke(target);
        } catch (Exception e) {
            throw new RuntimeException("Failed to invoke method: " + methodName, e);
        }
    }

    @Test
    void configure_SetsUpTimeRacingTexts() {
        ControllerFixture fixture = createFixture();
        ModeSelectionController controller = fixture.controller;

        controller.configure(
                HomeSelection.Mode.TIME_RACING,
                selection -> {},
                () -> {}
        );

        assertEquals("1 Minute Sprint", fixture.optionOneTitle.getText());
        assertEquals("3 Minute Rush", fixture.optionTwoTitle.getText());
        assertEquals("5 Minute Marathon", fixture.optionThreeTitle.getText());

        assertEquals("1M", fixture.badgeOne.getText());
        assertEquals("3M", fixture.badgeTwo.getText());
        assertEquals("5M", fixture.badgeThree.getText());
    }

    @Test
    void handleOptionOne_FiresSelectionWithCorrectOption() {
        ControllerFixture fixture = createFixture();
        ModeSelectionController controller = fixture.controller;

        AtomicReference<HomeSelection> selectionRef = new AtomicReference<>();

        controller.configure(
                HomeSelection.Mode.TIME_RACING,
                selectionRef::set,
                () -> {}
        );

        invokePrivateNoArgs(controller, "handleOptionOne");

        HomeSelection selection = selectionRef.get();
        assertNotNull(selection);
        assertEquals(HomeSelection.Mode.TIME_RACING, selection.mode());
        assertEquals(fixture.optionOneTitle.getText(), selection.option());
    }
}
