//src\main\java\com\mycompany\sgdcliente\controller\RMIClient.java
package com.mycompany.sgdcliente.controller;

import com.mycompany.sgdcliente.service.RMIService;

public class RMIClient {
    private final RMIService rmiService;

    public RMIClient() {
        this.rmiService = new RMIService();
    }

    public void connectToServer() {
        rmiService.connect();
    }
}
