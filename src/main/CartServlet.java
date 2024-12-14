package controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.BOs.CartBO;
import model.entities.Cart;
import model.entities.Client;

@WebServlet("/Trangchu/GioHang")
public class CartServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    req.setCharacterEncoding("UTF-8");
	    resp.setCharacterEncoding("UTF-8");
	    resp.setContentType("text/html; charset=UTF-8");
	    String actionCart = req.getParameter("actionCart");

	    HttpSession session = req.getSession();
	    Client client = (Client) session.getAttribute("user");
	    if (client == null) {
	        System.out.println("Client is not logged in!");
	        resp.sendRedirect(req.getContextPath() + "/login");
	        return;
	    } else {
	        System.out.println("Client ID: " + client.getId());
	    }

	    if (actionCart != null) {
	        int cartID;
	        int quantity;
	        switch (actionCart) {
	            case "add":
	                int productID = Integer.parseInt(req.getParameter("productID"));
	                quantity = Integer.parseInt(req.getParameter("quantity"));
	                CartBO.addItemToCart(client.getId(), productID, quantity);
	                break;
	            case "remove":
	                cartID = Integer.parseInt(req.getParameter("cartID"));
	                CartBO.deleteItemInCart(cartID);
	                break;
	            case "plus":
	                cartID = Integer.parseInt(req.getParameter("cartID"));
	                quantity = Integer.parseInt(req.getParameter("quantity"));
	                CartBO.increaseItemInCart(cartID, quantity);
	                break;
	            case "minus":
	                cartID = Integer.parseInt(req.getParameter("cartID"));
	                quantity = Integer.parseInt(req.getParameter("quantity"));
	                CartBO.decreaseItemInCart(cartID, quantity);
	                break;
	        }
	    }

	    // Lấy danh sách giỏ hàng và truyền vào request
	    ArrayList<Cart> itemsCartList = CartBO.getItemsCartByClient(client.getId());
	    req.setAttribute("itemsCartList", itemsCartList);

	    // Chuyển hướng đến trang giỏ hàng
	    RequestDispatcher dispatcher = req.getRequestDispatcher("/Pages/ActionDataPage/Cart.jsp");
	    dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		HttpSession ses = req.getSession();
		Client client = (Client) ses.getAttribute("user");
		long totalMoney = Long.parseLong(req.getParameter("totalMoney"));
		CartBO.paymentInCart(client.getId(), totalMoney);
		resp.sendRedirect(req.getContextPath() + "/Trangchu/GioHang");
	}
}