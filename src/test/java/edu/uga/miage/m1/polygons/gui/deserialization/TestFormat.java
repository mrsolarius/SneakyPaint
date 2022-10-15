package edu.uga.miage.m1.polygons.gui.deserialization;

import org.junit.jupiter.api.Test;

public class TestFormat {
    @Test
    void testExists(){
        Format format = Format.JSON;
        Format format2 = Format.XML;
        assert (format.equals(Format.JSON));
        assert (format2.equals(Format.XML));
    }
}
