package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.EmployeeDAO;
import model.dao.SectionDAO;
import model.entity.EmployeeBean;
import model.entity.SectionBean;

/**
 * Servlet implementation class RagstrationServlet
 */
@WebServlet("/RagstrationServlet")
public class RagstrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RagstrationServlet() {
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

		String url = "ragstration.jsp";

		SectionDAO dao = new SectionDAO();
		List<SectionBean> list = dao.getSectionList();
		request.setAttribute("SECTION_LIST", list);

		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);

	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// エンコード設定
		request.setCharacterEncoding("Windows-31J");
		response.setCharacterEncoding("Windoows-31J");

		String url = null;		// 遷移先URL
		int result = 0;			// 従業員登録の結果

		/* インスタンス化 */
		EmployeeBean bean = new EmployeeBean();
		EmployeeDAO dao = new EmployeeDAO();
		bean.setAll(request);			// 登録情報をbeanに格納

		/* 登録情報にエラーがある場合 */
		if(bean.getError().size() == 0) {
			result = dao.employeeRegistration(bean);	// 従業員情報を登録
		}

		/* 従業員情報が登録されなかった場合 */
		if(result == 0) {
			request.setAttribute("ERROR", bean.getError());
			url = "filure_ragstration.jsp";
		/* 従業員情報が登録された場合 */
		} else {
			url = "seccess_ragstration.jsp";
		}

		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

}
