package crud;

import Modelo.ModeloCRUD;

public class CRUD {
    public static void main(String[] args) {

        ModeloCRUD modelo = new ModeloCRUD();

        modelo.crearBaseDatosYTablas();
        modelo.insertarCustomer("Joana");

        VistaGUICRUD vista = new VistaGUICRUD();
        vista.setVisible(true);
        System.out.println("Prueba terminada");

    }
}
