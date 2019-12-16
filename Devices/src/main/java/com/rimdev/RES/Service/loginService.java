package com.rimdev.RES.Service;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.springframework.stereotype.Service;

import com.rimdev.RES.Input.Regiter;

@Service
public class loginService {
	
	@PersistenceContext
    private EntityManager entityManager;

    public int checkUsernameAndPassword(Regiter input) {

        //"login" this is the name of your procedure
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("login_check"); 

        //Declare the parameters in the same order
        query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(3, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(4, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(5, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(6, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(7, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(8, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(9, Integer.class, ParameterMode.OUT);
        query.registerStoredProcedureParameter(10, Integer.class, ParameterMode.OUT);

        //Pass the parameter values
        query.setParameter(1, input.getUsername());
        query.setParameter(2, input.getPassword());
        query.setParameter(3, input.getEmail());
        query.setParameter(4, input.getLand_phone());
        query.setParameter(5, input.getMobile());
        query.setParameter(6, input.getPostal_code());
        query.setParameter(7, input.getIp());
        query.setParameter(8, input.getBrowser());

        //Execute query
        query.execute();

        //Get output parameters
        Integer outCodep = (Integer) query.getOutputParameterValue(9);
        Integer outMessage = (Integer) query.getOutputParameterValue(10);
        
        System.out.println(outMessage);

        return outMessage; //enter your condition
    }

}
