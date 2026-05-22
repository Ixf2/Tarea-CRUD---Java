# CRUD Tienda - Java Swing + MariaDB + MVC

Aplicación CRUD desarrollada en **Java** utilizando arquitectura **MVC (Modelo - Vista - Controlador)**, conectada a una base de datos **MariaDB** mediante **JDBC**. El programa permite gestionar clientes, pedidos y envíos mediante una interfaz gráfica desarrollada con **Java Swing**.

---

# Características

El proyecto permite realizar operaciones CRUD completas sobre tres entidades:

## Customers

- Insertar clientes
- Consultar clientes
- Actualizar clientes
- Eliminar clientes

## Orders

- Insertar pedidos
- Consultar pedidos
- Actualizar pedidos
- Eliminar pedidos

## Shipments

- Insertar envíos
- Consultar envíos
- Actualizar envíos
- Eliminar envíos

Además, el programa incorpora:

- Creación automática de la base de datos
- Creación automática de tablas
- Gestión de claves foráneas
- Actualización dinámica mediante JTable
- Validación básica de errores
- Arquitectura MVC

---

# Tecnologías utilizadas

- Java
- Java Swing
- MariaDB / MySQL
- JDBC
- XAMPP
- Apache NetBeans IDE

---

# Arquitectura MVC

El proyecto sigue el patrón:

```text
Usuario
 ↓
VistaGUICRUD
 ↓
ControladorCRUD
 ↓
ModeloCRUD
 ↓
MariaDB
```

## Estructura del proyecto

```text
Proyecto CRUD
│
├── Controlador
│      └── ControladorCRUD.java
│
├── Modelo
│      ├── Conexión.java
│      └── ModeloCRUD.java
│
├── Vista
│      └── VistaGUICRUD.java
│
└── CRUD.java
```

---

# Base de datos

Nombre:

```text
Tienda
```

Tablas:

## Customers

```sql
customer_id INT AUTO_INCREMENT PRIMARY KEY
customer_name VARCHAR(50)
```

---

## Orders

```sql
order_id INT AUTO_INCREMENT PRIMARY KEY
customer_id INT
order_date DATE
```

Relación:

```text
Orders.customer_id
↓
Customers.customer_id
```

---

## Shipments

```sql
shipment_id INT AUTO_INCREMENT PRIMARY KEY
order_id INT
shipment_date DATE
```

Relación:

```text
Shipments.order_id
↓
Orders.order_id
```

---

# Instalación

## 1. Clonar repositorio

```bash
git clone URL_DEL_REPOSITORIO
```

---

## 2. Abrir proyecto

Abrir desde:

```text
Apache NetBeans IDE
```

---

## 3. Iniciar MariaDB/MySQL

Abrir:

```text
XAMPP
```

Iniciar:

```text
MySQL
```

---

## 4. Ejecutar proyecto

Ejecutar:

```text
CRUD.java
```

El programa creará automáticamente:

- Base de datos
- Tablas
- Relaciones

si no existen previamente.

---

# Funcionamiento

## Insertar Customer

Introducir:

```text
Nombre
```

Pulsar:

```text
Insertar
```

---

## Insertar Order

Introducir:

```text
ID Customer
Fecha (AAAA-MM-DD)
```

Ejemplo:

```text
1
2026-05-22
```

---

## Insertar Shipment

Introducir:

```text
ID Order
Fecha (AAAA-MM-DD)
```

Ejemplo:

```text
1
2026-05-23
```

---

# Problemas encontrados

Durante el desarrollo aparecieron errores relacionados con:

- Conexión JDBC
- NumberFormatException
- Formato incorrecto de fechas
- Claves foráneas
- Gestión de JTable
- Eventos Swing

Todos fueron corregidos mediante validaciones y control de errores.

---

# Mejoras futuras

- Añadir búsqueda de registros
- Implementar JComboBox para claves foráneas
- Añadir confirmación antes de eliminar
- Mejorar validación de fechas
- Añadir autenticación de usuarios
- Exportación de datos

---

# Capturas del proyecto

Puedes añadir aquí imágenes del funcionamiento:

```md
![Customers](ruta_imagen)
![Orders](ruta_imagen)
![Shipments](ruta_imagen)
```

---

# Autor
Joana del Pino Ramírez García
Proyecto desarrollado para la práctica y aprendizaje de:

- Java
- Swing
- MariaDB
- JDBC
- Arquitectura MVC
- CRUD
- Bases de datos relacionales

---

# Uso de Inteligencia Artificial

Durante el desarrollo del proyecto se utilizaron herramientas de inteligencia artificial como apoyo para:

- Explicación de conceptos técnicos
- Resolución de errores
- Organización del proyecto
- Asistencia en arquitectura MVC
- Corrección y mejora del código
- Redacción de documentación técnica
- Depuración de errores JDBC y MariaDB
- Ayuda durante la implementación CRUD

Todas las soluciones fueron revisadas, adaptadas y comprobadas manualmente antes de su implementación final.

---

# Licencia

Proyecto desarrollado con fines educativos y de aprendizaje.
