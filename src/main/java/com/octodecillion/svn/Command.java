/**
 * 
 */
package com.octodecillion.svn;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Properties;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.apache.commons.exec.PumpStreamHandler;

import com.google.common.base.Preconditions;
import com.google.common.io.Resources;

/**
 * Invoke SVN commands from Java.
 * Just log and list for now.
 * <p>
 * Requires the command line SVN executable.
 * These are specified in svn.properties file in class path.
 * 
 * Note: Thread safety has not been tested.
 * 
 * @author j.betancourt
 * 
 */
public class Command {
	
	/**
	 * Constructor.
	 * @throws IOException
	 */
	public Command() throws IOException {
		try(BufferedReader is = (Resources.asCharSource(Resources.getResource("svn.properties"),Charset.defaultCharset())).openBufferedStream()){
			Properties props = new Properties();
			props.load(is);
			commandLineClientLocation = props.getProperty("commandLineClientLocation",DEFAULT_SVN_PROGRAM_LOCATION);
			commandLineClientFileName = props.getProperty("commandLineClientFileName", DEFAULT_SVN_EXE);
		} 
	}
	
	/**
	 * <p>
	 * 
	 * @see http://svnbook.red-bean.com/en/1.4/svn.ref.svn.c.log.html
	 * 
	 * @param url
	 * @return
	 * @throws IOException
	 * @throws ExecuteException
	 * @since Nov 18, 2014
	 */
	public String log(String url) throws ExecuteException, IOException {
		Preconditions.checkNotNull(url, "url param is null");
		CommandLine cmdLine = createCmdLine()
				.addArgument("log").addArgument("--stop-on-copy")
				.addArgument("--verbose").addArgument("--xml").addArgument(url);

		return executeCommand(cmdLine);
	}

	/**
	 * @see http://svnbook.red-bean.com/en/1.4/svn.ref.svn.c.list.html
	 * @param baseURL
	 * @return
	 * @throws IOException
	 * @throws ExecuteException
	 * @since Nov 19, 2014
	 */
	public String list(String url) throws ExecuteException,IOException {
		Preconditions.checkNotNull(url, "url param is null");
		CommandLine cmdLine = createCmdLine().addArgument("list")
				.addArgument("--xml").addArgument(url);
		return executeCommand(cmdLine);
	}

	/** */
	private CommandLine createCmdLine() {
		return CommandLine.parse(commandLineClientFileName);
	}

	/**
	 * Execute command line at working directory.
	 * @param cmdLine
	 * @return String that captured the error and standard output streams
	 * @throws ExecuteException
	 * @throws IOException
	 * @since 2014-11-20
	 */
	private String executeCommand(CommandLine cmdLine) throws ExecuteException,IOException {
		DefaultExecutor exec = new DefaultExecutor();
		exec.setWorkingDirectory(new File(commandLineClientLocation));

		String str ="";
		try(ByteArrayOutputStream outputStream = new ByteArrayOutputStream()){
			exec.setStreamHandler(new PumpStreamHandler(outputStream));
			exec.execute(cmdLine);
			str =  outputStream.toString();
		}
		
		return str;
	}

	private static final String DEFAULT_SVN_EXE = "svn.exe";
	private static final String DEFAULT_SVN_PROGRAM_LOCATION = "\\Program Files\\CollabNet\\Subversion Client";
	
	private String commandLineClientLocation = DEFAULT_SVN_PROGRAM_LOCATION;
	private String commandLineClientFileName = DEFAULT_SVN_EXE;

}