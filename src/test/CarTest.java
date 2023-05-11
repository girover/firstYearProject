package test;

import org.junit.jupiter.api.Test;

import configs.Config;

class CarTest extends BaseTestCase {

	@Test
	void itCanAddNewCustomerToDatabase() {
		System.out.println(Config.get("database.sqlServer.dbName"));
		assertTrue(true);
	}
	@Test
	void itCanDoAnotherThing() {
		System.out.println(Config.get("gui.path"));
		assertTrue(true);
	}
	@Test
	void itCanDoMoreThings() {
		System.out.println(Config.get("log.driver"));
		assertTrue(true);
	}
	@Test
	void itCanDoMoreThingsssss() {
		System.out.println(Config.get("log.driver"));
		assertTrue(true);
	}

}
