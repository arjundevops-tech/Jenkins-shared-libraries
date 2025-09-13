def call (Map config = [:]) {
   def nexus_credentials_id = config.nexus_credentials_id
   def application_name = config.application_name
   def nexus_url = config.nexus_url
   def nexus_repo_name = config.nexus_repo_name 
   if(nexus_credentials_id != null && application_name != null && nexus_url != null && nexus_repo_name != null) {
       withCredentials([usernamePassword(credentialsId: "${nexus_credentials_id}", passwordVariable: 'NEXUS_PASSWORD', usernameVariable: 'NEXUS_USER_NAME')]) {
           def DATE_TIME = sh(script: "date +%Y%m%d-%H%M%S", returnStdout: true).trim()
           def arifactName = "${application_name}_${DATE_TIME}"
           sh "zip -r ${arifactName}.zip ."
           sh """
           curl -u ${NEXUS_USER_NAME}:'${NEXUS_PASSWORD}' --upload-file ${arifactName}.zip  '${nexus_url}/repository/${nexus_repo_name}/${arifactName}.zip'
           """
       }
   } else {    
      error("Missing required parameters like nexus_credentials_id or application_name or nexus_url or nexus_repo_name")
   }
         
}
