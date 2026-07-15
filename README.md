# -_-_proyecto_java-21_juego_de_domino_con_mySQL_jdbc_- :.
# 🁫 Proyecto Java 21 - Juego de Dominó con MySQL y JDBC:

<img width="1254" height="1254" alt="image" src="https://github.com/user-attachments/assets/1788ea7b-e310-47fb-8449-ff7ed0e760c5" />    

Proyecto muy básico desarrollado en **Java 21**, utilizando **IntelliJ IDEA**, **Consola**, **MySQL** y **JDBC**, donde se implementa un **Juego de Dominó**.

---

# 📌 Características

Este proyecto permite:

- ✅ Crear las **28 fichas** del dominó.
- ✅ Barajar las fichas aleatoriamente.
- ✅ Mostrar todas las fichas en consola.
- ✅ Guardar cada ficha en una base de datos MySQL.
- ✅ Consultar las fichas almacenadas.
- ✅ Mostrar el historial registrado desde MySQL.

---

# 🛠 Tecnologías utilizadas

- Java 21
- IntelliJ IDEA
- Maven
- JDBC
- MySQL 8+
- Programación Orientada a Objetos (POO)

---

# 📁 Estructura del proyecto

```text
DominoJavaMySQL
│
├── pom.xml
├── script.sql
│
└── src
    └── main
        └── java
            └── com
                └── domino
                    │
                    ├── Main.java
                    ├── Conexion.java
                    ├── Ficha.java
                    ├── Domino.java
                    └── DominoDAO.java
```

---

# 🗄 Base de datos

## Crear la base de datos

```sql
CREATE DATABASE domino_db;

USE domino_db;

CREATE TABLE fichas (

    id INT AUTO_INCREMENT PRIMARY KEY,

    lado1 INT,

    lado2 INT

);
```

---

# 📦 Archivo `pom.xml`

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0">

    <modelVersion>4.0.0</modelVersion>

    <groupId>com.domino</groupId>

    <artifactId>DominoJavaMySQL</artifactId>

    <version>1.0</version>

    <properties>

        <maven.compiler.source>21</maven.compiler.source>
        <maven.compiler.target>21</maven.compiler.target>

    </properties>

    <dependencies>

        <dependency>

            <groupId>com.mysql</groupId>

            <artifactId>mysql-connector-j</artifactId>

            <version>9.4.0</version>

        </dependency>

    </dependencies>

</project>
```

---

# 🔌 Clase `Conexion.java`

```java
package com.domino;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {

    private static final String URL =
            "jdbc:mysql://localhost:3306/domino_db";

    private static final String USER = "root";

    private static final String PASSWORD = "1234";

    public static Connection conectar() throws Exception {

        return DriverManager.getConnection(URL, USER, PASSWORD);

    }

}
```

---

# 🁫 Clase `Ficha.java`

```java
package com.domino;

public class Ficha {

    private int lado1;

    private int lado2;

    public Ficha(int lado1, int lado2) {

        this.lado1 = lado1;
        this.lado2 = lado2;

    }

    public int getLado1() {
        return lado1;
    }

    public int getLado2() {
        return lado2;
    }

    @Override
    public String toString() {

        return "[" + lado1 + "|" + lado2 + "]";

    }

}
```

---

# 🎲 Clase `Domino.java`

```java
package com.domino;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Domino {

    private List<Ficha> fichas = new ArrayList<>();

    public Domino() {

        crearFichas();

    }

    private void crearFichas() {

        for (int i = 0; i <= 6; i++) {

            for (int j = i; j <= 6; j++) {

                fichas.add(new Ficha(i, j));

            }

        }

    }

    public void barajar() {

        Collections.shuffle(fichas);

    }

    public List<Ficha> getFichas() {

        return fichas;

    }

}
```

---

# 💾 Clase `DominoDAO.java`

```java
package com.domino;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DominoDAO {

    public void guardar(Ficha ficha) throws Exception {

        Connection cn = Conexion.conectar();

        String sql =
                "INSERT INTO fichas(lado1,lado2) VALUES(?,?)";

        PreparedStatement ps =
                cn.prepareStatement(sql);

        ps.setInt(1, ficha.getLado1());

        ps.setInt(2, ficha.getLado2());

        ps.executeUpdate();

        ps.close();

        cn.close();

    }

    public void mostrar() throws Exception {

        Connection cn = Conexion.conectar();

        String sql = "SELECT * FROM fichas";

        PreparedStatement ps =
                cn.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();

        System.out.println();

        System.out.println("FICHAS EN MYSQL");

        System.out.println("--------------------");

        while (rs.next()) {

            System.out.println(

                    rs.getInt("id")
                            + " "
                            + "["
                            + rs.getInt("lado1")
                            + "|"
                            + rs.getInt("lado2")
                            + "]"

            );

        }

        rs.close();

        ps.close();

        cn.close();

    }

}
```

---

# 🚀 Clase `Main.java`

```java
package com.domino;

