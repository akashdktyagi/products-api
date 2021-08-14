pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
              sh "mvn clean install"
            }
        }
        stage('sonar') {
            steps {
               sh "mvn -B verify org.sonarsource.scanner.maven:sonar-maven-plugin:sonar -Dsonar.projectKey=yantraCloudApp_products-api"
            }
        }
        stage('Deploy') {
            steps {
                echo "yaku"
            }
        }
    }
}