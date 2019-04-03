drop database if exists AsistenteDeComprasMovil;

CREATE DATABASE AsistenteDeComprasMovil;

USE AsistenteDeComprasMovil;

CREATE TABLE Persona(
idPersona INT AUTO_INCREMENT PRIMARY KEY,
nombre VARCHAR(20) NOT NULL,
apellidoPaterno VARCHAR(20) NOT NULL,
apellidoMaterno VARCHAR(20),
rfc VARCHAR(14),
domicilio VARCHAR(100),
telefono VARCHAR(15)
);

CREATE TABLE Usuario(
idUsuario INT AUTO_INCREMENT PRIMARY KEY,
nombreUsuario VARCHAR(20),
contrasenia VARCHAR(12),
rol VARCHAR(20)
);

CREATE TABLE Cliente(
idCliente INT AUTO_INCREMENT PRIMARY KEY,
numeroCliente VARCHAR(20) NOT NULL,
correoElectronico VARCHAR(30),
estatus INT NOT NULL,
idUsuario INT NOT NULL,
idPersona INT NOT NULL,
CONSTRAINT fk_idPersonaC FOREIGN KEY (idPersona) REFERENCES persona (idPersona) ON DELETE CASCADE,
CONSTRAINT fk_idUsuarioC FOREIGN KEY (idUsuario) REFERENCES usuario (idUsuario)	ON DELETE CASCADE
);

CREATE TABLE Almacen(
idAlmacen INT AUTO_INCREMENT PRIMARY KEY,
nombre VARCHAR(50) NOT NULL,
domicilio VARCHAR(200) NOT NULL,
estatus INT NOT NULL
);

CREATE TABLE Vendedor(
idVendedor INT AUTO_INCREMENT PRIMARY KEY,
numeroVendedor VARCHAR(20) NOT NULL, 
fotografiaVendedor LONGTEXT,
reputacion INT DEFAULT 0,
estatus INT NOT NULL,
idUsuario INT NOT NULL,
idPersona INT NOT NULL,
idAlmacen INT NOT NULL,
CONSTRAINT fk_idPersonaV FOREIGN KEY (idPersona) REFERENCES persona (idPersona),
CONSTRAINT fk_idUsuarioV FOREIGN KEY (idUsuario) REFERENCES usuario (idUsuario),
CONSTRAINT fk_idAlmacenV FOREIGN KEY (idAlmacen) REFERENCES almacen (idAlmacen)
);

CREATE TABLE Producto(
idProducto INT AUTO_INCREMENT PRIMARY KEY,
nombre VARCHAR(100) NOT NULL,
marca VARCHAR(50) NOT NULL,
precio FLOAT,
categoria VARCHAR(50) NOT NULL,
estatus INT NOT NULL,  
idAlmacen INT NOT NULL,
CONSTRAINT fk_idAlmacen FOREIGN KEY (idAlmacen) REFERENCES almacen (idAlmacen)
);

CREATE TABLE Proveedor(
idProveedor INT AUTO_INCREMENT PRIMARY KEY,
nombreProveedor VARCHAR(50) NOT NULL,
marca VARCHAR(50) NOT NULL,
correoElectronico VARCHAR(100) NOT NULL,
numeroContacto VARCHAR(15) NOT NULL,
estatus INT NOT NULL
);

CREATE TABLE CompraVenta(
idCompraVenta INT PRIMARY KEY AUTO_INCREMENT,
fechaCompraVenta DATE NOT NULL,
cantidadProductos INT NOT NULL,
importe FLOAT NOT NULL,
facturaExpendida VARCHAR(50),
numeroTicket INT NOT NULL,
estatus INT NOT NULL,
fechaPago date NOT NULL
);

CREATE TABLE DetallesCompra(
idCompra INT NOT NULL,
idProducto INT NOT NULL,
idVendedor INT NOT NULL,
idAlmacen INT NOT NULL,
idProveedor INT NOT NULL,
costoUnitario FLOAT,
CONSTRAINT fk_idCompraC FOREIGN KEY (idCompra) REFERENCES compraventa (idCompraVenta),
CONSTRAINT fk_idProductoC FOREIGN KEY (idProducto) REFERENCES producto (idProducto),
CONSTRAINT fk_idVendedorC FOREIGN KEY (idVendedor) REFERENCES vendedor (idVendedor),
CONSTRAINT fk_idAlmacenC FOREIGN KEY (idAlmacen) REFERENCES almacen (idAlmacen),
CONSTRAINT fk_idProveedorC FOREIGN KEY (idProveedor) REFERENCES proveedor (idProveedor) 
);

