-- init SQL
CREATE SCHEMA IF NOT EXISTS app;
USE app;

CREATE TABLE IF NOT EXISTS cat
(
    id          bigint       NOT NULL AUTO_INCREMENT PRIMARY KEY,
    cat_id      varchar(255) NOT NULL UNIQUE,
    url         varchar(500) NOT NULL,
    width       int          NOT NULL,
    height      int          NOT NULL,
    breed_id    varchar(255) NULL,
    breed_name  varchar(255) NULL,
    temperament varchar(255) NULL,
    origin      varchar(255) NULL
);
