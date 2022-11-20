# Sneaky Paint
## Introduction
Sneaky Paint et un petit logiciel trés simple qui permet :
- poser des formes géométriques (carré, cercle, triangle)
- grouper des formes
- déplacer des formes
- definir leur elevation
- sauvegarder le dessin dans un fichier
- charger un dessin depuis un fichier

Les buts principaux de ce projet sont de nous faire pratiquer la programmation orientée objet en java en utilisant les patrons de conception.

## Installation
### Prérequis
- Java 18
- Maven 3.8.1

### Procédure d'installation
Pour installer le projet, il suffit de cloner le projet git :
```bash
git clone git@github.com:mrsolarius/SneakyPain.git
```
Puis de lancer 
```bash
mvn clean compile exec:java
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

Lorsqu'un seul goupe est sélectionné, il est possible de le dégroupé en cliquant sur le menu "Edit" puis "Ungroup".

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






