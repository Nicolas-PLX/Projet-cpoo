# Compilation

Pour compiler et exécuter le projet, il suffit normalement d'éxécuter gradle, en faisant :
```./gradlew run```

# Test

Il n'y a pas de programme de tests malheureusement, nous avons eu quelque problème
concernant les tests, nous avons donc préféré laissez tomber et priorisé le 
projet plutôt que les tests.

# Fonctionnalités implémentées

Le mode normal et le mode jeu sont implémenté. Pour le mode normal, il manque
la surbrillance des erreurs, mais par fautes de temps, nous ne l'avons pas fait.
Pour le mode jeu, il manque la couleur des mots pour récupérer ses points de vies.
Le mode multijoueur n'a pas été implémenté également malheureusement. A part cela,
Le jeu est jouable dans les 2 modes, en affichant les statistiques à la fin de la 
partie.

Pour l'interface graphique, nous avons utiliser l'outil SceneBuilder, qui n'utilise
pas le principe de croisement View/Controler/Modele directement. En effet, 
Le Controler n'intéragit qu'avec le Model dans un seul sens, et le Controler agit
sur la vue en appelant des fonctions statique présent dans la vue.

Pour l'utilisation du chronomètre, ExecutorTimer utilise la concurrence, en utilisant
ScheduledExecutorService. Il était probablement possible de l'utiliser sans concurrence
(notamment juste en utilisant un TimerTask), mais pour toucher un peu à tout, nous
avons décidé d'utilisé la concurrence (cependant, qui est très rudimentaire, nous 
avons vu bien plus en cours notamment). De plus, nous avions commencé à faire 
un chronomètre avec TimerTask, mais il y avait eu quelques problèmes. Après coup, nous
pensons que c'était juste une erreur de code, mais finalement, nous sommes rester
sur le ScheduledExecutorService.

Le générateur de mot utilise un tableau de String. Nous aurions pu utiliser n'importe quelle
autre fonctionnalité de "stockage", notamment les Stacks, il aurait été certainement
plus pratique, mais nous avons garder la même idée (pas forcément la meilleur ...)

Nous avons malheureusement peu utiliser de patron de conception. Il était des fois
pas nécessaire d'en utiliser, nous en avons utilisé un cependant pour la classe Game,
qui utilise un Builder pour être créer.

Nous avons utilisé une classe Game qui permet de tout regrouper (Joueur, Statistique,
Tampon, mécanique de jeu lié aux mots ...), puis nous l'avons mis dans un Modele qui
est utilisé par le Controleur directement. Nous avons préféré procédé ainsi car cela
était plus pratique. A la base, nous voulions faire de la classe Game un modele directement,
mais nous avons changé d'idée pour une question pratique et de propreté.

Nous voulions ajouter d'autre fonctionnalité, notamment le fait que le joueur puisse
choisir les mots sur lesquelles il voulait joué, ou pouvoir changer de langue.
Malheureusement, encore une fois par faute de temps, nous n'avons pas pu le faire,
surtout que certaine d'entre elles sont déjà faites (il "suffit" que le joueur puisse
écrire quelque part les mots qu'il veut, on la récupère et la générateur de mot 
peut changer de String par celle que l'on lui a donné).

# Conclusion

Malheureusement, comme vous pouvez le remarquer, le projet n'est pas fini. Cela
nous chagrine, surtout que le sujet était intéressant, et nous permettait de traiter
des cas encore jamais vu pour nous, simple étudiant sans expérience. Nous avons été
pris de cours par les autres projets, très demandant niveau temps, par les examens,
par les fêtes ... 
En esperant que le jeu vous a plus un minimum.


