pipeline {
    agent any
    stages {
        stage('Update Jenkins Server') {
            steps {
                sh '''
                sudo apt update
                '''
            }
        }
        stage('Unit and Integration Testing') {
            steps {
                    script{
                                sh '''
                                ssh ubuntu@10.0.3.69  <<EOF
                                rm -rf Final_Project
                                git clone -b development https://github.com/jake4327/Final_Project.git
                                cd Final_Project
                                mvn test >> test.txt
EOF
                                '''
                            }
                        }                     
        }

        stage('SSH into NEXUS and Build Backend image') {
            steps {
                sh '''
                ssh ubuntu@10.0.3.249 <<EOF
                rm -rf Final_Project
                git clone -b build https://github.com/jake4327/Final_Project.git
                cd Final_Project
                docker build -t jstoneqa/sfia-3-backend:nginx .
EOF
                '''
            }
        }
        
        stage('SSH into NEXUS and Build Frontend image') {
            steps {
                sh '''
                ssh ubuntu@10.0.3.249 <<EOF
                rm -rf Final_Project
                git clone -b react https://github.com/jake4327/Final_Project.git
                cd Final_Project
                docker build -t jstoneqa/sfia-3-frontend:nginx .
EOF
                '''
            }
        }

        stage('SSH into NEXUS and push backend to private repo'){
            steps{
                sh '''
                ssh ubuntu@10.0.3.249 <<EOF
                docker push jstoneqa/sfia-3-backend:nginx
EOF
                '''
            }
        }

        stage('SSH into NEXUS and push frontend to private repo'){
            steps{
                sh '''
                ssh ubuntu@10.0.3.249 <<EOF
                docker push jstoneqa/sfia-3-frontend:nginx
EOF
                '''
            }
        }

        stage ('Kubectl'){
          steps {
              sh '''
              ssh ubuntu@10.0.3.249 <<EOF
              rm -rf Final_Project
              git clone -b kubernetes https://github.com/jake4327/Final_Project.git
              kubectl delete -f Final_Project/K8S
              kubectl apply -f Final_Project/K8S
              kubectl get svc
EOF
              '''
          }
        }
    }
}
