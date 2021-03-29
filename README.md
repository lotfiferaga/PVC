# PVC 

Jouez à trouver le meilleur parcours possible et découvrez différentes méthodes informatiques proposées pour résoudre ce problème.  
Pour un nombre de villes égal à N, le nombre de parcours possibles est égal à (N-1)!/2 = [(N-1)(N-2)(N-3)…1]/2.      
      
Par exemple, pour 5 villes, le nombre de parcours est égal à 12, mais pour 10, il est déjà de 181 440 et pour 20, il est d’environ 60 × 1015. 
Supposons un ordinateur assez rapide pour évaluer un parcours en une demi-microseconde :        
le cas à 5 villes serait résolu en moins de 6 microsecondes, le cas à 10 villes en 0,09 secondes,    
mais il faudrait 964 ans pour résoudre le cas à 20 villes en balayant toutes les solutions possibles.

C’est pourquoi il est indispensable d’élaborer des techniques performantes de résolution pour trouver rapidement la meilleure solution,
ou du moins une solution de bonne qualité.    

# Représentation du problème

Exemple de graphe à 4 sommets. ![alt text](https://user-images.githubusercontent.com/43786211/112902743-7d0bd900-90de-11eb-811a-af94a2e9bb1c.png)

Le problème du voyageur de commerce peut être modélisé à l’aide d’un graphe constitué d’un ensemble de sommets et d’un ensemble d’arêtes. 
Chaque sommet représente une ville, une arête symbolise le passage d’une ville à une autre, et on lui associe un poids pouvant représenter une distance,
un temps de parcours ou encore un coût. Ci-contre, un exemple de graphe à 4 sommets.

Résoudre le problème du voyageur de commerce revient à trouver dans ce graphe un cycle passant par tous les sommets une unique fois (un tel cycle est dit « hamiltonien ») et qui soit de longueur minimale. 
Pour le graphe ci-contre, une solution à ce problème serait le cycle 1, 2, 3, 4 et 1, correspondant à une distance totale de 23. 
Cette solution est optimale, il n’en existe pas de meilleure.

Comme il existe une arête entre chaque paire de sommets, on dit que ce graphe est « complet ». Pour tout graphe, une matrice de poids peut être établie. En lignes figurent les sommets d’origine des arêtes et en colonnes les sommets de destination ; le poids sur chaque arête apparaît à l’intersection de la ligne et de la colonne correspondantes. Pour notre exemple, cette matrice est la suivante :

0	5	8	7      
5	0	6	7      
8	6	0	5     
7	7	5	0      
 

Dans cet exemple, le graphe est non orienté, c’est-à-dire qu’une arête peut être parcourue indifféremment dans les deux sens,
cela explique que la matrice soit symétrique. Cette symétrie n’est pas forcément respectée dans le cas d’un graphe orienté. 
Il existe alors deux catégories de problèmes : 
le cas symétrique (le poids de l’arc du sommet X vers Y est égal au poids de l’arc du sommet Y vers X) 
et le cas asymétrique (le poids de l’arc du sommet X vers Y peut être différent du poids de l’arc du sommet Y vers X).

# Méthodes de résolution
![alt text](https://github.com/dylanswift2018/PVC/blob/master/img/a.PNG?raw=true)
# Méthodes exactes
# Méthodes approchées
