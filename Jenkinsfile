pipeline {
    agent any

    triggers {
        cron('H 8 * * *')
    }
    stages {
        stage('Checkout') {
            steps {
                git branch: "${params.BRANCH}", url: 'https://github.com/KazadaevIvan/Calculator-UI-Automation.git'
            }
        }
        stage('Running tests') {
            steps {
                bat "mvn clean test -Dmaven.test.failure.ignore=true"
            }
            post {
                success {
                    junit '**/target/surefire-reports/TEST-*.xml'
                }
            }
        }
        stage('Generating Allure Report') {
            steps {
                script {
                    allure([
                        includeProperties: false,
                        jdk: '',
                        properties: [],
                        reportBuildPolicy: 'ALWAYS',
                        results: [[path: 'target/allure-results']]
                    ])
                }
            }
        }
    }
}
