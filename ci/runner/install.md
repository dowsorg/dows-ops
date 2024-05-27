docker login -u admin -p shdy123! 192.168.111.103:88



sudo gitlab-runner uninstall

sudo gitlab-runner install --working-directory /home/gitlab-runner --user root

systemctl daemon-reload
systemctl start gitlab-runner
systemctl enable gitlab-runner

gitlab-runner register  --url http://192.168.23.19  --token glrt-n6MqTshDH_AxaQ2DohqT

gitlab-runner unregister  --url http://192.168.23.19  --token glrt-n6MqTshDH_AxaQ2DohqT