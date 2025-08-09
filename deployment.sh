#!/bin/bash
# filepath: deploy.sh

# Définir les répertoires
bin_dir="bin"
web_dir="web"
lib_dir="lib"
conf_dir="config"

target_name="SkyReserve"
target_dir="/usr/local/tomcat/webapps"  # Chemin typique pour Tomcat dans un conteneur Docker

# Nettoyer le répertoire temporaire s'il existe
rm -rf temp

# Créer un nouveau répertoire temporaire
mkdir -p temp/WEB-INF/lib
mkdir -p temp/WEB-INF/classes

# Copier les fichiers
cp -r $web_dir/* temp/
cp -r $lib_dir/* temp/WEB-INF/lib/
cp -r $bin_dir/* temp/WEB-INF/classes/
cp -r $conf_dir/* temp/WEB-INF/

# Créer l'archive WAR
jar -cvf $target_name.war -C temp .

# Copier l'archive vers le conteneur Docker
# Supposons que votre conteneur s'appelle 'tomcat'
sudo docker cp $target_name.war tomcat:$target_dir

# Nettoyer
rm -f $target_name.war
rm -rf temp

echo "Déploiement terminé: $target_name.war a été copié vers le conteneur Docker Tomcat"