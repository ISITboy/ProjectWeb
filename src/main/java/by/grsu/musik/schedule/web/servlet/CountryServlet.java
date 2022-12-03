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

import by.grsu.musik.schedule.web.ValidationUtils;
import by.grsu.musik.schedule.db.dao.IDao;
import by.grsu.musik.schedule.db.dao.impl.CountryDaoImpl;
import by.grsu.musik.schedule.db.model.Country;
import by.grsu.musik.schedule.web.dto.CountryDto;

public class CountryServlet extends HttpServlet {
	private static final IDao<Integer, Country> countryDao = CountryDaoImpl.INSTANCE;

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
		List<Country> country = countryDao.getAll(); // get data

		List<CountryDto> dtos = country.stream().map((entity) -> {
			CountryDto dto = new CountryDto();
			// copy necessary fields as-is
			dto.setId(entity.getId());
			dto.setName(entity.getName());
			dto.setCountCity(entity.getCountCity());
			dto.setDataSave(entity.getDataSave());

			// build data for complex fields
			return dto;
		}).collect(Collectors.toList());

		req.setAttribute("list", dtos); // set data as request attribute (like "add to map") to be used later in JSP
		req.getRequestDispatcher("country-list.jsp").forward(req, res); // delegate request processing to JSP
	}


	private void handleEditView(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String countryIdStr = req.getParameter("id");
		CountryDto dto = new CountryDto();
		if (!Strings.isNullOrEmpty(countryIdStr)) {
			// object edit
			Integer countryId = Integer.parseInt(countryIdStr);
			Country entity = countryDao.getById(countryId);
			dto.setId(entity.getId());
			dto.setName(entity.getName());
			dto.setCountCity(entity.getCountCity());
			dto.setDataSave(entity.getDataSave());
		}
		req.setAttribute("dto", dto);
		req.getRequestDispatcher("country-edit.jsp").forward(req, res);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("doPost");
		Country country = new Country();
		String countryIdStr = req.getParameter("id");
		String nameIdStr = req.getParameter("name");
		String countCityIdStr = req.getParameter("countCity");

		country.setName(nameIdStr == null ? null : nameIdStr);
		country.setCountCity(countCityIdStr == null ? null : Integer.parseInt(countCityIdStr));
		country.setDataSave(new Timestamp(new Date().getTime()));
		if (Strings.isNullOrEmpty(countryIdStr)) {
			// new entity
			country.setDataSave(new Timestamp(new Date().getTime()));
			countryDao.insert(country);
		} else {
			// updated entity
			country.setId(Integer.parseInt(countryIdStr));
			countryDao.update(country);
		}
		res.sendRedirect("/country"); // will send 302 back to client and client will execute GET /car
	}
	@Override
	public void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("doDelete");
		countryDao.delete(Integer.parseInt(req.getParameter("id")));
	}
}