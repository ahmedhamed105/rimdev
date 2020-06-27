package com.rimdev.accounting.Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rimdev.accounting.Enttities.AllStatus;
import com.rimdev.accounting.Enttities.Currency;
import com.rimdev.accounting.Exception.PopupException;
import com.rimdev.accounting.Services.AllStatusServ;
import com.rimdev.accounting.Services.CurrencyServ;
import com.rimdev.accounting.Services.ExternalServ;
import com.rimdev.accounting.Services.TextConvertionServ;
import com.rimdev.accounting.Utils.ObjectUtils;

@Controller // This means that this class is a Controller
@RequestMapping(path = "/Currency") //
public class CurrencyController {

	@Autowired
	CurrencyServ currencyServ;

	@Autowired
	ExternalServ externalServ;

	@Autowired
	TextConvertionServ textConvertionServ;

	@Autowired
	AllStatusServ allStatusServ;

	@RequestMapping(value = "/all/{langcode}", method = RequestMethod.GET)
	public ResponseEntity<List<Currency>> getAll(HttpServletRequest request,
			@RequestHeader("Devicecode") String Devicecode, @RequestHeader("username") String username,
			@RequestHeader("usertokean") String usertokean, @RequestHeader("pageid") String pagenum,
			@PathVariable("langcode") String langcode) {

		try {
			boolean result = externalServ.Log(request, Devicecode, username, usertokean, pagenum, langcode,
					"Currency in processing", "", 34, 0);
			if (result) {

				return new ResponseEntity<List<Currency>>(currencyServ.getall(langcode), HttpStatus.OK);

			} else {

				externalServ.Log(request, Devicecode, username, usertokean, pagenum, langcode,
						"Currency Error in processing", "while auth and log in first", 34, 1);
				throw new PopupException(textConvertionServ.search("auth_error", langcode));

			}
		} catch (Exception e) {

			throw new PopupException(textConvertionServ.search("no_data", langcode));
		}

	}

	@RequestMapping(value = "/save/{langcode}", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<List<Currency>> save(HttpServletRequest request,
			@RequestHeader("Devicecode") String Devicecode, @RequestHeader("username") String username,
			@RequestHeader("usertokean") String usertokean, @RequestHeader("pageid") String pagenum,
			@PathVariable("langcode") String langcode, @RequestBody Currency input) {
		// This returns a JSON or XML with the users

		boolean result = externalServ.Log(request, Devicecode, username, usertokean, pagenum, langcode,
				"Currency in processing", "", 34, 0);

		if (result) {
			try {
				if (input.getId() == null || input.getAllstatusID().getId() == null) {
					// System.out.println("enter 3");
					AllStatus status = allStatusServ.getbyid(input.getAllstatusID().getId(), langcode);
					input.setAllstatusID(status);
					currencyServ.Save(input, langcode);
					externalServ.Log(request, Devicecode, username, usertokean, pagenum, langcode, "Currency inserted",
							"", 34, 0);
				} else {
					throw new PopupException("error while insertion");
				}
			} catch (Exception e) {
				// TODO: handle exception
				throw new PopupException("error while insertion");
			}

			return getAll(request, Devicecode, username, usertokean, pagenum, langcode);

		} else {

			externalServ.Log(request, Devicecode, username, usertokean, pagenum, langcode,
					"Currency Error in processing", "while auth and log in first", 34, 1);
			throw new PopupException(textConvertionServ.search("auth_error", langcode));

		}

	}

	@RequestMapping(value = "/update/{langcode}", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<List<Currency>> update(HttpServletRequest request,
			@RequestHeader("Devicecode") String Devicecode, @RequestHeader("username") String username,
			@RequestHeader("usertokean") String usertokean, @RequestHeader("pageid") String pagenum,
			@PathVariable("langcode") String langcode, @RequestBody Currency input) {
		// This returns a JSON or XML with the users
		Currency user = null;
		boolean result = externalServ.Log(request, Devicecode, username, usertokean, pagenum, langcode,
				"Currency in processing", "", 34, 0);

		if (result) {
			if (input.getId() == null) {

				throw new PopupException("error while updating");

			} else {

				try {
					user = currencyServ.getcurrency(input.getId(), langcode);
					// System.out.println("enter 2");

					if (user == null) {
						// System.out.println("enter 3");
						throw new PopupException("error while updating");

					} else {
						AllStatus status = allStatusServ.getbyid(input.getAllstatusID().getId(), langcode);
						input.setAllstatusID(status);
						user = currencyServ.update(user, input, langcode);
						externalServ.Log(request, Devicecode, username, usertokean, pagenum, langcode,
								"Currency updated", "", 34, 0);

					}
				} catch (Exception e) {
					// TODO: handle exception

					throw new PopupException("error while updating");
				}

			}

			return getAll(request, Devicecode, username, usertokean, pagenum, langcode);
		} else {

			externalServ.Log(request, Devicecode, username, usertokean, pagenum, langcode,
					"Currency Error in processing", "while auth and log in first", 34, 1);
			throw new PopupException(textConvertionServ.search("auth_error", langcode));

		}

	}

}
