package org.wbukowsk;

import org.wbukowsk.input.ArgumentParser;
import org.wbukowsk.output.OutputData;
import org.wbukowsk.output.OutputDataPrinter;

public class App 
{
    public static void main( String[] args )
    {
        String mainArgument = ArgumentParser.parse(args);
        OutputData outputData = MainService.produceOutputData(mainArgument);
        OutputDataPrinter.printOutput(outputData);
    }
}
