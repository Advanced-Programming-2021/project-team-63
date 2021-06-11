package View.CommandLines;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import java.io.IOException;
import java.io.InputStream;

import opennlp.tools.sentdetect.SentenceDetector;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;

import java.util.Scanner;

public class CommandLine

{



    public Object run(String b) throws ParameterException {
        String[] a=b.split(" ");

        JCommander.newBuilder()
                .addObject(this)
                .build()
                .parse(a);
        return this;

    }
}
