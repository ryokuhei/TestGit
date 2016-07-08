package Servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.UserBean;
import Model.DAO.UserDAO;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// エンコード設定
		request.setCharacterEncoding("Windows-31J");
		response.setCharacterEncoding("Windows-31J");

		RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// エンコード設定
		request.setCharacterEncoding("Windows-31J");
		response.setCharacterEncoding("Windows-31J");
		// パラメータ取得
		String action = request.getParameter("ACTION");
		// 遷移先URL
		String url = null;
		// 条件分岐
		if("ログイン".equals(action)) {
			// DAO,Beanをインスタンス化
			UserDAO dao = new UserDAO();
			UserBean bean = null;

			String id = request.getParameter("ID");
			String password = request.getParameter("PASSWORD");

			try {
				bean = dao.loginCheck(id, password);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(bean.getUserId() == null) {
				url = "loginFailure.jsp";
			} else {
				HttpSession session = request.getSession();
				session.setAttribute("USER", bean);
				url = "menu.jsp";
			}
		}

		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);

	}


}
