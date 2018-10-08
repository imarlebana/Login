package es.cice.demo.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;

@RestController
public class TestController {

    @RequestMapping( method = RequestMethod.POST, path = "/login")
    public String test(@RequestParam(name="user")  String user, @RequestParam(name="pass") String pass){
        String respuesta = null;
        respuesta = user + "  " + pass;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jcbd:mysql://localhos:8889/Login","root","root");

            Statement statement = connection.createStatement();
            ResultSet busqueda = statement.executeQuery("Select * FROM usuarios WHERE user ='" + user + "' AND pass = '" + pass + "'");

            if(busqueda.first()){
                respuesta = "usuario encontrado";
            }else{
                respuesta = "usuario y contrasenia no coindicen.";
            }


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return respuesta;
    }




}


