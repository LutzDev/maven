package de.hfu.Resident;

import static org.junit.Assert.assertEquals;



import java.sql.Date;

import org.junit.*;

import de.hfu.residents.domain.Resident;
import de.hfu.residents.service.BaseResidentService;
import de.hfu.residents.service.ResidentServiceException;

public class ResidentServiceTest {
	private BaseResidentService baseResidentService;
	private ResidentRepositoryStub residentRepositoryStub;

	//Wird vor derm Testen durchgeführt
	@SuppressWarnings("deprecation")
	@Before
	public void setUp() {
		this.baseResidentService = new BaseResidentService();
		this.residentRepositoryStub = new ResidentRepositoryStub();

		// Einträge werden zu Beginn der Liste hinzugefügt
		this.residentRepositoryStub.addToResidentList(new Resident("Lutz", "Weigold", "Hintergasse", "München", new Date(2003, 03, 03)));
		this.residentRepositoryStub.addToResidentList(new Resident("Moritz", "Müller", "Grünestraße", "Berlin", new Date(1943, 01, 12)));
		this.residentRepositoryStub.addToResidentList(new Resident("Tim", "Schneider", "Anonymestraße", "Bamberg", new Date(2003, 03, 03)));
		this.baseResidentService.setResidentRepository(this.residentRepositoryStub);

	}


	// ----------  getUniqueResident()  ---------- //


	@Test(expected=ResidentServiceException.class, timeout=1000)
	public void noUniqueResident() throws ResidentServiceException {
		Resident uniqueResident = new Resident("Tom", "Weigold", "Hintergasse", "München", new Date(2003, 03, 03));
		this.baseResidentService.getUniqueResident(uniqueResident);
	}



	// Der Name ist nicht eindeutig, da WildCards
	@Test (expected=ResidentServiceException.class, timeout=1000)
	public void uniqueResidentGivenName() throws ResidentServiceException {
		Resident uniqueResident = new Resident("*", "*", "*", "München", new Date(2003, 03, 03));
		this.baseResidentService.getUniqueResident(uniqueResident);
	}


	// Resident ist eindeutig
	@Test
	public void uniqueResidentFamilyName() {
		Resident uniqueResident = new Resident("Lutz", "Weigold", "Hintergasse", "München", new Date(2003, 03, 03));
		assertEquals(uniqueResident.getFamilyName(), this.baseResidentService.getFilteredResidentsList(uniqueResident).get(0).getFamilyName());
		assertEquals(uniqueResident.getStreet(), this.baseResidentService.getFilteredResidentsList(uniqueResident).get(0).getStreet());
	}


	// ---------- getFilteredResidentsList()  ---------- //


	// Mehr als ein Ergebnis beim Filtern
	@Test
	public void filteredResidentsListCity() {
		Resident filterResident = new Resident("*", "*", "*", "B*", new Date(2003, 03, 03));
		int checkedResults = 2; //Berlin, Bamberg
		assertEquals(checkedResults, this.baseResidentService.getFilteredResidentsList(filterResident).size());
	}


	//Filtern ohne Straßennahme
	@Test
	public void filteredResidentsListStreet() {
		Resident filterResident = new Resident("Lutz", "*", null, "*", new Date(2003, 03, 03));
		String checkedStreetName = "Hintergasse";
		assertEquals(checkedStreetName, this.baseResidentService.getFilteredResidentsList(filterResident).get(0).getStreet());
	}

	
	//Filter hat keine Rückgabe
	@Test
	public void filteredResidentsListWrongFilter() {
		Resident filterResident = new Resident("*", "Lehmann", "*", "*", new Date(2003, 03, 03));
		int checkedResults = 0; //Nichts gefunden
		assertEquals(checkedResults, this.baseResidentService.getFilteredResidentsList(filterResident).size());
	}
}
