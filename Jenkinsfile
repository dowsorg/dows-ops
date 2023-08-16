pipeline {
    agent any

    environment {
        JAVA_HOME = '/usr/local/jdk17'  // 指定 JDK 17 的路径
        MAVEN_HOME = '/usr/local/mvn/bin/mvn'  // 指定 Maven 的路径
        PATH = "${env.JAVA_HOME}/bin:${env.MAVEN_HOME}/bin:${env.PATH}"
    }

    stages {
        stage('build jar') {
            steps {
                script {
                    // 获取分支名称 并用分割出版本号和名称
                    def branch = env.BRANCH_NAME.split('/')[1]
                    def rte = branch.split('-')[0]
                    def tag = branch.split('-')[1]
                    echo "=============build $rte-$tag=============="
                    // 根据分支名称的前缀判断不同的环境
                    if (branch.startsWith('dev-')) {
                        echo 'Building for development environment'
                        sh '''
                            /usr/local/mvn/bin/mvn -v
                            /usr/local/mvn/bin/mvn -Dmaven.test.skip=true clean package -U
                            docker login --username=dxz@dows --password=dowsdxz123456 registry.cn-hangzhou.aliyuncs.com
                            docker build . --file Dockerfile --tag registry.cn-hangzhou.aliyuncs.com/dows/dows-ops-dev:${tag}
                            docker push registry.cn-hangzhou.aliyuncs.com/dows/dows-ops-dev:${tag}
                        '''
                    } else if (branch.startsWith('sit-')) {
                        // 测试环境
                        // 执行测试环境的构建步骤
                        echo 'Building for sit environment'
                        // 执行 Maven 打包等操作
                    } else if (branch.startsWith('uat-')) {
                        // 生产环境
                        // 执行生产环境的构建步骤
                        echo 'Building for uat environment'
                        // 执行 Maven 打包等操作
                    } else if (branch.startsWith('prd-')){
                        // 其他环境
                        // 执行其他环境的构建步骤
                        echo 'Building for production environment'
                        // 执行 Maven 打包等操作
                    }
                }
            }
        }
    }
}