public class Main {

    public static void main(String[] args) {

        try {

            Domino domino = new Domino();

            domino.barajar();

            DominoDAO dao = new DominoDAO();

            System.out.println();

            System.out.println("DOMINO");

            System.out.println("----------------------");

            for (Ficha ficha : domino.getFichas()) {

                System.out.println(ficha);

                dao.guardar(ficha);

            }

            dao.mostrar();

        }

        catch (Exception e) {

            e.printStackTrace();

        }

    }

}
```

---

# ▶ Resultado en consola

```text
DOMINO
----------------------

[6|6]
[0|1]
[5|6]
[3|5]
[2|2]
[1|4]
...
```

```text
FICHAS EN MYSQL
---------------------

1 [6|6]
2 [0|1]
3 [5|6]
4 [3|5]
5 [2|2]
...
28 [0|0]
```

---

# 🗃 Consultar los registros almacenados

```sql
SELECT * FROM fichas;
```

---

# 📊 Resultado esperado

| id | lado1 | lado2 |
|---:|------:|------:|
| 1 | 6 | 6 |
| 2 | 0 | 1 |
| 3 | 5 | 6 |
| 4 | 3 | 5 |
| ... | ... | ... |
| 28 | 0 | 0 |

---

# 🔄 Flujo del programa

```text
Inicio
   │
   ▼
Crear las 28 fichas
   │
   ▼
Barajar las fichas
   │
   ▼
Mostrar en consola
   │
   ▼
Guardar cada ficha en MySQL
   │
   ▼
Consultar las fichas almacenadas
   │
   ▼
Mostrar historial desde MySQL
   │
   ▼
Fin
```

---

# 📚 Conceptos aplicados

Durante el desarrollo del proyecto se utilizan los siguientes conceptos:

- Programación Orientada a Objetos (POO)
- Clases y Objetos
- Encapsulamiento
- Colecciones (`ArrayList`)
- Bucles `for`
- JDBC
- MySQL
- Maven
- Manejo de excepciones (`try-catch`)
- Métodos
- Sobrescritura de `toString()`

---

# 🚀 Mejoras que se pueden agregar

Este proyecto puede evolucionar a una versión mucho más completa incorporando:

- 🎮 Juego para **2 jugadores** (usuario vs usuario).
- 🤖 Modo **usuario vs computadora**.
- 🂠 Reparto automático de **7 fichas por jugador**.
- 📋 Registro de partidas, jugadores y movimientos en MySQL.
- 🏆 Cálculo automático del ganador.
- 📈 Sistema de puntajes.
- 🖥️ Interfaz gráfica con **Swing** o **JavaFX**.
- 📊 Historial de partidas.
- 📉 Estadísticas de jugadores.
- 🔄 Reinicio automático de partidas.
- 🌐 Versión cliente-servidor mediante sockets.
- 🌍 Versión web utilizando Spring Boot.

---

# 🎯 Conclusión

Este proyecto constituye una excelente práctica para aprender el uso de **Java 21**, **JDBC** y **MySQL**, implementando un clásico **Juego de Dominó** desde consola.

A lo largo del desarrollo se fortalecen conocimientos sobre:

- Programación Orientada a Objetos.
- Manejo de colecciones.
- Persistencia de datos con JDBC.
- Conexión con MySQL.
- Organización de proyectos con Maven.
- Separación de responsabilidades mediante clases (Modelo, DAO y Lógica).

Es una base ideal para evolucionar posteriormente hacia aplicaciones con interfaz gráfica, aplicaciones web o incluso juegos multijugador.
