package ud18;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class main_app {
	public static void main(String[] args) {
        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
        	Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:33060","root","password");
        	System.out.println("ServerConnected");
        	
        	crearDB(conexion);
        	
        	tarea1(conexion);
        	
        	//Para leer la tabla de la tarea 1 descomentar la linea inferior
        	//leerTabla1(conexion);
        	
        	tarea2(conexion);
        	
        	tarea3(conexion);
        	
        	tarea4(conexion);
        	
        	tarea5(conexion);
        	
        	tarea6(conexion);
        	
        	tarea7(conexion);
        	
        	tarea8(conexion);
        	
        	tarea9(conexion);
        	
        	closeConnection(conexion);
        	
        }catch(SQLException | ClassNotFoundException ex) {
        	System.out.println("no se ha podido establecer la conexion con la base de datos");
        	System.out.println(ex);
        }
    }
	
	public static void closeConnection(Connection conexion) {
		try {
			conexion.close();
			System.out.println("Se ha finalizado la conexion con el servidor");
		}catch(SQLException ex) {
			System.out.println(ex);

		}
	}
	
	public static void crearDB(Connection conexion) {
		try {
			 Statement statement = conexion.createStatement();
			 
			 statement.executeUpdate("DROP DATABASE IF EXISTS actividades2");
             statement.executeUpdate("CREATE DATABASE IF NOT EXISTS actividades2");
             statement.executeUpdate("USE actividades2");
             
		}catch(SQLException ex) {
			System.out.println(ex);
		}
	}
	
	public static void leerTabla1(Connection conexion) {
		try {
			Statement statement = conexion.createStatement();
			String consultaMostrarArticulos = "SELECT * FROM articulos";
	        java.sql.ResultSet resultado = statement.executeQuery(consultaMostrarArticulos);

	        while (resultado.next()) {
	            int codigo = resultado.getInt("CODIGO");
	            String nombre = resultado.getString("NOMBRE");
	            double precio = resultado.getDouble("PRECIO");
	            int fabricante = resultado.getInt("FABRICANTE");

	            System.out.println("Código: " + codigo + ", Nombre: " + nombre + ", Precio: " + precio + ", Fabricante: " + fabricante);
	        }
            
		}catch(SQLException ex) {
			System.out.println(ex);
		}
		
	}
	
	public static void tarea1(Connection conexion) {
		try {
			Statement statement = conexion.createStatement();

			statement.executeUpdate("CREATE TABLE IF NOT EXISTS fabricantes ("
			        + "CODIGO INT NOT NULL, "
			        + "NOMBRE VARCHAR(255) NOT NULL, "
			        + "PRIMARY KEY (CODIGO))");

			statement.executeUpdate("INSERT INTO fabricantes (CODIGO, NOMBRE) VALUES "
			        + "(1, 'Sony'), "
			        + "(2, 'Creative Labs'), "
			        + "(3, 'Hewlett-Packard'), "
			        + "(4, 'Iomega'), "
			        + "(5, 'Fujitsu'), "
			        + "(6, 'Winchester')");

			statement.executeUpdate("CREATE TABLE IF NOT EXISTS articulos ("
			        + "CODIGO INT NOT NULL, "
			        + "NOMBRE VARCHAR(255) NOT NULL, "
			        + "PRECIO DECIMAL(10,0) NOT NULL, "
			        + "FABRICANTE INT NOT NULL, "
			        + "PRIMARY KEY (CODIGO), "
			        + "KEY FABRICANTE (FABRICANTE), "
			        + "CONSTRAINT articulos_ibfk_1 FOREIGN KEY (FABRICANTE) "
			        + "REFERENCES fabricantes (CODIGO))");

			statement.executeUpdate("INSERT INTO articulos (CODIGO, NOMBRE, PRECIO, FABRICANTE) VALUES "
			        + "(1, 'Hard drive', 240, 5), "
			        + "(2, 'Memory', 120, 6), "
			        + "(3, 'ZIP drive', 150, 4), "
			        + "(4, 'Floppy disk', 5, 6), "
			        + "(5, 'Monitor', 240, 1), "
			        + "(6, 'DVD drive', 180, 2), "
			        + "(7, 'CD drive', 90, 2), "
			        + "(8, 'Printer', 270, 3), "
			        + "(9, 'Toner cartridge', 66, 3), "
			        + "(10, 'DVD burner', 180, 2)");
			
            System.out.println("Tarea 1 DONE!");
		}catch(SQLException ex) {
			System.out.println(ex);
		}
	}
	
	public static void tarea2(Connection conexion) {
		try {
			Statement statement = conexion.createStatement();
			
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS departamentos ("
			        + "CODIGO INT NOT NULL, "
			        + "NOMBRE VARCHAR(255) NOT NULL, "
			        + "PRESUPUESTO DECIMAL(10,0) NOT NULL, "
			        + "PRIMARY KEY (CODIGO))");

			statement.executeUpdate("INSERT INTO departamentos (CODIGO, NOMBRE, PRESUPUESTO) VALUES "
			        + "(14, 'IT', 65000), "
			        + "(37, 'Accounting', 15000), "
			        + "(59, 'Human Resources', 240000), "
			        + "(77, 'Research', 55000)");

			statement.executeUpdate("CREATE TABLE IF NOT EXISTS empleados ("
			        + "DNI INT NOT NULL, "
			        + "NOMBRE VARCHAR(255) NOT NULL, "
			        + "APELLIDOS VARCHAR(255) NOT NULL, "
			        + "DEPARTAMENTO INT NOT NULL, "
			        + "PRIMARY KEY (DNI), "
			        + "KEY DEPARTAMENTO (DEPARTAMENTO), "
			        + "CONSTRAINT empleados_ibfk_1 FOREIGN KEY (DEPARTAMENTO) "
			        + "REFERENCES departamentos (CODIGO))");

			statement.executeUpdate("INSERT INTO empleados (DNI, NOMBRE, APELLIDOS, DEPARTAMENTO) VALUES "
			        + "(123234877, 'Michael', 'Rogers', 14), "
			        + "(152934485, 'Anand', 'Manikutty', 14), "
			        + "(222364883, 'Carol', 'Smith', 37), "
			        + "(326587417, 'Joe', 'Stevens', 37), "
			        + "(332154719, 'Mary-Anne', 'Foster', 14), "
			        + "(332569843, 'George', 'O''Donnell', 77), "
			        + "(546523478, 'John', 'Doe', 59), "
			        + "(631231482, 'David', 'Smith', 77), "
			        + "(654873219, 'Zacary', 'Efron', 59), "
			        + "(745685214, 'Eric', 'Goldsmith', 59), "
			        + "(845657233, 'Luis', 'López', 14), "
			        + "(845657245, 'Elizabeth', 'Doe', 14), "
			        + "(845657246, 'Kumar', 'Swamy', 14), "
			        + "(845657266, 'Jose', 'Pérez', 77)");
			
			
            System.out.println("Tarea 2 DONE!");
		}catch(SQLException ex) {
			System.out.println(ex);
		}
	}
	
	public static void tarea3(Connection conexion) {
		try {
			Statement statement = conexion.createStatement();
			
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS almacenes ("
                    + "CODIGO INT NOT NULL, "
                    + "LUGAR VARCHAR(255) NOT NULL, "
                    + "CAPACIDAD INT NOT NULL, "
                    + "PRIMARY KEY (CODIGO))");

            statement.executeUpdate("INSERT INTO almacenes (CODIGO, LUGAR, CAPACIDAD) VALUES "
                    + "(1, 'Valencia', 3), "
                    + "(2, 'Barcelona', 4), "
                    + "(3, 'Bilbao', 7), "
                    + "(4, 'Los Angeles', 2), "
                    + "(5, 'San Francisco', 8)");
            
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS cajas ("
                    + "NUMREFERENCIA VARCHAR(255) NOT NULL, "
                    + "CONTENIDO VARCHAR(255) NOT NULL, "
                    + "VALOR DOUBLE NOT NULL, "
                    + "ALMACEN INT NOT NULL, "
                    + "PRIMARY KEY (NUMREFERENCIA), "
                    + "KEY ALMACEN (ALMACEN), "
                    + "CONSTRAINT cajas_ibfk_1 FOREIGN KEY (ALMACEN) "
                    + "REFERENCES almacenes (CODIGO))");

            statement.executeUpdate("INSERT INTO cajas (NUMREFERENCIA, CONTENIDO, VALOR, ALMACEN) VALUES "
                    + "('0MN7', 'Rocks', 180, 3), "
                    + "('4H8P', 'Rocks', 250, 1), "
                    + "('4RT3', 'Scissors', 190, 4), "
                    + "('7G3H', 'Rocks', 200, 1), "
                    + "('8JN6', 'Papers', 75, 1), "
                    + "('8Y6U', 'Papers', 50, 3), "
                    + "('9J6F', 'Papers', 175, 2), "
                    + "('LL08', 'Rocks', 140, 4), "
                    + "('P0H6', 'Scissors', 125, 1), "
                    + "('P2T6', 'Scissors', 150, 2), "
                    + "('TU55', 'Papers', 90, 5)");
			
            System.out.println("Tarea 3 DONE!");
		}catch(SQLException ex) {
			System.out.println(ex);
		}
	}
	
	public static void tarea4(Connection conexion) {
		try {
			Statement statement = conexion.createStatement();

			statement.executeUpdate("CREATE TABLE IF NOT EXISTS peliculas ("
			        + "CODIGO INT NOT NULL, "
			        + "NOMBRE VARCHAR(255) NOT NULL, "
			        + "CALIFICACIONEDAD VARCHAR(255), "
			        + "PRIMARY KEY (CODIGO))");

			statement.executeUpdate("INSERT INTO peliculas (CODIGO, NOMBRE, CALIFICACIONEDAD) VALUES "
			        + "(1, 'Citizen Kane', 'PG'), "
			        + "(2, 'Singin'' in the Rain', 'G'), "
			        + "(3, 'The Wizard of Oz', 'G'), "
			        + "(4, 'The Quiet Man', NULL), "
			        + "(5, 'North by Northwest', NULL), "
			        + "(6, 'The Last Tango in Paris', 'NC-17'), "
			        + "(7, 'Some Like it Hot', 'PG-13'), "
			        + "(8, 'A Night at the Opera', NULL), "
			        + "(9, 'Citizen King', 'G')");

			statement.executeUpdate("CREATE TABLE IF NOT EXISTS salas ("
			        + "CODIGO INT NOT NULL, "
			        + "NOMBRE VARCHAR(255) NOT NULL, "
			        + "PELICULA INT, "
			        + "PRIMARY KEY (CODIGO), "
			        + "KEY PELICULA (PELICULA), "
			        + "CONSTRAINT salas_ibfk_1 FOREIGN KEY (PELICULA) "
			        + "REFERENCES peliculas (CODIGO))");

			statement.executeUpdate("INSERT INTO salas (CODIGO, NOMBRE, PELICULA) VALUES "
			        + "(1, 'Odeon', 5), "
			        + "(2, 'Imperial', 1), "
			        + "(3, 'Majestic', NULL), "
			        + "(4, 'Royale', 6), "
			        + "(5, 'Paraiso', 3), "
			        + "(6, 'Nickelodeon', NULL)");
			
            System.out.println("Tarea 4 DONE!");
		}catch(SQLException ex) {
			System.out.println(ex);
		}
	}
	
	public static void tarea5(Connection conexion) {
		try {
			Statement statement = conexion.createStatement();

            statement.executeUpdate("CREATE TABLE IF NOT EXISTS Despachos ("
                    + "numero INT AUTO_INCREMENT, "
                    + "capacidad INT, "
                    + "PRIMARY KEY (numero))");

            statement.executeUpdate("INSERT INTO Despachos (capacidad) VALUES "
                    + "(10), "
                    + "(15), "
                    + "(7), "
                    + "(9), "
                    + "(11), "
                    + "(6), "
                    + "(25), "
                    + "(100), "
                    + "(3), "
                    + "(3)");

            statement.executeUpdate("CREATE TABLE IF NOT EXISTS Directores ("
                    + "dni VARCHAR(8), "
                    + "NomApels VARCHAR(255), "
                    + "DNIjefe VARCHAR(8), "
                    + "despacho INT, "
                    + "PRIMARY KEY (dni), "
                    + "FOREIGN KEY (DNIjefe) REFERENCES Directores (dni) ON DELETE CASCADE ON UPDATE CASCADE, "
                    + "FOREIGN KEY (despacho) REFERENCES Despachos (numero) ON DELETE CASCADE ON UPDATE CASCADE)");

            statement.executeUpdate("INSERT INTO Directores VALUES "
                    + "('dni1', 'Sergi', NULL, 1), "
                    + "('dni2', 'Asier', 'dni1', 2), "
                    + "('dni3', 'Marcos', NULL, 3), "
                    + "('dni4', 'Juan', 'dni1', 4), "
                    + "('dni5', 'Andres', NULL, 5), "
                    + "('dni6', 'Sergi', 'dni3', 6), "
                    + "('dni7', 'Antonio', 'dni5', 7), "
                    + "('dni8', 'Jose', NULL, 8), "
                    + "('dni9', 'Marc', NULL, 9), "
                    + "('dni10', 'David', 'dni8', 1)");
            
            System.out.println("Tarea 5 DONE!");
		}catch(SQLException ex) {
			System.out.println(ex);
		}
	}

	public static void tarea6(Connection conexion) {
		try {
			Statement statement = conexion.createStatement();
			
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS Proveedores ("
                    + "id VARCHAR(4), "
                    + "nombre VARCHAR(100), "
                    + "PRIMARY KEY (id))");

            statement.executeUpdate("INSERT INTO Proveedores (id, nombre) VALUES "
                    + "('id1', 'proveedor1'), "
                    + "('id2', 'proveedor2'), "
                    + "('id3', 'proveedor3'), "
                    + "('id4', 'proveedor4'), "
                    + "('id5', 'proveedor5'), "
                    + "('id6', 'proveedor6'), "
                    + "('id7', 'proveedor7'), "
                    + "('id8', 'proveedor8'), "
                    + "('id9', 'proveedor9'), "
                    + "('id10', 'proveedor10')");

            statement.executeUpdate("CREATE TABLE IF NOT EXISTS Piezas ("
                    + "codigo INT AUTO_INCREMENT, "
                    + "nombre VARCHAR(100), "
                    + "PRIMARY KEY (codigo))");

            statement.executeUpdate("INSERT INTO Piezas (nombre) VALUES "
                    + "('Sergi'), "
                    + "('Juan'), "
                    + "('Marc'), "
                    + "('Jose'), "
                    + "('Antonio'), "
                    + "('Manel'), "
                    + "('Ainara'), "
                    + "('Ainoa'), "
                    + "('Marc'), "
                    + "('Rafa')");

            statement.executeUpdate("CREATE TABLE IF NOT EXISTS Suministra ("
                    + "codigoPieza INT, "
                    + "idProveedor VARCHAR(4), "
                    + "precio DOUBLE, "
                    + "PRIMARY KEY (codigoPieza, idProveedor), "
                    + "FOREIGN KEY (codigoPieza) REFERENCES Piezas (codigo) ON DELETE CASCADE ON UPDATE CASCADE, "
                    + "FOREIGN KEY (idProveedor) REFERENCES Proveedores (id) ON DELETE CASCADE ON UPDATE CASCADE)");

            statement.executeUpdate("INSERT INTO Suministra (codigoPieza, idProveedor, precio) VALUES "
                    + "(1, 'id1', 150), "
                    + "(2, 'id5', 64), "
                    + "(3, 'id7', 3), "
                    + "(4, 'id10', 44), "
                    + "(5, 'id6', 157), "
                    + "(6, 'id8', 10.3), "
                    + "(7, 'id9', 2.3), "
                    + "(8, 'id4', 8.99), "
                    + "(9, 'id8', 74.3), "
                    + "(10, 'id1', 99.99)");
			           
            System.out.println("Tarea 6 DONE!");
		}catch(SQLException ex) {
			System.out.println(ex);
		}
	}
	
	public static void tarea7(Connection conexion) {
		try {
			Statement statement = conexion.createStatement();

            statement.executeUpdate("CREATE TABLE IF NOT EXISTS Cientificos ("
                    + "dni VARCHAR(8), "
                    + "nom_apels VARCHAR(255), "
                    + "PRIMARY KEY (dni))");

            statement.executeUpdate("INSERT INTO Cientificos (dni, nom_apels) VALUES "
                    + "('dni1', 'nombre1'), "
                    + "('dni2', 'nombre2'), "
                    + "('dni3', 'nombre3'), "
                    + "('dni4', 'nombre4'), "
                    + "('dni5', 'nombre5'), "
                    + "('dni6', 'nombre6'), "
                    + "('dni7', 'nombre7'), "
                    + "('dni8', 'nombre8'), "
                    + "('dni9', 'nombre9'), "
                    + "('dni10', 'Sergi')");

            statement.executeUpdate("CREATE TABLE IF NOT EXISTS Proyecto ("
                    + "id CHAR(4), "
                    + "nombre VARCHAR(255), "
                    + "horas INT, "
                    + "PRIMARY KEY (id))");

            statement.executeUpdate("INSERT INTO Proyecto (id, nombre, horas) VALUES "
                    + "('id1', 'proyecto1', 10), "
                    + "('id2', 'proyecto2', 15), "
                    + "('id3', 'proyecto3', 6), "
                    + "('id4', 'proyecto4', 7), "
                    + "('id5', 'proyecto5', 8), "
                    + "('id6', 'proyecto6', 5), "
                    + "('id7', 'proyecto7', 1), "
                    + "('id8', 'proyecto8', 33), "
                    + "('id9', 'proyecto9', 4), "
                    + "('id10', 'proyecto10', 20)");

            statement.executeUpdate("CREATE TABLE IF NOT EXISTS Asignado_a ("
                    + "dni_cientifico VARCHAR(8), "
                    + "id_proyecto CHAR(4), "
                    + "PRIMARY KEY (dni_cientifico, id_proyecto), "
                    + "FOREIGN KEY (dni_cientifico) REFERENCES Cientificos (dni) ON DELETE CASCADE ON UPDATE CASCADE, "
                    + "FOREIGN KEY (id_proyecto) REFERENCES Proyecto (id) ON DELETE CASCADE ON UPDATE CASCADE)");

            statement.executeUpdate("INSERT INTO Asignado_a (dni_cientifico, id_proyecto) VALUES "
                    + "('dni1', 'id1'), "
                    + "('dni2', 'id2'), "
                    + "('dni3', 'id3'), "
                    + "('dni4', 'id4'), "
                    + "('dni5', 'id5'), "
                    + "('dni6', 'id6'), "
                    + "('dni7', 'id7'), "
                    + "('dni8', 'id8'), "
                    + "('dni9', 'id9'), "
                    + "('dni10', 'id10')");
			           
            System.out.println("Tarea 7 DONE!");
		}catch(SQLException ex) {
			System.out.println(ex);
		}
	}

	public static void tarea8(Connection conexion) {
		try {
			Statement statement = conexion.createStatement();

            statement.executeUpdate("CREATE TABLE IF NOT EXISTS Cajeros ("
                    + "codigo INT AUTO_INCREMENT, "
                    + "nom_apels VARCHAR(255), "
                    + "PRIMARY KEY (codigo))");

            statement.executeUpdate("INSERT INTO Cajeros (nom_apels) VALUES "
                    + "('nombre1'), "
                    + "('nombre2'), "
                    + "('nombre3'), "
                    + "('nombre4'), "
                    + "('nombre5'), "
                    + "('nombre6'), "
                    + "('nombre7'), "
                    + "('nombre8'), "
                    + "('nombre9'), "
                    + "('nombre10')");

            statement.executeUpdate("CREATE TABLE IF NOT EXISTS Productos ("
                    + "codigo INT AUTO_INCREMENT, "
                    + "nombre VARCHAR(100), "
                    + "precio DOUBLE, "
                    + "PRIMARY KEY (codigo))");

            statement.executeUpdate("INSERT INTO Productos (nombre, precio) VALUES "
                    + "('nombre1', 10.3), "
                    + "('nombre2', 0.5), "
                    + "('nombre3', 80), "
                    + "('nombre4', 12.3), "
                    + "('nombre5', 10.5), "
                    + "('nombre6', 17), "
                    + "('nombre7', 99.9), "
                    + "('nombre8', 103), "
                    + "('nombre9', 100), "
                    + "('nombre10', 1)");

            statement.executeUpdate("CREATE TABLE IF NOT EXISTS Maquinas_registradoras ("
                    + "codigo INT AUTO_INCREMENT, "
                    + "piso INT, "
                    + "PRIMARY KEY (codigo))");

            statement.executeUpdate("INSERT INTO Maquinas_registradoras (piso) VALUES "
                    + "(10), "
                    + "(0), "
                    + "(8), "
                    + "(12), "
                    + "(10), "
                    + "(17), "
                    + "(99), "
                    + "(1), "
                    + "(150), "
                    + "(16)");

            statement.executeUpdate("CREATE TABLE IF NOT EXISTS Venta ("
                    + "codigo_cajero INT, "
                    + "codigo_maquina INT, "
                    + "codigo_producto INT, "
                    + "PRIMARY KEY (codigo_cajero, codigo_maquina, codigo_producto), "
                    + "FOREIGN KEY (codigo_cajero) REFERENCES Cajeros (codigo) ON DELETE CASCADE ON UPDATE CASCADE, "
                    + "FOREIGN KEY (codigo_maquina) REFERENCES Maquinas_registradoras (codigo) ON DELETE CASCADE ON UPDATE CASCADE, "
                    + "FOREIGN KEY (codigo_producto) REFERENCES Productos (codigo) ON DELETE CASCADE ON UPDATE CASCADE)");

            statement.executeUpdate("INSERT INTO Venta (codigo_cajero, codigo_maquina, codigo_producto) VALUES "
                    + "(1, 1, 1), "
                    + "(2, 2, 2), "
                    + "(3, 3, 3), "
                    + "(4, 4, 4), "
                    + "(5, 5, 5), "
                    + "(6, 6, 6), "
                    + "(7, 7, 7), "
                    + "(8, 8, 8), "
                    + "(9, 9, 9), "
                    + "(10, 10, 10)");
			           
            System.out.println("Tarea 8 DONE!");
		}catch(SQLException ex) {
			System.out.println(ex);
		}
	}

	public static void tarea9(Connection conexion) {
		try {
			Statement statement = conexion.createStatement();

            statement.executeUpdate("CREATE TABLE IF NOT EXISTS Facultad ("
                    + "codigo INT AUTO_INCREMENT, "
                    + "nombre VARCHAR(100), "
                    + "PRIMARY KEY (codigo))");

            statement.executeUpdate("INSERT INTO Facultad (nombre) VALUES "
                    + "('facultad1'), "
                    + "('facultad2'), "
                    + "('facultad3'), "
                    + "('facultad4'), "
                    + "('facultad5'), "
                    + "('facultad6'), "
                    + "('facultad7'), "
                    + "('facultad8'), "
                    + "('facultad9'), "
                    + "('facultad10')");

            statement.executeUpdate("CREATE TABLE IF NOT EXISTS Investigadores ("
                    + "dni VARCHAR(8), "
                    + "nom_apels VARCHAR(100), "
                    + "facultad INT, "
                    + "PRIMARY KEY (dni), "
                    + "FOREIGN KEY (facultad) REFERENCES Facultad (codigo) ON DELETE CASCADE ON UPDATE CASCADE)");

            statement.executeUpdate("INSERT INTO Investigadores (dni, nom_apels, facultad) VALUES "
                    + "('dni1', 'nombre1', 1), "
                    + "('dni2', 'nombre2', 2), "
                    + "('dni3', 'nombre3', 3), "
                    + "('dni4', 'nombre4', 4), "
                    + "('dni5', 'nombre5', 5), "
                    + "('dni6', 'nombre6', 6), "
                    + "('dni7', 'nombre7', 7), "
                    + "('dni8', 'nombre8', 8), "
                    + "('dni9', 'nombre9', 9), "
                    + "('dni10', 'nombre10', 10)");

            statement.executeUpdate("CREATE TABLE IF NOT EXISTS Equipos ("
                    + "num_serie CHAR(10), "
                    + "nombre VARCHAR(100), "
                    + "facultad INT, "
                    + "PRIMARY KEY (num_serie), "
                    + "FOREIGN KEY (facultad) REFERENCES Facultad (codigo) ON DELETE CASCADE ON UPDATE CASCADE)");

            statement.executeUpdate("INSERT INTO Equipos (num_serie, nombre, facultad) VALUES "
                    + "('num1', 'nombre1', 1), "
                    + "('num2', 'nombre2', 2), "
                    + "('num3', 'nombre3', 3), "
                    + "('num4', 'nombre4', 4), "
                    + "('num5', 'nombre5', 5), "
                    + "('num6', 'nombre6', 6), "
                    + "('num7', 'nombre7', 7), "
                    + "('num8', 'nombre8', 8), "
                    + "('num9', 'nombre9', 9), "
                    + "('num10', 'nombre10', 10)");

            statement.executeUpdate("CREATE TABLE IF NOT EXISTS Reserva ("
                    + "dni_investigador VARCHAR(8), "
                    + "num_serie_equipo CHAR(10), "
                    + "comienzo DATE, "
                    + "fin DATE, "
                    + "PRIMARY KEY (dni_investigador, num_serie_equipo), "
                    + "FOREIGN KEY (dni_investigador) REFERENCES Investigadores (dni) ON DELETE CASCADE ON UPDATE CASCADE, "
                    + "FOREIGN KEY (num_serie_equipo) REFERENCES Equipos (num_serie) ON DELETE CASCADE ON UPDATE CASCADE)");

            statement.executeUpdate("INSERT INTO Reserva (dni_investigador, num_serie_equipo, comienzo, fin) VALUES "
                    + "('dni1', 'num1', '2023-10-01', '2023-10-30'), "
                    + "('dni2', 'num2', '2023-10-01', '2023-10-30'), "
                    + "('dni3', 'num3', '2023-10-01', '2023-10-30'), "
                    + "('dni4', 'num4', '2023-10-01', '2023-10-30'), "
                    + "('dni5', 'num5', '2023-10-01', '2023-10-30'), "
                    + "('dni6', 'num6', '2023-10-01', '2023-10-30'), "
                    + "('dni7', 'num7', '2023-10-01', '2023-10-30'), "
                    + "('dni8', 'num8', '2023-10-01', '2023-10-30'), "
                    + "('dni9', 'num9', '2023-10-01', '2023-10-30'), "
                    + "('dni10', 'num10', '2023-10-01', '2023-10-30')");
			           
            System.out.println("Tarea 9 DONE!");
		}catch(SQLException ex) {
			System.out.println(ex);
		}
	}
}
