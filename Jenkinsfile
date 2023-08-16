pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                script {
                    // 获取分支名称
                    def branchName = env.BRANCH_NAME
                    echo '=============build ${branchName}=============='
                    // 根据分支名称的前缀判断不同的环境
                    if (branchName.startsWith('dev-')) {
                        // 开发环境
                        // 执行开发环境的构建步骤
                        echo 'Building for development environment'
                        // 执行 Maven 打包等操作
                    } else if (branchName.startsWith('test-')) {
                        // 测试环境
                        // 执行测试环境的构建步骤
                        echo 'Building for test environment'
                        // 执行 Maven 打包等操作
                    } else if (branchName.startsWith('prod-')) {
                        // 生产环境
                        // 执行生产环境的构建步骤
                        echo 'Building for production environment'
                        // 执行 Maven 打包等操作
                    } else {
                        // 其他环境
                        // 执行其他环境的构建步骤
                        echo 'Building for other environment'
                        // 执行 Maven 打包等操作
                    }
                }
            }
        }
    }
}