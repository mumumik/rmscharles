node{
	stage('SCM checkout'){
		git 'https://https://github.com/mumumik/rmscharles'
	}
	stage('Compile-Package'){
		sh 'mvn clean package'
	}
}