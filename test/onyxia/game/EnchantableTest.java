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
public class EnchantableTest {
    
    public EnchantableTest() {
    }


    /**
     * Test of enchant method, of class Enchantable.
     */
    @Test
    public void testEnchant() {
        System.out.println("enchant");
        boolean expStatus = true;
        Enchantable axeToEnchant = new Enchantable("bat","a bat", null);
        //Enchantable items start as not enchanted.
        axeToEnchant.enchant(true);
        
        assertEquals(axeToEnchant.isEnchanted(), expStatus);
    }


}
