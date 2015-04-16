package pos.tester;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

import pos.controller.PosManager;
import pos.model.Decorator;
import pos.model.IceCream;

public class PosManagerTester extends TestCase{

	private PosManager pos;
	
	public void setUp(){
		pos = new PosManager();
	}
	
	public void testCalTotalPrice() {
		IceCream c = new IceCream();
		c.setDescription("Cholocate");
		c.setPrice(20);
		
		IceCream c1 = new IceCream();
		c1.setDescription("M&M");
		c1.setPrice(5);
		
		IceCream c2 = new IceCream();
		c2.setDescription("M&M");
		c2.setPrice(5.5);
		
		List<IceCream> selectionList = new ArrayList<IceCream>();
		selectionList.add(c);
		selectionList.add(c1);
		
		List<IceCream> selectionList2 = new ArrayList<IceCream>();
		selectionList2.add(c);
		selectionList2.add(c2);
		
		double totalPrice = 0;
		
		totalPrice = 25;
		assertEquals(totalPrice, pos.calTotalPrice(selectionList));
		totalPrice = 25.5;
		assertEquals(totalPrice, pos.calTotalPrice(selectionList2));
		
	}
	
	public void testValidIceCream() {
		int validInput = 0;
		int invalidDesc = 1;
		int invalidPrice = 2;
		
		//Valid
		assertEquals(validInput, pos.validIceCream("Green Tea", "30"));
		assertEquals(validInput, pos.validIceCream("Green Tea", "30.5"));
		assertEquals(validInput, pos.validIceCream("Green Tea 2", "0"));
		
		//InValid
		assertEquals(invalidDesc, pos.validIceCream("", ""));
		assertEquals(invalidDesc, pos.validIceCream("", "30"));
		assertEquals(invalidPrice, pos.validIceCream("Green Tea", "aaaaa"));
		assertEquals(invalidPrice, pos.validIceCream("Green Tea", ""));
	}
	
}
