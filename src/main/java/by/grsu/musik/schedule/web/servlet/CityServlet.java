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
import by.grsu.musik.schedule.db.model.City;
import by.grsu.musik.schedule.db.model.Country;
import by.grsu.musik.schedule.web.dto.CityDto;
import by.grsu.musik.schedule.web.dto.CountryDto;

public class CityServlet extends HttpServlet {
	private static final IDao<Integer, Country> countryDao = CountryDaoImpl.INSTANCE;
	private static final IDao<Integer, City> cityDao = CityDaoImpl.INSTANCE;


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
		List<City> city = cityDao.getAll(); // get data

		List<CityDto> dtos = city.stream().map((entity) -> {
			CityDto dto = new CityDto();
			// copy necessary fields as-is
			dto.setId(entity.getId());
			dto.setName(entity.getName());
			//dto.setCountryId(entity.getCountryId());
			dto.setCountStreets(entity.getCountStreets());
			dto.setDataSave(entity.getDataSave());
			
			// build data for complex fields
			
			Country country = countryDao.getById(entity.getCountryId());
			dto.setCountryName(country.getName());
			
			return dto;
		}).collect(Collectors.toList());

		req.setAttribute("list", dtos); // set data as request attribute (like "add to map") to be used later in JSP
		req.getRequestDispatcher("city-list.jsp").forward(req, res); // delegate request processing to JSP
	}


	private void handleEditView(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String cityIdStr = req.getParameter("id");
		CityDto dto = new CityDto();
		if (!Strings.isNullOrEmpty(cityIdStr)) {
			// object edit
			Integer cityId = Integer.parseInt(cityIdStr);
			City entity = cityDao.getById(cityId);
			dto.setId(entity.getId());
			dto.setName(entity.getName());
			dto.setCountryId(entity.getCountryId());
			dto.setCountStreets(entity.getCountStreets());
			dto.setDataSave(entity.getDataSave());
			
		}
		req.setAttribute("dto", dto);
		req.setAttribute("allCountry", getAllCountryDtos());
		req.getRequestDispatcher("city-edit.jsp").forward(req, res);
	}
	
	private List<CountryDto> getAllCountryDtos() {
		return countryDao.getAll().stream().map((entity) -> {
			CountryDto dto = new CountryDto();
			dto.setId(entity.getId());
			dto.setName(entity.getName());
			return dto;
		}).collect(Collectors.toList());
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("doPost");
		City city = new City();
		String cityIdStr = req.getParameter("id");
		String nameIdStr = req.getParameter("name");
		String countryIdStr = req.getParameter("countryId");
		String countStreetsIdStr = req.getParameter("countStreets");
		
		

		city.setName(nameIdStr == null ? null : nameIdStr);
		city.setCountryId(countryIdStr == null ? null : Integer.parseInt(countryIdStr));
		city.setCountStreets(countStreetsIdStr == null ? null : Integer.parseInt(countStreetsIdStr));
		city.setDataSave(new Timestamp(new Date().getTime()));
		if (Strings.isNullOrEmpty(cityIdStr)) {
			// new entity
			city.setDataSave(new Timestamp(new Date().getTime()));
			cityDao.insert(city);
		} else {
			// updated entity
			city.setId(Integer.parseInt(cityIdStr));
			cityDao.update(city);
		}
		res.sendRedirect("/city"); // will send 302 back to client and client will execute GET /car
	}

	@Override
	public void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("doDelete");
		cityDao.delete(Integer.parseInt(req.getParameter("id")));
	}
}