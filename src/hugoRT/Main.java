package hugoRT;
import picocli.CommandLine;
import java.util.concurrent.Callable;
@CommandLine.Command(name = "mbfair",
        version = "1.0.0",
        mixinStandardHelpOptions = true,
        requiredOptionMarker = '*',
        description = "This is a software fairness analysis Tool.",
        header = "MBFair CLI",
        optionListHeading = "%nOptions are:%n",
        footerHeading = "%nCopyright",
        footer = "%nDeveloped by SFAT",
        subcommandsRepeatable = true,
        subcommands = {
                MBFair.class,
                modelTranslator.class
        }
)

public class Main implements Callable<Integer> {
    final Integer SUCCESS = 0;
    final Integer FAILURE = 1;

    public static void main(String[] args) {
        int exitStatus = new CommandLine(new Main())
                .setCaseInsensitiveEnumValuesAllowed(true)
                .execute(args);
        System.exit(exitStatus);
    }

    @Override
    public Integer call() throws Exception {
        System.out.println("[mbfair] Welcome to Software Fairness Analysis -- Version 1.0.0");
        return SUCCESS;
    }
}

