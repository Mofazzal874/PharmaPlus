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


    //sob property update korar test eita
    @Test
    public void testComplexPropertyUpdates() {
        //abar sob property update korlam..instance ta ekhono same ache
        viewAllModel.setName("Bactin Ophthalmic Solution");
        viewAllModel.setGname("Ciprofloxacin 0.3%");
        viewAllModel.setPrice(35);
        viewAllModel.setDescription("Ciprofloxacin 0.3% Eye/Ear Drops is indicated for the treatment of infections caused by susceptible strains of the designated microorganisms...");
        viewAllModel.setImg_url("https://firebasestorage.googleapis.com/v0/b/projecto-a1b41.appspot.com/o/product_images%2F1700086071103_1000009602?alt=media&token=15391a9b-5575-4671-9135-b9317a75360b");
        viewAllModel.setType("health");
        viewAllModel.setDiscount("Discount 10% Off");

        assertEquals("Bactin Ophthalmic Solution", viewAllModel.getName());
        assertEquals("Ciprofloxacin 0.3%", viewAllModel.getGname());
        assertEquals(35, viewAllModel.getPrice(), 0.0);
        assertEquals("Ciprofloxacin 0.3% Eye/Ear Drops is indicated for the treatment of infections caused by susceptible strains of the designated microorganisms...", viewAllModel.getDescription());
        assertEquals("https://firebasestorage.googleapis.com/v0/b/projecto-a1b41.appspot.com/o/product_images%2F1700086071103_1000009602?alt=media&token=15391a9b-5575-4671-9135-b9317a75360b", viewAllModel.getImg_url());
        assertEquals("health", viewAllModel.getType());
        assertEquals("Discount 10% Off", viewAllModel.getDiscount());
    }


    //eikhane field null or empty thakle oitar test hobe

    @Test
    public void testNullAndEmptyVal() {
        //abar update korlam same instance
        viewAllModel.setName(null);
        viewAllModel.setGname("");
        viewAllModel.setPrice(0);
        viewAllModel.setDescription(null);
        viewAllModel.setImg_url("");
        viewAllModel.setType(null);
        viewAllModel.setDiscount("");

        assertNull(viewAllModel.getName());
        assertEquals("", viewAllModel.getGname());
        assertEquals(0, viewAllModel.getPrice(), 0.0);
        assertNull(viewAllModel.getDescription());
        assertEquals("", viewAllModel.getImg_url());
        assertNull(viewAllModel.getType());
        assertEquals("", viewAllModel.getDiscount());
    }

}
