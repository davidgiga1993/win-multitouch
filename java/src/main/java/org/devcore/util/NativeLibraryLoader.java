package org.devcore.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Loads shared libraries from a natives jar file
 */
public class NativeLibraryLoader
{
	private static boolean is64Bit = System.getProperty("os.arch").equals("amd64")
			|| System.getProperty("os.arch").equals("x86_64");

	/**
	 * Maps a platform independent library name to a platform dependent name.
	 */
	private static String getLibraryName(String libraryName)
	{
		return libraryName + (is64Bit ? "64.dll" : ".dll");
	}

	/**
	 * Loads a native library with the given name from the current jar file
	 *
	 * @param libraryName Name of the library
	 */
	public static void load(String libraryName)
	{
		String libFileName = getLibraryName(libraryName);
		try
		{
			File file = new File(System.getProperty("java.io.tmpdir"), libFileName);
			System.load(extractFile(libFileName, file).getAbsolutePath());
		}
		catch (Throwable ex)
		{
			throw new RuntimeException("Couldn't load shared library " + libFileName, ex);
		}
	}

	/**
	 * Tries to read a file with the given path from the current jar
	 *
	 * @param path Path/name of the file
	 * @return Input stream
	 */
	private static InputStream readFile(String path)
	{
		InputStream input = NativeLibraryLoader.class.getResourceAsStream("/" + path);
		if (input == null)
		{
			throw new RuntimeException("Unable to find file: " + path);
		}
		return input;
	}

	/**
	 * Extracts the given source path from the jar to the destination.
	 * If the destination does already exist it will be directly returned
	 *
	 * @param sourcePath    Source
	 * @param extractedFile Destination
	 * @return Extracted file
	 * @throws IOException General IO error
	 */
	private static File extractFile(String sourcePath, File extractedFile) throws IOException
	{
		if (extractedFile.exists() && !extractedFile.delete())
		{
			throw new RuntimeException("Could not extract file");
		}

		try (InputStream input = readFile(sourcePath))
		{
			try (FileOutputStream output = new FileOutputStream(extractedFile))
			{
				byte[] buffer = new byte[4096];
				while (true)
				{
					int length = input.read(buffer);
					if (length == -1)
					{
						break;
					}
					output.write(buffer, 0, length);
				}

			}
			catch (IOException ex)
			{
				throw new RuntimeException("Error extracting file: " + sourcePath + " to " + extractedFile.getAbsolutePath(), ex);
			}
		}

		return extractedFile;
	}
}