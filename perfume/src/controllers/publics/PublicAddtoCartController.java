package controllers.publics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import daos.PerfumeDao;
import models.Item;
import models.Order;
import models.Perfume;

public class PublicAddtoCartController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PublicAddtoCartController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession sessionUserIF = request.getSession();
		if (sessionUserIF.getAttribute("userInfor") == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		PerfumeDao productDao = new PerfumeDao();
		int quantity = 1;
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
			Perfume product = productDao.viewsID(id);
			if (product != null) {
				if (request.getParameter("quantity") != null) {
					quantity = Integer.parseInt(request.getParameter("quantity"));
				}
				HttpSession session = request.getSession();
				if (session.getAttribute("order") == null) {
				    Order order = new Order();
				    List<Item> listItems = new ArrayList<Item>();
				    Item item = new Item();
				    item.setId(1); // Gán ID khởi đầu là 1
				    item.setQuantity(quantity);
				    item.setProduct(product);
				    item.setPrice(product.getMoney());
				    listItems.add(item);
				    order.setItem(listItems);
				    session.setAttribute("order", order);
				} else {
				    Order order = (Order) session.getAttribute("order");
				    List<Item> listItems = order.getItem();
				    boolean check = false;
				    for (Item item : listItems) {
				        if (item.getProduct().getId() == product.getId()) {
				            item.setQuantity(item.getQuantity() + quantity);
				            check = true;
				        }
				    }
				    if (check == false) {
				        Item item = new Item();
				        // Gán ID dựa trên kích thước của danh sách hiện tại
				        item.setId(listItems.size() + 1);
				        item.setQuantity(quantity);
				        item.setProduct(product);
				        item.setPrice(product.getMoney());
				        listItems.add(item);
				    }
				    session.setAttribute("order", order);
				}

			}
			response.sendRedirect(request.getContextPath() + "/order");
		} catch (NumberFormatException e) {
			RequestDispatcher rd = request.getRequestDispatcher("/views/error/index.jsp");
			rd.forward(request, response);
			return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    int idOrder;
	    if (request.getParameter("idOrder") != null) {
	        try {
	            idOrder = Integer.parseInt(request.getParameter("idOrder"));
	            HttpSession session = request.getSession();
	            Order order = (Order) session.getAttribute("order");
	            if (order != null) {
	                List<Item> listItems = order.getItem();
	                // Xóa sản phẩm dựa trên idOrder
	                listItems.removeIf(item -> item.getId() == idOrder);
	                // Cập nhật lại session
	                order.setItem(listItems);
	                session.setAttribute("order", order);
	            }
	        } catch (NumberFormatException e) {
	            e.printStackTrace();
	        }
	    }
	    response.sendRedirect(request.getContextPath() + "/order");
	}

}
