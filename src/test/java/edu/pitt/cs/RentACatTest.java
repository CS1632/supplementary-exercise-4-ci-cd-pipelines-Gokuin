//Code written by Taittinger Gabelhart and Micheal Kharvoski 
//For CS1632 QA Exercise 2
package edu.pitt.cs;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.mockito.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RentACatTest {

	/**
	 * The test fixture for this JUnit test. Test fixture: a fixed state of a set of
	 * objects used as a baseline for running tests. The test fixture is initialized
	 * using the @Before setUp method which runs before every test case. The test
	 * fixture is removed using the @After tearDown method which runs after each
	 * test case.
	 */

	RentACat r; // Object to test
	Cat c1; // First cat object
	Cat c2; // Second cat object
	Cat c3; // Third cat object

	@Before
	public void setUp() throws Exception {
		// Turn on automatic bug injection in the Cat class, to emulate a buggy Cat.
		// Your unit tests should work regardless of these bugs.
		Cat.bugInjectionOn = true;

		// INITIALIZE THE TEST FIXTURE
		// 1. Create a new RentACat object and assign to r
		r = RentACat.createInstance();

		// 2. Create an unrented Cat with ID 1 and name "Jennyanydots", assign to c1
		// TODO: Fill in
		c1 = Mockito.mock(Cat.class);
		Mockito.when(c1.getId()).thenReturn(1);
		Mockito.when(c1.toString()).thenReturn("ID " + 1 + ". " + "Jennyanydots");

		// 3. Create an unrented Cat with ID 2 and name "Old Deuteronomy", assign to c2
		// TODO: Fill in
		c2 = Mockito.mock(Cat.class);
		Mockito.when(c2.getId()).thenReturn(2);
		Mockito.when(c2.toString()).thenReturn("ID " + 2 + ". " + "Old Deuteronomy");

		// 4. Create an unrented Cat with ID 3 and name "Mistoffelees", assign to c3
		// TODO: Fill in
		c3 = Mockito.mock(Cat.class);
		Mockito.when(c3.getId()).thenReturn(3);
		Mockito.when(c3.toString()).thenReturn("ID " + 3 + ". " + "Mistoffelees");
	}

	@After
	public void tearDown() throws Exception {
		// Not necessary strictly speaking since the references will be overwritten in
		// the next setUp call anyway and Java has automatic garbage collection.
		r = null;
		c1 = null;
		c2 = null;
		c3 = null;
	}

	/**
	 * Test case for Cat getCat(int id).
	 * 
	 * <pre>
	 * Preconditions: r has no cats.
	 * Execution steps: Call getCat(2).
	 * Postconditions: Return value is null.
	 * </pre>
	 */

	@Test
	public void testGetCatNullNumCats0() {
		Cat c = r.getCat(2);
		assertNull("calling getCat(2) and seeing if return value is null", c);
	}

	/**
	 * Test case for Cat getCat(int id).
	 * 
	 * <pre>
	 * Preconditions: c1, c2, and c3 are added to r using addCat(Cat c).
	 * Execution steps: Call getCat(2).
	 * Postconditions: Return value is not null.
	 *                 Returned cat has an ID of 2.
	 * </pre>
	 */

	@Test
	public void testGetCatNumCats3() {
		// TODO
		r.addCat(c1);
		r.addCat(c2);
		r.addCat(c3);

		Cat c = r.getCat(2);
		assertNotNull("calling getCat(2) and seeing if return value is not null", c);
		assertEquals(2, c.getId());
	}

	/**
	 * Test case for boolean catAvailable(int id).
	 * 
	 * <pre>
	 * Preconditions: r has no cats.
	 * Execution steps: Call catAvailable(2).
	 * Postconditions: Return value is false.
	 * </pre>
	 */

	@Test
	public void testCatAvailableFalseNumCats0() {
		assertFalse("called catAvailable without r having any cats, should be false", r.catAvailable(2));
	}

	/**
	 * Test case for boolean catAvailable(int id).
	 * 
	 * <pre>
	 * Preconditions: c1, c2, and c3 are added to r using addCat(Cat c).
	 *                c3 is rented.
	 *                c1 and c2 are not rented.
	 * Execution steps: Call catAvailable(2).
	 * Postconditions: Return value is true.
	 * </pre>
	 */

	@Test
	public void testCatAvailableTrueNumCats3() {
		// TODO
		Mockito.when(c2.getRented()).thenReturn(false);

		r.addCat(c1);
		r.addCat(c2);
		r.addCat(c3);
		r.rentCat(3);
		assertTrue("c3 rented, c1 and c2 not rented, ret value should be true", r.catAvailable(2));

	}

	/**
	 * Test case for boolean catAvailable(int id).
	 * 
	 * <pre>
	 * Preconditions: c1, c2, and c3 are added to r using addCat(Cat c).
	 *                c2 is rented.
	 *                c1 and c3 are not rented.
	 * Execution steps: Call catAvailable(2).
	 * Postconditions: Return value is false.
	 * </pre>
	 */

	@Test
	public void testCatAvailableFalseNumCats3() {
		/*this function is failing when its ran on RentACatBuggy
			Failure Message: testRentCatFailureNumCats3(RentACatTestSolution): 3 cats and cat is rented out but rentCat(2) returns true*/
		Mockito.when(c2.getRented()).thenReturn(true);
		r.addCat(c1);//cat 1, id 1
		r.addCat(c2);//cat 2, id 2
		r.addCat(c3);//cat 3, id 3
		r.rentCat(2);// cat 2 should now be rented


		// need to mock the behavior for it to be rented
		// currently the behavior is not there

		assertFalse("c2 rented, c1 and c3 not rented, ret value should be false", r.catAvailable(2));
	}

	/**
	 * Test case for boolean catExists(int id).
	 * 
	 * <pre>
	 * Preconditions: r has no cats.
	 * Execution steps: Call catExists(2).
	 * Postconditions: Return value is false.
	 * </pre>
	 */

	@Test
	public void testCatExistsFalseNumCats0() {
		assertFalse("r has no cats, call catExists(2), ret val should be false", r.catExists(2));
	}

	/**
	 * Test case for boolean catExists(int id).
	 * 
	 * <pre>
	 * Preconditions: c1, c2, and c3 are added to r using addCat(Cat c).
	 * Execution steps: Call catExists(2).
	 * Postconditions: Return value is true.
	 * </pre>
	 */

	@Test
	public void testCatExistsTrueNumCats3() {
		// TODO
		r.addCat(c1);//cat 1, id 1
		r.addCat(c2);//cat 2, id 2
		r.addCat(c3);//cat 3, id 3
		assertTrue("c1, c2 and c3 not rented, call catExists, ret value should be true", r.catExists(2));
	}

	/**
	 * Test case for String listCats().
	 * 
	 * <pre>
	 * Preconditions: r has no cats.
	 * Execution steps: Call listCats().
	 * Postconditions: Return value is "".
	 * </pre>
	 */

	@Test
	public void testListCatsNumCats0() {
		// TODO
		assertEquals("r has no cats, call listCats(), ret val should be '''' ", "", r.listCats());
	}

	/**
	 * Test case for String listCats().
	 * 
	 * <pre>
	 * Preconditions: c1, c2, and c3 are added to r using addCat(Cat c).
	 * Execution steps: Call listCats().
	 * Postconditions: Return value is "ID 1. Jennyanydots\nID 2. Old
	 *                 Deuteronomy\nID 3. Mistoffelees\n".
	 * </pre>
	 */

	@Test
	public void testListCatsNumCats3() {
		// TODO
		r.addCat(c1);//cat 1, id 1
		r.addCat(c2);//cat 2, id 2
		r.addCat(c3);//cat 3, id 3
		assertEquals("ID 1. Jennyanydots\nID 2. Old Deuteronomy\nID 3. Mistoffelees\n", r.listCats());
	}

	/**
	 * Test case for boolean rentCat(int id).
	 * 
	 * <pre>
	 * Preconditions: r has no cats.
	 * Execution steps: Call rentCat(2).
	 * Postconditions: Return value is false.
	 * </pre>
	 */

	@Test
	public void testRentCatFailureNumCats0() {
		assertFalse("r has no cats to rent should return false when a cat is rented",r.rentCat(2));
	}

	/**
	 * Test case for boolean rentCat(int id).
	 * 
	 * <pre>
	 * Preconditions: c1, c2, and c3 are added to r using addCat(Cat c).
	 *                c2 is rented.
	 * Execution steps: Call rentCat(2).
	 * Postconditions: Return value is false.
	 *                 c1.rentCat(), c2.rentCat(), c3.rentCat() are never called.
	 * </pre>
	 * 
	 * Hint: See sample_code/mockito_example/NoogieTest.java in the course
	 * repository for an example of behavior verification. Refer to the
	 * testBadgerPlayCalled method.
	 */

	@Test
	public void testRentCatFailureNumCats3() {
		//this function is failing when its ran on RentACatBuggy
		/*RentACatTest passed but RentACatTestSolution failed.  Failure details:
			testRentCatFailureNumCats3(RentACatTestSolution): 3 cats and cat 2 is rented but rentCat(2) returns true */

		Mockito.when(c2.getRented()).thenReturn(true);
		r.addCat(c1);//cat 1, id 1
		r.addCat(c2);//cat 2, id 2
		r.addCat(c3);//cat 3, id 3
		r.rentCat(2);
		
		assertFalse("c1, c3, c2 added, c3 rented  ret value should be false when trying to rent a second time", r.rentCat(2));

		Mockito.verify(c1, never()).rentCat();
		Mockito.verify(c2, never()).rentCat();
		Mockito.verify(c3, never()).rentCat();
	}

	/**
	 * Test case for boolean returnCat(int id).
	 * 
	 * <pre>
	 * Preconditions: r has no cats.
	 * Execution steps: Call returnCat(2).
	 * Postconditions: Return value is false.
	 * </pre>
	 */

	@Test
	public void testReturnCatFailureNumCats0() {
		// TODO
		assertFalse("r had no cats, call returnCat(2), ret val should be false", r.returnCat(2));

	}

	/**
	 * Test case for boolean returnCat(int id).
	 * 
	 * <pre>
	 * Preconditions: c1, c2, and c3 are added to r using addCat(Cat c).
	 *                c2 is rented.
	 * Execution steps: Call returnCat(2).
	 * Postconditions: Return value is true.
	 *                 c2.returnCat() is called exactly once.
	 *                 c1.returnCat() and c3.returnCat are never called.
	 * </pre>
	 * 
	 * Hint: See sample_code/mockito_example/NoogieTest.java in the course
	 * repository for an example of behavior verification. Refer to the
	 * testBadgerPlayCalled method.
	 */

	@Test
	public void testReturnCatNumCats3() {
		//this function is failing when its ran on RentACatBuggy
		/*RentACatTest passed but RentACatTestSolution failed.  Failure details:
			testReturnCatNumCats3(RentACatTestSolution): 
			Wanted but not invoked:
			cat.returnCat();
			-> at RentACatTestSolution.testReturnCatNumCats3(RentACatTestSolution.java:278)

			However, there were other interactions with this mock:
			cat.getId();
			-> at RentACatBuggy.getCat(RentACatBuggy.java:143)

			cat.getRented();
			-> at RentACatBuggy.returnCat(RentACatBuggy.java:21) */

		Mockito.when(c2.getRented()).thenReturn(true);
		r.addCat(c1);//cat 1, id 1
		r.addCat(c2);//cat 2, id 2
		r.addCat(c3);//cat 3, id 3
		r.rentCat(2);

		assertTrue(r.returnCat(2));

		//make sure that c2's returnCat() funciton is only called once
		Mockito.verify(c2, times(1)).returnCat();
		Mockito.verify(c1, never()).returnCat();
		Mockito.verify(c3, never()).returnCat();
	}
}
