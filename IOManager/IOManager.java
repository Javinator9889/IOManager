/**
 * IOManager - Java lib for reading from keyboard and files. Also printing
 * Copyright (C) 2017 Javinator9889 - https://goo.gl/6tcVR3
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * The license written above must be included in all distributions of this lib
 * with the author, year and link to the original lib.
 *
 * The license written above must be included in all distributions of this lib
 * with the author, year and link to the original lib.
 *
 * Contact me on: javialonso007@hotmail.es
 */

/**
 * The license written above must be included in all distributions of this lib
 * with the author, year and link to the original lib.
 */
package IOManager;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class IOManager {
    private static BufferedReader bufferedReader;
    private static BufferedWriter bufferedWriter;
    private static String FILENAME = null;
    private static String ENCODING = null;
    private static IOManager IO = new IOManager();

    /**
     * As the constructor is 'private', we need a method for getting the class without @params.
     * @return an instance of the class, with everything needed initialized.
     */
    public static IOManager initIO() {
        return IO;
    }

    /**
     * Simple method for writing (is the same as using "System.out.print" but simpler).
     * It follows "C printf" usage, as '\n' is required for a new line
     * @param message has the data you want to write
     */
    public void write(Object message) {
        System.out.print(String.valueOf(message));
    }

    /**
     * Prints a new line in screen
     */
    public void writeln() {
        System.out.println();
    }

    /**
     * Method for writing with a new line at the end
     * @param message has the data you want to write
     */
    public void writeln(Object message) {
        write(message);
        writeln();
    }

    /**
     * Method for reading almost everything from keyboard: int, char, String, char[], boolean, etc.
     * @param displayMessage prints in console a custom message when the input is requested to the user
     * @param saveVar is an Object where you will save your input. As Java pass Objects by value, it has to return the
     *                new value the user put from keyboard. In this case, it is used to determine which class/object is.
     * @param <Any> has the ability of returning any type of data. So it can return int, char, etc without using
     *             overloaded methods, only one.
     * @return gets the value the user put from keyboard.
     */
    public <Any> Any readKeyboard(String displayMessage, Object saveVar) {
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            if (displayMessage != null)
                write(displayMessage);
            switch (saveVar.getClass().getName()) {
                case "java.lang.Integer": {
                    saveVar = Integer.parseInt(bufferedReader.readLine());
                    break;
                }
                case "java.lang.Character": {
                    saveVar = bufferedReader.readLine().charAt(0);
                    break;
                }
                case "java.lang.String": {
                    saveVar = bufferedReader.readLine();
                    break;
                }
                case "java.lang.Byte": {
                    saveVar = Byte.parseByte(bufferedReader.readLine());
                    break;
                }
                case "java.lang.Long": {
                    saveVar = Long.parseLong(bufferedReader.readLine());
                    break;
                }
                case "java.lang.Float": {
                    saveVar = Float.parseFloat(bufferedReader.readLine());
                    break;
                }
                case "java.lang.Double": {
                    saveVar = Double.parseDouble(bufferedReader.readLine());
                    break;
                }
                case "java.lang.Short": {
                    saveVar = Short.parseShort(bufferedReader.readLine());
                    break;
                }
                case "java.lang.Boolean": {
                    saveVar = Boolean.parseBoolean(bufferedReader.readLine());
                    break;
                }
                case "[C": {
                    saveVar = bufferedReader.readLine().toCharArray();
                    break;
                }
                default: {
                    throw new IOManager.IOErrors.AttributesError("Incompatible typos were found.\nTrying to read String/Integer/Byte... and found: " + saveVar.getClass().getName());
                }
            }
            return ((Any) (saveVar.getClass().cast(saveVar)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Method for reading files, by passing the filename as a String.
     * @param filename is a String which has the filename. For a correct usage, this must include all the path to the
     *                 file: String filename = "C:\\Users\\myFile.txt";
     */
    public void openFile(String filename) {
        try {
            String encoding = new InputStreamReader(new FileInputStream(filename)).getEncoding();
            if (encoding == null || encoding.equals(""))
                encoding = "UTF-8";
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(filename), encoding));
            bufferedWriter = new BufferedWriter(new FileWriter(new File(filename), true));
            FILENAME = filename;
            ENCODING = encoding;
        } catch (UnsupportedEncodingException e) {
            write("The encoding you choose is not supported by the system. Full trace: ");
            write("\n");
            e.printStackTrace();
        } catch (IOException e) {
            write("It is recommended to include all the path to the file. In Windows, add double backslash (\\\\)\n");
            e.printStackTrace();
        }
    }

    /**
     * Method for getting the object for reading files
     * @return BufferedReader -> https://docs.oracle.com/javase/8/docs/api/java/io/BufferedReader.html
     */
    public BufferedReader getFileReader() {
        return bufferedReader;
    }

    /**
     * Method for getting the object for writing files
     * @return BufferedWriter -> https://docs.oracle.com/javase/8/docs/api/java/io/BufferedWriter.html
     */
    public BufferedWriter getFileWriter() {
        return bufferedWriter;
    }

    /**
     * Method for getting the filename of the current opened file
     * @return String which contains filename
     */
    public String getFilename() {
        return FILENAME;
    }

    /**
     * Method for getting the encoding of the current opened file
     * @return String which contains encoding
     */
    public String getEncoding() {
        return ENCODING;
    }

    /**
     * Method for creating files
     * @param filename contains the name of the file
     * @param isBytes is true when a bytes object will be created. False, in other case
     */
    public void createFile(String filename, boolean isBytes) {
        if (isBytes) {
            try {
                new FileOutputStream(filename).close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                new PrintWriter(filename).close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Returns a String that contains one read line from file
     * @return String
     */
    public String readLine() {
        try {
            return bufferedReader.readLine();
        } catch (IOException e) {
            write("There was an error while trying to read file. Full trace: \n");
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Get current number of lines in file
     * @return long
     */
    public long countLines() {
        try {
            return Files.lines(Paths.get(FILENAME)).count();
        } catch (IOException e) {
            write("There was an error while obtaining the count of the lines of the file you opened. Full trace: \n");
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * Resets read file for viewing new changes made
     */
    public void resetFile() {
        try {
            bufferedReader.close();
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(FILENAME), ENCODING));
        } catch (IOException e) {
            write("There was an error while trying to reset your file. Full trace: \n");
            e.printStackTrace();
        }
    }

    /**
     * Writes into file the object
     * @param message contains the data that will be written to the file
     */
    public void fWrite(Object message) {
        try {
            bufferedWriter.write(String.valueOf(message));
        } catch (IOException e) {
            write("There was an error while trying to write into your file. Full trace: \n");
            e.printStackTrace();
        }
    }

    /**
     * Writes a new line into the file
     */
    public void fWriteln() {
        try {
            bufferedWriter.newLine();
        } catch (IOException e) {
            write("There was an error while trying to write into your file. Full trace: \n");
            e.printStackTrace();
        }
    }

    /**
     * Writes the message and a new line into the file
     * @param message contains the data that will be written to the file
     */
    public void fWriteln(Object message) {
        fWrite(message);
        fWriteln();
    }

    /**
     * Writes a bytes object (array) into the opened file
     * @param message is a byte[] that contains the data that will be written
     * Changes are applied automatically
     */
    public void fWriteb(byte[] message) {
        try {
            Files.write(Paths.get(FILENAME), message);
            resetFile();
        } catch (IOException e) {
            write("There was an error while trying to write bytes to the file. Full trace: \n");
            e.printStackTrace();
        }
    }

    /**
     * Writes a bytes object (array) into the opened file
     * @param message is a String that contains the bytes object
     * Changes are applied automatically
     */
    public void fWriteb(String message) {
        fWriteb(message.getBytes());
    }

    /**
     * When writing a file, until this method is called, no changes are made
     */
    public void appendChanges() {
        try {
            bufferedWriter.flush();
            resetFile();
        } catch (IOException e) {
            write("There was an error while trying to append the new values to your file. Full trace: \n");
            e.printStackTrace();
        }
    }

    /**
     * Method for closing and opened file
     * @return 0 when file is closed correctly, -1 in other case
     */
    public int closeFile() {
        try {
            bufferedReader.close();
            bufferedWriter.close();
            FILENAME = null;
            ENCODING = null;
            return 0;
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * Class for handling and creating special exceptions in IOManager.
     * Public for handling exceptions in your main code.
     */
    public static class IOErrors extends RuntimeException {
        public IOErrors(String cause) {
            super(cause);
        }

        public static class AttributesError extends IOErrors {
            public AttributesError(String cause) {
                super(cause);
            }
        }
    }

    /**
     * Link to the official gist on GitHub: https://goo.gl/6tcVR3
     */
    public static void HELP() {
        IO.write("Please, go to -> https://goo.gl/6tcVR3 <- for getting a full guide");
    }

    /**
     * Cleans all the instances of the class
     * @throws Throwable when it is not possible to perform an action
     */
    public void close() throws Throwable {
        try {
            bufferedReader.close();
            bufferedWriter.close();
            FILENAME = null;
            ENCODING = null;
        } finally {
            super.finalize();
        }
    }
}
