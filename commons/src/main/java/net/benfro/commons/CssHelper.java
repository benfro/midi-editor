package net.benfro.commons;

import com.guigarage.css.DefaultPropertyBasedCssMetaData;
import com.guigarage.css.SkinPropertyBasedCssMetaData;
import javafx.css.converter.EnumConverter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javafx.css.CssMetaData;
import javafx.css.SimpleStyleableObjectProperty;
import javafx.css.StyleConverter;
import javafx.css.Styleable;
import javafx.css.StyleableObjectProperty;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Control;
import javafx.scene.control.SkinBase;

public class CssHelper {
    public CssHelper() {
    }

    public static List<CssMetaData<? extends Styleable, ?>> createCssMetaDataList(List<CssMetaData<? extends Styleable, ?>> baseList, CssMetaData<? extends Styleable, ?>... metaData) {
        List<CssMetaData<? extends Styleable, ?>> styleables = new ArrayList(baseList);
        styleables.addAll(Arrays.asList(metaData));
        return Collections.unmodifiableList(styleables);
    }

    public static List<CssMetaData<? extends Styleable, ?>> createCssMetaDataList(CssMetaData<? extends Styleable, ?>... metaData) {
        return createCssMetaDataList(new ArrayList(), metaData);
    }

    public static <S extends Styleable, V> StyleableObjectProperty<V> createProperty(DefaultPropertyBasedCssMetaData<S, V> metaData, S styleable) {
        return new SimpleStyleableObjectProperty(metaData, styleable, metaData.getPropertyName(), metaData.getInitialValue(styleable));
    }

    public static <S extends Control, V> StyleableObjectProperty<V> createProperty(SkinPropertyBasedCssMetaData<S, V> metaData, SkinBase<S> skin) {
        return new SimpleStyleableObjectProperty(metaData, skin, metaData.getPropertyName(), metaData.getInitialValue(skin.getSkinnable()));
    }

    public static <S extends Styleable, V> DefaultPropertyBasedCssMetaData<S, V> createMetaData(String property, StyleConverter<?, V> converter, String propertyName, V defaultValue) {
        return new DefaultPropertyBasedCssMetaData(property, converter, propertyName, defaultValue);
    }

    public static <S extends Control, V> SkinPropertyBasedCssMetaData<S, V> createSkinMetaData(String property, StyleConverter<?, V> converter, String propertyName, V defaultValue) {
        return new SkinPropertyBasedCssMetaData(property, converter, propertyName, defaultValue);
    }

    public static <S extends Control, T extends VPos> SkinPropertyBasedCssMetaData<S, T> createSkinMetaDataForVPos(String property, String propertyName, T defaultValue) {
        return new SkinPropertyBasedCssMetaData(property, new EnumConverter(VPos.class), propertyName, defaultValue);
    }

    public static <S extends Control, T extends HPos> SkinPropertyBasedCssMetaData<S, T> createSkinMetaDataForHPos(String property, String propertyName, T defaultValue) {
        return new SkinPropertyBasedCssMetaData(property, new EnumConverter(HPos.class), propertyName, defaultValue);
    }

    public static <S extends Control, T extends Pos> SkinPropertyBasedCssMetaData<S, T> createSkinMetaDataForPos(String property, String propertyName, T defaultValue) {
        return new SkinPropertyBasedCssMetaData(property, new EnumConverter(Pos.class), propertyName, defaultValue);
    }
}