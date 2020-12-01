package com.rimdev.rimlog.Services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.NonTransientDataAccessException;
import org.springframework.dao.RecoverableDataAccessException;
import org.springframework.dao.TransientDataAccessException;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.springframework.stereotype.Service;

import com.rimdev.rimlog.Exception.NoResultException;
import com.rimdev.rimlog.Repo.LogErrorRepo;
import com.rimdev.rimlog.Repo.LogFatalRepo;
import com.rimdev.rimlog.Repo.LogInfoRepo;
import com.rimdev.rimlog.Repo.LogOtherRepo;
import com.rimdev.rimlog.Repo.LogTypeRepo;
import com.rimdev.rimlog.Repo.LogWarningRepo;
import com.rimdev.rimlog.Utils.Generate;
import com.rimdev.rimlog.entities.Device;
import com.rimdev.rimlog.entities.LogError;
import com.rimdev.rimlog.entities.LogFatal;
import com.rimdev.rimlog.entities.LogInfo;
import com.rimdev.rimlog.entities.LogOther;
import com.rimdev.rimlog.entities.LogType;
import com.rimdev.rimlog.entities.LogWarning;

@Service
public class LogServ {

	@Autowired
	LogErrorRepo logErrorRepo;
	@Autowired
	LogFatalRepo logFatalRepo;
	@Autowired
	LogInfoRepo logInfoRepo;
	@Autowired
	LogOtherRepo logOtherRepo;
	@Autowired
	LogTypeRepo logTypeRepo;
	@Autowired
	LogWarningRepo logWarningRepo;
	@Autowired
	ExternalServ externalServ;


	@Autowired
	ConfigurationServ configurationServ;

	public LogError errorlogexternal(String ipaddress, String webservice, String text, Device deviceId, int userId,
			int logtypeID, String langcode, String logException) {
		String errorcode = "";
		if (configurationServ.getbykey("Log_error").getConfigboolean() == 1) {
			LogError a = new LogError();
			a.setDeviceId(deviceId);
			a.setLogText(text);
			a.setLogException(logException);
			LogType logtype = logtypebyid(logtypeID, langcode);
			a.setLogtypeID(logtype);
			a.setUserId(userId);
			Date current = new Date();
			a.setLogTime(current);
			a.setWebService(webservice);
			Generate gen = new Generate();
			errorcode = gen.token(100);
			a.setErrorcode(errorcode);
			a.setIpaddress(ipaddress);
			logErrorRepo.save(a);
			return a;
		}
		return null;
	}

	public LogInfo infoexternal(String ipaddress, String webservice, String text, Device deviceId, int userId,
			int logtypeID, String langcode, String logException) {
		String errorcode = "";
		if (configurationServ.getbykey("Log_info").getConfigboolean() == 1) {
			LogInfo a = new LogInfo();
			a.setDeviceId(deviceId);
			a.setLogText(text);
			a.setLogException(logException);
			LogType logtype = logtypebyid(logtypeID, langcode);
			a.setLogtypeID(logtype);
			a.setUserId(userId);
			Date current = new Date();
			a.setLogTime(current);
			a.setWebService(webservice);
			Generate gen = new Generate();
			errorcode = gen.token(100);
			a.setErrorcode(errorcode);
			a.setIpaddress(ipaddress);
			logInfoRepo.save(a);
			return a;
		}
		throw new NoResultException(externalServ.search("E100", langcode));
	}

	public LogFatal fatalerrorexternal(String ipaddress, String webservice, String text, Device deviceId, int userId,
			int logtypeID, String langcode, String logException) {
		String errorcode = "";
		if (configurationServ.getbykey("Log_fatal_error").getConfigboolean() == 1) {
			LogFatal a = new LogFatal();
			a.setDeviceId(deviceId);
			a.setLogText(text);
			a.setLogException(logException);
			LogType logtype = logtypebyid(logtypeID, langcode);
			a.setLogtypeID(logtype);
			a.setUserId(userId);
			Date current = new Date();
			a.setLogTime(current);
			a.setWebService(webservice);
			Generate gen = new Generate();
			errorcode = gen.token(100);
			a.setErrorcode(errorcode);
			a.setIpaddress(ipaddress);
			logFatalRepo.save(a);
			return a;
		}
		throw new NoResultException(externalServ.search("E100", langcode));
	}