CREATE TABLE DetalleVenta(
idVenta INT NOT NULL,
idProducto INT NOT NULL,
idVendedor INT NOT NULL,
idAlmacen INT NOT NULL,
idCliente INT NOT NULL,
costoUnitario FLOAT,
CONSTRAINT fk_idVentaDV FOREIGN KEY (idVenta) REFERENCES compraventa (idCompraVenta),
CONSTRAINT fk_idProductoDV FOREIGN KEY (idProducto) REFERENCES producto (idProducto),
CONSTRAINT fk_idVendedorDV FOREIGN KEY (idVendedor) REFERENCES vendedor (idVendedor),
CONSTRAINT fk_idAlmacenDV FOREIGN KEY (idAlmacen) REFERENCES almacen (idAlmacen),
CONSTRAINT fk_idClienteDV FOREIGN KEY (idCliente) REFERENCES cliente (idCliente)
);

CREATE TABLE Factura(
idFactura INT PRIMARY KEY AUTO_INCREMENT,
idCliente INT NOT NULL,
idVendedor INT NOT NULL,
idCompra INT NOT NULL,
iva FLOAT,
CONSTRAINT fk_idClienteF FOREIGN KEY (idCliente) REFERENCES cliente (idCliente),
CONSTRAINT fk_idVendedorF FOREIGN KEY (idVendedor) REFERENCES vendedor (idVendedor),
CONSTRAINT fk_idCompraF FOREIGN KEY (idCompra) REFERENCES compraventa (idCompraVenta)
);

DELIMITER //

CREATE PROCEDURE registroCliente (
									/*---- Datos Entrada Persona ----*/
									IN var_nombre VARCHAR(20), 				-- 1
                                    IN var_apellidoPaterno VARCHAR(20),		-- 2
                                    IN var_apellidoMaterno VARCHAR(20),		-- 3
                                    IN var_rfc VARCHAR(14),					-- 4
                                    IN var_domicilio VARCHAR(100),			-- 5
                                    IN var_telefono VARCHAR(15),			-- 6
                                    
                                    /*---- Datos Entrada Usuario ----*/
                                    IN var_nombreUsuario VARCHAR(20),		-- 7
                                    IN var_contrasenia VARCHAR(12),			-- 8
                                    
                                    /*---- Datos Entrada Cliente ----*/
                                    IN var_correoElectronico VARCHAR(30),	-- 9
                                    
                                    /*---- Datos Salida Persona ----*/
                                    OUT var_idPersona INT,					-- 10
                                    
                                    /*---- Datos Salida Usuario ----*/
                                    OUT var_idUsuario INT,					-- 11
                                    
                                    /*---- Datos Salida Cliente ----*/
                                    OUT var_idCliente INT,					-- 12
                                    OUT var_numeroCliente VARCHAR(20)		-- 13
									)

BEGIN
	
    INSERT INTO persona (nombre, apellidoPaterno, apellidoMaterno, rfc, domicilio, telefono) VALUES
    (var_nombre, var_apellidoPaterno, var_apellidoMaterno, var_rfc, var_domicilio, var_telefono);
    
    SET var_idPersona = LAST_INSERT_ID();
    
    INSERT INTO usuario (rol, nombreUsuario, contrasenia) VALUES
    ('Cliente', var_nombreUsuario, var_contrasenia);
    
    SET var_idUsuario = LAST_INSERT_ID();
    
    SET var_numeroCliente = CONCAT(SUBSTRING(var_rfc, 1, 4), CAST(UNIX_timestamp()AS CHAR));
    
    INSERT INTO cliente (numeroCliente, correoElectronico, estatus, idUsuario, idPersona) VALUES
    (var_numeroCliente, var_correoElectronico, 1, var_idUsuario, var_idPersona);
    
END //

