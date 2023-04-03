package org.example;

import Modelos.Usuario;
import Repositorios.UsuarioRepository;
import java.sql.SQLException;
import java.util.Scanner;

public class Main{
    private static String opc = "";
    private static Scanner scn;
    public static void main(String[] args) throws SQLException {
        UsuarioRepository repo = new UsuarioRepository();
        while(opc.equals("5")){
            System.out.println("---------Bienvenido al repositorio de Usuarios---------");
            System.out.println("Que deseas hacer? \n\n1. Insertar Nuevo Usuario\n2. Actualizar un Usuario" +
                    "\n3. Eliminar Usuario\n4. Listar Usuarios\n5. SALIR DEL PROGRAMA");
            opc = scn.nextLine();
            switch(opc){
                case "1" -> {
                    System.out.println("\nIngrese los datos del usuario separados por coma en este orden: username, email, password");
                    String datos = scn.nextLine();
                    String[] datosA = datos.split(",");
                    if(datosA.length==3){
                        Usuario u = new Usuario(0,datosA[0],datosA[1],datosA[2]);
                        System.out.println("Filas afectadas en la base de datos: " + repo.agregar(u));
                    }else{
                        System.out.println("\nLos datos ingresados son incorrectos :)");
                    }
                }
                case "2" -> {
                    System.out.println("\nIngrese los datos del usuario separados por coma en este orden: id, username, email, password");
                    String datos = scn.nextLine();
                    String[] datosA = datos.split(",");
                    Usuario u = validar(datosA);
                    if(u!=null){
                        System.out.println("Filas afectadas en la base de datos: " + repo.actualizar(u));
                    }else{
                        System.out.println("\nLos datos ingresados son incorrectos :)");
                    }
                }
                case "3" -> {
                    System.out.println("\nIngrese el numero de id del usuario que quiere eliminar...");
                    try{
                        int x = Integer.parseInt(scn.nextLine());
                        System.out.println("\nFilas afectadas en la base de datos: " + repo.eliminar(x));
                    }catch(Exception e) {
                        System.out.println("\nIngrese datos validos");
                    }
                }
                case "4" -> {
                    System.out.println("\nUsuarios presentes en la base de datos: ");
                    repo.listar().stream().forEach(System.out::println);
                }
                case "5" -> {
                    System.out.println("\nADIOS!!");
                    repo.getConection().close();
                }
                default ->
                        System.out.println("\nLo que ustede ingresó no es una opcion :)");
            }
        }
    }
    public static Usuario validar(String[] a){
        int x;
        if(a.length!=4) return null;
        try{
            x = Integer.parseInt(a[0]);
        }catch(Exception e){
            return null;
        }
        return new Usuario(x,a[1],a[2],a[3]);
    }
}