/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarys;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.jdom2.Document;         // |
import org.jdom2.Element;          // |\ Librerías
import org.jdom2.JDOMException;    // |/ JDOM
import org.jdom2.input.SAXBuilder; // |

/**
 *
 * @author Angel CM <angelvcuenca at gmail.com>
 */
public class web_services_class {

    //public static String dominio = "";

    /**
     *
     */
//    public static String dominio = "http://localhost/billingsof_core/";
    public static String dominio;

    public void Obtener_data_XML() {
       // System.out.println("- WS Enviado -");
        SAXBuilder builder = new SAXBuilder();
        File xmlFile = new File("xml/datos.xml");
        try {
            Document document = (Document) builder.build(xmlFile);
            Element rootNode = document.getRootElement();
            List list = rootNode.getChildren("tabla");
            for (int i = 0; i < list.size(); i++) {
                Element tabla = (Element) list.get(i);
                String nombreTabla = tabla.getAttributeValue("nombre");
                List lista_campos = tabla.getChildren();
                for (int j = 0; j < lista_campos.size(); j++) {
                    Element campo = (Element) lista_campos.get(j);
                    dominio = campo.getChildTextTrim("dominio");
                }
            }
        } catch (IOException io) {
            System.out.println(io.getMessage());
        } catch (JDOMException jdomex) {
            System.out.println(jdomex.getMessage());
        }
    }

    public void recibe(String codigoPro, String nombrePro, String codBarras3) {
        Obtener_data_XML();
        String address = dominio;
        String a = "aaa" + address;
        System.out.println("-->" + codigoPro + "/url->" + address);
    }
    
    public String descargar_tipo_cliente ()throws MalformedURLException, IOException{
        Obtener_data_XML();
        String address = dominio + ("pventamanual/rest/descargar_tipo_cliente");
        URL URL = new URL(address);
        HttpURLConnection connection = (HttpURLConnection) URL.openConnection();
        connection.setDoOutput(true);
        connection.setRequestProperty("Accept", "application/json");
        connection.setRequestMethod("GET");
        connection.setReadTimeout(10 * 1000);

        BufferedReader br = new BufferedReader(new InputStreamReader((connection.getInputStream())));

        String output;
        String texto = "";
        while ((output = br.readLine()) != null) {
            texto = output;
        }
        return texto;
    }
    public String descargar_cargosempleado ()throws MalformedURLException, IOException{
        Obtener_data_XML();
        String address = dominio + ("pventamanual/rest/descargar_cargosempleado");
        URL URL = new URL(address);
        HttpURLConnection connection = (HttpURLConnection) URL.openConnection();
        connection.setDoOutput(true);
        connection.setRequestProperty("Accept", "application/json");
        connection.setRequestMethod("GET");
        connection.setReadTimeout(10 * 1000);

        BufferedReader br = new BufferedReader(new InputStreamReader((connection.getInputStream())));

        String output;
        String texto = "";
        while ((output = br.readLine()) != null) {
            texto = output;
        }
        return texto;
    }
    public String descargar_departamentos ()throws MalformedURLException, IOException{
        Obtener_data_XML();
        String address = dominio + ("pventamanual/rest/descargar_departamentos");
        URL URL = new URL(address);
        HttpURLConnection connection = (HttpURLConnection) URL.openConnection();
        connection.setDoOutput(true);
        connection.setRequestProperty("Accept", "application/json");
        connection.setRequestMethod("GET");
        connection.setReadTimeout(10 * 1000);

        BufferedReader br = new BufferedReader(new InputStreamReader((connection.getInputStream())));

        String output;
        String texto = "";
        while ((output = br.readLine()) != null) {
            texto = output;
        }
        return texto;
    }
    
    public String descargar_empleado ()throws MalformedURLException, IOException{
        Obtener_data_XML();
        String address = dominio + ("pventamanual/rest/descargar_empleados");
        URL URL = new URL(address);
        HttpURLConnection connection = (HttpURLConnection) URL.openConnection();
        connection.setDoOutput(true);
        connection.setRequestProperty("Accept", "application/json");
        connection.setRequestMethod("GET");
        connection.setReadTimeout(10 * 1000);

        BufferedReader br = new BufferedReader(new InputStreamReader((connection.getInputStream())));

        String output;
        String texto = "";
        while ((output = br.readLine()) != null) {
            texto = output;
        }
        return texto;
    }
     
    public String descargar_cliente ()throws MalformedURLException, IOException{
        Obtener_data_XML();
        String address = dominio + ("pventamanual/rest/descargar_clientes");
        URL URL = new URL(address);
        HttpURLConnection connection = (HttpURLConnection) URL.openConnection();
        connection.setDoOutput(true);
        connection.setRequestProperty("Accept", "application/json");
        connection.setRequestMethod("GET");
        connection.setReadTimeout(10 * 1000);

        BufferedReader br = new BufferedReader(new InputStreamReader((connection.getInputStream())));

        String output;
        String texto = "";
        while ((output = br.readLine()) != null) {
            texto = output;
        }
        return texto;
    }
    
}
