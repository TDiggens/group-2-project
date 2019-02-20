FROM mysql
WORKDIR /tmp
COPY world.sql /tmp/
COPY world.sql /docker-entrypoint-initdb.d
ENV MYSQL_ROOT_PASSWORD example
