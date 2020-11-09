package com.rimdev.rimfile.Services;

import java.util.Date;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.NonTransientDataAccessException;
import org.springframework.dao.RecoverableDataAccessException;
import org.springframework.dao.TransientDataAccessException;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.springframework.stereotype.Service;

import com.rimdev.rimfile.Repo.LogErrorRepo;
import com.rimdev.rimfile.Repo.LogFatalRepo;
import com.rimdev.rimfile.Repo.LogInfoRepo;
import com.rimdev.rimfile.Repo.LogOtherRepo;
import com.rimdev.rimfile.Repo.LogTypeRepo;
import com.rimdev.rimfile.Repo.LogWarningRepo;
import com.rimdev.rimfile.Utils.Generate;
import com.rimdev.rimfile.entities.Device;
import com.rimdev.rimfile.entities.LogError;
import com.rimdev.rimfile.entities.LogFatal;
import com.rimdev.rimfile.entities.LogInfo;
import com.rimdev.rimfile.entities.LogOther;
import com.rimdev.rimfile.entities.LogType;
import com.rimdev.rimfile.entities.LogWarning;


@Service
public class LogServ {
	
	@Autowired
	LogErrorRepo logErrorRepo;
	@Autowired
	LogFatalRepo  logFatalRepo;
	@Autowired
	LogInfoRepo logInfoRepo;
	@Autowired
	LogOtherRepo logOtherRepo;
	@Autowired
	LogTypeRepo logTypeRepo;
	@Autowired
	LogWarningRepo logWarningRepo;
	
	@Autowired
	TextConvertionServ textConvertionServ;
	
	@Autowired
	ConfigurationServ configurationServ;
	
	

	
	
	public String errorlog(String ipaddress,HttpServletRequest webservice,String text,Device deviceId,int userId,int logtypeID,String langcode,String logException) {
		String errorcode = "";
if(configurationServ.getbykey("Log_error").getConfigboolean() == 1) {
		LogError a= new LogError();
		a.setDeviceId(deviceId);
		a.setLogText(text);
		a.setLogException(logException);
		LogType	logtype =logtypebyid(logtypeID,langcode);
		a.setLogtypeID(logtype);
		a.setUserId(userId);
		Date current=new Date();
		a.setLogTime(current);
		a.setWebService(webservice.getRequestURI().toString());
		Generate gen=new Generate();
		 errorcode=gen.token(100);
		a.setErrorcode(errorcode);
		a.setIpaddress(ipaddress);
		logErrorRepo.save(a);
}
		return errorcode;
	}
	
	
	
	public String errorlogexternal(String ipaddress,String webservice,String text,Device deviceId,int userId,int logtypeID,String langcode,String logException) {
		String errorcode = "";
if(configurationServ.getbykey("Log_error").getConfigboolean() == 1) {
		LogError a= new LogError();
		a.setDeviceId(deviceId);
		a.setLogText(text);
		a.setLogException(logException);
		LogType	logtype =logtypebyid(logtypeID,langcode);
		a.setLogtypeID(logtype);
		a.setUserId(userId);
		Date current=new Date();
		a.setLogTime(current);
		a.setWebService(webservice);
		Generate gen=new Generate();
		 errorcode=gen.token(100);
		a.setErrorcode(errorcode);
		a.setIpaddress(ipaddress);
		logErrorRepo.save(a);
}
		return errorcode;
	}
	
	
	public String info(String ipaddress,HttpServletRequest webservice,String text,Device deviceId,int userId,int logtypeID,String langcode,String logException) {
		String errorcode = "";
if(configurationServ.getbykey("Log_info").getConfigboolean() == 1) {
		LogInfo a= new LogInfo();
		a.setDeviceId(deviceId);
		a.setLogText(text);
		a.setLogException(logException);
		LogType	logtype =logtypebyid(logtypeID,langcode);
		a.setLogtypeID(logtype);
		a.setUserId(userId);
		Date current=new Date();
		a.setLogTime(current);
		a.setWebService(webservice.getRequestURI().toString());
		Generate gen=new Generate();
		 errorcode=gen.token(100);
		a.setErrorcode(errorcode);
		a.setIpaddress(ipaddress);
		logInfoRepo.save(a);
}
		return errorcode;
	}
	
	

	public String infoexternal(String ipaddress,String webservice,String text,Device deviceId,int userId,int logtypeID,String langcode,String logException) {
		String errorcode = "";
if(configurationServ.getbykey("Log_info").getConfigboolean() == 1) {	
		LogInfo a= new LogInfo();
		a.setDeviceId(deviceId);
		a.setLogText(text);
		a.setLogException(logException);
		LogType	logtype =logtypebyid(logtypeID,langcode);
		a.setLogtypeID(logtype);
		a.setUserId(userId);
		Date current=new Date();
		a.setLogTime(current);
		a.setWebService(webservice);
		Generate gen=new Generate();
		 errorcode=gen.token(100);
		a.setErrorcode(errorcode);
		a.setIpaddress(ipaddress);
		logInfoRepo.save(a);
}
		return errorcode;
	}
	
	
	
	public String fatalerror(String ipaddress,HttpServletRequest webservice,String text,Device deviceId,int userId,int logtypeID,String langcode,String logException) {
		String errorcode = "";
if(configurationServ.getbykey("Log_fatal_error").getConfigboolean() == 1) {
		LogFatal a= new LogFatal();
		a.setDeviceId(deviceId);
		a.setLogText(text);
		a.setLogException(logException);
		LogType	logtype =logtypebyid(logtypeID,langcode);
		a.setLogtypeID(logtype);
		a.setUserId(userId);
		Date current=new Date();
		a.setLogTime(current);
		a.setWebService(webservice.getRequestURI().toString());
		Generate gen=new Generate();
		 errorcode=gen.token(100);
		a.setErrorcode(errorcode);
		a.setIpaddress(ipaddress);
		logFatalRepo.save(a);
}
		return errorcode;
	}
	
