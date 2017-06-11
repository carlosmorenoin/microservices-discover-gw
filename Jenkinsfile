pipeline {
    agent any
    stages {
        stage('build') {
            steps {
                sh 'mvn clean install'
            }
        }

        stage('deploy') {
            steps {
                sh 'docker-compose stop && docker-compose rm -f'
                sh 'docker-compose up -d'
            }
        }
    }
}