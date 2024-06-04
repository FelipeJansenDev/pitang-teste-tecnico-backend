-- Criação da sequência para geração de IDs
CREATE SEQUENCE USUARIO_SEQ START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE CARRO_SEQ START WITH 1 INCREMENT BY 1;

-- Criação da tabela Usuario
CREATE TABLE Usuario (
     id BIGINT PRIMARY KEY,
     firstname VARCHAR(255),
     lastname VARCHAR(255),
     email VARCHAR(255) UNIQUE,
     birthday DATE,
     login VARCHAR(255) UNIQUE,
     password VARCHAR(255),
     phone VARCHAR(255),
     createdat TIMESTAMP,
     lastlogin TIMESTAMP
);

-- Criação da tabela Carro
CREATE TABLE Carro (
    id BIGINT PRIMARY KEY,
    color VARCHAR(255),
    licenseplate VARCHAR(255) UNIQUE,
    model VARCHAR(255),
    usuario_id BIGINT,
    manufactureyear INTEGER,
    FOREIGN KEY (usuario_id) REFERENCES Usuario(id)
);

ALTER TABLE Usuario ALTER COLUMN id SET DEFAULT next value for USUARIO_SEQ;
ALTER TABLE Carro ALTER COLUMN id SET DEFAULT next value for CARRO_SEQ;

-- INSERÇÃO DE USUÁRIOS
INSERT INTO Usuario (firstName, lastName, email, birthday, login, password, phone, createdAt, lastLogin)
VALUES ('John', 'Robert', 'john.doe@example.com', '1990-01-01', 'john', '$2a$12$b8Ubh7aFabxV7ZtNe/aOwOylxknJ40.ml2pyO443No/snPNf2AcxS', '555-1234', '2024-06-03 12:00:00', '2024-06-03 12:00:00');

INSERT INTO Usuario (firstName, lastName, email, birthday, login, password, phone, createdAt, lastLogin)
VALUES ('Jane', 'Emily', 'jane.smith@example.com', '1985-05-15', 'janesmith', '$2a$12$b8Ubh7aFabxV7ZtNe/aOwOylxknJ40.ml2pyO443No/snPNf2AcxS', '555-5678', '2024-06-03 12:10:00', '2024-06-03 12:10:00');

INSERT INTO Usuario (firstName, lastName, email, birthday, login, password, phone, createdAt, lastLogin)
VALUES ('Robert', 'Michael', 'robert.brown@example.com', '1978-08-20', 'robertbrown', '$2a$12$b8Ubh7aFabxV7ZtNe/aOwOylxknJ40.ml2pyO443No/snPNf2AcxS', '555-8765', '2024-06-03 12:20:00', '2024-06-03 12:20:00');

INSERT INTO Usuario (firstName, lastName, email, birthday, login, password, phone, createdAt, lastLogin)
VALUES ('Emily', 'Robert', 'emily.jones@example.com', '1992-11-30', 'emilyjones', '$2a$12$b8Ubh7aFabxV7ZtNe/aOwOylxknJ40.ml2pyO443No/snPNf2AcxS', '555-4321', '2024-06-03 12:30:00', '2024-06-03 12:30:00');

INSERT INTO Usuario (firstName, lastName, email, birthday, login, password, phone, createdAt, lastLogin)
VALUES ('Michael', 'Robert', 'michael.taylor@example.com', '1982-03-10', 'michaeltaylor', '$2a$12$b8Ubh7aFabxV7ZtNe/aOwOylxknJ40.ml2pyO443No/snPNf2AcxS', '555-9876', '2024-06-03 12:40:00', '2024-06-03 12:40:00');

-- INSERÇÃO DE CARROS
INSERT INTO Carro (color, licensePlate, model, usuario_id, manufactureYear) VALUES
                                                                                ('Red', 'ABC1234', 'Model S', 1, 2020),
                                                                                ('Blue', 'DEF5678', 'Model 3', 1, 2021),
                                                                                ('Black', 'GHI9101', 'Model X', 1, 2019),
                                                                                ('White', 'JKL2345', 'Model Y', 1, 2022),
                                                                                ('Silver', 'MNO6789', 'Cybertruck', 1, 2023);

INSERT INTO Carro (color, licensePlate, model, usuario_id, manufactureYear) VALUES
                                                                                ('Green', 'PQR1234', 'Civic', 2, 2018),
                                                                                ('Yellow', 'STU5678', 'Accord', 2, 2019),
                                                                                ('Purple', 'VWX9101', 'CR-V', 2, 2020),
                                                                                ('Orange', 'YZA2345', 'Pilot', 2, 2021),
                                                                                ('Brown', 'BCD6789', 'Fit', 2, 2022);

INSERT INTO Carro (color, licensePlate, model, usuario_id, manufactureYear) VALUES
                                                                                ('Red', 'EFG1234', 'Mustang', 3, 2017),
                                                                                ('Blue', 'HIJ5678', 'Explorer', 3, 2018),
                                                                                ('Black', 'KLM9101', 'F-150', 3, 2019),
                                                                                ('White', 'NOP2345', 'Fusion', 3, 2020),
                                                                                ('Silver', 'QRS6789', 'Escape', 3, 2021);

INSERT INTO Carro (color, licensePlate, model, usuario_id, manufactureYear) VALUES
                                                                                ('Green', 'TUV1234', 'Camry', 4, 2016),
                                                                                ('Yellow', 'WXY5678', 'Corolla', 4, 2017),
                                                                                ('Purple', 'ZAB9101', 'RAV4', 4, 2018),
                                                                                ('Orange', 'CDE2345', 'Highlander', 4, 2019),
                                                                                ('Brown', 'FGH6789', 'Tacoma', 4, 2020);

INSERT INTO Carro (color, licensePlate, model, usuario_id, manufactureYear) VALUES
                                                                                ('Red', 'IJK1234', 'Altima', 5, 2015),
                                                                                ('Blue', 'LMN5678', 'Sentra', 5, 2016),
                                                                                ('Black', 'OPQ9101', 'Maxima', 5, 2017),
                                                                                ('White', 'RST2345', 'Rogue', 5, 2018),
                                                                                ('Silver', 'UVW6789', 'Murano', 5, 2019);
