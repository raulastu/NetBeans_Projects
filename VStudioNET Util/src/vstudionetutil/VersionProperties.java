package vstudionetutil;

public class VersionProperties {

    String CodeBehind;

    public VersionProperties(Version v) {
        if (v == Version.v2008) {
            this.CodeBehind = "CodeBehind";
        }else if(v==Version.v2005){
            this.CodeBehind = "CodeFile";
        }

    }
}
