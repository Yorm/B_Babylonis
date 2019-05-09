package bibliotheca;

import bibliotheca.books.BabylonianBook;
import bibliotheca.books.Book;
import bibliotheca.books.OldNorthBook;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class Main {

    public static void main(String... args){

        Options options = new Options();

        Option bookOption = new Option("b", "book", true, "Type of books. en for English. on for Old North ");
        bookOption.setRequired(true);
        options.addOption(bookOption);

        Option numberOption = new Option("n", "number", true, "Number of books. Must be a positive number.");
        numberOption.setRequired(true);
        options.addOption(numberOption);

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();

        CommandLine cmd = null;
        int numberOfBooks = -1;
        String typeOfBook = null;

        try {
            cmd = parser.parse(options, args);
            numberOfBooks = Integer.parseInt(cmd.getOptionValue("number"));
            typeOfBook = cmd.getOptionValue("book");
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("utility-name", options);
            System.exit(1);
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage() + " must be a positive number");
            formatter.printHelp("utility-name", options);
            System.exit(1);
        }

        Book book = null;
        switch (typeOfBook) {
            case "en" :
                book = new BabylonianBook();
                break;

            case "on" :
                book = new OldNorthBook();
                break;

            default:
                System.out.println(typeOfBook + " is a wrong type of book");
                formatter.printHelp("utility-name", options);
                System.exit(1);
        }

        book.makeBook(numberOfBooks);
    }
}
