pipeline {
  agent any
  stages {
    stage('Build') { steps { dir('backend') { sh './mvnw -B verify' } } }
    stage('Frontend') { steps { dir('frontend') { sh 'npm ci && npm run build' } } }
  }
}
