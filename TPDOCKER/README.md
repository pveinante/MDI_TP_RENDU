# TP 5 - Docker

Le but de ce TP est de se familiariser avec Docker en déployant une application avec la mise en place d'un serveur web en reverse proxy. 

Pour cela, nous avons besoin d'un Dockerfile et d'un fichier docker-compose et d'une application à déployer. 

## Dockerfile

Dans un premier temps, il faut créer l'image docker de l'application et pour cela, on utilise un Dockerfile. Ce fichier va installer des dépendences nécessaire à la création de l'image docker et lancer la commande de création de l'image docker de l'application. 
*Le Dockerfile présent à la racine est le même que celui présent dans l'application dont nous voulons l'image docker.*

## Docker-Compose

Ensuite, il faut mettre en place d'un serveur web en reverse proxy. Pour cela, il faut un fichier docker-compose qui va lancer 5 services : 1 loadbalancer qui est le serveur web Nginx et 4 images de l'application.

Ainsi dans un navigateur, on peut lancer http://localhost/:8080. 