# Sneaky Paint
## Introduction
Sneaky Paint est un petit logiciel très simple qui permet de :
- poser des formes géométriques (carré, cercle, triangle)
- grouper des formes
- déplacer des formes
- definir leur élévation
- sauvegarder le dessin dans un fichier
- charger un dessin depuis un fichier

Les buts principaux de ce projet sont de nous faire pratiquer la programmation orienté objet en java en utilisant les patrons de conception.

## Installation
### Prérequis
- Java 18
- Maven 3.8.1

### Procédure d'installation
Pour installer le projet, il suffit de cloner le projet git :
```bash
git clone git@github.com:mrsolarius/SneakyPaint.git
```
Puis de lancer 
```bash
mvn clean compile exec:java
```

### Procédure de test
Pour lancer les tests, il suffit de lancer la commande suivante :
```bash
mvn clean test
```


## Utilisation
### Poser une forme
Pour poser une forme, il suffit de cliquer sur le bouton correspondant à la forme que l'on souhaite poser sur la gauche.
Ensuite, il suffit de cliquer sur la zone de dessin pour poser la forme.

### Mode sélection
Pour passer en mode sélection, il suffit de cliquer sur le bouton "Sélection" en forme de souris en bas à gauche.

Ensuite, il suffit de cliquer sur la ou les formes que l'on souhaite sélectionner.

On peut alors déplacer le ou les formes en le(s) glissant(s) avec la souris.

Vous pouvez aussi modifier l'élevation de la forme en cliquant sur le menu "Edit" puis "Elevate" ou "Lower".

Vous pouvez supprimer la forme en cliquant sur le menu "Edit" puis "Delete".

Il est aussi possible de grouper les formes en cliquant sur le menu "Edit" puis "Group".

Lorsqu'un seul goupe est sélectionné, il est possible de le dégrouper en cliquant sur le menu "Edit" puis "Ungroup".

### Sauvegarder le dessin
Pour sauvegarder le dessin, il suffit de cliquer sur le menu "File" puis "Save as XML" ou "Save as JSON" et de suivre les instructions.

### Charger un dessin
Pour charger un dessin, il suffit de cliquer sur le menu "File" puis "Open" et de suivre les instructions.

### Raccourcis clavier

| **Action**  | **Raccourcis**                   |
|-------------|----------------------------------|
| **Undo**    | <kbd>Ctrl</kbd>\+<kbd>Z</kbd>    |
| **Redo**    | <kbd>Ctrl</kbd>\+<kbd>Y</kbd>    |
| **Group**   | <kbd>Ctrl</kbd>\+<kbd>G</kbd>    |
| **Ungroup** | <kbd>Ctrl</kbd>\+<kbd>U</kbd>    |
| **Elevate** | <kbd>Ctrl</kbd>\+<kbd>Up</kbd>   |
| **Lower**   | <kbd>Ctrl</kbd>\+<kbd>Down</kbd> |

