package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ModeloCRUD {

    public void crearBaseDatosYTablas() {
        crearBaseDatos();
        crearTablas();
    }

    private void crearBaseDatos() {
        String sql = "CREATE DATABASE IF NOT EXISTS Tienda";

        try (Connection con = Conexión.conectarServidor();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.executeUpdate();
            System.out.println("Base de datos creada correctamente.");

        } catch (SQLException e) {
            System.out.println("Error al crear la base de datos: " + e.getMessage());
        }
    }

    private void crearTablas() {
        String customers = """
            CREATE TABLE IF NOT EXISTS Customers (
                customer_id INT NOT NULL AUTO_INCREMENT,
                customer_name CHAR(50) NOT NULL,
                PRIMARY KEY (customer_id)
            )
        """;

        String orders = """
            CREATE TABLE IF NOT EXISTS Orders (
                order_id INT NOT NULL AUTO_INCREMENT,
                customer_id INT NOT NULL,
                order_date DATE NOT NULL,
                PRIMARY KEY (order_id),
                FOREIGN KEY (customer_id) REFERENCES Customers(customer_id)
            )
        """;

        String shipments = """
            CREATE TABLE IF NOT EXISTS Shipments (
                shipment_id INT NOT NULL AUTO_INCREMENT,
                order_id INT NOT NULL,
                shipment_date DATE NOT NULL,
                PRIMARY KEY (shipment_id),
                FOREIGN KEY (order_id) REFERENCES Orders(order_id)
            )
        """;

        try (Connection con = Conexión.conectarBD()) {

            con.prepareStatement(customers).executeUpdate();
            con.prepareStatement(orders).executeUpdate();
            con.prepareStatement(shipments).executeUpdate();

            System.out.println("Tablas creadas correctamente.");

        } catch (SQLException e) {
            System.out.println("Error al crear las tablas: " + e.getMessage());
        }
    }


    public void insertarCustomer(String nombre) {
        String sql = "INSERT INTO Customers (customer_name) VALUES (?)";

        try (Connection con = Conexión.conectarBD();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nombre);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al insertar customer: " + e.getMessage());
        }
    }

    public ResultSet listarCustomers() {
        String sql = "SELECT * FROM Customers";

        try {
            Connection con = Conexión.conectarBD();
            PreparedStatement ps = con.prepareStatement(sql);
            return ps.executeQuery();

        } catch (SQLException e) {
            System.out.println("Error al listar customers: " + e.getMessage());
            return null;
        }
    }

    public void actualizarCustomer(int id, String nombre) {
        String sql = "UPDATE Customers SET customer_name = ? WHERE customer_id = ?";

        try (Connection con = Conexión.conectarBD();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nombre);
            ps.setInt(2, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al actualizar customer: " + e.getMessage());
        }
    }

    public void eliminarCustomer(int id) {
        String sql = "DELETE FROM Customers WHERE customer_id = ?";

        try (Connection con = Conexión.conectarBD();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al eliminar customer: " + e.getMessage());
        }
    }
}