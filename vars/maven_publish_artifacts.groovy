def call(String credentialsId) {
    withCredentials([file(credentialsId: credentialsId, variable: 'MAVEN_SETTINGS')]) {
        sh "mvn deploy -s $MAVEN_SETTINGS"
    }
}
