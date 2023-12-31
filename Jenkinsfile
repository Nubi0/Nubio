pipeline {
    agent any

    environment { 
            SLACK_CHANNEL = '#자동배포' 
            TEAM_DOMAIN = 'nubiohq' 
            TOKEN_CREDENTIAL_ID = 'slack-access-token'

        }

    stages {

        stage('Start') {
                    steps {
                        slackSend (channel: SLACK_CHANNEL, color: '#FFFF00', message: "STARTED: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]' (${env.BUILD_URL})", teamDomain: TEAM_DOMAIN, tokenCredentialId: TOKEN_CREDENTIAL_ID )
                    }
                }


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
                    file(credentialsId: 'authentication-service-application-oauth.yml', variable: 'OAUTHFILE'),
                    file(credentialsId: 'enjoy-service-application-db.yml', variable: 'ENJOYDBFILE'),
                    file(credentialsId: 'enjoy-service-application-s3.yml', variable: 'ENJOYS3FILE'),
                    file(credentialsId: 'enjoy-service-application-map.yml', variable: 'ENJOYMAPFILE'),
                    file(credentialsId: 'enjoy-service-application-prod.yml', variable: 'ENJOYFILE'),
                    file(credentialsId: 'safe-service-application-db.yml', variable: 'SAFEDBFILE'),
                    file(credentialsId: 'safe-service-application-s3.yml', variable: 'SAFES3FILE'),
                    file(credentialsId: 'safe-service-application-map.yml', variable: 'SAFEMAPFILE'),
                    file(credentialsId: 'safe-service-application-prod.yml', variable: 'SAFEFILE'),
                    file(credentialsId: 'chatting-service-application-db.yml', variable: 'CHATDBFILE'),
                    file(credentialsId: 'chatting-service-application-s3.yml', variable: 'CHATS3FILE'),
                    file(credentialsId: 'chatting-service-application-map.yml', variable: 'CHATMAPFILE'),
                    file(credentialsId: 'chatting-service-application-prod.yml', variable: 'CHATFILE'),
                ]) {
                    script {
                        sh "cp \$AUTHFILE backend/authentication-service/src/main/resources/application-auth.yml"
                        sh "cp \$OAUTHFILE backend/authentication-service/src/main/resources/application-oauth.yml"
                        sh "cp \$ENJOYDBFILE backend/enjoy-service/src/main/resources/application-db.yml"
                        sh "cp \$ENJOYS3FILE backend/enjoy-service/src/main/resources/application-s3.yml"
                        sh "cp \$ENJOYMAPFILE backend/enjoy-service/src/main/resources/application-map.yml"
                        sh "cp \$ENJOYFILE backend/enjoy-service/src/main/resources/application-prod.yml"
                        sh "cp \$SAFEDBFILE backend/safe-service/src/main/resources/application-db.yml"
                        sh "cp \$SAFES3FILE backend/safe-service/src/main/resources/application-s3.yml"
                        sh "cp \$SAFEMAPFILE backend/safe-service/src/main/resources/application-map.yml"
                        sh "cp \$SAFEFILE backend/safe-service/src/main/resources/application-prod.yml"
                        sh "cp \$CHATDBFILE backend/chatting-service/src/main/resources/application-db.yml"
                        sh "cp \$CHATS3FILE backend/chatting-service/src/main/resources/application-s3.yml"
                        sh "cp \$CHATMAPFILE backend/chatting-service/src/main/resources/application-map.yml"
                        sh "cp \$CHATFILE backend/chatting-service/src/main/resources/application-prod.yml"
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


        stage('Docker build and push enjoy') {
            steps {
                withCredentials([
                    usernamePassword(credentialsId: 'docker-hub', usernameVariable: 'DOCKER_HUB_USER', passwordVariable: 'DOCKER_HUB_PASS')
                ]) {
                    dir('backend/enjoy-service') {
                        sh 'docker login -u $DOCKER_HUB_USER -p $DOCKER_HUB_PASS'
                        sh 'docker build -t enjoy-service:latest .'
                        sh 'docker tag enjoy-service:latest kathyleesh/enjoy-service:latest'
                        sh 'docker push kathyleesh/enjoy-service:latest'
                    }
                }
            }
        }

        stage('Docker build and push safe') {
            steps {
                withCredentials([
                    usernamePassword(credentialsId: 'docker-hub', usernameVariable: 'DOCKER_HUB_USER', passwordVariable: 'DOCKER_HUB_PASS')
                ]) {
                    dir('backend/safe-service') {
                        sh 'docker login -u $DOCKER_HUB_USER -p $DOCKER_HUB_PASS'
                        sh 'docker build -t safe-service:latest .'
                        sh 'docker tag safe-service:latest kathyleesh/safe-service:latest'
                        sh 'docker push kathyleesh/safe-service:latest'
                    }
                }
            }
        }

        stage('Docker build and push chatting') {
            steps {
                withCredentials([
                    usernamePassword(credentialsId: 'docker-hub', usernameVariable: 'DOCKER_HUB_USER', passwordVariable: 'DOCKER_HUB_PASS')
                ]) {
                    dir('backend/chatting-service') {
                        sh 'docker login -u $DOCKER_HUB_USER -p $DOCKER_HUB_PASS'
                        sh 'docker build -t chatting-service:latest .'
                        sh 'docker tag chatting-service:latest kathyleesh/chatting-service:latest'
                        sh 'docker push kathyleesh/chatting-service:latest'
                    }
                }
            }
        }

        stage('Docker build and push recommendation') {
            steps {
                withCredentials([
                    usernamePassword(credentialsId: 'docker-hub', usernameVariable: 'DOCKER_HUB_USER', passwordVariable: 'DOCKER_HUB_PASS')
                ]) {
                    dir('backend/recommendation-service') {
                        sh 'docker login -u $DOCKER_HUB_USER -p $DOCKER_HUB_PASS'
                        sh 'docker build -t recommendation-service:latest .'
                        sh 'docker tag recommendation-service:latest kathyleesh/recommendation-service:latest'
                        sh 'docker push kathyleesh/recommendation-service:latest'
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
                    [$class: 'AmazonWebServicesCredentialsBinding', credentialsId: 'nubio'],
                    string(credentialsId: 'MONGO_URL', variable: 'MONGO_URL')
                ]) {
                    sh """
                        export AWS_ACCESS_KEY_ID=${AWS_ACCESS_KEY_ID}
                        export AWS_SECRET_ACCESS_KEY=${AWS_SECRET_ACCESS_KEY}
                        export MONGO_URL=${MONGO_URL}
                        docker-compose -f docker-compose.yml up -d
                    """
                }
            }
        }

        stage('Force deletion of all unused images of stopped containers') {
            steps {
                sh '''
                    docker system prune -a -f
                '''
            }
        }
    }

    post {
    success {
         slackSend (channel: SLACK_CHANNEL, color: '#00FF00', message: "SUCCESSFUL: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]' (${env.BUILD_URL})", teamDomain: TEAM_DOMAIN, tokenCredentialId: TOKEN_CREDENTIAL_ID )
         }

    failure {
         slackSend (channel: SLACK_CHANNEL, color: '#F01717', message: "FAILURE: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]' (${env.BUILD_URL})", teamDomain: TEAM_DOMAIN, tokenCredentialId: TOKEN_CREDENTIAL_ID )
         }
    }
}
