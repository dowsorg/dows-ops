package org.dows.ops;

import lombok.Data;

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
 */
@Data
public class TriggerRequest {

    private PushData pushData;
    private Repository repository;

    @Data
    public static class PushData {
        private String digest;
        private String pushed_at;
        private String tag;
    }

    @Data
    public static class Repository {
        private String date_created;
        private String name;
        private String namespace;
        private String region;
        private String repo_authentication_type;
        private String repo_full_name;
        private String repo_origin_type;
        private String repo_type;
    }
}