	public LogWarning warningexternal(String ipaddress, String webservice, String text, Device deviceId, int userId,
			int logtypeID, String langcode, String logException) {
		String errorcode = "";
		if (configurationServ.getbykey("Log_warning").getConfigboolean() == 1) {
			LogWarning a = new LogWarning();
			a.setDeviceId(deviceId);
			a.setLogText(text);
			a.setLogException(logException);
			LogType logtype = logtypebyid(logtypeID, langcode);
			a.setLogtypeID(logtype);
			a.setUserId(userId);
			Date current = new Date();
			a.setLogTime(current);
			a.setWebService(webservice);
			Generate gen = new Generate();
			errorcode = gen.token(100);
			a.setErrorcode(errorcode);
			a.setIpaddress(ipaddress);
			logWarningRepo.save(a);
			return a;
		}
		throw new NoResultException(externalServ.search("E100", langcode));
	}

	public LogOther logotherexternal(String ipaddress, String webservice, String text, Device deviceId, int userId,
			int logtypeID, String langcode, String logException) {
		String errorcode = "";
		if (configurationServ.getbykey("Log_other").getConfigboolean() == 1) {
			LogOther a = new LogOther();
			a.setDeviceId(deviceId);
			a.setLogText(text);
			a.setLogException(logException);
			LogType logtype = logtypebyid(logtypeID, langcode);
			a.setLogtypeID(logtype);
			a.setUserId(userId);
			Date current = new Date();
			a.setLogTime(current);
			a.setWebService(webservice);
			Generate gen = new Generate();
			errorcode = gen.token(100);
			a.setErrorcode(errorcode);
			a.setIpaddress(ipaddress);
			logOtherRepo.save(a);
			return a;
		}
		throw new NoResultException(externalServ.search("E100", langcode));
	}

	public LogType logtypebyid(int id, String langcode) {

		try {
			Optional<LogType> flowid = logTypeRepo.findById(id);

			if (flowid.isPresent()) {
				LogType a = flowid.get();

				return a;

			} else {
				//
				throw new NoResultException(externalServ.search("E100", langcode));

			}
		} catch (TransientDataAccessException se) {
			throw new NoResultException(externalServ.search("E104", langcode));
		} catch (RecoverableDataAccessException se) {
			throw new NoResultException(externalServ.search("E104", langcode));
		} catch (ScriptException se) {
			throw new NoResultException(externalServ.search("E104", langcode));
		} catch (NonTransientDataAccessException se) {
			throw new NoResultException(externalServ.search("E104", langcode));
		}

	}

	public List<LogInfo> getallinfo(String langcode) {

		try {

			List<LogInfo> flowid = (List<LogInfo>) logInfoRepo.findAll();

			return flowid;

		} catch (TransientDataAccessException se) {
			throw new NoResultException(externalServ.search("E104", langcode));
		} catch (RecoverableDataAccessException se) {
			throw new NoResultException(externalServ.search("E104", langcode));
		} catch (ScriptException se) {
			throw new NoResultException(externalServ.search("E104", langcode));
		} catch (NonTransientDataAccessException se) {
			throw new NoResultException(externalServ.search("E104", langcode));
		}

	}

	public LogInfo logbycodeinfo(String errorcode, String langcode) {

		try {

			Optional<LogInfo> flowid = logInfoRepo.findbycode(errorcode);

			if (flowid.isPresent()) {
				LogInfo a = flowid.get();

				return a;

			} else {
				//
				throw new NoResultException(externalServ.search("E100", langcode));

			}

		} catch (TransientDataAccessException se) {
			throw new NoResultException(externalServ.search("E104", langcode), new Throwable());
		} catch (RecoverableDataAccessException se) {
			throw new NoResultException(externalServ.search("E104", langcode));
		} catch (ScriptException se) {
			throw new NoResultException(externalServ.search("E104", langcode));
		} catch (NonTransientDataAccessException se) {
			throw new NoResultException(externalServ.search("E104", langcode));
		}

	}

	public List<LogError> getallerror(String langcode) {

		try {

			List<LogError> flowid = (List<LogError>) logErrorRepo.findAll();

			return flowid;

		} catch (TransientDataAccessException se) {
			throw new NoResultException(externalServ.search("E104", langcode));
		} catch (RecoverableDataAccessException se) {
			throw new NoResultException(externalServ.search("E104", langcode));
		} catch (ScriptException se) {
			throw new NoResultException(externalServ.search("E104", langcode));
		} catch (NonTransientDataAccessException se) {
			throw new NoResultException(externalServ.search("E104", langcode));
		}

	}

	public LogError logbycodeerror(String errorcode, String langcode) {

		try {

			Optional<LogError> flowid = logErrorRepo.findbycode(errorcode);

			if (flowid.isPresent()) {
				LogError a = flowid.get();

				return a;

			} else {
				//
				throw new NoResultException(externalServ.search("E100", langcode));

			}

		} catch (TransientDataAccessException se) {
			throw new NoResultException(externalServ.search("E104", langcode), new Throwable());
		} catch (RecoverableDataAccessException se) {
			throw new NoResultException(externalServ.search("E104", langcode));
		} catch (ScriptException se) {
			throw new NoResultException(externalServ.search("E104", langcode));
		} catch (NonTransientDataAccessException se) {
			throw new NoResultException(externalServ.search("E104", langcode));
		}

	}

