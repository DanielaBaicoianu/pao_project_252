-- Create tables for magazin_muzical database

CREATE TABLE IF NOT EXISTS clienti (
    id SERIAL PRIMARY KEY,
    nume VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    numar_comenzi INTEGER DEFAULT 0,
    categorie VARCHAR(20) DEFAULT 'BASIC'
);

CREATE TABLE IF NOT EXISTS produse (
    id SERIAL PRIMARY KEY,
    nume VARCHAR(100) NOT NULL,
    pret DECIMAL(10,2) NOT NULL,
    tip_produs VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS comenzi (
    id SERIAL PRIMARY KEY,
    client_id INTEGER NOT NULL,
    data_comanda TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(20) DEFAULT 'NEPROCESAT',
    FOREIGN KEY (client_id) REFERENCES clienti(id)
);

CREATE TABLE IF NOT EXISTS comanda_produse (
    comanda_id INTEGER NOT NULL,
    produs_id INTEGER NOT NULL,
    cantitate INTEGER NOT NULL,
    pret_unitar DECIMAL(10,2) NOT NULL,
    PRIMARY KEY (comanda_id, produs_id),
    FOREIGN KEY (comanda_id) REFERENCES comenzi(id),
    FOREIGN KEY (produs_id) REFERENCES produse(id)
);

CREATE TABLE IF NOT EXISTS furnizori (
    id SERIAL PRIMARY KEY,
    nume VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    telefon VARCHAR(20) NOT NULL,
    adresa TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS facturi (
    id SERIAL PRIMARY KEY,
    numar_factura VARCHAR(50) NOT NULL UNIQUE,
    data_emitere TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    client_id INTEGER NOT NULL,
    total DECIMAL(10,2) NOT NULL,
    status VARCHAR(20) DEFAULT 'NEPLATITA',
    FOREIGN KEY (client_id) REFERENCES clienti(id)
);

CREATE TABLE IF NOT EXISTS factura_produse (
    factura_id INTEGER NOT NULL,
    produs_id INTEGER NOT NULL,
    cantitate INTEGER NOT NULL,
    pret_unitar DECIMAL(10,2) NOT NULL,
    PRIMARY KEY (factura_id, produs_id),
    FOREIGN KEY (factura_id) REFERENCES facturi(id),
    FOREIGN KEY (produs_id) REFERENCES produse(id)
);

CREATE TABLE IF NOT EXISTS recenzii (
    id SERIAL PRIMARY KEY,
    client_id INTEGER NOT NULL,
    produs_id INTEGER NOT NULL,
    rating INTEGER NOT NULL CHECK (rating BETWEEN 1 AND 5),
    comentariu TEXT,
    data_creare TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (client_id) REFERENCES clienti(id),
    FOREIGN KEY (produs_id) REFERENCES produse(id)
); 