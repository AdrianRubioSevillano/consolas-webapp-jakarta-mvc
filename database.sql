-- ===========================================================================
-- NUEVO PROYECTO - BASE DE DATOS DE CONSOLAS RETRO
-- Estructura de tabla única con restricciones estrictas
-- IDs autogenerados como BIGINT mediante GENERATED ALWAYS AS IDENTITY
-- Uso de DATE para fechas y CHECK IN para emular enumeraciones
-- ===========================================================================

DROP TABLE IF EXISTS consolas;

CREATE TABLE CONSOLAS
(
    ID                  BIGINT GENERATED ALWAYS AS IDENTITY,
    NOMBRE              VARCHAR(100),
    FABRICANTE          VARCHAR(50),
    FECHA_LANZAMIENTO   DATE,
    FOTO                VARCHAR(500),

    CONSTRAINT "PK_CONSOLAS"                  PRIMARY KEY (ID),
    CONSTRAINT "NN_CONSOLAS.NOMBRE"            CHECK (NOMBRE IS NOT NULL),
    CONSTRAINT "NN_CONSOLAS.FABRICANTE"        CHECK (FABRICANTE IS NOT NULL),
    CONSTRAINT "NN_CONSOLAS.FECHA_LANZAMIENTO" CHECK (FECHA_LANZAMIENTO IS NOT NULL),
    CONSTRAINT "NN_CONSOLAS.FOTO"              CHECK (FOTO IS NOT NULL),

    -- Restricción de Enumeración (Emula un Enum de Java)
    CONSTRAINT "CH_CONSOLAS.FABRICANTE_ENUM"   CHECK (FABRICANTE IN ('NINTENDO', 'SONY', 'SEGA', 'MICROSOFT', 'ATARI'))
);

-- ===========================================================================
-- INSERCIÓN DE CONSOLAS HISTÓRICAS
-- Las fechas están en formato YYYY-MM-DD (ISO 8601 estándar de bases de datos)
-- ===========================================================================

INSERT INTO CONSOLAS(NOMBRE, FABRICANTE, FECHA_LANZAMIENTO, FOTO)
VALUES ('Nintendo Entertainment System (NES)', 'NINTENDO', '1983-07-15', 'https://www.backmarket.es/cdn-cgi/image/format%3Dauto%2Cquality%3D75%2Cwidth%3D1080/https://d2e6ccujb3mkqf.cloudfront.net/06b9fe6a-a4b1-4eae-838b-eda464d8ed08-1_9897c925-8c8d-4e5a-8fb4-71444b1cfe47.jpg');

INSERT INTO CONSOLAS(NOMBRE, FABRICANTE, FECHA_LANZAMIENTO, FOTO)
VALUES ('Super Nintendo (SNES)', 'NINTENDO', '1990-11-21', 'https://www.backmarket.es/cdn-cgi/image/format%3Dauto%2Cquality%3D75%2Cwidth%3D1080/https://d2e6ccujb3mkqf.cloudfront.net/6c2f47fc-9117-407e-bf93-e282910859d2-1_c0e94d0c-ca62-4583-bba4-fa6e93f3641a.jpg');

INSERT INTO CONSOLAS(NOMBRE, FABRICANTE, FECHA_LANZAMIENTO, FOTO)
VALUES ('PlayStation 1', 'SONY', '1994-12-03', 'https://www.backmarket.es/cdn-cgi/image/format%3Dauto%2Cquality%3D75%2Cwidth%3D1080/https://d2e6ccujb3mkqf.cloudfront.net/998d41a6-5401-4761-a464-80b6ec4bb3a0-1_f4097489-8aa2-4c38-a548-24d9b90262ab.jpg');

INSERT INTO CONSOLAS(NOMBRE, FABRICANTE, FECHA_LANZAMIENTO, FOTO)
VALUES ('Sega Mega Drive', 'SEGA', '1988-10-29', 'https://m.media-amazon.com/images/I/61jG4uJjuJL.jpg');

INSERT INTO CONSOLAS(NOMBRE, FABRICANTE, FECHA_LANZAMIENTO, FOTO)
VALUES ('Xbox Original', 'MICROSOFT', '2001-11-15', 'https://upload.wikimedia.org/wikipedia/commons/4/43/Xbox-console.jpg?utm_source=ca.wikipedia.org&utm_campaign=index&utm_content=original');

COMMIT;