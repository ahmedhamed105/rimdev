package com.rimdev.rimlog.Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.rimdev.rimlog.Services.ExternalServ;
import com.rimdev.rimlog.Services.LogServ;
import com.rimdev.rimlog.entities.Device;
import com.rimdev.rimlog.entities.LogWarning;
import com.rimdev.rimlog.outputobject.GetLogob;
import com.rimdev.rimlog.outputobject.Logobject;

@Controller // This means that this class is a Controller
@RequestMapping(path = "/warning") //
public class WarninglogController {

	@Autowired
	LogServ logServ;

	@Autowired
	ExternalServ externalServ;

	@RequestMapping(value = "/insert/{langcode}", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Boolean> log_text(HttpServletRequest req, @RequestBody Logobject log,
			@PathVariable("langcode") String langcode) {

		try {

			Device devinfo = externalServ.getdevicebyid(log.getDeviceid(), langcode);

			if (devinfo == null) {
				System.out.println("Device is null");
				return new ResponseEntity<Boolean>(false, HttpStatus.UNAUTHORIZED);
			}

			logServ.warningexternal(devinfo.getDeviceip(), log.getRequesturl(), log.getLogtext(), devinfo,
					log.getUserid(), log.getLogtype(), langcode, log.getLogexception());

			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}

	}

	@RequestMapping(value = "/get/{langcode}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<LogWarning> getbyerrorcode(HttpServletRequest req, @RequestBody GetLogob log,
			@PathVariable("langcode") String langcode) {

		try {

			LogWarning loging = logServ.logbycodewarning(log.getErrorcode(), langcode);

			return new ResponseEntity<LogWarning>(loging, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception

			throw e;
		}

	}

	@RequestMapping(value = "/all/{langcode}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<LogWarning>> getallwarning(HttpServletRequest req,
			@PathVariable("langcode") String langcode) {

		try {

			return new ResponseEntity<List<LogWarning>>(logServ.getallwarning(langcode), HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}

	}

}
