package ui;

import controller.SaxController;

import java.nio.file.Path;

public class UI_leerSAX {
    public void leerSAX(){
        Path p = Path.of("src/main/resources/espacios.xml");
        SaxController saxController = new SaxController();
        System.out.println(saxController.leerXMLconSax(p));
    }


}
