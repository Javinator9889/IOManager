/**
 * IOManager - Java lib for reading from keyboard and files. Also printing
 * Copyright (C) 2017 Javinator9889 - https://goo.gl/6tcVR3
 * <p>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * <p>
 * The license written above must be included in all distributions of this lib
 * with the author, year and link to the original lib.
 * <p>
 * The license written above must be included in all distributions of this lib
 * with the author, year and link to the original lib.
 * <p>
 * Contact me on: javialonso007@hotmail.es
 * <p>
 * The license written above must be included in all distributions of this lib
 * with the author, year and link to the original lib.
 * <p>
 * The license written above must be included in all distributions of this lib
 * with the author, year and link to the original lib.
 */

/**
 * The license written above must be included in all distributions of this lib
 * with the author, year and link to the original lib.
 */

/**
 * Updated - 18/12/17
 */
package IOManager;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class IOManager {
    private static BufferedReader bufferedReader;
    private static BufferedWriter bufferedWriter;
    private static BufferedReader keyBufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static String FILENAME = null;
    private static String ENCODING = null;
    private static boolean BYTES = false;
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
        try {
            if (displayMessage != null)
                write(displayMessage);
            switch (saveVar.getClass().getName()) {
                case "java.lang.Integer": {
                    saveVar = Integer.parseInt(keyBufferedReader.readLine());
                    break;
                }
                case "java.lang.Character": {
                    saveVar = keyBufferedReader.readLine().charAt(0);
                    break;
                }
                case "java.lang.String": {
                    saveVar = keyBufferedReader.readLine();
                    break;
                }
                case "java.lang.Byte": {
                    saveVar = Byte.parseByte(keyBufferedReader.readLine());
                    break;
                }
                case "java.lang.Long": {
                    saveVar = Long.parseLong(keyBufferedReader.readLine());
                    break;
                }
                case "java.lang.Float": {
                    saveVar = Float.parseFloat(keyBufferedReader.readLine());
                    break;
                }
                case "java.lang.Double": {
                    saveVar = Double.parseDouble(keyBufferedReader.readLine());
                    break;
                }
                case "java.lang.Short": {
                    saveVar = Short.parseShort(keyBufferedReader.readLine());
                    break;
                }
                case "java.lang.Boolean": {
                    saveVar = Boolean.parseBoolean(keyBufferedReader.readLine());
                    break;
                }
                case "[C": {
                    saveVar = keyBufferedReader.readLine().toCharArray();
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
     * Method for reading files, by passing the filename as a String.
     * @param filename is a String which has the filename. For a correct usage, this must include all the path to the
     *                 file: String filename = "C:\\Users\\myFile.txt";
     * @param bytes is a boolean that says if the file is a byte's one
     */
    public void openFile(String filename, boolean bytes) {
        try {
            String encoding = new InputStreamReader(new FileInputStream(filename)).getEncoding();
            if (encoding == null || encoding.equals(""))
                encoding = "UTF-8";
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(filename), encoding));
            bufferedWriter = new BufferedWriter(new FileWriter(new File(filename), true));
            FILENAME = filename;
            ENCODING = encoding;
            BYTES = true;
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
     * Method for converting any type of object to byte array
     * @param obj value to be converted
     * @return byte[] array
     * @throws IOException when is not possible to convert
     */
    private static byte[] toByteArray(Object obj) throws IOException {
        byte[] bytes = null;
        ByteArrayOutputStream bos = null;
        ObjectOutputStream oos = null;
        try {
            bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            oos.flush();
            bytes = bos.toByteArray();
        } finally {
            if (oos != null) {
                oos.close();
            }
            if (bos != null) {
                bos.close();
            }
        }
        return bytes;
    }

    /**
     * Method for creating files
     * @param filename contains the name of the file
     * @param isBytes is true when a bytes object will be created. False, in other case
     */
    public static void createFile(String filename, boolean isBytes) {
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
     * Method for creating any necessary directories for a specific dir
     * @param dirname contains the directory (or directorys) that will be created
     */
    public static void createDir(String dirname) {
        if (!(new File(dirname).mkdirs()))
            throw new IOErrors.dirCreationError("There was an error while trying to create the directory. Please, check permissions");
    }

    /**
     * Delete specified dir and its content
     * @param dirname String that contains the directory to be deleted
     */
    public static void deleteDir(String dirname) {
        File[] contents = new File(dirname).listFiles();
        if (contents != null) {
            for (File file : contents)
                deleteDir(file.getPath());
        }
        if (!(new File(dirname).delete()))
            throw new IOErrors.dirEliminationError("There was an error while trying to remove the directory: " + dirname + ". Please, check permissions");
    }

    /**
     * Delete specified file
     * @param filename String that contains the file to be deleted
     */
    public static void deleteFile(String filename) {
        if (!(new File(filename).delete()))
            throw new IOErrors.fileEliminationError("There was an error while trying to remove file: "+filename+". Please, check permissions");
    }

    /**
     * Move files (or directories) to specified path
     * @param originPath String that contains the original file/folder to be moved
     * @param destinationPath String that contains the destination path to save the old file/directories
     * @param replaceExisting boolean that indicates if replace any existing file/directory if duplicated
     */
    public static void moveFilesDirs(String originPath, String destinationPath, boolean replaceExisting) {
        try {
            if (replaceExisting)
                Files.move(Paths.get(originPath), Paths.get(destinationPath), REPLACE_EXISTING);
            else
                Files.move(Paths.get(originPath), Paths.get(destinationPath));
        } catch (IOException e) {
            IO.write("There was an error while trying to move the file/dir specified. Full trace: \n");
            e.printStackTrace();
        }
    }

    /**
     * Copy files (or directories) to specified path
     * @param originPath String that contains the original file/folder to be copied
     * @param destinationPath String that contains the destination path to save the old file/directories
     * @param replaceExisting boolean that indicates if replace any existing file/directory if duplicated
     * By default, symlinks are copied as symlinks (not their content)
     */
    public static void copyFilesDirs(String originPath, String destinationPath, boolean replaceExisting) {
        try {
            if (replaceExisting)
                Files.copy(Paths.get(originPath), Paths.get(destinationPath), REPLACE_EXISTING);
            else
                Files.copy(Paths.get(originPath), Paths.get(destinationPath));
        } catch (IOException e) {
            IO.write("There was an error while trying to copy the file/dir specified. Full trace: \n");
            e.printStackTrace();
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
     * Returns a String that contains the specified line from file
     * @param line is a long that is the line number
     * @return a String::null if there was an error. The line, in other case
     */
    public String readLine(long line) {
        if (line > countLines()) {
            throw new IOErrors.LineOutOfRange("The line is out of range (number of lines: " + countLines() + ")");
        } else {
            try {
                Stream<String> getLine = Files.lines(Paths.get(FILENAME));
                return getLine.skip(line).findFirst().get();
            } catch (IOException e) {
                writeln("There was a problem while trying to get the specified line. Full trace:");
                e.printStackTrace();
            }
        }
        return null;
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
            if (BYTES) {
                fWriteb(message);
            } else {
                bufferedWriter.write(String.valueOf(message));
            }
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
    public void fWriteb(Object message) {
        try {
            fWriteb(toByteArray(message));
        } catch (IOException e) {
            writeln("There was a problem while trying to convert your data to byte. Full trace:");
            e.printStackTrace();
        }
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
            BYTES = false;
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

        public static class dirCreationError extends IOErrors {
            public dirCreationError(String cause) {
                super(cause);
            }
        }

        public static class dirEliminationError extends IOErrors {
            public dirEliminationError(String cause) {
                super(cause);
            }
        }

        public static class fileEliminationError extends IOErrors {
            public fileEliminationError(String cause) {
                super(cause);
            }
        }

        public static class LineOutOfRange extends IOErrors {
            public LineOutOfRange(String cause) {
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
            closeFile();
        } finally {
            super.finalize();
        }
    }
}