	public List<LogWarning> getallwarning(String langcode) {

		try {

			List<LogWarning> flowid = (List<LogWarning>) logWarningRepo.findAll();

			return flowid;

		} catch (TransientDataAccessException se) {
			throw new NoResultException(externalServ.search("E104", langcode));
		} catch (RecoverableDataAccessException se) {
			throw new NoResultException(externalServ.search("E104", langcode));
		} catch (ScriptException se) {
			throw new NoResultException(externalServ.search("E104", langcode));
		} catch (NonTransientDataAccessException se) {
			throw new NoResultException(externalServ.search("E104", langcode));
		}

	}

	public LogWarning logbycodewarning(String errorcode, String langcode) {

		try {

			Optional<LogWarning> flowid = logWarningRepo.findbycode(errorcode);

			if (flowid.isPresent()) {
				LogWarning a = flowid.get();

				return a;

			} else {
				//
				throw new NoResultException(externalServ.search("E100", langcode));

			}

		} catch (TransientDataAccessException se) {
			throw new NoResultException(externalServ.search("E104", langcode), new Throwable());
		} catch (RecoverableDataAccessException se) {
			throw new NoResultException(externalServ.search("E104", langcode));
		} catch (ScriptException se) {
			throw new NoResultException(externalServ.search("E104", langcode));
		} catch (NonTransientDataAccessException se) {
			throw new NoResultException(externalServ.search("E104", langcode));
		}

	}

	public List<LogFatal> getallfatal(String langcode) {

		try {

			List<LogFatal> flowid = (List<LogFatal>) logFatalRepo.findAll();

			return flowid;

		} catch (TransientDataAccessException se) {
			throw new NoResultException(externalServ.search("E104", langcode));
		} catch (RecoverableDataAccessException se) {
			throw new NoResultException(externalServ.search("E104", langcode));
		} catch (ScriptException se) {
			throw new NoResultException(externalServ.search("E104", langcode));
		} catch (NonTransientDataAccessException se) {
			throw new NoResultException(externalServ.search("E104", langcode));
		}

	}

	public LogFatal logbycodefatal(String errorcode, String langcode) {

		try {

			Optional<LogFatal> flowid = logFatalRepo.findbycode(errorcode);

			if (flowid.isPresent()) {
				LogFatal a = flowid.get();

				return a;

			} else {
				//
				throw new NoResultException(externalServ.search("E100", langcode));

			}

		} catch (TransientDataAccessException se) {
			throw new NoResultException(externalServ.search("E104", langcode), new Throwable());
		} catch (RecoverableDataAccessException se) {
			throw new NoResultException(externalServ.search("E104", langcode));
		} catch (ScriptException se) {
			throw new NoResultException(externalServ.search("E104", langcode));
		} catch (NonTransientDataAccessException se) {
			throw new NoResultException(externalServ.search("E104", langcode));
		}

	}

	public List<LogOther> getallother(String langcode) {

		try {

			List<LogOther> flowid = (List<LogOther>) logOtherRepo.findAll();

			return flowid;

		} catch (TransientDataAccessException se) {
			throw new NoResultException(externalServ.search("E104", langcode));
		} catch (RecoverableDataAccessException se) {
			throw new NoResultException(externalServ.search("E104", langcode));
		} catch (ScriptException se) {
			throw new NoResultException(externalServ.search("E104", langcode));
		} catch (NonTransientDataAccessException se) {
			throw new NoResultException(externalServ.search("E104", langcode));
		}

	}

	public LogOther logbycodeother(String errorcode, String langcode) {

		try {

			Optional<LogOther> flowid = logOtherRepo.findbycode(errorcode);

			if (flowid.isPresent()) {
				LogOther a = flowid.get();

				return a;

			} else {
				//
				throw new NoResultException(externalServ.search("E100", langcode));

			}

		} catch (TransientDataAccessException se) {
			throw new NoResultException(externalServ.search("E104", langcode), new Throwable());
		} catch (RecoverableDataAccessException se) {
			throw new NoResultException(externalServ.search("E104", langcode));
		} catch (ScriptException se) {
			throw new NoResultException(externalServ.search("E104", langcode));
		} catch (NonTransientDataAccessException se) {
			throw new NoResultException(externalServ.search("E104", langcode));
		}

	}

}
