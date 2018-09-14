/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import module.imageloader;

/**
 *
 * @author ZuraH
 */
public class controller implements Initializable {
    
    @Override
    public void initialize(java.net.URL location, java.util.ResourceBundle resources) 
    {
       il= new imageloader(grid);
    }
    @FXML private GridPane grid;
    imageloader il;
     public void gridClick(MouseEvent event)
    {
       // grid.setGridLinesVisible(true);
    }
}
