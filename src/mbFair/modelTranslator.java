package mbFair;

import picocli.CommandLine;

import java.io.File;
import java.util.concurrent.Callable;

@CommandLine.Command(name = "generate",
		aliases = {"-g"},
		version = "1.0.0",
		mixinStandardHelpOptions = true,
		requiredOptionMarker = '*',
		description = "This is a Sub Command to 'mbfair' which generates initializations to the software fairness analysis",
		header = "Add Initializations SubCommand",
		optionListHeading = "%nOptions are:%n",
		footerHeading = "%nCopyright",
		footer = "%nDeveloped by SFAT")

public class modelTranslator implements Callable<Integer>{
	@CommandLine.Option(
			names = {"-f", "--filename"},
			required = true,
			description = "Name of model from Papyrus")
	String filename;
	String projectPath = new File("").getAbsolutePath();
	String pathToHugoRt;
	String pathToModel;

	String pathToPythonMain;
	public static void main(String[] args) {
		System.out.println("Public void");
		int exitStatus = new CommandLine(new modelTranslator()).setCaseInsensitiveEnumValuesAllowed(true).execute(args);
		System.exit(exitStatus);
	}
	@Override
	public Integer call() throws Exception {
		this.translateToPML();
		this.generateInitalizations();
		return 0;
	}
	public boolean translateToPML() {
		Command cmd = new Command();
		this.pathToHugoRt = this.projectPath + "/hugort-0_8a/lib/hugort.jar";
		this.pathToModel = this.filename;
		String commandString = "java -jar " + this.pathToHugoRt + " -opromela " + this.pathToModel;
		System.out.println(commandString);
		String result = cmd.executeShellCommand(commandString);
		System.out.println(result);
		if (result.contains("Writing Promela system")) {
			return true;
		}
		cmd.executeShellCommand("mv model.pml model.new.pml");
		return false;
	}

	public void generateInitalizations() {
		Command cmd = new Command();
		this.pathToHugoRt = this.projectPath + "/hugort-0_8a/lib/hugort.jar";
		this.pathToModel = this.filename;
		this.pathToPythonMain = this.projectPath + "/pythonscripts/main.py";
		String commandString = "java -jar " + this.pathToHugoRt + " -opromela " + this.pathToModel;
		System.out.println(this.pathToModel + "path to model");
		String Command = "python3 " + this.pathToPythonMain + " --model " + this.pathToModel;
		System.out.println(Command);
		String result = cmd.executeShellCommand(Command);
		System.out.println(result);
	}
}

