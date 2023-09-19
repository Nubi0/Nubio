pipeline {
    agent any

    stages {
        stage('Cleanup Workspace') {
            steps {
                sh 'rm -rf *'
            }
        }

        stage('Clone Repository') {
            steps {
                checkout scm
            }
        }

        stage('Prepare, Build, and Test') {
            steps {
                withCredentials([
                    file(credentialsId: 'authentication-service-application-auth.yml', variable: 'AUTHFILE'),
                    file(credentialsId: 'authentication-service-application-oauth.yml', variable: 'OAUTHFILE')
                ]) {
                    script {
                        sh "cp \$AUTHFILE backend/authentication-service/src/main/resources/application-auth.yml"
                        sh "cp \$OAUTHFILE backend/authentication-service/src/main/resources/application-oauth.yml"
                    }
                }
                // sh 'chmod +x backend/authentication-service/gradlew'

                // script {
                //     dir('backend/authentication-service') {
                //         docker.build('authentication-service')
                //         sh './gradlew clean build -x test'
                //     }
                // }
            }
        }

        stage('Docker build and push authentication') {
            steps {
                withCredentials([
                    usernamePassword(credentialsId: 'docker-hub', usernameVariable: 'DOCKER_HUB_USER', passwordVariable: 'DOCKER_HUB_PASS')
                ]) {
                    dir('backend/authentication-service') {
                        sh 'docker login -u $DOCKER_HUB_USER -p $DOCKER_HUB_PASS'
                        sh 'docker build -t authentication-service:latest .'
                        sh 'docker tag authentication-service:latest kathyleesh/authentication-service:latest'
                        sh 'docker push kathyleesh/authentication-service:latest'
                    }
                }
            }
        }

        stage('Install Docker Compose') {
            steps {
                sh '''#!/bin/bash
                    curl -L "https://github.com/docker/compose/releases/download/1.29.2/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
                    chmod +x /usr/local/bin/docker-compose
                '''
            }
        }

        stage('Docker Compose Up') {
            steps {
                withCredentials([
                    usernamePassword(credentialsId: 'docker-hub', usernameVariable: 'DOCKER_HUB_USER', passwordVariable: 'DOCKER_HUB_PASS'),
                    string(credentialsId: 'nubio', variable: 'AWS_ACCESS_KEY_ID'),
                    string(credentialsId: 'nubio', variable: 'AWS_SECRET_ACCESS_KEY')
                ]) {
                    sh """
                        export AWS_ACCESS_KEY_ID=\$AWS_ACCESS_KEY_ID
                        export AWS_SECRET_ACCESS_KEY=\$AWS_SECRET_ACCESS_KEY
                        docker-compose -f docker-compose.yml up -d
                    """
                }
            }
        }

    }
}
