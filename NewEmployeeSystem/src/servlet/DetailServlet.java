package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.EmployeeDAO;
import model.dao.SectionDAO;
import model.entity.EmployeeBean;
import model.entity.SectionBean;

/**
 * Servlet implementation class DetailServlet
 */
@WebServlet("/DetailServlet")
public class DetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DetailServlet() {
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

		EmployeeDAO dao = new EmployeeDAO();
		HttpSession session = request.getSession();

		List<EmployeeBean> list = dao.employeeList();
		request.setAttribute("EMPLOYEE_LIST", list);
		session.invalidate();
		String url = "list.jsp";

		RequestDispatcher rd =request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// エンコード設定
		request.setCharacterEncoding("Windows-31J");
		response.setCharacterEncoding("Windows-31J");

		String action = request.getParameter("ACTION");		// 押されたボタンの情報を取得
		HttpSession session = request.getSession();
		EmployeeBean bean = (EmployeeBean)session.getAttribute("EMPLOYEE");	// 編集する従業員番号

		String url = null;		// 遷移先URL
		int result = 0;			// 編集/削除した結果

		// 編集ボタンが押された場合
		if("編集".equals(action)) {
			SectionDAO sectiondao = new SectionDAO();
			List<SectionBean> slist = sectiondao.getSectionList();
			request.setAttribute("SECTION_LIST", slist);
			url = "edit.jsp";

		} else if("削除".equals(action)) {
			EmployeeDAO dao = new EmployeeDAO();
			result = dao.employeeDelete(bean.getEmployeeNumber());
			if(result == 0) {
				url = "filure_delete.jsp";
			} else {
				url = "seccess_delete.jsp";
			}

		} else if("編集完了".equals(action)) {
			EmployeeDAO dao = new EmployeeDAO();
			EmployeeBean newBean = new EmployeeBean();
			newBean.setAll(request, 1);
			if(newBean.getError().size() == 0) {
				result = dao.employeeEdit(bean.getEmployeeNumber(),newBean);
			}
			if(result == 0) {
				request.setAttribute("ERROR", newBean.getError());
				url = "filure_edit.jsp";
			} else {
				session.removeAttribute("EMPLOYEE");
				session.setAttribute("EMPLOYEE", newBean);
				url = "seccess_edit.jsp";
			}

		} else if("詳細画面へ戻る".equals(action)) {
			url = "detail.jsp";
		} else {
			url = "error.jsp";
		}
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

}
