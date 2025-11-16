use logitrack_db;

create table IF NOT EXISTS usuario (
	id bigint auto_increment primary key,
	nombre varchar(100) not null,
	email varchar(200) not null unique,
	password_hash varchar(300) not null,
	rol enum('ADMIN', 'EMPLEADO') not null,
	activo boolean default true,
	created_at datetime default CURRENT_TIMESTAMP()
);

create table IF NOT EXISTS bodega (
	id bigint auto_increment primary key,
	nombre varchar(100) not null,
	ubicacion varchar(255) not null,
	capacidad int not null,
	encargado_id bigint not null,
	activo boolean default true,
	created_at datetime default CURRENT_TIMESTAMP(),
	updated_at datetime default CURRENT_TIMESTAMP() on update current_timestamp(),
	foreign key(encargado_id) references usuario(id)
);

create table IF NOT EXISTS producto (
	id bigint auto_increment primary key,
	nombre varchar(100) not null,
	categoria varchar(50) not null,
	precio decimal(10,2) not null,
	activo boolean default true,
	created_at datetime default CURRENT_TIMESTAMP(),
	update_at datetime default CURRENT_TIMESTAMP() on update current_timestamp()
);

create table IF NOT EXISTS inventario (
	id bigint auto_increment primary key,
	producto_id bigint not null,
	bodega_id bigint not null,
	stock_actual int not null,
	foreign key(producto_id) references producto(id),
	foreign key(bodega_id) references bodega(id)
);

create table IF NOT EXISTS movimiento_inventario (
	id bigint auto_increment primary key,
	fecha datetime not null,
	tipo enum('ENTRADA', 'SALIDA', 'TRANSFERENCIA') not null,
	usuario_id bigint not null,
	bodega_origen_id bigint,
	bodega_destino_id bigint,
	created_at datetime default CURRENT_TIMESTAMP(),
	foreign key (bodega_origen_id) references bodega(id),
	foreign key (bodega_destino_id) references bodega(id)
);

create table IF NOT EXISTS movimiento_detalle (
	id bigint auto_increment primary key,
	movimiento_id bigint not null,
	producto_id bigint not null,
	cantidad int not null,
	foreign key(movimiento_id) references movimiento_inventario(id),
	foreign key(producto_id) references producto(id)
);

create table IF NOT EXISTS auditoria_cambios (
	id bigint auto_increment primary key,
	fecha_hora datetime not null,
	usuario_id bigint not null,
	tipo_operacion enum('INSERT', 'UPDATE', 'DELETE') not null,
	entidad_afectada varchar(100) not null,
	id_entidad_afectada bigint not null,
	valores_antes JSON,
	valores_despues JSON,
	foreign key(usuario_id) references usuario(id)
);