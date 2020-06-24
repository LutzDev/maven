package de.hfu;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import de.hfu.residents.domain.Resident;
import de.hfu.residents.repository.ResidentRepositoryStub;
import de.hfu.residents.service.BaseResidentService;
import de.hfu.residents.service.ResidentServiceException;

public class ResidentServiceTest {
	private ResidentRepositoryStub residentRep;
	private BaseResidentService residentService;
	private Calendar date;

	@Before
	public void createQueue() {
		date= Calendar.getInstance();
		
		
		
		date.set(1987, 10, 10);
		Resident resident1= new Resident("Moritz", "Müller", "Teststraße", "Testdorf", null);
		Resident resident2= new Resident("Tim", "Tester", "Josefdorer", "Freiburg", null);
		Resident resident3= new Resident("Lutz", "Weigold", "Beispielstraße", "Schlumpfhausen", null);

		this.residentRep= new ResidentRepositoryStub();
		this.residentRep.addData(resident1);
		this.residentRep.addData(resident2);
		this.residentRep.addData(resident3);
		this.residentService= new BaseResidentService();
		this.residentService.setResidentRepository(this.residentRep);
	} 

	

	//Filter-Test mit Wildcard und mehreren Ergebnissen
	@Test
	public void inTest1(){
		Resident fr1= new Resident("*", "T*", "*", "*", new Date(date.getTimeInMillis()));
		Resident fr2= new Resident("*", "*", "*", "*", new Date(date.getTimeInMillis()));
		int expValue= 2;
		int expValue2= 4;

		assertEquals(expValue, this.residentService.getFilteredResidentsList(fr1).size());
		assertEquals(expValue2, this.residentService.getFilteredResidentsList(fr2).size());
	}

	//Falscher Filter
	@Test
	public void inTest2(){
		Resident fr1= new Resident("Lutzzzzzz", "Weieieiegold", "Keinestraße", "Schlumpfhausen", new Date(date.getTimeInMillis()));	
		int expValue= 0;
		assertEquals(expValue, this.residentService.getFilteredResidentsList(fr1).size());
	}
	
	//Filter-Test mit Wildcard
		@Test
		public void inTest3(){
			Resident fr1= new Resident("Lutz", null, "*", "*", new Date(date.getTimeInMillis()));
			Resident fr2= new Resident("Lutz", "Weigold", "*", "Schlumpfhausen", new Date(date.getTimeInMillis()));
			Resident fr3= new Resident("Lutz", "Weigold", "Beispielstraße", "*", new Date(date.getTimeInMillis()));
			String expLastName= "Weigold";
			String expName= "Lutz";
			String expCity= "Schlumpfhausen";

			assertEquals(expLastName, this.residentService.getFilteredResidentsList(fr1).get(0).getFamilyName());
			assertEquals(expName, this.residentService.getFilteredResidentsList(fr2).get(0).getGivenName());
			assertEquals(expCity, this.residentService.getFilteredResidentsList(fr3).get(0).getCity());
		}

	//Wildcards werden nicht erlaubt
	@Test(expected=ResidentServiceException.class, timeout=1000) 
	public void inTest4() throws ResidentServiceException{
		Resident fr1= new Resident("Lutz", "Weigold", "*", "Schlumpfhausen", new Date(500000));
		String expCity= "Schlumpfhausen";

		this.residentService.getUniqueResident(fr1);
	}

	//Das Ergebnis ist nicht eindeutig 
	@Test(expected=ResidentServiceException.class, timeout=1000) 
	public void inTest5() throws ResidentServiceException {
		Resident fr1= new Resident("Lutz", "Weigold", "Beispielstraße", "Schlumpfhausen", new Date(200000));

		this.residentService.getUniqueResident(fr1);
	}
	
	//Eindeutiges Ergebnis soll rückgegeben werden
		@Test
		public void inTest6() throws ResidentServiceException{
			Resident fr1= new Resident("Lutz", "Weigold", "Beispielstraße", "*", new Date(date.getTimeInMillis()));
			String expCity= "Schlumpfhausen";

			assertEquals(expCity, this.residentService.getUniqueResident(fr1).getCity());
		}
}
