package DaoUser;

import org.testcontainers.containers.PostgreSQLContainer;

public class TestContainer extends PostgreSQLContainer<TestContainer> {

    private static TestContainer container;
    private static final String INIT_POSTGRES = "init.sql";
    private static final String DOCKER_VERSION_POSTGRES = "postgres:latest";

    private TestContainer() {
        super(DOCKER_VERSION_POSTGRES);
    }

    @Override
    public void start() {
        super.start();
    }

    @Override
    public void stop() {
        super.stop();
    }

    public static TestContainer getContainer() {
        if (container == null) {
            container = new TestContainer().withInitScript(INIT_POSTGRES);
        }
        return container;
    }
}
