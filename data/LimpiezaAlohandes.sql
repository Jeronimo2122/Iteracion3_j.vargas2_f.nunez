--- Sentencias SQL para la creaci�n del esquema de parranderos
--- Las tablas tienen prefijo A_ para facilitar su acceso desde SQL Developer

-- USO
-- Copie el contenido deseado de este archivo en una pesta�a SQL de SQL Developer
-- Ejec�telo como un script - Utilice el bot�n correspondiente de la pesta�a utilizada
    
-- Eliminar todas las tablas de la base de datos
DROP TABLE PERSONA;
DROP TABLE HOTEL_HOSTAL;
DROP TABLE EDIFICIO_UNIVERSITARIO;
DROP TABLE ALOJAMIENTO_OPERADOR;
DROP TABLE ALOJAMIENTO_SERVICIO;
DROP TABLE HAB_HOSTAL;
DROP TABLE RESERVA;
DROP TABLE HAB_HOTEL;
DROP TABLE CLIENTE;
DROP TABLE OPERADOR;
DROP TABLE SERVICIO;
DROP TABLE ALOJAMIENTO;
COMMIT;

-- Eliminar el contenido de todas las tablas de la base de datos
-- El orden es importante. Por qu�?
delete from PERSONA;
delete from HOTEL_HOSTAL;
delete from EDIFICIO_UNIVERSITARIO;
delete from ALOJAMIENTO_OPERADOR;
delete from ALOJAMIENTO_SERVICIO;
delete from HAB_HOSTAL;
delete from HAB_HOTEL;
delete from RESERVA;
delete from CLIENTE;
delete from OPERADOR;
delete from SERVICIO;
delete from ALOJAMIENTO;
COMMIT;

