package com.gitlab.muhammadkholidb.pandora.utility;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Supplier;

import javafx.fxml.FXMLLoader;

public class PageLoader {

    private static final String TEMPLATE_SUFFIX = ".fxml";

    private static String resourcePath;
    private static Supplier<ResourceBundle> bundleSupplier;

    private PageLoader() {
    }

    public static void init(String resourcePath, Supplier<ResourceBundle> bundleSupplier) {
        PageLoader.resourcePath = resourcePath;
        PageLoader.bundleSupplier = bundleSupplier;
    }

    public static <T> T load(IPage page) throws IOException {
        return load(page, bundleSupplier.get());
    }

    public static <T> T load(IPage page, ResourceBundle resourceBundle) throws IOException {
        String templateName = page.templateName();
        String path = String.format("%s%s", resourcePath,
                (templateName.endsWith(TEMPLATE_SUFFIX) ? templateName : templateName + TEMPLATE_SUFFIX));
        URL location = PageLoader.class.getResource(path);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(location);
        loader.setResources(resourceBundle);
        return loader.load();
    }

}
