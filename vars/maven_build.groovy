def call(String credentialsId) {
    withCredentials([file(credentialsId: credentialsId, variable: 'MAVEN_SETTINGS')]) {
        sh "mvn clean package -s $MAVEN_SETTINGS"
    }
}
