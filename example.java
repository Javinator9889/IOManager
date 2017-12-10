import IOManager.IOManager;

import java.util.Arrays;

public class example {
    public static void main(String[] args) {
        //Init IOManager
        IOManager IO = IOManager.initIO();

        //Write some text
        IO.write("Hi, this is a sample\n");
        IO.write(15);
        IO.write("\n");

        //Read from keyboard
        String read1 = "";
        read1 = IO.readKeyboard("This will appear as a message. Put a String: ", read1);
        IO.writeln();
        int read2 = 0;
        read2 = IO.readKeyboard("Antoher message. Put an integer: ", read2);
        IO.writeln();
        char[] read3 = new char[1024];
        read3 = IO.readKeyboard("The latest one. Put a String that will be saved as a char[]: ", read3);
        IO.writeln("-----------------------------------------------");

        //Display the results
        IO.write("First  value: "+ read1);
        IO.writeln();
        IO.write("Second value: "+ read2);
        IO.writeln();
        IO.write("Third  value: "+ Arrays.toString(read3));
        IO.writeln();
        IO.writeln("-----------------------------------------------");

        //Open a file
        IO.openFile("file.txt");

        //Display contents of file
        String file;
        while ((file = IO.readLine()) != null) {
            IO.writeln(file);
        }
        IO.writeln("-----------------------------------------------");

        //Show line number in a file and filename
        IO.writeln("Number of lines of \""+IO.getFilename()+"\": "+ IO.countLines());
        IO.writeln("-----------------------------------------------");

        //Create new non-bytes files
        IO.createFile("newFile.txt", false);

        //Create new bytes file
        IO.createFile("newFile.bytes", true);

        //Closing latest file and opening a new one
        IO.closeFile();
        IO.openFile("newFile.txt");

        //Writing content to the opened file
        IO.fWriteln(Math.PI * Math.E);
        IO.fWrite("That was the result of doing PI * E");

        //Append changes to the file
        IO.appendChanges();

        //Reading the content of the file
        String file2;
        while ((file2 = IO.readLine()) != null) {
            IO.writeln(file2);
        }
        IO.writeln("\nThat is the content of \""+ IO.getFilename() + "\"");
        IO.writeln("-----------------------------------------------");

        //Closing latest file and opening a new one
        IO.closeFile();
        IO.openFile("newFile.bytes");

        //Writing a bytes-object
        IO.fWriteb("0 1b 15 17");

        //Saving changes
        IO.appendChanges();

        //Reading the content of the file
        String file3;
        while ((file3 = IO.readLine()) != null) {
            IO.writeln(file3);
        }
        IO.writeln("\nThat is the content of \""+ IO.getFilename() + "\"");
        IO.writeln("-----------------------------------------------");

        //Closing the file and the class
        IO.closeFile();
        try {
            IO.close();
        } catch (Throwable e) {
            System.out.println("There was an error while trying to close the class: IOManager. Full trace:");
            e.printStackTrace();
        }
        // This exception needs to be handled by the user
    }
}
