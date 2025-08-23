def call(Map config = [:]) {
    def imageName    = config.imageName ?: 'myorg/myapp'
    if(config.imageName) {
      def credentialsId = config.credentialsId ?: 'dockerhub-creds'
  
      if (!env.IMAGE_TAG) {
          error "IMAGE_TAG is not set. Please build the image first and set env.IMAGE_TAG."
      }
  
      withCredentials([usernamePassword(credentialsId: credentialsId, usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
          sh 'echo $DOCKER_PASS | docker login -u $DOCKER_USER --password-stdin'
          sh "docker push ${imageName}:${env.IMAGE_TAG}"
      }
    } else {
    throw new RuntimeException("Missing image name paramter..") 
  }
}
