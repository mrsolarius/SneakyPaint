/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package edu.uga.miage.m1.libs.shapes;

import edu.uga.miage.m1.libs.shapes.persistence.Visitor;

import java.awt.*;
import java.awt.geom.GeneralPath;

/**
 * This inner class implements the triangle <tt>SimpleShape</tt> service.
 * It simply provides a <tt>draw()</tt> that paints a triangle.
 *
 * @author <a href="mailto:christophe.saint-marcel@univ-grenoble-alpes.fr">Christophe</a>
 */
public class Triangle extends AbstractShape {
    protected Triangle(int x, int y) {
        super(x, y);
    }

    protected Triangle(int x, int y, int width, int height, int elevation) {
        super(x, y, width, height, elevation);
    }

    /**
     * Implements the <tt>SimpleShape.draw()</tt> method for painting
     * the shape.
     */
    public void draw(Graphics2D g2) {
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint gradient = new GradientPaint(x, y, Color.GREEN, x + 50, y, Color.WHITE);
        g2.setPaint(gradient);
        int[] xcoords = { x + 25, x, x + 50 };
        int[] ycoords = {y, y + 50, y + 50 };
        GeneralPath polygon = new GeneralPath(GeneralPath.WIND_EVEN_ODD, xcoords.length);
        polygon.moveTo(x + 25, y);
        for (int i = 0; i < xcoords.length; i++) {
            polygon.lineTo(xcoords[i], ycoords[i]);
        }
        polygon.closePath();
        g2.fill(polygon);
        BasicStroke wideStroke = new BasicStroke(2.0f);
        g2.setColor(Color.black);
        g2.setStroke(wideStroke);
        g2.draw(polygon);
        if (selected) {
            drawSelection(g2);
        }
    }

    /**
     * Implements the visitor pattern for the shape.
     * @param visitor the visitor
     */
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
