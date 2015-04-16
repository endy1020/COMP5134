package pos.controller;

import java.util.List;

import pos.model.IceCream;

public class PosManager{

	public static double calTotalPrice(List<IceCream> selectionList){

		double price = 0;

		for(IceCream i : selectionList ){
			price += i.getPrice();
		}

		return price;
	}
	
	public static int validIceCream(String desc, String price){
		if( desc == null || desc.isEmpty() ){
			return 1;
		}
		double d = 0;
		try{
			d = Double.parseDouble(price);
		}catch(Exception e){
			return 2;
		}
		if( d < 0 ){
			return 2;
		}
		return 0;
	}
	
}