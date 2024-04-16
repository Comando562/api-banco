DROP TABLE IF EXISTS Cliente;
CREATE TABLE Cliente
(
    id       BIGINT PRIMARY KEY AUTO_INCREMENT,
    nombre   VARCHAR(255),
    apellido VARCHAR(255),
    tipo    VARCHAR(255)
);