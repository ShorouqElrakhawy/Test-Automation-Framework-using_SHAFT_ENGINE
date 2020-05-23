package tests.cli.cmd;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.shaft.cli.TerminalActions;
import com.shaft.tools.io.ExcelFileManager;
import com.shaft.validation.Assertions;

import io.qameta.allure.Epic;


/**
 * 
 * @author Shorouq Elrakhawy
 *
 */

@Epic("CLI -> CMD Tests")
public class cmdTests {
    private TerminalActions terminal;
    private ExcelFileManager testDataReader;

    @Test(description = "TC6 - Verify JDK of current pc is up to date")
    public void verifyCurrentJDK_isCorrect() {
	String getCurrentJavaVersionCommand = "cmd.exe /c javac -version";
	String consoleLog = performTerminalCommand(getCurrentJavaVersionCommand);
	Assertions.assertEquals(testDataReader.getCellData("TC6_expectedJDK"), consoleLog.replaceAll("\\D+", ""));
    }

    @Test(description = "TC7 - Verify CLI_readMe file content")
    public void verifyFileContent() {
	String createCurrentCommand = "cmd.exe /c cd " + System.getProperty("user.dir") +"/"
		+ System.getProperty("testDataFolderPath") + "CLI Test Data/" + " && type CLI_readMe.txt";
	String consoleLog = performTerminalCommand(createCurrentCommand);
	Assertions.assertEquals(testDataReader.getCellData("TC7_expectedContent"), consoleLog);
    }

    private String performTerminalCommand(String command) {
	terminal = new TerminalActions();
	return terminal.performTerminalCommand(command);
    }

    @BeforeClass
    public void beforeClass() {
	testDataReader = new ExcelFileManager(System.getProperty("testDataFolderPath") + "CLI Test Data/cmdTests.xlsx");
    }

}
