package com.ifedorenko.maven.sample.test;

import io.takari.maven.testing.TestMavenRuntime;
import io.takari.maven.testing.TestResources;

import java.io.File;

import org.junit.Rule;
import org.junit.Test;

public class SampleMojoTest {

  @Rule
  public TestResources resources = new TestResources();

  @Rule
  public TestMavenRuntime mojos = new TestMavenRuntime();

  @Test
  public void testBasic() throws Exception {
    File basedir = resources.getBasedir("basic");
    mojos.executeMojo(basedir, "sample");
    TestResources.assertDirectoryContents(new File(basedir, "target/sample"), "sample.txt");
  }
}
