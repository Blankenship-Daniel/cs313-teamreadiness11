/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.byui.cs313.teamreadiness11;

import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Database
 * @author dblankenship
 */
public class Database {
    
    static Connection getConnection() throws Exception {
        Class.forName("org.postgresql.Driver");

        URI dbUri = new URI("postgres://leoqwnjypglsyh:cNO1pkz8-J9WChtNBG01rdz7Xe@ec2-54-235-183-5.compute-1.amazonaws.com:5432/d173p0s5e73ren");

        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();

        return DriverManager.getConnection(dbUrl, username, password);
    }
}