CREATE PROCEDURE registroVendedor (
									/*---- Datos Entrada Persona ----*/
									IN var_nombre VARCHAR(20), 
                                    IN var_apellidoPaterno VARCHAR(20),
                                    IN var_apellidoMaterno VARCHAR(20),
                                    IN var_rfc VARCHAR(14),
                                    IN var_domicilio VARCHAR(100),
                                    IN var_telefono VARCHAR(15),
                                    
                                    /*---- Datos Entrada Usuario ----*/
                                    IN var_nombreUsuario VARCHAR(20),
                                    IN var_contrasenia VARCHAR(12),
                                    IN var_rol VARCHAR(20),
                                    
                                    /*---- Datos Entrada Vendedor ----*/
                                    IN var_fotografiaVendedor LONGTEXT,
                                    IN var_reputacion INT,
                                    IN var_idAlmacen INT,
                                    
                                    /*---- Datos Salida Persona ----*/
                                    OUT var_idPersona INT,
                                    
                                    /*---- Datos Salida Usuario ----*/
                                    OUT var_idUsuario INT,
                                    
                                    /*---- Datos Salida Vendedor ----*/
                                    OUT var_idVendedor INT,
                                    OUT var_numeroVendedor VARCHAR(20)
									)

BEGIN
	
    INSERT INTO persona (nombre, apellidoPaterno, apellidoMaterno, rfc, domicilio, telefono) VALUES
    (var_nombre, var_apellidoPaterno, var_apellidoMaterno, var_rfc, var_domicilio, var_telefono);
    
    SET var_idPersona = LAST_INSERT_ID();
    
    INSERT INTO usuario (rol, nombreUsuario, contrasenia) VALUES
    (var_rol, var_nombreUsuario, var_contrasenia);
    
    SET var_idUsuario = LAST_INSERT_ID();
    
    SET var_numeroVendedor = CONCAT('E', SUBSTRING(var_rfc, 1, 4), CAST(UNIX_timestamp()AS CHAR));
    
    INSERT INTO vendedor (numeroVendedor, fotografiaVendedor, estatus, reputacion, idUsuario, idPersona, idAlmacen) VALUES
    (var_numeroVendedor, var_fotografiaVendedor, 1, var_reputacion, var_idUsuario, var_idPersona, var_idAlmacen);
    
END //


CREATE PROCEDURE actualizarCliente (
									/*---- Datos Entrada Persona ----*/
                                    IN var_idPersona INT,					-- 1
									IN var_nombre VARCHAR(20), 				-- 2
                                    IN var_apellidoPaterno VARCHAR(20),		-- 3
                                    IN var_apellidoMaterno VARCHAR(20),		-- 4
                                    IN var_rfc VARCHAR(14),					-- 5
                                    IN var_domicilio VARCHAR(100),			-- 6
                                    IN var_telefono VARCHAR(15),			-- 7
                                    
                                    /*---- Datos Entrada Usuario ----*/
                                    IN var_idUsuario INT,					-- 8
                                    IN var_nombreUsuario VARCHAR(20),		-- 9
                                    IN var_contrasenia VARCHAR(12),			-- 10
                                    
                                    /*---- Datos Entrada Cliente ----*/
                                    IN var_idCliente INT,					-- 11
                                    IN var_correoElectronico VARCHAR(30),	-- 12
                                    IN var_estatus INT
									)

BEGIN
	
    UPDATE persona SET nombre = var_nombre, apellidoPaterno = var_apellidoPaterno, 
	apellidoMaterno = var_apellidoMaterno, rfc = var_rfc, domicilio = var_domicilio,
    telefono = var_telefono WHERE idPersona = var_idPersona;
    
    UPDATE usuario SET rol = var_rol, nombreUsuario = var_nombreUsuario, contrasenia = var_contrasenia
    WHERE idUsuario = var_idUsuario;
    
    UPDATE cliente SET numeroCliente = var_numeroCliente, correoElectronico = var_correo, estatus = var_estatus,
    idUsuario = var_idUsuario, idPersona = var_idPersona WHERE idCliente = var_idCliente;
    
END//

CREATE PROCEDURE actualizarVendedor (
									/*---- Datos Entrada Persona ----*/
                                    IN var_idPersona INT,
									IN var_nombre VARCHAR(20), 
                                    IN var_apellidoPaterno VARCHAR(20),
                                    IN var_apellidoMaterno VARCHAR(20),
                                    IN var_rfc VARCHAR(14),
                                    IN var_domicilio VARCHAR(100),
                                    IN var_telefono VARCHAR(15),
                                    
                                    /*---- Datos Entrada Usuario ----*/
                                    IN var_idUsuario INT,
                                    IN var_puesto VARCHAR(20),
                                    IN var_nombreUsuario VARCHAR(20),
                                    IN var_contrasenia VARCHAR(12),	
                                    IN var_rol VARCHAR(20),
                                    
                                    /*---- Datos Entrada Vendedor ----*/
                                    IN var_idVendedor INT,
                                    IN var_fotografiaVendedor LONGTEXT,
                                    IN var_reputacion INT,
                                    IN var_idAlmacen INT
									)

