CREATE DATABASE banco WITH OWNER postgres;

CREATE SCHEMA "financiero" AUTHORIZATION postgres;

COMMENT ON SCHEMA "financiero" IS 'Esquema referente al flujo financiero';

CREATE TABLE "financiero".cliente (
    id_cliente bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
    nombre varchar(200) NOT NULL,
    genero varchar(20) NOT NULL,
    fe_nacimiento date NOT NULL,
    identificacion varchar(15) NOT NULL,
    direccion varchar(300) NULL,
    telefono varchar(100) NULL,
    clave varchar(20) NOT NULL,
    estado varchar(20) NOT NULL,
    usuario_creacion varchar(20) NOT NULL,
    fe_creacion date NOT NULL,
    ip_creacion varchar(20) NOT NULL,
    CONSTRAINT cliente_pk PRIMARY KEY (id_cliente)
);
COMMENT ON TABLE "financiero".cliente IS 'Guarda los datos de los clientes';

-- Column comments

COMMENT ON COLUMN "financiero".cliente.id_cliente IS 'id del cliente';
COMMENT ON COLUMN "financiero".cliente.nombre IS 'nombre de la persona';
COMMENT ON COLUMN "financiero".cliente.genero IS 'Genero de la persona Masculino/Femenino';
COMMENT ON COLUMN "financiero".cliente.fe_nacimiento IS 'Fecha de nacimiento';
COMMENT ON COLUMN "financiero".cliente.identificacion IS 'Identificacion de la persona';
COMMENT ON COLUMN "financiero".cliente.direccion IS 'direccion de la persona';
COMMENT ON COLUMN "financiero".cliente.telefono IS 'Telefono de la persona';
COMMENT ON COLUMN "financiero".cliente.clave IS 'clave del cliente';
COMMENT ON COLUMN "financiero".cliente.estado IS 'Estado del cliente Activo/Inactivo';
COMMENT ON COLUMN "financiero".cliente.usuario_creacion IS 'usuario de creacion del registro';
COMMENT ON COLUMN "financiero".cliente.fe_creacion IS 'fecha de creacion del registro';
COMMENT ON COLUMN "financiero".cliente.ip_creacion IS 'ip de creacion del registro';


CREATE TABLE "financiero".cuenta (
    id_cuenta bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
    cliente_id bigint NOT NULL,
    tipo_cuenta varchar(20) NOT NULL,
    numero varchar(20) NOT NULL,
    saldo_inicial decimal(15, 2) NOT NULL,
    estado varchar(20) NOT NULL,
    usuario_creacion varchar(20) NOT NULL,
    fe_creacion date NOT NULL,
    ip_creacion varchar(20) NOT NULL,
    CONSTRAINT cuenta_pk PRIMARY KEY (id_cuenta),
    CONSTRAINT cuenta_fk FOREIGN KEY (cliente_id) REFERENCES "financiero".cliente(id_cliente)
);
COMMENT ON TABLE "financiero".cuenta IS 'Guarda los datos de las cuentas de los clientes';

-- Column comments

COMMENT ON COLUMN "financiero".cuenta.id_cuenta IS 'id de la cuenta';
COMMENT ON COLUMN "financiero".cuenta.cliente_id IS 'id de cliente para relacionar con la tabla cliente';
COMMENT ON COLUMN "financiero".cuenta.tipo_cuenta IS 'Tipo de cuenta Corriente/Ahorro';
COMMENT ON COLUMN "financiero".cuenta.numero IS 'numero de cuenta';
COMMENT ON COLUMN "financiero".cuenta.saldo_inicial IS 'Saldo inicial de la cuenta';
COMMENT ON COLUMN "financiero".cuenta.estado IS 'estado de la cuenta Activo/Inactivo';
COMMENT ON COLUMN "financiero".cuenta.usuario_creacion IS 'usuario de creacion del registro';
COMMENT ON COLUMN "financiero".cuenta.fe_creacion IS 'Fecha de creacion del registro';
COMMENT ON COLUMN "financiero".cuenta.ip_creacion IS 'ip de creacion del registro';


CREATE TABLE "financiero".movimiento (
    id_movimiento bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
    cuenta_id bigint NOT NULL,
    tipo_movimiento varchar(20) NOT NULL,
    valor decimal(15, 2) NOT NULL,
    saldo decimal(15, 2) NOT NULL,
    usuario_creacion varchar(20) NOT NULL,
    fe_creacion date NOT NULL,
    ip_creacion varchar(20) NOT NULL,
    CONSTRAINT movimiento_pk PRIMARY KEY (id_movimiento),
    CONSTRAINT movimiento_fk FOREIGN KEY (cuenta_id) REFERENCES "financiero".cuenta(id_cuenta)
);
COMMENT ON TABLE "financiero".movimiento IS 'Guarda los movimientos de las cuentas';

-- Column comments

COMMENT ON COLUMN "financiero".movimiento.id_movimiento IS 'id del movimiento de cuenta';
COMMENT ON COLUMN "financiero".movimiento.cuenta_id IS 'id de la cuenta para relacionar con la tabla cuenta';
COMMENT ON COLUMN "financiero".movimiento.tipo_movimiento IS 'tipo de movimiento Debito/Credito';
COMMENT ON COLUMN "financiero".movimiento.valor IS 'valor del movimiento';
COMMENT ON COLUMN "financiero".movimiento.saldo IS 'saldo disponible';
COMMENT ON COLUMN "financiero".movimiento.usuario_creacion IS 'usuario de creacion del registro';
COMMENT ON COLUMN "financiero".movimiento.fe_creacion IS 'Fecha de creacion del registro';
COMMENT ON COLUMN "financiero".movimiento.ip_creacion IS 'ip de creacion del registro';

