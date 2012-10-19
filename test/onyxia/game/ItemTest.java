/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package onyxia.game;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author michael
 */
public class ItemTest {
    
    public ItemTest() {
    }


    /**
     * Test of getName method, of class Item.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Item anItem = new Item("axe","this is an axe",null);
        
        String expResult = "axe";
        
        String result = anItem.getName();

        assertEquals(expResult, result);
    }

    /**
     * Test of setName method, of class Item.
     **/
    @Test
    public void testSetName() {
        System.out.println("setName");
        String expname = "sword";
        Item axeItem = new Item("axe","no desc", null);
        
        axeItem.setName("sword");
        assertEquals(expname, axeItem.getName());
    }

    
}
