/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package module;

import java.awt.Point;
import javafx.scene.image.Image;
import java.io.FileInputStream;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.GridPane;
import javax.swing.JButton;
import javax.swing.JFileChooser;

/**
 *
 * @author ZuraH
 */
public class imageloader {
    int lastX=-1;
    int lastY=-1;
    int gridSize;
    Image image;
    Point[][] logicTable;
    public imageloader(GridPane grid)
    {
        try
        {
            
            JButton open = new JButton();
            JFileChooser fc = new JFileChooser();
            //FileNameExtensionFilter filter = new FileNameExtensionFilter("jpg", "png");
           // fc.setFileFilter(filter);
            
            if(fc.showOpenDialog(open)==JFileChooser.APPROVE_OPTION)
            {
                gridSize=2;
                String fullPath = fc.getSelectedFile().getAbsolutePath();
                image = new Image(new FileInputStream(fullPath));
                double width=image.getWidth();
                double height=image.getHeight();
                boolean[][] arr = new boolean[gridSize][gridSize];
                logicTable=new Point[gridSize][gridSize];
                for(double x=0; x<gridSize;x++)
                {
                    for(double y=0; y<gridSize;y++)
                    {
                        WritableImage croppedImage = new WritableImage(image.getPixelReader(), (int)(x*width/gridSize), (int)(y*height/gridSize),(int)(width/gridSize), (int)(height/gridSize));
                        
                        while(true)
                        {
                            int nx = (int)(Math.random()*gridSize);
                            int ny = (int)(Math.random()*gridSize);
                            if(arr[nx][ny]==false)
                            {
                                arr[nx][ny]=true;
                                ImageView iv = new ImageView(croppedImage);
                                iv.setOnMouseClicked(e -> 
                                {
                                    
                                    imageSwitch(grid, nx, ny);
                                });
                                this.logicTable[nx][ny]= new Point((int)x,(int)y);
                                grid.add(iv, nx, ny);
                                break;
                            }                        
                        }                    
                    }
                }
            }  
            else
            {
                Platform.exit();
            }
        }
        catch(Exception e)
        {
            System.err.println("Error reading data");
            Platform.exit();
        }
    }
    
    public void imageSwitch(GridPane grid, int x, int y)
    {
        int nx=lastX;
        int ny=lastY;
        if(nx!=-1)
        {
            ObservableList<Node> childrens = grid.getChildren();
            for (Node node : childrens) 
            {
                if(GridPane.getRowIndex(node) == y && GridPane.getColumnIndex(node) == x) 
                {
                    
                    for (Node node2 : childrens) 
                    {
                        if(GridPane.getRowIndex(node2) == ny && GridPane.getColumnIndex(node2) == nx) 
                        {
                            
                            GridPane.setRowIndex(node,20);
                            GridPane.setColumnIndex(node,20);
                            
                            GridPane.setRowIndex(node2,y);
                            GridPane.setColumnIndex(node2,x);
                            
                            GridPane.setRowIndex(node, ny);
                            GridPane.setColumnIndex(node, nx);
                            node.setOnMouseClicked(e -> 
                            {
                                    imageSwitch(grid, nx, ny);
                            });
                            node2.setOnMouseClicked(e -> 
                            {
                                    imageSwitch(grid, x, y);
                            });    
                            Point temp=this.logicTable[nx][ny];
                            this.logicTable[nx][ny]=this.logicTable[x][y];
                            this.logicTable[x][y]=temp;
                            break;
                        }
                    }
                    break;
                }
            }
            lastX=-1;
            lastY=-1;
            this.check(grid);
        }
        else
        {
            lastX=x;
            lastY=y;
        }
    }
    public String check(GridPane grid)
    {
        boolean flag=true;
        for(int x=0;x<gridSize;x++)
        {
            for(int y=0;y<gridSize;y++)
            {
                if(this.logicTable[x][y].x!=x||this.logicTable[x][y].y!=y)
                {
                    flag=false;
                }
            }
        }
        if(flag)
        {
            grid.setOpacity(0.3);
        }
        return "100%";
    }
}
