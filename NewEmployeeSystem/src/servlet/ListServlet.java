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
 * Servlet implementation class system_servlet
 */
@WebServlet("/ListServlet")
public class ListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		final String URL = "list.jsp";	// 遷移先

		/* エンコーディング設定 */
		request.setCharacterEncoding("Windows-31J");
		response.setCharacterEncoding("Windows-31J");

		/* インスタンス化とローカル変数 */
		EmployeeDAO dao = new EmployeeDAO();

		List<EmployeeBean> list = dao.employeeList();		// DBから登録されている従業員一覧を取得
		request.setAttribute("EMPLOYEE_LIST", list);					// 従業員一覧をリクエストに格納

		/* データ転送 */
		RequestDispatcher rd = request.getRequestDispatcher(URL);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		/* エンコーディング設定 */
		request.setCharacterEncoding("Windows-31J");
		response.setCharacterEncoding("Windows-31J");

		/* ローカル変数 */
		String url = "";		// 遷移先URL用変数
		String action = request.getParameter("ACTION");		// 押下されたボタンの情報を取得


		/*** 登録ボタン ***/
		if("従業員登録".equals(action)) {
			/* インスタンス化 */
			SectionDAO sectionDAO = new SectionDAO();

			List <SectionBean> sectionList = sectionDAO.getSectionList();		// 部署リストを取得しリストに格納
			request.setAttribute("SECTION_LIST", sectionList);			// 部署リストをリクエストへ格納

			url = "ragstration.jsp";				// 遷移先URL指定
		/*** 詳細ボタン ***/
		} else if("詳細".equals(action)) {
			/* インスタンス化 */
			EmployeeDAO employeeDAO = new EmployeeDAO();
			SectionDAO sectionDAO = new SectionDAO();
			String code = request.getParameter("EMPLOYEE_CODE");
			EmployeeBean employeeBean = employeeDAO.employeeSearch(code);
			try {
				employeeBean.setSectionName(sectionDAO.getSectionName(employeeBean.getSectionCode()));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			HttpSession session = request.getSession();
			session.setAttribute("EMPLOYEE", employeeBean);
			url = "detail.jsp";						// 遷移先URL指定
		}
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}
}