package controller;

import models.Espacio;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class SaxController {
    public String leerXMLconSax(Path p){
        StringBuilder mensaje = new StringBuilder();
        if (Files.isReadable(p)){
            //creamos las instancias de SAX
            SAXParserFactory saxPF= SAXParserFactory.newInstance();
            EspacioHandler handler = new EspacioHandler();
            ArrayList<Espacio> espacios = new ArrayList<Espacio>();
            try {
                SAXParser parser = saxPF.newSAXParser();
                parser.parse(p.toFile(),handler);
                espacios = handler.getEspacios();
                //para comprobar que se han cargado bien los coches del XML en mis objetos
                for (Espacio e : espacios){
                    mensaje.append(e.getNombre()).append(". Tipo: ").append(e.getTipo()).append("\n");
                }
            } catch (ParserConfigurationException e) {
                throw new RuntimeException(e);
            } catch (SAXException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else{
            mensaje.append("No se puede leer el archivo");
        }
        return mensaje.toString();
    }

}
