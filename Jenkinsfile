pipeline {
    stages {
        stage('build') {
            steps {
                sh 'mvn --version'
		sh 'mvn clean package -DskipTests'
            }
        }
    }
}
