package swen222_group_project.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import swen222_group_project.Main;

public class ClientTest {

	@Test
	public void test() {
		String[] args2 = new String[] { "-connect", Main.getAddy() };
		System.out.println("client " + args2);
		Main.main(args2);
	}

}
