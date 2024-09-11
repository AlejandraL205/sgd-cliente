//src\main\java\com\mycompany\sgdcliente\service\RMIService.java
package com.mycompany.sgdcliente.service;

import java.rmi.Naming;

public class RMIService {
    public void connect() {
        try {
            // Implement RMI connection logic
            Naming.lookup("rmi://localhost:1099/SGDService");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
