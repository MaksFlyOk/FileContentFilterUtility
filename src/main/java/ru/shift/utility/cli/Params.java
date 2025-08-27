package ru.shift.utility.cli;

import java.nio.file.Path;
import java.util.List;

/**
 * Record Params is a data model of arguments that can be passed when calling the utility on the command line
 * @param oEnabled Activation of the option to set the path for saving the results
 * @param writeFilesPath Path to results
 * @param pEnabled Enable option to set output file name prefix
 * @param filePrefix Output file name prefix
 * @param aEnabled Activation of the option to add the result to existing files
 * @param sEnabled Activation of the short statistics option
 * @param fEnabled Activation of the full statistics option
 * @param files Paths to files to be processed
 */
public record Params (boolean oEnabled, Path writeFilesPath, boolean pEnabled, String filePrefix,
                      boolean aEnabled, boolean sEnabled, boolean fEnabled, List<Path> files){ }
