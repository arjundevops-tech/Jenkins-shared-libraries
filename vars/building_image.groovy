def call(Map config = [:]) {
    if(config.imageName) {
        def imageName = config.imageName 
        def dockerfile  = config.dockerfile ?: 'Dockerfile'
        def buildContext = config.buildContext ?: '.'
    
        // generate timestamp tag
        def TAG = sh(script: "date +%Y%m%d-%H%M%S", returnStdout: true).trim()
        env.IMAGE_TAG = TAG
    
        sh """
            docker build -t ${imageName}:${env.IMAGE_TAG} -f ${dockerfile} ${buildContext}
        """
    } else {
        throw new RuntimeException("Missing image name paramter..")
    }
}
