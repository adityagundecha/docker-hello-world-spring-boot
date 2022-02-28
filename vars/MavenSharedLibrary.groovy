def call(String repoUrl) {
  pipeline {
       agent any
       tools {
           maven 'maven-3'
       }
       stages {
           stage("Tools initialization") {
               steps {
                   sh "mvn --version"
               }
           }
           stage("Checkout Code") {
               steps {
                   git branch: 'master',
                       url: "${repoUrl}"
               }
           }
           stage("Cleaning workspace") {
               steps {
                   sh "mvn clean"
               }
           }
           stage("Running Testcase") {
              steps {
                   sh "mvn test"
               }
           }
           stage("Packing Application") {
               steps {
                   sh "mvn package -DskipTests"
               }
           }
           stage("Docker Build"){
               steps {
                   sh 'docker build -t="hello-world-java" .'
               }
           }
       }
   }
}
