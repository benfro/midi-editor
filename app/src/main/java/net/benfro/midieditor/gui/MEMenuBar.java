package net.benfro.midieditor.gui;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import lombok.Getter;

@Getter
public class MEMenuBar {

    private final MenuBar menuBar;

    public MEMenuBar () {
        menuBar = new MenuBar(new Menu("File"), new Menu("Tools"), new Menu("MIDI"));
    }
}
