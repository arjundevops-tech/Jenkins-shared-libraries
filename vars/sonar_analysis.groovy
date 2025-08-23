def call(Map config = [:]) {
    // default values if not passed
    def toolName = config.toolName ?: 'sonar-scanner'
    def sonarEnv = config.sonarEnv ?: 'sonar'
    def projectKey = config.projectKey ?: 'default-project'

    def SONAR_SCANNER_HOME = tool(toolName)

    sh "${SONAR_SCANNER_HOME}/bin/sonar-scanner --version"

    withSonarQubeEnv(sonarEnv) {
        sh """
            ${SONAR_SCANNER_HOME}/bin/sonar-scanner \
            -Dsonar.projectKey=${projectKey}
        """
    }
}
