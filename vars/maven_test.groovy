def call(String credentialsId) {
    withCredentials([file(credentialsId: credentialsId, variable: 'MAVEN_SETTINGS')]) {
        sh "mvn test -s $MAVEN_SETTINGS"
    }
}
