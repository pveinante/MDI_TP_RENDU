# TP1 : Principe de conception

## Pattern Visiteur

#### Arbre de syntaxe abstraite de l'expression `Block(Assignment(Variable_Ref("x"),Integer_Value(10)),Print(Variable_Ref("x")))`

![AST](ast.jpg)

#### Définition de Bin_Expression, Integer_Value et Variable_Ref

```
Bin_Expression  := left: Expression; operator: op; right: Expression
Integer_Value   := value: Integer
Variable_Ref    := var_name: String
```

#### Grammaire concrète :

```
Block           := "{" Statement* "}"
Assignment      := var "=" rhs ";"
Conditional     := "if" "(" cond ")" then_part "else" else_part
Print           := "print "  value ";"
Read            := "read " var ";"
While           := "while" "(" cond ")" body
Bin_Expression  := left op right ";"
Integer_value   := value
Variable_ref    := var_name
```


## Pattern Decorator et State

### Question 1

Erreurs détectées :

- Dans la table Personne : l'attribut numéro de téléphone devrait être dans Collaborateur car un client n'a pas a renseigné son numéro
- Dans la table Personne : l'adresse n'a pas à être précisé pour un collaborateur
- Dans la table Commande : il manque le prix de la commande 
- Selon moi, la taille de la pizza ne devrait pas être précisée par un héritage de la table Pizza, mais plutôt par un attribut.
- La liste des moyens de transports n'est pas assez exhaustive
- La table Système manque de méthodes permettant d'affecter un collaborateur à un point pizza ou à une livraison. Elle ne permet pas également de modifier l'ordre de livraison des commandes.

### Question 2

Le patron de conception **Decorator** permet d'ajouter dynamiquement des responsabilités à un objet existant. Cette solution ressemble à l'héritage que nous connaissons déjà, mais elle propose en réalité plus de souplesse que celui-ci.

![Pattern Decorator](pattern_decorator.jpg)

Ici, nous avons plusieurs éléments :

- **Component** : il représente à la fois les objets décorés et les décorateurs
- **ConcreteComponent** : représente un objet décoré ou à décorer
- **Decorator** : l'interface des décorateurs
- **ConcreteDecorator** : les décorateurs

Les décorateurs permettent d'ajouter des fonctionnalités à des objets existants. Par exemple, on peut utiliser ce patron dans la modération de mails en ajoutant une modération (`ModeratorInsulte`) sur les insultes et une autre sur la diffusion d'emails (`ModeratorEmail`). 

Sans la patron de conception Decorator, on aurait un héritage de la forme : `ModeratorEmail` qui hérite de `ModeratorInsulte` qui lui-même hérite de `Moderator`. Cette conception pose des problèmes de compréhension : pourquoi `ModeratorEmail` hériterait-il de `ModeratorInsulte` ? Et surtout, elle poserait des problèmes quand à l'indépendance des fonctionnalités : l'ajout de la fonctionnalité `ModeratorEmail` obligerait à utiliser `ModeratorInsulte`.

Ainsi, le patron Decorator permet l'ajout dynamique de fonctionnalités indépendantes.

Dans notre cas on obtient ce diagram :

![Pattern Decorator](Copie_de_PatternDecorator.png)

L'avantage d'un tel système est d'ajouter des options sans tout redfinir ou faire un héritage en chaine. Dans nos cas, on pourra donc facilement avoir une grande pizza, avec une réduction lié au retard ainsi qu'une réduction lié aux happy hours


### Question 3

Nous avons apporter des modifications afin d'avoir une gestion des état de la pizza sans utiliser la conditionelle java

![Pattern Decorator](Copie_de_PatternDecorator2.png)



Sources : [design pattern decorator](https://blog.elao.com/fr/dev/design-pattern-decorator/)