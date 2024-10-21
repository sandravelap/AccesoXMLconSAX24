package controller;

import models.Espacio;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

public class EspacioHandler extends DefaultHandler {
    //variable donde almacenar los espacios
    ArrayList<Espacio> espacios = new ArrayList<>();
    //espacio auxiliar para leer los espacios del XML:
    Espacio espacioAux;
    //para almacenar el texto contenido en un nodo texto
    private StringBuilder buffer = new StringBuilder();

    public ArrayList<Espacio> getEspacios() {return espacios;}

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        switch(qName){
            case "espacios", "telefono":
                break;
            case "espacio":
                espacioAux = new Espacio();
                espacioAux.setTipo(attributes.getValue("tipo"));
                break;
            case "nombre", "municipios", "country":
                buffer.delete(0,buffer.length());
                break;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch(qName){
            case "espacios", "telefono":
                break;
            case "espacio":
                espacios.add(espacioAux);
                break;
            case "nombre":
                espacioAux.setNombre(buffer.toString());
                break;
            case "municipios":
                espacioAux.setMunicipios(buffer.toString());
                break;
            case "country":
                espacioAux.setPais(buffer.toString());
                break;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        buffer.append(ch, start, length);
    }
}
