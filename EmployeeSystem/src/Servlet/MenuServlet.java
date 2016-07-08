package Servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.EmployeeBean;
import Model.DAO.EmployeeDAO;

/**
 * Servlet implementation class MenuServlet
 */
public class MenuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MenuServlet() {
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


		HttpSession session = request.getSession();
		session.invalidate();

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
		String url = null;
		String action = request.getParameter("ACTION");


		if("一覧表示メニュー".equals(action)) {
			EmployeeDAO dao = new EmployeeDAO();
			List<EmployeeBean> list = null;
			try {
				list = dao.EmployeeDisplay();
			} catch (Exception e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
			request.setAttribute("LIST", list);
			url = "list.jsp";



		}else if("従業員登録メニュー".equals(action)) {

			EmployeeDAO dao = new EmployeeDAO();
			List<String> list = null;
			try {
				list = dao.getAllSectionName();
			} catch (Exception e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
			request.setAttribute("SECTION_NAME", list);
			url = "registration.jsp";




		} else if("登録".equals(action)) {
			// 各種インスタンス化
			EmployeeDAO dao = new EmployeeDAO();
			EmployeeBean bean = new EmployeeBean();

			int result = 0;
			List<String> message = null;

			try {
				bean.setAll(request);
			} catch (Exception e) {
				e.printStackTrace();
			}
			message = bean.getError();
			if(message.size() == 0) {
				url = "registrationSuccess.jsp";
				try {
					result = dao.EmployeeInsert(bean);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				url = "registrationFilure.jsp";
			}
			request.setAttribute("MESSAGE", message);

		} else if("ログアウト".equals(action)) {
			HttpSession session = request.getSession();
			session.invalidate();
			url = "logout.jsp";


		} else if("削除".equals(action)) {
			EmployeeDAO dao = new EmployeeDAO();
			int result = 0;
			String delete[] = request.getParameterValues("DELETE");
			if(delete != null) {
				for(int i = 0; i < delete.length; i++) {
					try {
						result += dao.EmployeeDelete(delete[i]);
					} catch (Exception e) {
						// TODO 自動生成された catch ブロック
						e.printStackTrace();
					}
				}
				request.setAttribute("DELETED", result);
				url = "deleted.jsp";
			} else {
				url = "deleteError.jsp";
			}



		} else if("メニュー画面へ".equals(action)) {
			url = "menu.jsp";
		}
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

}
