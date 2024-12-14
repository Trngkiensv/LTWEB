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

import model.BOs.CommentBO;
import model.BOs.OwnerShopBO;
import model.BOs.ProductBO;
import model.entities.Client;
import model.entities.Comment;
import model.entities.Product;

@WebServlet(urlPatterns = "/Trangchu/Product")
public class DetailProductServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	ArrayList<Product> productList = new ArrayList<Product>();

//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		req.setCharacterEncoding("UTF-8");
//		resp.setCharacterEncoding("UTF-8");
//		resp.setContentType("text/html; charset=UTF-8");
//		int id = Integer.parseInt(req.getParameter("id"));
//		Product product = ProductBO.getProductByID(id);
//		req.setAttribute("product", product);
//		req.setAttribute("shop", OwnerShopBO.getShopByID(product.getShopID()));
//		req.setAttribute("comments", CommentBO.getCommentsByProductID(id));
//		RequestDispatcher dispatcher = req.getRequestDispatcher("/Detail.jsp");
//		dispatcher.forward(req, resp);
//	}
//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//	    req.setCharacterEncoding("UTF-8");
//	    resp.setCharacterEncoding("UTF-8");
//	    resp.setContentType("text/html; charset=UTF-8");
//	    HttpSession ses=req.getSession();
//	    Client client=(Client)ses.getAttribute("user");
//	    String comment=req.getParameter("comment");
//	    int id= Integer.parseInt(req.getParameter("id"));
//
//	    CommentBO.addCommentToData(new Comment(comment,client.getId(),id));
//		resp.sendRedirect(req.getContextPath()+"/Trangchu/Product");
//	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    req.setCharacterEncoding("UTF-8");
	    resp.setCharacterEncoding("UTF-8");
	    resp.setContentType("text/html; charset=UTF-8");

	    int id = Integer.parseInt(req.getParameter("id")); // Lấy ID từ request
	    Product product = ProductBO.getProductByID(id); // Lấy đối tượng sản phẩm
	    req.setAttribute("product", product); // Đặt đối tượng vào request
	    req.setAttribute("shop", OwnerShopBO.getShopByID(product.getShopID()));
	    req.setAttribute("comments", CommentBO.getCommentsByProductID(id));

	    RequestDispatcher dispatcher = req.getRequestDispatcher("/Detail.jsp");
	    dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    req.setCharacterEncoding("UTF-8");
	    resp.setCharacterEncoding("UTF-8");
	    resp.setContentType("text/html; charset=UTF-8");

	    HttpSession session = req.getSession();

	    // Lấy hành động từ request
	    String action = req.getParameter("action");

	    if ("addToCart".equals(action)) {
	        // Thêm vào giỏ hàng
	        int productId = Integer.parseInt(req.getParameter("productId"));
	        ArrayList<Product> cart = (ArrayList<Product>) session.getAttribute("cart");
	        if (cart == null) {
	            cart = new ArrayList<>();
	        }

	        // Lấy thông tin sản phẩm từ ProductBO
	        Product product = ProductBO.getProductByID(productId);
	        if (product != null) {
	            cart.add(product);
	        }

	        // Lưu giỏ hàng lại trong session
	        session.setAttribute("cart", cart);

	        // Chuyển hướng lại trang hiện tại hoặc trang giỏ hàng
	        resp.sendRedirect(req.getHeader("referer"));
	    }
	}

}
