package net.benfro.midieditor;

import javafx.collections.ListChangeListener;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import net.miginfocom.layout.LC;
import org.tbee.javafx.scene.layout.MigPane;

public class PluginPane extends MigPane {

    public PluginPane() {
        init();
    }

    public PluginPane(LC layoutConstraints) {
        super(layoutConstraints);
        init();
    }

    private void init() {
        setBackground(new Background(new BackgroundFill(Color.LIGHTGREY, CornerRadii.EMPTY, Insets.EMPTY)));
        //getBackground().getFills().add();
        getChildren().addListener(new ListChangeListener<Node>() {
            @Override
            public void onChanged(Change<? extends Node> c) {

            }
        });
    }


}
