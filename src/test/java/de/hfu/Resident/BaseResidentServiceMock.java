package de.hfu.Resident;

import de.hfu.residents.domain.Resident;

import de.hfu.residents.repository.ResidentRepository;
import de.hfu.residents.service.BaseResidentService;
import de.hfu.residents.service.ResidentServiceException; 

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import static org.easymock.EasyMock.*; 
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;

public class BaseResidentServiceMock {
	private List<Resident> residentsList;
	private BaseResidentService baseResidentService;
	private ResidentRepository residentRep;

	@Before
	public void testSetup() {
		residentsList= Arrays.asList(new Resident("Moritz", "Müller", "Teststraße", "Testdorf", null), new Resident("Tim", "Tester", "Josefdorer", "Freiburg", null), new Resident("Lutz", "Weigold", "Beispielstraße", "Schlumpfhausen", null));
		residentRep= createMock(ResidentRepository.class);
		baseResidentService= new BaseResidentService();
	}

	@Test
	public void test1() throws ResidentServiceException{
		Resident filterResident = new Resident("Lutz", "Weigold", "Beispielstraße", "Schlumpfhausen", null);
		expect(residentRep.getResidents()).andReturn(residentsList).times(5);
		replay(residentRep);
		baseResidentService.setResidentRepository(residentRep);
		String expectedFamilyName= "Weigold";
		String expectedCity= "Schlumpfhausen";
		String expectedName= "Lutz";
		assertThat(baseResidentService.getUniqueResident(filterResident).getFamilyName(), equalTo(expectedFamilyName));
		assertThat(baseResidentService.getUniqueResident(filterResident).getCity(), equalTo(expectedCity));
		assertThat(baseResidentService.getUniqueResident(filterResident).getGivenName(), equalTo(expectedName));
		
	}
}