package model.BOs;

import java.util.ArrayList;

import model.DAOs.CartDAO;
import model.DAOs.ClientDAO;
import model.entities.Cart;

public class CartBO {
	public static void addCartToData(Cart cart) {
		CartDAO.insert(cart.getQuantity(), cart.getFeature(), cart.getClientID(), cart.getProductID());
	}
	
	public static void addItemToCart(int clientID, int productID, int quantity) {
	    // Kiểm tra nếu sản phẩm đã tồn tại trong giỏ
	    if (CartDAO.checkItemExists(clientID, productID)) {
	        CartDAO.increaseQuantity(clientID, productID, quantity);
	    } else {
	        CartDAO.addNewItem(clientID, productID, quantity);
	    }
	}


	public static ArrayList<Cart> getAllItemsCart() {

		return CartDAO.findAll();
	}

	public static int getNumItemCartByClient(int clientID) {

		return CartDAO.countByClientId(clientID);
	}

	public static ArrayList<Cart> getItemsCartByClient(int clientID) {

		return CartDAO.findByClientId(clientID);
	}

	public static void deleteItemInCart(int cartID) {
		CartDAO.deleteByCartId(cartID);
	}

	public static void decreaseItemInCart(int cartID, int quantity) {

		if (quantity == 1) {
			CartDAO.deleteByCartId(cartID);
		} else {
			CartDAO.updateQuantityByCartId(cartID, quantity - 1);
		}
	}

	public static void increaseItemInCart(int cartID, int quantity) {

		CartDAO.updateQuantityByCartId(cartID, quantity + 1);

	}

	public static void paymentInCart(int clientID, long money) {

		CartDAO.deleteByClientId(clientID);

		ClientDAO.decreaseMoneyByClientId(money, clientID);

	}
}
