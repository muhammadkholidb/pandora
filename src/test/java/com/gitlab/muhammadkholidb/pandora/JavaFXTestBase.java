package com.gitlab.muhammadkholidb.pandora;

import java.util.concurrent.CountDownLatch;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import javafx.application.Platform;

public abstract class JavaFXTestBase {

    @BeforeAll
    static void beforeAll() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);
        Platform.startup(() -> {
        });
        latch.countDown();
        latch.await();
    }

    @AfterAll
    static void afterAll() {
        Platform.exit();
    }
}
