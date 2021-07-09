package com.gitlab.muhammadkholidb.pandora;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import javafx.application.Platform;

public abstract class JavaFXTestBase {
    
    @BeforeAll
    static void beforeAll() {
        Platform.startup(() -> {});
    }

    @AfterAll
    static void afterAll() {
        Platform.exit();
    }
}
