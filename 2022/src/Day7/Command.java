package src.Day7;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Command {

    public static enum Action {
        CD,
        LS
    }

    public Action action;
    public String file;

    public Command(String line) {
        Pattern commandRegex = Pattern.compile("\\$\\s(\\w{2})\\s?(\\w+|\\/|\\.{2})?");
        Matcher commandMatcher = commandRegex.matcher(line);

        if (commandMatcher.matches()) {
            switch(commandMatcher.group(1)) {
                case "cd":
                    this.action = Action.CD;
                    break;
                case "ls":
                    this.action = Action.LS;
                    break;
            }
            this.file = commandMatcher.group(2);
        }
    }

}
