package edu.uga.miage.m1.libs.shapes.persistence;

import edu.uga.miage.m1.libs.shapes.*;

/**
 * You must define a method for each type of Visitable.
 * @author <a href="mailto:christophe.saint-marcel@univ-grenoble-alpes.fr">Christophe</a>
 *
 */
public interface Visitor {
	void visit(Circle circle);
	void visit(Square square);
	void visit(Triangle triangle);
	void visit(Group group);

	void visit(ImageShape imageShape);
}