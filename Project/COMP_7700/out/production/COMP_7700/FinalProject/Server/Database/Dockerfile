FROM mysql:latest

RUN mkdir /data

COPY users.csv /var/lib/mysql-files/users.csv
COPY items.csv /var/lib/mysql-files/items.csv
COPY lists.csv /var/lib/mysql-files/lists.csv
COPY user_lists.csv /var/lib/mysql-files/user_lists.csv
COPY item_list_pair.csv /var/lib/mysql-files/item_list_pair.csv
COPY init_database.sql /docker-entrypoint-initdb.d/init_database.sql