## Documentation de conception
Afin de respected au mieux les principes de KISS et de maintenabilité, nous avons choisi de suivre les patrons de conceptions suivants :
- Visiteur (pour l'enregistrement du dessin en XML ou JSON)
- Composite (pour la gestion des groupes)
- Factory (pour la création des formes)
- State (pour la gestion des comportements des formes et de l'application)
- Command (pour l'historisation des actions)

### Concepts généraux
L'application est principalement basée sur la classe whiteboard qui hérite de la classe JPanel.
C'est elle qui stock les formes et qui les affiche.
Pour supporter ces fonctionnalités, elle est au centre de deux patrons :
- Le patron command pour l'historisation des actions
- Le patron state pour gérer les comportements de l'application

Le whiteboard dispose d'un tableau de formes, ces formes sont le deuxième centre de l'application.
En effet, ce sont elles qui sont affichées et qui sont sauvegardées.
Elles sont, elles aussi, au centre de plusieurs patrons :
- Le patron composite pour la gestion des groupes
- Le patron factory pour la création des formes
- Le patron visitor pour l'enregistrement du dessin en XML ou JSON
- Le patron state pour gérer les comportements des formes

L'export en XML ou JSON est géré par des classes utilitaires.
Qui implémentent le patron visitor.
Cependant, petite particularité pour l'import, on utilise `jackson-dataformat-xml` pour parser le XML et `jackson-databind` pour parser le JSON.
Ces deux librairies, on l'avantage de mapper directement les données du fichier dans des objets java nos DTO.
Les DTO sont ensuite convertis en forme par la `ShapeFactory`.

### Patron visiteur et composite
![](doc\svg\Model!composite_visiteur_shapes.svg)

### Patron Factory
Il est utilisé pour la création des formes.
Nous avons choisi de ne pas en faire un diagramme de classe car il est extrêmement simple.
En effet, il s'agit d'une classe : `ShapeFactory` qui possède une méthode `createShape` qui dispose de plusieurs méthodes statiques 
qui permettent de créer les différentes formes.
Elle nous sert aussi pour la sérialisation des formes afin de convertir nos DTO en formes.

### Patron Command
![](doc\svg\Model!patron_command_whiteboard.svg)
Comme on peut l'observer ici le whiteboard est le centre du patron command.
Ici 13 commandes sont implémentées :
- AddCircleCommand
- AddCircleCommand
- AddTriangleCommand
- SelectCommand
- DeleteCommand
- GroupCommand
- UngroupCommand
- ElevateCommand
- LowerCommand
- MoveCommand
- UnselectAllCommand
- Undo
- Redo

Elles sont organisées autour d'héritages multiples de classes abstraites afin de factoriser les eventuels codes dupliqués.
Par exemple, les commandes `AddSquareCommand`, `AddCircleCommand` et `AddRectangleCommand` héritent de la classe `AbstractAddShapeCommand` qui implémente les méthodes `execute`, `undo` et `redo`.
Elles leur demandent juste d'implémenter une méthode `createShape` qui permet de créer la forme à ajouter.


L'historisation des actions est gérée par la classe `History` qui stock les commandes dans une liste et sauvgarde l'index de la dernière commande exécutée.
Chaque commande lorsqu'elle le juge nécessaire, peut appeler la méthode `addCommand` de la classe `History` afin d'ajouter une nouvelle commande à la liste.
Lorsqu'on veut annuler une action, on décrémente l'index et on exécute la commande à l'index avec la methode `undo`.
Lorsqu'on veut refaire une action, on incrémente l'index et on exécute la commande à l'index avec la methode `redo`.

Et évidemment, l'exécution des méthodes `undo` et `redo` de l'historique sont gérées directement par des commandes.

### Patron State
#### Implémentation sur les formes
![](doc\svg\Model!patron_state_shape.svg)
Les formes disposent de plusieurs états :
- Selected
- Unselected
- Grouped

Chaque état ne dispose pas des mêmes autorisations.
Par exemple, on ne peut pas déplacer une forme qui n'est pas sélectionnée.

Voici le diagramme d'état transition qui décrit les différents états et les transitions entre eux.
![](doc\svg\Model!FSM!patron_state_shape_etat_transition.svg)

Pour préciser les transitions et comportements des états, voici le diagrame de séquence qui décrit les différents comportements.
![](doc\svg\Model!Sequance!patron_state_shape.svg)

#### Implémentation sur le whiteboard
Sur le whiteboard, l'implémentation est plus complexe.
En effet, chaque état implémente les callbacks de la souris.
Ce ne sont donc cette fois-ci plus les méthodes de transition qui sont utilisées pour modifier l'objet mais bien les callbacks.
Cela simplifie les transitions, mais augmente le nombre de callback à implémenter.

![](doc\svg\Model!patron_state_whiteboard.svg)
Le whiteboard dispose de plusieurs états :
- SelectMode
- AddCircle
- AddTriangle
- AddSquare
- MoveMode

Ici les états SelectMode, AddCircle, AddTriangle et AddSquare ont uniquement besoin de gérer le callback du clic de la souris.
Ils héritent donc de la classe abstraite `SimpleClickState` qui implémente les autres callbacks en ne leur donnant aucun comportement.
Le seul état qui doit gérer presque tous les callbacks est MoveMode.
En effet, il doit gérer le déplacement des formes et doit donc implémenter le drag de la souris et doit stocker les coordonnées de la souris avant et après le drag.

Voici le diagram d'état transition qui décrit les différents états et les transitions entre eux :
![](doc\svg\Model!FSM!patron_state_whiteboard_etat_transition.svg)
Comme on l'observe le graphe entre les états est presque complet. Sauf pour le MouveMode qui ne doit être accessible que depuis le SelectMode.


#### Récapitulatif des états
Lorsque l'on clique sur le bouton `Square` par exemple, le whiteboard passe dans l'état `AddSquare`.

Dans l'état `AddSquare`, lorsque l'on clique sur la souris, la commande `AddSquareCommand` est exécutée et ajoutée à l'historique.

Lorsque l'on clique sur le bouton `Select` par exemple, le whiteboard passe dans l'état `SelectMode`.

Dans l'état `SelectMode`, lorsque l'on clique sur la souris, la commande `SelectCommand` est exécutée et ajoutée à l'historique si on a cliqué sur une forme.

La commande `SelectCommand` va passer la forme sélectionnée dans l'état `Selected`.

Lorsque l'event `MouseDragged` est déclenché, le whiteboard passe dans l'état `MoveMode` et la commande `MoveCommand` est exécutée puis ajoutée à l'historique lorsque l'on relache le clique de la souris.

La commande `MoveCommand` va éditer la position des formes sélectionnées.


## Conclusion
Ce projet nous a permis d'expérimenter différents patrons de conception.
Il nous a aussi appris à faire du clean code et à utiliser les tests unitaires pour vérifier le bon fonctionnement de notre code.
Au final ce projet est un bon exemple de ce que l'on peut faire avec les patrons de conception.

