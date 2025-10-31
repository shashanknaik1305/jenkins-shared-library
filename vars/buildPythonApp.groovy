def call(String imageName) {
    stage('Build Docker Image') {
        echo "Building Docker image: ${imageName}"
        sh "docker build -t ${imageName}:${env.BUILD_NUMBER} ."
    }
}
