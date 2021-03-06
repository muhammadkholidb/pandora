package com.gitlab.muhammadkholidb.pandora.utility;

import java.util.Arrays;

import com.gitlab.muhammadkholidb.pandora.exception.StageException;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

public final class StageUtils {

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
     * 
     * @return the created and displayed stage.
     */
    public static Stage open(
            IPage page,
            String title,
            String[] iconPaths,
            boolean resizeable,
            Modality modality,
            StageStyle stageStyle,
            EventHandler<WindowEvent> onClose) {

        try {
            PageContext container = PageLoader.load(page);
            Scene scene = new Scene(container.getRoot());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(resizeable);
            if (ArrayUtils.isNotEmpty(iconPaths)) {
                setIcons(stage, iconPaths);
            }
            if (StringUtils.isNotBlank(title)) {
                stage.setTitle(title);
            }
            if (modality != null) {
                stage.initModality(modality);
            }
            if (stageStyle != null) {
                stage.initStyle(stageStyle);
            }
            if (onClose != null) {
                stage.setOnHidden(onClose::handle);
            }
            stage.show();
            return stage;
        } catch (Exception e) {
            throw new StageException(String.format("Failed to show page %s: %s", page.toString(), e.getMessage()), e);
        }
    }

    public static Stage open(IPage page, boolean resizeable, EventHandler<WindowEvent> onClose) {
        return open(page, defaultTitle, defaultIconPaths, resizeable, Modality.NONE, null, onClose);
    }

    public static Stage open(IPage page, boolean resizeable) {
        return open(page, resizeable, null);
    }

    public static Stage open(IPage page, EventHandler<WindowEvent> onClose) {
        return open(page, true, onClose);
    }

    public static Stage open(IPage page) {
        return open(page, null);
    }

    public static Stage modal(
            IPage page,
            boolean resizeable,
            StageStyle stageStyle,
            EventHandler<WindowEvent> onClose) {
        return open(page, defaultTitle, defaultIconPaths, resizeable, Modality.APPLICATION_MODAL, stageStyle, onClose);
    }

    public static Stage modal(IPage page, boolean resizeable, EventHandler<WindowEvent> onClose) {
        return open(page, defaultTitle, defaultIconPaths, resizeable, Modality.APPLICATION_MODAL, null, onClose);
    }

    public static Stage modal(IPage page, boolean resizeable) {
        return modal(page, resizeable, null, null);
    }

    public static Stage modal(IPage page, EventHandler<WindowEvent> onClose) {
        return modal(page, true, null, onClose);
    }

    public static Stage modal(IPage page) {
        return modal(page, true, null, null);
    }

    public static Stage modal(IPage page, StageStyle stageStyle) {
        return modal(page, true, stageStyle, null);
    }

    public static void setIcons(Stage stage, String... iconPaths) {
        Arrays.stream(iconPaths)
                .forEach(path -> stage.getIcons().add(new Image(StageUtils.class.getResourceAsStream(path))));
    }

}
