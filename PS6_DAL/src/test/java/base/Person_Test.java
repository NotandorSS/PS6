package base;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import domain.PersonDomainModel;

public class Person_Test {
	

	private static PersonDomainModel per1;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		Date dDate = null;
		try {
			dDate = new SimpleDateFormat("yyyy-MM-dd").parse("1972-07-31");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		per1 = new PersonDomainModel();
		per1.setFirstName("Bert");
		per1.setLastName("Gibbons");
		per1.setBirthday(dDate);
		per1.setCity("Townsend");
		per1.setPostalCode(19010);
		per1.setStreet("214 Labrador Lane");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {		
	}
	
	@Test
	public void AddPersonTest()
	{		
		PersonDomainModel per;		
		per = PersonDAL.getPerson(per1.getPersonID());		
		assertNull("The Person shouldn't have been in the database",per);		
		PersonDAL.addPerson(per1);	
		
		per = PersonDAL.getPerson(per1.getPersonID());
		System.out.println(per1.getPersonID() + " found");
		assertNotNull("The Person should have been added to the database",per);
		
		PersonDAL.deletePerson(per1.getPersonID());
	}
	
	@Test
	public void UpdatePersonTest()
	{		
		PersonDomainModel per;
		final String C_LASTNAME = "Smith";
		
		per = PersonDAL.getPerson(per1.getPersonID());		
		assertNull("The Person shouldn't have been in the database",per);		
		PersonDAL.addPerson(per1);	
		
		per1.setLastName(C_LASTNAME);
		PersonDAL.updatePerson(per1);
		
		per = PersonDAL.getPerson(per1.getPersonID());

		assertTrue("Name Didn't Change",per1.getLastName() == C_LASTNAME);
		
		PersonDAL.deletePerson(per1.getPersonID());
	}

	@Test
	public void DeletePersonTest()
	{		
		PersonDomainModel per;		
		per = PersonDAL.getPerson(per1.getPersonID());		
		assertNull("The Person shouldn't have been in the database",per);	
		
		PersonDAL.addPerson(per1);			
		per = PersonDAL.getPerson(per1.getPersonID());
		System.out.println(per1.getPersonID() + " found");
		assertNotNull("The Person should have been added to the database",per);
	
		PersonDAL.deletePerson(per1.getPersonID());
		per = PersonDAL.getPerson(per1.getPersonID());
		assertNull("The person should not be in the database", per);
	}
	
	@Test
	public void GetPersonsTest()
	{
		PersonDomainModel per;		
		per = PersonDAL.getPerson(per1.getPersonID());		
		assertNull("The Person shouldn't have been in the database",per);	
		
		PersonDAL.addPerson(per1);			
		per = PersonDAL.getPerson(per1.getPersonID());
		System.out.println(per1.getPersonID() + " found");
		assertNotNull("The Person should have been added to the database",per);
		
		ArrayList<PersonDomainModel> persons = new ArrayList<PersonDomainModel>();
		persons = PersonDAL.getPersons();
		System.out.println(persons);
		assertNotNull("The persons should have been retrieved from the database", persons);
		
		PersonDAL.deletePerson(per1.getPersonID());
	}
	
	@Test
	public void GetPersonTest()
	{
		PersonDomainModel per;
		PersonDAL.addPerson(per1);			
		per = PersonDAL.getPerson(per1.getPersonID());
		System.out.println(per1.getPersonID() + " found");

			
		per = PersonDAL.getPerson(per1.getPersonID());		
		assertNotNull("The Person should have been in the database",per);	
		
		PersonDAL.deletePerson(per1.getPersonID());
		
	}
}