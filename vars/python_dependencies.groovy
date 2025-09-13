def call () {
  def file = new File("requirements.txt")
  if (file.exists()) {
    println "requirements.txt exists"
    sh "pip install -r requirements.txt"
 } else {
   error("requirements.txt does not exist")
  }
}

