pipeline {
    agent any

    stages {
        stage('Checkout SCM') {
            steps {
                // Update to the correct branch name (main instead of master)
                git branch: 'main', url: 'https://github.com/Hemanth-bs/MyMavenFireFox.git'
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
                    sh 'java -cp target/MyMavenFireFox-1.0-SNAPSHOT.jar com.example.App'              
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
