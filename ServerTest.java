package swen222_group_project.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import swen222_group_project.*;

public class ServerTest {

	@Test
	public void testServer() {
		String[] args1 = new String[] { "-server", "4" };
		Main.main(args1);
	}
}
