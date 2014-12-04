/**
 * 
 */
package com.octodecillion.svn;

import java.io.IOException;

import mockit.Mock;
import mockit.MockUp;
import mockit.integration.junit4.JMockit;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.ExecuteException;
import org.hamcrest.core.IsEqual;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;


/**
 * @author jbetancourt
 *
 */
@RunWith(JMockit.class)
public class CommandTest {

	private Command cmd;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		cmd = new Command();
	}

	/**
	 * Test method for {@link com.octodecillion.svn.Command#log(java.lang.String)}.
	 * @throws IOException 
	 * @throws ExecuteException 
	 */
	@Test
	public final void testLog() throws ExecuteException, IOException {
		
		/**	 */
		MockUp<Command> m = new MockUp<Command>() {
			/** */
			@Mock
			public String executeCommand(CommandLine cmdLine){
				String expected = "[svn.exe, log, --stop-on-copy, --verbose, --xml, http://some/repo/branch]";
				Assert.assertThat(cmdLine.toString(),IsEqual.equalTo(expected));
				return "";
			}
		};	
		
		cmd.log("http://some/repo/branch");
		m.tearDown();
	}

	/**
	 * Test method for {@link com.octodecillion.svn.Command#list(java.lang.String)}.
	 * @throws IOException 
	 * @throws ExecuteException 
	 */
	@Test
	public final void testList() throws ExecuteException, IOException {
		/**	 */
		MockUp<Command> m = new MockUp<Command>() {
			/** */
			@Mock
			public String executeCommand(CommandLine cmdLine){
				String expected = "[svn.exe, list, --xml, http://some/repo/branch]";
				Assert.assertThat(cmdLine.toString(),IsEqual.equalTo(expected));
				return "";
			}
		};	
		
		cmd.list("http://some/repo/branch");
		m.tearDown();
	}

}
