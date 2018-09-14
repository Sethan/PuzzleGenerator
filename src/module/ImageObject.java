/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package module;

import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;

/**
 *
 * @author ZuraH
 */
public class ImageObject extends ImageView 
{
    int realX;
    int realY;
    public ImageObject(WritableImage wi, int x, int y)
    {
        super(wi);
        this.realX=x;
        this.realY=y;
    }
    
    public int getRealX(Node n)
    {
        return this.realX;
    }
    public int getRealY(Node n)
    {
        return this.realY;
    }
    
}
