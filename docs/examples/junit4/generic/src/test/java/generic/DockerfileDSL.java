package generic;

import org.junit.Rule;
import org.junit.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.images.builder.ImageFromDockerfile;

public class DockerfileDSL {

    // dockerfileWithFiles {
    public GenericContainer dslContainer = new GenericContainer(
        new ImageFromDockerfile()
            .withFileFromString("folder/someFile.txt", "hello")
            .withFileFromClasspath("test.txt", "mappable-resource/test-resource.txt")
            .withFileFromClasspath("Dockerfile", "mappable-dockerfile/Dockerfile"));
    // }


    @Test
    public void createDockerImageFromBuilder() {
        // dockerfileDSL {
        new GenericContainer(
            new ImageFromDockerfile()
                .withDockerfileFromBuilder(builder ->
                    builder
                        .from("alpine:3.2")
                        .run("apk add --update nginx")
                        .cmd("nginx", "-g", "daemon off;")
                        .build()))
            .withExposedPorts(80);
        // }
    }


}
