INSERT INTO clientes (direccion, document_id, nombre) VALUES ('cra x 45 63', '123456', 'Felipe Castro');
INSERT INTO clientes (direccion, document_id, nombre) VALUES ('cra z 43 61', '123456', 'Andres Molina');
INSERT INTO clientes (direccion, document_id, nombre) VALUES ('cra y 40 59', '123456', 'Carlos Andrade');

INSERT INTO productos (nombre, precio) VALUES('Lapiz','2000');
INSERT INTO productos (nombre, precio) VALUES('Cuaderno','3000');
INSERT INTO productos (nombre, precio) VALUES('Mochila','30000');
INSERT INTO productos (nombre, precio) VALUES('Regla','7000');
INSERT INTO productos (nombre, precio) VALUES('Compas','12000');
INSERT INTO productos (nombre, precio) VALUES('Libros','50000');
INSERT INTO productos (nombre, precio) VALUES('Lonchera','14000');


INSERT INTO pedidos (fecha_creacion, observacion, cliente_id, estado) VALUES('2021-04-06 04:17:35', 'Lista escolar',  1, 'Recibido');

INSERT INTO pedido_detalles (cantidad, producto_id, pedido_id) VALUES(10, 1, 1);
INSERT INTO pedido_detalles (cantidad, producto_id, pedido_id) VALUES(20, 4, 1);
INSERT INTO pedido_detalles (cantidad, producto_id, pedido_id) VALUES(50, 5, 1);
INSERT INTO pedido_detalles (cantidad, producto_id, pedido_id) VALUES(5, 7, 1);