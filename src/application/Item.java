package application;

import javafx.scene.control.ListView;

public class Item {
	private ListView<String> list;
	private String itemName;
	private String itemColor;
	private String itemPrice;
	private String itemSize;
	private String itemCondition;
	private String itemLocation;
	
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemColor() {
		return itemColor;
	}
	public void setItemColor(String itemColor) {
		this.itemColor = itemColor;
	}
	public String getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(String itemPrice) {
		this.itemPrice = itemPrice;
	}
	public String getItemSize() {
		return itemSize;
	}
	public void setItemSize(String itemSize) {
		this.itemSize = itemSize;
	}
	public String getItemCondition() {
		return itemCondition;
	}
	public void setItemCondition(String itemCondition) {
		this.itemCondition = itemCondition;
	}
	public String getItemLocation() {
		return itemLocation;
	}
	public void setItemLocation(String itemLocation) {
		this.itemLocation = itemLocation;
	}
	public ListView<String> getList() {
		return list;
	}
	public void setList(ListView<String> list) {
		this.list = list;
	}
}
