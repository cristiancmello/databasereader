docker run --rm --detach -p "3306:3306" --name mariadb -e MARIADB_ROOT_PASSWORD=example -e MARIADB_DATABASE=exampledb mariadb:11.2.2