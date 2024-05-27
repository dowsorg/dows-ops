docker login -u admin -p shdy123! 192.168.111.103:88
docker build -f Dockerfile_3 -t gitlab-runner:j17m3s5 .
docker tag gitlab-runner:j17m3s5 192.168.111.103:88/cicd/gitlab-runner:j17m3s5
docker push 192.168.111.103:88/cicd/gitlab-runner:j17m3s5

# 执行docker-compose [docker-compose-3.yml](docker-compose-3.yml)

# 注册runner
docker exec gitlab-runner-jms gitlab-runner register \
--non-interactive \
--url "http://192.168.23.19/" \
--token "glrt-frvQrPaeHz8mzgz9DHie" \
--executor "docker" \
--docker-image docker:latest \
--description "docker cicd executor" \
--tag-list "cicd-shdy" \
--run-untagged="false" \
--locked="false" \
--access-level="not_protected"



gitlab-runner register  --url http://192.168.23.19  --token glrt-ysxvvz7Ch8xsjUGMCNT_ --executor "docker" --docker-image docker:latest