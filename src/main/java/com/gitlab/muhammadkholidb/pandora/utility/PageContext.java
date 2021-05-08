package com.gitlab.muhammadkholidb.pandora.utility;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.Parent;
import lombok.Data;

@Data
public class PageContext {
    private Parent root;
    private Object controller;
    private URL location;
    private ResourceBundle resources;
}