BEGIN
	
    UPDATE persona SET nombre = var_nombre, apellidoPaterno = var_apellidoPaterno, 
	apellidoMaterno = var_apellidoMaterno, rfc = var_rfc, domicilio = var_domicilio,
    telefono = var_telefono WHERE idPersona = var_idPersona;
    
    UPDATE usuario SET rol = var_rol, nombreUsuario = var_nombreUsuario, contrasenia = var_contrasenia
    WHERE idUsuario = var_idUsuario;
    
    UPDATE vendedor SET numeroVendedor = var_numeroVendedor, fotografiaVendedor = var_fotografiaVendedor,
    reputacion= var_reputacion, estatus = var_estatus WHERE idVendedor = var_idVendedor;
    
END //

DELIMITER ;

INSERT INTO Almacen (nombre, domicilio, estatus) VALUES
('Mandarinas', '37417, Amoles 119, Hacienda las Mandarinas, 37417 León, Gto.', '1'),
('Centro Max', 'Blvd. Adolfo López Mateos Nº 5218 Ote., Local 48, Centro Max León, Centro Comercial, 37530 León, Gto.', '1'),
('Plaza Mayor', 'Centro Comercial Plaza Mayor, Blvd. Juan Alonso de Torres Pte. 2002, Valle del Campestre, 37150 León, Gto.', '1');

INSERT INTO Producto (nombre, categoria, precio, marca, estatus, idAlmacen) VALUES
(' Nestlé La Lechera light 397 g', 'La Lechera', 21.50, 'Lactéo', 1, 1), 
('Leche Lala deslactosada 1 l', 'Lala', 21.50, 'Lactéo', 1, 2), 
('Leche evaporada Carnation Clavel 720 g', 'Lactéo', 22.00, 'Carnation', 1, 3), 
('Leche fresca Lala entera pasteurizada 1 l', 'Lactéo', 19.50, 'Lala', 1, 3), 
('Leche fresca Lala entera pasteurizada 3.78 l', 'Lactéo', 64.00, 'Lala', 1, 2), 
('Nutri Leche 1 l', 'Lactéo', 16.00, 'NutriLeche', 1, 1),
('Yoghurt Yoplait con fresas 1 kg', 'Lactéo', 32.60, 'Yoplait', 1, 2),
('Sabritas original con sal tamaño max 67 g', 'Botana', 14.90, 'Sabritas', 1, 3), 
('Papas Chips original 170 g', 'Botana', 31.50, 'Barcel', 1, 3), 
('Chocolate Hersheys Milk 40 G', 'Dulces', 14.90, 'Hersheys', 1, 2), 
('Chocolate con leche Milky Way relleno de caramelo y nougat 52.2 g', 'Dulces', 15.50, 'Milky Way', 1, 1), 
('Cerveza oscura Victoria 1 botella de 1.2 l', 'Bebidas Alcohólicas', 33.00, 'Victoria', 1, 1), 
('Cerveza clara Coronita extra 12 botellas de 210 ml c/u', 'Bebidas Alcohólicas', 110.00, 'Corona', 1, 2), 
('Cerveza clara Heineken 6 botellas de 355 ml c/u', 'Bebidas Alcohólicas', 99.00, 'Heineken', 1, 3),  
('Refresco Pepsi botella de 1.5 l', 'Refresco', 19.00, 'Pepsi', 1, 3),
('Refresco Coca Cola botella de 2.5 l', 'Refresco', 33, 'Coca Cola', 1, 1);

CALL registroCliente('Vanessa', 'Ortega', 'Torres', 'OETJ981207', 'Via asinaria #323-A, Col. Villamagna', '4775595234',
					'van1207', '1111', 'vanessato_98@hotmail.com', @idPersona, @idUsuario, @idCliente, @numeroCliente);
                    
CALL registroVendedor('Diego', 'Castro', 'Castro', 'CACD990225', 'Imperio Asiático #615', '4113603464',
					  'Vendedor', 'dieg0225','2222', 'Vendedor', null, null, 1, @idPersona, @idUsuario, @idVendedor, @numeroVendedor);
