package de.hfu;

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

public class BaseResidentServiceTest {
	private List<Resident> residentsList;
	private BaseResidentService baseResidentService;
	private ResidentRepository residentRep;

	@Before
	public void testSetup() {
		Resident r1= new Resident("Moritz", "Müller", "Teststraße", "Testdorf", null);
		Resident r2= new Resident("Tim", "Tester", "Josefdorer", "Freiburg", null);
		Resident r3= new Resident("Lutz", "Weigold", "Beispielstraße", "Schlumpfhausen", null);
		residentsList= Arrays.asList(r1, r2, r3);
		residentRep= createMock(ResidentRepository.class);
		baseResidentService= new BaseResidentService();
	}

	@Test
	public void test1() throws ResidentServiceException{
		Resident rf= new Resident("Lutz", "Weigold", "Beispielstraße", "Schlumpfhausen", null);
		expect(residentRep.getResidents()).andReturn(residentsList).times(3);
		replay(residentRep);
		baseResidentService.setResidentRepository(residentRep);
		String expectedFamilyName= "Weigold";
		String expectedCity= "Schlumpfhausen";
		String expectedName= "Lutz";
		assertThat(baseResidentService.getUniqueResident(rf).getFamilyName(), equalTo(expectedFamilyName));
		assertThat(baseResidentService.getUniqueResident(rf).getCity(), equalTo(expectedCity));
		assertThat(baseResidentService.getUniqueResident(rf).getGivenName(), equalTo(expectedName));
		
	}
}