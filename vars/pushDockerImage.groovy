def call(String imageName, String dockerCreds) {
    stage('Push to Docker Hub') {
        withCredentials([usernamePassword(credentialsId: dockerCreds, usernameVariable: 'USER', passwordVariable: 'PASS')]) {
            sh '''
                echo "$PASS" | docker login -u "$USER" --password-stdin
                docker push '${imageName}:${BUILD_NUMBER}'
                docker tag '${imageName}:${BUILD_NUMBER}' '${imageName}:latest'
                docker push '${imageName}:latest'
                docker logout
            '''
        }
    }
}
