package com.gitlab.muhammadkholidb.pandora.utility;

import java.util.Arrays;
import java.util.function.Consumer;

import com.gitlab.muhammadkholidb.pandora.exception.StageException;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class StageUtils {

    private static String defaultTitle;
    private static String[] defaultIconPaths;

    private StageUtils() {
    }

    /**
     * Initializes this class with default values.
     * 
     * @param defaultTitle     default title for a stage.
     * @param defaultIconPaths default icon paths for a stage.
     */
    public static void init(String defaultTitle, String[] defaultIconPaths) {
        if (ObjectUtils.allNotNull(StageUtils.defaultTitle, StageUtils.defaultIconPaths)) {
            throw new IllegalStateException("Already initialized! Call reset() before reinitialize.");
        }
        StageUtils.defaultTitle = defaultTitle;
        StageUtils.defaultIconPaths = defaultIconPaths;
    }

    /**
     * Resets this class's default values to null.
     */
    public static void reset() {
        StageUtils.defaultTitle = null;
        StageUtils.defaultIconPaths = null;
    }

    /**
     * Creates and displays a stage.
     * 
     * @param page       page to display in this stage.
     * @param title      stage title.
     * @param iconPaths  stage icon paths.
     * @param resizeable is this stage can be resized?
     * @param modality   modality of the stage.
     * @param onClose    action to run on stage close.
     * @return the created and displayed stage.
     */
    public static Stage open(IPage page, String title, String[] iconPaths, boolean resizeable, Modality modality,
            Consumer<WindowEvent> onClose) {

        try {
            PageContext container = PageLoader.load(page);
            Scene scene = new Scene(container.getRoot());
            Stage stage = new Stage();
            if (ArrayUtils.isNotEmpty(iconPaths)) {
                setIcons(stage, iconPaths);
            }
            if (StringUtils.isNotBlank(title)) {
                stage.setTitle(title);
            }
            stage.setResizable(resizeable);
            stage.initModality(modality);
            stage.setScene(scene);
            if (onClose != null) {
                stage.setOnHidden(onClose::accept);
            }
            stage.show();
            return stage;
        } catch (Exception e) {
            throw new StageException(String.format("Failed to show page %s: %s", page.toString(), e.getMessage()));
        }
    }

    public static Stage open(IPage page, boolean resizeable, Consumer<WindowEvent> onClose) {
        return open(page, defaultTitle, defaultIconPaths, resizeable, Modality.NONE, onClose);
    }

    public static Stage open(IPage page, boolean resizeable) {
        return open(page, resizeable, null);
    }

    public static Stage open(IPage page, Consumer<WindowEvent> onClose) {
        return open(page, true, onClose);
    }

    public static Stage open(IPage page) {
        return open(page, null);
    }

    public static Stage modal(IPage page, boolean resizeable, Consumer<WindowEvent> onClose) {
        return open(page, defaultTitle, defaultIconPaths, resizeable, Modality.APPLICATION_MODAL, onClose);
    }

    public static Stage modal(IPage page, boolean resizeable) {
        return modal(page, resizeable, null);
    }

    public static Stage modal(IPage page, Consumer<WindowEvent> onClose) {
        return modal(page, true, onClose);
    }

    public static Stage modal(IPage page) {
        return modal(page, null);
    }

    public static void setIcons(Stage stage, String... iconPaths) {
        Arrays.stream(iconPaths)
                .forEach(path -> stage.getIcons().add(new Image(StageUtils.class.getResourceAsStream(path))));
    }

}
