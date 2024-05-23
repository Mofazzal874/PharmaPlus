package com.example.projecto.models;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ViewAllModelTest {

    private ViewAllModel viewAllModel;

    //setUp e proti test e new instance create kore nitechi
    @Before
    public void setUp() {
        ViewAllModel.resetInstance(); // Ensure a fresh instance for each test
        viewAllModel = ViewAllModel.getInstance();
        viewAllModel.setName("Napa Extra");
        viewAllModel.setGname("Paracetamol + Caffeine (500mg+65mg)");
        viewAllModel.setPrice(2.5);
        viewAllModel.setDescription("Napa® Extra is a mild analgesic and antipyretic...");
        viewAllModel.setImg_url("https://firebasestorage.googleapis.com/v0/.../napa-extra-500-mg-tablet.webp");
        viewAllModel.setType("medication");
        viewAllModel.setDiscount("Discount 10% Off");
    }

    //Ei test e check kortechi je instance duita same hoilo kina
    @Test
    public void testSingletonInstance() {
        ViewAllModel firstInstance = ViewAllModel.getInstance();
        ViewAllModel secondInstance = ViewAllModel.getInstance();
        assertEquals(firstInstance, secondInstance);
    }

    //sob eksathe check
    @Test
    public void testModelProperties() {
        assertEquals("Napa Extra", viewAllModel.getName());
        assertEquals("Paracetamol + Caffeine (500mg+65mg)", viewAllModel.getGname());
        assertEquals(2.5, viewAllModel.getPrice(), 0.0);
        assertEquals("Napa® Extra is a mild analgesic and antipyretic...", viewAllModel.getDescription());
        assertEquals("https://firebasestorage.googleapis.com/v0/.../napa-extra-500-mg-tablet.webp", viewAllModel.getImg_url());
        assertEquals("medication", viewAllModel.getType());
        assertEquals("Discount 10% Off", viewAllModel.getDiscount());
    }

    //individual field gula check kortechi
    @Test
    public void testModelPropertyUpdateName() {
        viewAllModel.setName("Napa Extend");
        assertEquals("Napa Extend", viewAllModel.getName());
    }
    @Test
    public void testModelPropertyUpdateGname() {
        viewAllModel.setGname("Paracetamol(600mg) + Caffeine (600mg+65mg)");
        assertEquals("Paracetamol(600mg) + Caffeine (600mg+65mg)", viewAllModel.getGname());
    }
    @Test
    public void testModelPropertyUpdatePrice() {
        viewAllModel.setPrice(3.0);
        assertEquals(3.0, viewAllModel.getPrice(), 0.0);
    }

    //eikhane check kortechi je instance reset/null korar por duita alada hoilo kina
    //alada hoile test pass korbe
    @Test
    public void testResetInstance() {
        ViewAllModel oldIns = ViewAllModel.getInstance();
        ViewAllModel.resetInstance();   //reset kore dilam
        ViewAllModel newIns = ViewAllModel.getInstance();  //notun ekta create korlam
        assertNotNull(newIns);  //notun zehetu create korchi , taile obosshoi null hobe na
        assertNotSame(oldIns, newIns);  //but ager tar instance same hoile test failed
    }
}
