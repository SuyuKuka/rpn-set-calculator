# Projet CPOO

## Manuel d'utilisation

1) Compiler le programme:  
`javac -d bin src/main/java/**/**/*.java src/main/java/**/*.java src/main/java/*.java`

2) Lancer le programme:  
`java -cp bin Calculator`

3) Lancer la partie 4 & 6 (Calcul sur les ensembles & Au delà des ensembles):  
`run 1 set`  
`run 1 list`

4) Lancer la partie 5 (Calculatrice symbolique):  
`run 2`

*Tapez help pour plus d'information*

## Choix techniques

### Fonctionnalités implémentées
- Calcul sur les ensembles (Constante, Union, Intersection, Somme de Minkowski, min, max)
- Calculatrice symbolique (Constante, Union, Intersection, Somme de Minkowski, min, max, UC, DC)
- Au delà des ensembles (Set, List)

### Structure du code

```
📁 projet-cpoo/
├── 📄 README.md
│
├── 📄 Sujet doral de session 2.pdf
│
└── 📁 src/
    └── 📁 main/
        └── 📁 java/
            ├── 📁 set_calculator/
            │   └── 📁 operations/
            │
            └── 📁 symbolic_calculator/
                ├── 📁 formulas/
                │
                ├── 📁 memento/
                │
                └── 📁 operations/
```

### Modélisation

1. **Modélisation de la base (Calculator.java)**

Le main du programme dans lequel il est possible de choisir deux modes de calculatrice (ensembles/listes ou symbolique)

2. **Modélisation de la Calculatrice RPN sur Ensembles/Listes (set_calculator/)**  

Elle implémente la logique d'une calculatrice RPN. Elle fonctionne sur une pile de collections d'entiers (Set ou List dans notre cas). Chaque opération gère la manipulation de ces collections.

3. **Modélisation de la Calculatrice Symbolique (symbolic_calculator/)**

Elle gère la calculatrice symbolique. Elle manipule des Formula qui représentent des expressions d'ensembles. Chaque opération symbolique (FormulaOperation) construit ou modifie ces formules sur une pile. Ce qui permet d'avoir la formule et l'évaluation de celle ci. Les ensembles infini ou non défini sont aussi gerer. De plus, il est possible d'annuler la dernière opération (HistoryOperation.java)

### Patrons de conceptions
- **Composite :** Permet d'agencer les objets dans des arborescences afin de pouvoir traiter celles-ci comme des objets individuels `(formulas/*.java)`
- **Stratégie :** Permet de définir une famille d’algorithmes, de les mettre dans des classes séparées et de rendre leurs objets interchangeables `(operations/*.java)`
- **Command :** Prend une action à effectuer et la transforme en un objet autonome qui contient tous les détails de cette action. Cette transformation permet de paramétrer des méthodes avec différentes actions, planifier leur exécution, les mettre dans une file d’attente ou d’annuler des opérations effectuées `(RPNCalculator.java et SymbolicCalculator.java)`
- **Fabrique abstraite :** Permet de créer des familles d’objets apparentés sans préciser leur classe concrète `(Calculator.java)`
- **Mémento :** Permet de sauvegarder et de rétablir l'état précédent d’un objet sans révéler les détails de son implémentation `(HistoryOperation.java)`
- **Façade :** Procure une interface qui offre un accès simplifié à une librairie, un framework ou à n’importe quel ensemble complexe de classes `(Calculator.java (avec run 1/2))`

## Membres
|  Nom  | Prénom | N° étudiant |
| :---: | :----: | :---------: |
| CHEN  | Michel |  22202655   |