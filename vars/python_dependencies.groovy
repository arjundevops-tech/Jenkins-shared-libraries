def call () {
  def file = new File("requirements.txt")
  if (file.exists()) {
    println "requirements.txt exists"
    sh "pip install requirements.txt"
 } else {
   error("requirements.txt does not exist")
  }
}
