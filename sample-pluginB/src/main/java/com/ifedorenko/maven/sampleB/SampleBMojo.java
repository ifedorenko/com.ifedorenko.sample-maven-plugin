package com.ifedorenko.maven.sampleB;

import java.io.File;
import java.io.IOException;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

@Mojo(name = "sample-b")
public class SampleBMojo extends AbstractMojo {

  @Parameter(defaultValue = "${project.build.directory}/sample")
  private File outputDirectory;

  @Override
  public void execute() throws MojoExecutionException, MojoFailureException {
    if (!outputDirectory.isDirectory() && !outputDirectory.mkdirs()) {
      throw new MojoExecutionException(String.format("Could not create output directory %s",
          outputDirectory));
    }
    try {
      new File(outputDirectory, "sample-b.txt").createNewFile();
    } catch (IOException e) {
      throw new MojoExecutionException("Could not create sample output", e);
    }
  }

}
