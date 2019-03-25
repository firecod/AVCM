drop database if exists AsistenteDeComprasMovil;
CREATE DATABASE AsistenteDeComprasMovil;

USE AsistenteDeComprasMovil;
CREATE TABLE Almacen(
idAlmacen INT AUTO_INCREMENT PRIMARY KEY,
nombre VARCHAR(50) NOT NULL,
domicilio VARCHAR(100) NOT NULL,
estatus INT NOT NULL
);

CREATE TABLE Persona(
idPersona INT AUTO_INCREMENT PRIMARY KEY,
nombre VARCHAR(20) NOT NULL,
apellidoPaterno VARCHAR(20) NOT NULL,
apellidoMaterno VARCHAR(20),
rfc VARCHAR(14),
domicilio VARCHAR(50),
telefono VARCHAR(15)
);


CREATE TABLE Usuario(
idUsuario INT AUTO_INCREMENT PRIMARY KEY, 
puesto VARCHAR(20),
nombreUsuario VARCHAR(100),
contrasenia VARCHAR(12)
);

CREATE TABLE Cliente(
idCliente INT AUTO_INCREMENT PRIMARY KEY,
numeroCliente VARCHAR(10) NOT NULL,
correoElectronico VARCHAR(30),
estatus INT NOT NULL,
idUsuario INT NOT NULL,
idPersona INT NOT NULL,
CONSTRAINT fk_idPersonaC FOREIGN KEY (idPersona) REFERENCES persona (idPersona),
CONSTRAINT fk_idUsuarioC FOREIGN KEY (idUsuario) REFERENCES usuario (idUsuario)	
);



CREATE TABLE Vendedor(
idVendedor INT AUTO_INCREMENT PRIMARY KEY,
numeroVendedor VARCHAR(10) NOT NULL, 
fotografíaVendedor LONGTEXT,
reputación INT NOT NULL,
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
nombre VARCHAR(50) NOT NULL,
marca VARCHAR(50) NOT NULL,
precio Float,
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

CREATE TABLE Compra(
idCompra INT PRIMARY KEY AUTO_INCREMENT,
fechaCompra DATE NOT NULL,
importe FLOAT NOT NULL,
estatus INT NOT NULL,
fechaPago date NOT NULL
);

CREATE TABLE DetallesCompra(
idCompra INT NOT NULL,
idProducto INT NOT NULL,
idVendedor INT NOT NULL,
idAlmacen INT NOT NULL,
idProveedor INT NOT NULL,
CONSTRAINT fk_idCompraC FOREIGN KEY (idCompra) REFERENCES compra (idCompra),
CONSTRAINT fk_idProductoC FOREIGN KEY (idProducto) REFERENCES producto (idProducto),
CONSTRAINT fk_idVendedorC FOREIGN KEY (idVendedor) REFERENCES vendedor (idVendedor),
CONSTRAINT fk_idAlmacenC FOREIGN KEY (idAlmacen) REFERENCES almacen (idAlmacen),
CONSTRAINT fk_idProveedorC FOREIGN KEY (idProveedor) REFERENCES proveedor (idProveedor) 
);

CREATE TABLE Venta(
idVenta INT PRIMARY KEY AUTO_INCREMENT,
fechaVenta DATE NOT NULL,
importe FLOAT NOT NULL,
facturaExpendida VARCHAR(50),
numeroTicket INT NOT NULL
);

CREATE TABLE DetalleVenta(
idVenta INT NOT NULL,
idProducto INT NOT NULL,
idVendedor INT NOT NULL,
idAlmacen INT NOT NULL,
idCliente INT NOT NULL,
CONSTRAINT fk_idVentaDV FOREIGN KEY (idVenta) REFERENCES venta (idVenta),
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
CONSTRAINT fk_idCompraF FOREIGN KEY (idCompra) REFERENCES compra (idCompra)
);