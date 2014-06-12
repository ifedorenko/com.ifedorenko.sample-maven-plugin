package com.ifedorenko.maven.sample.test;

import static org.apache.maven.plugin.testing.resources.TestResources.assertDirectoryContents;

import java.io.File;

import org.apache.maven.plugin.testing.MojoRule;
import org.apache.maven.plugin.testing.resources.TestResources;
import org.junit.Rule;
import org.junit.Test;

public class SampleMojoTest {

  @Rule
  public TestResources resources = new TestResources();

  @Rule
  public MojoRule mojos = new MojoRule();

  @Test
  public void testBasic() throws Exception {
    File basedir = resources.getBasedir("basic");
    mojos.executeMojo(basedir, "sample");
    assertDirectoryContents(new File(basedir, "target/sample"), "sample.txt");
  }
}
