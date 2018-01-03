import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import com.beust.jcommander.internal.Lists;

import java.util.List;

/**
 * Created by yasin_000 on 29.12.2017.
 */
@Parameters(separators = "=")
public class ArgsClass {

    @Parameter
    public List<String> parameters = Lists.newArrayList();

    @Parameter(names = {"-l", "--locale"}, description = "The following staements are valid to make sorting locale sensitive: \n -l tr-TR, --locale tr-TR , -l en-US, --locale en-US")
    private String locale = "en-US";

    @Parameter(names = {"-r", "--reverse"} , description = "The following staements are valid to make sorting reverse: \n -r , --reverse")
    private boolean isReverse = false;

    @Parameter(names = {"-i", "--ignore-case"}, description = "The following statements are valid to make sorting ignore case: \n -i , --ignore-case")
    private boolean isIgnoreCase = false;

    @Parameter(names = {"-o", "--output"}, description = "The following statements are valid to obtain output file: \n -o example.txt , -o=example.txt , --output example.txt, --output=example.txt")
    private String outputFile = null;


    public String getLocale() {
        return locale;
    }

    public boolean isReverse() {
        return isReverse;
    }

    public boolean isIgnoreCase() {
        return isIgnoreCase;
    }

    public String getOutputFile() {
        return outputFile;
    }

    public List<String> getParameters() {
        return parameters;
    }
}
