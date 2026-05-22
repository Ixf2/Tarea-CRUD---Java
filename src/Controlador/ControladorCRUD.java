package Controlador;

import Modelo.ModeloCRUD;
import Vista.VistaGUICRUD;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ControladorCRUD {

    private VistaGUICRUD vista;
    private ModeloCRUD modelo;

    public ControladorCRUD(VistaGUICRUD vista, ModeloCRUD modelo) {
        this.vista = vista;
        this.modelo = modelo;

        modelo.crearBaseDatosYTablas();

        cargarCustomers();

        vista.getBInsertar().addActionListener(e -> insertarCustomer());
        vista.getBActualizar().addActionListener(e -> actualizarCustomer());
        vista.getBEliminar().addActionListener(e -> eliminarCustomer());
        vista.getBLimpiar().addActionListener(e -> limpiarCustomer());

        vista.getRegistrosCustomer().addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seleccionarCustomer();
            }
        });
    }

    private void cargarCustomers() {
        DefaultTableModel tabla = (DefaultTableModel) vista.getRegistrosCustomer().getModel();
        tabla.setRowCount(0);

        ResultSet rs = modelo.listarCustomers();

        try {
            while (rs != null && rs.next()) {
                tabla.addRow(new Object[]{
                    rs.getInt("customer_id"),
                    rs.getString("customer_name")
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(vista, "Error al cargar Customers: " + e.getMessage());
        }
    }

    private void insertarCustomer() {
        String nombre = vista.getTNombre().getText();

        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Escribe un nombre");
            return;
        }

        modelo.insertarCustomer(nombre);
        limpiarCustomer();
        cargarCustomers();
    }

    private void actualizarCustomer() {
        if (vista.getTIDCustomer().getText().isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Selecciona un Customer");
            return;
        }

        int id = Integer.parseInt(vista.getTIDCustomer().getText());
        String nombre = vista.getTNombre().getText();

        modelo.actualizarCustomer(id, nombre);
        limpiarCustomer();
        cargarCustomers();
    }

    private void eliminarCustomer() {
        if (vista.getTIDCustomer().getText().isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Selecciona un Customer");
            return;
        }

        int id = Integer.parseInt(vista.getTIDCustomer().getText());

        modelo.eliminarCustomer(id);
        limpiarCustomer();
        cargarCustomers();
    }

    private void limpiarCustomer() {
        vista.getTIDCustomer().setText("");
        vista.getTNombre().setText("");
        vista.getRegistrosCustomer().clearSelection();
    }

    private void seleccionarCustomer() {
        int fila = vista.getRegistrosCustomer().getSelectedRow();

        if (fila >= 0) {
            vista.getTIDCustomer().setText(
                    vista.getRegistrosCustomer().getValueAt(fila, 0).toString()
            );

            vista.getTNombre().setText(
                    vista.getRegistrosCustomer().getValueAt(fila, 1).toString()
            );
        }
    }
}