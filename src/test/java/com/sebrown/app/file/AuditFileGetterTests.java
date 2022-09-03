package com.sebrown.app.file;

//import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AuditFileGetterTests {

	@Test
	void test() {
		AuditFileGetter fileGetter = 
				new AuditFileGetter("./src/test/resources/new-file.xlsx","Vendor Not Found");
		fileGetter.getFile();
//		fail("Not yet implemented");
	}

}
