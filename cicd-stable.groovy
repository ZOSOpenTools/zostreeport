node('linux')
{
  stage ('Poll') {
   // Poll from upstream:
    checkout([
                        $class: 'GitSCM',
                        branches: [[name: '*/main']],
                        doGenerateSubmoduleConfigurations: false,
                        extensions: [],
                        userRemoteConfigs: [[url: "https://github.com/IBM/zos-pstree.git"]]])
    checkout([
      $class: 'GitSCM',
      branches: [[name: '*/main']],
      doGenerateSubmoduleConfigurations: false,
      extensions: [],
      userRemoteConfigs: [[url: 'https://github.com/ZOSOpenTools/zospstreeport.git']]])
  }
  stage('Build') {
    build job: 'Port-Pipeline', parameters: [string(name: 'PORT_GITHUB_REPO', value: 'https://github.com/ZOSOpenTools/zospstreeport.git'), string(name: 'PORT_DESCRIPTION', value: 'List visible processes in tree form' ), string(name: 'BUILD_LINE', value: 'STABLE') ]
  }
}
