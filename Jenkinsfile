pipeline {
    agent any
    
    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/Hemanth-bs/MyMavenFireFox.git'
            }
        }
        
        stage('Build') {
            steps {
                script {
                    sh 'mvn clean package'
                }
            }
        }

        stage('Run Tests') {
            steps {
                script {
                    // Ensure FirefoxDriver works
                    sh 'java -cp target/your-artifact.jar com.example.App'
                }
            }
        }
    }
    
    post {
        always {
            cleanWs()
        }
    }
}
