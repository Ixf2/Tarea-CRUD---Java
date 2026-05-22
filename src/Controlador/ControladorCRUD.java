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

    public ControladorCRUD(
            VistaGUICRUD vista,
            ModeloCRUD modelo
    ) {

        this.vista = vista;
        this.modelo = modelo;

        modelo.crearBaseDatosYTablas();

        cargarCustomers();

        vista.getBInsertar()
                .addActionListener(
                        e -> insertarCustomer()
                );

        vista.getBActualizar()
                .addActionListener(
                        e -> actualizarCustomer()
                );

        vista.getBEliminar()
                .addActionListener(
                        e -> eliminarCustomer()
                );

        vista.getBLimpiar()
                .addActionListener(
                        e -> limpiarCustomer()
                );

        vista.getRegistrosCustomer()
                .addMouseListener(
                        new java.awt.event.MouseAdapter() {

                    public void mouseClicked(
                            java.awt.event.MouseEvent evt
                    ) {

                        seleccionarCustomer();

                    }

                });

        cargarOrders();

        vista.getBInsertar1()
                .addActionListener(
                        e -> insertarOrder()
                );

        vista.getBActualizar1()
                .addActionListener(
                        e -> actualizarOrder()
                );

        vista.getBEliminar1()
                .addActionListener(
                        e -> eliminarOrder()
                );

        vista.getBLimpiar1()
                .addActionListener(
                        e -> limpiarOrder()
                );

        vista.getRegistrosOrder()
                .addMouseListener(
                        new java.awt.event.MouseAdapter() {

                    public void mouseClicked(
                            java.awt.event.MouseEvent evt
                    ) {

                        seleccionarOrder();

                    }

                });

        cargarShipments();

        vista.getBInsertar2()
                .addActionListener(
                        e -> insertarShipment()
                );

        vista.getBActualizar2()
                .addActionListener(
                        e -> actualizarShipment()
                );

        vista.getBEliminar2()
                .addActionListener(
                        e -> eliminarShipment()
                );

        vista.getBLimpiar2()
                .addActionListener(
                        e -> limpiarShipment()
                );

        vista.getRegistrosShipment()
                .addMouseListener(
                        new java.awt.event.MouseAdapter() {

                    public void mouseClicked(
                            java.awt.event.MouseEvent evt
                    ) {

                        seleccionarShipment();

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

    private void cargarOrders() {

        DefaultTableModel tabla
                = (DefaultTableModel) vista.getRegistrosOrder().getModel();

        tabla.setRowCount(0);

        ResultSet rs
                = modelo.listarOrders();

        try {

            while (rs != null && rs.next()) {

                tabla.addRow(new Object[]{
                    rs.getInt("order_id"),
                    rs.getInt("customer_id"),
                    rs.getString("order_date")

                });

            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    vista,
                    e.getMessage()
            );

        }

    }

    private void insertarOrder() {

        modelo.insertarOrder(
                Integer.parseInt(
                        vista.getTIDCustomer2()
                                .getText()
                ),
                vista.getTFechaPedido()
                        .getText()
        );

        limpiarOrder();
        cargarOrders();

    }

    private void actualizarOrder() {

        if (vista.getTIDOrder().getText().isEmpty()) {

            JOptionPane.showMessageDialog(
                    vista,
                    "Selecciona un Order"
            );

            return;
        }

        modelo.actualizarOrder(
                Integer.parseInt(
                        vista.getTIDOrder()
                                .getText()
                ),
                Integer.parseInt(
                        vista.getTIDCustomer2()
                                .getText()
                ),
                vista.getTFechaPedido()
                        .getText()
        );

        limpiarOrder();
        cargarOrders();

    }

    private void eliminarOrder() {

        modelo.eliminarOrder(
                Integer.parseInt(
                        vista.getTIDOrder()
                                .getText()
                )
        );

        limpiarOrder();
        cargarOrders();

    }

    private void limpiarOrder() {

        vista.getTIDOrder()
                .setText("");

        vista.getTIDCustomer2()
                .setText("");

        vista.getTFechaPedido()
                .setText("");

    }

    private void seleccionarOrder() {

        int fila
                = vista.getRegistrosOrder()
                        .getSelectedRow();

        if (fila >= 0) {

            vista.getTIDOrder()
                    .setText(
                            vista.getRegistrosOrder()
                                    .getValueAt(
                                            fila,
                                            0
                                    ).toString()
                    );

            vista.getTIDCustomer2()
                    .setText(
                            vista.getRegistrosOrder()
                                    .getValueAt(
                                            fila,
                                            1
                                    ).toString()
                    );

            vista.getTFechaPedido()
                    .setText(
                            vista.getRegistrosOrder()
                                    .getValueAt(
                                            fila,
                                            2
                                    ).toString()
                    );

        }

    }

    private void cargarShipments() {

        DefaultTableModel tabla
                = (DefaultTableModel) vista.getRegistrosShipment()
                        .getModel();

        tabla.setRowCount(0);

        ResultSet rs
                = modelo.listarShipments();

        try {

            while (rs != null && rs.next()) {

                tabla.addRow(new Object[]{
                    rs.getInt("shipment_id"),
                    rs.getInt("order_id"),
                    rs.getString("shipment_date")

                });

            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    vista,
                    e.getMessage()
            );

        }

    }

    private void insertarShipment() {

        modelo.insertarShipment(
                Integer.parseInt(
                        vista.getTIDOrders2()
                                .getText()
                ),
                vista.getTFechaEnvio()
                        .getText()
        );

        limpiarShipment();
        cargarShipments();

    }

    private void actualizarShipment() {

        modelo.actualizarShipment(
                Integer.parseInt(
                        vista.getTIDShipment()
                                .getText()
                ),
                Integer.parseInt(
                        vista.getTIDOrders2()
                                .getText()
                ),
                vista.getTFechaEnvio()
                        .getText()
        );

        limpiarShipment();
        cargarShipments();

    }

    private void eliminarShipment() {

        modelo.eliminarShipment(
                Integer.parseInt(
                        vista.getTIDShipment()
                                .getText()
                )
        );

        limpiarShipment();
        cargarShipments();

    }

    private void limpiarShipment() {

        vista.getTIDShipment()
                .setText("");

        vista.getTIDOrders2()
                .setText("");

        vista.getTFechaEnvio()
                .setText("");

    }

    private void seleccionarShipment() {

        int fila
                = vista.getRegistrosShipment()
                        .getSelectedRow();

        if (fila >= 0) {

            vista.getTIDShipment()
                    .setText(
                            vista.getRegistrosShipment()
                                    .getValueAt(
                                            fila,
                                            0
                                    ).toString()
                    );

            vista.getTIDOrders2()
                    .setText(
                            vista.getRegistrosShipment()
                                    .getValueAt(
                                            fila,
                                            1
                                    ).toString()
                    );

            vista.getTFechaEnvio()
                    .setText(
                            vista.getRegistrosShipment()
                                    .getValueAt(
                                            fila,
                                            2
                                    ).toString()
                    );

        }

    }

}
