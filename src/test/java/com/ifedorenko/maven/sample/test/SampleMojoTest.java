package com.ifedorenko.maven.sample.test;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.apache.maven.execution.MavenSession;
import org.apache.maven.project.MavenProject;
import org.junit.Rule;
import org.junit.Test;

import io.takari.maven.testing.TestMavenRuntime;
import io.takari.maven.testing.TestResources;

public class SampleMojoTest {

  @Rule
  public TestResources resources = new TestResources();

  @Rule
  public TestMavenRuntime maven = new TestMavenRuntime();

  @Test
  public void testBasic() throws Exception {
    File basedir = resources.getBasedir("basic");
    maven.executeMojo(basedir, "sample");
    TestResources.assertDirectoryContents(new File(basedir, "target/sample"), "sample.txt");
  }

  @Test
  public void testMultimodule() throws Exception {
    File basedir = resources.getBasedir("multimodule");

    // read all projects, needed to populate session
    MavenProject parent = maven.readMavenProject(basedir);
    MavenProject moduleA = maven.readMavenProject(new File(basedir, "module-a"));
    MavenProject moduleB = maven.readMavenProject(new File(basedir, "module-b"));
    List<MavenProject> projects = Arrays.asList(moduleA, moduleB, parent);

    // establish inter-project dependencies
    maven.newDependency(new File(moduleB.getBuild().getOutputDirectory())) //
        .setGroupId(moduleB.getGroupId()) //
        .setArtifactId(moduleB.getArtifactId()) //
        .setVersion(moduleB.getVersion()) //
        .addTo(moduleA);

    // create and populate session
    MavenSession session = maven.newMavenSession(moduleA);
    session.setProjects(projects);

    // execute the mojo using specific session and project
    maven.executeMojo(session, moduleA, "sample");
  }
}
