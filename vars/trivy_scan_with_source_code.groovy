def call(Map config = [:]) {
    def outputFile = config.outputFile ?: 'trivy-fs-report.json'
    def format     = config.format ?: 'json'
    def scanPath   = config.scanPath ?: '.'

    sh """
        trivy fs --format ${format} --output ${outputFile} ${scanPath}
    """

    archiveArtifacts artifacts: outputFile, fingerprint: true
}
