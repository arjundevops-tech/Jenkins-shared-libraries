def call(Map config = [:]) {
    def scanPath   = config.scanPath ?: '.'
    def format     = config.format ?: 'json'
    def outputFile = config.outputFile ?: 'trivy-fs-report.json'

    sh """
        trivy fs --format ${format} --output ${outputFile} ${scanPath}
    """

    archiveArtifacts artifacts: outputFile, fingerprint: true
}
