##Financiero
Permite llevar el control de los movimientos por cuentas bancarias y clientes

####Api rest

##Configuracion

> servidor:localhost

> puerto: 8080

####Listado de Clientes

`[Get] <servidor>:<puerto>/api/clientes`

Devuelve el listado de clientes

```Response
[
    {
        "nombre": "Jose Lema",
        "direccion": "Otavalo sn y Principal",
        "telefono": "098254785",
        "clave": "1234",
        "estado": "Activo"
    }
]
```

####Crear Cliente

`[Post] <servidor>:<puerto>/api/clientes`

Crea un nuevo cliente

```
Request
{
    "nombre": "Jose Lema",
    "genero": "Masculino",
    "feNacimiento": "1995-12-23",
    "identificacion": "0900000001",
    "direccion": "Otavalo sn y Principal",
    "telefono": "098254785",
    "clave": "1234",
    "estado": "Activo",
    "usuarioCreacion": "jpru",
    "feCreacion": "2022-02-27",
    "ipCreacion": "127.0.0.1"
}
```
```
Response
{
    "nombre": "Jose Lema",
    "genero": "Masculino",
    "feNacimiento": "1995-12-23",
    "identificacion": "0900000001",
    "direccion": "Otavalo sn y Principal",
    "telefono": "098254785",
    "clave": "1234",
    "estado": "Activo",
    "usuarioCreacion": "jpru",
    "feCreacion": "2022-02-27",
    "ipCreacion": "127.0.0.1"
}
```