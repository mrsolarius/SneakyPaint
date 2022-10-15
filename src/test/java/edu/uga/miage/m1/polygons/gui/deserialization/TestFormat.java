package edu.uga.miage.m1.polygons.gui.deserialization;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestFormat {
    @Test
    void testExists(){
        Format format = Format.JSON;
        Format format2 = Format.XML;
        assertEquals (Format.JSON,format);
        assertEquals (Format.XML,format2);
    }
}
