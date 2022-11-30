package org.egov.codegen;

public class Config {

    private String url;
    private String groupId;
    private String artifactId;
    private String basePackage;
    private boolean useLombok;
    private boolean useTracer;
    private boolean useRedis;
    private boolean enableFlyway;
    private boolean serverLibrary;

    public Config(String url, String groupId, String artifactId, String basePackage, boolean useLombok, boolean useTracer, boolean useRedis, boolean enableFlyway, boolean serverLibrary) {
        this.url = url;
        this.groupId = groupId;
        this.artifactId = artifactId;
        this.basePackage = basePackage;
        this.useLombok = useLombok;
        this.useTracer = useTracer;
        this.useRedis = useRedis;
        this.enableFlyway = enableFlyway;
        this.serverLibrary = serverLibrary;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }

    public String getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    public boolean isUseLombok() {
        return useLombok;
    }

    public void setUseLombok(boolean useLombok) {
        this.useLombok = useLombok;
    }

    public boolean isUseTracer() {
        return useTracer;
    }

    public void setUseTracer(boolean useTracer) {
        this.useTracer = useTracer;
    }

    public boolean isUseRedis() {
        return useRedis;
    }

    public void setUseRedis(boolean useRedis) {
        this.useRedis = useRedis;
    }

    public boolean isEnableFlyway() {
        return enableFlyway;
    }

    public void setEnableFlyway(boolean enableFlyway) {
        this.enableFlyway = enableFlyway;
    }

    public boolean isServerLibrary() {
        return serverLibrary;
    }

    public void setServerLibrary(boolean serverLibrary) {
        this.serverLibrary = serverLibrary;
    }

    @Override
    public String toString() {
        return "org.egov.codegen.Config{" +
                "url='" + url + '\'' +
                ", groupId='" + groupId + '\'' +
                ", artifactId='" + artifactId + '\'' +
                ", basePackage='" + basePackage + '\'' +
                ", useLombok=" + useLombok +
                ", useTracer=" + useTracer +
                ", useRedis=" + useRedis +
                ", enableFlyway=" + enableFlyway +
                ", serverLibrary=" + serverLibrary +
                '}';
    }


}
