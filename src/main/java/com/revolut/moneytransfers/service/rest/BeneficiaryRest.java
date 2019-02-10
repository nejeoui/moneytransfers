package com.revolut.moneytransfers.service.rest;

import java.util.Optional;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;

import com.revolut.moneytransfers.model.Beneficiary;
import com.revolut.moneytransfers.service.BeneficiaryService;

@Path("/Beneficiary")
public class BeneficiaryRest {
	/**
	 * BeneficiarytService offering CRUD 
	 */
	@Inject
	private BeneficiaryService beneficiaryService;

	/**
	 * Self4j Logger
	 */
	@Inject
	private transient Logger logger;
	
	/**
	 * 
	 * @param beneficiary in Json format
	 * @return CREATED 201 response if the new beneficiary is created successfully, Otherwise
	 *         returns the status error message
	 */
	@Path("/newBeneficiary")
	@PUT
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response createNewAccount(Beneficiary beneficiary) {
		if (beneficiary == null||beneficiary.getPhone()==null) {
			logger.info("PUT Entity is null: ");
			return Response.status(Status.BAD_REQUEST).build();
		}
		try {
			logger.info("PUT Entity : " + beneficiary.toString());
			beneficiary = beneficiaryService.save(beneficiary);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}

		return Response.status(Status.CREATED).build();
	}
	@GET
	@Path("{phone}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBeneficiaryPhone(@PathParam("phone") String phone) {
		Beneficiary beneficiary=null;
		if (phone == null) {
			logger.info("PUT phone is null: ");
			return Response.status(Status.BAD_REQUEST).build();
		}
		try {
			logger.info("PUT phone : " + phone);
			Optional<Beneficiary> optional = beneficiaryService.find(phone);
			if(optional.isPresent()) beneficiary=optional.get();
		} catch (Exception e) {
			logger.info("getBeneficiaryPhone fail: " + e.getMessage());
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		if (beneficiary != null)
			return Response.ok(beneficiary, MediaType.APPLICATION_JSON).build();
		else
			return Response.status(Response.Status.NOT_FOUND).build();
	}
}
