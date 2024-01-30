package org.dows.ops;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class TriggerRest {

    /**
     * {
     *     "push_data": {
     *         "digest": "sha256:457f4aa83fc9a6663ab9d1b0a6e2dce25a12a943ed5bf2c1747c58d48bbb4917",
     *         "pushed_at": "2016-11-29 12:25:46",
     *         "tag": "latest"
     *     },
     *     "repository": {
     *         "date_created": "2016-10-28 21:31:42",
     *         "name": "repoTest",
     *         "namespace": "namespace",
     *         "region": "cn-hangzhou",
     *         "repo_authentication_type": "NO_CERTIFIED",
     *         "repo_full_name": "namespace/repoTest",
     *         "repo_origin_type": "NO_CERTIFIED",
     *         "repo_type": "PUBLIC"
     *     }
     * }
     * curl -s https://files-cdn.cnblogs.com/files/nihaorz/install_docker_and_compose.sh | bash
     */
    @PostMapping("trigger")
    public void trigger(@RequestBody TriggerRequest triggerRequest) {

        String tag = triggerRequest.getPushData().getTag();
        String name = triggerRequest.getRepository().getName();





    }


}
