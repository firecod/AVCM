USE asistentedecomprasmovil;

DROP VIEW IF EXISTS vistaproducto;
CREATE VIEW vistaproducto AS
	SELECT P.idProducto,
		   P.nombre AS 'nombreProducto',
		   P.marca,		
           P.precio,
           P.categoria,
           P.estatus,
           P.idAlmacen,
           A.nombre AS 'nombreAlmacen'
	FROM producto P
    INNER JOIN almacen A 
    ON P.idAlmacen = A.idAlmacen;
    
    SELECT * FROM vistaproducto;