	public String fatalerrorexternal(String ipaddress,String webservice,String text,Device deviceId,int userId,int logtypeID,String langcode,String logException) {
		String errorcode = "";
if(configurationServ.getbykey("Log_fatal_error").getConfigboolean() == 1) {
		LogFatal a= new LogFatal();
		a.setDeviceId(deviceId);
		a.setLogText(text);
		a.setLogException(logException);
		LogType	logtype =logtypebyid(logtypeID,langcode);
		a.setLogtypeID(logtype);
		a.setUserId(userId);
		Date current=new Date();
		a.setLogTime(current);
		a.setWebService(webservice);
		Generate gen=new Generate();
		 errorcode=gen.token(100);
		a.setErrorcode(errorcode);
		a.setIpaddress(ipaddress);
		logFatalRepo.save(a);
}
		return errorcode;
	}
	
	
	public String warning(String ipaddress,HttpServletRequest webservice,String text,Device deviceId,int userId,int logtypeID,String langcode,String logException) {
		String errorcode = "";
if(configurationServ.getbykey("Log_warning").getConfigboolean() == 1) {
		LogWarning a= new LogWarning();
		a.setDeviceId(deviceId);
		a.setLogText(text);
		a.setLogException(logException);
		LogType	logtype =logtypebyid(logtypeID,langcode);
		a.setLogtypeID(logtype);
		a.setUserId(userId);
		Date current=new Date();
		a.setLogTime(current);
		a.setWebService(webservice.getRequestURI().toString());
		Generate gen=new Generate();
		 errorcode=gen.token(100);
		a.setErrorcode(errorcode);
		a.setIpaddress(ipaddress);
		logWarningRepo.save(a);
}
		return errorcode;
	}
	
	
	public String warningexternal(String ipaddress,String webservice,String text,Device deviceId,int userId,int logtypeID,String langcode,String logException) {
		String errorcode = "";
if(configurationServ.getbykey("Log_warning").getConfigboolean() == 1) {
		LogWarning a= new LogWarning();
		a.setDeviceId(deviceId);
		a.setLogText(text);
		a.setLogException(logException);
		LogType	logtype =logtypebyid(logtypeID,langcode);
		a.setLogtypeID(logtype);
		a.setUserId(userId);
		Date current=new Date();
		a.setLogTime(current);
		a.setWebService(webservice);
		Generate gen=new Generate();
		 errorcode=gen.token(100);
		a.setErrorcode(errorcode);
		a.setIpaddress(ipaddress);
		logWarningRepo.save(a);
}
		return errorcode;
	}
	
	
	public String logother(String ipaddress,HttpServletRequest webservice,String text,Device deviceId,int userId,int logtypeID,String langcode,String logException) {
		String errorcode = "";
if(configurationServ.getbykey("Log_other").getConfigboolean() == 1) {
		LogOther a= new LogOther();
		a.setDeviceId(deviceId);
		a.setLogText(text);
		a.setLogException(logException);
		LogType	logtype =logtypebyid(logtypeID,langcode);
		a.setLogtypeID(logtype);
		a.setUserId(userId);
		Date current=new Date();
		a.setLogTime(current);
		a.setWebService(webservice.getRequestURI().toString());
		Generate gen=new Generate();
		 errorcode=gen.token(100);
		a.setErrorcode(errorcode);
		a.setIpaddress(ipaddress);
		logOtherRepo.save(a);
}
		return errorcode;
	}
	
	
	public String logotherexternal(String ipaddress,String webservice,String text,Device deviceId,int userId,int logtypeID,String langcode,String logException) {
		String errorcode = "";
if(configurationServ.getbykey("Log_other").getConfigboolean() == 1) {
		LogOther a= new LogOther();
		a.setDeviceId(deviceId);
		a.setLogText(text);
		a.setLogException(logException);
		LogType	logtype =logtypebyid(logtypeID,langcode);
		a.setLogtypeID(logtype);
		a.setUserId(userId);
		Date current=new Date();
		a.setLogTime(current);
		a.setWebService(webservice);
		Generate gen=new Generate();
		 errorcode=gen.token(100);
		a.setErrorcode(errorcode);
		a.setIpaddress(ipaddress);
		logOtherRepo.save(a);
}
		return errorcode;
	}
	
	
	public LogType logtypebyid(int id,String langcode) {
		
		try {
			Optional<LogType> flowid =logTypeRepo.findById(id);
			 
			 if (flowid.isPresent()){
				 LogType a= flowid.get();
			
				return a;

						}
				else{
				   // 
			throw new NullPointerException(textConvertionServ.search("E100", langcode));

				
					
				}
		}  catch (TransientDataAccessException  se) {
			throw new NullPointerException(textConvertionServ.search("E104", langcode));
	    } catch (RecoverableDataAccessException  se) {
			throw new NullPointerException(textConvertionServ.search("E104", langcode));
	    }catch (ScriptException  se) {
			throw new NullPointerException(textConvertionServ.search("E104", langcode));
	    }catch (NonTransientDataAccessException  se) {
			throw new NullPointerException(textConvertionServ.search("E104", langcode));
	    }
		
	}
	
	
	
	
}
