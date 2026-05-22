package crud;

import Controlador.ControladorCRUD;
import Modelo.ModeloCRUD;
import Vista.VistaGUICRUD;

public class CRUD {

    public static void main(String[] args) {

        VistaGUICRUD vista = new VistaGUICRUD();
        ModeloCRUD modelo = new ModeloCRUD();

        ControladorCRUD controlador = new ControladorCRUD(vista, modelo);

        vista.setVisible(true);
    }
}