def call(Map config = [:]) {
    def imageName   = config.imageName
    def format     = config.format ?: 'json'
    def outputFile = config.outputFile ?: 'trivy-image-scan-report.json'

    sh """
        trivy image -f ${format} -o ${outputFile} ${imageName}:${env.IMAGE_TAG}
    """

    archiveArtifacts artifacts: outputFile, fingerprint: true
}
