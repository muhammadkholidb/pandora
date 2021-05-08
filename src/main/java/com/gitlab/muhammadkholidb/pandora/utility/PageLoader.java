package com.gitlab.muhammadkholidb.pandora.utility;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Supplier;

import org.apache.commons.lang3.ObjectUtils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class PageLoader {

    private static final String TEMPLATE_SUFFIX = ".fxml";

    private static String resourcePath;
    private static Supplier<ResourceBundle> bundleSupplier;

    private PageLoader() {
    }

    public static void init(String resourcePath, Supplier<ResourceBundle> bundleSupplier) {
        if (ObjectUtils.allNotNull(PageLoader.resourcePath, PageLoader.bundleSupplier)) {
            throw new IllegalStateException("Already initialized! Call reset() before reinitialize.");
        }
        PageLoader.resourcePath = resourcePath;
        PageLoader.bundleSupplier = bundleSupplier;
    }

    public static void reset() {
        PageLoader.resourcePath = null;
        PageLoader.bundleSupplier = null;
    }

    public static PageContext load(IPage page) throws IOException {
        return load(page, bundleSupplier.get());
    }

    public static PageContext load(IPage page, ResourceBundle resourceBundle) throws IOException {
        String templateName = page.templateName();
        String path = String.format("%s%s", resourcePath,
                (templateName.endsWith(TEMPLATE_SUFFIX) ? templateName : templateName + TEMPLATE_SUFFIX));
        URL location = PageLoader.class.getResource(path);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(location);
        loader.setResources(resourceBundle);
        Parent root = loader.load();
        PageContext pageContext = new PageContext();
        pageContext.setRoot(root);
        pageContext.setController(loader.getController());
        pageContext.setLocation(location);
        pageContext.setResources(resourceBundle);
        return pageContext;
    }

}
