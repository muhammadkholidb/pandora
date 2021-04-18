package com.gitlab.muhammadkholidb.pandora.utility;

import java.util.Arrays;
import java.util.function.Consumer;

import com.gitlab.muhammadkholidb.pandora.exception.StageException;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class StageUtils {

    private static String defaultTitle;
    private static String[] defaultIconPaths;

    private StageUtils() {
    }

    public static void init(String defaultTitle, String[] defaultIconPaths) {
        StageUtils.defaultTitle = defaultTitle;
        StageUtils.defaultIconPaths = defaultIconPaths;
    }

    public static Stage show(IPage page, String title, String[] iconPaths, boolean resizeable,
            Consumer<WindowEvent> onClose) {
        try {
            Pane container = PageLoader.load(page);
            Scene scene = new Scene(container);
            Stage stage = new Stage();
            if (ArrayUtils.isNotEmpty(iconPaths)) {
                setIcons(stage, iconPaths);
            }
            if (StringUtils.isNotBlank(title)) {
                stage.setTitle(title);
            }
            stage.setResizable(resizeable);
            stage.initModality(Modality.APPLICATION_MODAL);
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

    public static Stage show(IPage page, boolean resizeable, Consumer<WindowEvent> onClose) {
        return show(page, defaultTitle, defaultIconPaths, resizeable, onClose);
    }

    public static Stage show(IPage page, boolean resizeable) {
        return show(page, resizeable, null);
    }

    public static Stage show(IPage page, Consumer<WindowEvent> onClose) {
        return show(page, true, onClose);
    }

    public static Stage show(IPage page) {
        return show(page, null);
    }

    public static void setIcons(Stage stage, String... iconPaths) {
        Arrays.stream(iconPaths)
                .forEach(path -> stage.getIcons().add(new Image(StageUtils.class.getResourceAsStream(path))));
    }

}
