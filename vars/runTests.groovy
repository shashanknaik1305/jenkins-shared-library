def call() {
    stage('Run Tests') {
        docker.image('python:3.9').inside {
            sh '''
                pip install -r requirements.txt
                mkdir -p reports
                pytest --junitxml=reports/results.xml --html=reports/report.html --self-contained-html
            '''
        }
        junit 'reports/results.xml'
        publishHTML(target: [
            reportDir: 'reports',
            reportFiles: 'report.html',
            reportName: 'Pytest Report'
        ])
    }
}
