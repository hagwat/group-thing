package swen222_group_project.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import swen222_group_project.*;

public class Startuptest {

	@Test
	public void test() {
		String[] args = new String[] {"-server","1"};
		new Main().main(args);
		args = new String[] {"-connect", "a url"};
		new Main().main(args);
	}
}
