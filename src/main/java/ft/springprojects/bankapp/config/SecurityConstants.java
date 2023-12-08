package ft.springprojects.bankapp.config;

public interface SecurityConstants {

    // just for the sake of simplicity, not a production approach
    String JWT_KEY = "yMRw4oeQQHjc2Mw5QIYn8Plhd05jgo0kgOcRWCCtPBXriNQeiFy5BMv2pRlT4RdF";
    String JWT_HEADER = "jwt";
    long EXPIRATION_TIME = 28_800_000;
}
