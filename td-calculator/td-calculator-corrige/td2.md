[&larr; Retour](index.html)

# TD2 : Calculateur de frais de port

> Avertissement : entre héritage et polymorphisme, il y a de quoi devenir complètement timbré.

## Contexte

La Poste vient tout juste de mettre à jour sa politique de tarifs appliqués aux colis.
Afin d'informer ses clients, l'entreprise décide de mettre à disposition un calculateur de frais de port.
Jeune diplômé, elle fait appel à vous pour réaliser ce calculateur.

## Cahier des charges

Les frais de ports dépendent du poids et du volume des colis. Un colis qui rentre dans un gabarit de 229mm x 162mm x 25mm coûtera 12€. Jusqu'à 1800g, le client devra payer 17,59€/kg, auquel il faut rajouter des frais fixes de 2,86€. Au dela, le client devra payer la somme la plus élevée entre 21,62€/kg et 1,43€ du décimètre cube. Pour les envois à Monaco, il faudra compter un supplément de 8,7%. Les envois en Europe et hors Europe sont soumis à une autre politique tarifaire.

## Exemples

#### En France métropolitaine

Dimensions (mm x mm x mm) | Poids (kg) | Destination | Tarif (€)
------------ | ------------- | ------------- | -------------
191 x 123 x 18  | 2,354 | FR | 12,00
253 x 215 x 164 | 1,565 | FR | 30,39
653 x 133 x 271 | 2,132 | FR | 46,09
653 x 331 x 271 | 3,650 | FR | 83,76

#### A Monaco

Dimensions (mm x mm x mm) | Poids (kg) | Destination | Tarif (€)
------------ | ------------- | ------------- | -------------
123 x 191 x 18  | 2,354 | MC | 13,04
253 x 215 x 164 | 1,565 | MC | 33,03
653 x 133 x 271 | 2,132 | MC | 50,10
653 x 331 x 271 | 3,650 | MC | 91,05

## Golden Master

> When software reaches the golden master (GM) version, it means the development is complete and the software will soon be publicly available.

Vous pouvez profiter des exemples ci-dessus pour coder le _Golden Master_ de votre calculateur.

Afin d'améliorer la lisibilité de vos tests, vous pouvez profiter des [tests paramétrés avec JUnit](https://github.com/junit-team/junit4/wiki/Parameterized-tests).

```
class Test {
	private static final Object[][] testParameters = new Object[][]{
		{ 191, 123, 18,  2.354d, "FR", "12,00" },
		{ 253, 215, 164, 1.565d, "FR", "30,39" },
		{ 653, 133, 271, 2.132d, "FR", "46,09" },
		{ 653, 331, 271, 3.650d, "FR", "83,76" },
		{ 123, 191, 18,  2.354d, "MC", "13,04" },
		{ 253, 215, 164, 1.565d, "MC", "33,03" },
		{ 653, 133, 271, 2.132d, "MC", "50,10" },
		{ 653, 331, 271, 3.650d, "MC", "91,05" }
	}
}
```

## Développement du calculateur

### Make it work

#### En France métropolitaine

```java
public class Package {
    public Package(int height, int width, int depth, int weight) {
        // ...
    }
    public double calculateLocalShippingCost(){
        // Retourne les frais de port en France
    }
}
```

> Note : Si vous avez des difficultés avec les arrondis des sommes, vous pouvez utiliser la classe [BigDecimal](https://docs.oracle.com/javase/7/docs/api/java/math/BigDecimal.html) à la place de `double`.

Commitez et poussez votre code.

#### En fonction de la destination

```java
public class ShippingCostsCalculator {
    public double calculateShippingCost(Package package, Destination destination){
        // Retourne les frais de port du package en fonction de la destination
    }
}
```

Vous avez le choix sur le type de `Destination` : `class`, `enum` ou `interface` ?

Commitez et poussez votre code.

### Make it OO

#### Package abstraction

La classe `Package` peut avoir différents comportements en fonction de son état interne (dimensions et poids). Or en réalité un colis ne peut pas changer de dimension ou de poids. Il serait donc plus représentatif d'extraire chacun de ces comportements dans des classes différentes. Par exemple : `SmallPackage`, `MediumPackage`, `BigPackage`...

Chaque taille de colis ayant un coût calculé d'une manière différente, il est cependant intéressant que chacun de ces types de colis héritent de la même classe (devenue abstraite) :
```java
public abstract class Package {
    public abstract double calculateLocalShippingCost();
}
```

> Conseil : à chaque étape de votre refactoring, n'oubliez pas de lancer votre Golden Master pour vérifier que vous n'avez pas introduit de régression ! Commitez aussi souvent que possible.

#### Package creation

En vous inspirant du design pattern [Factory](https://refactoring.guru/design-patterns/factory-method), créez la classe `PackageFactory` qui aura pour responsabilité de construire chaque type de package en fonction de ses dimensions et de son poids.

### Et les DOM/TOM alors ?

Pour envoyer des colis en outre-mer, il faudra compter un supplément 5,4% par rapport à la métropole, auxquels seront ajoutés 1,26€ de frais fixes.

Avant d'ajouter cette fonctionnalité, refactorer votre afin que cette évolution ait un impact minimum (en terme de modifications) dans le code.

Commitez et poussez votre code.

Enrichissez votre Golden Master pour prendre en compte cette fonctionnalité, puis ajoutez la fonctionnalité demandée.

Dimensions (mm x mm x mm) | Poids (kg) | Destination | Tarif (€)
------------ | ------------- | ------------- | -------------
191 x 123 x 18  | 2,354 | DOM/TOM | 13,91
253 x 215 x 164 | 1,565 | DOM/TOM | 33,29
653 x 133 x 271 | 2,132 | DOM/TOM | 49,84
653 x 331 x 271 | 3,650 | DOM/TOM | 89,54

Commitez et poussez votre code.

## Développement de l'interface utilisateur

Développez une interface utilisateur pour que votre calculateur soit utilisable en console.

> Rappel : n'oubliez pas que vous pouvez utiliser Mockito pour tester les interactions avec votre calculateur.

---
Thanks to [Matthew Butt](https://twitter.com/bnathyuw) from [Codurance](https://codurance.com/) for his videos that inspired me while crafting this tutorial.

[&larr; Retour](index.html)

INSA de Toulouse
4ème année Informatique et Réseaux
Année universitaire 2017/2018





