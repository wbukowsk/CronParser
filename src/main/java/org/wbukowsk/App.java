package org.wbukowsk;

import org.wbukowsk.input.ArgumentParser;
import org.wbukowsk.output.OutputData;
import org.wbukowsk.output.OutputDataPrinter;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        String mainArgument = ArgumentParser.parse(args);
        MainService mainService = new MainService();
        OutputData outputData = mainService.produceOutputData(mainArgument);
        OutputDataPrinter.printOutput(outputData);
    }
}
