package by.grsu.musik.schedule.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.common.base.Strings;

import by.grsu.musik.schedule.db.dao.IDao;
import by.grsu.musik.schedule.db.dao.impl.CountryDaoImpl;
import by.grsu.musik.schedule.db.dao.impl.RouteDaoImpl;
import by.grsu.musik.schedule.db.model.Country;
import by.grsu.musik.schedule.db.model.Route;
import by.grsu.musik.schedule.web.dto.CountryDto;
import by.grsu.musik.schedule.web.dto.RouteDto;

public class RouteServlet extends HttpServlet {
	private static final IDao<Integer, Route> routeDao = RouteDaoImpl.INSTANCE;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("doGet");
		String viewParam = req.getParameter("view");
		if ("edit".equals(viewParam)) {
			handleEditView(req, res);
		} else {
			handleListView(req, res);
		}
	}

	private void handleListView(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		List<Route> route = routeDao.getAll(); // get data

		List<RouteDto> dtos = route.stream().map((entity) -> {
			RouteDto dto = new RouteDto();
			// copy necessary fields as-is
			dto.setId(entity.getId());
			dto.setName(entity.getName());
			dto.setCountStops(entity.getCountStops());
			dto.setDuration(entity.getDuration());

			// build data for complex fields
			return dto;
		}).collect(Collectors.toList());

		req.setAttribute("list", dtos); // set data as request attribute (like "add to map") to be used later in JSP
		req.getRequestDispatcher("route-list.jsp").forward(req, res); // delegate request processing to JSP
	}


	private void handleEditView(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String routeIdStr = req.getParameter("id");
		RouteDto dto = new RouteDto();
		if (!Strings.isNullOrEmpty(routeIdStr)) {
			// object edit
			Integer routeId = Integer.parseInt(routeIdStr);
			Route entity = routeDao.getById(routeId);
			dto.setId(entity.getId());
			dto.setName(entity.getName());
			dto.setCountStops(entity.getCountStops());
			dto.setDuration(entity.getDuration());
		}
		req.setAttribute("dto", dto);
		req.getRequestDispatcher("route-edit.jsp").forward(req, res);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("doPost");
		Route route = new Route();
		String routeIdStr = req.getParameter("id");
		String nameIdStr = req.getParameter("name");
		String countStopsIdStr = req.getParameter("countStops");
		String durationIdStr = req.getParameter("duration");
		

		route.setName(nameIdStr == null ? null : nameIdStr);
		route.setCountStops(countStopsIdStr == null ? null : Integer.parseInt(countStopsIdStr));
		route.setDuration(durationIdStr == null ? null : Integer.parseInt(durationIdStr));
		if (Strings.isNullOrEmpty(routeIdStr)) {
			// new entity
			routeDao.insert(route);
		} else {
			// updated entity
			route.setId(Integer.parseInt(routeIdStr));
			routeDao.update(route);
		}
		res.sendRedirect("/route"); // will send 302 back to client and client will execute GET /car
	}
	@Override
	public void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("doDelete");
		routeDao.delete(Integer.parseInt(req.getParameter("id")));
	}
}