def call (Map config = [:]){
  def inboudPort_number = config.inboudPort_number
  def outboundPort_number = config.outboundPort_number
  def container_name = config.container_name
  def image_name = config.image_name
  if(inboudPort_number != null &&  outboundPort_number != null && container_name != null &&  image_name != null) {
      timeout(time: 5, unit: 'MINUTES') {
        input message: 'Do you approve this deployment?', ok: 'Yes, proceed'
      }
      sh "docker stop ${container_name} || true"
      sh "docker rm ${container_name} || true"
      println("############## STARTED APPLICATION DEPLOYMENT #################")
      sh "docker run -itd --name ${container_name} -p ${inboudPort_number}:${outboundPort_number} ${image_name}"
      println("############## COMPLETED DEPLOYMENT SUCCESSFULLY #################")
  } else {
    error("Missing one of the parameters of inboudPort_number, outboundPort_number, container_name, image_name")
  }
}

  
  
