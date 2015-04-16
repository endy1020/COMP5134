package pos;

import java.util.ArrayList;
import java.util.List;

import pos.model.Decorator;
import pos.model.IceCream;
import pos.view.MainFrame;

public class Main
{

	public static void main(String[] args) {           

		MainFrame view = new MainFrame();
		
		IceCream c = new IceCream();
		c.setDescription("Cholocate");
		c.setPrice(20);
		IceCream c1 = new IceCream();
		c1.setDescription("Vanilla");
		c1.setPrice(20);
		
		List<IceCream> iceCreamList = new ArrayList<IceCream>();
		iceCreamList.add(c);
		iceCreamList.add(c1);
		
		Decorator d = new Decorator();
		d.setDescription("M&M");
		d.setPrice(5);
		Decorator d1 = new Decorator();
		d1.setDescription("Strawberry");
		d1.setPrice(4);
		
		List<IceCream> decoratorList = new ArrayList<IceCream>();
		decoratorList.add(d);
		decoratorList.add(d1);
		
		view.preSet(iceCreamList, decoratorList);
	}
}

