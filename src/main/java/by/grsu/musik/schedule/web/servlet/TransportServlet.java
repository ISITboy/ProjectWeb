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
import by.grsu.musik.schedule.db.dao.impl.CityDaoImpl;
import by.grsu.musik.schedule.db.dao.impl.CountryDaoImpl;
import by.grsu.musik.schedule.db.dao.impl.RouteDaoImpl;
import by.grsu.musik.schedule.db.dao.impl.TransportDaoImpl;
import by.grsu.musik.schedule.db.model.City;
import by.grsu.musik.schedule.db.model.Country;
import by.grsu.musik.schedule.db.model.Route;
import by.grsu.musik.schedule.db.model.Transport;
import by.grsu.musik.schedule.web.dto.CityDto;
import by.grsu.musik.schedule.web.dto.CountryDto;
import by.grsu.musik.schedule.web.dto.RouteDto;
import by.grsu.musik.schedule.web.dto.TransportDto;

public class TransportServlet extends HttpServlet {
	private static final IDao<Integer, Route> routeDao = RouteDaoImpl.INSTANCE;
	private static final IDao<Integer, City> cityDao = CityDaoImpl.INSTANCE;
	private static final IDao<Integer, Transport> transportDao = TransportDaoImpl.INSTANCE;



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
		List<Transport> transport = transportDao.getAll(); // get data

		List<TransportDto> dtos = transport.stream().map((entity) -> {
			TransportDto dto = new TransportDto();
			// copy necessary fields as-is
			dto.setId(entity.getId());
			dto.setName(entity.getName());
			//dto.setCountryId(entity.getCountryId());
			dto.setYearRelease(entity.getYearRelease());
			dto.setInspection(entity.getInspection());
			dto.setNumber(entity.getNumber());
			
			// build data for complex fields
			
			City city = cityDao.getById(entity.getCityId());
			dto.setNameCity(city.getName());
			
			Route route = routeDao.getById(entity.getRoutId());
			dto.setNameRoute(route.getName());
			
			return dto;
		}).collect(Collectors.toList());

		req.setAttribute("list", dtos); // set data as request attribute (like "add to map") to be used later in JSP
		req.getRequestDispatcher("transport-list.jsp").forward(req, res); // delegate request processing to JSP
	}


	private void handleEditView(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String transportIdStr = req.getParameter("id");
		TransportDto dto = new TransportDto();
		if (!Strings.isNullOrEmpty(transportIdStr)) {
			
			Integer transportId = Integer.parseInt(transportIdStr);
			Transport entity = transportDao.getById(transportId);
			dto.setId(entity.getId());
			dto.setName(entity.getName());
			dto.setCityId(entity.getCityId());
			dto.setRoutId(entity.getRoutId());
			dto.setYearRelease(entity.getYearRelease());
			dto.setInspection(entity.getInspection());
			dto.setNumber(entity.getNumber());
			
		}
		req.setAttribute("dto", dto);
		req.setAttribute("allCity", getAllCityDtos());
		req.setAttribute("allRoute", getAllRouteDtos());

		req.getRequestDispatcher("transport-edit.jsp").forward(req, res);
	}
	
	private List<CityDto> getAllCityDtos() {
		return cityDao.getAll().stream().map((entity) -> {
			CityDto dto = new CityDto();
			dto.setId(entity.getId());
			dto.setName(entity.getName());
			return dto;
		}).collect(Collectors.toList());
	}
	private List<RouteDto> getAllRouteDtos() {
		return routeDao.getAll().stream().map((entity) -> {
			RouteDto dto = new RouteDto();
			dto.setId(entity.getId());
			dto.setName(entity.getName());
			return dto;
		}).collect(Collectors.toList());
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("doPost");
		Transport transport = new Transport();
		String transportIdStr = req.getParameter("id");
		String nameIdStr = req.getParameter("name");
		String cityIdStr = req.getParameter("cityId");
		String routIdStr = req.getParameter("routId");
		//Integer inspectionIdStr = Integer.parseInt(req.getParameter("inspection"));
		//Integer numberIdStr = Integer.parseInt(req.getParameter("number"));
		

		transport.setName(nameIdStr == null ? null : nameIdStr);
		transport.setCityId(cityIdStr == null ? null : Integer.parseInt(cityIdStr));
		transport.setRoutId(routIdStr == null ? null : Integer.parseInt(routIdStr));
		transport.setInspection(Integer.parseInt(req.getParameter("inspection")));
		transport.setNumber(Integer.parseInt(req.getParameter("number")));
		transport.setYearRelease(new Timestamp(new Date().getTime()));
		if (Strings.isNullOrEmpty(transportIdStr)) {
			// new entity
			transport.setYearRelease(new Timestamp(new Date().getTime()));
			transportDao.insert(transport);
		} else {
			// updated entity
			transport.setId(Integer.parseInt(transportIdStr));
			transportDao.update(transport);
		}
		res.sendRedirect("/city"); // will send 302 back to client and client will execute GET /car
	}

	@Override
	public void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("doDelete");
		cityDao.delete(Integer.parseInt(req.getParameter("id")));
	}
}