package net.camillasatte.mynewhome.model;

import java.util.ArrayList;
import java.util.List;

public class Status {
	
	private static List<String> statusList = new ArrayList<String>();
	
	public static List<String> getStatusList() {
		statusList.add("Purchased");
		statusList.add("Wishlist");
		statusList.add("Pending");
		statusList.add("Order ASAP");
		statusList.add("Wishy Wishlist");
		statusList.add("Have it");
		statusList.add("To return ASAP");
		return statusList;
	}
}
